package componentes;

import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import util.ImagePanel;

public class Contenedor extends JPanel implements Serializable{

    private ImagePanel img1;
    private ImagePanel img2;
    private ImagePanel img3;

    public Contenedor() {
        super.setLayout(new GridLayout(1,3));
        super.add(new JLabel(""));
        super.add(new JLabel("BIENVENIDO"));
        super.add(new JLabel(""));
        super.setBackground(Color.red);
    }

    public Contenedor(ImagePanel img1, ImagePanel img2, ImagePanel img3) {
        
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;

        add(img1);
        add(img2);
        add(img3);
        
    }

    /**
     * @return the img1
     */
    public ImagePanel getImg1() {
        return img1;
    }

    /**
     * @param img1 the img1 to set
     */
    public void setImg1(ImagePanel img1) {
        this.img1 = img1;
    }

    /**
     * @return the img2
     */
    public ImagePanel getImg2() {
        return img2;
    }

    /**
     * @param img2 the img2 to set
     */
    public void setImg2(ImagePanel img2) {
        this.img2 = img2;
    }

    /**
     * @return the img3
     */
    public ImagePanel getImg3() {
        return img3;
    }

    /**
     * @param img3 the img3 to set
     */
    public void setImg3(ImagePanel img3) {
        this.img3 = img3;
    }

}
