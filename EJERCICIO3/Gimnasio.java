package EJERCICIO3;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
    private final List<Miembro> miembros = new ArrayList<>();

    public boolean agregarMiembro(Miembro m){
        if (m == null) return false;
        for (Miembro x : miembros) if (x.getid().equals(m.getid())) return false;
        return miembros.add(m);
    }

    public String listarMiembros(){
        if (miembros.isEmpty()) return "No hay miembros registrados";
        StringBuilder sb = new StringBuilder("Miembros del gimnasio:\n");
        for (Miembro m : miembros) sb.append(m).append("\n");
        return sb.toString();
    }


    
}
