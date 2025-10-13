import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorCitas {
    private List<CitaMedica> citas;

    public GestorCitas() {
        this.citas = new ArrayList<>();
    }

    public boolean agregarCita(CitaMedica cita) {
        // Verificar conflictos con citas existentes
        for (CitaMedica citaExistente : citas) {
            if (citaExistente.tieneConflictoCon(cita) && 
                !citaExistente.getEstado().equals("CANCELADA") &&
                !citaExistente.getEstado().equals("COMPLETADA")) {
                return false; // Hay conflicto
            }
        }
        
        citas.add(cita);
        return true;
    }

    public CitaMedica buscarCita(String idCita) {
        for (CitaMedica cita : citas) {
            if (cita.getIdCita().equals(idCita)) {
                return cita;
            }
        }
        return null;
    }

    boolean reagendarAutomatico(CitaMedica cita) {
        if (cita == null) {
            return false;
        }

        // Buscar siguiente horario disponible 
        LocalDateTime nuevaFecha = cita.getFechaHora().plusDays(1);
        
        // Verificar disponibilidad
        boolean disponible = true;
        for (CitaMedica citaExistente : citas) {
            if (citaExistente.getMedico().equals(cita.getMedico()) &&
                citaExistente.getFechaHora().equals(nuevaFecha) &&
                !citaExistente.getEstado().equals("CANCELADA") &&
                !citaExistente.getEstado().equals("COMPLETADA")) {
                disponible = false;
                break;
            }
        }

        if (disponible) {
            cita.reagendar(nuevaFecha, "Reagendamiento automático del sistema");
            return true;
        }

        return false;
    }

    public List<CitaMedica> obtenerCitasPorEstado(String estado) {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : citas) {
            if (cita.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public List<CitaMedica> obtenerCitasPorMedico(Medico medico) {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : citas) {
            if (cita.getMedico().equals(medico)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public List<CitaMedica> obtenerCitasPorFecha(LocalDateTime fecha) {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : citas) {
            if (cita.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public boolean cancelarCita(String idCita) {
        CitaMedica cita = buscarCita(idCita);
        if (cita != null) {
            cita.actualizarEstado("CANCELADA");
            return true;
        }
        return false;
    }

    public String obtenerEstadisticas() {
        int programadas = obtenerCitasPorEstado("PROGRAMADA").size();
        int confirmadas = obtenerCitasPorEstado("CONFIRMADA").size();
        int completadas = obtenerCitasPorEstado("COMPLETADA").size();
        int canceladas = obtenerCitasPorEstado("CANCELADA").size();
        int reagendadas = obtenerCitasPorEstado("REAGENDADA").size();

        return String.format("Estadísticas de Citas:\n" +
                           "  Programadas: %d\n" +
                           "  Confirmadas: %d\n" +
                           "  Completadas: %d\n" +
                           "  Canceladas: %d\n" +
                           "  Reagendadas: %d\n" +
                           "  Total: %d",
                           programadas, confirmadas, completadas, canceladas, reagendadas, citas.size());
    }

    // Getter
    public List<CitaMedica> getCitas() {
        return new ArrayList<>(citas); // Retorna copia para proteger encapsulación
    }

    public int getTotalCitas() {
        return citas.size();
    }
}