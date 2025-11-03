//Clase Main
//Punto de entrada del sistema de gestión de equipos agro-tecnológicos
//Mauricio Corado 
//2/11/2025
public class Main {
    
    //Método principal que inicia la aplicación
    public static void main(String[] args) {
        // Crear el controlador (patrón MVC)
        Controlador controlador = new Controlador();
        
        // Inicializar el sistema con la carga inicial
        controlador.inicializarSistema();
        
        // Ejecutar el sistema
        controlador.ejecutar();
    }
}