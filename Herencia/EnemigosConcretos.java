class Bandido extends Enemigo {
    
    public Bandido(boolean esJefe) {
        super(esJefe ? "Bandido Jefe" : "Bandido", 
              TipoEnemigo.BANDIDO, 
              esJefe ? 80 : 50, 
              esJefe ? 20 : 12, 
              esJefe);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        int reduccion = esJefe ? 8 : 5;
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 2, -reduccion));
        this.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 2, reduccion));
    }
}

class BandidoJefe extends Bandido {
    
    public BandidoJefe() {
        super(true);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo); 
        this.atacar(objetivo);
    }
}

class Chaman extends Enemigo {
    
    public Chaman(boolean esJefe) {
        super(esJefe ? "Chamán Jefe" : "Chamán", 
              TipoEnemigo.CHAMAN, 
              esJefe ? 60 : 40, 
              esJefe ? 15 : 10, 
              esJefe);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        int cura = esJefe ? 25 : 15;
        this.curar(cura);
    }
}

class ChamanJefe extends Chaman {
    
    public ChamanJefe() {
        super(true);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo); 
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.VENENO, 2, 3));
    }
}

class Arquero extends Enemigo {
    
    public Arquero(boolean esJefe) {
        super(esJefe ? "Arquero Jefe" : "Arquero", 
              TipoEnemigo.ARQUERO, 
              esJefe ? 55 : 35, 
              esJefe ? 18 : 15, 
              esJefe);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        int daño = this.atk + (esJefe ? 15 : 8);
        objetivo.recibirDaño(daño);
    }
}

class ArqueroJefe extends Arquero {
    
    public ArqueroJefe() {
        super(true);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo); 
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.EVASION, 2, -5));
    }
}

class Lancero extends Enemigo {
    
    public Lancero(boolean esJefe) {
        super(esJefe ? "Lancero Jefe" : "Lancero", 
              TipoEnemigo.LANCERO, 
              esJefe ? 70 : 45, 
              esJefe ? 22 : 14, 
              esJefe);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        int daño = this.atk + (esJefe ? 10 : 6);
        objetivo.recibirDaño(daño);
        this.agregarEfecto(new EfectoTemporal(Efecto.ACELERACION, 1, 1));
    }
}

class LanceroJefe extends Lancero {
    
    public LanceroJefe() {
        super(true);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo); 
        this.agregarEfecto(new EfectoTemporal(Efecto.ESCUDO, 2, 5));
    }
}

class CaballeroCorompido extends Enemigo {
    
    public CaballeroCorompido(boolean esJefe) {
        super(esJefe ? "Caballero Corrompido Jefe" : "Caballero Corrompido", 
              TipoEnemigo.CABALLERO_CORROMPIDO, 
              esJefe ? 100 : 65, 
              esJefe ? 25 : 16, 
              esJefe);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        int daño = esJefe ? 12 : 8;
        objetivo.recibirDaño(daño);
        objetivo.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 3, esJefe ? -8 : -5));
    }
}

class CaballeroCorrompidoJefe extends CaballeroCorompido {
    
    public CaballeroCorrompidoJefe() {
        super(true);
    }
    
    @Override
    public void habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo); 
        this.agregarEfecto(new EfectoTemporal(Efecto.ATAQUE_PLUS, 2, 10));
        this.agregarEfecto(new EfectoTemporal(Efecto.ESCUDO, 2, 3));
    }
}
