/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import org.bson.types.ObjectId;

/**
 *
 * @author Oliver Valle
 */
public class AlumnoDTO {
    private ObjectId _id;
    private String curp, nombre, apellidoP, apellidoM, gradoGrupo, urlFoto, emailTutor ;

    public AlumnoDTO() {
    }
    
    public AlumnoDTO(ObjectId _id, String curp, String nombre, String apellidoP, String apellidoM, String gradoGrupo, String urlFoto, String emailTutor) {
        this._id = _id;
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.gradoGrupo = gradoGrupo;
        this.urlFoto = urlFoto;
        this.emailTutor = emailTutor;
    }
    
    public AlumnoDTO(String curp, String nombre, String apellidoP, String apellidoM, String gradoGrupo, String urlFoto, String emailTutor) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.gradoGrupo = gradoGrupo;
        this.urlFoto = urlFoto;
        this.emailTutor = emailTutor;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
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
}
