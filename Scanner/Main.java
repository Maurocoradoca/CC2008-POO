package Scanner;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su nombre ");
        String nombre = sc.nextLine();

         System.out.print("Ingrese su edad ");
        int edad = sc.nextInt();

        sc.nextLine();

        System.out.print("Hola " + nombre + "Tenes " + " anos");

    }
    
} 

/// 