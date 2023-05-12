package listeners;
public class ManejadorCronometroEventListener implements CronometroEventListener{

    @Override
    public void StopCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
        System.out.println("Para");
       
    }

    @Override
    public void StartCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
        System.out.println("Empieza");
      
    }

    @Override
    public void ResetCronometro(CronometroEventObject args) {
        // TODO Auto-generated method stub
      
    }
    
}
