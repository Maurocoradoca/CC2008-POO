package Perro;

public class main {
    public static void main(String[] args) 
    {
        ///perro miPerro = new perro();
        ///miPerro.setnombre("terre");
        ///miPerro.setraza("pitbull");
        ///miPerro.setedad(3);
        ///System.out.println(miPerro.getnombre());
        ///System.out.println(miPerro.getraza());
        ///System.err.println(miPerro.getedad());


        ///perro tuPerro = new perro();
        ///tuPerro.setnombre("teo");
        ///tuPerro.setraza("chihua");
        ///tuPerro.setedad(2);
        ///System.out.println(tuPerro.getnombre());
        ///System.out.println(tuPerro.getraza());
        ///System.err.println(tuPerro.getedad());

        perro miPerro = new perro("Teo", "Chihua", 3);
        System.out.println(miPerro.getnombre());
        System.out.println(miPerro.getraza());
        System.out.println(miPerro.getedad());

        perro tuPerro = new perro("KODA", "Golden", 1);
        System.out.println(tuPerro.getnombre());
        System.out.println(tuPerro.getraza());
        System.out.println(tuPerro.getedad());


        
    }
}
