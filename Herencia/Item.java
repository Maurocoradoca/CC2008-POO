public abstract class Item {
    protected String nombre;
    protected int usos;
    
    public Item(String nombre, int usos) {
        this.nombre = nombre;
        this.usos = usos;
    }
    
    public abstract boolean usar(Combatiente origen, Combatiente objetivo);
    
    public boolean puedeUsarse() {
        return usos > 0;
    }
    
    protected void consumirUso() {
        if (usos > 0) {
            usos--;
        }
    }
    
    // Getters
    public String getNombre() { 
        return nombre; 
    }
    
    public int getUsos() { 
        return usos; 
    }
    
    @Override
    public String toString() {
        return nombre + " (usos: " + usos + ")";
    }
}