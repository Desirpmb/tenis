package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometre extends Thread{

       private int seconde =0;
       private  int minute = 0;
       private boolean fin = false;
     //   Timer chrono = new Timer();



/**
        TimerTask timerTask = new TimerTask() {

            public void run() {
                seconde++;
                if(seconde == 30)
                   // System.out.println(minute + ":" + seconde);
                    if (seconde == 60) {
                        seconde = 0;
                        minute++;
                       // System.out.println(minute + ":" + seconde);
                    }
                if(fin) {
                    chrono.cancel();
                }

            }
        };
 **/

    public void run(){
        while(!fin){
            try {
                Thread.sleep(30000);
                if(incrementerseconde()){
                    incrementMinute();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean incrementerseconde(){
        seconde = seconde+30;
        if(seconde==60){
            seconde=0;
            return true;
        }
        return false;
    }

    public void incrementMinute(){
        minute++;

    }

    public void fin(){
        fin = true;
    }

    public int getSeconde() {
        return seconde;
    }

    public int getMinute() {
        return minute;
    }

}
