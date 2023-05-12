package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel{

    private BufferedImage image;

public ImagePanel( ){}
    public ImagePanel(String ruta) {
        setVisible(false);
       try {                
          image = ImageIO.read(new File(ruta));
       } catch (IOException ex) {
            System.out.println("NO SE ENCUENTRA RUTA");
       }
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
}

}
