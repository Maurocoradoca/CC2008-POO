public class Main {
    public static void main(String[] args) {
        // Configuración MVC
        Planificador model = new Planificador();
        VistaConsola view = new VistaConsola();
        ControladorProceso controller = new ControladorProceso(model, view);
        
        // Iniciar simulación
        controller.iniciarSimulacion();
        
        // Ejecutar simulación completa
        controller.ejecutarSimulacionConResumen();
        
        // Demostrar funcionalidades específicas
        controller.demostrarFuncionalidadesEspecificas();
        
        // Demostrar eliminación de proceso
        view.mostrarDemostracionEliminacion();
        controller.eliminarProcesoDesdeVista(102);
        
        // Mostrar estado final
        view.mostrarTitulo("ESTADO FINAL");
        view.mostrarResumen(model);
        
        view.mostrarFinSimulacion();
    }
}