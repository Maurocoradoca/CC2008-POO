public class DoctorGeneral extends Medico {
    private double tarifaConsulta;
    private int consultasAtendidas;

    public DoctorGeneral(String idEmpleado, String nombre, String departamento,
                        int aniosExperiencia, double salarioBase, double tarifaConsulta) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tarifaConsulta = tarifaConsulta;
        this.consultasAtendidas = 0;
    }
    //Calculos
    @Override
    public double calcularSalario() {
        return salarioBase + (consultasAtendidas * tarifaConsulta);
    }

    // Getters y Setters
    public double getTarifaConsulta() {
        return tarifaConsulta;
    }

    public void setTarifaConsulta(double tarifaConsulta) {
        this.tarifaConsulta = tarifaConsulta;
    }

    public int getConsultasAtendidas() {
        return consultasAtendidas;
    }

    public void setConsultasAtendidas(int consultasAtendidas) {
        this.consultasAtendidas = consultasAtendidas;
    }

    // Método para registrar una consulta atendida
    public void registrarConsulta() {
        this.consultasAtendidas++;
    }

    @Override
    public String toString() {
        return String.format("DOCTOR GENERAL - %s | Consultas: %d | Tarifa: Q%.2f | Salario Total: Q%.2f",
                super.toString(), consultasAtendidas, tarifaConsulta, calcularSalario());
    }
}