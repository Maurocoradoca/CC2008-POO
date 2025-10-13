import java.util.ArrayList;
import java.util.List;

public class Cirujano extends Medico {
    private List<String> tiposOperacion;
    private int horasCirugia;
    private double bonoRiesgo;
    private static final double TARIFA_POR_HORA = 500.0; // Tarifa base por hora de cirugía

    public Cirujano(String idEmpleado, String nombre, String departamento,
                   int aniosExperiencia, double salarioBase, double bonoRiesgo) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tiposOperacion = new ArrayList<>();
        this.horasCirugia = 0;
        this.bonoRiesgo = bonoRiesgo;
    }
    //Cálculos
    @Override
    public double calcularSalario() {
        return salarioBase + (horasCirugia * TARIFA_POR_HORA) + bonoRiesgo;
    }

    //Opereaciones
    public void agregarTipoOperacion(String tipoOperacion) {
        if (!tiposOperacion.contains(tipoOperacion)) {
            tiposOperacion.add(tipoOperacion);
        }
    }
    //Registrar horas de cirugia
    public void registrarHorasCirugia(int horas) {
        this.horasCirugia += horas;
    }

    // Getters y Setters
    public List<String> getTiposOperacion() {
        return new ArrayList<>(tiposOperacion); // Retorna copia para proteger encapsulación
    }

    public void setTiposOperacion(List<String> tiposOperacion) {
        this.tiposOperacion = new ArrayList<>(tiposOperacion);
    }

    public int getHorasCirugia() {
        return horasCirugia;
    }

    public void setHorasCirugia(int horasCirugia) {
        this.horasCirugia = horasCirugia;
    }

    public double getBonoRiesgo() {
        return bonoRiesgo;
    }

    public void setBonoRiesgo(double bonoRiesgo) {
        this.bonoRiesgo = bonoRiesgo;
    }

    @Override
    public String toString() {
        return String.format("CIRUJANO - %s | Horas Cirugía: %d | Bono Riesgo: Q%.2f | Operaciones: %s | Salario Total: Q%.2f",
                super.toString(), horasCirugia, bonoRiesgo, tiposOperacion, calcularSalario());
    }
}