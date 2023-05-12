package tarea03;

import componentes.Boton;
import componentes.Contenedor;
import componentes.Cronometro;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Ventana;

public class Tarea03 {

    public static void main(String args[]) {
        Contenedor cont = new Contenedor();
        Cronometro crono = new Cronometro();
        Boton boton = new Boton();
        ObjectOutputStream salida = null;
        try {
            salida = new ObjectOutputStream(new FileOutputStream("crono.obj"));
            salida.writeObject(crono);
            salida.close();

            salida = new ObjectOutputStream(new FileOutputStream("boton.obj"));
            salida.writeObject(boton);
            salida.close();

            salida = new ObjectOutputStream(new FileOutputStream("contenedor.obj"));
            salida.writeObject(cont);
            salida.close();

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Ventana().setVisible(true);

                }
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tarea03.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tarea03.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(Tarea03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
