package EJERCICIOENCLASE;

import java.util.Random;

public class Battleship {

    private final int N;
    private final int BARCOS;
    private final int DISPAROS_MAX;

    private final Tablero tablero;
    private final Consola vista;
    private final Random rnd = new Random();
    private int disparosUsados = 0;

    public Battleship(int n, int barcos, int disparosMax) {
        this.N = n;
        this.BARCOS = barcos;
        this.DISPAROS_MAX = disparosMax;
        this.tablero = new Tablero(n);
        this.vista = new Consola();
    }

    public void jugar() {
        this.tablero.colocarBarcos(this.BARCOS, this.rnd);
        this.vista.mostrarBienvenida(this.N, this.BARCOS, this.DISPAROS_MAX);
        this.disparosUsados = 0;

        while (disparosUsados < this.DISPAROS_MAX && !this.tablero.todosHundidos()) {
            this.vista.dibujarTablero(this.tablero);

            String entrada = this.vista.pedirEntrada("Disparo " + (this.disparosUsados + 1) + " de "
                    + this.DISPAROS_MAX + ": ");
            if (entrada == null) entrada = "";
            entrada = entrada.trim().toUpperCase();


            if (entrada.equals("SALIR")) {
                this.vista.mostrarMensaje("Gracias por jugar. ¡Hasta la próxima!");
                return;
            }
            if (entrada.equals("REVELAR")) {
                this.vista.dibujarTableroTrampa(this.tablero);
                continue;
            }

            int[] input = this.parsear(entrada);
            if (input == null) {
                this.vista.mostrarMensaje("Entrada inválida. Usa formato como A1, B3, etc.");
                continue;
            }

            int fila = input[0];
            int col = input[1];

            if (this.tablero.yaDisparada(fila, col)) {
                this.vista.mostrarMensaje("Ya disparaste a esa casilla. ¡Prueba otra!");
                continue; 
            }


            boolean acierto = this.tablero.disparar(fila, col);
            this.disparosUsados++;

            if (acierto) {
                this.vista.mostrarMensaje("¡Le diste a un barco! ");
                if (this.tablero.todosHundidos()) {
                    this.vista.dibujarTablero(this.tablero);
                    this.vista.mostrarMensaje("¡Felicidades! Hundiste los " + this.BARCOS + " barcos en "
                            + this.disparosUsados + " disparos.");
                    return;
                }
            } else {
                this.vista.mostrarMensaje("Agua");
            }
        }

        if (!this.tablero.todosHundidos()) {
            this.vista.mostrarMensaje("\nTe quedaste sin disparos. Estos eran los barcos:");
            this.vista.dibujarTableroTrampa(this.tablero);
            this.vista.mostrarMensaje("Barcos hundidos: " + this.tablero.barcosHundidos() + " de " + this.BARCOS);
            this.vista.mostrarMensaje("¡Suerte a la próxima!");
        }
    }
    private int[] parsear(String s) {
        if (s.length() < 2 || s.length() > 3) {
            return null;
        }

        char letra = s.charAt(0);
        if (letra < 'A' || letra >= 'A' + this.N) {
            return null;
        }

        String numero = s.substring(1);
        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return null;
            }
        }

        int col = Integer.parseInt(numero);
        if (col < 1 || col > this.N) {
            return null;
        }

        int fila = letra - 'A';
        return new int[]{fila, col - 1};
    }
}
