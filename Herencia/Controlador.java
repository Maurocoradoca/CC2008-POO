import java.util.List;

public class Controlador {
    private Batalla batalla;
    private Vista vista;
    
    public Controlador(Batalla batalla, Vista vista) {
        this.batalla = batalla;
        this.vista = vista;
    }
    
    public String turnoDE(Combatiente c) {
        if (c instanceof Jugador) {
            return manejarTurnoJugador((Jugador) c);
        } else if (c instanceof Enemigo) {
            return manejarTurnoEnemigo((Enemigo) c);
        }
        return "Turno completado.";
    }
    
    private String manejarTurnoJugador(Jugador jugador) {
        vista.mostrarMensaje(vista.mostrarEstado(batalla));
        
        // Bucle para manejar opciones inválidas
        for (int intentos = 0; intentos < 5; intentos++) {
            String accion = vista.menuTurno(jugador, batalla);
            String resultado = resolverAccion(accion, jugador);
            
            // Si es un error, mostrar y permitir otro intento
            if (resultado.startsWith("ERROR:")) {
                vista.mostrarMensaje(resultado);
                continue;
            }
            
            return resultado;
        }
        
        // Si se agotan los intentos, pasar turno
        batalla.registrarAccion(jugador.getNombre() + " pasó su turno (demasiados intentos inválidos).");
        return "Turno pasado por intentos inválidos.";
    }
    
    private String manejarTurnoEnemigo(Enemigo enemigo) {
        // Los enemigos actúan automáticamente
        batalla.tomarTurno(enemigo);
        return enemigo.getNombre() + " ha tomado su turno.";
    }
    
    public String resolverAccion(String accion, Jugador jugador) {
        Acciones accionEnum = convertirStringAAccion(accion);
        
        switch (accionEnum) {
            case ATACAR:
                return manejarAtaque(jugador);
            case USAR_ITEM:
                return manejarUsoItem(jugador);
            case PASAR_TURNO:
                batalla.registrarAccion(jugador.getNombre() + " pasó su turno.");
                return "Has pasado tu turno.";
            case SALIR:
                return "SALIR";
            default:
                return vista.mensajeError("Opción inválida. Intenta de nuevo.");
        }
    }
    
    private Acciones convertirStringAAccion(String opcion) {
        switch (opcion) {
            case "1":
                return Acciones.ATACAR;
            case "2":
                return Acciones.USAR_ITEM;
            case "3":
                return Acciones.PASAR_TURNO;
            case "4":
                return Acciones.SALIR;
            default:
                return null;
        }
    }
    
    private String manejarAtaque(Jugador jugador) {
        List<Enemigo> enemigos = batalla.getEnemigos();
        
        if (enemigos.isEmpty()) {
            return "No hay enemigos para atacar.";
        }
        
        int indiceObjetivo = vista.seleccionarObjetivo(enemigos, "enemigo");
        
        if (indiceObjetivo == -1) {
            return vista.mensajeError("Objetivo inválido.");
        }
        
        Enemigo objetivo = enemigos.get(indiceObjetivo);
        int hpAntes = objetivo.getHp();
        
        jugador.atacar(objetivo);
        
        int daño = hpAntes - objetivo.getHp();
        String mensaje = jugador.getNombre() + " atacó a " + objetivo.getNombre() + 
                        " causando " + daño + " de daño.";
        
        if (!objetivo.estaVivo()) {
            mensaje += " " + objetivo.getNombre() + " ha sido derrotado!";
        }
        
        batalla.registrarAccion(mensaje);
        return mensaje;
    }
    
    private String manejarUsoItem(Jugador jugador) {
        if (!jugador.getInventario().tieneItemsUsables()) {
            return "No tienes items usables.";
        }
        
        int indiceItem = vista.seleccionarItem(jugador);
        
        if (indiceItem == -1) {
            return "Uso de item cancelado.";
        }
        
        Item item = jugador.getInventario().getItem(indiceItem);
        
        if (item == null || !item.puedeUsarse()) {
            return vista.mensajeError("Item no disponible.");
        }
        
        // Determinar objetivo según el tipo de item
        Combatiente objetivo = determinarObjetivoItem(item, jugador);
        
        if (objetivo == null) {
            return vista.mensajeError("No se pudo determinar el objetivo para el item.");
        }
        
        boolean usado = jugador.usarItem(indiceItem, objetivo);
        
        if (usado) {
            String mensaje = jugador.getNombre() + " usó " + item.getNombre() + 
                           " en " + objetivo.getNombre() + ".";
            batalla.registrarAccion(mensaje);
            return mensaje;
        } else {
            return vista.mensajeError("No se pudo usar el item.");
        }
    }
    
    private Combatiente determinarObjetivoItem(Item item, Jugador jugador) {
        // Items que se usan en uno mismo
        if (item instanceof PocionVida || item instanceof BoostAtaque || 
            item instanceof EscudoPortatil || item instanceof TonicoVigor) {
            return jugador;
        } 
        // Items que se usan en enemigos
        else {
            List<Enemigo> enemigos = batalla.getEnemigos();
            if (enemigos.isEmpty()) {
                return null;
            }
            
            int indiceObjetivo = vista.seleccionarObjetivo(enemigos, "enemigo");
            if (indiceObjetivo == -1) {
                return null;
            }
            return enemigos.get(indiceObjetivo);
        }
    }
    
    public String reporteFinal() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n" + "=".repeat(50) + "\n");
        reporte.append("RESULTADO DE LA BATALLA\n");
        reporte.append("=".repeat(50) + "\n");
        
        if (batalla.hanGanadoJugadores()) {
            reporte.append("¡VICTORIA! Has derrotado a todos los enemigos.\n");
        } else if (batalla.hanGanadoEnemigos()) {
            reporte.append("DERROTA. Todos los jugadores han caído.\n");
        } else {
            reporte.append("La batalla terminó sin un ganador claro.\n");
        }
        
        reporte.append("\nEstadísticas finales:\n");
        reporte.append("Jugadores restantes: " + batalla.getJugadores().size() + "\n");
        reporte.append("Enemigos restantes: " + batalla.getEnemigos().size() + "\n");
        
        return reporte.toString();
    }
}