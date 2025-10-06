public class ProcesoCPU extends Proceso {
    private double useCPU;
    
    public ProcesoCPU(int pid, String nombre, double useCPU) {
        super(pid, nombre);
        this.useCPU = useCPU;
    }
    
    public double getUseCPU() { return useCPU; }
    public void setUseCPU(double useCPU) { this.useCPU = useCPU; }
    
    public void optimizar() {
        System.out.println("Optimizando proceso CPU: " + getNombre());
        this.useCPU *= 0.9; // Reducir uso de CPU
    }
    
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando proceso CPU '" + getNombre() + 
                          "' con uso de CPU: " + useCPU + "%");
        // Simular trabajo 
        for (int i = 0; i < 3; i++) {
            System.out.println("  Calculando... paso " + (i + 1));
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + " [CPU, uso=" + useCPU + "%]";
    }
}