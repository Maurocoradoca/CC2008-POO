package EJERCICIOENCLASE;

import java.util.Scanner;

public class Consola {

    private final Scanner sc;

    public Consola() {
        this.sc = new Scanner(System.in);
    }

    public void mostrarBienvenida(int n, int barcos, int disparosMax) {
        println("Bienvenido a Battleship");
        println("El tablero es de tamaño " + n + "x" + n);
        println("Filas: A-" + (char)('A' + n - 1) + " Columnas: 1-" + n);
        println("Hay " + barcos + " barcos ocultos");
        println("Tienes un máximo de " + disparosMax + " disparos para hundirlos");
        println("Comandos:  \n\t Coordenada (ejemplo: E1) \n\t REVELAR (hacer trampa) \n\t Salir (Para terminar el juego)");
        println("Buena suerte\n");
    }

     public void mostrarMensaje(String mensaje) {
        println(mensaje);
    }
    public String pedirEntrada(String prompt){
        print(prompt);
        return this.sc.nextLine();
    }

    public void dibujarTableroTrampa(Tablero t) {
        int n = t.getN();
        print("  ");
        for (int i = 1; i <= n; i++) {
            print(i + " ");
        }
        println("");

        for (int i = 0; i < n; i++) {
            print((char)('A' + i) + " ");
            for (int j = 0; j < n; j++) {
                Celda c = t.getCelda(i, j);
                print(c.cheat() + " ");
            }
            println("");
        }
        println("");
    }
    public void dibujarTablero(Tablero t) {
        int n = t.getN();
        print("  ");
        for (int i = 1; i <= n; i++) {
            print(i + " ");
        }
        println("");

        for (int i = 0; i < n; i++) {
            print((char)('A' + i) + " ");
            for (int j = 0; j < n; j++) {
                print(t.getCelda(i, j) + " ");
            }
            println("");
        }
        println("");
    }

    public void print(String s) {
        System.out.print(s);     
    }

    public void println(String s) {
        System.out.println(s);  
    }

}
