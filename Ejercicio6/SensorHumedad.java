//Clase SensorHumedad
//Representa un sensor de humedad ambiental

public class SensorHumedad extends Equipo implements Medible {
    private String rangoMedicion;
    private double precision;
    private double ultimaLectura;
    
    public SensorHumedad(String id, String nombre, String fabricante, String modelo, 
                         double consumo, String rango, double precision) {
        super(id, nombre, fabricante, modelo, consumo);
        this.rangoMedicion = rango;
        this.precision = precision;
        this.ultimaLectura = 0.0;
    }
    
    public String getRangoMedicion() {
        return rangoMedicion;
    }
    
    public void setRangoMedicion(String rangoMedicion) {
        this.rangoMedicion = rangoMedicion;
    }
    
    public double getPrecision() {
        return precision;
    }
    
    public void setPrecision(double precision) {
        this.precision = precision;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== SENSOR DE HUMEDAD ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Rango de Medición: ").append(rangoMedicion).append("\n");
        info.append("Precisión: ±").append(precision).append("%\n");
        info.append("Última Lectura: ").append(ultimaLectura).append(" %\n");
        return info.toString();
    }
    
    @Override
    public String realizarMedicion() {
        ultimaLectura = 30.0 + Math.random() * 60.0; // Humedad entre 30% y 90%
        return "Medición de humedad ambiental: " + String.format("%.2f", ultimaLectura) + "%";
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
}