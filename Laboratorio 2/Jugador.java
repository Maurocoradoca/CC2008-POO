public class Jugador {
    // Atributos simples
    private String nombre;
    private int pares;

    // Constructor
    public Jugador(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank()) ? "Jugador" : nombre.trim();
        this.pares = 0; // Inicialmente sin pares
    }

    // Comportamientos básicos
    public void anadirPar() {
        this.pares += 1;
    }

    public void reset() {
        this.pares = 0;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    public int getPares() {
        return pares;
    }

    public void setPares(int pares) {
        if (pares >= 0) {
            this.pares = pares;
        }
    }

    public String toString() {
        return nombre + " (pares: " + pares + ")";
    }
}
