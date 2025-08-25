package EJERCICIO2;
 //No se implementaron setters para los arreglos ni para los índices
 //porque en el diseño final el propio modelo controla su manejo interno.
 //Incluirlos sería redundante y podría romper la consistencia del sistema.
public class CentroEventos {

    private Salon[] salones;
    private Reserva[] reservas;
    private Evento[] listaEspera;

    private int idxSalones;
    private int idxReservas;
    private int idxEspera;

    private int maxSalones;
    private int maxReservas;
    private int maxEspera;

    public CentroEventos(int maxSalones, int maxReservas, int maxEspera) {
        this.maxSalones = maxSalones;
        this.maxReservas = maxReservas;
        this.maxEspera = maxEspera;

        this.salones = new Salon[maxSalones];
        this.reservas = new Reserva[maxReservas];
        this.listaEspera = new Evento[maxEspera];

        this.idxSalones = 0;
        this.idxReservas = 0;
        this.idxEspera = 0;
    }
    
       public boolean agregarSalon(Salon s) {
        if (idxSalones < maxSalones) {
            salones[idxSalones++] = s;
            return true;
        }
        return false;
    }

    public Salon[] getSalones() {
        return salones;
    }

    public boolean recibirSolicitud(Evento e) {
        int idx = buscarSalonDisponible(e);
        if (idx >= 0) {
            Salon s = salones[idx];
            if (reglasCumplidas(e, s)) {
                Reserva r = new Reserva(e, s, true);
                reservas[idxReservas++] = r;
                s.marcarOcupado(); // lo ocupamos
                return true;
            }
        }

        if (idxEspera < maxEspera) {
            listaEspera[idxEspera++] = e;
            return false;
        }
        return false;
    }

    public boolean reglasCumplidas(Evento e, Salon s) {
        if (e == null || s == null) return false;

        if (!s.esAptoPara(e.getTipo())) return false;

        if (e.getAsistentes() > s.getCapacidad()) return false;

        if (!e.isDepositoPagado()) return false;

        for (int i = 0; i < idxReservas; i++) {
            Reserva r = reservas[i];
            if (r != null && r.getSalon().getNumero() == s.getNumero()) {
                Evento otro = r.getEvento();
                if (otro.getFecha().equals(e.getFecha())) {
                    int inicio1 = otro.getHoraInicio();
                    int fin1 = inicio1 + otro.getDuracionHoras();
                    int inicio2 = e.getHoraInicio();
                    int fin2 = inicio2 + e.getDuracionHoras();
                    if (!(fin2 <= inicio1 || inicio2 >= fin1)) {
                        return false; 
                    }
                }
            }
        }

        return true;
    }

    public int buscarSalonDisponible(Evento e) {
        for (int i = 0; i < idxSalones; i++) {
            Salon s = salones[i];
            if (s.isDisponible() && reglasCumplidas(e, s)) {
                return i;
            }
        }
        return -1;
    }

    
    public boolean moverDeEspera() {
        if (idxEspera == 0) return false;
        Evento e = listaEspera[0];

        for (int i = 1; i < idxEspera; i++) {
            listaEspera[i - 1] = listaEspera[i];
        }
        idxEspera--;

        return recibirSolicitud(e);
    }

   
    public String listarReservas() {
        StringBuilder sb = new StringBuilder("Reservas\n");
        for (int i = 0; i < idxReservas; i++) {
            sb.append(i + 1).append(". ").append(reservas[i]).append("\n");
        }
        return sb.toString();
    }

    public Reserva[] getReservas() { return reservas; }
    public Evento[] getListaEspera() { return listaEspera; }

    public int getIdxSalones() { return idxSalones; }
    public int getIdxReservas() { return idxReservas; }
    public int getIdxEspera() { return idxEspera; }
    
}
