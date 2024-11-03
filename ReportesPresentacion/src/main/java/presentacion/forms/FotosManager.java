/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.forms;

import javax.swing.ImageIcon;

/**
 *
 * @author Oliver Valle
 */
public class FotosManager {
    
    public FotosManager() {
        
    }
    
    public ImageIcon getFoto(String url) {
        return new ImageIcon(url) ;
    }
}
