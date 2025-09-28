public class EfectoTemporal {
    private Efecto tipo;
    private int turnos;
    private int magnitud;
    
    public EfectoTemporal(Efecto tipo, int turnos, int magnitud) {
        this.tipo = tipo;
        this.turnos = turnos;
        this.magnitud = magnitud;
    }
    
    public void tick() {
        turnos--;
    }
    
    // Getters
    public Efecto getTipo() { 
        return tipo; 
    }
    
    public int getTurnos() { 
        return turnos; 
    }
    
    public int getMagnitud() { 
        return magnitud; 
    }
    
    public boolean haExpirado() {
        return turnos <= 0;
    }
}