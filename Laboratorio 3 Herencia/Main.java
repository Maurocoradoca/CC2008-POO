// Clase Main 
// Punto de entrada del programa 
// Mauricio Corado 25218
// 13/10/2025

public class Main {
    public static void main(String[] args) {
        VistaHospital vista = new VistaHospital();
        HospitalManager hospital = new HospitalManager();
        ControladorHospital controlador = new ControladorHospital(vista, hospital);

        int opcion;
        do {
            vista.limpiarPantalla();
            opcion = vista.menu();
            controlador.ejecutarOpcion(opcion);
            if (opcion != 0) {
                vista.pausar();
            }
        } while (opcion != 0);

        vista.cerrar();
    }
}
