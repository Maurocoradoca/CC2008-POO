package EJERCICIO2;

import java.util.Scanner;

public class Vista {

    public String obtenerMenu() {
        return """
               MENÚ 
               1) Agregar salón
               2) Recibir nueva solicitud de evento
               3) Intentar asignar salones a solicitudes en espera
               4) Reservas
               5) Salones
               6) Lista de espera
               0) Salir
               """;
    }

    public int leerEntero(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try { return Integer.parseInt(line); }
            catch (NumberFormatException e) {}
        }
    }

    public double leerDouble(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try { return Double.parseDouble(line); }
            catch (NumberFormatException e) {}
        }
    }

    public String leerLinea(Scanner sc) {
        return sc.nextLine();
    }

    public TipoSalon leerTipoSalon(Scanner sc) {
        while (true) {
            String t = sc.nextLine().trim().toUpperCase();
            if (t.equals("P")) return TipoSalon.PEQUENO;
            if (t.equals("M")) return TipoSalon.MEDIANO;
            if (t.equals("G")) return TipoSalon.GRANDE;
            try { return TipoSalon.valueOf(t); }
            catch (IllegalArgumentException ex) {}
        }
    }

    public TipoEvento leerTipoEvento(Scanner sc) {
        while (true) {
            String t = sc.nextLine().trim().toUpperCase();
            if (t.equals("R")) return TipoEvento.REGULAR;
            if (t.equals("V")) return TipoEvento.VIP;
            try { return TipoEvento.valueOf(t); }
            catch (IllegalArgumentException ex) {}
        }
    }

    public boolean leerBooleanSiNo(Scanner sc) {
        while (true) {
            String t = sc.nextLine().trim().toLowerCase();
            if (t.equals("s") || t.equals("si") || t.equals("sí")) return true;
            if (t.equals("n") || t.equals("no")) return false;
        }
    }
}

    
