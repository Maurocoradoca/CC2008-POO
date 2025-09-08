public class Movimiento {
    
    private int r1, c1, r2, c2;

    public Movimiento(int r1, int c1, int r2, int c2) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }

    // Regla básica
    public boolean esDistintoPar() {
        return !(r1 == r2 && c1 == c2);
    }

    // Getters
    public int getR1() { return r1; }
    public int getC1() { return c1; }
    public int getR2() { return r2; }
    public int getC2() { return c2; }

    @Override
    public String toString() {
        return "(" + r1 + "," + c1 + ") y (" + r2 + "," + c2 + ")";
    }
}
