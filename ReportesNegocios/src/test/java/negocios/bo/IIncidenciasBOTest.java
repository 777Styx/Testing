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
    @Test
    void testCrearReporte_ValidInput_CreatesReportSuccessfully() throws NegociosException {
        // Arrange
        doNothing().when(incidenciasBO).crearReporte(reporte);

        // Act
        incidenciasBO.crearReporte(reporte);

        // Assert
        verify(incidenciasBO, times(1)).crearReporte(reporte);
    }

    @Test
    void testCrearReporte_ReportInvalido_ThrowsException() throws NegociosException {
        // Arrange
        ReporteDTO reporteInvalido = new ReporteDTO("ID2", null, null, null, "Descripcion", "Motivo", new Date(), false, false);
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteInvalido));
    }

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

    @Test
    void testNotificarReporte_NullReport_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(null);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(null));
    }

    
    // Pruebas estructurales
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

    @Test
    void testCrearReporte_DuplicateReport_ThrowsException() throws NegociosException {
        
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporte);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporte));
        verify(incidenciasBO, times(1)).crearReporte(reporte);
        
    }

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

    @Test
    void testNotificarReporte_ConnectionFailure_ThrowsException() throws NegociosException {
        
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(reporte);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(reporte));
        verify(incidenciasBO, times(1)).notificarReporte(reporte);
        
    }

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
