package TheErasTour;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();
    static Localidad[] localidades;
    static Usuario compradorActual;
    public static void main(String[] args) {
       localidades = new Localidad[]{
        new Localidad(1,1000,100),
        new Localidad(5,500,500),
        new Localidad(10,100,1000)
       };

        mostrarMenu();
        sc.close();
     }  
        
     public static void mostrarMenu() {
        System.out.println("\n---Menu Principal---");
        System.out.println("1. Nuevo Comprador");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Disponibilidad total");
        System.out.println("4. Disponibilidad individual");
        System.out.println("5. Reporte");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opcion: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                registrarnuevoComprador();
                break;
            case 2:
                solicituddeboleto();
                break;
            case 3:
                disponiblestotal();
                break;
            case 4:
                disponibleindividual();
                break;
            case 5:
                hacerreporte();
                break;
            case 6:
                return;
            default:
                System.out.println("Opcion invalida");
        }
        
        mostrarMenu();
        
    }

    private static void registrarnuevoComprador() {
    System.out.println("Ingrese su nombre: ");
    String nombre = sc.nextLine();
    System.out.println("Ingrese su email: ");
    String email = sc.nextLine();
    int totalDisponible = 0;
    for (Localidad loc : localidades) {
        totalDisponible += loc.disponibles();
    }
    System.out.println("Cantidad de boletos deseados (máximo " + totalDisponible + "): ");
    int cantidad = sc.nextInt();
    if (cantidad > totalDisponible) {
        System.out.println("Error: No hay suficientes boletos. Disponibles: " + totalDisponible);
        return; 
    }
    System.out.println("Presupuesto maximo: ");
    double presupuesto = sc.nextDouble();

    compradorActual = new Usuario(nombre, email, cantidad, presupuesto);
    System.out.println("Comprador registrado");
}
    
    private static void solicituddeboleto() {
    if (compradorActual == null) {
        System.out.println("Error: no existe ningún comprador registrado");
        return;
    }

    int a = r.nextInt(15000) + 1;
    int b = r.nextInt(15000) + 1; 
   
    int min = Math.min(a, b);
    int max = Math.max(a, b);
    int ticketNumber = min + r.nextInt(max - min + 1); 

    Ticket ticket = new Ticket(ticketNumber);

    System.out.println("Validacion");
    System.out.println("Numero de ticket generado: " + ticket.getNumero());
    System.out.println("Rango generado: [" + a + ", " + b + "]");

    if (ticket.esValido(a, b)) {
        System.out.println("Ticket válido");

        Localidad loc = localidades[r.nextInt(localidades.length)];
        System.out.println("\nLocalidad asignada: " + loc.getId());
        System.out.println("Precio: $" + loc.getPrecio());
        System.out.println("Boletos disponibles: " + loc.disponibles());

        if (loc.getPrecio() > compradorActual.getpresupuestoMaximo()) {
            System.out.println("Presupuesto insuficiente.");
        } else if (!loc.tieneEspacio()) {
            System.out.println("Localidad agotada.");
        } else {
            int disponibles = loc.disponibles();
            int cantidaddeseada = compradorActual.getCantidad();
            int cantidadvendida = loc.venderBoletos(Math.min(disponibles, cantidaddeseada));

            System.out.println("Compra realizada correctamente, boletos adquiridos: " + cantidadvendida);
        }

    } else {
        System.out.println("Ticket no válido");
    }
    }

    private static void disponiblestotal(){
        System.out.println("\n---DISPONIBLES---");
        for (Localidad loc : localidades){
            System.out.println("Localidad " + loc.getId() + ": " + loc.disponibles() + " disponibles ");
        }
    }

    private static void disponibleindividual(){
        System.out.println("Ingrese ID de localidad ya sea 1, 5 o 10: ");
        int id = sc.nextInt();

        for (Localidad loc : localidades){
            if(loc.getId() == id) {
                System.out.println("Localidad "+ id + ": " + loc.disponibles() + " disponibles");
                return;
            }
        }
        System.out.println("Localidad no encontrada");
    }

    private static void hacerreporte(){
        System.out.println("\n---REPORTE---");
        double total = 0;
        for(Localidad loc : localidades){
            double ingreso = loc.ingresoObtenidos();
            System.out.println("Localidad " + loc.getId() + ": " + ingreso);
            total += ingreso;
        }
        System.out.println("TOTAL = " + total);
    }
    
}
