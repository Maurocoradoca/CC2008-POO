import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Batalla {
    private List<Jugador> jugadores;
    private List<Enemigo> enemigos;
    private RegistroAcciones log;
    private Random rnd;
    private Map<Combatiente, List<EfectoTemporal>> buffs;
    private int turnoActual;
    private List<Combatiente> ordenTurnos;
    
    public Batalla() {
        this.jugadores = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.log = new RegistroAcciones();
        this.rnd = new Random();
        this.buffs = new HashMap<>();
        this.turnoActual = 0;
        this.ordenTurnos = new ArrayList<>();
    }
    
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
    
    public void iniciar() {
        // Generar enemigos aleatorios 
        int numEnemigos = rnd.nextInt(3) + 1;
        
        for (int i = 0; i < numEnemigos; i++) {
            Enemigo enemigo = generarEnemigoAleatorio();
            enemigos.add(enemigo);
        }
        
        // Crear orden de turnos
        ordenTurnos.addAll(jugadores);
        ordenTurnos.addAll(enemigos);
        
        log.push("¡La batalla ha comenzado!");
        log.push("Enemigos: " + numEnemigos);
    }
    
    private Enemigo generarEnemigoAleatorio() {
        TipoEnemigo[] tipos = TipoEnemigo.values();
        TipoEnemigo tipoAleatorio = tipos[rnd.nextInt(tipos.length)];
        boolean esJefe = rnd.nextDouble() < 0.3; // 30% chance de ser jefe
        
        switch (tipoAleatorio) {
            case BANDIDO:
                return esJefe ? new BandidoJefe() : new Bandido(false);
            case CHAMAN:
                return esJefe ? new ChamanJefe() : new Chaman(false);
            case ARQUERO:
                return esJefe ? new ArqueroJefe() : new Arquero(false);
            case LANCERO:
                return esJefe ? new LanceroJefe() : new Lancero(false);
            case CABALLERO_CORROMPIDO:
                return esJefe ? new CaballeroCorrompidoJefe() : new CaballeroCorompido(false);
            default:
                return new Bandido(false);
        }
    }
    
    public void tomarTurno(Combatiente c) {
        if (c instanceof Enemigo) {
            c.tomarTurno(this);
        }

    }
    
    public void aplicarEfectos() {
        // Aplicar efectos a todos los combatientes vivos
        for (Combatiente combatiente : new ArrayList<>(ordenTurnos)) {
            if (combatiente.estaVivo()) {
                combatiente.aplicarEfectos();
            }
        }
        
        // Remover muertos de las listas
        jugadores.removeIf(j -> !j.estaVivo());
        enemigos.removeIf(e -> !e.estaVivo());
        ordenTurnos.removeIf(c -> !c.estaVivo());
        
        // Ajustar turno actual si es necesario
        if (turnoActual >= ordenTurnos.size() && !ordenTurnos.isEmpty()) {
            turnoActual = 0;
        }
    }
    
    public boolean estaTerminada() {
        return jugadores.isEmpty() || enemigos.isEmpty();
    }
    
    public Combatiente getCombatienteActual() {
        if (!ordenTurnos.isEmpty() && turnoActual < ordenTurnos.size()) {
            return ordenTurnos.get(turnoActual);
        }
        return null;
    }
    
    public void siguienteTurno() {
        if (!ordenTurnos.isEmpty()) {
            turnoActual = (turnoActual + 1) % ordenTurnos.size();
        }
    }
    
    public Combatiente getJugadorAleatorio() {
        if (!jugadores.isEmpty()) {
            return jugadores.get(rnd.nextInt(jugadores.size()));
        }
        return null;
    }
    
    public Enemigo getEnemigoAleatorio() {
        if (!enemigos.isEmpty()) {
            return enemigos.get(rnd.nextInt(enemigos.size()));
        }
        return null;
    }
    
    public void registrarAccion(String accion) {
        log.push(accion);
    }
    
    // Getters
    public List<Jugador> getJugadores() { 
        return new ArrayList<>(jugadores); 
    }
    
    public List<Enemigo> getEnemigos() { 
        return new ArrayList<>(enemigos); 
    }
    
    public RegistroAcciones getLog() { 
        return log; 
    }
    
    public int getTurnoActual() { 
        return turnoActual; 
    }
    
    public boolean hanGanadoJugadores() {
        return enemigos.isEmpty() && !jugadores.isEmpty();
    }
    
    public boolean hanGanadoEnemigos() {
        return jugadores.isEmpty() && !enemigos.isEmpty();
    }
}