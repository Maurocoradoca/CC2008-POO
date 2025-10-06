public class Daemon extends Proceso {
    private String servicio;
    private int contadorEjecuciones;
    
    public Daemon(int pid, String nombre, String servicio) {
        super(pid, nombre);
        this.servicio = servicio;
        this.contadorEjecuciones = 0;
    }
    
    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    public int getContadorEjecuciones() { return contadorEjecuciones; }
    
    public void reiniciar() {
        if (isActivo()) {
            this.contadorEjecuciones = 0; // Reiniciar contador
        }
    }
    
    @Override
    public void ejecutar() {
        if (isActivo()) {
            this.contadorEjecuciones++;
            // Simular trabajo en segundo plano
        }
    }
    
    @Override
    public void detener() {
        super.detener();
        reiniciar(); // Reiniciar al detener
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Daemon, servicio=" + servicio + 
               ", ejecuciones=" + contadorEjecuciones + "]";
    }
}