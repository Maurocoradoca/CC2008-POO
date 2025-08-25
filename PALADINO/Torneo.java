package PALADINO;

public class Torneo {
    String nombre;
    int cantidadequipos;
    Equipo[] equipos;


    public Torneo(String nombre, int cantidadEquipos) {
        this.nombre = nombre;
        this.cantidadequipos = cantidadEquipos;
        this.equipos = new Equipo[cantidadEquipos];
    }
    
    public void addEquipo(int numero, Equipo e){
        this.equipos[numero] = e;
    }

    public String getResumen()
    {
        String res = "===" + this.nombre + "===\n";

        for (Equipo e: equipos) {
            res += e;
        }

        return res;
    }

    
}
