 //Interfaz Registrable
 //Define el contrato para equipos que pueden registrar datos históricos

public interface Registrable {
    //Registra los datos actuales en el historial
    void registrarDatos();
    
    //Obtiene el historial completo de registros
    String obtenerHistorial();
    
    //limpia todos los registros almacenados
    void limpiarRegistros();
}
