//Interfaz Medible
//Define el contrato para equipos que pueden realizar mediciones

public interface Medible {
    //Realiza una medición y devuelve el resultado como una cadena de texto
    String realizarMedicion();
    
    //Obtiene la última lectura realizada por el sensor
    double obtenerUltimaLectura();
}