public class Jugador extends Combatiente {
    private Rol rol;
    private Inventario inventario;
    
    public Jugador(String nombre, Rol rol) {
        super(nombre, configurarHP(rol), configurarAtaque(rol));
        this.rol = rol;
        this.inventario = new Inventario(this);
        inicializarInventario();
    }
    
    private static int configurarHP(Rol rol) {
        switch (rol) {
            case GUERRERO:
                return 120; // Bastante vida
            case EXPLORADOR:
                return 80; // Vida normal
            default:
                return 80;
        }
    }
    
    private static int configurarAtaque(Rol rol) {
        switch (rol) {
            case GUERRERO:
                return 25; // Bastante ataque
            case EXPLORADOR:
                return 18; // Ataque normal
            default:
                return 18;
        }
    }
    
    private void inicializarInventario() {
        switch (rol) {
            case GUERRERO:
                // Poca capacidad para items
                inventario.agregar(new PocionVida(2, 30));
                inventario.agregar(new BoostAtaque(1, 10));
                inventario.agregar(new EscudoPortatil(1));
                inventario.agregar(new BombaHumo(1));
                break;
            case EXPLORADOR:
                // Amplia variedad de items
                inventario.agregar(new PocionVida(3, 25));
                inventario.agregar(new BoostAtaque(2, 8));
                inventario.agregar(new FlechaEnvenenada(2));
                inventario.agregar(new BengalaCegadora(2));
                inventario.agregar(new EscudoPortatil(2));
                inventario.agregar(new TonicoVigor(1));
                inventario.agregar(new BombaHumo(2));
                inventario.agregar(new Trampa(1));
                break;
        }
    }
    
    public boolean usarItem(int idx, Combatiente objetivo) {
        Item item = inventario.getItem(idx);
        if (item != null && item.puedeUsarse()) {
            boolean usado = item.usar(this, objetivo);
            if (usado) {
                inventario.removerSiAgotado();
            }
            return usado;
        }
        return false;
    }
    
    @Override
    public void tomarTurno(Batalla ctx) {
    }
    
    // Getters
    public Rol getRol() { 
        return rol; 
    }
    
    public Inventario getInventario() { 
        return inventario; 
    }
    
    @Override
    public String toString() {
        return nombre + " (" + rol + ") - HP: " + hp + "/" + hpMax + " ATK: " + atk;
    }
}
