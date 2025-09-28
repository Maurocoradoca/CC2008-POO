class PocionVida extends Item {
    private int cura;
    
    public PocionVida(int usos, int cura) {
        super("Poción de Vida", usos);
        this.cura = cura;
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.curar(cura);
        consumirUso();
        return true;
    }
}

// BoostAtaque.java
class BoostAtaque extends Item {
    private int plus;
    
    public BoostAtaque(int usos, int plus) {
        super("Boost de Ataque", usos);
        this.plus = plus;
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 3, plus));
        consumirUso();
        return true;
    }
}

// FlechaEnvenenada.java
class FlechaEnvenenada extends Item {
    
    public FlechaEnvenenada(int usos) {
        super("Flecha Envenenada", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        // Atacar y envenenar
        origen.atacar(objetivo);
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.VENENO, 3, 2));
        consumirUso();
        return true;
    }
}

// BengalaCegadora.java
class BengalaCegadora extends Item {
    
    public BengalaCegadora(int usos) {
        super("Bengala Cegadora", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 2, -5));
        consumirUso();
        return true;
    }
}

// EscudoPortatil.java
class EscudoPortatil extends Item {
    
    public EscudoPortatil(int usos) {
        super("Escudo Portátil", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ESCUDO, 3, 5));
        consumirUso();
        return true;
    }
}

// TonicoVigor.java
class TonicoVigor extends Item {
    
    public TonicoVigor(int usos) {
        super("Tónico de Vigor", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ACELERACION, 2, 1));
        consumirUso();
        return true;
    }
}

// BombaHumo.java
class BombaHumo extends Item {
    
    public BombaHumo(int usos) {
        super("Bomba de Humo", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 2, -7));
        origen.agregarEfecto(new EfectoTemporal(Efecto.EVASION, 2, 3));
        consumirUso();
        return true;
    }
}

// Trampa.java
class Trampa extends Item {
    
    public Trampa(int usos) {
        super("Trampa", usos);
    }
    
    @Override
    public boolean usar(Combatiente origen, Combatiente objetivo) {
        if (!puedeUsarse()) {
            return false;
        }
        
        int daño = origen.getAtk() / 2;
        objetivo.recibirDaño(daño);
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ACELERACION, 3, -1));
        consumirUso();
        return true;
    }
}
