import java.util.ArrayList;
import java.util.Scanner;

//Clase Vista
//Maneja toda la interacción con el usuario (única clase con System.out.println)
public class Vista {
    private Scanner scanner;
    
    //Constructor de Vista
    //Inicializa el scanner para entrada de usuario
    public Vista() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenuPrincipal() {
        System.out.println("SISTEMA DE GESTIÓN DE EQUIPOS AGRO-TECNOLÓGICOS");
        System.out.println("1. Listar todos los equipos");
        System.out.println("2. Buscar equipo por ID");
        System.out.println("3. Buscar equipo por nombre");
        System.out.println("4. Ordenar equipos por consumo eléctrico");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    //Muestra la lista completa de equipos
    public void mostrarListaEquipos(ArrayList<Equipo> equipos) {
        System.out.println("LISTADO DE EQUIPOS");
        
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados en el sistema.");
            return;
        }
        
        int contador = 1;
        for (Equipo equipo : equipos) {
            System.out.println("\n[" + contador + "] " + equipo.toString());
            contador++;
        }
        System.out.println("\n" + "─".repeat(56));
        System.out.println("Total de equipos: " + equipos.size());
    }
    
    //Muestra la información detallada de un equipo
    public void mostrarDetalleEquipo(Equipo equipo) {
        System.out.println("INFORMACIÓN DETALLADA DEL EQUIPO");
        System.out.println(equipo.mostrarInformacion());
    }
    
    //Muestra un mensaje general al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n✓ " + mensaje);
    }
    
    //Muestra un mensaje de error al usuario
    public void mostrarError(String error) {
        System.out.println("\nERROR: " + error);
    }
    
    //Solicita al usuario ingresar un ID
    public String solicitarId() {
        System.out.print("\nIngrese el ID del equipo: ");
        return scanner.nextLine().trim();
    }
    
    //Solicita al usuario ingresar un nombre
    public String solicitarNombre() {
        System.out.print("\nIngrese el nombre del equipo: ");
        return scanner.nextLine().trim();
    }
    
    //Solicita al usuario seleccionar una opción del menú
    public int solicitarOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    //Pausa la ejecución hasta que el usuario presione Enter
    public void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    //Muestra mensaje de despedida

    public void mostrarDespedida() {
        System.out.println("Gracias por usar el sistema");
    }
}