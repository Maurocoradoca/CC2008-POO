package EJERCICIO3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Entrenador {
    private String id;
    private String nombre;
    private String especialidad;
    private List<Miembro> miembrosAsignados;

    public Entrenador(String id, String nombre, String especialidad) {
        this.id = (id == null ? "" : id.trim());
        this.nombre = (nombre == null ? "" : nombre.trim());
        this.especialidad = (especialidad == null ? "" : especialidad.trim());
        this.miembrosAsignados = new ArrayList<>();
    }

    public void agregarMiembro(Miembro m) {
        if (m != null && !miembrosAsignados.contains(m)) {
            miembrosAsignados.add(m);
        }
    }

    public int contarMiembros() {
        return miembrosAsignados.size();
    }

    public String toString() {
        return "Entrenador{id='" + id + "', nombre='" + nombre + "', esp='" + especialidad +
               "', alumnos=" + contarMiembros() + "}";
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = (especialidad == null ? "" : especialidad.trim());
    }

    public List<Miembro> getMiembrosAsignados() {
        return miembrosAsignados;
    }

    public void setMiembrosAsignados(List<Miembro> miembrosAsignados) {
        this.miembrosAsignados = (miembrosAsignados == null ? new ArrayList<>() : miembrosAsignados);
    }

    public boolean equals(Object o) {
        return o instanceof Entrenador e && Objects.equals(id, e.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}
