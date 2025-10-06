import java.util.Objects;

// Clase abstracta que representa un proceso en el sistema operativo
public abstract class Proceso {
    private int pid;
    private String nombre;
    private boolean activo;
    
    public Proceso(int pid, String nombre) {
        this.pid = pid;
        this.nombre = nombre;
        this.activo = true;
    }
    
    // Getters y setters
    public int getPid() { return pid; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public boolean isActivo() { return activo; }
    
    // Método abstracto que será implementado por las subclases
    public abstract void ejecutar();
    
    // Método común para todos los procesos
    public void detener() {
        this.activo = false;
    }
    
    public void activar() {
        this.activo = true;
    }
    
    @Override
    public String toString() {
        return "Proceso{pid=" + pid + ", nombre='" + nombre + "', activo=" + activo + "}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proceso proceso = (Proceso) o;
        return pid == proceso.pid;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}