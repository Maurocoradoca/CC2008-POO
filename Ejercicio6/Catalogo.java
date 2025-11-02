import java.util.ArrayList;
import java.util.Collections;

//Clase Catalogo
//Gestiona la lista polimórfica única de todos los equipos del sistema

public class Catalogo {
    // Lista polimórfica única que almacena todos los equipos
    private ArrayList<Equipo> equipos;
    
    //Constructor del Catalogo
    //Inicializa la lista de equipos vacía
    public Catalogo() {
        this.equipos = new ArrayList<>();
    }
    
    //Agrega un equipo al catálogo
    public void agregarEquipo(Equipo equipo) {
        if (equipo != null) {
            equipos.add(equipo);
        }
    }
    
    //Busca un equipo por su ID
    public Equipo buscarPorId(String id) {
        for (Equipo equipo : equipos) {
            if (equipo.getId().equalsIgnoreCase(id)) {
                return equipo;
            }
        }
        return null;
    }
    
    //Busca un equipo por su nombre
    public Equipo buscarPorNombre(String nombre) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return equipo;
            }
        }
        return null;
    }
    
    //Retorna la lista completa de equipos
    public ArrayList<Equipo> listarTodos() {
        return equipos;
    }
    
    //Ordena el catálogo por consumo eléctrico de menor a mayor
    //Utiliza el método compareTo implementado en la clase Equipo
    public void ordenarPorConsumo() {
        Collections.sort(equipos);
    }
    
    //Obtiene la cantidad total de equipos en el catálogo
    public int obtenerCantidadEquipos() {
        return equipos.size();
    }
}