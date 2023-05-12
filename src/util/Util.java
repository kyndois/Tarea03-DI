package util;

import componentes.Contenedor;
import java.io.File;
import java.util.*;

public class Util {

    public ArrayList<ImagePanel> listaImagenes(String s) {
        ArrayList<ImagePanel> lista = new ArrayList<>();
        File f = new File("src/img/" + s + "/");

        int o = f.listFiles().length;
        for (int i = 1; i <= o; i++) {
            lista.add(new ImagePanel("src/img/" + s + "/img" + i + ".jpg"));
        }

        return lista;
    }
}
