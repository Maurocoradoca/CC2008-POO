package Laboratorio;

import java.util.Random;

public class Habilidad {
    
    private String nombre, efecto;
    private int valor;
    private double probabilidad;

    public Habilidad(String nombre, String efecto, int valor, double probabilidad){

        this.nombre = nombre;
        this.efecto = efecto;
        this.valor = valor;
        this.probabilidad = probabilidad;
    }

    public boolean seActiva(){
        Random r = new Random();
        double numero = r.nextDouble();
        return numero <= probabilidad;

    }

    public void aplicar(Pokemon propio, Pokemon enemigo){

        System.out.println("Se uso la habiliad " + nombre);

        String tipodeefecto = efecto.toUpperCase();

        if(tipodeefecto.equals("ATAQUE")){
            propio.aumentarAtaque(valor);
        } else if(tipodeefecto.equals("DEFENSA")){
            propio.aumentarDefensa(valor);
        } else if(tipodeefecto.equals("DAÑO")){
            propio.aumentarDano(valor);
        } else {
            System.out.println("No existe este efecto");
        }
    }

}
