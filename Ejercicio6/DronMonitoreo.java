//Clase DronMonitoreo
//Representa un dron para monitoreo con múltiples capacidades

public class DronMonitoreo extends Equipo implements Accionable, Registrable, Medible {
    private String resolucionCamara;
    private double alturaMaxima;
    private String estadoAccion;
    private double ultimaLectura;
    private String historialRegistros;
    
    public DronMonitoreo(String id, String nombre, String fabricante, String modelo, 
                         double consumo, String resolucion, double alturaMax) {
        super(id, nombre, fabricante, modelo, consumo);
        this.resolucionCamara = resolucion;
        this.alturaMaxima = alturaMax;
        this.estadoAccion = "En tierra";
        this.ultimaLectura = 0.0;
        this.historialRegistros = "";
    }
    
    public String getResolucionCamara() {
        return resolucionCamara;
    }
    
    public void setResolucionCamara(String resolucionCamara) {
        this.resolucionCamara = resolucionCamara;
    }
    
    public double getAlturaMaxima() {
        return alturaMaxima;
    }
    
    public void setAlturaMaxima(double alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== DRON DE MONITOREO ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Resolución de Cámara: ").append(resolucionCamara).append("\n");
        info.append("Altura Máxima: ").append(alturaMaxima).append(" metros\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        info.append("Imágenes Capturadas: ").append((int)ultimaLectura).append("\n");
        return info.toString();
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Capturando imágenes";
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
        ultimaLectura = 10.0 + Math.random() * 90.0; // Entre 10 y 100 imágenes
        return "Imágenes capturadas: " + (int)ultimaLectura;
    }

    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }

    @Override
    public void registrarDatos() {
        String registro = "Vuelo - Imágenes: " + (int)ultimaLectura + " | ";
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
    
