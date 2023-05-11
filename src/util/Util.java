package util;

import componentes.Contenedor;


public class Util {
    
    public Contenedor cambiarImagenes(Contenedor c, String s){
        
        c.removeAll();
        c.add(new ImagePanel("img/"+s+"/1.jpg"));
        c.add(new ImagePanel("img/"+s+"/2.jpg"));
        c.add(new ImagePanel("img/"+s+"/3.jpg"));
        
        return c;
    }
    
}
