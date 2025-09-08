import java.util.ArrayList;
import java.util.List;

public class JuegoMemoria {
    private Tablero tablero;
    private Jugador[] jugadores;        
    private EstadoDelJuego estado;
    private List<Puntaje> sesion;       
    private List<String> poolGuardado;  
    private int filas, columnas;

    public JuegoMemoria() {
        this.sesion = new ArrayList<>();
    }

    public boolean iniciar(int filas, int columnas, List<String> pool, String j1, String j2) {
    
        if (filas < 4 || columnas < 4 || filas % 2 != 0 || columnas % 2 != 0) return false;
        if (pool == null || pool.size() != (filas * columnas) / 2) return false;
        if (j1 == null || j1.isBlank() || j2 == null || j2.isBlank()) return false;

        try {
            this.filas = filas;
            this.columnas = columnas;
            this.poolGuardado = new ArrayList<>(pool);

            this.tablero = new Tablero(filas, columnas, poolGuardado);
            this.jugadores = new Jugador[] { new Jugador(j1.trim()), new Jugador(j2.trim()) };
            int totalPares = (filas * columnas) / 2;
            this.estado = new EstadoDelJuego(totalPares);
            jugadores[0].reset();
            jugadores[1].reset();
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    
    public boolean jugarTurno(Movimiento mv) {
        if (mv == null || !mv.esDistintoPar()) {
            return false; 
        }
        int r1 = mv.getR1(), c1 = mv.getC1(), r2 = mv.getR2(), c2 = mv.getC2();

        if (tablero.estaEmparejada(r1, c1) || tablero.estaEmparejada(r2, c2)) {
            return false;
        }

        String s1 = tablero.revelar(r1, c1);
        String s2 = tablero.revelar(r2, c2);

        if (s1.isEmpty() || s2.isEmpty()) {
            tablero.ocultar(r1, c1);
            tablero.ocultar(r2, c2);
            return false;
        }

        boolean acierto = false;
        if (s1.equals(s2)) {
            acierto = tablero.marcarPar(r1, c1, r2, c2);
            if (acierto) {
                estado.incEncontrados();
                jugadores[estado.actual()].anadirPar();
                estado.mantenerTurno(); 
            }
        } else {
            tablero.ocultar(r1, c1);
            tablero.ocultar(r2, c2);
            estado.cambiarTurno();
        }

        return acierto;
    }

    public String tableroSnapshot() {
        return tablero.snapshot();
    }

    public boolean esFinDePartida() {
        return estado.terminado();
    }

    public String marcadorActual() {
        return jugadores[0].toString() + " | " + jugadores[1].toString() +
               "  | Turno: " + jugadores[estado.actual()].getNombre();
    }

    public Puntaje puntajeFinal() {
        int p1 = jugadores[0].getPares();
        int p2 = jugadores[1].getPares();
        int ganador;
        if (p1 == p2) ganador = -1;
        else if (p1 > p2) ganador = 0;   
        else ganador = 1;               
        Puntaje p = new Puntaje(p1, p2, ganador);
        sesion.add(p);
        return p;
    }

    public void reiniciarPartidaManteniendoSesion() {
        iniciar(this.filas, this.columnas, this.poolGuardado,
                jugadores[0].getNombre(), jugadores[1].getNombre());
    }

    public List<Puntaje> getSesion() {
        return sesion;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
}
