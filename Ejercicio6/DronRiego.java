//Clase DronRiego
//Representa un dron para riego que puede ejecutar acciones y medir cobertura

public class DronRiego extends Equipo implements Accionable, Medible {
    private double capacidadTanque;
    private int autonomiaVuelo;
    private String estadoAccion;
    private double ultimaLectura;
    
    public DronRiego(String id, String nombre, String fabricante, String modelo, 
                     double consumo, double capacidad, int autonomia) {
        super(id, nombre, fabricante, modelo, consumo);
        this.capacidadTanque = capacidad;
        this.autonomiaVuelo = autonomia;
        this.estadoAccion = "En tierra";
        this.ultimaLectura = 0.0;
    }
    
    public double getCapacidadTanque() {
        return capacidadTanque;
    }
    
    public void setCapacidadTanque(double capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }
    
    public int getAutonomiaVuelo() {
        return autonomiaVuelo;
    }
    
    public void setAutonomiaVuelo(int autonomiaVuelo) {
        this.autonomiaVuelo = autonomiaVuelo;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== DRON DE RIEGO ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Capacidad del Tanque: ").append(capacidadTanque).append(" litros\n");
        info.append("Autonomía de Vuelo: ").append(autonomiaVuelo).append(" minutos\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        info.append("Área Regada: ").append(ultimaLectura).append(" m²\n");
        return info.toString();
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Regando parcela";
    }
    
    @Override
    public void detenerAccion() {
        estadoAccion = "En tierra";
    }
    
    @Override
    public String obtenerEstadoAccion() {
        return estadoAccion;
    }
    
    @Override
    public String realizarMedicion() {
        ultimaLectura = 500.0 + Math.random() * 1500.0; // Área entre 500 y 2000 m²
        return "Área de cobertura: " + String.format("%.2f", ultimaLectura) + " m²";
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
}