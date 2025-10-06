import java.util.List;

/**
 * Vista para interactuar con la consola
 */
public class VistaConsola {
    
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
    
    public void mostrarError(String msg) {
        System.out.println("ERROR: " + msg);
    }
    
    public void mostrarExito(String msg) {
        System.out.println(msg);
    }
    
    public void mostrarOperacionProceso() {
        System.out.println("Operación de proceso completada");
    }
    
    public void mostrarLista(List<Proceso> lista) {
        if (lista.isEmpty()) {
            mostrarMensaje("No hay procesos en la lista");
            return;
        }
        
        System.out.println(" LISTA DE PROCESOS (" + lista.size() + ")");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }
    
    public void mostrarResumen(Planificador planificador) {
        System.out.println("RESUMEN DEL SISTEMA");
        System.out.println("Total de procesos: " + planificador.contarProcesos());
        System.out.println("Procesos activos: " + planificador.contarProcesosActivos());
        System.out.println("Lista de procesos:");
        mostrarLista(planificador.getProcesos());
    }
    
    public void mostrarEjecucionProceso(Proceso proceso) {
        System.out.println("Ejecutando: " + proceso.getNombre() + " (PID: " + proceso.getPid() + ")");
        
        // Mostrar comportamiento específico según el tipo de proceso
        if (proceso instanceof ProcesoCPU) {
            ProcesoCPU cpu = (ProcesoCPU) proceso;
            System.out.println(" Uso de CPU: " + cpu.getUsoCPU() + "%");
            System.out.println(" Optimizado: " + (cpu.isOptimizado() ? "Sí" : "No"));
            System.out.println(" Realizando cálculos intensivos");
        } else if (proceso instanceof ProcesoIO) {
            ProcesoIO io = (ProcesoIO) proceso;
            System.out.println("  Dispositivo: " + io.getDispositivo());
            System.out.println("  Esperando IO: " + (io.isEsperandoIO() ? "Sí" : "No"));
            System.out.println("  Realizando operaciones de entrada/salida");
        } else if (proceso instanceof Daemon) {
            Daemon daemon = (Daemon) proceso;
            System.out.println(" Servicio: " + daemon.getServicio());
            System.out.println(" jecuciones: " + daemon.getContadorEjecuciones());
            System.out.println(" Ejecutando en segundo plano...");
        }
        System.out.println("---");
    }
    
    public void mostrarEstadisticas(Planificador planificador) {
        int cpus = 0, ios = 0, daemons = 0;
        
        for (Proceso p : planificador.getProcesos()) {
            if (p instanceof ProcesoCPU) cpus++;
            else if (p instanceof ProcesoIO) ios++;
            else if (p instanceof Daemon) daemons++;
        }
        
        System.out.println("ESTADÍSTICAS");
        System.out.println("Procesos CPU: " + cpus);
        System.out.println("Procesos IO: " + ios);
        System.out.println("Daemons: " + daemons);
        System.out.println("Total: " + planificador.contarProcesos());
    }
}