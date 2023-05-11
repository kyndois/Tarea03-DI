package componentes;

import java.awt.*;
import javax.swing.*;

public class Contenedor extends JPanel {

    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    
    public Contenedor(JLabel img1, JLabel img2, JLabel img3){
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        
        super.setLayout(new FlowLayout());
        super.add(img1);
        super.add(img2);
        super.add(img3);
    }

    /**
     * @return the img1
     */
    public JLabel getImg1() {
        return img1;
    }

    /**
     * @param img1 the img1 to set
     */
    public void setImg1(JLabel img1) {
        this.img1 = img1;
    }

    /**
     * @return the img2
     */
    public JLabel getImg2() {
        return img2;
    }

    /**
     * @param img2 the img2 to set
     */
    public void setImg2(JLabel img2) {
        this.img2 = img2;
    }

    /**
     * @return the img3
     */
    public JLabel getImg3() {
        return img3;
    }

    /**
     * @param img3 the img3 to set
     */
    public void setImg3(JLabel img3) {
        this.img3 = img3;
    }
    

}
