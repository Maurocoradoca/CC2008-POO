import java.util.ArrayList;
import java.util.List;

//Planificador que gestiona todos los procesos

public class Planificador {
    private List<Proceso> procesos;
    
    public Planificador() {
        this.procesos = new ArrayList<>();
    }
    
    // Sobrecarga de métodos 
    public void agregarProceso(Proceso proceso) {
        if (proceso != null && !procesos.contains(proceso)) {
            procesos.add(proceso);
        }
    }
    
    public void agregarProceso(List<Proceso> procesos) {
        if (procesos != null) {
            for (Proceso p : procesos) {
                agregarProceso(p); // Reutilizar el método con validación
            }
        }
    }
    
    public List<Proceso> getProcesos() {
        return new ArrayList<>(procesos);
    }
    
    public List<Proceso> getProcesosActivos() {
        List<Proceso> activos = new ArrayList<>();
        for (Proceso p : procesos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }
    
    // Ejecución polimórfica de todos los procesos
    public void ejecutarProcesos() {
        for (Proceso p : getProcesosActivos()) {
            p.ejecutar(); // Polimorfismo: se ejecuta el método específico de cada tipo
        }
    }
    
    public void ejecutarProcesos(int ciclos) {
        for (int i = 0; i < ciclos; i++) {
            ejecutarProcesos();
        }
    }
    
    public Proceso buscarProcesoPorPid(int pid) {
        for (Proceso p : procesos) {
            if (p.getPid() == pid) {
                return p;
            }
        }
        return null;
    }
    
    public boolean eliminarProceso(int pid) {
        Proceso proceso = buscarProcesoPorPid(pid);
        if (proceso != null) {
            proceso.detener(); // Detener antes de eliminar
            procesos.remove(proceso);
            return true;
        }
        return false;
    }
    
    public void detenerTodos() {
        for (Proceso p : procesos) {
            p.detener();
        }
    }
    
    public int contarProcesos() {
        return procesos.size();
    }
    
    public int contarProcesosActivos() {
        return getProcesosActivos().size();
    }
    
    public void limpiarProcesos() {
        detenerTodos();
        procesos.clear();
    }
}