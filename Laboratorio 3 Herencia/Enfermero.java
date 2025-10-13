// Clase Enfermero 
// Subclase de medico que modela a los enfermeros del hospital

public class Enfermero extends Medico {
    private String turno; // "DIURNO" o "NOCTURNO"
    private String nivelCertificacion; // "BASICO", "INTERMEDIO", "AVANZADO"
    private static final double BONO_NOCTURNO = 1500.0;
    private static final double BONO_CERTIFICACION_AVANZADA = 800.0;
    private static final double BONO_CERTIFICACION_INTERMEDIA = 400.0;

    public Enfermero(String idEmpleado, String nombre, String departamento,
                    int aniosExperiencia, double salarioBase, String turno, 
                    String nivelCertificacion) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.turno = turno;
        this.nivelCertificacion = nivelCertificacion;
    }
    //Calculos
    @Override
    public double calcularSalario() {
        double salarioTotal = salarioBase;
        
        // Agregar bono nocturno si aplica
        if (turno.equalsIgnoreCase("NOCTURNO")) {
            salarioTotal += BONO_NOCTURNO;
        }
        
        // Agregar bono por certificación
        if (nivelCertificacion.equalsIgnoreCase("AVANZADO")) {
            salarioTotal += BONO_CERTIFICACION_AVANZADA;
        } else if (nivelCertificacion.equalsIgnoreCase("INTERMEDIO")) {
            salarioTotal += BONO_CERTIFICACION_INTERMEDIA;
        }
        
        return salarioTotal;
    }

    // Getters y Setters
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNivelCertificacion() {
        return nivelCertificacion;
    }

    public void setNivelCertificacion(String nivelCertificacion) {
        this.nivelCertificacion = nivelCertificacion;
    }

    @Override
    public String toString() {
        return String.format("ENFERMERO - %s | Turno: %s | Certificación: %s | Salario Total: Q%.2f",
                super.toString(), turno, nivelCertificacion, calcularSalario());
    }
}