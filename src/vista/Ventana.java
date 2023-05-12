package vista;

import componentes.Boton;
import componentes.Contenedor;
import componentes.Cronometro;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import listeners.*;
import util.*;

public class Ventana extends JFrame implements ActionListener {

    GridBagConstraints gbc;
    JPanel principal = new JPanel();
    Boton btInf, btRom, btTerr, btReset, btExit;
    ArrayList<Boton> listaboton = new ArrayList<Boton>();
    Cronometro crono;
    Contenedor c;
    Util util = new Util();

    public Ventana() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().add(principal);
        this.setSize(800, 600);
        principal.setSize(getWidth(), getHeight());
        principal.setLayout(new GridBagLayout());
        this.getContentPane().add(principal);

        iniciarObjectos();
        iniciarListeners();

        crono.addCronometroEventListener(new ManejadorCronometroEventListener());
        crono.setActivo(false);
        crono.setContenedor(c);

        principal.add(btInf,
                addConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));
        principal.add(btRom,
                addConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));
        principal.add(btTerr,
                addConstraints(4, 0, GridBagConstraints.REMAINDER, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, 1.0, 1.0));

        principal.add(crono.getContenedor(),
                addConstraints(0, 1, GridBagConstraints.REMAINDER, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 20.0));

        principal.add(crono,
                addConstraints(0, 3, GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1.0, 1.0));

        principal.add(btReset,
                addConstraints(0, 4, GridBagConstraints.RELATIVE, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0));
        principal.add(btExit,
                addConstraints(2, 4, GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Boton bt = (Boton) e.getSource();

        switch (bt.getText()) {
            case "Infantil":
                if (!btInf.getPressed()) {
                    btInf.setPressed(true);
                    btRom.setPressed(false);
                    btTerr.setPressed(false);
                    cambiarTema("Comic Sans MS");
                    principal.setBackground(Color.yellow);
                    c.setBackground(Color.yellow);
                }
                break;

            case "Romantica":
                if (!btRom.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(true);
                    btTerr.setPressed(false);
                    cambiarTema("Script MT Bold");
                    principal.setBackground(Color.pink);
                    c.setBackground(Color.pink);
                }
                break;

            case "Terror":
                if (!btTerr.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(false);
                    btTerr.setPressed(true);
                    cambiarTema("Chiller");
                    principal.setBackground(Color.black);
                    c.setBackground(Color.black);
                }
                break;

            case "Reiniciar":
                crono.reiniciarCrono();
                break;

            case "Salir":
                System.exit(0);
                break;
        }
        if (!bt.equals(btReset)) {
            ArrayList<ImagePanel> lista = util.listaImagenes(bt.getText());
            crono.setListaImg(lista);
            if (!crono.getActivo()) {
                crono.startCronometro();
            } else {
                crono.reiniciarCrono();
            }
            c.removeAll();
            crono.setContenedor(c);
            c.revalidate();
            c.repaint();

        }
    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double gridweightx, double gridweighty) {
        gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, gridweightx, gridweighty,
                anchor, fill, new Insets(5, 5, 0, 5), 0, 0);
        return gbc;
    }

    private void iniciarObjectos() {
        ObjectInputStream flujoEntrada;

        try {

            for (int i = 0; i < 5; i++) {
                flujoEntrada = new ObjectInputStream(new FileInputStream("src/componentes/boton.obj"));
                switch (i) {
                    case 0:
                        btInf = (Boton) flujoEntrada.readObject();
                        listaboton.add(btInf);
                        break;
                    case 1:
                        btRom = (Boton) flujoEntrada.readObject();
                        listaboton.add(btRom);
                        break;
                    case 2:
                        btTerr = (Boton) flujoEntrada.readObject();
                        listaboton.add(btTerr);
                        break;
                    case 3:
                        btReset = (Boton) flujoEntrada.readObject();
                        listaboton.add(btReset);
                        break;
                    case 4:
                        btExit = (Boton) flujoEntrada.readObject();
                        listaboton.add(btExit);
                        break;
                }
            }

            btInf.setText("Infantil");
            btRom.setText("Romantica");
            btTerr.setText("Terror");
            btReset.setText("Reiniciar");
            btExit.setText("Salir");

        } catch (Exception e) {
            System.out.println("Error al recuperar la clase BOTON\n" + e.getMessage());
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("src/componentes/contenedor.obj"));
            c = (Contenedor) flujoEntrada.readObject();
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CONTENEDOR");
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("src/componentes/crono.obj"));
            crono = (Cronometro) flujoEntrada.readObject();
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CRONOMETRO");
        }
    }

    private void iniciarListeners() {
        btInf.addActionListener(this);
        btRom.addActionListener(this);
        btTerr.addActionListener(this);
        btReset.addActionListener(this);
        btExit.addActionListener(this);
    }

    private void cambiarTema(String text) {

        crono.setFont(new Font(text, Font.BOLD, 22));
        for (int i = 0; i < 5; i++) {
            listaboton.get(i).setFont(new Font(text, Font.BOLD, 18));
        }

    }

}
