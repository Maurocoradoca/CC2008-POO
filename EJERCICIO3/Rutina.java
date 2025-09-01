package EJERCICIO3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rutina {
    private String id;
    private String nombre;
    private String descripcion;
    private int duracion; 
    private Entrenador entrenador; 
    private List<Miembro> miembros; 

    public Rutina(String id, String nombre, String descripcion, int duracion, Entrenador entrenador) {
        this.id = (id == null ? "" : id.trim());
        this.nombre = (nombre == null ? "" : nombre.trim());
        this.descripcion = (descripcion == null ? "" : descripcion.trim());
        this.duracion = Math.max(1, duracion);
        this.entrenador = entrenador;
        this.miembros = new ArrayList<>();
    }

    public void agregarMiembro(Miembro m) {
        if (m != null && !miembros.contains(m)) {
            miembros.add(m);
        }
    }

    public int contarMiembros() {
        return miembros.size();
    }

    public String toString() {
        String ent = (entrenador == null ? "sin entrenador" : entrenador.getNombre());
        return "Rutina{id='" + id + "', nombre='" + nombre + "', dur=" + duracion +
               "min, entrenador='" + ent + "', practicantes=" + contarMiembros() + "}";
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null ? "" : descripcion.trim());
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = Math.max(1, duracion);
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = (miembros == null ? new ArrayList<>() : miembros);
    }

    public boolean equals(Object o) {
        return o instanceof Rutina r && Objects.equals(id, r.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}
