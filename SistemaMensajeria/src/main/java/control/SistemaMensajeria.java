/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dto.ReporteDTO;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author gamaliel
 */
public class SistemaMensajeria {

    private static final Logger LOG = Logger.getLogger(SistemaMensajeria.class.getName());
    
    
    public boolean enviarMensaje(ReporteDTO reporteDto){
        // Configuración de las propiedades del correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Credenciales de autenticación
        String username = "armenta.gamaliel.cbtis37@gmail.com";
        String password = "bsajncfpxxgtixob";

        // Crear la sesión de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            
            // Crear el mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("armenta.gamaliel.cbtis37@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reporteDto.getAlumno().getEmailTutor()));
            message.setSubject("Notificación de Incidencia" + LocalDate.now());
            message.setText("Buen día. Se notifica por este medio que el alumno "
                    + reporteDto.getAlumno().getNombre() + " " 
                    + reporteDto.getAlumno().getApellidoP() + " " 
                    + reporteDto.getAlumno().getApellidoM() + " presenta el siguiente reporte: "
                    + reporteDto.getMotivo() + ". El reporte cuenta con el estatus de severidad: " 
                    + reporteDto.getNivelIncidencia().toString());

            // Enviar el correo
            Transport transport = session.getTransport("smtp");
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Correo enviado a: " + reporteDto.getAlumno().getEmailTutor());


        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "Hubo un error al enviar la notificaci\u00f3n: {0}", e.getMessage());
            return false;
        }
        return true;
    }
}
