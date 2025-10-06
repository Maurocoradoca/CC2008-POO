//Proceso que corre en segundo plano, manejando servicios del sistema
public class Daemon extends Proceso {
    private String servicio;
    
    public Daemon(int pid, String nombre, String servicio) {
        super(pid, nombre);
        this.servicio = servicio;
    }
    
    public String getServiceio() { return servicio; }
    public void setService(String servicio) { this.servicio = servicio; }
    
    public void reiniciar() {
        System.out.println("Reiniciando servicio: " + servicio);
    }
    
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando daemon '" + getNombre() + 
                          "' - Servicio: " + servicio);
        System.out.println("  Monitoreando sistema en segundo plano...");
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Daemon, servicio=" + servicio + "]";
    }
}