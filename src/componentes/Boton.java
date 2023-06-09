package componentes;

import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.Border;

public class Boton extends JButton implements Serializable{

    private boolean pressed = false;

    private final Color backPressed = Color.BLUE;
    private final Color backNormal = Color.lightGray;
    private final Color textPressed = Color.WHITE;
    private final Color textNormal = Color.BLACK;
    private final Border borderPressed = BorderFactory.createLoweredBevelBorder();
    private final Border borderNormal = BorderFactory.createRaisedBevelBorder();

    public Boton() {
        //Atributos guardados al crear el objeto
        
//        this.pressed = false;
//        super.setForeground(textNormal);
//        super.setBackground(backNormal);
//        super.setBorder(borderNormal);
//        super.setMargin(new Insets(0,0,20,20));
//        super.setPreferredSize(new Dimension(100, 25));
    }

    public void setPressed(Boolean pressed) {
        this.pressed = pressed;
        if (pressed) {
            super.setForeground(textPressed);
            super.setBackground(backPressed);
            super.setBorder(borderPressed);
        } else {
            super.setForeground(textNormal);
            super.setBackground(backNormal);
            super.setBorder(borderNormal);
        }
    }

    public boolean getPressed() {
        return this.pressed;
    }

}
