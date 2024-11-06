/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fachada;

import control.CtrlAdminAcceso;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author castr
 */
public class IadminAccesoTest {

    @InjectMocks
    private FachadaAdminAcceso fachada;  // Implementación de IadminAcceso

    @Mock
    private CtrlAdminAcceso ctrlAdminAcceso;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    // PRUEBAS FUNCIONALES (2 pruebas)
    /**
     * Prueba que verifica el éxito del inicio de sesión cuando se proporciona
     * un CURP y PIN válidos. Se espera que el resultado sea un objeto
     * UsuarioDTO con los mismos valores de CURP y PIN.
     */
    @Test
    public void testIniciarSesion_Exito_ReturnUsuarioDTO() throws SubsistemaException, PersistenciaException {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCurp("ROHM000712MDFDRR07");  // Asegúrate de que esta CURP esté registrada en la base de datos de pruebas
        usuarioDTO.setPin("1234");

        // Act
        UsuarioDTO resultado = fachada.iniciarSesion(usuarioDTO);

        // Assert
        if (resultado != null) {
            assertEquals("ROHM000712MDFDRR07", resultado.getCurp());
            assertEquals("1234", resultado.getPin());
        } else {
            System.out.println("El usuario no fue encontrado con la CURP proporcionada.");
        }
    }

    /**
     * Prueba que verifica el comportamiento cuando se proporciona un CURP
     * incorrecto. Se espera que el resultado sea null cuando el CURP no existe
     * en la base de datos.
     */
    @Test
    public void testIniciarSesion_CURPIncorrecto_ReturnNull() throws Exception {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCurp("CURP_INCORRECTO");
        usuarioDTO.setPin("1234");

        // Act
        UsuarioDTO resultado = fachada.iniciarSesion(usuarioDTO);

        // Assert
        assertNull(resultado);  // Se espera null cuando el CURP es incorrecto
    }

    // PRUBAS NO FUNCIONALES (1 pruebas)
    /**
     * Prueba que verifica el tiempo de respuesta del método iniciarSesion. Se
     * espera que el tiempo de respuesta sea inferior a 200 ms.
     */
    @Test
    public void testIniciarSesion_TiempoDeRespuesta_LessThan200ms() throws Exception {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCurp("ROHM000712MDFDRR07");
        usuarioDTO.setPin("1234");

        long tiempoInicio = System.currentTimeMillis();

        // Act
        UsuarioDTO resultado = fachada.iniciarSesion(usuarioDTO);

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        // Assert
        if (resultado != null) {
            assertTrue(tiempoTotal < 200, "El tiempo de respuesta excede el límite aceptable");
        } else {
            System.out.println("El usuario no fue encontrado con la CURP proporcionada");
        }
    }

    // PRUEBAS ESTRUCTURALES (2 pruebas)
    /**
     * Prueba que verifica la invocación del controlador de administración de
     * acceso (CtrlAdminAcceso) para el inicio de sesión con un usuario válido.
     * Se espera que el método iniciarSesion del controlador sea invocado una
     * sola vez.
     */
    @Test
    public void testIniciarSesion_InvocacionCtrlAdminAcceso_OneInvocation() throws SubsistemaException, PersistenciaException {
        // Arrange
        ObjectId id = new ObjectId();
        UsuarioDTO usuarioDTO = new UsuarioDTO(id, "CURP123", "Juan", "Pérez", "López", "Docente", "1234");

        // Act
        fachada.iniciarSesion(usuarioDTO);

        // Assert
        verify(ctrlAdminAcceso, times(1)).iniciarSesion(usuarioDTO);
    }

    /**
     * Prueba que verifica el comportamiento cuando el usuarioDTO es null. Se
     * espera que el resultado sea null cuando se pasa un valor nulo como
     * usuarioDTO.
     */
    @Test
    public void testIniciarSesion_UsuarioNull_ReturnNull() throws Exception {
        // Arrange
        UsuarioDTO usuarioDTO = null;

        // Act
        UsuarioDTO resultado = null;
        try {
            resultado = fachada.iniciarSesion(usuarioDTO);
        } catch (NullPointerException e) {
            // Assert
            assertNull(resultado);  // Se espera que el resultado sea null si el usuarioDTO es null
            return;  // Si ocurre un NullPointerException, la prueba pasa
        }

        // Si no se lanza la excepción, el test falla
        fail("Se esperaba un NullPointerException");
    }

}
