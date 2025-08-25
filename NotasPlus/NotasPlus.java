package NotasPlus;

public class NotasPlus {
    private Estudiante[] estudiantes;
    private Curso[] cursos;
    private double[][] calificaciones; 

    public NotasPlus() {
       
        this.estudiantes = new Estudiante[0];
        this.cursos = new Curso[0];
        this.calificaciones = new double[0][0];
    }

    public void iniciar() {
     
        this.estudiantes = new Estudiante[]{
            new Estudiante("Mauricio Corado", "25218", "Ingeniería"),
            new Estudiante("Andres Casttro", "25039", "Ingenieria")
        };
        
        this.cursos = new Curso[]{
            new Curso("Programación orientada a objetos", "123"),
            new Curso("Calculo I", "321")
        };
        
        
        this.calificaciones = new double[estudiantes.length][cursos.length];
        
        calificaciones[0][0] = 8.5;
        calificaciones[0][1] = 7.0; 
        calificaciones[1][0] = 9.0; 
        calificaciones[1][1] = 8.0; 
    }

    public double calcularPromedioGeneral() {
        if (calificaciones.length == 0 || calificaciones[0].length == 0) {
            return 0;
        }
        
        double suma = 0;
        int contador = 0;
        
        for (int i = 0; i < calificaciones.length; i++) {
            for (int j = 0; j < calificaciones[i].length; j++) {
                suma += calificaciones[i][j];
                contador++;
            }
        }
        
        return suma / contador;
    }

    public double calcularPromedioEstudiante(int indiceEstudiante) {
        if (indiceEstudiante < 0 || indiceEstudiante >= calificaciones.length) {
            return 0;
        }
        
        double suma = 0;
        int contador = 0;
        
        for (int j = 0; j < calificaciones[indiceEstudiante].length; j++) {
            suma += calificaciones[indiceEstudiante][j];
            contador++;
        }
        
        return contador == 0 ? 0 : suma / contador;
    }

    public double calcularPromedioCurso(int indiceCurso) {
        if (calificaciones.length == 0 || indiceCurso < 0 || indiceCurso >= calificaciones[0].length) {
            return 0;
        }
        
        double suma = 0;
        int contador = 0;
        
        for (int i = 0; i < calificaciones.length; i++) {
            suma += calificaciones[i][indiceCurso];
            contador++;
        }
        
        return suma / contador;
    }
}