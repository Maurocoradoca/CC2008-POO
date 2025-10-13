// Clase Hospital manager 
// Actua como coordinador principal entre los gestores de personal y de citas

import java.util.List;
import java.util.Map;

public class HospitalManager {
    private GestorCitas gestorCitas;
    private GestorPersonal gestorPersonal;

    public HospitalManager() {
        this.gestorCitas = new GestorCitas();
        this.gestorPersonal = new GestorPersonal();
    }

    public String generarReporteNomina() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("                        REPORTE DE NÓMINA HOSPITALARIA                       \n");
       

        List<Medico> personal = gestorPersonal.getPersonal();
        
        if (personal.isEmpty()) {
            reporte.append("No hay personal registrado en el sistema.\n");
            return reporte.toString();
        }

        // Reporte por departamento
        List<String> departamentos = gestorPersonal.obtenerDepartamentos();
        
        for (String departamento : departamentos) {
            reporte.append(String.format("  DEPARTAMENTO: %s\n", departamento.toUpperCase()));
            
            List<Medico> personalDepto = gestorPersonal.buscarPorDepartamento(departamento);
            double nominaDepto = 0;
            
            for (Medico medico : personalDepto) {
                double salario = medico.calcularSalario(); // Polimorfismo
                nominaDepto += salario;
                reporte.append(String.format(" %s\n", medico.toString()));
                reporte.append(String.format("Salario Calculado: Q%.2f\n\n", salario));
            }
            
            reporte.append(String.format(" SUBTOTAL %s: Q%.2f\n\n", departamento.toUpperCase(), nominaDepto));
        }

        // Estadísticas generales
        reporte.append("                           ESTADÍSTICAS GENERALES                            \n");
 
        Map<String, Integer> estadisticas = gestorPersonal.obtenerEstadisticasPorTipo();
        reporte.append("\n  Personal por Especialización:\n");
        for (Map.Entry<String, Integer> entry : estadisticas.entrySet()) {
            reporte.append(String.format(" %s: %d\n", entry.getKey(), entry.getValue()));
        }
        
        Medico mayorSalario = gestorPersonal.obtenerMayorSalario();
        if (mayorSalario != null) {
            reporte.append(String.format("\n  Empleado con Mayor Salario:\n"));
            reporte.append(String.format(" %s - Q%.2f\n", mayorSalario.getNombre(), mayorSalario.calcularSalario()));
        }
        
        reporte.append(String.format("  TOTAL EMPLEADOS: %d\n", gestorPersonal.getTotalPersonal()));
        reporte.append(String.format("  NÓMINA TOTAL: Q%.2f\n", gestorPersonal.calcularNomina()));

        return reporte.toString();
    }

    public String generarReporteCitas() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("                          REPORTE DE CITAS MÉDICAS                            \n");

        List<CitaMedica> citas = gestorCitas.getCitas();
        
        if (citas.isEmpty()) {
            reporte.append("No hay citas registradas en el sistema.\n");
            return reporte.toString();
        }

        // Reporte por estado
        String[] estados = {"PROGRAMADA", "CONFIRMADA", "EN PROGRESO", "COMPLETADA", "CANCELADA", "REAGENDADA"};
        
        for (String estado : estados) {
            List<CitaMedica> citasEstado = gestorCitas.obtenerCitasPorEstado(estado);
            
            if (!citasEstado.isEmpty()) {
                reporte.append(String.format("  ESTADO: %s (%d citas)\n", estado, citasEstado.size()));
                
                for (CitaMedica cita : citasEstado) {
                    reporte.append(String.format("  • %s\n", cita.toString()));
                    
                    // Mostrar historial si existe
                    if (!cita.getHistorial().isEmpty()) {
                        reporte.append(String.format("    Historial de Reagendamientos:%s\n", cita.obtenerHistorialFormateado()));
                    }
                    reporte.append("\n");
                }
            }
        }

        // Estadísticas
        reporte.append("                            ESTADÍSTICAS DE CITAS                             \n");
        reporte.append(gestorCitas.obtenerEstadisticas());
        reporte.append("\n");

        return reporte.toString();
    }

    public String generarReporteReagendamientos() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("                      HISTORIAL DE REAGENDAMIENTOS                            \n");

        List<CitaMedica> citasReagendadas = gestorCitas.obtenerCitasPorEstado("REAGENDADA");
        
        if (citasReagendadas.isEmpty()) {
            reporte.append("No hay citas reagendadas en el sistema.\n");
            return reporte.toString();
        }

        for (CitaMedica cita : citasReagendadas) {
            reporte.append(String.format("Cita ID: %s | Paciente: %s | Médico: %s\n", 
                cita.getIdCita(), cita.getPaciente(), cita.getMedico().getNombre()));
            reporte.append(String.format("Historial:%s\n\n", cita.obtenerHistorialFormateado()));
        }

        reporte.append(String.format("Total de citas reagendadas: %d\n", citasReagendadas.size()));

        return reporte.toString();
    }

    public List<Medico> buscarPersonalDisponible(Class<? extends Medico> tipoClase) {
        return gestorPersonal.buscarPorEspecializacion(tipoClase);
    }

    public boolean resolverConflicto(String idCita) {
        CitaMedica cita = gestorCitas.buscarCita(idCita);
        return gestorCitas.reagendarAutomatico(cita);
    }

    // Getters para acceso controlado a los gestores
    public GestorCitas getGestorCitas() {
        return gestorCitas;
    }

    public GestorPersonal getGestorPersonal() {
        return gestorPersonal;
    }
}