

import java.util.logging.Level;
import java.util.logging.Logger;


public class main {
    public static void main(String[] args) {
        System.out.println("Proje başlangıcı!");
       
         
         Elevator1 elevator = new Elevator1();
       
        Thread login1 = new Thread(new Runnable() {
            @Override
            public void run() {
                elevator.login();
            }
        });
        Thread threadExit = new Thread(new Runnable() {
            @Override
            public void run() {
                elevator.exit();

            }
        });
        Thread elevator1 = new Thread(new Runnable() {
            @Override
            public void run() {
                elevator.lift();
            }
        });
        
        Thread controlThread = new Thread(new Runnable() {
            @Override
            public void run() {
                elevator.control();
            }
        });
        
        login1.start();
        threadExit.start();
        elevator1.start();
        controlThread.start();
        
        try {
            login1.join();
            threadExit.join();
            elevator1.join();
            controlThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
      
        
    }
}
