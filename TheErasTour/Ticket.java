package TheErasTour;

import java.util.Random;

public class Ticket {
    private final int numero;

    public Ticket() {
        Random objeto = new Random();
        this.numero = objeto.nextInt(15000) + 1;
    }

    public Ticket(int numero) {
        this.numero = numero;
    }

    public boolean esValido(int a, int b) {
        int limiteinferior = Math.min(a, b);
        int limitesuperior = Math.max(a, b);

        return numero >= limiteinferior && numero <= limitesuperior;
    }

    public int getNumero() {
        return numero;
    }
}
