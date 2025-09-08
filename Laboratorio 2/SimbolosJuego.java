import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimbolosJuego {
    private final List<String> catalogo;
    private final Random random;

    public SimbolosJuego() {
            this.random = new Random();
            catalogo = new ArrayList<>();
            Collections.addAll(catalogo,
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                "@", "#", "$", "%", "&", "*", "+", "-", "=", "?", "!", "^",
                "☺", "☻", "♥", "♦", "♣", "♠", "★", "☆", "☼", "☽", "☾", "♛", "♚"
            );// no me funcionaron correctamnte los anteriores simbolos por eso los cambie
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

