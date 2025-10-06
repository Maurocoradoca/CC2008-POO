//Proceso de entrada/salida
public class ProcesoIO extends Proceso {
    private String dispositive;
    
    public ProcesoIO(int pid, String nombre, String dispositive) {
        super(pid, nombre);
        this.dispositive = dispositive;
    }
    
    public String getDispositivo() { return dispositive; }
    public void setDispositivo(String dispositive) { this.dispositive = dispositive; }
    
    public void esperar() {
        System.out.println("Esperando dispositivo IO: " + dispositive);
    }
    
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando proceso IO '" + getNombre() + 
                          "' con dispositivo: " + dispositive);
        esperar();
        System.out.println("  Datos leídos y escritos correctamente");
    }
    
    @Override
    public String toString() {
        return super.toString() + " [IO, dispositivo=" + dispositive + "]";
    }
}