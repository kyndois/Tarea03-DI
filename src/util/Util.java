package util;

import componentes.Contenedor;


public class Util {
    
    public Contenedor cambiarImagenes(Contenedor c, String s){
        
        c.removeAll();
        c.add(new ImagePanel("src/img/"+s+"/1.jpg",c.getWidth(),c.getHeight()));
        c.add(new ImagePanel("src/img/"+s+"/2.jpg",c.getWidth(),c.getHeight()));
        c.add(new ImagePanel("src/img/"+s+"/3.jpg",c.getWidth(),c.getHeight()));
        
        return c;
    }
    
}
