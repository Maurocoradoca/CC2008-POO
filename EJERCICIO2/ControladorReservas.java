package EJERCICIO2;

public class ControladorReservas {
    
     private final CentroEventos modelo;

    public ControladorReservas(CentroEventos modelo) {
        this.modelo = modelo;
    }

    public boolean agregarSalon(int numero, TipoSalon tipo, int capacidad, double costoHora) {
        Salon s = new Salon(numero, tipo, capacidad, costoHora);
        return modelo.agregarSalon(s);
    }

    public boolean recibirSolicitud(String encargado, String nombre, TipoEvento tipoEvento,
                                String fecha, int horaInicio, int duracionHoras,
                                int asistentes, boolean deposito) {
    Evento e = new Evento(encargado, nombre, tipoEvento, fecha,
                          horaInicio, duracionHoras, asistentes, deposito);
    return modelo.recibirSolicitud(e);
    }

    public boolean intentarAsignarDesdeEspera() {
        return modelo.moverDeEspera();
    }


    public String listarReservas() {
        return modelo.listarReservas();
    }

    public String listarSalones() {
        StringBuilder sb = new StringBuilder("Salones\n");
        Salon[] arr = modelo.getSalones();
        for (int i = 0; i < modelo.getIdxSalones(); i++) {
            if (arr[i] != null) sb.append(i + 1).append(". ").append(arr[i]).append("\n");
        }
        return sb.toString();
    }

    public String listarEspera() {
        StringBuilder sb = new StringBuilder("Lista de espera\n");
        Evento[] arr = modelo.getListaEspera();
        for (int i = 0; i < modelo.getIdxEspera(); i++) {
            Evento e = arr[i];
            if (e != null) sb.append(i + 1).append(". ").append(e).append("\n");
        }
        if (modelo.getIdxEspera() == 0) sb.append("(vacía)\n");
        return sb.toString();
    }
}

