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
public class UsuarioDTO {
    private ObjectId _id ;
    private String curp, nombre, apellidoP, apellidoM, rol, pin;
    
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(ObjectId _id, String curp, String nombre, String apellidoP, String apellidoM, String rol, String pin) {
        this._id = _id;
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.rol = rol;
        this.pin = pin;
    }
    
    public UsuarioDTO(String curp, String nombre, String apellidoP, String apellidoM, String rol, String pin) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.rol = rol;
        this.pin = pin;
    }
    
    public UsuarioDTO(String curp, String nombre, String apellidoP, String apellidoM) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
