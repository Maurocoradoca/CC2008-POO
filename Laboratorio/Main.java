package Laboratorio;

import java.util.Scanner;

public class Main {

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarEntrenadores();
    }

    public static void inicializarEntrenadores() {
        System.out.print("Nombre del primer entrenador: ");
        String nombre1 = entrada.nextLine();
        Entrenador e1 = new Entrenador(nombre1);

        System.out.print("Nombre del segundo entrenador: ");
        String nombre2 = entrada.nextLine();
        Entrenador e2 = new Entrenador(nombre2);

        Habilidad h1 = new Habilidad("Lanzallamas", "ATAQUE", 10, 0.5);   
        Habilidad h2 = new Habilidad("Ascuas", "ATAQUE", 8, 0.4);         
        Habilidad h3 = new Habilidad("Fotosíntesis", "DEFENSA", 12, 0.6); 
        Habilidad h4 = new Habilidad("Drenadoras", "DEFENSA", 10, 0.5);   
        Habilidad h5 = new Habilidad("Refugio", "DEFENSA", 10, 0.6);      
        Habilidad h6 = new Habilidad("Pistola Agua", "ATAQUE", 9, 0.5);   
        Habilidad h7 = new Habilidad("Impactrueno", "DAÑO", 8, 0.5);      
        Habilidad h8 = new Habilidad("Chispa", "DAÑO", 7, 0.5);           

    
        e1.agregarPokemon(new Pokemon("Charmander", "FUEGO", 30, 20, h1));
        e1.agregarPokemon(new Pokemon("Bulbasaur", "PLANTA", 25, 25, h3));
        e1.agregarPokemon(new Pokemon("Pikachu", "ELECTRICO", 28, 18, h7));
        e1.agregarPokemon(new Pokemon("Squirtle", "AGUA", 22, 30, h5));
        
        e2.agregarPokemon(new Pokemon("Torchic", "FUEGO", 31, 19, h2));
        e2.agregarPokemon(new Pokemon("Chikorita", "PLANTA", 26, 24, h4));
        e2.agregarPokemon(new Pokemon("Magnemite", "ELECTRICO", 27, 20, h8));
        e2.agregarPokemon(new Pokemon("Totodile", "AGUA", 23, 29, h6));

        menuBatalla(e1, e2);
    }

    public static void menuBatalla(Entrenador e1, Entrenador e2) {
        for (int ronda = 1; ronda <= 4; ronda++) {
            System.out.println("Ronda " + ronda + ":");

            Pokemon p1 = e1.elegirPokemon();
            Pokemon p2 = e2.elegirPokemon();

            if (p1 == null || p2 == null) {
                System.out.println("No se puede continuar la batalla, uno de los entrenadores no tiene Pokémon.");
                return;
            }

            Pelea pelea = new Pelea(p1, p2);

            System.out.println(e1.getNombre() + ", elige acción: 1. Atacar  2. Usar habilidad especial");
            int accion1 = entrada.nextInt();
            if (accion1 == 2) p1.aplicarHabilidad(p2);

            System.out.println(e2.getNombre() + ", elige acción: 1. Atacar  2. Usar habilidad especial");
            int accion2 = entrada.nextInt();

            if (accion2 == 2) p2.aplicarHabilidad(p1);
            pelea.resolverPelea();
            int ganador = pelea.determinarGanador();

            if (ganador == 1) {
                e1.incrementarRondas();
                System.out.println("Ganador de la ronda: " + e1.getNombre());
            } else if (ganador == 2) {
                e2.incrementarRondas();
                System.out.println("Ganador de la ronda: " + e2.getNombre());
            } else {
                System.out.println("Empate en la ronda.");
            }
        }

        System.out.println("Resultado final:");
        System.out.println(e1.getNombre() + " ganó " + e1.getRondasGanadas() + " rondas.");
        System.out.println(e2.getNombre() + " ganó " + e2.getRondasGanadas() + " rondas.");

        if (e1.getRondasGanadas() > e2.getRondasGanadas()) {
            System.out.println(e1.getNombre() + " es el campeón");
        } else if (e2.getRondasGanadas() > e1.getRondasGanadas()) {
            System.out.println(e2.getNombre() + " es el campeón");
        } else {
            System.out.println("Empate total");
        }
    }

    public static Pokemon elegirPokemonInteractivo(Entrenador entrenador) {
        System.out.println("\n" + entrenador.getNombre() + ", elige tu Pokémon:");
        for (int i = 0; i < entrenador.getEquipo().size(); i++) {
            Pokemon p = entrenador.getEquipo().get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " (" + p.getTipo() + ")");
        }

        int opcion = entrada.nextInt();
        while (opcion < 1 || opcion > entrenador.getEquipo().size()) {
            System.out.println("Opcion invalida. Intenta de nuevo:");
            opcion = entrada.nextInt();
        }
        return entrenador.removerPokemon(opcion - 1);
    }
}

