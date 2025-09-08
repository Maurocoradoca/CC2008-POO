public class Vista {
    public String menu() {
        return """
               === MEMORIA ===
               1) Jugar turno
               2) Mostrar tablero
               3) Mostrar marcador
               4) Terminar partida
               5) Nueva partida (con las mismas dimensiones)
               0) Salir
               Opción: """;
    }

    public String renderTablero(String raw) {
        return "\nTablero:\n" + raw;
    }

    public String mensajeInfo(String m) {
        return "Informacion: " + m;
    }

    public String mensajeError(String m) {
        return "Error: " + m;
    }

    public String mensajeTurno(String nombre, boolean acierto) {
        return acierto ? "Acierto de " + nombre + " Conserva su turno"
                       : "Falló el intento de " + nombre + ". Cambia el turno";
    }
}
