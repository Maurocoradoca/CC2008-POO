// Clase controlador hospital
// Controla la logica principal del sistema hospilatario siguendio MVC
// Conecta la vista con el modelo 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ControladorHospital {
    private VistaHospital vista;
    private HospitalManager hospital;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ControladorHospital(VistaHospital vista, HospitalManager hospital) {
        this.vista = vista;
        this.hospital = hospital;
    }

    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> contratarPersonal();
            case 2 -> buscarPorDepartamento();
            case 3 -> buscarMedicoPorId();
            case 4 -> listarTodoPersonal();
            case 5 -> registrarActividad();
            case 6 -> programarCita();
            case 7 -> buscarCitaPorId();
            case 8 -> listarCitasPorEstado();
            case 9 -> reagendarCita();
            case 10 -> actualizarEstadoCita();
            case 11 -> cancelarCita();
            case 12 -> generarReporteNomina();
            case 13 -> generarReporteCitas();
            case 14 -> generarReporteReagendamientos();
            case 15 -> calcularNominaTotal();
            case 0 -> vista.mostrarDespedida();
            default -> vista.mensajeError("Opción no válida, intente nuevamente");
        }
    }

    private void contratarPersonal() {
        vista.mostrarEncabezado("CONTRATAR NUEVO PERSONAL MÉDICO");
        int tipo = vista.menuContratarPersonal();
        if (tipo == 0) return;

        String id = vista.solicitarTexto("ID de empleado: ");
        String nombre = vista.solicitarTexto("Nombre completo: ");
        String departamento = vista.solicitarTexto("Departamento: ");
        int experiencia = vista.solicitarEntero("Años de experiencia: ");
        double salarioBase = vista.solicitarDouble("Salario base: Q");

        Medico nuevoMedico = switch (tipo) {
            case 1 -> {
                double tarifaConsulta = vista.solicitarDouble("Tarifa por consulta: Q");
                yield new DoctorGeneral(id, nombre, departamento, experiencia, salarioBase, tarifaConsulta);
            }
            case 2 -> {
                double bonoRiesgo = vista.solicitarDouble("Bono por riesgo: Q");
                Cirujano cirujano = new Cirujano(id, nombre, departamento, experiencia, salarioBase, bonoRiesgo);
                vista.mensajeInfo("Ingrese tipos de operaciones (deje vacío y presione Enter para terminar):");
                agregarElementosDinamicos("Tipo de operación", cirujano::agregarTipoOperacion);
                yield cirujano;
            }
            case 3 -> {
                String turno = vista.solicitarTexto("Turno (DIURNO/NOCTURNO): ");
                String certificacion = vista.solicitarTexto("Nivel de certificación (BASICO/INTERMEDIO/AVANZADO): ");
                yield new Enfermero(id, nombre, departamento, experiencia, salarioBase, turno, certificacion);
            }
            case 4 -> {
                double tarifaEstudio = vista.solicitarDouble("Tarifa por estudio: Q");
                Radiologo radiologo = new Radiologo(id, nombre, departamento, experiencia, salarioBase, tarifaEstudio);
                vista.mensajeInfo("Ingrese equipos certificados (deje vacío y presione Enter para terminar):");
                agregarElementosDinamicos("Equipo certificado", radiologo::agregarEquipoCertificado);
                yield radiologo;
            }
            case 5 -> {
                int limite = vista.solicitarEntero("Límite de prescripciones por día: ");
                boolean licencia = vista.solicitarBoolean("¿Tiene licencia para sustancias controladas?");
                yield new Farmaceutico(id, nombre, departamento, experiencia, salarioBase, limite, licencia);
            }
            case 6 -> {
                String tipoTerapia = vista.solicitarTexto("Tipo de terapia: ");
                int duracionSesion = vista.solicitarEntero("Duración de sesión (minutos): ");
                yield new Terapeuta(id, nombre, departamento, experiencia, salarioBase, tipoTerapia, duracionSesion);
            }
            default -> null;
        };

        if (nuevoMedico == null) return;

        if (hospital.getGestorPersonal().agregarMedico(nuevoMedico)) {
            vista.mensajeExito("Personal médico contratado exitosamente");
            vista.mostrarMensaje("\n" + nuevoMedico.toString());
        } else {
            vista.mensajeError("No se pudo contratar, El ID ya existe en el sistema");
        }
    }

    //Método genérico para listas dinámicas 
    private void agregarElementosDinamicos(String prompt, java.util.function.Consumer<String> accionAgregar) {
        List<String> elementos = new ArrayList<>();
        String entrada;
        do {
            entrada = vista.solicitarTexto(prompt + " (Enter para terminar): ");
            if (!entrada.isEmpty()) {
                accionAgregar.accept(entrada);
                elementos.add(entrada);
            }
        } while (!entrada.isEmpty());
    }

    private void buscarPorDepartamento() {
        vista.mostrarEncabezado("BUSCAR PERSONAL POR DEPARTAMENTO");
        String departamento = vista.solicitarTexto("Ingrese el nombre del departamento: ");
        List<Medico> personal = hospital.getGestorPersonal().buscarPorDepartamento(departamento);

        if (personal.isEmpty()) {
            vista.mensajeInfo("No se encontró personal en el departamento: " + departamento);
        } else {
            vista.mostrarMensaje("\nPersonal encontrado en " + departamento + ":");
            for (Medico medico : personal) {
                vista.mostrarMensaje(medico.toString());
            }
            vista.mostrarMensaje("\nTotal: " + personal.size() + " trabajadores");
        }
    }

    private void buscarMedicoPorId() {
        vista.mostrarEncabezado("BUSCAR MÉDICO POR ID");
        String id = vista.solicitarTexto("Ingrese el ID del empleado: ");
        Medico medico = hospital.getGestorPersonal().buscarPorId(id);

        if (medico == null) {
            vista.mensajeError("No se encontró ningún médico con el ID: " + id);
        } else {
            vista.mostrarMensaje(medico.toString());
            vista.mostrarMensaje(String.format("\nSalario calculado: Q%.2f", medico.calcularSalario()));
        }
    }

    private void listarTodoPersonal() {
        vista.mostrarEncabezado("LISTADO COMPLETO DE PERSONAL MÉDICO");
        List<Medico> personal = hospital.getGestorPersonal().getPersonal();

        if (personal.isEmpty()) {
            vista.mensajeInfo("No hay personal registrado en el sistema");
            return;
        }

        for (Medico medico : personal) {
            vista.mostrarMensaje(medico.toString());
        }
        vista.mostrarMensaje(String.format("\nTotal de trabajadores: %d", personal.size()));
    }

    private void registrarActividad() {
        vista.mostrarEncabezado("REGISTRAR ACTIVIDAD MÉDICA");
        String id = vista.solicitarTexto("ID del médico: ");
        Medico medico = hospital.getGestorPersonal().buscarPorId(id);

        if (medico == null) {
            vista.mensajeError("No se encontró el médico con ID: " + id);
            return;
        }

        if (medico instanceof DoctorGeneral d) {
            int c = vista.solicitarEntero("Cantidad de consultas: ");
            for (int i = 0; i < c; i++) d.registrarConsulta();
        } else if (medico instanceof Cirujano c) {
            int h = vista.solicitarEntero("Horas de cirugía: ");
            c.registrarHorasCirugia(h);
        } else if (medico instanceof Radiologo r) {
            int e = vista.solicitarEntero("Cantidad de estudios: ");
            for (int i = 0; i < e; i++) r.registrarEstudio();
        } else if (medico instanceof Farmaceutico f) {
            int p = vista.solicitarEntero("Cantidad de prescripciones: ");
            for (int i = 0; i < p; i++) {
                if (!f.registrarPrescripcion()) {
                    vista.mensajeError("Límite de prescripciones alcanzado");
                    break;
                }
            }
        } else if (medico instanceof Terapeuta t) {
            int s = vista.solicitarEntero("Cantidad de sesiones: ");
            for (int i = 0; i < s; i++) t.registrarSesion();
        } else {
            vista.mensajeError("Este tipo de médico no tiene actividades registrables");
            return;
        }

        vista.mensajeExito("Actividad registrada correctamente");
        vista.mostrarMensaje("Salario actualizado: Q" + String.format("%.2f", medico.calcularSalario()));
    }

    private void programarCita() {
        vista.mostrarEncabezado("PROGRAMAR NUEVA CITA MÉDICA");
        String idCita = vista.solicitarTexto("ID de la cita: ");
        String paciente = vista.solicitarTexto("Nombre del paciente: ");
        String idMedico = vista.solicitarTexto("ID del médico: ");
        Medico medico = hospital.getGestorPersonal().buscarPorId(idMedico);

        if (medico == null) {
            vista.mensajeError("No se encontró el médico con ID: " + idMedico);
            return;
        }

        try {
            LocalDateTime fecha = LocalDateTime.parse(
                vista.solicitarTexto("Fecha y hora (dd/MM/yyyy HH:mm): "), FORMATO_FECHA);
            String tipoCita = vista.solicitarTexto("Tipo de cita (CONSULTA GENERAL/CIRUGIA/TERAPIA/DIAGNOSTICO): ");
            CitaMedica cita = new CitaMedica(idCita, paciente, medico, fecha, tipoCita);

            if (hospital.getGestorCitas().agregarCita(cita)) {
                vista.mensajeExito("Cita programada exitosamente");
                vista.mostrarMensaje(cita.toString());
            } else {
                vista.mensajeError("No se pudo programar la cita, Existe un conflicto de horario");
            }
        } catch (DateTimeParseException e) {
            vista.mensajeError("Formato de fecha inválido, Usa: dd/MM/yyyy HH:mm");
        }
    }

    private void buscarCitaPorId() {
        vista.mostrarEncabezado("BUSCAR CITA POR ID");
        String id = vista.solicitarTexto("Ingrese el ID de la cita: ");
        CitaMedica cita = hospital.getGestorCitas().buscarCita(id);

        if (cita == null) {
            vista.mensajeError("No se encontró ninguna cita con ese ID");
            return;
        }

        vista.mostrarMensaje(cita.toString());
        if (!cita.getHistorial().isEmpty()) {
            vista.mostrarMensaje("\nHistorial de reagendamientos:" + cita.obtenerHistorialFormateado());
        }
    }

    private void listarCitasPorEstado() {
        vista.mostrarEncabezado("LISTAR CITAS POR ESTADO");
        String estado = vista.solicitarTexto("Ingrese el estado de las citas: ");
        List<CitaMedica> citas = hospital.getGestorCitas().obtenerCitasPorEstado(estado);

        if (citas.isEmpty()) {
            vista.mensajeInfo("No hay citas con el estado: " + estado);
        } else {
            for (CitaMedica cita : citas) vista.mostrarMensaje(cita.toString());
            vista.mostrarMensaje("\nTotal: " + citas.size() + " citas");
        }
    }

    private void reagendarCita() {
        vista.mostrarEncabezado("REAGENDAR CITA MÉDICA");
        String id = vista.solicitarTexto("ID de la cita: ");
        CitaMedica cita = hospital.getGestorCitas().buscarCita(id);

        if (cita == null) {
            vista.mensajeError("No se encontró la cita con ese ID");
            return;
        }

        try {
            LocalDateTime nuevaFecha = LocalDateTime.parse(
                vista.solicitarTexto("Nueva fecha y hora (dd/MM/yyyy HH:mm): "), FORMATO_FECHA);
            String motivo = vista.solicitarTexto("Motivo del reagendamiento: ");
            cita.reagendar(nuevaFecha, motivo);
            vista.mensajeExito("Cita reagendada exitosamente");
            vista.mostrarMensaje(cita.toString());
        } catch (DateTimeParseException e) {
            vista.mensajeError("Formato de fecha inválido");
        }
    }

    private void actualizarEstadoCita() {
        vista.mostrarEncabezado("ACTUALIZAR ESTADO DE CITA");
        String id = vista.solicitarTexto("ID de la cita: ");
        CitaMedica cita = hospital.getGestorCitas().buscarCita(id);

        if (cita == null) {
            vista.mensajeError("No se encontró la cita con ese ID");
            return;
        }

        String nuevo = vista.solicitarTexto("Nuevo estado (PROGRAMADA/CONFIRMADA/EN PROGRESO/COMPLETADA/CANCELADA): ");
        cita.actualizarEstado(nuevo);
        vista.mensajeExito("Estado actualizado correctamente.");
    }

    private void cancelarCita() {
        vista.mostrarEncabezado("CANCELAR CITA MÉDICA");
        String id = vista.solicitarTexto("ID de la cita a cancelar: ");

        if (hospital.getGestorCitas().cancelarCita(id)) {
            vista.mensajeExito("Cita cancelada exitosamente");
        } else {
            vista.mensajeError("No se encontró la cita con ese ID");
        }
    }

    private void generarReporteNomina() {
        vista.mostrarMensaje(hospital.generarReporteNomina());
    }

    private void generarReporteCitas() {
        vista.mostrarMensaje(hospital.generarReporteCitas());
    }

    private void generarReporteReagendamientos() {
        vista.mostrarMensaje(hospital.generarReporteReagendamientos());
    }

    private void calcularNominaTotal() {
        vista.mostrarEncabezado("CÁLCULO DE NÓMINA TOTAL");
        double total = hospital.getGestorPersonal().calcularNomina();
        int empleados = hospital.getGestorPersonal().getTotalPersonal();
        vista.mostrarMensaje(String.format("Total empleados: %d", empleados));
        vista.mostrarMensaje(String.format("Nómina total: Q%.2f", total));
        if (empleados > 0)
            vista.mostrarMensaje(String.format("Promedio salarial: Q%.2f", total / empleados));
    }
}
