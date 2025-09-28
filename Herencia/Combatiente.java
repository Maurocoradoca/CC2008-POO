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
        hp -= d;
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
            // Aplicar el efecto según su tipo
            switch (efecto.getTipo()) {
                case VENENO:
                    recibirDaño(efecto.getMagnitud());
                    break;
                case ESCUDO:
                    // El escudo reduce el daño recibido
                    break;
                // Otros efectos se pueden implementar aquí
            }
            
            efecto.tick();
            if (efecto.haExpirado()) {
                efectosARemover.add(efecto);
            }
        }
        
        // Remover efectos expirados
        efectos.removeAll(efectosARemover);
    }
    
    public void agregarEfecto(EfectoTemporal efecto) {
        efectos.add(efecto);
    }
    
    // Método abstracto que deben implementar las subclases
    public abstract void tomarTurno(Batalla ctx);
    
    // Getters básicos
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getAtk() { return atk; }
    public List<EfectoTemporal> getEfectos() { return efectos; }
    
    // Setter para ataque
    public void setAtk(int atk) { this.atk = atk; }
    
    @Override
    public String toString() {
        return nombre + " - HP: " + hp + "/" + hpMax + " ATK: " + atk;
    }
}