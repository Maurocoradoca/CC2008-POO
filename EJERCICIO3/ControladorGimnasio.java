package EJERCICIO3;

public class ControladorGimnasio {
    private final Gimnasio modelo;

    public ControladorGimnasio(Gimnasio modelo) {
        this.modelo = modelo;
    }

    public boolean agregarMiembro(String id, String nombre, TipoMembresia tipo) {
        return modelo.agregarMiembro(new Miembro(id, nombre, tipo));
    }

    public boolean agregarEntrenador(String id, String nombre, String especialidad) {
        return modelo.agregarEntrenador(new Entrenador(id, nombre, especialidad));
    }

    public boolean agregarRutina(String id, String nombre, String desc, int durMin, String idEntrenador) {
        Entrenador e = modelo.buscarEntrenador(idEntrenador);
        Rutina r = new Rutina(id, nombre, desc, durMin, e);
        return modelo.agregarRutina(r);
    }

    public boolean asignarRutinaAMiembro(String idMiembro, String idRutina, String idEntrenador) {
        return modelo.asignarRutinaAMiembro(idMiembro, idRutina, idEntrenador);
    }

    public String listarMiembros() {
        return modelo.listarMiembros();
    }

    public String listarEntrenadores() {
        return modelo.listarEntrenadores();
    }

    public String listarRutinas() {
        return modelo.listarRutinas();
    }

    public String reporteResumen() {
        Rutina top = modelo.rutinaMasPracticada();
        int activas = modelo.totalRutinasActivas();
        Entrenador topEnt = modelo.entrenadorConMasAlumnos();
        return "== Reportes ==\n" +
               "Rutina más practicada: " + (top == null ? "N/A" : top.getNombre() + " (" + top.contarMiembros() + ")") + "\n" +
               "Total de rutinas activas (≥1 practicante): " + activas + "\n" +
               "Entrenador con más alumnos: " + (topEnt == null ? "N/A" : topEnt.getNombre() + " (" + topEnt.contarMiembros() + ")") + "\n";
    }
}
