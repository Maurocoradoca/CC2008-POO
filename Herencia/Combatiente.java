import java.util.List;
import java.util.ArrayList;

public abstract class Combatiente {
    protected String nombre;
    protected int hp;
    protected int hpMax;
    protected int atk;
    protected boolean vivo;
    protected List<EfectoTemporal> efectos;
    
    public Combatiente(String nombre, int hp, int atk) {
        this.nombre = nombre;
        this.hp = hp;
        this.hpMax = hp;
        this.atk = atk;
        this.vivo = true;
        this.efectos = new ArrayList<>();
    }
    
    public void recibirDaño(int d) {
        int daño = d;
        
        // Aplicar reducción por escudo
        for (EfectoTemporal efecto : efectos) {
            if (efecto.getTipo() == Efecto.ESCUDO) {
                daño -= efecto.getMagnitud();
                if (daño < 0) daño = 0;
                break;
            }
        }
        
        hp -= daño;
        if (hp <= 0) {
            hp = 0;
            vivo = false;
        }
    }
    
    public void curar(int c) {
        hp += c;
        if (hp > hpMax) {
            hp = hpMax;
        }
    }
    
    public boolean estaVivo() {
        return vivo;
    }
    
    public void aplicarEfectos() {
        List<EfectoTemporal> efectosARemover = new ArrayList<>();
        
        for (EfectoTemporal efecto : efectos) {
            switch (efecto.getTipo()) {
                case VENENO:
                    recibirDaño(efecto.getMagnitud());
                    break;
            }
            
            efecto.tick();
            if (efecto.haExpirado()) {
                efectosARemover.add(efecto);
            }
        }
        
        efectos.removeAll(efectosARemover);
    }
    
    public void agregarEfecto(EfectoTemporal efecto) {
        efectos.add(efecto);
    }
    
    // Método abstracto
    public abstract void tomarTurno(Batalla ctx);
    
    // Método para atacar
    public void atacar(Combatiente objetivo) {
        int daño = this.atk;
        
        // Aplicar bonus de ataque
        for (EfectoTemporal efecto : this.efectos) {
            if (efecto.getTipo() == Efecto.ATAQUE_PLUS) {
                daño += efecto.getMagnitud();
            }
        }
        
        objetivo.recibirDaño(daño);
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getAtk() { return atk; }
    public List<EfectoTemporal> getEfectos() { return efectos; }
    
    @Override
    public String toString() {
        return nombre + " - HP: " + hp + "/" + hpMax + " ATK: " + atk;
    }
}