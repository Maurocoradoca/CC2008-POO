package EJERCICIO3;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Gimnasio modelo = new Gimnasio();
        ControladorGimnasio ctrl = new ControladorGimnasio(modelo);
        Vista vista = new Vista();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.print(vista.menu());
            System.out.print("Elige opción: ");
            opcion = vista.leerOpcion(sc);

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID miembro: ");
                    String id = vista.leerLinea(sc);
                    System.out.print("Nombre: ");
                    String nom = vista.leerLinea(sc);
                    System.out.print("Membresía (BASICA/PREMIUM): ");
                    TipoMembresia plan = vista.leerMembresia(sc);
                    boolean ok = ctrl.agregarMiembro(id, nom, plan);
                    System.out.println(ok ? "Miembro agregado." : "No se pudo agregar (ID duplicado o datos inválidos).");
                }
                case 2 -> System.out.print(ctrl.listarMiembros());
                case 3 -> {
                    System.out.print("ID entrenador: ");
                    String id = vista.leerLinea(sc);
                    System.out.print("Nombre: ");
                    String nom = vista.leerLinea(sc);
                    System.out.print("Especialidad: ");
                    String esp = vista.leerLinea(sc);
                    boolean ok = ctrl.agregarEntrenador(id, nom, esp);
                    System.out.println(ok ? "Entrenador agregado." : "No se pudo agregar (ID duplicado o datos inválidos).");
                }
                case 4 -> System.out.print(ctrl.listarEntrenadores());
                case 5 -> {
                    System.out.print("ID rutina: ");
                    String id = vista.leerLinea(sc);
                    System.out.print("Nombre: ");
                    String nom = vista.leerLinea(sc);
                    System.out.print("Descripción: ");
                    String d = vista.leerLinea(sc);
                    System.out.print("Duración (min): ");
                    int dur = vista.leerEntero(sc);
                    System.out.print("ID entrenador (debe existir): ");
                    String ent = vista.leerLinea(sc);
                    boolean ok = ctrl.agregarRutina(id, nom, d, dur, ent);
                    System.out.println(ok ? "Rutina agregada." : "No se pudo agregar (ID duplicado o entrenador no existe).");
                }
                case 6 -> System.out.print(ctrl.listarRutinas());
                case 7 -> {
                    System.out.print("ID miembro: ");
                    String m = vista.leerLinea(sc);
                    System.out.print("ID rutina: ");
                    String r = vista.leerLinea(sc);
                    System.out.print("ID entrenador: ");
                    String e = vista.leerLinea(sc);
                    boolean ok = ctrl.asignarRutinaAMiembro(m, r, e);
                    System.out.println(ok ? "Asignación completa." : "No se pudo asignar (IDs inválidos).");
                }
                case 8 -> System.out.print(ctrl.reporteResumen());
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
