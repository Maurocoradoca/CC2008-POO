package EJERCICIO2;

public class Reserva {

    private Evento evento;
    private Salon salon;
    private String estado;

    public static final String PENDIENTE = "Pendiente";
    public static final String CONFIRMADA = "Confirmada";
    public static final String CANCELADA = "Cancelada";

    public Reserva(Evento evento, Salon salon, boolean confirmarsiDeposito) {
        this.evento = evento;
        this.salon = salon;
        if (confirmarsiDeposito && evento != null && evento.isDepositoPagado()) {
            this.estado = CONFIRMADA;
        } else {
            this.estado = PENDIENTE;
        }
    }

    public double calcularCostoTotal() {
        if (salon == null || evento == null) return 0.0;
        return evento.getDuracionHoras() * salon.getCostoPorHora();
    }

    public void confirmar(){
        this.estado = CONFIRMADA;
    }
    
    public void cancelar(){
        this.estado = CANCELADA;
    }

      public String toString() {
        String sSalon = (salon == null ? "sin salón" : ("#"+salon.getNumero()+" ("+salon.getTipo()+")"));
        String sEvento = (evento == null ? "sin evento" : evento.getNombre());
        return "Reserva{" +
               "evento=" + sEvento +
               ", salon=" + sSalon +
               ", estado='" + estado + '\'' +
               ", costoTotal=Q" + calcularCostoTotal() +
               '}';
    }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public Salon getSalon() { return salon; }
    public void setSalon(Salon salon) { this.salon = salon; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
