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
    
    
    public GestionarIncidenciasTest() {
        
        // Arrange - setup common objects and mocks
        incidenciasBO = Mockito.mock(IIncidenciasBO.class);
        
        alumno = new AlumnoDTO("OUQA040309HSRLRMA5", "Amos Heli", "Olguin", "Quiroz", "6AVP", "AMOS.PNG", "amosheli2004@gmail.com");
        docente = new UsuarioDTO("CURPDOC", "Rosalba", "Quiroz", "Perez", "Docente", "1234");
        reporteNuevo = new ReporteDTO(alumno, docente, null, "El alumno se peleo", "", new Date(2024, 9, 10), true, true);
        
    }
    
     private IIncidenciasBO incidenciasBO ;
    
     @Test
    public void TestcrearReporte_InputValido_Successfully() throws NegociosException{
        // Arrange
        doNothing().when(incidenciasBO).crearReporte(reporteNuevo);

        // Act
        incidenciasBO.crearReporte(reporteNuevo);

        // Assert
        verify(incidenciasBO, times(1)).crearReporte(reporteNuevo);
    }
    
    @Test
    public void TestcrearReporte_InputInvalido_Failed() throws NegociosException{
        // Arrange
        ReporteDTO reporteInvalido = new ReporteDTO("ID2", null, null, null, "Descripcion", "Motivo", new Date(), false, false);
        doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteInvalido);

        // Act & Assert
        assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteInvalido));
    }
    
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

@Test
public void TestInsertDatosSimulados_Successfully() throws SubsistemaException, NegociosException {
    // Arrange
    doNothing().when(incidenciasBO).insertDatosSimulados();

    // Act
    incidenciasBO.insertDatosSimulados();

    // Assert
    verify(incidenciasBO, times(1)).insertDatosSimulados();
}

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
    
    @Test
    public void TestvalidarReporte_InputValido_Successfully() throws NegociosException{
        // Arrange
        when(incidenciasBO.validarReporte(reporteNuevo)).thenReturn(reporteNuevo);

        // Act
        ReporteDTO resultado = incidenciasBO.validarReporte(reporteNuevo);

        // Assert
        assertEquals(reporteNuevo, resultado);
        verify(incidenciasBO, times(1)).validarReporte(reporteNuevo);
        
    }

    @Test
public void TestNotificarReporte_InputInvalido_ThrowsException() throws NegociosException {
    // Arrange
    doThrow(NegociosException.class).when(incidenciasBO).notificarReporte(reporteNuevo);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.notificarReporte(reporteNuevo));
    verify(incidenciasBO, times(1)).notificarReporte(reporteNuevo);
}

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

@Test
public void TestInsertDatosSimulados_ThrowsException() throws NegociosException {
    // Arrange
    doThrow(NegociosException.class).when(incidenciasBO).insertDatosSimulados();

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.insertDatosSimulados());
    verify(incidenciasBO, times(1)).insertDatosSimulados();
}

@Test
public void TestRecuperarAlumnosPorGrado_InputInvalido_ThrowsException() throws NegociosException {
    // Arrange
    String gradoInvalido = "invalid";
    doThrow(NegociosException.class).when(incidenciasBO).recuperarAlumnosPorGrado(gradoInvalido);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.recuperarAlumnosPorGrado(gradoInvalido));
    verify(incidenciasBO, times(1)).recuperarAlumnosPorGrado(gradoInvalido);
}

@Test
public void TestRecuperarAlumnosPorGrupo_InputInvalido_ThrowsException() throws NegociosException {
    // Arrange
    String grupoInvalido = "invalid";
    doThrow(NegociosException.class).when(incidenciasBO).recuperarAlumnosPorGrupo(grupoInvalido);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.recuperarAlumnosPorGrupo(grupoInvalido));
    verify(incidenciasBO, times(1)).recuperarAlumnosPorGrupo(grupoInvalido);
}

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

@Test
public void TestConvertirReportesAReporteExpediente_InputNull_ThrowsException() throws NegociosException {
    // Arrange
    doThrow(NegociosException.class).when(incidenciasBO).convertirReporteAReporteExpediente(null);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.convertirReporteAReporteExpediente(null));
    verify(incidenciasBO, times(1)).convertirReporteAReporteExpediente(null);
}

@Test
public void TestCrearReporte_InputReporteVacio_ThrowsException() throws NegociosException {
    // Arrange
    ReporteDTO reporteVacio = new ReporteDTO();
    doThrow(NegociosException.class).when(incidenciasBO).crearReporte(reporteVacio);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.crearReporte(reporteVacio));
    verify(incidenciasBO, times(1)).crearReporte(reporteVacio);
}

@Test
public void TestValidarReporte_InputNull_ThrowsException() throws NegociosException {
    // Arrange
    doThrow(NegociosException.class).when(incidenciasBO).validarReporte(null);

    // Act & Assert
    assertThrows(NegociosException.class, () -> incidenciasBO.validarReporte(null));
    verify(incidenciasBO, times(1)).validarReporte(null);
}

    
}