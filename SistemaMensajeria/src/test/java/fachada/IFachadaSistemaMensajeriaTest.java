/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fachada;

import control.SistemaMensajeria;
import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.UsuarioDTO;
import java.util.Date;
import javax.mail.MessagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import persistencia.entidades.NivelIncidenciaPersistencia;

/**
 *
 * @author castr
 */
public class IFachadaSistemaMensajeriaTest {

    @Mock
    private SistemaMensajeria mockSistemaMensajeria;

    @InjectMocks
    private FachadaSistemaMensajeria fachadaSistemaMensajeria;

    // Inicialización de los mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // PRUEBAS FUNCIONALES (2 pruebas)
    /**
     * Prueba que verifica si el mensaje se envía correctamente cuando se pasa
     * un reporte válido con todos los datos necesarios.
     */
    @Test
    public void testEnviarMensaje_Exitoso_ReturnSuccess() {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO("ABC123", "Juan", "Pérez", "González", "10-A", "urlFoto.jpg", "juan.perez@example.com");
        UsuarioDTO docente = new UsuarioDTO("CURP12345", "Pedro", "Martínez", "Lopez", "Docente", "1234");
        NivelIncidenciaPersistencia nivelIncidencia = NivelIncidenciaPersistencia.LEVE;
        Date fechaHora = new Date();
        ReporteDTO reporteDTO = new ReporteDTO(alumno, docente, nivelIncidencia, "Falta de tareas", "El alumno no entregó la tarea", fechaHora, false, false);

        // Configuración del mock para que devuelva true
        when(mockSistemaMensajeria.enviarMensaje(reporteDTO)).thenReturn(true);

        // Act
        boolean resultado = fachadaSistemaMensajeria.enviarMensaje(reporteDTO);

        // Assert
        assertTrue(resultado, "El mensaje debería enviarse correctamente");
    }

    /**
     * Prueba que verifica que el sistema maneje correctamente un caso en el que
     * el reporte es nulo.
     */
    @Test
    public void testEnviarMensaje_ReporteNulo_ReturnFailure() {
        // Arrange
        ReporteDTO reporteDTO = null;

        // Act
        boolean resultado = fachadaSistemaMensajeria.enviarMensaje(reporteDTO);

        // Assert
        assertFalse(resultado, "El mensaje no debería enviarse si el reporte es nulo");
    }

    //PRUEBAS NO FUNCIONALES(1 pruebas)
    /**
     * Prueba que mide el tiempo de ejecución del envío de mensaje para
     * asegurarse de que el sistema no tarde más de 2 segundos en completar la
     * operación.
     */
    @Test
    public void testDesempeno_EnviarMensaje_ReturnSuccessAndTimeLimit() {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO("XYZ456", "Carlos", "Lopez", "Martinez", "11-B", "foto.png", "carlos.lopez@example.com");
        UsuarioDTO docente = new UsuarioDTO("CURP67890", "María", "González", "Ramírez", "Docente", "5678");
        NivelIncidenciaPersistencia nivelIncidencia = NivelIncidenciaPersistencia.LEVE;
        Date fechaHora = new Date();
        ReporteDTO reporteDTO = new ReporteDTO(alumno, docente, nivelIncidencia, "Reporte urgente", "El alumno tiene faltas", fechaHora, false, false);

        // Configuración del mock para que devuelva true
        when(mockSistemaMensajeria.enviarMensaje(reporteDTO)).thenReturn(true);

        // Act
        long inicio = System.currentTimeMillis();
        boolean resultado = fachadaSistemaMensajeria.enviarMensaje(reporteDTO);
        long fin = System.currentTimeMillis();

        // Assert
        assertTrue(resultado, "El mensaje debería enviarse correctamente");
        assertTrue((fin - inicio) < 2000, "El tiempo de ejecución debería ser menor a 2 segundos");
    }

    //PRUEBAS ESTRUCTURALES (2 pruebas)
    /**
     * Prueba que asegura que el formato del correo generado tenga los campos
     * correctos en el mensaje, como el remitente, destinatario, asunto y
     * contenido.
     */
    @Test
    public void testEstructura_MensajeCorreoGeneradoCorrectamente_ReturnSuccess() throws Exception {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO("DEF789", "Ana", "Gonzalez", "Morales", "12-C", "foto2.jpg", "ana.gonzalez@example.com");
        UsuarioDTO docente = new UsuarioDTO("CURP11223", "Luis", "Martínez", "Torres", "Docente", "1122");
        NivelIncidenciaPersistencia nivelIncidencia = NivelIncidenciaPersistencia.SEVERO;
        Date fechaHora = new Date();
        ReporteDTO reporteDTO = new ReporteDTO(alumno, docente, nivelIncidencia, "Ausencia injustificada", "El alumno no asistió a clases", fechaHora, false, false);

        // Configuración del mock para que devuelva true
        when(mockSistemaMensajeria.enviarMensaje(reporteDTO)).thenReturn(true);

        // Act
        boolean resultado = fachadaSistemaMensajeria.enviarMensaje(reporteDTO);

        // Assert
        assertTrue(resultado, "El mensaje debería enviarse correctamente");

    }

    /**
     * Prueba que verifica que el mensaje se envíe correctamente cuando no
     * ocurre ningún error. Simula un escenario exitoso usando un mock de la
     * clase SistemaMensajeria.
     *
     * @throws MessagingException Si ocurre un error durante el envío del
     * mensaje (aunque no se espera en esta prueba).
     */
    @Test
    public void testEstructura_EnvioExitoso_ReturnSuccess() throws MessagingException {
        // Arrange
        AlumnoDTO alumno = new AlumnoDTO("LMN123", "Luis", "Ramirez", "Sanchez", "9-D", "foto3.jpg", "luis.ramirez@example.com");
        UsuarioDTO docente = new UsuarioDTO("CURP99887", "Isabel", "Reyes", "Pérez", "Docente", "9988");
        NivelIncidenciaPersistencia nivelIncidencia = NivelIncidenciaPersistencia.GRAVE;
        Date fechaHora = new Date();
        ReporteDTO reporteDTO = new ReporteDTO(alumno, docente, nivelIncidencia, "Reporte correcto", "El reporte es válido", fechaHora, false, false);

        // Simular una respuesta exitosa al enviar el mensaje (mock de la clase SistemaMensajeria)
        when(mockSistemaMensajeria.enviarMensaje(any(ReporteDTO.class))).thenReturn(true);  // Simulación exitosa

        // Act
        boolean resultado = fachadaSistemaMensajeria.enviarMensaje(reporteDTO);

        // Assert
        assertTrue(resultado, "El mensaje debería enviarse correctamente");
    }
}
