package componentes;

import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import util.ImagePanel;

public class Contenedor extends JPanel implements Serializable {


    private ImagePanel img1;
    private ImagePanel img2;
    private ImagePanel img3;

    public Contenedor() {
//        super.setLayout(new GridLayout(1, 3));
        
    }

    /**
     * @param img1 the img1 to set
     */
    public void setImg1(ImagePanel img1) {
        add(img1);
        this.img1 = img1;
    }

    /**
     * @param img2 the img2 to set
     */
    public void setImg2(ImagePanel img2) {
        add(img2);
        this.img2 = img2;
    }

    /**
     * @param img3 the img3 to set
     */
    public void setImg3(ImagePanel img3) {
        add(img3);
        this.img3 = img3;
    }

}
