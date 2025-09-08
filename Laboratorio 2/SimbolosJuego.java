import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimbolosJuego {
    private final List<String> catalogo;
    private final Random random;

    public SimbolosJuego() {
        this.random = new Random();
        this.catalogo = new ArrayList<>();
        Collections.addAll(catalogo,
                "🐶","🐱","🐭","🐹","🐰","🦊","🐻","🐼",
                "🐨","🐯","🦁","🐮","🐷","🐸","🐵","🐤",
                "🍎","🍌","🍒","🍇","🍉","🍍","🥝","🥕"
        );
    }

    public List<String> pick(int k) {
        if (k <= 0 || k > catalogo.size()) {
            return new ArrayList<>(); 
        }
        List<String> copia = new ArrayList<>(catalogo);
        Collections.shuffle(copia, random);
        return new ArrayList<>(copia.subList(0, k));
    }

    public boolean tieneSuficiente(int k) {
        return k > 0 && k <= catalogo.size();
    }

    public int cantidad() {
        return catalogo.size();
    }
}

