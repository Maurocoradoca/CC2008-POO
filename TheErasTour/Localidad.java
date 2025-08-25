package TheErasTour;

public class Localidad {
    
    private int id, capacidadMaxima, boletosVendidos;
    private double precio;

    public Localidad(int id, int capacidadMaxima, double precio){
        this.id = id;
        this.capacidadMaxima = capacidadMaxima;
        this.boletosVendidos = 0;
        this.precio = precio;

    }

    public boolean tieneEspacio() {
        return boletosVendidos < capacidadMaxima;
    }

    public int disponibles() {
        return capacidadMaxima - boletosVendidos;
    }

    public int venderBoletos(int n) {
        int boletosaVender = Math.min(n, disponibles());

        boletosVendidos += boletosaVender;

        return boletosaVender;
    }

    public double ingresoObtenidos(){
        return boletosVendidos * precio;
    }

    public double getPrecio(){
        return precio;
    }

    public int getId(){
        return id;
    }

}
