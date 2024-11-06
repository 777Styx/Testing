package persistencia.persistenciaEscuela;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import persistencia.entidades.UsuarioEntity;
import persistencia.excepciones.PersistenciaException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Clase que contiene las pruebas unitarias para la lógica de manejo de
 * usuarios.
 */
@ExtendWith(MockitoExtension.class)
class IUsuarioDAOTest {

    /**
     * Simulacion de interfaz IUsuarioDAO
     */
    @Mock
    private IUsuarioDAO usuarioDAO;

    //variables
    private UsuarioEntity docenteValido;
    private UsuarioEntity docenteInvalido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Docente válido
        docenteValido = new UsuarioEntity();
        docenteValido.setNombre("Juan Perez");
        docenteValido.setCurp("PERJ800101HDFXXX01");

        // Docente inválido
        docenteInvalido = new UsuarioEntity();
        docenteInvalido.setCurp("CURP_INVALIDA");
    }

    /**
     * Simula insercion de datos sin errores
     *
     * @throws PersistenciaException
     */
    @Test
    void insertarDocentesSimulados_NoExistenDocentes_InsertarExitosamente() throws PersistenciaException {
        // Arrange
        doNothing().when(usuarioDAO).insertarDocentesSimulados();

        // Act & Assert
        assertDoesNotThrow(() -> usuarioDAO.insertarDocentesSimulados());
        verify(usuarioDAO, times(1)).insertarDocentesSimulados();
    }

    /**
     * simula insertarDocentesSimulados cuando ocurre un error en la base de
     * datos al intentar insertar un docentes
     *
     * @throws PersistenciaException
     */
    @Test
    void insertarDocentesSimulados_ErrorBaseDatos_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        doThrow(new PersistenciaException("Error al insertar docentes"))
                .when(usuarioDAO).insertarDocentesSimulados();

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.insertarDocentesSimulados());
        assertEquals("Error al insertar docentes", exception.getMessage());
        verify(usuarioDAO, times(1)).insertarDocentesSimulados();
    }

    /**
     * valida que insertarDocentesSimulados maneja correctamente una excepcion
     * de tipo PersistenciaException cuando ocurre un error en la base de datos
     *
     * @throws PersistenciaException
     */
    @Test
    void insertarDocentesSimulados_ErrorConexion_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        doThrow(new PersistenciaException("Error de conexión a la base de datos"))
                .when(usuarioDAO).insertarDocentesSimulados();

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.insertarDocentesSimulados());
        assertEquals("Error de conexión a la base de datos", exception.getMessage());
        verify(usuarioDAO, times(1)).insertarDocentesSimulados();
    }

    /**
     * valida el obtener un docente existente en la base de datos
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_ExisteDocente_RetornarDocente() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class))).thenReturn(docenteValido);

        // Act
        UsuarioEntity result = usuarioDAO.obtenerDocente(new UsuarioEntity());

        // Assert
        assertNotNull(result);
        assertEquals("Juan Perez", result.getNombre());
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * simula que obtenerDocente no encuentra un docente y que devuelve null
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_NoExisteDocente_RetornarNull() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class))).thenReturn(null);

        // Act
        UsuarioEntity result = usuarioDAO.obtenerDocente(new UsuarioEntity());

        // Assert
        assertNull(result);
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * valida que al dar un valor nulo como argumento, el método lanza una
     * excepción de tipo PersistenciaException
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_NullDocente_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(null))
                .thenThrow(new PersistenciaException("El docente no puede ser null"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocente(null));
        assertEquals("El docente no puede ser null", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocente(null);
    }

    /**
     * valida que se lance una excepción de tipo cuando ocurre un error en la
     * base de datos al intentar obtener un docente válido.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_ErrorBaseDatos_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("Error en la base de datos"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocente(docenteValido));
        assertEquals("Error en la base de datos", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * valida que al proporcionar un CURP valido, el método devuelve un al
     * docente con el CURP correspondiente especificado.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_CurpValido_RetornarDocente() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class))).thenReturn(docenteValido);

        // Act
        UsuarioEntity result = usuarioDAO.obtenerDocentePorCurp(docenteValido);

        // Assert
        assertNotNull(result);
        assertEquals("PERJ800101HDFXXX01", result.getCurp());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * * valida que se devuelva null cuando se le proporciona un CURP no valido
     * o inexistente.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_CurpInvalido_RetornarNull() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class))).thenReturn(null);

        // Act
        UsuarioEntity result = usuarioDAO.obtenerDocentePorCurp(docenteInvalido);

        // Assert
        assertNull(result);
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * valida que se lance una excepción de tipo `PersistenciaException` cuando
     * se le proporciona un valor null en lugar de un objeto UsuarioEntity.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_NullDocente_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(null))
                .thenThrow(new PersistenciaException("El docente no puede ser null"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(null));
        assertEquals("El docente no puede ser null", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(null);
    }

    /**
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_CurpVacia_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        UsuarioEntity docenteSinCurp = new UsuarioEntity();
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("La CURP no puede estar vacía"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(docenteSinCurp));
        assertEquals("La CURP no puede estar vacía", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * * valida que se lanza una excepción de tipo PersistenciaException cuando
     * se le una curp con formato invalido
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_CurpFormatoInvalido_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        UsuarioEntity docenteCurpInvalida = new UsuarioEntity();
        docenteCurpInvalida.setCurp("123"); // CURP demasiado corta
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("Formato de CURP inválido"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(docenteCurpInvalida));
        assertEquals("Formato de CURP inválido", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * Verifica que el método lanza una excepción de tipo PersistenciaException
     * cuando ocurre un error en la base de datos al intentar obtener un docente
     * por su CURP.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_ErrorBaseDatos_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("Error en la base de datos"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(docenteValido));
        assertEquals("Error en la base de datos", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     *  * valida que se lance una excepción cuando se intenta insertar docentes
     * si ya existen registros de docentes previamente.
     *
     * @throws PersistenciaException
     */
    @Test
    void insertarDocentesSimulados_DocentesYaExistentes_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        doThrow(new PersistenciaException("Ya existen docentes en la base de datos"))
                .when(usuarioDAO).insertarDocentesSimulados();

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.insertarDocentesSimulados());
        assertEquals("Ya existen docentes en la base de datos", exception.getMessage());
        verify(usuarioDAO, times(1)).insertarDocentesSimulados();
    }

    /**
     * valida que se lance una excepción cuando se intenta obtener un docente
     * que no existe en la base de datos.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_DocenteInexistente_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("El docente no existe"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocente(docenteInvalido));
        assertEquals("El docente no existe", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * valida que el método lanza una excepción cuando no se encuentra un
     * docente en la base de datos utilizando la CURP.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_DocenteNoEncontrado_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("Docente no encontrado por CURP"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(docenteInvalido));
        assertEquals("Docente no encontrado por CURP", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * valida que se lanza una excepción cuando se intenta obtener un docente
     * cuyo nombre está vacío.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_NombreVacio_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        UsuarioEntity docenteSinNombre = new UsuarioEntity();
        docenteSinNombre.setCurp("PERJ800101HDFXXX01");
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("El nombre del docente no puede estar vacío"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocente(docenteSinNombre));
        assertEquals("El nombre del docente no puede estar vacío", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * valida que se lance una excepción cuando se intenta obtener un docente
     * cuyo CURP está duplicado en la base de datos.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocentePorCurp_CurpDuplicada_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocentePorCurp(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("La CURP debe ser única"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocentePorCurp(docenteValido));
        assertEquals("La CURP debe ser única", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocentePorCurp(any(UsuarioEntity.class));
    }

    /**
     * * valida que se lance una excepción cuando se encuentra un error en el
     * formato del docente.
     *
     * @throws PersistenciaException
     */
    @Test
    void obtenerDocente_ErrorEnFormato_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        when(usuarioDAO.obtenerDocente(any(UsuarioEntity.class)))
                .thenThrow(new PersistenciaException("Error en el formato del docente"));

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.obtenerDocente(docenteInvalido));
        assertEquals("Error en el formato del docente", exception.getMessage());
        verify(usuarioDAO, times(1)).obtenerDocente(any(UsuarioEntity.class));
    }

    /**
     * valida que el método insertarDocentesSimulados lanza una excepción con el
     * mensaje correcto cuando la operación es cancelada,
     *
     * @throws PersistenciaException
     */
    @Test
    void insertarDocentesSimulados_OperacionCancelada_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        doThrow(new PersistenciaException("Operación cancelada por el usuario"))
                .when(usuarioDAO).insertarDocentesSimulados();

        // Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class,
                () -> usuarioDAO.insertarDocentesSimulados());
        assertEquals("Operación cancelada por el usuario", exception.getMessage());
        verify(usuarioDAO, times(1)).insertarDocentesSimulados();
    }
}
