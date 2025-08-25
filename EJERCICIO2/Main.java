package EJERCICIO2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CentroEventos modelo = new CentroEventos(20, 200, 50);
        ControladorReservas ctrl = new ControladorReservas(modelo);
        Vista vista = new Vista();
        Scanner sc = new Scanner(System.in);

        ctrl.agregarSalon(101, TipoSalon.PEQUENO, 30, 150);
        ctrl.agregarSalon(201, TipoSalon.MEDIANO, 60, 300);
        ctrl.agregarSalon(202, TipoSalon.MEDIANO, 80, 350);
        ctrl.agregarSalon(301, TipoSalon.GRANDE, 150, 700);

        int opcion;
        do {
            System.out.print(vista.obtenerMenu());
            System.out.print("Elige una opción: ");
            opcion = vista.leerEntero(sc);

            switch (opcion) {
                case 1 -> opcionAgregarSalon(ctrl, vista, sc);
                case 2 -> opcionNuevaSolicitud(ctrl, vista, sc);
                case 3 -> opcionAsignarDesdeEspera(ctrl);
                case 4 -> System.out.println(ctrl.listarReservas());
                case 5 -> System.out.println(ctrl.listarSalones());
                case 6 -> System.out.println(ctrl.listarEspera());
                case 0 -> System.out.println("Saliendo gracias por usar el sistema.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }


    private static void opcionAgregarSalon(ControladorReservas ctrl, Vista vista, Scanner sc) {
        System.out.print("Número del salón: ");
        int numero = vista.leerEntero(sc);

        System.out.print("Tipo de salón (PEQUENO = P, MEDIANO = M, GRANDE = G): ");
        TipoSalon tipo = vista.leerTipoSalon(sc);

        System.out.print("Capacidad: ");
        int capacidad = vista.leerEntero(sc);

        System.out.print("Costo por hora: ");
        double costo = vista.leerDouble(sc);

        boolean ok = ctrl.agregarSalon(numero, tipo, capacidad, costo);
        System.out.println(ok ? "Salón agregado." : "No hay espacio para más salones.");
    }

    private static void opcionNuevaSolicitud(ControladorReservas ctrl, Vista vista, Scanner sc) {
        System.out.print("Encargado: ");
        String encargado = vista.leerLinea(sc);

        System.out.print("Nombre del evento: ");
        String nombre = vista.leerLinea(sc);

        System.out.print("Tipo de evento (REGULAR = R, VIP = V): ");
        TipoEvento tipo = vista.leerTipoEvento(sc);

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = vista.leerLinea(sc).trim();

        System.out.print("Hora de inicio (0-23): ");
        int hora = vista.leerEntero(sc);

        System.out.print("Duración (horas, >=1): ");
        int dur = vista.leerEntero(sc);

        System.out.print("Asistentes (>=0): ");
        int asistentes = vista.leerEntero(sc);

        System.out.print("¿Depósito pagado? (si/no): ");
        boolean deposito = vista.leerBooleanSiNo(sc);

        boolean asignado = ctrl.recibirSolicitud(encargado, nombre, tipo, fecha, hora, dur, asistentes, deposito);
        if (asignado) {
            System.out.println("Reserva creada y salón asignado");
        } else {
            System.out.println("No se pudo asignar salón, La solicitud fue enviada a la lista de espera.");
        }
    }

    private static void opcionAsignarDesdeEspera(ControladorReservas ctrl) {
        boolean ok = ctrl.intentarAsignarDesdeEspera();
        System.out.println(ok
                ? "Se asignó un salón a la primera solicitud en espera."
                : "No fue posible asignar debido a lista vacía o sin salón compatible.");
    }
}

