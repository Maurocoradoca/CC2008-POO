package Laboratorio;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrenador {
    
    private String nombre;
    private ArrayList<Pokemon> equipo;
    private int rondasGanadas;

    public Entrenador(String nombre){
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
        this.rondasGanadas = 0;
    }

    public Pokemon elegirPokemon() {
    System.out.println("\n" + nombre + ", elige tu Pokémon:");
    for (int i = 0; i < equipo.size(); i++) {
        Pokemon p = equipo.get(i);
        System.out.println((i + 1) + ". " + p.getNombre() + " (" + p.getTipo() + ")");
    }

    Scanner entrada = new Scanner(System.in);
    int opcion = entrada.nextInt();

    while (opcion < 1 || opcion > equipo.size()) {
        System.out.println("Opción inválida. Intenta de nuevo:");
        opcion = entrada.nextInt();
    }

    return equipo.remove(opcion - 1);
}

    public void incrementarRondas(){
        rondasGanadas++;
    }

    
    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPokemon(Pokemon p) {
        if (equipo.size() < 4) {
            equipo.add(p);
        } else {
            System.out.println("El equipo ya tiene 4 Pokémon.");
        } 
    } 

    public ArrayList<Pokemon> getEquipo(){
        return equipo;
    }

    public Pokemon removerPokemon(int indice){
        return equipo.remove(indice);
    }
} 




