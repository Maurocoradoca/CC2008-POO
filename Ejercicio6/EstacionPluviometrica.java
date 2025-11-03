//Clase EstacionPluviometrica
//Representa una estación que mide precipitación y registra datos

public class EstacionPluviometrica extends Equipo implements Medible, Registrable {
    private double areaRecoleccion;
    private double resolucion;
    private double ultimaLectura;
    private String historialRegistros;
    
    public EstacionPluviometrica(String id, String nombre, String fabricante, String modelo, 
                                 double consumo, double area, double resolucion) {
        super(id, nombre, fabricante, modelo, consumo);
        this.areaRecoleccion = area;
        this.resolucion = resolucion;
        this.ultimaLectura = 0.0;
        this.historialRegistros = "";
    }
    
    public double getAreaRecoleccion() {
        return areaRecoleccion;
    }
    
    public void setAreaRecoleccion(double areaRecoleccion) {
        this.areaRecoleccion = areaRecoleccion;
    }
    
    public double getResolucion() {
        return resolucion;
    }
    
    public void setResolucion(double resolucion) {
        this.resolucion = resolucion;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== ESTACION PLUVIOMÉTRICA ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Área de Recolección: ").append(areaRecoleccion).append(" cm²\n");
        info.append("Resolución: ").append(resolucion).append(" mm\n");
        info.append("Última Precipitación: ").append(ultimaLectura).append(" mm\n");
        return info.toString();
    }
    
    @Override
    public String realizarMedicion() {
        ultimaLectura = Math.random() * 50.0; // Precipitación entre 0 y 50mm
        return "Precipitación registrada: " + String.format("%.2f", ultimaLectura) + " mm";
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
    
    @Override
    public void registrarDatos() {
        String registro = "Precipitación: " + String.format("%.2f", ultimaLectura) + "mm | ";
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
