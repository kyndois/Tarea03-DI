package componentes;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import listeners.*;
import javax.swing.JLabel;

/*Duration also can be accessed using other duration-based units, such as minutes and hours. In addition, the DAYS unit can be used and is treated as exactly equal to 24 hours. You convert a Duration to these time units using these conversion methods:

    long toDays(): Gets the number of days in this duration.
    long toHours(): Gets the number of hours in this duration.
    long toMillis(): Converts this duration to the total length in milliseconds.
    long toMinutes(): Gets the number of minutes in this duration.
    long toNanos(): Converts this duration to the total length in nanoseconds expressed as a long.

And these methods available since Java 9:

    long toDaysPart(): Extracts the number of days in the duration.
    int toHoursPart(): Extracts the number of hours part in the duration.
    int toMillisPart(): Extracts the number of milliseconds part of the duration.
    int toMinutesPart(): Extracts the number of minutes part in the duration.
    int toNanosPart(): Get the nanoseconds part within seconds of the duration.
    long toSeconds(): Gets the number of seconds in this duration.
    int toSecondsPart(): Extracts the number of seconds part in the duration.*/
public class Cronometro extends JLabel implements Serializable, CronometroEventListener {

    private Boolean activo;
    private Timer timer;
    private LocalTime time, inicio;
    private TimerTask task;
    private int tiempo; //Propiedad que le va a indicar el tiempo a cronometrar

    //Aqui se almacenan todos los manejadores
    private ArrayList listeners;

    //Constructor vacio
    public Cronometro() {
        this.activo = true;
        this.inicio = LocalTime.now();
        super.setFont(new Font("TimesRoman", Font.BOLD, 22));
        super.setForeground(Color.blue);

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

    public final void contar() {

        //Inicializar el tiempo que quiero que cronometre para probar la ejecución
        tiempo = 12;
        activo = true;

        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                time = LocalTime.now();
                Duration d1 = Duration.between(inicio, time);

                setText(String.valueOf(d1.toSeconds()));
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
        contar();
    }

    public void reiniciarCrono() {
        if (task != null) {
            task.cancel();
        }
        contar();
    }

    @Override
    public void StopCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
        System.out.println("Voy a parar el cronometro");
    }

    @Override
    public void StartCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
        System.out.println("Voy a inicializar el cronometro");

    }

    @Override
    public void ResetCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
        System.out.println("Voy a resetear el cronometro");

    }

}
