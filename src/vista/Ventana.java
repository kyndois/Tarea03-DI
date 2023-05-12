package vista;

import componentes.Boton;
import componentes.Contenedor;
import componentes.Cronometro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import listeners.*;
import util.Util;

public class Ventana extends JFrame implements ActionListener {

    GridBagConstraints gbc;

    Boton btInf;
    Boton btRom;
    Boton btTerr;
    Boton btReset;
    Boton btExit;

    Cronometro crono;
    Contenedor c = new Contenedor();
    Util util = new Util();

    public Ventana() {

        btInf.addActionListener(this);
        btRom.addActionListener(this);
        btTerr.addActionListener(this);
        btReset.addActionListener(this);
        btExit.addActionListener(this);

        crono = new Cronometro();
        crono.addCronometroEventListener(new ManejadorCronometroEventListener());
        crono.setActivo(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridBagLayout());
        this.setSize(600, 600);

        GridBagConstraints constraints = new GridBagConstraints();

        this.getContentPane().add(btInf,
                addConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));
        this.getContentPane().add(btRom,
                addConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));
        this.getContentPane().add(btTerr,
                addConstraints(4, 0, GridBagConstraints.REMAINDER, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));

        this.getContentPane().add(c,
                addConstraints(0, 1, GridBagConstraints.REMAINDER, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 20.0));

        this.getContentPane().add(crono,
                addConstraints(0, 3, GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1.0, 1.0));

        this.getContentPane().add(btReset,
                addConstraints(0, 4, GridBagConstraints.RELATIVE, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0));
        this.getContentPane().add(btExit,
                addConstraints(2, 4, GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Boton bt = (Boton) e.getSource();
        crono.setActivo(true);

        switch (bt.getText()) {
            case "Infantil":
                if (!btInf.getPressed()) {
                    btInf.setPressed(true);
                    btRom.setPressed(false);
                    btTerr.setPressed(false);
                    c = util.cambiarImagenes(c, "infantil");
                    crono.reiniciarCrono();
                    crono.setFont(new Font("Comic Sans MS", Font.BOLD, 22));

                }
                break;
            case "Romantica":
                if (!btRom.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(true);
                    btTerr.setPressed(false);
                    c = util.cambiarImagenes(c, "romantica");

                }
                break;
            case "Terror":
                if (!btTerr.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(false);
                    btTerr.setPressed(true);
                    c = util.cambiarImagenes(c, "terror");
                }
                break;
            case "Salir":
                System.exit(0);

        }

        c.revalidate();
        c.repaint();

    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double gridweightx, double gridweighty) {
        gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, gridweightx, gridweighty,
                anchor, fill, new Insets(5, 5, 0, 5), 0, 0);
        return gbc;
    }

    private void iniciarObjectos() {
        ObjectInputStream flujoEntrada;
        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("boton.obj"));
            String str = (String) flujoEntrada.readObject();
            System.out.println(str);
            btInf = (Boton) flujoEntrada.readObject();
            btRom = (Boton) flujoEntrada.readObject();
            btTerr = (Boton) flujoEntrada.readObject();
            btReset = (Boton) flujoEntrada.readObject();
            btExit = (Boton) flujoEntrada.readObject();

            btInf.setText("Infantil");
            btRom.setText("Romantica");
            btTerr.setText("Terror");
            btReset.setText("Reiniciar");
            btExit.setText("Salir");
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase BOTON");
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("contenedor.obj"));
            String str = (String) flujoEntrada.readObject();
            System.out.println(str);
            c = (Contenedor) flujoEntrada.readObject();
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CONTENEDOR");
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("crono.obj"));
            String str = (String) flujoEntrada.readObject();
            System.out.println(str);
            crono = (Cronometro) flujoEntrada.readObject();
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CRONOMETRO");
        }
    }
}
