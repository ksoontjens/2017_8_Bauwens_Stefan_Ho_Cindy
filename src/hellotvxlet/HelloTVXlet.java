package hellotvxlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Timer;
import javax.tv.xlet.*;
import org.dvb.event.*;
import org.havi.ui.*;
import org.havi.ui.event.*;


public class HelloTVXlet implements Xlet, HActionListener {

    HScene scene;
    private HStaticText minutesText;
    private HStaticText secondsText;
    private HStaticText msText;
    private HStaticText gameOverText;
    private HStaticText endMin;
    private HStaticText endSec;
    private HStaticText endMs;
    private HStaticText restartText;

    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) { //720 x 576
    
      scene=HSceneFactory.getInstance().getDefaultHScene();
      BackgroundImage bgImage = new BackgroundImage(720,576);
      Track bord=new Track();
         UserEventRepository repo=new UserEventRepository("repo");
         repo.addAllArrowKeys();
     EventManager man=EventManager.getInstance();
     man.addUserEventListener(bord, repo);
     
      Font fontTime = new Font("Verdana", Font.PLAIN, 25);
      Font fontGO = new Font("Verdana", Font.PLAIN, 50);
      Font fontEndTime = new Font("Verdana", Font.PLAIN, 30);
      Font fontRestart = new Font("Verdana", Font.PLAIN, 20);
      
      minutesText = new HStaticText("0");
      minutesText.setFont(fontTime);
      secondsText = new HStaticText("00");
      secondsText.setFont(fontTime);
      msText = new HStaticText("00");
      msText.setFont(fontTime);
      minutesText.setLocation(400, -80);
      minutesText.setSize(300,250);
      secondsText.setLocation(430, -80);
      secondsText.setSize(300, 250);
      msText.setLocation(465, -80);
      msText.setSize(300,250);
      
      gameOverText = new HStaticText("GAME OVER");
      gameOverText.setFont(fontGO);
      gameOverText.setLocation(60, -150);
      gameOverText.setSize(600,600);
      endMin = new HStaticText("0");
      endMin.setFont(fontEndTime);
      endMin.setLocation(22, -100);
      endMin.setSize(600,600);
      endSec = new HStaticText("00");
      endSec.setFont(fontEndTime);
      endSec.setLocation(58, -100);
      endSec.setSize(600,600);
      endMs = new HStaticText("00");
      endMs.setFont(fontEndTime);
      endMs.setLocation(97, -100);
      endMs.setSize(600,600);
      restartText = new HStaticText("Press \"up arrow\" to restart!");
      restartText.setFont(fontRestart);
      restartText.setLocation(60,160);
      restartText.setSize(600,600);
      restartText.setForeground(Color.DARK_GRAY);
      
      Stopwatch objStopwatch = new Stopwatch();
      Timer stopwatchTimer = new Timer();
      objStopwatch.setStopwatchText( minutesText, secondsText, msText, gameOverText, restartText);
      stopwatchTimer.scheduleAtFixedRate(objStopwatch, 0, 10); // every 10 ms
      
      objStopwatch.SetTimerStopwatch(stopwatchTimer);
      objStopwatch.SetEndTime(endMin, endSec, endMs);
      scene.add(gameOverText);
      scene.add(endMin);
      scene.add(endSec);
      scene.add(endMs);
      scene.add(restartText);
      scene.add(bord);
      scene.add(minutesText);
      scene.add(secondsText);
      scene.add(msText);
      
      
      scene.add(bgImage);
      scene.validate();
      scene.setVisible(true);
      
      gameOverText.setVisible(false);
      endMin.setVisible(false);
      endSec.setVisible(false);
      endMs.setVisible(false);
      restartText.setVisible(false);
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
