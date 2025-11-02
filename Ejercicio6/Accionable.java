//Interfaz Accionable
//Define el contrato para equipos que pueden ejecutar acciones

public interface Accionable {
    //Ejecuta la acción principal del equipo
    void ejecutarAccion();
    
    //Detiene la accion en curso
    void detenerAccion();
    
    //Estado actual de la accion
    String obtenerEstadoAccion();
}