import java.util.Objects;
// Esta es una clase abstracta que representa un proceso en un sistema operativo.
public abstract class Proceso {
    private int pid;
    private String nombre;

    public Proceso(int pid, String nombre) {
        this.pid = pid;
        this.nombre = nombre;
    }

    //Getters y Setters
    public int getPid() { return pid; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // Método abstracto que será implementado por las subclases
    public abstract void ejecutar();
    
    // Método común para todos los procesos
    public void dstener() {
        System.out.println("Deteniendo proceso: " + nombre);
    }
    
    @Override
    public String toString() {
        return "Proceso {pid=" + pid + ", nombre='" + nombre + "'}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proceso processo = (Proceso) o;
        return pid == processo.pid;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
        
}

