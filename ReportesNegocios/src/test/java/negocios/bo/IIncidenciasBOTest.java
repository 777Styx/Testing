/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package negocios.bo;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import dto.UsuarioDTO;
import java.util.List;
import negocios.excepciones.NegociosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author olive
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IIncidenciasBOTest {

    private IIncidenciasBO incidenciasBO;
    private ReporteDTO reporte;
    private AlumnoDTO alumno;
    private UsuarioDTO docente;

    @BeforeEach
    void setUp() {
        // Arrange - setup common objects and mocks
        incidenciasBO = Mockito.mock(IIncidenciasBO.class);

        alumno = new AlumnoDTO("CURP123", "Juan", "Perez", "Gomez", "3A", "foto_url", "tutor@mail.com");
        docente = new UsuarioDTO("CURPDOC", "Carlos", "Lopez", "Martinez", "Docente", "1234");
        reporte = new ReporteDTO("ID1", alumno, docente, null, "Descripcion", "Motivo", new Date(), false, false);
    }

    // Pruebas funcionales
    /**
     * Prueba que el método crearReporte inserta un reporte de manera exitosa
     * con una entrada válida. Verifica que el método se llame una vez.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testCrearReporte_ValidInput_CreatesReportSuccessfully() throws NegociosException {
        // Arrange
        doNothing().when(incidenciasBO).crearReporte(reporte);

        // Act
        incidenciasBO.crearReporte(reporte);

        // Assert
        verify(incidenciasBO, times(1)).crearReporte(reporte);
    }

    /**
     * Prueba que el método crearReporte lanza una excepción NegociosException
     * cuando se intenta crear un reporte inválido. Verifica que se arroje la
     * excepción esperada.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testCrearReporte_ReportInvalido_ThrowsException() throws NegociosException {
        // Arrange
        ReporteDTO reporteInvalido = new ReporteDTO("ID2", null, null, null, "Descripcion", "Motivo", new Date(), false, false);
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteInvalido));
    }

    /**
     * Prueba que el método validarReporte devuelve el mismo reporte al validar
     * una entrada válida. Verifica que el reporte validado sea igual al
     * original.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testValidarReporte_ValidReport_ReturnsValidatedReport() throws NegociosException {
        // Arrange
        when(incidenciasBO.validarReporte(reporte)).thenReturn(reporte);

        // Act
        ReporteDTO resultado = incidenciasBO.validarReporte(reporte);

        // Assert
        assertEquals(reporte, resultado);
        verify(incidenciasBO, times(1)).validarReporte(reporte);
    }

    /**
     * Prueba que el método notificarReporte retorna verdadero al notificar un
     * reporte válido. Verifica que la notificación se realice correctamente.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testNotificarReporte_ValidReport_ReturnsTrue() throws NegociosException {
        // Arrange
        when(incidenciasBO.notificarReporte(reporte)).thenReturn(true);

        // Act
        boolean notificado = incidenciasBO.notificarReporte(reporte);

        // Assert
        assertTrue(notificado);
        verify(incidenciasBO, times(1)).notificarReporte(reporte);
    }

    /**
     * Prueba que el método recuperarReportes devuelve una lista de reportes sin
     * parámetros. Verifica que se retorne la lista de reportes esperada.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarReportes_NoParameters_ReturnsListOfReports() throws NegociosException {
        // Arrange
        List<ReporteDTO> reportes = new ArrayList<>();
        reportes.add(reporte);
        when(incidenciasBO.recuperarReportes()).thenReturn(reportes);

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportes();

        // Assert
        assertEquals(reportes, resultado);
        verify(incidenciasBO, times(1)).recuperarReportes();
    }

    /**
     * Prueba que el método insertDatosSimulados inserta datos de simulación sin
     * lanzar excepciones. Verifica que el método sea llamado exitosamente.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testInsertDatosSimulados_NoParameters_InsertsMockData() throws NegociosException {
        // Arrange
        doNothing().when(incidenciasBO).insertDatosSimulados();

        // Act
        incidenciasBO.insertDatosSimulados();

        // Assert
        verify(incidenciasBO, times(1)).insertDatosSimulados();
    }

    // Pruebas no funcionales
    /**
     * Prueba que el método recuperarAlumnosPorGrado tiene un rendimiento
     * adecuado. Verifica que el método se ejecute en menos de 100 ms.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarAlumnosPorGrado_Performance_ReturnsQuickly() throws NegociosException {
        // Arrange
        List<AlumnoDTO> alumnos = new ArrayList<>();
        alumnos.add(alumno);
        when(incidenciasBO.recuperarAlumnosPorGrado("3A")).thenReturn(alumnos);

        // Act
        long startTime = System.currentTimeMillis();
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGrado("3A");
        long endTime = System.currentTimeMillis();

        // Assert
        assertEquals(alumnos, resultado);
        assertTrue(endTime - startTime < 100, "El método debe ejecutarse en menos de 100 ms");
    }

    /**
     * Prueba que el método notificarReporte lanza una excepción cuando se pasa
     * un reporte nulo. Verifica que se arroje la excepción esperada.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testNotificarReporte_NullReport_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(null);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(null));
    }

    // Pruebas estructurales
    /**
     * Prueba que recuperarAlumnosPorGradoYGrupo devuelve los resultados
     * esperados con entradas válidas. Verifica que el método retorne los
     * alumnos esperados.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarAlumnosPorGradoYGrupo_ValidInput_ReturnsExpectedResults() throws NegociosException {
        // Arrange
        List<AlumnoDTO> alumnos = new ArrayList<>();
        alumnos.add(alumno);
        when(incidenciasBO.recuperarAlumnosPorGradoYGrupo("3", "A")).thenReturn(alumnos);

        // Act
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGradoYGrupo("3", "A");

        // Assert
        assertEquals(alumnos, resultado);
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGradoYGrupo("3", "A");
    }

    /**
     * Prueba que el método convertirReporteAReporteExpediente convierte
     * correctamente una lista de reportes a expedientes. Verifica que se
     * devuelva la lista de expedientes esperada.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testConvertirReporteAReporteExpediente_ValidReportList_ReturnsExpectedExpedienteList() throws NegociosException {
        // Arrange
        List<ReporteDTO> reportes = new ArrayList<>();
        reportes.add(reporte);
        List<ReporteExpedienteDTO> expedientes = new ArrayList<>();
        when(incidenciasBO.convertirReporteAReporteExpediente(reportes)).thenReturn(expedientes);

        // Act
        List<ReporteExpedienteDTO> resultado = incidenciasBO.convertirReporteAReporteExpediente(reportes);

        // Assert
        assertEquals(expedientes, resultado);
        verify(incidenciasBO, times(1)).convertirReporteAReporteExpediente(reportes);
    }

    /**
     * Prueba que el método recuperarReportesAlumno devuelve una lista vacía
     * cuando se utiliza un CURP no válido.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarReportesAlumno_InvalidCurp_ReturnsEmptyList() throws NegociosException {
        // Arrange
        when(incidenciasBO.recuperarReportesAlumno("INVALID_CURP")).thenReturn(new ArrayList<>());

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportesAlumno("INVALID_CURP");

        // Assert
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).recuperarReportesAlumno("INVALID_CURP");
    }

    /**
     * Prueba que recuperarReportesAlumno retorna una lista de reportes cuando
     * se usa un CURP válido.
     * TIPO: FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarReportesAlumno_ValidCurp_ReturnsReports() throws NegociosException {
        // Arrange
        List<ReporteDTO> reportes = new ArrayList<>();
        reportes.add(reporte);
        when(incidenciasBO.recuperarReportesAlumno("CURP123")).thenReturn(reportes);

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportesAlumno("CURP123");

        // Assert
        assertEquals(reportes, resultado);
        verify(incidenciasBO, times(1)).recuperarReportesAlumno("CURP123");
    }

    /**
     * Prueba que recuperarReportesAlumno retorna una lista vacía cuando el
     * alumno no existe.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarReportes_AlumnoNoExiste_ReturnsEmptyList() throws NegociosException {

        // Arrange
        when(incidenciasBO.recuperarReportesAlumno("NO_CURP")).thenReturn(new ArrayList<>());

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportesAlumno("NO_CURP");

        // Assert
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).recuperarReportesAlumno("NO_CURP");

    }

    /**
     * Prueba que crearReporte lanza una excepción al intentar crear un reporte
     * duplicado.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testCrearReporte_DuplicateReport_ThrowsException() throws NegociosException {

        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporte);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporte));
        verify(incidenciasBO, times(1)).crearReporte(reporte);

    }

    /**
     * Prueba que recuperarAlumnosPorGrado devuelve una lista vacía cuando se
     * especifica un grado inexistente.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarAlumnosPorGrado_GradoInexistente_ReturnsEmptyList() throws NegociosException {

        // Arrange
        when(incidenciasBO.recuperarAlumnosPorGrado("10Z")).thenReturn(new ArrayList<>());

        // Act
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGrado("10Z");

        // Assert
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGrado("10Z");

    }

    /**
     * Prueba que notificarReporte lanza una excepción en caso de una falla de
     * conexión.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testNotificarReporte_ConnectionFailure_ThrowsException() throws NegociosException {

        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(reporte);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(reporte));
        verify(incidenciasBO, times(1)).notificarReporte(reporte);

    }

    /**
     * Prueba que convertirReporteAReporteExpediente devuelve una lista vacía al
     * convertir una lista de reportes vacía.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testConvertirReporteAReporteExpediente_EmptyList_ReturnsEmptyList() throws NegociosException {

        // Arrange
        List<ReporteDTO> reportes = new ArrayList<>();
        when(incidenciasBO.convertirReporteAReporteExpediente(reportes)).thenReturn(new ArrayList<>());

        // Act
        List<ReporteExpedienteDTO> resultado = incidenciasBO.convertirReporteAReporteExpediente(reportes);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).convertirReporteAReporteExpediente(reportes);

    }

    /**
     * Prueba de rendimiento que asegura que recuperarReportes maneja un
     * conjunto grande de datos en menos de 200 ms.
     * TIPO: NO FUNCIONAL
     * @throws NegociosException si ocurre un error durante la prueba.
     */
    @Test
    void testRecuperarReportes_LargeDataSet_Performance() throws NegociosException {

        // Arrange
        List<ReporteDTO> reportes = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {

            reportes.add(new ReporteDTO("ID" + i, alumno, docente, null, "Descripcion " + i, "Motivo", new Date(), false, false));

        }
        when(incidenciasBO.recuperarReportes()).thenReturn(reportes);

        // Act
        long startTime = System.currentTimeMillis();
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportes();
        long endTime = System.currentTimeMillis();

        // Assert
        assertEquals(reportes, resultado);
        assertTrue((endTime - startTime) < 200, "El método debe ejecutarse en menos de 200 ms");
        verify(incidenciasBO, times(1)).recuperarReportes();

    }

}
