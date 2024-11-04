/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package persistencia.persistenciaSistema;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.entidades.NivelIncidenciaPersistencia;
import persistencia.entidades.ReporteEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author olive
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IReportesDAOTest {

    private IReportesDAO reportesDAO;
    private ReporteEntity reporte;

    @BeforeEach
    void setUp() {
        // Arrange - setup common objects and mocks
        reportesDAO = Mockito.mock(IReportesDAO.class);
        reporte = new ReporteEntity();
    }

    // Pruebas funcionales
    @Test
    void testInsertarReporte_ValidReporte_InsertsSuccessfully() throws PersistenciaException {
        // Arrange
        when(reportesDAO.insertarReporte(reporte)).thenReturn(reporte);

        // Act
        ReporteEntity resultado = reportesDAO.insertarReporte(reporte);

        // Assert
        assertEquals(reporte, resultado);
        verify(reportesDAO, times(1)).insertarReporte(reporte);
    }

    @Test
    void testValidarReporte_ValidReporte_ReturnsValidatedReport() throws PersistenciaException {
        // Arrange
        when(reportesDAO.validarReporte(reporte)).thenReturn(reporte);

        // Act
        ReporteEntity resultado = reportesDAO.validarReporte(reporte);

        // Assert
        assertEquals(reporte, resultado);
        verify(reportesDAO, times(1)).validarReporte(reporte);
    }

    @Test
    void testModificarReporte_ValidReporte_UpdatesSuccessfully() throws PersistenciaException {
        // Arrange
        when(reportesDAO.modificarReporte(reporte)).thenReturn(reporte);

        // Act
        ReporteEntity resultado = reportesDAO.modificarReporte(reporte);

        // Assert
        assertEquals(reporte, resultado);
        verify(reportesDAO, times(1)).modificarReporte(reporte);
    }

    @Test
    void testNotificarReporte_ValidReporte_ReturnsTrue() throws PersistenciaException {
        // Arrange
        when(reportesDAO.notificarReporte(reporte)).thenReturn(true);

        // Act
        boolean notificado = reportesDAO.notificarReporte(reporte);

        // Assert
        assertTrue(notificado);
        verify(reportesDAO, times(1)).notificarReporte(reporte);
    }

    @Test
    void testRecuperarReportes_NoParameters_ReturnsListOfReports() throws PersistenciaException {
        // Arrange
        List<ReporteEntity> reportes = new ArrayList<>();
        reportes.add(reporte);
        when(reportesDAO.recuperarReportes()).thenReturn(reportes);

        // Act
        List<ReporteEntity> resultado = reportesDAO.recuperarReportes();

        // Assert
        assertEquals(reportes, resultado);
        verify(reportesDAO, times(1)).recuperarReportes();
    }

    @Test
    void testInsertarReportesSimulados_NoParameters_InsertsMockData() throws PersistenciaException {
        // Arrange
        doNothing().when(reportesDAO).insertarReportesSimulados();

        // Act
        reportesDAO.insertarReportesSimulados();

        // Assert
        verify(reportesDAO, times(1)).insertarReportesSimulados();
    }

    // Pruebas no funcionales
    @Test
    void testRecuperarReportes_Performance_ReturnsQuickly() throws PersistenciaException {
        // Arrange
        List<ReporteEntity> reportes = new ArrayList<>();
        reportes.add(reporte);
        when(reportesDAO.recuperarReportes()).thenReturn(reportes);

        // Act
        long startTime = System.currentTimeMillis();
        List<ReporteEntity> resultado = reportesDAO.recuperarReportes();
        long endTime = System.currentTimeMillis();

        // Assert
        assertEquals(reportes, resultado);
        assertTrue(endTime - startTime < 100, "El método debe ejecutarse en menos de 100 ms");
    }

    @Test
    void testNotificarReporte_NullReporte_ThrowsException() throws PersistenciaException {
        // Arrange
        doThrow(PersistenciaException.class).when(reportesDAO).notificarReporte(null);

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.notificarReporte(null));
    }

    // Pruebas estructurales
    @Test
    void testRecuperarReportesAlumno_ValidCurp_ReturnsExpectedResults() throws PersistenciaException {
        // Arrange
        List<ReporteEntity> reportes = new ArrayList<>();
        reportes.add(reporte);
        when(reportesDAO.recuperarReportesAlumno("CURP123")).thenReturn(reportes);

        // Act
        List<ReporteEntity> resultado = reportesDAO.recuperarReportesAlumno("CURP123");

        // Assert
        assertEquals(reportes, resultado);
        verify(reportesDAO, times(1)).recuperarReportesAlumno("CURP123");
    }

    @Test
    void testRecuperarReportesAlumno_InvalidCurp_ReturnsEmptyList() throws PersistenciaException {
        // Arrange
        when(reportesDAO.recuperarReportesAlumno("INVALID_CURP")).thenReturn(new ArrayList<>());

        // Act
        List<ReporteEntity> resultado = reportesDAO.recuperarReportesAlumno("INVALID_CURP");

        // Assert
        assertTrue(resultado.isEmpty());
        verify(reportesDAO, times(1)).recuperarReportesAlumno("INVALID_CURP");
    }

    @Test
    void testInsertarReporte_NullReporte_ThrowsException() throws PersistenciaException {
        // Arrange
        doThrow(PersistenciaException.class).when(reportesDAO).insertarReporte(null);

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.insertarReporte(null));
    }

    @Test
    void testModificarReporte_NullReporte_ThrowsException() throws PersistenciaException {
        // Arrange
        doThrow(PersistenciaException.class).when(reportesDAO).modificarReporte(null);

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.modificarReporte(null));
    }
}
