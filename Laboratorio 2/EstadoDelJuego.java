public class EstadoDelJuego {
    private int jugadorActual;   
    private int totalPares;      
    private int encontrados;    

    public EstadoDelJuego(int totalPares) {
        this.totalPares = totalPares;
        this.encontrados = 0;
        this.jugadorActual = 0; 
    }

    public int actual() {
        return jugadorActual;
    }

    public void mantenerTurno() {
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == 0) ? 1 : 0;
    }

    public void incEncontrados() {
        encontrados++;
    }

    public boolean terminado() {
        return encontrados >= totalPares;
    }

    public void reiniciar(int totalPares) {
        this.totalPares = totalPares;
        this.encontrados = 0;
        this.jugadorActual = 0;
    }

    // Getters 
    public int getTotalPares() { return totalPares; }
    public int getEncontrados() { return encontrados; }
}
