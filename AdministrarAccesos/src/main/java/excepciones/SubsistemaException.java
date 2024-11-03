/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Oliver Valle
 */
public class SubsistemaException extends Exception {

    public SubsistemaException() {
    }

    public SubsistemaException(String message) {
        super(message);
    }

    public SubsistemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubsistemaException(Throwable cause) {
        super(cause);
    }

    public SubsistemaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
