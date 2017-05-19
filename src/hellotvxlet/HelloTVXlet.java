package hellotvxlet;

import java.awt.event.ActionEvent;
import java.util.Timer;
import javax.tv.xlet.*;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {

    HScene scene;
    private HStaticText minutesText;
    private HStaticText secondsText;
    private HStaticText msText;
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) { //720 x 576
      scene=HSceneFactory.getInstance().getDefaultHScene();
      Track bord=new Track();
         UserEventRepository repo=new UserEventRepository("repo");
         repo.addAllArrowKeys();
     EventManager man=EventManager.getInstance();
     man.addUserEventListener(bord, repo);
     
      minutesText = new HStaticText("0");
      secondsText = new HStaticText("00");
      msText = new HStaticText("00");
      minutesText.setLocation(450, -100);
      minutesText.setSize(300,250);
      secondsText.setLocation(485, -100);
      secondsText.setSize(300, 250);
      msText.setLocation(520, -100);
      msText.setSize(300,250);
      
     
      Stopwatch objStopwatch = new Stopwatch();
      Timer stopwatchTimer = new Timer();
      objStopwatch.setStopwatchText( minutesText, secondsText, msText);
      stopwatchTimer.scheduleAtFixedRate(objStopwatch, 0, 10); // every 10 ms
     
      scene.add(bord);
      scene.add(minutesText);
      scene.add(secondsText);
      scene.add(msText);
      
      scene.validate();
      scene.setVisible(true);
    }

    public void startXlet() {
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
       
        }
        
    }
