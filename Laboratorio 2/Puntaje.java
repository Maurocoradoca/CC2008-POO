public class Puntaje {
    private int p1Pares;
    private int p2Pares;
    private int ganador;

    public Puntaje(int p1Pares, int p2Pares, int ganador) {
        this.p1Pares = p1Pares;
        this.p2Pares = p2Pares;
        this.ganador = ganador;
    }

    public int ganador() { return ganador; }

    public String resumen(String nombre1, String nombre2) {
        if (ganador == -1) {
            return "Empate " + nombre1 + " " + p1Pares + " - " + p2Pares + " " + nombre2;
        } else if (ganador == 0) {
            return "Ganó " + nombre1 + " (" + p1Pares + " a " + p2Pares + ")";
        } else {
            return "Ganó " + nombre2 + " (" + p2Pares + " a " + p1Pares + ")";
        }
    }

    public int getP1Pares() { return p1Pares; }
    public int getP2Pares() { return p2Pares; }
    public int getGanador() { return ganador; }
}
