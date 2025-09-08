import java.util.List;

public class Controlador {
    private final JuegoMemoria juego;
    private final Vista vista;

    public Controlador(JuegoMemoria juego, Vista vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public String iniciarNuevaPartida(int f, int c, List<String> pool, String j1, String j2) {
        boolean ok = juego.iniciar(f, c, pool, j1, j2);
        return ok ? vista.mensajeInfo("Partida iniciada con " + f + "x" + c + ".")
                  : vista.mensajeError("No se pudo iniciar la partida Verifica los datos    ");
    }

    public String turno(int r1, int c1, int r2, int c2) {
        boolean acierto = juego.jugarTurno(new Movimiento(r1, c1, r2, c2));
        String despues = juego.tableroSnapshot();
        String nombre = juego.getJugadores()[0].getNombre(); 
        try {
            nombre = juego.getJugadores()[(acierto) ? juego.getJugadores()[0].getNombre().equals(nombre) ? 0 : 1
                                                 : (juego.getJugadores()[0].getNombre().equals(nombre) ? 1 : 0)].getNombre();
        } catch (Exception ignore) {}
        return vista.renderTablero(despues) + "\n" + vista.mensajeTurno(nombre, acierto);
    }

    public String reporteFinal() {
        Puntaje p = juego.puntajeFinal();
        String n1 = juego.getJugadores()[0].getNombre();
        String n2 = juego.getJugadores()[1].getNombre();
        return vista.mensajeInfo("Resultado final: " + p.resumen(n1, n2));
    }

    public String mostrarMarcador() {
        return vista.mensajeInfo(juego.marcadorActual());
    }

    public String mostrarTablero() {
        return vista.renderTablero(juego.tableroSnapshot());
    }

    public void reiniciarPartida() {
        juego.reiniciarPartidaManteniendoSesion();
    }

    public boolean fin() {
        return juego.esFinDePartida();
    }
}
