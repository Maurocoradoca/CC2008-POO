import java.util.List;
import java.util.ArrayList;

public class RegistroAcciones {
    private String[] cola;
    private int idx;
    
    public RegistroAcciones() {
        this.cola = new String[3];
        this.idx = 0;
        for (int i = 0; i < 3; i++) {
            cola[i] = "";
        }
    }
    
    public void push(String msg) {
        cola[idx] = msg;
        idx = (idx + 1) % 3;
    }
    
    public List<String> ultimas() {
        List<String> ultimas = new ArrayList<>();
        
        // Empezar desde la posición más antigua
        for (int i = 0; i < 3; i++) {
            int pos = (idx + i) % 3;
            if (cola[pos] != null && !cola[pos].isEmpty()) {
                ultimas.add(cola[pos]);
            }
        }
        
        return ultimas;
    }
}