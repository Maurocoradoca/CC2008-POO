import java.util.Arrays;

// Controlador principal que coordina el modelo y la vista
 
public class ControladorProceso {
    private Planificador planificador;
    private VistaConsola vista;
    
    public ControladorProceso(Planificador planificador, VistaConsola vista) {
        this.planificador = planificador;
        this.vista = vista;
    }
    
    public void iniciarSimulacion() {
        vista.mostrarInicioSimulacion();
        crearProcesosDePrueba();
    }
    
    public void crearProcesosDePrueba() {
        // Crear procesos de ejemplo para demostrar polimorfismo
        ProcesoCPU proceso1 = new ProcesoCPU(101, "Compilador Java", 85.5);
        ProcesoIO proceso2 = new ProcesoIO(102, "Lectura Disco", "HDD");
        Daemon proceso3 = new Daemon(103, "SysLogger", "Registro de Eventos");
        ProcesoCPU proceso4 = new ProcesoCPU(104, "Render 3D", 95.0);
        ProcesoIO proceso5 = new ProcesoIO(105, "Conexión Red", "Wi-Fi");
        Daemon proceso6 = new Daemon(106, "Antivirus", "Escaneo en Tiempo Real");
        
        planificador.agregarProceso(Arrays.asList(proceso1, proceso2, proceso3, proceso4, proceso5, proceso6));
        vista.mostrarProcesosCreados();
    }
    
    public void ejecutarSimulacionConResumen() {
        vista.mostrarTitulo("RESUMEN INICIAL");
        vista.mostrarResumen(planificador);
        vista.mostrarEjecucionCiclos(3);
        
        // Ejecutar múltiples ciclos
        for (int ciclo = 1; ciclo <= 3; ciclo++) {
            vista.mostrarCicloActual(ciclo);
            for (Proceso proceso : planificador.getProcesosActivos()) {
                vista.mostrarEjecucionProceso(proceso);
                proceso.ejecutar(); // Ejecución polimórfica
            }
        }
        
        vista.mostrarTitulo("ESTADÍSTICAS FINALES");
        vista.mostrarEstadisticas(planificador);
    }
    
    public void eliminarProcesoDesdeVista(int pid) {
        vista.mostrarIntentoEliminacion(pid);
        boolean eliminado = planificador.eliminarProceso(pid);
        vista.mostrarResultadoEliminacion(eliminado, pid);
    }
    
    public void demostrarFuncionalidadesEspecificas() {
        vista.mostrarDemostracionFuncionalidades();
        
        // Demostrar optimización de CPU
        ProcesoCPU cpu = (ProcesoCPU) planificador.buscarProcesoPorPid(101);
        if (cpu != null) {
            double usoAntes = cpu.getUsoCPU();
            cpu.optimizar();
            double usoDespues = cpu.getUsoCPU();
            vista.mostrarOptimizacionCPU(usoAntes, usoDespues);
        }
        
        // Demostrar reinicio de daemon
        Daemon daemon = (Daemon) planificador.buscarProcesoPorPid(103);
        if (daemon != null) {
            int ejecucionesAntes = daemon.getContadorEjecuciones();
            daemon.reiniciar();
            int ejecucionesDespues = daemon.getContadorEjecuciones();
            vista.mostrarReinicioDaemon(ejecucionesAntes, ejecucionesDespues);
        }
    }
}