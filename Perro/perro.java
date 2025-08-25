package Perro;

public class perro 
{
    private String nombre, raza;
    private int edad;

    public perro(String nombre, String raza, int edad){
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
    
    }

    //setters
    // public void setnombre(String nombre){
    //     this.nombre = nombre;
    // }

    // public void setraza(String raza){
    //     this.raza = raza;
    // }

    // public void setedad(int edad){
    //     this.edad = edad;
    // }
    
    //getters
    public String getnombre(){

        return nombre;
    }    

    public String getraza(){
        return raza;
    }

    public int getedad(){
        return edad;
    }
    
}
