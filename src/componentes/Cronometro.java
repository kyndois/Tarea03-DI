package componentes;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import listeners.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import util.ImagePanel;

public class Cronometro extends JLabel implements Serializable, CronometroEventListener {

    private Boolean activo;
    private Contenedor contenedor;
    private Timer timer;
    private LocalTime time, inicio;
    private TimerTask task;
    ArrayList<ImagePanel> lista;
    private int tiempo; //Propiedad que le va a indicar el tiempo a cronometrar
    //Aqui se almacenan todos los manejadores
    private ArrayList listeners;

    //Constructor vacio
    public Cronometro() {
        this.activo = true;
        this.inicio = LocalTime.now();
        super.setBackground(Color.BLUE);
        super.setForeground(Color.WHITE);
        super.setOpaque(true);
        super.setHorizontalAlignment(SwingConstants.CENTER);

        //Creamos el almacen de los listeners
        listeners = new ArrayList<>();
        listeners.add(this);

    }

    public void addCronometroEventListener(CronometroEventListener listener) {
        //Agregamos el manejador a nuestra lista
        listeners.add(listener);
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            if (this.activo) {
                ((CronometroEventListener) li.next()).StartCronometro(new CronometroEventObject(this));
            } else {
                ((CronometroEventListener) li.next()).StopCronometro(new CronometroEventObject(this));
            }
        }
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Timer getTimer() {
        return timer;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setListaImg(ArrayList<ImagePanel> lista) {
        this.lista = lista;
    }

    public void setContenedor(Contenedor c) {
        this.contenedor = c;
        cambiarImg(lista);
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    private void cambiarImg(ArrayList<ImagePanel> lista) {

        if (lista != null) {
            contenedor.removeAll();
            contenedor.setImg1(lista.get(0));
            contenedor.setImg2(lista.get(1));
            contenedor.setImg3(lista.get(2));
        }
    }

    public final void contar() {

        //Inicializar el tiempo que quiero que cronometre para probar la ejecución
        tiempo = (lista.size()) * 10;
        activo = true;

        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                time = LocalTime.now();
                Duration d1 = Duration.between(inicio, time);
                lista.get(1).setVisible(false);
                lista.get(2).setVisible(false);
                setText(String.valueOf(d1.toSeconds()));
                if ((int) d1.toSeconds() >= 1) {
                    lista.get(0).setVisible(true);
                }
                if ((int) d1.toSeconds() >= 10) {
                    lista.get(1).setVisible(true);
                }
                if ((int) d1.toSeconds() >= 20) {
                    lista.get(2).setVisible(true);
                }
                int reset = 1;
                for (int i = 3; i < lista.size(); i++) {
                    if (i - 3 == 3) {
                        reset++;
                    }
                    if ((int) d1.toSeconds() == i * 10) {
                        ImagePanel im = lista.get(i);
                        lista.set(i - (3 * reset), im);
                        cambiarImg(lista);
                    }
                }

                if (tiempo == (int) d1.toSeconds()) {
                    pararCronometro();
                }

            }
        };
        timer.schedule(task, 0, 1000);

    }

    public void pararCronometro() {
        setActivo(false);
        task.cancel();
    }

    public void startCronometro() {
        setActivo(true);
        this.inicio = LocalTime.now();
        contar();
    }

    public void reiniciarCrono() {

        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            ((CronometroEventListener) li.next()).ResetCronometro(new CronometroEventObject(this));
        }
    }

    @Override
    public void StopCronometro(CronometroEventObject args
    ) {
        // TODO Auto-generated method stub
        System.out.println("Voy a parar el cronometro");
    }

    @Override
    public void StartCronometro(CronometroEventObject args
    ) {
        // TODO Auto-generated method stub
        System.out.println("Voy a inicializar el cronometro");

    }

    @Override
    public void ResetCronometro(CronometroEventObject args
    ) {
        // TODO Auto-generated method stub
        System.out.println("Voy a resetear el cronometro");
        if (!activo) {
            startCronometro();
        }
        pararCronometro();
        startCronometro();

    }

}
