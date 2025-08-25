package EJERCICIO2;

public class Salon {

    private int numero;
    private TipoSalon tipo;
    private int capacidad;
    private double costoPorHora;
    private boolean disponible;

    public Salon(int numero, TipoSalon tipo, int capacidad, double costoPorHora) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = Math.max(0, capacidad);
        this.costoPorHora = Math.max(0.0, costoPorHora);
        this.disponible = true; 
    }

    public boolean esAptoPara(TipoEvento tipoEvento) {
        if (tipo == TipoSalon.GRANDE){
            return tipoEvento == TipoEvento.VIP;
        }
        return true;

    }

    public void marcarOcupado(){
        this.disponible = false;
    }
    public void marcarDisponible(){
        this.disponible = true;
    }

    public String toString() {
        return "Salon No. " + numero + "[" + tipo + "], capacidad = "
        + capacidad + ", Q/hr=" + costoPorHora + ", disponible="
        + disponible;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public TipoSalon getTipo(){
        return tipo;
    }

    public void setTipo(TipoSalon tipo){
        this.tipo = tipo;
    }

    public int getCapacidad(){
        return capacidad;
    }   

    public void setCapacidad(int capacidad){
        this.capacidad = Math.max(0, capacidad);
    }

    public double getCostoPorHora(){
        return costoPorHora;
    }

    public void setCostoPorHora(double costoPorHora){
        this.costoPorHora = Math.max(0.0, costoPorHora);
    }

    public boolean isDisponible(){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
 }

        
