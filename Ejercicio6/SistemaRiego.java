//Clase SistemaRiego
//Representa un sistema de riego automatizado

public class SistemaRiego extends Equipo implements Accionable {
    private double cobertura;
    private double presionOperacion;
    private String estadoAccion;
    
    public SistemaRiego(String id, String nombre, String fabricante, String modelo, 
                        double consumo, double cobertura, double presion) {
        super(id, nombre, fabricante, modelo, consumo);
        this.cobertura = cobertura;
        this.presionOperacion = presion;
        this.estadoAccion = "Detenido";
    }
    
    public double getCobertura() {
        return cobertura;
    }
    
    public void setCobertura(double cobertura) {
        this.cobertura = cobertura;
    }
    
    public double getPresionOperacion() {
        return presionOperacion;
    }
    
    public void setPresionOperacion(double presionOperacion) {
        this.presionOperacion = presionOperacion;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== SISTEMA DE RIEGO ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Cobertura: ").append(cobertura).append(" m²\n");
        info.append("Presión de Operación: ").append(presionOperacion).append(" bar\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        return info.toString();
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Regando";
    }
    
    @Override
    public void detenerAccion() {
        estadoAccion = "Detenido";
    }
    
    @Override
    public String obtenerEstadoAccion() {
        return estadoAccion;
    }
}