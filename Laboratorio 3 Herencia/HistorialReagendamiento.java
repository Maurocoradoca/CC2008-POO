import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialReagendamiento {
    private LocalDateTime fechaAnterior;
    private LocalDateTime nuevaFecha;
    private String motivo;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public HistorialReagendamiento(LocalDateTime fechaAnterior, LocalDateTime nuevaFecha, String motivo) {
        this.fechaAnterior = fechaAnterior;
        this.nuevaFecha = nuevaFecha;
        this.motivo = motivo;
    }

    // Getters y Setters
    public LocalDateTime getFechaAnterior() {
        return fechaAnterior;
    }

    public void setFechaAnterior(LocalDateTime fechaAnterior) {
        this.fechaAnterior = fechaAnterior;
    }

    public LocalDateTime getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(LocalDateTime nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return String.format("Cambio: %s → %s | Motivo: %s",
                fechaAnterior.format(FORMATO),
                nuevaFecha.format(FORMATO),
                motivo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HistorialReagendamiento that = (HistorialReagendamiento) obj;
        return fechaAnterior.equals(that.fechaAnterior) && 
               nuevaFecha.equals(that.nuevaFecha);
    }

    @Override
    public int hashCode() {
        return fechaAnterior.hashCode() + nuevaFecha.hashCode();
    }
}
