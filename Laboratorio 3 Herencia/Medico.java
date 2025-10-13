public abstract class Medico {
    // Atributos protegidos para acceso desde clases hijas
    protected String idEmpleado;
    protected String nombre;
    protected String departamento;
    protected int Experiencia;
    protected double salarioBase;

    public Medico(String idEmpleado, String nombre, String departamento, 
                  int Experiencia, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.departamento = departamento;
        this.Experiencia = Experiencia;
        this.salarioBase = salarioBase;
    }

    // Método abstracto para calcular el salario, implementado en subclases
    public abstract double calcularSalario();

    // Getters y Setters
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getAniosExperiencia() {
        return Experiencia;
    }

    public void setAniosExperiencia(int Experiencia) {
        this.Experiencia = Experiencia;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    // Representación en String del médico
    @Override
    public String toString() {
        return String.format("ID: %s | %s | Depto: %s | Exp: %d años | Salario Base: Q%.2f",
                idEmpleado, nombre, departamento, Experiencia, salarioBase);
    }
    // Igualdad basada en el ID del empleado
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medico medico = (Medico) obj;
        return idEmpleado.equals(medico.idEmpleado);
    }

    @Override
    public int hashCode() {
        return idEmpleado.hashCode();
    }
}