package Laboratorio;

public class Pokemon {
    
    private String nombre, tipo;
    private int ataque, defensa;
    private Habilidad habilidadEspecial;
    private int modifcadorExtra = 0;


    public Pokemon(String nombre, String tipo, int ataque, int defensa, Habilidad habilidadEspecial){

        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.habilidadEspecial = habilidadEspecial; 
    }

    public String getTipo(){
        return tipo;
    }

    public void aumentarAtaque(int valor){
        this.modifcadorExtra += valor;
    }

    public void aumentarDefensa(int valor){
        this.defensa += valor;
    }

    public void aumentarDano(int valor){
        this.modifcadorExtra += valor;
    }

    public int calcularPoder(Pokemon enemigo){
        int poderTotal = ataque + modifcadorExtra;
        int bonusTipo = 0;

        String tipoAtacante = tipo.toUpperCase();
        String tipoDefensa = enemigo.tipo.toUpperCase();

        if ((tipoAtacante.equals("FUEGO") && tipoDefensa.equals("PLANTA")) ||
        (tipoAtacante.equals("PLANTA") && tipoDefensa.equals("AGUA")) ||
        (tipoAtacante.equals("AGUA") && tipoDefensa.equals("FUEGO"))||
        (tipoAtacante.equals("ELECTRICO") && tipoDefensa.equals("AGUA"))){

        bonusTipo = 20;
        System.out.println("Ganaste, Bonus de tipo +20");

        } else if ((tipoAtacante.equals("FUEGO")&& tipoDefensa.equals("AGUA")) ||
        (tipoAtacante.equals("AGUA") && tipoDefensa.equals("PLANTA")) ||
        (tipoAtacante.equals("PLANTA") && tipoDefensa.equals("FUEGO")) ||
        (tipoAtacante.equals("AGUA") && tipoDefensa.equals("ELECTRICO"))){

        bonusTipo = -10;    
        System.out.println("Perdiste, Bonus de tipo -10");

        } else {
            System.out.println("Tipo neutral");
        }

        return poderTotal + bonusTipo;
            
    }

    public boolean activdarHabilidad(){

        return habilidadEspecial.seActiva();

    }

    public void aplicarHabilidad(Pokemon enemigo){
        
        if(activdarHabilidad()){
            habilidadEspecial.aplicar(this, enemigo);
        } else{
            System.out.println("La habilidad no puedo activasrse");
        }
    }

    public String getNombre(){
        return nombre;
    }


}   
