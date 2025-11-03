//Clase EstacionMeteorologica
//Representa una estación meteorológica que mide y registra datos climáticos

public class EstacionMeteorologica extends Equipo implements Medible, Registrable {
    private double alturaInstalacion;
    private double rangoComunicacion;
    private double ultimaLectura;
    private String historialRegistros;
    
    public EstacionMeteorologica(String id, String nombre, String fabricante, String modelo, 
                                 double consumo, double altura, double rango) {
        super(id, nombre, fabricante, modelo, consumo);
        this.alturaInstalacion = altura;
        this.rangoComunicacion = rango;
        this.ultimaLectura = 0.0;
        this.historialRegistros = "";
    }
    
    public double getAlturaInstalacion() {
        return alturaInstalacion;
    }
    
    public void setAlturaInstalacion(double alturaInstalacion) {
        this.alturaInstalacion = alturaInstalacion;
    }
    
    public double getRangoComunicacion() {
        return rangoComunicacion;
    }
    
    public void setRangoComunicacion(double rangoComunicacion) {
        this.rangoComunicacion = rangoComunicacion;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== ESTACION METEOROLÓGICA ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Altura de Instalación: ").append(alturaInstalacion).append(" metros\n");
        info.append("Rango de Comunicación: ").append(rangoComunicacion).append(" km\n");
        info.append("Última Temperatura: ").append(ultimaLectura).append(" °C\n");
        return info.toString();
    }
    
    @Override
    public String realizarMedicion() {
        ultimaLectura = 15.0 + Math.random() * 20.0; // Temperatura entre 15°C y 35°C
        return "Temperatura ambiental: " + String.format("%.2f", ultimaLectura) + "°C";
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
    
    @Override
    public void registrarDatos() {
        String registro = "Temp: " + String.format("%.2f", ultimaLectura) + "°C | ";
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