// Clase Vista hospital 
// Maneja toda la interaccion con el usuario mediante consola 

import java.util.Scanner;

public class VistaHospital {
    private Scanner scanner;

    public VistaHospital() {
        this.scanner = new Scanner(System.in);
    }

    public int menu() {
        System.out.println("              SISTEMA INTEGRAL DE GESTION HOSPITALARIA                        ");
        System.out.println("\n GESTION DE PERSONAL");
        System.out.println("1. Contratar nuevo personal médico ");
        System.out.println("2. Buscar personal por departamento ");
        System.out.println("3. Buscar médico por ID ");
        System.out.println("4. Listar todo el personal ");
        System.out.println("5. Registrar actividad médica ");
        System.out.println("\n GESTION DE CITAS");
        System.out.println("6. Programar nueva cita médica ");
        System.out.println("7. Buscar cita por ID ");
        System.out.println("8. Listar citas por estado ");
        System.out.println("9. Reagendar cita ");
        System.out.println("10. Actualizar estado de cita ");
        System.out.println("11. Cancelar cita ");
        System.out.println("\n REPORTES");
        System.out.println("12. Generar reporte de nómina completo ");
        System.out.println("13. Generar reporte de cita ");
        System.out.println("14. Generar reporte de reagendamientos ");
        System.out.println("15. Calcular nómina total ");
        System.out.println("\n SISTEMA");
        System.out.println("0. Salir del sistema ");
        System.out.print("\n Seleccione una opción: ");
        
        return leerEntero();
    }

    public int menuContratarPersonal() {
        System.out.println("\n CONTRATAR PERSONAL MEDICO");
        System.out.println("1. Doctor General ");
        System.out.println("2. Cirujano ");
        System.out.println("3. Enfermero ");
        System.out.println("4. Radiólogo ");
        System.out.println("5. Farmacéutico ");
        System.out.println("6. Terapeuta ");
        System.out.println("0. Volver al menú principal ");
        System.out.print("\n Seleccione el tipo de médico: ");
        
        return leerEntero();
    }

    public void mensajeError(String mensaje) {
        System.out.println("\n ERROR: " + mensaje);
    }

    public void mensajeExito(String mensaje) {
        System.out.println("\n ÉXITO: " + mensaje);
    }

    public void mensajeInfo(String mensaje) {
        System.out.println("\n INFO: " + mensaje);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEncabezado(String titulo) {
        System.out.println(centrarTexto(titulo, 76));
    } 

    public String solicitarTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int solicitarEntero(String prompt) {
        System.out.print(prompt);
        return leerEntero();
    }

    public double solicitarDouble(String prompt) {
        System.out.print(prompt);
        return leerDouble();
    }

    public boolean solicitarBoolean(String prompt) {
        System.out.print(prompt + " (S/N): ");
        String respuesta = scanner.nextLine().trim();
        return respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("SI");
    }

    private int leerEntero() {
        int numero = -1;
        boolean valido = false;
        
        do {
            try {
                numero = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, Ingrese un número entero: ");
            }
        } while (!valido);
        
        return numero;
    }

    private double leerDouble() {
        double numero = -1;
        boolean valido = false;
        
        do {
            try {
                numero = Double.parseDouble(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, Ingrese un número decimal: ");
            }
        } while (!valido);
        
        return numero;
    }

    private String centrarTexto(String texto, int ancho) {
        int espacios = (ancho - texto.length()) / 2;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append(texto);
        
        while (sb.length() < ancho) {
            sb.append(" ");
        }
        
        return sb.toString();
    }

    public void mostrarDespedida() {
        System.out.println("            Gracias por usar el Sistema de Gestión Hospitalaria               ");
    }

    public void pausar() {
        System.out.print("\nPresione Enter para continuar.");
        scanner.nextLine();
    }

    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void cerrar() {
        scanner.close();
    }
}
