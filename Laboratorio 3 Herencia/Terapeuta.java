// Clase Terapeuta 
// Subclase de medico que representa a los terapeutas del hospital 

public class Terapeuta extends Medico {
    private String tipoTerapia; // "FÍSICA", "OCUPACIONAL", "RESPIRATORIA", "PSICOLÓGICA"
    private int duracionSesion; // Duración promedio en minutos
    private int sesionesRealizadas;
    private static final double TARIFA_POR_SESION = 200.0;

    public Terapeuta(String idEmpleado, String nombre, String departamento,
                    int aniosExperiencia, double salarioBase, String tipoTerapia,
                    int duracionSesion) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tipoTerapia = tipoTerapia;
        this.duracionSesion = duracionSesion;
        this.sesionesRealizadas = 0;
    }

    //Cálculos
    @Override
    public double calcularSalario() {
        return salarioBase + (sesionesRealizadas * TARIFA_POR_SESION);
    }
    //Operaciones
    public void registrarSesion() {
        this.sesionesRealizadas++;
    }

    // Getters y Setters
    public String getTipoTerapia() {
        return tipoTerapia;
    }

    public void setTipoTerapia(String tipoTerapia) {
        this.tipoTerapia = tipoTerapia;
    }

    public int getDuracionSesion() {
        return duracionSesion;
    }

    public void setDuracionSesion(int duracionSesion) {
        this.duracionSesion = duracionSesion;
    }

    public int getSesionesRealizadas() {
        return sesionesRealizadas;
    }

    public void setSesionesRealizadas(int sesionesRealizadas) {
        this.sesionesRealizadas = sesionesRealizadas;
    }

    @Override
    public String toString() {
        return String.format("TERAPEUTA - %s | Tipo: %s | Sesiones: %d | Duración: %d min | Salario Total: Q%.2f",
                super.toString(), tipoTerapia, sesionesRealizadas, duracionSesion, calcularSalario());
    }
}