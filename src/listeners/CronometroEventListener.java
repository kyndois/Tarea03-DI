package listeners;
public interface CronometroEventListener {
    public void StopCronometro(CronometroEventObject args);
    public void StartCronometro(CronometroEventObject args);
    public void ResetCronometro(CronometroEventObject args);
}
