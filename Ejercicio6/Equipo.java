//Clase abstracta Equipo
//Representa la clase base para todos los equipos del sistema
//Implementa Comparable para permitir ordenamiento por consumo eléctrico
public abstract class Equipo implements Comparable<Equipo> {
    // Atributos protegidos para acceso desde clases hijas
    protected String id;
    protected String nombre;
    protected String fabricante;
    protected String modelo;
    protected double consumoElectrico; // en watts
    protected String estado;
    
    //Constructor completo de Equipo
    public Equipo(String id, String nombre, String fabricante, String modelo, double consumoElectrico) {
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.consumoElectrico = consumoElectrico;
        this.estado = "Activo";
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getFabricante() {
        return fabricante;
    }
    
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public double getConsumoElectrico() {
        return consumoElectrico;
    }
    
    public void setConsumoElectrico(double consumoElectrico) {
        this.consumoElectrico = consumoElectrico;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    //Método abstracto que debe ser implementado por las clases hijas
    public abstract String mostrarInformacion();
    
    //Override de toString para representación en texto del equipo
    @Override
    public String toString() {
        return String.format("ID: %s | %s (%s) | Consumo: %.2fW", 
                            id, nombre, fabricante, consumoElectrico);
    }
    
    //Override de equals para comparación de equipos por ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return id.equals(equipo.id);
    }
    
    //Implementación de Comparable para ordenar por consumo eléctrico
    @Override
    public int compareTo(Equipo otro) {
        return Double.compare(this.consumoElectrico, otro.consumoElectrico);
    }
}
