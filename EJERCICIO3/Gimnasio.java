package EJERCICIO3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Gimnasio {
    private List<Miembro> miembros;
    private List<Entrenador> entrenadores;
    private List<Rutina> rutinas;

    public Gimnasio() {
        this.miembros = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.rutinas = new ArrayList<>();
    }

    public boolean agregarMiembro(Miembro m) {
        if (m == null || buscarMiembro(m.getId()) != null) return false;
        return miembros.add(m);
    }

    public boolean agregarEntrenador(Entrenador e) {
        if (e == null || buscarEntrenador(e.getId()) != null) return false;
        return entrenadores.add(e);
    }

    public boolean agregarRutina(Rutina r) {
        if (r == null || buscarRutina(r.getId()) != null) return false;
        return rutinas.add(r);
    }

    public Miembro buscarMiembro(String id) {
        for (Miembro m : miembros) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    public Entrenador buscarEntrenador(String id) {
        for (Entrenador e : entrenadores) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public Rutina buscarRutina(String id) {
        for (Rutina r : rutinas) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    public boolean asignarRutinaAMiembro(String idMiembro, String idRutina, String idEntrenador) {
        Miembro m = buscarMiembro(idMiembro);
        Rutina r = buscarRutina(idRutina);
        Entrenador e = buscarEntrenador(idEntrenador);
        if (m == null || r == null || e == null) return false;
        if (r.getEntrenador() == null) return false;
        if (!r.getEntrenador().getId().equals(e.getId())) return false;
        r.agregarMiembro(m);
        e.agregarMiembro(m);
        m.asignarRutina(r);

        return true;
    }

    public String listarMiembros() {
        if (miembros.isEmpty()) return "=== Miembros ===\n(vacío)\n";
        StringBuilder sb = new StringBuilder("=== Miembros ===\n");
        for (Miembro m : miembros) {
            sb.append(m).append("\n");
        }
        return sb.toString();
    }

    public String listarEntrenadores() {
        if (entrenadores.isEmpty()) return "=== Entrenadores ===\n(vacío)\n";
        StringBuilder sb = new StringBuilder("=== Entrenadores ===\n");
        for (Entrenador e : entrenadores) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }

    public String listarRutinas() {
        if (rutinas.isEmpty()) return "=== Rutinas ===\n(vacío)\n";
        StringBuilder sb = new StringBuilder("=== Rutinas ===\n");
        for (Rutina r : rutinas) {
            sb.append(r).append("\n");
        }
        return sb.toString();
    }

    public Rutina rutinaMasPracticada() {
        if (rutinas.isEmpty()) return null;
        return rutinas.stream().max(Comparator.comparingInt(Rutina::contarMiembros)).orElse(null);
    }

    public int totalRutinasActivas() {
        int c = 0;
        for (Rutina r : rutinas) {
            if (r.contarMiembros() > 0) c++;
        }
        return c;
    }

    public Entrenador entrenadorConMasAlumnos() {
        if (entrenadores.isEmpty()) return null;
        return entrenadores.stream().max(Comparator.comparingInt(Entrenador::contarMiembros)).orElse(null);
    }
}
