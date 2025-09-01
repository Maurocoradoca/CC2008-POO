package EJERCICIO3;

import java.util.Scanner;

public class Vista {
    public String menu() {
        return """
               ===== GIMNASIO =====
               1) Agregar miembro
               2) Listar miembros
               3) Agregar entrenador
               4) Listar entrenadores
               5) Agregar rutina
               6) Listar rutinas
               7) Asignar rutina a miembro
               8) Reportes
               0) Salir
               """;
    }

    public String leerLinea(Scanner sc) {
        return sc.nextLine();
    }

    public int leerEntero(Scanner sc) {
        int op;
        boolean ok;
        do {
            String s = sc.nextLine().trim();
            try {
                op = Integer.parseInt(s);
                ok = true;
            } catch (NumberFormatException e) {
                op = -1;
                ok = false;
            }
        } while (!ok);
        return op;
    }

    public int leerOpcion(Scanner sc) {
        return leerEntero(sc);
    }

    public TipoMembresia leerMembresia(Scanner sc) {
        String s = sc.nextLine().trim().toUpperCase();
        return s.startsWith("P") ? TipoMembresia.PREMIUM : TipoMembresia.BASICA;
    }
}
