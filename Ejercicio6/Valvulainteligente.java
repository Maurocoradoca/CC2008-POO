//Clase ValvulaInteligente
//Representa una válvula que puede ejecutar acciones de apertura/cierre

public class Valvulainteligente extends Equipo implements Accionable {
    private double presionMaxima;
    private double diametro;
    private String estadoAccion;
    
    public Valvulainteligente(String id, String nombre, String fabricante, String modelo, 
                              double consumo, double presion, double diametro) {
        super(id, nombre, fabricante, modelo, consumo);
        this.presionMaxima = presion;
        this.diametro = diametro;
        this.estadoAccion = "Cerrada";
    }
    
    public double getPresionMaxima() {
        return presionMaxima;
    }
    
    public void setPresionMaxima(double presionMaxima) {
        this.presionMaxima = presionMaxima;
    }
    
    public double getDiametro() {
        return diametro;
    }
    
    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== VaLVULA INTELIGENTE ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Presión Máxima: ").append(presionMaxima).append(" bar\n");
        info.append("Diámetro: ").append(diametro).append(" pulgadas\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        return info.toString();
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Abierta";
    }
    
    @Override
    public void detenerAccion() {
        estadoAccion = "Cerrada";
    }
    
    @Override
    public String obtenerEstadoAccion() {
        return estadoAccion;
    }
}