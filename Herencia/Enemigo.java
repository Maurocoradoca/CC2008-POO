import java.util.Random;

public abstract class Enemigo extends Combatiente {
    protected TipoEnemigo tipo;
    protected boolean esJefe;
    protected Random random;
    
    public Enemigo(String nombre, TipoEnemigo tipo, int hp, int atk, boolean esJefe) {
        super(nombre, hp, atk);
        this.tipo = tipo;
        this.esJefe = esJefe;
        this.random = new Random();
    }
    
    public abstract void habilidadEspecial(Combatiente objetivo);
    
    @Override
    public void tomarTurno(Batalla ctx) {
    Acciones accion = (random.nextDouble() < 0.7) ? Acciones.ATACAR : Acciones.HABILIDAD_ESPECIAL;
    
    Combatiente objetivo = ctx.getJugadorAleatorio();
        if (objetivo != null) {
            switch (accion) {
                case ATACAR:
                    atacarEnemigo(objetivo, ctx);
                    break;
                case HABILIDAD_ESPECIAL:
                    usarHabilidadEspecial(objetivo, ctx);
                    break;
                case USAR_ITEM:
                    ctx.registrarAccion(this.getNombre() + " pasó su turno.");
                    break;
                case PASAR_TURNO:
                    ctx.registrarAccion(this.getNombre() + " pasó su turno.");
                    break;
                case SALIR:
                    ctx.registrarAccion(this.getNombre() + " pasó su turno.");
                    break;
            }
        } else {
            ctx.registrarAccion(this.getNombre() + " pasó su turno (no hay objetivos).");
        }
    }
    
    private void atacarEnemigo(Combatiente objetivo, Batalla ctx) {
        int hpAntes = objetivo.getHp();
        this.atacar(objetivo);
        int daño = hpAntes - objetivo.getHp();
        
        String mensaje = this.getNombre() + " atacó a " + objetivo.getNombre() + 
                        " causando " + daño + " de daño.";
        
        if (!objetivo.estaVivo()) {
            mensaje += " " + objetivo.getNombre() + " ha sido derrotado!";
        }
        
        ctx.registrarAccion(mensaje);
    }
    
    private void usarHabilidadEspecial(Combatiente objetivo, Batalla ctx) {
        this.habilidadEspecial(objetivo);
        ctx.registrarAccion(this.getNombre() + " usó su habilidad especial en " + objetivo.getNombre() + ".");
    }
    
    @Override
    public void atacar(Combatiente objetivo) {
        int daño = this.atk;
        
        // Aplicar bonus de ataque
        for (EfectoTemporal efecto : this.efectos) {
            if (efecto.getTipo() == Efecto.ATAQUE_PLUS) {
                daño += efecto.getMagnitud();
            }
        }
        
        // Los jefes hacen más daño
        if (esJefe) {
            daño = (int)(daño * 1.5);
        }
        
        objetivo.recibirDaño(daño);
    }
    
    // Getters
    public TipoEnemigo getTipo() { 
        return tipo; 
    }
    
    public boolean esJefe() { 
        return esJefe; 
    }
    
    @Override
    public String toString() {
        String titulo = esJefe ? " (Jefe)" : "";
        return nombre + titulo + " - HP: " + hp + "/" + hpMax + " ATK: " + atk;
    }
}