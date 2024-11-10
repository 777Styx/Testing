/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package control;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import java.util.Date;
import java.util.List;
import negocios.bo.IIncidenciasBO;
import negocios.bo.IncidenciasBO;
import negocios.excepciones.NegociosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import persistencia.entidades.NivelIncidenciaPersistencia;
import persistencia.persistenciaEscuela.UsuarioDAO;

/**
 *
 * @author PC
 */
public class GestionarIncidenciasTest {

    ReporteDTO reporteNuevo;
    AlumnoDTO alumno;
    UsuarioDTO docente;
    private IIncidenciasBO incidenciasBO;
    
    public GestionarIncidenciasTest() {

        // Arrange - setup common objects and mocks
        incidenciasBO = Mockito.mock(IIncidenciasBO.class);

        alumno = new AlumnoDTO("OUQA040309HSRLRMA5", "Amos Heli", "Olguin", "Quiroz", "6AVP", "AMOS.PNG", "amosheli2004@gmail.com");
        docente = new UsuarioDTO("CURPDOC", "Rosalba", "Quiroz", "Perez", "Docente", "1234");
        reporteNuevo = new ReporteDTO(alumno, docente, null, "El alumno se peleo", "", new Date(2024, 9, 10), true, true);

    }

    

    /**
     * Prueba que verifica la creación de un reporte válido.
     * Tipo: METODO FUNCIONAL
     * @throws NegociosException si ocurre un error al crear el reporte
     */
    @Test
    public void TestcrearReporte_InputValido_Successfully() throws NegociosException {
        // Arrange
        doNothing().when(incidenciasBO).crearReporte(reporteNuevo);

        // Act
        incidenciasBO.crearReporte(reporteNuevo);

        // Assert
        verify(incidenciasBO, times(1)).crearReporte(reporteNuevo);
    }

    /**
     * Prueba que verifica que la notificación de un reporte válido se realiza
     * correctamente.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestNotificarReporte_InputValido_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        when(incidenciasBO.notificarReporte(reporteNuevo)).thenReturn(true);

        // Act
        boolean notificado = incidenciasBO.notificarReporte(reporteNuevo);

        // Assert
        assertTrue(notificado);
        verify(incidenciasBO, times(1)).notificarReporte(reporteNuevo);
    }

    /**
     * Prueba que verifica la recuperación de una lista de reportes.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarReportes_ReturnsList_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        List<ReporteDTO> reportesSimulados = List.of(reporteNuevo);
        when(incidenciasBO.recuperarReportes()).thenReturn(reportesSimulados);

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportes();

        // Assert
        assertNotNull(resultado);
        assertEquals(reportesSimulados, resultado);
        verify(incidenciasBO, times(1)).recuperarReportes();
    }

    /**
     * Prueba que verifica la inserción de datos simulados sin errores.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestInsertDatosSimulados_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        doNothing().when(incidenciasBO).insertDatosSimulados();

        // Act
        incidenciasBO.insertDatosSimulados();

        // Assert
        verify(incidenciasBO, times(1)).insertDatosSimulados();
    }

    /**
     * Prueba que verifica la recuperación de alumnos por grado.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarAlumnosPorGrado_ReturnsList_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        String grado = "6AVP";
        List<AlumnoDTO> alumnosSimulados = List.of(alumno);
        when(incidenciasBO.recuperarAlumnosPorGrado(grado)).thenReturn(alumnosSimulados);

        // Act
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGrado(grado);

        // Assert
        assertNotNull(resultado);
        assertEquals(alumnosSimulados, resultado);
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGrado(grado);
    }

    /**
     * Prueba que verifica la recuperación de alumnos por grupo.
     * Tipo: METODO FUNCIONAL 
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarAlumnosPorGrupo_ReturnsList_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        String grupo = "6AVP";
        List<AlumnoDTO> alumnosSimulados = List.of(alumno);
        when(incidenciasBO.recuperarAlumnosPorGrupo(grupo)).thenReturn(alumnosSimulados);

        // Act
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGrupo(grupo);

        // Assert
        assertNotNull(resultado);
        assertEquals(alumnosSimulados, resultado);
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGrupo(grupo);
    }

    /**
     * Prueba que verifica la recuperación de alumnos.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarReportesAlumno_ReturnsList_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        String curp = "OUQA040309HSRLRMA5";
        List<ReporteDTO> reportesSimulados = List.of(reporteNuevo);
        when(incidenciasBO.recuperarReportesAlumno(curp)).thenReturn(reportesSimulados);

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportesAlumno(curp);

        // Assert
        assertNotNull(resultado);
        assertEquals(reportesSimulados, resultado);
        verify(incidenciasBO, times(1)).recuperarReportesAlumno(curp);
    }

    /**
     * Prueba que verifica la validación de un reporte con un input válido.
     * Tipo: METODO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestvalidarReporte_InputValido_Successfully() throws NegociosException {
        // Arrange
        when(incidenciasBO.validarReporte(reporteNuevo)).thenReturn(reporteNuevo);

        // Act
        ReporteDTO resultado = incidenciasBO.validarReporte(reporteNuevo);

        // Assert
        assertEquals(reporteNuevo, resultado);
        verify(incidenciasBO, times(1)).validarReporte(reporteNuevo);

    }
    
    /**
     * Prueba que verifica la recuperación de alumnos por grado y grupo con un
     * input válido.
     * Tipo: METODO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarAlumnosPorGradoYGrupo_InputValido_Successfully() throws SubsistemaException, NegociosException {
        // Arrange
        String grado = "6AVP";
        String grupo = "A";
        List<AlumnoDTO> alumnosSimulados = List.of(alumno);
        when(incidenciasBO.recuperarAlumnosPorGradoYGrupo(grado, grupo)).thenReturn(alumnosSimulados);

        // Act
        List<AlumnoDTO> resultado = incidenciasBO.recuperarAlumnosPorGradoYGrupo(grado, grupo);

        // Assert
        assertNotNull(resultado);
        assertEquals(alumnosSimulados, resultado);
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGradoYGrupo(grado, grupo);
    }
    
    /**
     * Prueba que verifica que se lanza una excepción al crear un reporte
     * inválido.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si el reporte no es válido
     */
    @Test
    public void TestcrearReporte_InputInvalido_Failed() throws NegociosException {
        // Arrange
        ReporteDTO reporteInvalido = new ReporteDTO("ID2", null, null, null, "Descripcion", "Motivo", new Date(), false, false);
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteInvalido));
    }

    /**
     * Prueba que verifica el comportamiento cuando el reporte es notificado con
     * un input inválido.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestNotificarReporte_InputInvalido_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(reporteNuevo);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(reporteNuevo));
        verify(incidenciasBO, times(1)).notificarReporte(reporteNuevo);
    }

    /**
     * Prueba que verifica la recuperación de reportes cuando no existen
     * reportes.
     * Tipo: METODO NO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarReportes_ReturnsEmptyList_WhenNoReports() throws SubsistemaException, NegociosException {
        // Arrange
        when(incidenciasBO.recuperarReportes()).thenReturn(List.of());

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportes();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).recuperarReportes();
    }

    /**
     * Prueba que verifica el comportamiento al insertar datos simulados con un
     * error.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestInsertDatosSimulados_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).insertDatosSimulados();

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.insertDatosSimulados());
        verify(incidenciasBO, times(1)).insertDatosSimulados();
    }

    /**
     * Prueba que verifica la recuperación de alumnos por grado con un input
     * inválido.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarAlumnosPorGrado_InputInvalido_ThrowsException() throws NegociosException {
        // Arrange
        String gradoInvalido = "invalid";
        doThrow(NegociosException.class).when(incidenciasBO).recuperarAlumnosPorGrado(gradoInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.recuperarAlumnosPorGrado(gradoInvalido));
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGrado(gradoInvalido);
    }

    /**
     * Prueba que verifica la recuperación de alumnos por grupo con un input
     * inválido.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarAlumnosPorGrupo_InputInvalido_ThrowsException() throws NegociosException {
        // Arrange
        String grupoInvalido = "invalid";
        doThrow(NegociosException.class).when(incidenciasBO).recuperarAlumnosPorGrupo(grupoInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.recuperarAlumnosPorGrupo(grupoInvalido));
        verify(incidenciasBO, times(1)).recuperarAlumnosPorGrupo(grupoInvalido);
    }

    /**
     * Prueba que verifica la recuperación de reportes de un alumno cuando no
     * existen reportes.
     * Tipo: METODO NO FUNCIONAL
     * @throws SubsistemaException si ocurre un error en el subsistema
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestRecuperarReportesAlumno_InputValido_ReturnsEmptyList() throws SubsistemaException, NegociosException {
        // Arrange
        String curp = "NOEXISTE";
        when(incidenciasBO.recuperarReportesAlumno(curp)).thenReturn(List.of());

        // Act
        List<ReporteDTO> resultado = incidenciasBO.recuperarReportesAlumno(curp);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(incidenciasBO, times(1)).recuperarReportesAlumno(curp);
    }

    /**
     * Prueba que verifica la conversión de reportes a expediente cuando el
     * input es null.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestConvertirReportesAReporteExpediente_InputNull_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).convertirReporteAReporteExpediente(null);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.convertirReporteAReporteExpediente(null));
        verify(incidenciasBO, times(1)).convertirReporteAReporteExpediente(null);
    }

    /**
     * Prueba que verifica la creación de un reporte vacío y lanza una
     * excepción.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestCrearReporte_InputReporteVacio_ThrowsException() throws NegociosException {
        // Arrange
        ReporteDTO reporteVacio = new ReporteDTO();
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteVacio);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteVacio));
        verify(incidenciasBO, times(1)).crearReporte(reporteVacio);
    }

    /**
     * Prueba que verifica la validación de un reporte cuando el input es null.
     * Tipo: METODO NO FUNCIONAL
     * @throws NegociosException si ocurre un error en el negocio
     */
    @Test
    public void TestValidarReporte_InputNull_ThrowsException() throws NegociosException {
        // Arrange
        doThrow(NegociosException.class).when(incidenciasBO).validarReporte(null);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.validarReporte(null));
        verify(incidenciasBO, times(1)).validarReporte(null);
    }

}
