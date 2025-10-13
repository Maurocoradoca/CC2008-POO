import java.util.ArrayList;
import java.util.List;

public class Radiologo extends Medico {
    private List<String> equiposCertificados;
    private double tarifaEstudio;
    private int estudiosRealizados;

    public Radiologo(String idEmpleado, String nombre, String departamento,
                    int aniosExperiencia, double salarioBase, double tarifaEstudio) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.equiposCertificados = new ArrayList<>();
        this.tarifaEstudio = tarifaEstudio;
        this.estudiosRealizados = 0;
    }

    //Cálculos
    @Override
    public double calcularSalario() {
        return salarioBase + (estudiosRealizados * tarifaEstudio);
    }

    //Operaciones
    public void agregarEquipoCertificado(String equipo) {
        if (!equiposCertificados.contains(equipo)) {
            equiposCertificados.add(equipo);
        }
    }
    //Registrar estudios realizados
    public void registrarEstudio() {
        this.estudiosRealizados++;
    }

    // Getters y Setters
    public List<String> getEquiposCertificados() {
        return new ArrayList<>(equiposCertificados);
    }

    public void setEquiposCertificados(List<String> equiposCertificados) {
        this.equiposCertificados = new ArrayList<>(equiposCertificados);
    }

    public double getTarifaEstudio() {
        return tarifaEstudio;
    }

    public void setTarifaEstudio(double tarifaEstudio) {
        this.tarifaEstudio = tarifaEstudio;
    }

    public int getEstudiosRealizados() {
        return estudiosRealizados;
    }

    public void setEstudiosRealizados(int estudiosRealizados) {
        this.estudiosRealizados = estudiosRealizados;
    }

    @Override
    public String toString() {
        return String.format("RADIÓLOGO - %s | Estudios: %d | Tarifa: Q%.2f | Equipos: %s | Salario Total: Q%.2f",
                super.toString(), estudiosRealizados, tarifaEstudio, equiposCertificados, calcularSalario());
    }
}