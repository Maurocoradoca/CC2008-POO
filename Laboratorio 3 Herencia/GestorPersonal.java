// Clase gestor personal 
// Administra el conjunto de medicos registrados en el hospital 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorPersonal {
    private List<Medico> personal;

    public GestorPersonal() {
        this.personal = new ArrayList<>();
    }

    public boolean agregarMedico(Medico medico) {
        // Verificar que no exista un médico con el mismo ID
        for (Medico m : personal) {
            if (m.getIdEmpleado().equals(medico.getIdEmpleado())) {
                return false; // Ya existe
            }
        }
        
        personal.add(medico);
        return true;
    }

    public Medico buscarPorId(String idEmpleado) {
        for (Medico medico : personal) {
            if (medico.getIdEmpleado().equals(idEmpleado)) {
                return medico;
            }
        }
        return null;
    }

    public List<Medico> buscarPorDepartamento(String departamento) {
        List<Medico> resultado = new ArrayList<>();
        for (Medico medico : personal) {
            if (medico.getDepartamento().equalsIgnoreCase(departamento)) {
                resultado.add(medico);
            }
        }
        return resultado;
    }

    public double calcularNomina() {
        double nominaTotal = 0;
        for (Medico medico : personal) {
            nominaTotal += medico.calcularSalario(); // Polimorfismo 
        }
        return nominaTotal;
    }

    public double calcularNominaPorDepartamento(String departamento) {
        double nominaDepartamento = 0;
        for (Medico medico : buscarPorDepartamento(departamento)) {
            nominaDepartamento += medico.calcularSalario();
        }
        return nominaDepartamento;
    }

    public List<String> obtenerDepartamentos() {
        List<String> departamentos = new ArrayList<>();
        for (Medico medico : personal) {
            String depto = medico.getDepartamento();
            if (!departamentos.contains(depto)) {
                departamentos.add(depto);
            }
        }
        return departamentos;
    }

    public Map<String, Integer> obtenerEstadisticasPorTipo() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        for (Medico medico : personal) {
            String tipo = medico.getClass().getSimpleName();
            estadisticas.put(tipo, estadisticas.getOrDefault(tipo, 0) + 1);
        }
        
        return estadisticas;
    }

    public List<Medico> buscarPorEspecializacion(Class<? extends Medico> tipoClase) {
        List<Medico> resultado = new ArrayList<>();
        for (Medico medico : personal) {
            if (tipoClase.isInstance(medico)) {
                resultado.add(medico);
            }
        }
        return resultado;
    }

    public boolean eliminarMedico(String idEmpleado) {
        Medico medico = buscarPorId(idEmpleado);
        if (medico != null) {
            personal.remove(medico);
            return true;
        }
        return false;
    }

    public Medico obtenerMayorSalario() {
        if (personal.isEmpty()) {
            return null;
        }
        
        Medico mayor = personal.get(0);
        for (Medico medico : personal) {
            if (medico.calcularSalario() > mayor.calcularSalario()) {
                mayor = medico;
            }
        }
        return mayor;
    }

    // Getter
    public List<Medico> getPersonal() {
        return new ArrayList<>(personal); // Retorna copia para proteger encapsulación
    }

    public int getTotalPersonal() {
        return personal.size();
    }
}