package Laboratorio;

public class Pelea {
    
    private Pokemon atacante;
    private Pokemon defensor;

    public Pelea(Pokemon atacante, Pokemon defensor){

        this.atacante = atacante;
        this.defensor = defensor;
    }

    public void resolverPelea(){
        System.out.println("Iniciar la pelea entre " + atacante.getNombre() + " y " + defensor.getNombre());

        atacante.aplicarHabilidad(defensor);
        defensor.aplicarHabilidad(atacante);

        int poderAtacante = atacante.calcularPoder(defensor);
        int poderDefensor = defensor.calcularPoder(atacante);

        System.out.println("Poder de " + atacante.getNombre() + ": " + poderAtacante);
        System.out.println("Poder de " + defensor.getNombre() + ": " + poderDefensor);
    }

    public int determinarGanador() {
        int poderAtacante = atacante.calcularPoder(defensor);
        int poderDefensor = defensor.calcularPoder(atacante);

        if (poderAtacante > poderDefensor) {
            return 1;
        } else if (poderDefensor > poderAtacante) {
            return 2;
        } else {
            return 0;
        }
    }

    

}
