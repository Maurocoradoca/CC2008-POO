//Clase SensorSuelo
//Representa un sensor de suelo que puede medir y registrar datos
public class SensorSuelo extends Equipo implements Medible, Registrable {
    private double profundidad;
    private String tipoSuelo;
    private double ultimaLectura;
    private String historialRegistros;
    
    //Constructor completo de SensorSuelo
    public SensorSuelo(String id, String nombre, String fabricante, String modelo, 
                       double consumo, double profundidad, String tipoSuelo) {
        super(id, nombre, fabricante, modelo, consumo);
        this.profundidad = profundidad;
        this.tipoSuelo = tipoSuelo;
        this.ultimaLectura = 0.0;
        this.historialRegistros = "";
    }
    
    // Getters y Setters específicos
    public double getProfundidad() {
        return profundidad;
    }
    
    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    
    public String getTipoSuelo() {
        return tipoSuelo;
    }
    
    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== SENSOR DE SUELO ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Profundidad: ").append(profundidad).append(" metros\n");
        info.append("Tipo de Suelo: ").append(tipoSuelo).append("\n");
        info.append("Última Lectura: ").append(ultimaLectura).append(" %\n");
        return info.toString();
    }
    
    // Implementación de Medible
    @Override
    public String realizarMedicion() {
        ultimaLectura = 15.0 + Math.random() * 70.0; // Humedad entre 15% y 85%
        return "Medición de humedad del suelo: " + String.format("%.2f", ultimaLectura) + "%";
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
    
    // Implementación de Registrable
    @Override
    public void registrarDatos() {
        String registro = "Registro - Humedad: " + String.format("%.2f", ultimaLectura) + "% | ";
        historialRegistros += registro;
    }
    
    @Override
    public String obtenerHistorial() {
        return historialRegistros.isEmpty() ? "Sin registros" : historialRegistros;
    }
    
    @Override
    public void limpiarRegistros() {
        historialRegistros = "";
    }
}
