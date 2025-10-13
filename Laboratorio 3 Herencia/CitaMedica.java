// Clase cita medica 
// Cita medica programada en el sistema, asociada a paciente 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CitaMedica {
    private String idCita;
    private String paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private String tipoCita; // "CONSULTAGE NERAL", "CIRUGIA", "TERAPIA", "DIAGNOSTICO"
    private String estado; // "PROGRAMADA", "CONFIRMADA", "EN PROGRESO", "COMPLETADA", "CANCELADA", "REAGENDADA"
    private List<HistorialReagendamiento> historial;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public CitaMedica(String idCita, String paciente, Medico medico, 
                     LocalDateTime fechaHora, String tipoCita) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.estado = "PROGRAMADA";
        this.historial = new ArrayList<>();
    }

    //Reagendar
    public void reagendar(LocalDateTime nuevaFecha, String motivo) {
        // Crear registro de historial antes de cambiar
        HistorialReagendamiento registro = new HistorialReagendamiento(
            this.fechaHora, nuevaFecha, motivo
        );
        historial.add(registro);
        
        // Actualizar fecha y estado
        this.fechaHora = nuevaFecha;
        this.estado = "REAGENDADA";
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    //verificacion
    public boolean tieneConflictoCon(CitaMedica otraCita) {
        // Verificar si es el mismo médico
        if (!this.medico.equals(otraCita.getMedico())) {
            return false;
        }
        
        // Verificar si las fechas se solapan 
        return this.fechaHora.toLocalDate().equals(otraCita.getFechaHora().toLocalDate()) &&
               Math.abs(this.fechaHora.getHour() - otraCita.getFechaHora().getHour()) < 2;
    }

    // Getters y Setters
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<HistorialReagendamiento> getHistorial() {
        return new ArrayList<>(historial); // Retorna copia para proteger encapsulación
    }

    public String obtenerHistorialFormateado() {
        if (historial.isEmpty()) {
            return "Sin reagendamientos";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historial.size(); i++) {
            sb.append("\n  ").append(i + 1).append(". ").append(historial.get(i).toString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Cita [%s] | Paciente: %s | Médico: %s | Fecha: %s | Tipo: %s | Estado: %s",
                idCita, paciente, medico.getNombre(), fechaHora.format(FORMATO), tipoCita, estado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CitaMedica that = (CitaMedica) obj;
        return idCita.equals(that.idCita);
    }

    @Override
    public int hashCode() {
        return idCita.hashCode();
    }
}