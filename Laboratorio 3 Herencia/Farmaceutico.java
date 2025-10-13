public class Farmaceutico extends Medico {
    private int limitePrescripciones;
    private boolean licenciaSustancias;
    private int prescripcionesRealizadas;
    private static final double BONO_POR_PRESCRIPCION = 15.0;
    private static final double BONO_LICENCIA_ESPECIAL = 1200.0;

    public Farmaceutico(String idEmpleado, String nombre, String departamento,
                       int aniosExperiencia, double salarioBase, int limitePrescripciones,
                       boolean licenciaSustancias) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.limitePrescripciones = limitePrescripciones;
        this.licenciaSustancias = licenciaSustancias;
        this.prescripcionesRealizadas = 0;
    }

    //Cálculos
    @Override
    public double calcularSalario() {
        double salarioTotal = salarioBase;
        salarioTotal += prescripcionesRealizadas * BONO_POR_PRESCRIPCION;
        
        if (licenciaSustancias) {
            salarioTotal += BONO_LICENCIA_ESPECIAL;
        }
        
        return salarioTotal;
    }

    //Operaciones
    public boolean registrarPrescripcion() {
        if (prescripcionesRealizadas < limitePrescripciones) {
            prescripcionesRealizadas++;
            return true;
        }
        return false;
    }

    // Getters y Setters
    public int getLimitePrescripciones() {
        return limitePrescripciones;
    }

    public void setLimitePrescripciones(int limitePrescripciones) {
        this.limitePrescripciones = limitePrescripciones;
    }

    public boolean isLicenciaSustancias() {
        return licenciaSustancias;
    }

    public void setLicenciaSustancias(boolean licenciaSustancias) {
        this.licenciaSustancias = licenciaSustancias;
    }

    public int getPrescripcionesRealizadas() {
        return prescripcionesRealizadas;
    }

    public void setPrescripcionesRealizadas(int prescripcionesRealizadas) {
        this.prescripcionesRealizadas = prescripcionesRealizadas;
    }

    @Override
    public String toString() {
        return String.format("FARMACÉUTICO - %s | Prescripciones: %d/%d | Licencia Especial: %s | Salario Total: Q%.2f",
                super.toString(), prescripcionesRealizadas, limitePrescripciones, 
                (licenciaSustancias ? "Sí" : "No"), calcularSalario());
    }
}