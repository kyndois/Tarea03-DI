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
    String lastbt = "";
    final JLabel firma = new JLabel("Imagenes con licencia de uso por https://www.pexels.com/");
    public Ventana() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firma.setForeground(Color.red);
        firma.setFont(new Font("Times New Roman", Font.BOLD, 12));
        this.getContentPane().add(principal);
        this.setSize(800, 600);
        principal.setSize(getWidth(), getHeight());
        principal.setLayout(new GridBagLayout());
        principal.setBackground(Color.cyan);
        this.getContentPane().add(principal);

        iniciarObjectos();
        iniciarListeners();

        crono.addCronometroEventListener(new ManejadorCronometroEventListener());
        crono.setActivo(false);

        principal.add(btInf,
                addConstraints(0, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                        1.0, 1.0, 0, 0, 0, 5));
        principal.add(btRom,
                addConstraints(2, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                        1.0, 1.0, 0, 0, 0, 5));
        principal.add(btTerr,
                addConstraints(4, 0, GridBagConstraints.REMAINDER, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                        1.0, 1.0, 0, 0, 0, 5));
        principal.add(c,
                addConstraints(0, 1, GridBagConstraints.REMAINDER, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        1.0, 20.0, 5, 5, 0, 5));
        principal.add(crono,
                addConstraints(1, 4, GridBagConstraints.RELATIVE, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        1.0, 1.0, 0, 0, 0, 0));
        principal.add(btReset,
                addConstraints(0, 5, GridBagConstraints.RELATIVE, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        1.0, 1.0, 0, 0, 0, 0));
        principal.add(btExit,
                addConstraints(2, 5, GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        1.0, 1.0, 0, 0, 0, 0));
        principal.add(firma,
                addConstraints(0, 6, 5, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                        1.0, 1.0, 0, 10, 0, 0));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!btReset.isEnabled()) {
            btReset.setEnabled(true);
        }
        Boton bt = (Boton) e.getSource();

        switch (bt.getText()) {
            case "INFANTIL":
                if (!btInf.getPressed()) {
                    btInf.setPressed(true);
                    btRom.setPressed(false);
                    btTerr.setPressed(false);
                    cambiarTema("Comic Sans MS");
                    principal.setBackground(Color.yellow);
                    c.setBackground(Color.yellow);
                    lastbt = "Infantil";
                }
                break;

            case "ROMANTICA":
                if (!btRom.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(true);
                    btTerr.setPressed(false);
                    cambiarTema("Script MT Bold");
                    principal.setBackground(Color.pink);
                    c.setBackground(Color.pink);
                    lastbt = "Romantica";
                }
                break;

            case "TERROR":
                if (!btTerr.getPressed()) {
                    btInf.setPressed(false);
                    btRom.setPressed(false);
                    btTerr.setPressed(true);
                    cambiarTema("Chiller");
                    principal.setBackground(Color.black);
                    c.setBackground(Color.black);
                    lastbt = "Terror";

                }
                break;

            case "REINICIAR":
                crono.reiniciarCrono();
                break;

            case "SALIR":
                System.exit(0);
                break;
        }

        ArrayList<ImagePanel> lista = util.listaImagenes(lastbt);
        crono.setListaImg(lista);
        if (!bt.equals(btReset)) {
            if (!crono.getActivo()) {
                crono.startCronometro();
            } else {
                crono.reiniciarCrono();
            }
        }
        c.removeAll();
        crono.setContenedor(c);
        c.revalidate();
        c.repaint();
    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double gridweightx, double gridweighty, int topi, int lefti, int boti, int righti) {
        gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, gridweightx, gridweighty,
                anchor, fill, new Insets(topi, lefti, boti, righti), 0, 0);
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

            btInf.setText("INFANTIL");
            btRom.setText("ROMANTICA");
            btTerr.setText("TERROR");
            btReset.setText("REINICIAR");
            btReset.setEnabled(false);
            btExit.setText("SALIR");

        } catch (Exception e) {
            System.out.println("Error al recuperar la clase BOTON\n" + e.getMessage());
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("src/componentes/contenedor.obj"));
            c = (Contenedor) flujoEntrada.readObject();
            c.add(new ImagePanel("src/img/Inicio/welcome.jpg"));

            c.revalidate();
            c.repaint();
        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CONTENEDOR\n" + e.getMessage());
        }

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream("src/componentes/crono.obj"));
            crono = (Cronometro) flujoEntrada.readObject();

        } catch (Exception e) {
            System.out.println("Error al recuperar la clase CRONOMETRO\n" + e.getMessage());
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
            listaboton.get(i).setFont(new Font(text, Font.BOLD, 14));
        }

    }

}
