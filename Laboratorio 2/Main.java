import java.util.List;
import java.util.Scanner;

public class Main {


    private static final int MAX_FILAS = 10;
    private static final int MAX_COLUMNAS = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SimbolosJuego sjTemp = new SimbolosJuego();
        int maxParesCatalogo = sjTemp.cantidad();   
        int maxCasillasCatalogo = maxParesCatalogo * 2;

        int ejemploFilas = 0, ejemploColumnas = 0, mejorArea = 0;
        for (int f = Math.min(MAX_FILAS, maxCasillasCatalogo); f >= 4; f--) {
            if (f % 2 != 0) continue;
            int maxColsPermitidasPorArea = maxCasillasCatalogo / f;
            int topeC = Math.min(MAX_COLUMNAS, maxColsPermitidasPorArea);
            for (int c = topeC; c >= 4; c--) {
                if (c % 2 != 0) continue;
                int area = f * c;
                if (area <= maxCasillasCatalogo && area > mejorArea) {
                    mejorArea = area;
                    ejemploFilas = f;
                    ejemploColumnas = c;
                }
            }
        }

        System.out.println("INFORMACIÓN DEL CATÁLOGO DE SÍMBOLOS");
        System.out.println("- Símbolos disponibles (pares): " + maxParesCatalogo);
        System.out.println("- Casillas máximas por catálogo: " + maxCasillasCatalogo + " (cada par = 2 casillas)");
        System.out.println();
        System.out.println("LÍMITES DEL PROGRAMA");
        System.out.println("- Filas <= " + MAX_FILAS + ", Columnas <= " + MAX_COLUMNAS);
        if (mejorArea > 0) {
            System.out.println("- Un tablero máximo válido por ejemplo: " + ejemploFilas + " x " + ejemploColumnas
                    + " = " + mejorArea + " casillas (" + (mejorArea / 2) + " pares)");
        } else {
            System.out.println("No hay combinación par/par >=4 que cumpla catálogo + límites.");
        }
        System.out.println();

        System.out.println("Configuración de jugadores");
        System.out.print("Nombre Jugador 1: ");
        String j1 = sc.nextLine();
        System.out.print("Nombre Jugador 2: ");
        String j2 = sc.nextLine();

        int filas, columnas;
        while (true) {
            System.out.print("Filas (par, >=4 y <= " + MAX_FILAS + "): ");
            filas = leerEntero(sc);
            System.out.print("Columnas (par, >=4 y <= " + MAX_COLUMNAS + "): ");
            columnas = leerEntero(sc);

            boolean formaValida = filas >= 4 && columnas >= 4
                    && filas % 2 == 0 && columnas % 2 == 0
                    && filas <= MAX_FILAS && columnas <= MAX_COLUMNAS;

            if (!formaValida) {
                System.out.println("Error dimensiones inválidas, Deben ser pares, >=4 y no exceder los límites.");
                continue;
            }

            int area = filas * columnas;
            if (area > maxCasillasCatalogo) {
                System.out.println("Error tu catálogo soporta como máximo " + maxCasillasCatalogo
                        + " casillas (" + maxParesCatalogo + " pares). Elegiste " + area + " casillas.");
                if (mejorArea > 0) {
                    System.out.println("Sugerencia: intenta " + ejemploFilas + " x " + ejemploColumnas
                            + " (" + mejorArea + " casillas) o reduce un poco alguna dimensión.");
                }
                continue;
            }
            break; 
        }

        SimbolosJuego sj = new SimbolosJuego();
        int k = (filas * columnas) / 2;
        if (!sj.tieneSuficiente(k)) {
            System.out.println("Error o nhay símbolos suficientes en el catálogo para " + k + " pares.");
            return;
        }
        List<String> pool = sj.pick(k); 

        JuegoMemoria juego = new JuegoMemoria();
        Vista vista = new Vista();
        Controlador ctrl = new Controlador(juego, vista);

        System.out.println(ctrl.iniciarNuevaPartida(filas, columnas, pool, j1, j2));
        System.out.println(ctrl.mostrarTablero());
        System.out.println(ctrl.mostrarMarcador());

        System.out.println("\nINSTRUCCIONES");
        System.out.println("1) El tablero usa coordenadas de filas y columnas");
        System.out.println("2) Las coordenadas empiezan en 0");
        System.out.println("3) En tu turno ingresas 4 números: r1, c1, r2, c2.");
        System.out.println("   Ejemplo: r1=0, c1=1, r2=2, c2=3  (destapa (0,1) y (2,3)).");
        System.out.println("4) Si aciertas, conservas turno, Si fallas, pasa al otro jugador.\n");

        while (true) {
            System.out.print(vista.menu());
            int op = leerEntero(sc);
            if (op == 0) {
                System.out.println("Gracias por jugar");
                break;
            }
            switch (op) {
                case 1 -> {
                    System.out.print("r1: "); int r1 = leerEntero(sc);
                    System.out.print("c1: "); int c1 = leerEntero(sc);
                    System.out.print("r2: "); int r2 = leerEntero(sc);
                    System.out.print("c2: "); int c2 = leerEntero(sc);
                    System.out.println(ctrl.turno(r1, c1, r2, c2));
                    if (ctrl.fin()) System.out.println(ctrl.reporteFinal());
                }
                case 2 -> System.out.println(ctrl.mostrarTablero());
                case 3 -> System.out.println(ctrl.mostrarMarcador());
                case 4 -> System.out.println(ctrl.reporteFinal());
                case 5 -> {
                    ctrl.reiniciarPartida();
                    System.out.println(vista.mensajeInfo("Se reinició la partida con el mismo tamaño."));
                    System.out.println(ctrl.mostrarTablero());
                    System.out.println(ctrl.mostrarMarcador());
                }
                default -> System.out.println("Error, Opción inválida.");
            }
        }
        sc.close();
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un número entero: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
