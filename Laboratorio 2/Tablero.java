import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private int filas;
    private int columnas;
    private String[][] simbolos;      
    private boolean[][] emparejados;  
    private boolean[][] visibles;     

    // Constructor
    public Tablero(int filas, int columnas, List<String> base) {
        if (filas < 4 || columnas < 4 || filas % 2 != 0 || columnas % 2 != 0) {
            throw new IllegalArgumentException("Dimensiones inválidas deben de ser pares y >= 4.");
        }
        if (base.size() != (filas * columnas) / 2) {
            throw new IllegalArgumentException("Número de símbolos no coincide con tamaño del tablero");
        }

        this.filas = filas;
        this.columnas = columnas;
        this.simbolos = new String[filas][columnas];
        this.emparejados = new boolean[filas][columnas];
        this.visibles = new boolean[filas][columnas];

        List<String> lista = new ArrayList<>();
        for (String s : base) {
            lista.add(s);
            lista.add(s);
        }

        Collections.shuffle(lista);

        int k = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                simbolos[i][j] = lista.get(k++);
                emparejados[i][j] = false;
                visibles[i][j] = false;
            }
        }
    }

 
    private boolean enRango(int r, int c) {
        return r >= 0 && r < filas && c >= 0 && c < columnas;
    }

    public boolean estaEmparejada(int r, int c) {
        if (!enRango(r, c)) return true; 
        return emparejados[r][c];
    }


    public String revelar(int r, int c) {
        if (!enRango(r, c) || emparejados[r][c]) {
            return ""; 
        }
        visibles[r][c] = true;
        return simbolos[r][c];
    }

    public void ocultar(int r, int c) {
        if (enRango(r, c) && !emparejados[r][c]) {
            visibles[r][c] = false;
        }
    }

    public boolean marcarPar(int r1, int c1, int r2, int c2) {
        if (!enRango(r1, c1) || !enRango(r2, c2)) return false;
        if (emparejados[r1][c1] || emparejados[r2][c2]) return false;
        if (!simbolos[r1][c1].equals(simbolos[r2][c2])) return false;

        emparejados[r1][c1] = true;
        emparejados[r2][c2] = true;
        return true;
    }

 
    public boolean todasEmparejadas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!emparejados[i][j]) return false;
            }
        }
        return true;
    }


    public String snapshot() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (emparejados[i][j]) {
                    sb.append("[").append(simbolos[i][j]).append("] ");
                } else if (visibles[i][j]) {
                    sb.append(" ").append(simbolos[i][j]).append("  ");
                } else {
                    sb.append(" ?  ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

