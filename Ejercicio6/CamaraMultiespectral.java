//Clase CamaraMultiespectral
//Representa una cámara que captura imágenes y registra datos

public class CamaraMultiespectral extends Equipo implements Accionable, Registrable {
    private int numeroSensores;
    private String rangoEspectral;
    private String estadoAccion;
    private String historialRegistros;
    
    public CamaraMultiespectral(String id, String nombre, String fabricante, String modelo, 
                                double consumo, int numSensores, String rango) {
        super(id, nombre, fabricante, modelo, consumo);
        this.numeroSensores = numSensores;
        this.rangoEspectral = rango;
        this.estadoAccion = "Inactiva";
        this.historialRegistros = "";
    }
    
    public int getNumeroSensores() {
        return numeroSensores;
    }
    
    public void setNumeroSensores(int numeroSensores) {
        this.numeroSensores = numeroSensores;
    }
    
    public String getRangoEspectral() {
        return rangoEspectral;
    }
    
    public void setRangoEspectral(String rangoEspectral) {
        this.rangoEspectral = rangoEspectral;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== CÁMARA MULTIESPECTRAL ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Número de Sensores: ").append(numeroSensores).append("\n");
        info.append("Rango Espectral: ").append(rangoEspectral).append("\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        return info.toString();
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Capturando imágenes";
    }
    
    @Override
    public void detenerAccion() {
        estadoAccion = "Inactiva";
    }
    
    @Override
    public String obtenerEstadoAccion() {
        return estadoAccion;
    }
    
    @Override
    public void registrarDatos() {
        String registro = "Captura realizada | ";
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