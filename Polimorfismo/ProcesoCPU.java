public class ProcesoCPU extends Proceso {
    private double usoCPU;
    private boolean optimizado;
    
    public ProcesoCPU(int pid, String nombre, double usoCPU) {
        super(pid, nombre);
        this.usoCPU = usoCPU;
        this.optimizado = false;
    }
    
    public double getUsoCPU() { return usoCPU; }
    public void setUsoCPU(double usoCPU) { this.usoCPU = usoCPU; }
    public boolean isOptimizado() { return optimizado; }
    
    public void optimizar() {
        if (!optimizado && usoCPU > 50) {
            this.usoCPU *= 0.8; // Reducir uso de CPU 
            this.optimizado = true;
        }
    }
    
    @Override
    public void ejecutar() {
        if (isActivo()) {
            // Simular consumo de recursos
            if (usoCPU > 90) {
                optimizar(); // Autooptimizar si el uso es muy alto
            }
        }
    }
    
    @Override
    public void detener() {
        super.detener();
        this.usoCPU = 0; // Liberar recursos al detener
    }
    
    @Override
    public String toString() {
        return super.toString() + " [CPU, uso=" + usoCPU + "%, optimizado=" + optimizado + "]";
    }
}