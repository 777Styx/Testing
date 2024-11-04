/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package persistencia.persistenciaSistema;

import com.mongodb.MongoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;
import org.mockito.InjectMocks;
import persistencia.entidades.NivelIncidenciaPersistencia;
import persistencia.entidades.ReporteEntity;
import persistencia.excepciones.PersistenciaException;

class ReportesDAOTest {
    
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

    // Pruebas para insertarReporte
    @Test
    void deberiaInsertarReporteExitosamente() {
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
    void deberiaLanzarExcepcionCuandoHayErrorDeInsercion() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        doThrow(new MongoException("Error de conexión")).when(coleccion).insertOne(reporte);
        
        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class, 
            () -> reportesDAO.insertarReporte(reporte));
        assertEquals("Error al insertar reportes", exception.getMessage());
    }

    // Pruebas para validarReporte
    @Test
    void deberiaValidarReporteExistente() {
        // Arrange
        ObjectId id = new ObjectId();
        ReporteEntity reporte = crearReportePrueba(id);
        
        when(coleccion.find(Filters.eq("_id", id))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(reporte);
        
        // Act & Assert
        assertDoesNotThrow(() -> {
            ReporteEntity resultado = reportesDAO.validarReporte(reporte);
            assertNotNull(resultado);
            assertEquals(id, resultado.getId());
        });
        verify(coleccion).updateOne(any(), any());
    }
    
    @Test
    void deberiaRetornarNullCuandoReporteNoExiste() {
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

    // Pruebas para modificarReporte
    @Test
    void deberiaModificarReporteExitosamente() {
        // Arrange
        ObjectId id = new ObjectId();
        ReporteEntity reporte = crearReportePrueba(id);
        
        when(coleccion.find(Filters.eq("_id", id))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(reporte);
        
        // Act & Assert
        assertDoesNotThrow(() -> {
            ReporteEntity resultado = reportesDAO.modificarReporte(reporte);
            assertNotNull(resultado);
            assertEquals(id, resultado.getId());
        });
        verify(coleccion).replaceOne(any(), eq(reporte));
    }
    
    @Test
    void deberiaLanzarExcepcionCuandoHayErrorDeModificacion() {
        // Arrange
        ReporteEntity reporte = new ReporteEntity();
        reporte.setId(new ObjectId());
        
        doThrow(new MongoException("Error de conexión")).when(coleccion).replaceOne(any(), any());
        
        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.modificarReporte(reporte));
    }

    // Pruebas para notificarReporte
    @Test
    void deberiaNotificarReporteExitosamente() {
        // Arrange
        ObjectId id = new ObjectId();
        ReporteEntity reporte = crearReportePrueba(id);
        
        when(coleccion.updateOne(any(), any()))
            .thenReturn(UpdateResult.acknowledged(1, 1L, null));
        
        // Act & Assert
        assertDoesNotThrow(() -> {
            boolean resultado = reportesDAO.notificarReporte(reporte);
            assertTrue(resultado);
        });
        verify(coleccion).updateOne(
            Filters.eq("_id", id), 
            Updates.set("notificado", true)
        );
    }
    
    @Test
    void deberiaRetornarFalsoCuandoHayErrorDeNotificacion() {
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

    // Pruebas para recuperarReportes
    @Test
    void deberiaRecuperarTodosLosReportes() {
        // Arrange
        List<ReporteEntity> reportesEsperados = Arrays.asList(
            crearReportePrueba(new ObjectId()),
            crearReportePrueba(new ObjectId())
        );
        
        when(coleccion.find()).thenReturn(findIterable);
        when(findIterable.into(any())).thenReturn(reportesEsperados);
        
        // Act & Assert
        assertDoesNotThrow(() -> {
            List<ReporteEntity> resultado = reportesDAO.recuperarReportes();
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
        });
    }
    
    @Test
    void deberiaLanzarExcepcionCuandoHayErrorDeRecuperacion() {
        // Arrange
        when(coleccion.find()).thenThrow(new MongoException("Error de conexión"));
        
        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.recuperarReportes());
    }

    // Pruebas para recuperarReportesAlumno
    @Test
    void deberiaRecuperarReportesPorAlumno() {
        // Arrange
        String curp = "CURP123";
        List<ReporteEntity> reportesEsperados = Arrays.asList(
            crearReportePrueba(new ObjectId())
        );
        
        when(coleccion.find(Filters.eq("alumno.cURP", curp))).thenReturn(findIterable);
        when(findIterable.into(any())).thenReturn(reportesEsperados);
        
        // Act & Assert
        assertDoesNotThrow(() -> {
            List<ReporteEntity> resultado = reportesDAO.recuperarReportesAlumno(curp);
            assertNotNull(resultado);
            assertEquals(1, resultado.size());
        });
    }
    
    @Test
    void deberiaLanzarExcepcionCuandoHayErrorDeRecuperacionPorAlumno() {
        // Arrange
        String curp = "CURP123";
        when(coleccion.find(any(Bson.class))).thenThrow(new MongoException("Error de conexión"));
        
        // Act & Assert
        assertThrows(PersistenciaException.class, () -> reportesDAO.recuperarReportesAlumno(curp));
    }

    // Pruebas no funcionales
    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void deberiaCompletarOperacionEnTiempoEsperado() {
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