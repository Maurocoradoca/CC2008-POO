package EJERCICIO3;

import java.util.Scanner;

public class Vista {

    public String menu(){
        return "Gimnasio\n"
                + "1. Agregar Entrenador\n"
                + "2. Agregar Rutina\n"
                + "3. Asignar Rutina a Entrenador\n"
                + "4. Mostrar Entrenadores y sus Rutinas\n"
                + "5. Salir\n";
    }

    public String leerLinea(Scanner sc){
        return sc.nextLine();
    }
    
}
