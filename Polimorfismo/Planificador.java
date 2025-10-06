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
        if (proceso != null) {
            procesos.add(proceso);
        }
    }
    
    public void agregarProceso(List<Proceso> procesos) {
        if (procesos != null) {
            this.procesos.addAll(procesos);
        }
    }
    
    public List<Proceso> getProcesos() {
        return new ArrayList<>(procesos); // Retorna copia para proteger encapsulación
    }
    
    // Ejecución polimórfica de todos los procesos
    public void ejecutarProcesos() {
        System.out.println("=== EJECUTANDO " + procesos.size() + " PROCESOS ===");
        for (Proceso p : procesos) {
            p.ejecutar(); // Polimorfismo: se ejecuta el método específico de cada tipo
            System.out.println("---");
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
            procesos.remove(proceso);
            return true;
        }
        return false;
    }
    
    public int contarProcesos() {
        return procesos.size();
    }
    
    public void limpiarProcesos() {
        procesos.clear();
    }
}