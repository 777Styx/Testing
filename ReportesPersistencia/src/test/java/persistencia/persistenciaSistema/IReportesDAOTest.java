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
class IReportesDAOTest {

    @Mock
    private MongoCollection<ReporteEntity> coleccion;

    @Mock
    private FindIterable<ReporteEntity> findIterable;

    @InjectMocks
    private ReportesDAO reportesDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertarReporte_CuandoInsercionExitosa_DeberiaNoLanzarExcepcion() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        doNothing().when(coleccion).insertOne(reporte);

        // Act & Assert
        assertDoesNotThrow(() -> {
            ReporteEntity resultado = reportesDAO.insertarReporte(reporte);
            assertNull(resultado);
        });
        verify(coleccion).insertOne(reporte);
    }

    @Test
    void insertarReporte_CuandoErrorDeInsercion_DeberiaLanzarPersistenciaException() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        doThrow(new MongoException("Error de conexión")).when(coleccion).insertOne(reporte);

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> reportesDAO.insertarReporte(reporte));
        assertEquals("Error al insertar reportes", exception.getMessage());
    }

    @Test
    void validarReporte_CuandoReporteNoExiste_DeberiaRetornarNull() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        reporte.setId(new ObjectId());

        when(coleccion.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> {
            ReporteEntity resultado = reportesDAO.validarReporte(reporte);
            assertNull(resultado);
        });
        verify(coleccion, never()).updateOne(any(), any());
    }

    @Test
    void modificarReporte_CuandoErrorDeModificacion_DeberiaLanzarPersistenciaException() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        reporte.setId(new ObjectId());

        doThrow(new MongoException("Error de conexión")).when(coleccion).replaceOne(any(), any());

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.modificarReporte(reporte));
    }

    @Test
    void notificarReporte_CuandoErrorDeNotificacion_DeberiaRetornarFalso() {
        // Arrange
        ObjectId id = new ObjectId();
        ReporteEntity reporte = crearReportePrueba(id);

        doThrow(new MongoException("Error de conexión"))
                .when(coleccion).updateOne(any(), any());

        // Act & Assert
        assertDoesNotThrow(() -> {
            boolean resultado = reportesDAO.notificarReporte(reporte);
            assertFalse(resultado);
        });
    }

    @Test
    void recuperarReportes_CuandoHayReportes_DeberiaRetornarListaDeReportes() {
        // Arrange
        List<ReporteEntity> reportesEsperados = Arrays.asList(
                crearReportePrueba(new ObjectId()),
                crearReportePrueba(new ObjectId())
        );

        when(coleccion.find()).thenReturn(findIterable);
        when(findIterable.into(any())).thenAnswer(invocation -> {
            List<ReporteEntity> target = invocation.getArgument(0);
            target.addAll(reportesEsperados);
            return target;
        });

        // Act & Assert
        assertDoesNotThrow(() -> {
            List<ReporteEntity> resultado = reportesDAO.recuperarReportes();
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
        });
    }

    @Test
    void recuperarReportes_CuandoErrorDeRecuperacion_DeberiaLanzarPersistenciaException() {
        // Arrange
        when(coleccion.find()).thenThrow(new MongoException("Error de conexión"));

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.recuperarReportes());
    }

    @Test
    void recuperarReportesAlumno_CuandoErrorDeRecuperacionPorAlumno_DeberiaLanzarPersistenciaException() {
        // Arrange
        String curp = "CURP123";
        when(coleccion.find(any(Bson.class))).thenThrow(new MongoException("Error de conexión"));

        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.recuperarReportesAlumno(curp));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void recuperarReportes_CuandoOperacionRapida_DeberiaCompletarEnTiempoEsperado() {
        // Arrange
        when(coleccion.find()).thenReturn(findIterable);
        when(findIterable.into(any())).thenReturn(new ArrayList<>());

        // Act & Assert
        assertDoesNotThrow(() -> reportesDAO.recuperarReportes());
    }

    // Método auxiliar para crear reportes de prueba
    private ReporteEntity crearReportePrueba(ObjectId id) {
        ReporteEntity reporte = new ReporteEntity();
        reporte.setId(id);
        reporte.setNivelIncidencia(NivelIncidenciaPersistencia.GRAVE);
        reporte.setDescripcion("Descripción de prueba");
        reporte.setMotivo("Motivo de prueba");
        return reporte;
    }
}