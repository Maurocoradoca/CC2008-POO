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
        return r = (rutinaAsignada == null ? "Sin rutina asignada" : rutinaAsignada.getNombre());   
        return "Miembro{id='" + id + "', nombre='" + nombre + "', plan=" + tipoMembresia + ", rutina=" + r + "}";
    }

    
}
