import java.util.List;
import java.util.ArrayList;

public class Inventario {
    private List<Item> items;
    
    public Inventario(Combatiente dueño) {
        this.items = new ArrayList<>();
    }
    
    public void agregar(Item item) {
        items.add(item);
    }
    
    public void removerSiAgotado() {
        items.removeIf(item -> !item.puedeUsarse());
    }
    
    public List<Item> listar() {
        return new ArrayList<>(items);
    }
    
    public Item getItem(int indice) {
        if (indice >= 0 && indice < items.size()) {
            return items.get(indice);
        }
        return null;
    }
    
    public int size() {
        return items.size();
    }
    
    public boolean tieneItemsUsables() {
        return items.stream().anyMatch(Item::puedeUsarse);
    }
}
