/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package control;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import dto.UsuarioDTO;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import persistencia.entidades.NivelIncidenciaPersistencia;

/**
 *
 * @author olive
 */
public class GestionarIncidenciasTestIntegracion {

    private GestionarIncidencias gestionarIncidencias;

    @BeforeEach
    public void setUp() {
        gestionarIncidencias = new GestionarIncidencias();
    }

    /**
     * Prueba la creación de un reporte para un alumno.
     *
     * @throws Exception si ocurre un error al crear o recuperar reportes.
     */
    @Test
    public void testCrearReporte() throws Exception {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO ("IUVO040706HSLNLLA2", "Oliver", "Inzunza", "Valle", "3B", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_oliver.jpeg") ;
        UsuarioDTO docente = new UsuarioDTO("GALJ940519HDFLRN05", "Juan", "García", "López", "DOCENTE", "1234") ;
        ReporteDTO reporte = new ReporteDTO(alumno, docente, 
                NivelIncidenciaPersistencia.GRAVE, "Se encontró al alumno fumando un tabaco en la entrada de los baños de hombres", 
                "Fumar Tabaco dentro de la Escuela", new Date(), false, false);
        
        // Act
        gestionarIncidencias.crearReporte(reporte);

        // Assert
        List<ReporteDTO> reportes = gestionarIncidencias.recuperarReportesAlumno(alumno.getCurp());
        boolean reporteEncontrado = reportes.stream()
                                        .anyMatch(r -> r.getMotivo().equals(reporte.getMotivo()));

        assertTrue(reporteEncontrado, "El reporte debería estar presente en la lista de reportes con el mismo motivo.");
    }

    /**
     * Prueba la validación de un reporte previamente creado.
     *
     * @throws Exception si ocurre un error al crear o validar el reporte.
     */
    @Test
    public void testValidarReporte() throws Exception {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO ("IUVO040706HSLNLLA2", "Oliver", "Inzunza", "Valle", "3B", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_oliver.jpeg") ;
        UsuarioDTO docente = new UsuarioDTO("GALJ940519HDFLRN05", "Juan", "García", "López", "DOCENTE", "1234") ;
        ReporteDTO reporte = new ReporteDTO(alumno, docente, 
                NivelIncidenciaPersistencia.GRAVE, "Se encontró al alumno fumando un tabaco en la entrada de los baños de hombres", 
                "Fumar Tabaco dentro de la Escuela", new Date(), false, false);
        gestionarIncidencias.crearReporte(reporte);
        
        List<ReporteDTO> reportes = gestionarIncidencias.recuperarReportesAlumno(alumno.getCurp());

        // Act
        ReporteDTO reporteEncontrado = reportes.stream()
                                           .filter(r -> r.getMotivo().equals(reporte.getMotivo()) && r.isValidado() == false)
                                           .findFirst()
                                           .orElse(null);
        
        ReporteDTO resultado = gestionarIncidencias.validarReporte(reporteEncontrado);

        // Assert
        assertNotNull(resultado, "El reporte validado no debería ser nulo.");
        assertTrue(resultado.isValidado(), "El reporte debería estar marcado como validado.");
    }

    /**
     * Prueba la notificación de un reporte para un alumno.
     *
     * @throws Exception si ocurre un error al crear o notificar el reporte.
     */
    @Test
    public void testNotificarReporte() throws Exception {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO ("IUVO040706HSLNLLA2", "Oliver", "Inzunza", "Valle", "3B", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_oliver.jpeg") ;
        UsuarioDTO docente = new UsuarioDTO("GALJ940519HDFLRN05", "Juan", "García", "López", "DOCENTE", "1234") ;
        ReporteDTO reporte = new ReporteDTO(alumno, docente, 
                NivelIncidenciaPersistencia.GRAVE, "Se encontró al alumno fumando un tabaco en la entrada de los baños de hombres", 
                "Fumar Tabaco dentro de la Escuela", new Date(), false, false);
        gestionarIncidencias.crearReporte(reporte);
        
        List<ReporteDTO> reportes = gestionarIncidencias.recuperarReportesAlumno(alumno.getCurp());
        
        ReporteDTO reporteEncontrado = reportes.stream()
                                           .filter(r -> r.getMotivo().equals(reporte.getMotivo()) && r.isNotificado() == false)
                                           .findFirst()
                                           .orElse(null);

        // Act
        boolean notificado = gestionarIncidencias.notificarReporte(reporteEncontrado);

        // Assert
        assertTrue(notificado, "La notificación del reporte debería ser exitosa.");
        assertTrue(reporte.isNotificado(), "El reporte debería estar marcado como notificado.");
    }

    /**
     * Prueba la recuperación de todos los reportes registrados.
     *
     * @throws Exception si ocurre un error al recuperar los reportes.
     */
    @Test
    public void testRecuperarReportes() throws Exception {
        // Arrange
        //No requiere configuracion adicional

        // Act
        List<ReporteDTO> reportes = gestionarIncidencias.recuperarReportes();

        // Assert
        assertNotNull(reportes, "La lista de reportes no debería ser nula.");
        assertFalse(reportes.isEmpty(), "La lista de reportes no debería estar vacía.");
    }

    /**
     * Prueba la recuperación de alumnos por grado.
     *
     * @throws Exception si ocurre un error al recuperar los alumnos por grado.
     */
    @Test
    public void testRecuperarAlumnosPorGrado() throws Exception {
        // Arrange
        String grado = "1"; // Configura un grado válido
        gestionarIncidencias.insertDatosSimulados();

        // Act
        List<AlumnoDTO> alumnos = gestionarIncidencias.recuperarAlumnosPorGrado(grado);

        // Assert
        assertNotNull(alumnos, "La lista de alumnos no debería ser nula.");
        assertFalse(alumnos.isEmpty(), "La lista de alumnos no debería estar vacía.");
    }

    /**
     * Prueba la recuperación de alumnos por grupo.
     *
     * @throws Exception si ocurre un error al recuperar los alumnos por grupo.
     */
    @Test
    public void testRecuperarAlumnosPorGrupo() throws Exception {
        // Arrange
        String grupo = "A"; // Configura un grupo válido
        gestionarIncidencias.insertDatosSimulados();

        // Act
        List<AlumnoDTO> alumnos = gestionarIncidencias.recuperarAlumnosPorGrupo(grupo);

        // Assert
        assertNotNull(alumnos, "La lista de alumnos no debería ser nula.");
        assertFalse(alumnos.isEmpty(), "La lista de alumnos no debería estar vacía.");
    }

    /**
     * Prueba la recuperación de alumnos por grado y grupo específicos.
     *
     * @throws Exception si ocurre un error al recuperar los alumnos por grado y grupo.
     */
    @Test
    public void testRecuperarAlumnosPorGradoYGrupo() throws Exception {
        // Arrange
        String grado = "2";
        String grupo = "A";
        gestionarIncidencias.insertDatosSimulados();

        // Act
        List<AlumnoDTO> alumnos = gestionarIncidencias.recuperarAlumnosPorGradoYGrupo(grado, grupo);

        // Assert
        assertNotNull(alumnos, "La lista de alumnos no debería ser nula.");
        assertFalse(alumnos.isEmpty(), "La lista de alumnos no debería estar vacía.");
    }

    /**
     * Prueba la recuperación de reportes de un alumno específico.
     *
     * @throws Exception si ocurre un error al recuperar los reportes del alumno.
     */
    @Test
    public void testRecuperarReportesAlumno() throws Exception {
        // Arrange
        String curp = "IUVO040706HSLNLLA2";

        // Act
        List<ReporteDTO> reportes = gestionarIncidencias.recuperarReportesAlumno(curp);

        // Assert
        assertNotNull(reportes, "La lista de reportes no debería ser nula.");
        assertFalse(reportes.isEmpty(), "La lista de reportes no debería estar vacía.");
    }

}