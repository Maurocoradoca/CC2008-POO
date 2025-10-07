import java.util.List;

public class VistaConsola {
    
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
    
    public void mostrarError(String msg) {
        System.out.println("ERROR: " + msg);
    }
    
    public void mostrarOperacionProceso() {
        System.out.println("Operación de proceso completada");
    }
    
    public void mostrarLista(List<Proceso> lista) {
        if (lista.isEmpty()) {
            mostrarMensaje("No hay procesos en la lista");
            return;
        }
        
        System.out.println("LISTA DE PROCESOS (" + lista.size() + ")");
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
        
        if (proceso instanceof ProcesoCPU) {
            ProcesoCPU cpu = (ProcesoCPU) proceso;
            System.out.println("Uso de CPU: " + cpu.getUsoCPU() + "%");
            System.out.println("Optimizado: " + (cpu.isOptimizado() ? "Sí" : "No"));
            System.out.println("Realizando cálculos intensivos");
        } else if (proceso instanceof ProcesoIO) {
            ProcesoIO io = (ProcesoIO) proceso;
            System.out.println("Dispositivo: " + io.getDispositivo());
            System.out.println("Esperando IO: " + (io.isEsperandoIO() ? "Sí" : "No"));
            System.out.println("Realizando operaciones de entrada/salida");
        } else if (proceso instanceof Daemon) {
            Daemon daemon = (Daemon) proceso;
            System.out.println("Servicio: " + daemon.getServicio());
            System.out.println("Ejecuciones: " + daemon.getContadorEjecuciones());
            System.out.println("Ejecutando en segundo plano");
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
    
    public void mostrarSeparador() {
        System.out.println("\n" + "=".repeat(50));
    }
    
    public void mostrarTitulo(String titulo) {
        System.out.println(titulo);
    }
    
    public void mostrarInicioSimulacion() {
        mostrarSeparador();
        System.out.println("INICIANDO SIMULADOR DE SISTEMA OPERATIVO");
        mostrarSeparador();
    }
    
    public void mostrarFinSimulacion() {
        mostrarSeparador();
        System.out.println("SIMULACIÓN COMPLETADA EXITOSAMENTE");
        mostrarSeparador();
    }
    
    public void mostrarProcesosCreados() {
        mostrarMensaje("Procesos de prueba creados exitosamente");
    }
    
    public void mostrarEjecucionCiclos(int ciclos) {
        mostrarMensaje("Ejecutando todos los procesos por " + ciclos + " ciclos...");
    }
    
    public void mostrarCicloActual(int ciclo) {
        System.out.println("CICLO " + ciclo );
    }
    
    public void mostrarDemostracionEliminacion() {
        mostrarTitulo("DEMOSTRACIÓN ELIMINACIÓN");
    }
    
    public void mostrarDemostracionFuncionalidades() {
        mostrarTitulo("DEMOSTRACIÓN FUNCIONALIDADES ESPECÍFICAS");
    }
    
    public void mostrarOptimizacionCPU(double antes, double despues) {
        mostrarMensaje("Antes de optimizar: " + antes + "%");
        mostrarMensaje("Después de optimizar: " + despues + "%");
    }
    
    public void mostrarReinicioDaemon(int antes, int despues) {
        mostrarMensaje("Ejecuciones del daemon antes: " + antes);
        mostrarMensaje("Ejecuciones del daemon después: " + despues);
    }
    
    public void mostrarIntentoEliminacion(int pid) {
        mostrarMensaje("Intentando eliminar proceso PID: " + pid);
    }
    
    public void mostrarResultadoEliminacion(boolean eliminado, int pid) {
        if (eliminado) {
            mostrarMensaje("Proceso con PID " + pid + " eliminado exitosamente");
        } else {
            mostrarError("No se encontró proceso con PID " + pid);
        }
    }
}