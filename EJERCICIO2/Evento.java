package EJERCICIO2;

public class Evento {

    private String encargado;
    private String nombre;
    private TipoEvento tipo;
    private String fecha;
    private int horaInicio;
    private int duracionHoras;
    private int asistentes;
    private boolean depositoPagado;

    public Evento(String encargado, String nombre, TipoEvento tipo, String fecha,
                  int horaInicio, int duracionHoras, int asistentes, boolean depositoPagado) {
        this.encargado = (encargado == null ? "" : encargado);
        this.nombre = (nombre == null ? "" : nombre);
        this.tipo = (tipo == null ? TipoEvento.REGULAR : tipo);
        this.fecha = (fecha == null ? "" : fecha);
        this.horaInicio = clamp(horaInicio, 0, 23);
        this.duracionHoras = Math.max(1, duracionHoras);
        this.asistentes = Math.max(0, asistentes);
        this.depositoPagado = depositoPagado;  
    }


    public Evento(String encargado, String nombre, TipoEvento tipo, String fecha,
                  int horaInicio, int duracionHoras, int asistentes) {
        this(encargado, nombre, tipo, fecha, horaInicio, duracionHoras, asistentes, false);
    }

    public boolean requiereVIP() { return tipo == TipoEvento.VIP; }

    public double costoEstimado(double costoHoraSalon){
        return Math.max(0.0, costoHoraSalon) * duracionHoras;
    }

    public String toString() {
        return "Evento{" +
               "encargado='" + encargado + '\'' +
               ", nombre='" + nombre + '\'' +
               ", tipo=" + tipo +
               ", fecha='" + fecha + '\'' +
               ", hora de inicio=" + horaInicio +
               ", duracion=" + duracionHoras + "h" +
               ", asistentes=" + asistentes +
               ", deposito pagado=" + depositoPagado +
               '}';
    }

    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    public String getEncargado() {
        return encargado; 
    }
    public void setEncargado(String encargado) { 
        this.encargado = (encargado == null ? "" : encargado); 
    }
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = (nombre == null ? "" : nombre); 
    }
    public TipoEvento getTipo() { 
        return tipo; 
    }
    public void setTipo(TipoEvento tipo) { 
        this.tipo = (tipo == null ? TipoEvento.REGULAR : tipo); 
    }
    public String getFecha() { 
        return fecha; 
    }
    public void setFecha(String fecha) { 
        this.fecha = (fecha == null ? "" : fecha);
    }
    public int getHoraInicio() { 
        return horaInicio; 
    }
    public void setHoraInicio(int horaInicio) { 
        this.horaInicio = clamp(horaInicio, 0, 23); 
    }
    public int getDuracionHoras() { 
        return duracionHoras; 
    }
    public void setDuracionHoras(int duracionHoras) { 
        this.duracionHoras = Math.max(1, duracionHoras); 
    }
    public int getAsistentes() {
         return asistentes; 
        }
    public void setAsistentes(int asistentes) { 
        this.asistentes = Math.max(0, asistentes); 
    }
    public boolean isDepositoPagado() { 
        return depositoPagado; 
    }
    public void setDepositoPagado(boolean depositoPagado) {
         this.depositoPagado = depositoPagado; 
        }
}
