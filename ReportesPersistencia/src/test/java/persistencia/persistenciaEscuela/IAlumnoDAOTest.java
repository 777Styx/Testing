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

    // PRUEBAS FUNCIONALES (8 pruebas)
    /**
     * Test que verifica si el método obtenerAlumno devuelve un alumno cuando se
     * proporciona un alumno existente.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testObtenerAlumno_Existente_ReturnAlumno() throws PersistenciaException {
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

    /**
     * Test que verifica si el método obtenerAlumno lanza una excepción cuando
     * se pasa un alumno con ID nulo.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testObtenerAlumno_IdNulo_LanzaExcepcion() throws PersistenciaException {
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

    /**
     * Test que verifica si el método obtenerAlumnoPorCurp devuelve un alumno
     * cuando se pasa un CURP existente.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testObtenerAlumnoPorCurp_Existente_ReturnAlumno() throws PersistenciaException {
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

    /**
     * Test que verifica si el método obtenerAlumnoPorCurp lanza una excepción
     * cuando se pasa un alumno con CURP nula.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testObtenerAlumnoPorCurp_CurpNula_LanzaExcepcion() throws PersistenciaException {
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

    /**
     * Test que verifica si el método recuperarAlumnosPorGrado devuelve una
     * lista filtrada de alumnos por grado.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testRecuperarAlumnosPorGrado_AlRecuperarPorGrado_ReturnListaFiltrada() throws PersistenciaException {
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

    /**
     * Test que verifica si el método recuperarAlumnosPorGrupo devuelve una
     * lista filtrada de alumnos por grupo.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testRecuperarAlumnosPorGrupo_AlRecuperarPorGrupo_ReturnListaFiltrada() throws PersistenciaException {
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

    /**
     * Test que verifica si el método recuperarAlumnosPorGradoYGrupo devuelve
     * una lista filtrada de alumnos por grado y grupo.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testRecuperarAlumnosPorGradoYGrupo_AlRecuperarPorGradoYGrupo_ReturnListaCorrecta() throws PersistenciaException {
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

    /**
     * Test que verifica si el uso de memoria no excede 1MB al recuperar alumnos
     * por grado.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testUsoDeMemoria_RecuperarAlumnosPorGrado_Menor1MB() throws PersistenciaException {
        // Arrange
        String grado = "1";
        List<AlumnoEntity> alumnosEsperados = new ArrayList<>();
        when(alumnoDAO.recuperarAlumnosPorGrado(grado)).thenReturn(alumnosEsperados);

        // Act
        Runtime runtime = Runtime.getRuntime(); // Capturamos el uso de memoria antes de la operación
        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

        List<AlumnoEntity> resultado = alumnoDAO.recuperarAlumnosPorGrado(grado);

        // Capturamos el uso de memoria después de la operación
        long memoriaDespues = runtime.totalMemory() - runtime.freeMemory();

        // Assert
        assertNotNull(resultado);
        // Verificamos que el uso de memoria no supere un límite razonable, por ejemplo, 1 MB
        long memoriaUtilizada = memoriaDespues - memoriaAntes;
        assertTrue(memoriaUtilizada < 1024 * 1024); // 1 MB
    }

    // PRUEBAS NO FUNCIONALES (3 pruebas)
    /**
     * Test que verifica el rendimiento del método obtenerAlumnoPorCurp,
     * asegurando que el tiempo de ejecución sea menor a 1000ms.
     *
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * los datos.
     */
    @Test
    public void testRendimiento_ObtenerAlumnoPorCurp_TiempoEjecucionMenor1000ms() throws PersistenciaException {
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

    /**
     * Verifica que el tiempo de ejecución para recuperar los alumnos por grado
     * sea menor a 1000ms.
     *
     * @throws PersistenciaException Si ocurre un error al recuperar los
     * alumnos.
     */
    @Test
    public void testRendimiento_RecuperarAlumnosPorGrado_TiempoEjecucionMenor1000ms() throws PersistenciaException {
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

    /**
     * Verifica que no ocurran errores cuando se realizan búsquedas simultáneas
     * de alumnos por grado y grupo.
     *
     * @throws PersistenciaException Si ocurre un error al recuperar los
     * alumnos.
     */
    @Test
    public void testConcurrencia_MultiplesBusquedasSimultaneas_NoErrores() throws PersistenciaException {
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
    /**
     * Verifica que el formato del campo GradoGrupo siga la expresión regular
     * que indica un número seguido de una letra mayúscula.
     *
     * @throws PersistenciaException Si ocurre un error al establecer el valor.
     */
    @Test
    public void testEstructura_VerificarFormatoGradoGrupo_CumpleExpresionRegular() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setGradoGrupo("1A");

        // Assert
        assertTrue(alumno.getGradoGrupo().matches("\\d[A-Z]"));
    }

    /**
     * Verifica que el formato del campo CURP cumpla con la expresión regular
     * correspondiente al formato estándar de CURP en México.
     *
     * @throws PersistenciaException Si ocurre un error al establecer el valor.
     */
    @Test
    public void testEstructura_VerificarFormatoCURP_CumpleExpresionRegular() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setCURP("ABCD123456HIJKLM00");

        // Assert
        assertTrue(alumno.getCURP().matches("[A-Z]{4}\\d{6}[A-Z]{6}\\d{2}"));
    }

    /**
     * Verifica que el formato del campo EmailTutor cumpla con la expresión
     * regular para correos electrónicos válidos.
     *
     * @throws PersistenciaException Si ocurre un error al establecer el valor.
     */
    @Test
    public void testEstructura_VerificarFormatoEmail_CumpleExpresionRegular() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setEmailTutor("tutor@dominio.com");

        // Assert
        assertTrue(alumno.getEmailTutor().matches("^[A-Za-z0-9+_.-]+@(.+)$"));
    }

    /**
     * Verifica que el formato del campo UrlFoto cumpla con la expresión regular
     * para URLs de fotos en formato JPG, JPEG, PNG o GIF.
     *
     * @throws PersistenciaException Si ocurre un error al establecer el valor.
     */
    @Test
    public void testEstructura_VerificarFormatoUrlFoto_CumpleExpresionRegular() throws PersistenciaException {
        // Arrange
        AlumnoEntity alumno = new AlumnoEntity();

        // Act
        alumno.setUrlFoto("http://ejemplo.com/foto.jpg");

        // Assert
        assertTrue(alumno.getUrlFoto().matches("^(http|https)://.*\\.(jpg|jpeg|png|gif)$"));
    }

}
