package EJERCICIO3;

public class Miembro {
    private String id;
    private String nombre;
    private TipoMembresia tipoMembresia;
    private Rutina rutinaAsignada;

    public Miembro(String id, String nombre, TipoMembresia tipoMembresia) {
        this.id = (id == null ? "" : id.trim());
        this.nombre = (nombre == null ? "" : nombre.trim());
        this.tipoMembresia = (tipoMembresia == null ? TipoMembresia.BASICA : tipoMembresia);
        this.rutinaAsignada = null;
    }

    public void asignarRutina(Rutina r) {
        this.rutinaAsignada = r;
    }

    public String toString() {
        String r = (rutinaAsignada == null ? "sin rutina" : rutinaAsignada.getNombre());
        return "Miembro{id='" + id + "', nombre='" + nombre + "', plan=" + tipoMembresia + ", rutina=" + r + "}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id == null ? "" : id.trim());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre == null ? "" : nombre.trim());
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = (tipoMembresia == null ? TipoMembresia.BASICA : tipoMembresia);
    }

    public Rutina getRutinaAsignada() {
        return rutinaAsignada;
    }

    public void setRutinaAsignada(Rutina rutinaAsignada) {
        this.rutinaAsignada = rutinaAsignada;
    }
}

