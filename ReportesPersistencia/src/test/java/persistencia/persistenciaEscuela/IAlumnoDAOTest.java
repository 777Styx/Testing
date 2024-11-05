/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package persistencia.persistenciaEscuela;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.entidades.AlumnoEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author castr
 */
public class IAlumnoDAOTest {

    @Mock
    private MongoCollection<AlumnoEntity> coleccion;

    @Mock
    private FindIterable<AlumnoEntity> findIterable;

    @Mock
    private MongoCursor<AlumnoEntity> cursor;

    @Mock
    private IAlumnoDAO alumnoDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        alumnoDAO = Mockito.mock(IAlumnoDAO.class); 
    }

    // PRUEBAS FUNCIONALES
    @Test
    public void testObtenerAlumno_ExistenteRetornaAlumno() throws PersistenciaException {
        // Arrange
        ObjectId id = new ObjectId();
        AlumnoEntity alumnoEntrada = new AlumnoEntity(id, "CURP1", "Juan", "Pérez", "López", "1A", "tutor@mail.com", "foto.jpg");
        AlumnoEntity alumnoEsperado = new AlumnoEntity(id, "CURP1", "Juan", "Pérez", "López", "1A", "tutor@mail.com", "foto.jpg");
        when(alumnoDAO.obtenerAlumno(alumnoEntrada)).thenReturn(alumnoEsperado);

        // Act
        AlumnoEntity alumnoResultado = alumnoDAO.obtenerAlumno(alumnoEntrada);

        // Assert
        assertNotNull(alumnoResultado);
        assertEquals(id, alumnoResultado.getId());
        verify(alumnoDAO).obtenerAlumno(alumnoEntrada);
    }

    @Test
    public void testObtenerAlumno_IdNuloLanzaExcepcion() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumnoNulo = new AlumnoEntity();
        when(alumnoDAO.obtenerAlumno(alumnoNulo))
                .thenThrow(new PersistenciaException("El id del alumno es nulo."));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            alumnoDAO.obtenerAlumno(alumnoNulo);
        });

        // Assert adicional para verificar el mensaje
        assertEquals("El id del alumno es nulo.", exception.getMessage());
        verify(alumnoDAO).obtenerAlumno(alumnoNulo);
    }

    @Test
    public void testObtenerAlumnoPorCurp_ExistenteRetornaAlumno() throws PersistenciaException {
        // Arrange
        String curp = "CURP123";
        AlumnoEntity alumnoEntrada = new AlumnoEntity();
        alumnoEntrada.setCURP(curp);
        AlumnoEntity alumnoEsperado = new AlumnoEntity();
        alumnoEsperado.setCURP(curp);
        when(alumnoDAO.obtenerAlumnoPorCurp(alumnoEntrada)).thenReturn(alumnoEsperado);

        // Act
        AlumnoEntity resultado = alumnoDAO.obtenerAlumnoPorCurp(alumnoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals(curp, resultado.getCURP());
        verify(alumnoDAO).obtenerAlumnoPorCurp(alumnoEntrada);
    }

    @Test
    public void testObtenerAlumnoPorCurp_CurpNulaLanzaExcepcion() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumnoCurpNula = new AlumnoEntity();
        when(alumnoDAO.obtenerAlumnoPorCurp(alumnoCurpNula))
                .thenThrow(new PersistenciaException("La curp del alumno es nula."));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            alumnoDAO.obtenerAlumnoPorCurp(alumnoCurpNula);
        });

        // Assert adicional para verificar el mensaje
        assertEquals("La curp del alumno es nula.", exception.getMessage());
        verify(alumnoDAO).obtenerAlumnoPorCurp(alumnoCurpNula);
    }

    @Test
    public void testRecuperarAlumnosPorGrado_RetornaListaFiltrada() throws PersistenciaException {
        // Arrange
        String grado = "1";
        List<AlumnoEntity> alumnosEsperados = new ArrayList<>();
        alumnosEsperados.add(new AlumnoEntity(null, "CURP1", "Juan", "Pérez", "López", "1A", "mail1@mail.com", "foto1.jpg"));
        when(alumnoDAO.recuperarAlumnosPorGrado(grado)).thenReturn(alumnosEsperados);

        // Act
        List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrado(grado);

        // Assert
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getGradoGrupo().startsWith("1"));
        verify(alumnoDAO).recuperarAlumnosPorGrado(grado);
    }

    @Test
    public void testRecuperarAlumnosPorGrupo_RetornaListaFiltrada() throws PersistenciaException {
        // Arrange
        String grupo = "A";
        List<AlumnoEntity> alumnosEsperados = new ArrayList<>();
        alumnosEsperados.add(new AlumnoEntity(null, "CURP2", "María", "García", "Ruiz", "2A", "mail2@mail.com", "foto2.jpg"));
        when(alumnoDAO.recuperarAlumnosPorGrupo(grupo)).thenReturn(alumnosEsperados);

        // Act
        List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrupo(grupo);

        // Assert
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getGradoGrupo().endsWith("A"));
        verify(alumnoDAO).recuperarAlumnosPorGrupo(grupo);
    }

    @Test
    public void testRecuperarAlumnosPorGradoYGrupo_RetornaListaCorrecta() throws PersistenciaException {
        // Arrange
        String grado = "3";
        String grupo = "B";
        List<AlumnoEntity> alumnosEsperados = new ArrayList<>();
        alumnosEsperados.add(new AlumnoEntity(null, "CURP3", "Pedro", "Sánchez", "Gómez", "3B", "mail3@mail.com", "foto3.jpg"));
        when(alumnoDAO.recuperarAlumnosPorGradoYGrupo(grado, grupo)).thenReturn(alumnosEsperados);

        // Act
        List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGradoYGrupo(grado, grupo);

        // Assert
        assertFalse(resultado.isEmpty());
        assertEquals("3B", resultado.get(0).getGradoGrupo());
        verify(alumnoDAO).recuperarAlumnosPorGradoYGrupo(grado, grupo);
    }

    // PRUEBAS NO FUNCIONALES
    @Test
    public void testRendimiento_ObtenerAlumnoPorCurp() throws PersistenciaException {
        // Arrange
        String curp = "CURP1";
        AlumnoEntity alumnoEntrada = new AlumnoEntity();
        alumnoEntrada.setCURP(curp);
        AlumnoEntity alumnoEsperado = new AlumnoEntity();
        alumnoEsperado.setCURP(curp);
        when(alumnoDAO.obtenerAlumnoPorCurp(alumnoEntrada)).thenReturn(alumnoEsperado);

        // Act
        long startTime = System.currentTimeMillis();
        AlumnoEntity resultado = alumnoDAO.obtenerAlumnoPorCurp(alumnoEntrada);
        long endTime = System.currentTimeMillis();

        // Assert
        assertNotNull(resultado);
        assertTrue((endTime - startTime) < 1000);
    }

    @Test
    public void testRendimiento_RecuperarAlumnosPorGrado() throws PersistenciaException {
        // Arrange
        String grado = "1";
        List<AlumnoEntity> alumnosEsperados = new ArrayList<>();
        when(alumnoDAO.recuperarAlumnosPorGrado(grado)).thenReturn(alumnosEsperados);

        // Act
        long startTime = System.currentTimeMillis();
        List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrado(grado);
        long endTime = System.currentTimeMillis();

        // Assert
        assertNotNull(resultado);
        assertTrue((endTime - startTime) < 1000);
    }

    @Test
    public void testConcurrencia_MultiplesBusquedasSimultaneas() throws PersistenciaException {
        // Arrange
        String grado = "1";
        String grupo = "A";
        when(alumnoDAO.recuperarAlumnosPorGrado(grado)).thenReturn(new ArrayList<>());
        when(alumnoDAO.recuperarAlumnosPorGrupo(grupo)).thenReturn(new ArrayList<>());

        // Act
        CountDownLatch latch = new CountDownLatch(2);
        AtomicBoolean hasError = new AtomicBoolean(false);

        Thread t1 = new Thread(() -> {
            try {
                List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrado(grado);
                assertNotNull(resultado);
            } catch (Exception e) {
                hasError.set(true);
            } finally {
                latch.countDown();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrupo(grupo);
                assertNotNull(resultado);
            } catch (Exception e) {
                hasError.set(true);
            } finally {
                latch.countDown();
            }
        });

        t1.start();
        t2.start();

        // Assert
        try {
            latch.await(5, TimeUnit.SECONDS);
            assertFalse(hasError.get());
        } catch (InterruptedException e) {
            fail("Error en la concurrencia");
        }
    }

// PRUEBAS ESTRUCTURALES (4 pruebas)
    @Test
    public void testEstructura_VerificarFormatoGradoGrupo() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setGradoGrupo("1A");

        // Assert
        assertTrue(alumno.getGradoGrupo().matches("\\d[A-Z]"));
    }

    @Test
    public void testEstructura_VerificarFormatoCURP() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setCURP("ABCD123456HIJKLM00");

        // Assert
        assertTrue(alumno.getCURP().matches("[A-Z]{4}\\d{6}[A-Z]{6}\\d{2}"));
    }

    @Test
    public void testEstructura_VerificarFormatoEmail() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setEmailTutor("tutor@dominio.com");

        // Assert
        assertTrue(alumno.getEmailTutor().matches("^[A-Za-z0-9+_.-]+@(.+)$"));
    }

    @Test
    public void testEstructura_VerificarFormatoUrlFoto() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setUrlFoto("http://ejemplo.com/foto.jpg");

        // Assert
        assertTrue(alumno.getUrlFoto().matches("^(http|https)://.*\\.(jpg|jpeg|png|gif)$"));
    }
    
    


}
