package TheErasTour;

public class Usuario {

    private String nombre, email;
    private int cantidad;
    private double presupuestoMaximo;
    
    public Usuario(String nombre, String email, int cantidad, double presupuestoMaximo) {
            this.nombre = nombre;
            this.email = email;
            this.cantidad = cantidad;
            this.presupuestoMaximo = presupuestoMaximo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public double getpresupuestoMaximo(){
        return presupuestoMaximo;
    }

    public void nuevopresupuesto(double nuevo){
        this.presupuestoMaximo = nuevo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
}   
