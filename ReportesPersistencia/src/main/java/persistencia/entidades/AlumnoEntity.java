/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author Oliver Valle
 */
public class AlumnoEntity {
    
    private ObjectId _id ;
    private String CURP, nombre, apellidoP, apellidoM, gradoGrupo, emailTutor, urlFoto ;

    public AlumnoEntity(){
        
    }
    
    public AlumnoEntity(ObjectId _id, String CURP, String nombre, String apellidoP, String apellidoM, String gradoGrupo, String emailTutor, String urlFoto) {
        this._id = _id;
        this.CURP = CURP;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.gradoGrupo = gradoGrupo;
        this.emailTutor = emailTutor;
        this.urlFoto = urlFoto ;
    }

    public AlumnoEntity(String CURP, String nombre, String apellidoP, String apellidoM, String gradoGrupo, String emailTutor, String urlFoto) {
        this.CURP = CURP;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.gradoGrupo = gradoGrupo;
        this.emailTutor = emailTutor;
        this.urlFoto = urlFoto ;
    }
    
    
    public ObjectId getId() {
        return _id;
    }

    public String getCURP() {
        return CURP;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public String getGradoGrupo() {
        return gradoGrupo;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public void setGradoGrupo(String gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    
    
}
