public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("    BIENVENIDO AL SIMULADOR DE BATALLA RPG");
        System.out.println("=".repeat(60));
        
        Vista vista = new Vista();
        Batalla batalla = new Batalla();
        Controlador controlador = new Controlador(batalla, vista);
        
        // Crear jugador
        String nombre = vista.pedirNombre();
        Rol rol = vista.seleccionarRol();
        Jugador jugador = new Jugador(nombre, rol);
        
        vista.mostrarMensaje("\nBienvenido, " + jugador.getNombre() + 
                           " el " + rol );
        vista.mostrarMensaje("Preparándose para la batalla");
        vista.pausar();
        
        // Iniciar batalla
        batalla.agregarJugador(jugador);
        batalla.iniciar();
        
        vista.mostrarMensaje("\nLos enemigos han aparecido");
        vista.mostrarMensaje("Enemigos en esta batalla:");
        for (Enemigo enemigo : batalla.getEnemigos()) {
            vista.mostrarMensaje("  - " + enemigo.toString());
        }
        vista.pausar();
        
        // Bucle principal de batalla 
        boolean batallaActiva = true;
        int turnosTranscurridos = 0;
        int maxTurnos = 200; 
        
        for (int ronda = 1; batallaActiva && ronda <= maxTurnos && !batalla.estaTerminada(); ronda++) {
            vista.mostrarMensaje("\n--- RONDA " + ronda + " ---");
            
            // Aplicar efectos al inicio de cada ronda
            batalla.aplicarEfectos();
            
            // Verificar si la batalla terminó por efectos
            if (batalla.estaTerminada()) {
                break;
            }
            
            // Cada combatiente toma su turno en esta ronda
            List<Combatiente> combatientesVivos = new ArrayList<>();
            combatientesVivos.addAll(batalla.getJugadores());
            combatientesVivos.addAll(batalla.getEnemigos());
            
            for (Combatiente actual : combatientesVivos) {
                if (!actual.estaVivo() || batalla.estaTerminada()) {
                    continue;
                }
                
                String resultado = controlador.turnoDE(actual);
                
                // Verificar si el jugador quiere salir
                if (resultado.equals("SALIR")) {
                    vista.mostrarMensaje("Has decidido huir de la batalla");
                    batallaActiva = false;
                    break;
                }
                
                // Si es un enemigo, mostrar lo que hizo
                if (actual instanceof Enemigo && !resultado.isEmpty()) {
                    vista.mostrarMensaje(resultado);
                    vista.pausar();
                }
                
                turnosTranscurridos++;
                
                // Verificar condiciones de finalización después de cada turno
                if (batalla.estaTerminada()) {
                    batallaActiva = false;
                    break;
                }
            }
        }
        
        // Mostrar resultado final
        if (turnosTranscurridos >= maxTurnos && !batalla.estaTerminada()) {
            vista.mostrarMensaje("La batalla se prolongó demasiado y terminó en empate");
        } else {
            vista.mostrarMensaje(controlador.reporteFinal());
        }
        
        vista.mostrarMensaje("\nGracias por jugar");
        vista.mostrarMensaje("Presiona Enter para salir");
        vista.pausar();
        
        System.exit(0);
    }
}