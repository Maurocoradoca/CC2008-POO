//Clase ControladorPH
//Representa un controlador de pH que puede medir y ajustar

public class ControladorPH extends Equipo implements Medible, Accionable {
    private String rangoAjuste;
    private double velocidadRespuesta;
    private String estadoAccion;
    private double ultimaLectura;
        
    public ControladorPH(String id, String nombre, String fabricante, String modelo, 
                         double consumo, String rango, double velocidad) {
        super(id, nombre, fabricante, modelo, consumo);
        this.rangoAjuste = rango;
        this.velocidadRespuesta = velocidad;
        this.estadoAccion = "Detenido";
        this.ultimaLectura = 7.0;
    }
    
    public String getRangoAjuste() {
        return rangoAjuste;
    }
    
    public void setRangoAjuste(String rangoAjuste) {
        this.rangoAjuste = rangoAjuste;
    }
    
    public double getVelocidadRespuesta() {
        return velocidadRespuesta;
    }
    
    public void setVelocidadRespuesta(double velocidadRespuesta) {
        this.velocidadRespuesta = velocidadRespuesta;
    }
    
    @Override
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== CONTROLADOR DE pH ===\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Fabricante: ").append(fabricante).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Consumo Eléctrico: ").append(consumoElectrico).append("W\n");
        info.append("Estado: ").append(estado).append("\n");
        info.append("Rango de Ajuste: ").append(rangoAjuste).append("\n");
        info.append("Velocidad de Respuesta: ").append(velocidadRespuesta).append(" seg\n");
        info.append("Estado de Acción: ").append(estadoAccion).append("\n");
        info.append("Última Lectura pH: ").append(ultimaLectura).append("\n");
        return info.toString();
    }
    
    @Override
    public String realizarMedicion() {
        ultimaLectura = 5.5 + Math.random() * 3.0; // pH entre 5.5 y 8.5
        return "Medición de pH: " + String.format("%.2f", ultimaLectura);
    }
    
    @Override
    public double obtenerUltimaLectura() {
        return ultimaLectura;
    }
    
    @Override
    public void ejecutarAccion() {
        estadoAccion = "Ajustando pH";
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
