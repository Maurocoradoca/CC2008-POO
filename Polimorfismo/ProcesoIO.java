public class ProcesoIO extends Proceso {
    private String dispositivo;
    private boolean esperandoIO;
    
    public ProcesoIO(int pid, String nombre, String dispositivo) {
        super(pid, nombre);
        this.dispositivo = dispositivo;
        this.esperandoIO = false;
    }
    
    public String getDispositivo() { return dispositivo; }
    public void setDispositivo(String dispositivo) { this.dispositivo = dispositivo; }
    public boolean isEsperandoIO() { return esperandoIO; }
    
    public void esperar() {
        if (isActivo()) {
            this.esperandoIO = true;
        }
    }
    
    public void completarIO() {
        this.esperandoIO = false;
    }
    
    @Override
    public void ejecutar() {
        if (isActivo()) {
            esperar(); // Simular espera de IO
        }
    }
    
    @Override
    public void detener() {
        super.detener();
        completarIO(); // Completar cualquier operación pendiente
    }
    
    @Override
    public String toString() {
        return super.toString() + " [IO, dispositivo=" + dispositivo + ", esperando=" + esperandoIO + "]";
    }
}