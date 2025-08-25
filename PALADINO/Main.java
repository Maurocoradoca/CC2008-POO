package PALADINO;

public class Main {

    public static void main(String[] args) {
        
        Torneo palatino = new Torneo("Torneo palatino", 3);
        Equipo t1 = new Equipo("Barca", 1, 100);
        Equipo t2 = new Equipo("Patetico de madrid", 2, 6);
        Equipo t3 = new Equipo("Real Madrid",3 ,3);

        palatino.addEquipo(0, t1);
        palatino.addEquipo(1, t2);
        palatino.addEquipo(2, t3);

        ///t3.setGoles(1);
        ///t3.anotar();
        ///t3.anotar();
        
       /// int totalGoles = t1.getGoles() + t2.getGoles() + t3.getGoles();
        
       // System.out.println(totalGoles);
    

        //System.out.println(t1);
        //System.out.println(t2);
        //System.out.println(t3);


        System.out.println(palatino.getResumen());

    }   
    
}
