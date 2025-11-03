import java.util.ArrayList;

//Clase Controlador
//Orquesta la lógica entre el Modelo (Catalogo) y la Vista
//Patrón MVC
public class Controlador {
    private Catalogo catalogo;
    private Vista vista;
    
    //Constructor del Controlador
    //Inicializa el catálogo y la vista
    public Controlador() {
        this.catalogo = new Catalogo();
        this.vista = new Vista();
    }
    
    //Inicializa el sistema cargando los equipos iniciales
    public void inicializarSistema() {
        crearEquiposIniciales();
        vista.mostrarMensaje("Sistema inicializado con " + catalogo.obtenerCantidadEquipos() + " equipos.");
    }
    
    //Ejecuta el loop principal del sistema
    //Maneja el menú y las opciones del usuario
    public void ejecutar() {
        int opcion;
        
        do {
            vista.mostrarMenuPrincipal();
            opcion = vista.solicitarOpcion();
            
            switch (opcion) {
                case 1:
                    listarTodosEquipos();
                    break;
                case 2:
                    buscarEquipoPorId();
                    break;
                case 3:
                    buscarEquipoPorNombre();
                    break;
                case 4:
                    ordenarPorConsumoElectrico();
                    break;
                case 5:
                    vista.mostrarDespedida();
                    break;
                default:
                    vista.mostrarError("Opción inválida, Por favor seleccione una opción del 1 al 5.");
            }
            
            if (opcion != 5) {
                vista.pausar();
            }
            
        } while (opcion != 5);
    }
    
    //Lista todos los equipos del catálogo
    private void listarTodosEquipos() {
        ArrayList<Equipo> equipos = catalogo.listarTodos();
        vista.mostrarListaEquipos(equipos);
    }
    
    //Busca un equipo por su ID y muestra su información
    private void buscarEquipoPorId() {
        String id = vista.solicitarId();
        
        if (id.isEmpty()) {
            vista.mostrarError("El ID no puede estar vacío.");
            return;
        }
        
        Equipo equipo = catalogo.buscarPorId(id);
        
        if (equipo != null) {
            vista.mostrarDetalleEquipo(equipo);
        } else {
            vista.mostrarError("No se encontró ningún equipo con el ID: " + id);
        }
    }
    
    //Busca un equipo por su nombre y muestra su información
    private void buscarEquipoPorNombre() {
        String nombre = vista.solicitarNombre();
        
        if (nombre.isEmpty()) {
            vista.mostrarError("El nombre no puede estar vacío.");
            return;
        }
        
        Equipo equipo = catalogo.buscarPorNombre(nombre);
        
        if (equipo != null) {
            vista.mostrarDetalleEquipo(equipo);
        } else {
            vista.mostrarError("No se encontró ningún equipo con el nombre: " + nombre);
        }
    }
    
    //Ordena los equipos por consumo eléctrico y muestra el resultado
    private void ordenarPorConsumoElectrico() {
        catalogo.ordenarPorConsumo();
        vista.mostrarMensaje("Equipos ordenados por consumo eléctrico (de menor a mayor):");
        listarTodosEquipos();
    }
    
    //Crea la carga inicial de 10 equipos diversos
    //Método privado que inicializa el catálogo con equipos predefinidos
    private void crearEquiposIniciales() {
        // Sensor 1: Sensor de Suelo
        SensorSuelo sensor1 = new SensorSuelo(
            "SS001", "Sensor Suelo Premium", "AgriTech", "ST-500",
            45.0, 0.5, "Arcilloso"
        );
        catalogo.agregarEquipo(sensor1);
        
        // Sensor 2: Sensor de Humedad
        SensorHumedad sensor2 = new SensorHumedad(
            "SH001", "HumiSense Pro", "EnviroSys", "HS-200",
            30.0, "0-100%", 1.5
        );
        catalogo.agregarEquipo(sensor2);
        
        // Sensor 3: Controlador de pH
        ControladorPH sensor3 = new ControladorPH(
            "CPH001", "pH Controller Max", "AquaTech", "PHC-300",
            65.0, "4.0-9.0", 2.5
        );
        catalogo.agregarEquipo(sensor3);
        
        // Estación 1: Estación Meteorológica
        EstacionMeteorologica estacion1 = new EstacionMeteorologica(
            "EM001", "MeteoStation Advanced", "WeatherPro", "WP-1000",
            120.0, 10.0, 5.0
        );
        catalogo.agregarEquipo(estacion1);
        
        // Estación 2: Estación Pluviométrica
        EstacionPluviometrica estacion2 = new EstacionPluviometrica(
            "EP001", "RainGauge Digital", "HydroMetrics", "RG-250",
            55.0, 200.0, 0.1
        );
        catalogo.agregarEquipo(estacion2);
        
        // Dron 1: Dron de Riego
        DronRiego dron1 = new DronRiego(
            "DR001", "AgroFlyer Irrigator", "SkyFarm", "AF-I500",
            450.0, 50.0, 45
        );
        catalogo.agregarEquipo(dron1);
        
        // Dron 2: Dron de Monitoreo
        DronMonitoreo dron2 = new DronMonitoreo(
            "DM001", "CropScanner Pro", "AeroAgri", "CS-P800",
            380.0, "4K Ultra HD", 120.0
        );
        catalogo.agregarEquipo(dron2);
        
        // Actuador 1: Válvula Inteligente
        Valvulainteligente valvula1 = new Valvulainteligente(
            "VI001", "SmartValve Industrial", "FlowControl", "SV-I100",
            75.0, 10.0, 2.5
        );
        catalogo.agregarEquipo(valvula1);
        
        // Actuador 2: Sistema de Riego
        SistemaRiego riego1 = new SistemaRiego(
            "SR001", "IrriSystem Master", "WaterTech", "IS-M2000",
            250.0, 5000.0, 4.5
        );
        catalogo.agregarEquipo(riego1);
        
        // Actuador 3: Cámara Multiespectral
        CamaraMultiespectral camara1 = new CamaraMultiespectral(
            "CM001", "MultiSpec Vision", "OptiCrop", "MSV-600",
            95.0, 6, "400-1000nm"
        );
        catalogo.agregarEquipo(camara1);
    }
}