package listeners;

import componentes.Boton;
import componentes.Contenedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import util.Util;

public class BotonEvent implements ActionListener {

        Boton btpressed;
        Contenedor contenedor;
        Util util;
        
        public BotonEvent(Boton btpressed, Contenedor contenedor){
            this.btpressed=btpressed;
            this.contenedor=contenedor;
        }
        
        
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
//        switch(btpressed.getText())
//        {
//            case "Infantil":
//                if(!btpressed.getPressed()){
//                    btpressed.setPressed(true);
//                    contenedor=util.cambiarImagenes(contenedor);
//                }
//                break;
//            case "Romantica":
//                if(!btpressed.getPressed()){
//                    btpressed.setPressed(true);
//                    contenedor=util.cambiarImagenes(contenedor);
//                }
//                break;
//            case "Terror":
//                if(!btpressed.getPressed()){
//                    btpressed.setPressed(true);
//                    contenedor=util.cambiarImagenes(contenedor);
//                }
//                break;
//        }
;
    }

}
