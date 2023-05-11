package vista;

import componentes.Boton;
import java.awt.*;
import javax.swing.*;


public class Ventana extends JFrame {

    GridBagConstraints gbc;

    public Ventana() {
        this.getContentPane().setLayout(new GridBagLayout());
        this.setSize(600, 600);

        GridBagConstraints constraints = new GridBagConstraints();

        Boton btInf = new Boton("Infantil");
        Boton btRom = new Boton("Romantica");
        Boton btTerr = new Boton("Terror");
        Boton btReset = new Boton("Reiniciar");
        Boton btExit = new Boton("Salir");

        this.getContentPane().add(btInf, addConstraints(0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        this.getContentPane().add(btRom, addConstraints(0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE));
        this.getContentPane().add(btTerr, addConstraints(0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE));

    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
        gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets(5, 5, 0, 5), 0, 0);
        return gbc;
    }
}
