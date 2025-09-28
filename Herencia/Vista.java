import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Vista {
    private Scanner scanner;
    
    public Vista() {
        this.scanner = new Scanner(System.in);
    }
    
    public String menuTurno(Combatiente c, Batalla b) {
        if (!(c instanceof Jugador)) {
            return "";
        }
        
        System.out.println("\nTURNO DE " + c.getNombre().toUpperCase());
        System.out.println("¿Que quieres hacer?");
        System.out.println("1. Atacar");
        System.out.println("2. Usar item");
        System.out.println("3. Pasar turno");
        System.out.println("4. Salir de la batalla");
        System.out.print("Elige una opción (1-4): ");
        
        return scanner.nextLine().trim();
    }
    
    public int seleccionarObjetivo(List<? extends Combatiente> objetivos, String tipo) {
        System.out.println("\nSELECCIONAR " + tipo.toUpperCase());
        
        for (int i = 0; i < objetivos.size(); i++) {
            System.out.println((i + 1) + ". " + objetivos.get(i));
        }
        
        System.out.print("Selecciona el número del objetivo: ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            if (opcion >= 1 && opcion <= objetivos.size()) {
                return opcion - 1; // Convertir a índice base 0
            }
        } catch (NumberFormatException e) {
        }
        
        return -1; 
    }
    
    public int seleccionarItem(Jugador jugador) {
        List<Item> items = jugador.getInventario().listar();
        
        if (items.isEmpty()) {
            System.out.println("No tienes items disponibles.");
            return -1;
        }
        
        // Filtrar solo items usables
        List<Item> itemsUsables = new ArrayList<>();
        for (Item item : items) {
            if (item.puedeUsarse()) {
                itemsUsables.add(item);
            }
        }
        
        if (itemsUsables.isEmpty()) {
            System.out.println("No tienes items usables.");
            return -1;
        }
        
        System.out.println("\nTUS ITEMS");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.puedeUsarse()) {
                System.out.println((i + 1) + ". " + item);
            }
        }
        
        System.out.print("Selecciona el número del item (0 para cancelar): ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            if (opcion == 0) {
                return -1; 
            }
            if (opcion >= 1 && opcion <= items.size()) {
                // Verificar que el item seleccionado es usable
                Item itemSeleccionado = items.get(opcion - 1);
                if (itemSeleccionado.puedeUsarse()) {
                    return opcion - 1; // Convertir a índice base 0
                }
            }
        } catch (NumberFormatException e) {
        }
        
        return -1; 
    }
    
    public String mostrarEstado(Batalla b) {
        StringBuilder estado = new StringBuilder();
        
        estado.append("\n" + "=".repeat(50) + "\n");
        estado.append("ESTADO DE LA BATALLA\n");
        estado.append("=".repeat(50) + "\n");

        estado.append("JUGADORES:\n");
        for (Jugador jugador : b.getJugadores()) {
            estado.append("  " + jugador + "\n");
            if (!jugador.getEfectos().isEmpty()) {
                estado.append("    Efectos: ");
                for (EfectoTemporal efecto : jugador.getEfectos()) {
                    estado.append(efecto.getTipo() + "(" + efecto.getTurnos() + ") ");
                }
                estado.append("\n");
            }
        }
        
        estado.append("\nENEMIGOS:\n");
        for (Enemigo enemigo : b.getEnemigos()) {
            estado.append("  " + enemigo + "\n");
            if (!enemigo.getEfectos().isEmpty()) {
                estado.append("    Efectos: ");
                for (EfectoTemporal efecto : enemigo.getEfectos()) {
                    estado.append(efecto.getTipo() + "(" + efecto.getTurnos() + ") ");
                }
                estado.append("\n");
            }
        }
    
        estado.append("\nÚLTIMAS ACCIONES:\n");
        List<String> ultimas = b.getLog().ultimas();
        for (String accion : ultimas) {
            if (accion != null && !accion.isEmpty()) {
                estado.append("  > " + accion + "\n");
            }
        }
        
        return estado.toString();
    }
    
    public String mensajeError(String m) {
        return "ERROR: " + m;
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void limpiarPantalla() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
    
    public void pausar() {
        System.out.println("\nPresiona Enter para continuar");
        scanner.nextLine();
    }
    
    public Rol seleccionarRol() {
        System.out.println("SELECCIÓN DE ROL");
        System.out.println("1. Guerrero - Mucha vida y ataque, pocos items");
        System.out.println("2. Explorador - Vida y ataque normales, muchos items");
        System.out.print("Selecciona tu rol (1-2): ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            switch (opcion) {
                case 1:
                    return Rol.GUERRERO;
                case 2:
                    return Rol.EXPLORADOR;
                default:
                    System.out.println("Opción inválida. Seleccionando Explorador por defecto.");
                    return Rol.EXPLORADOR;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida. Seleccionando Explorador por defecto.");
            return Rol.EXPLORADOR;
        }
    }
    
    public String pedirNombre() {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine().trim();
        return nombre.isEmpty() ? "Aventurero" : nombre;
    }
}