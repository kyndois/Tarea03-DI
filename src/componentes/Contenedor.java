package componentes;

import java.awt.*;
import javax.swing.*;
import util.ImagePanel;

public class Contenedor extends JPanel {

    private ImagePanel img1;
    private ImagePanel img2;
    private ImagePanel img3;

    public Contenedor() {
        super.setLayout(new FlowLayout());
        super.add(new JLabel("BIENVENIDO"));
    }

    public Contenedor(ImagePanel img1, ImagePanel img2, ImagePanel img3) {
        super.removeAll();

        this.img1 = img1;
        img1.setBackground(Color.yellow);
        img1.setOpaque(true);
        this.img2 = img2;
        img2.setBackground(Color.red);
        img2.setOpaque(true);
        this.img3 = img3;
        img3.setBackground(Color.orange);
        img3.setOpaque(true);

        super.add(img1);
        super.add(img2);
        super.add(img3);
        
        super.revalidate();
        super.repaint();
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
