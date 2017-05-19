package hellotvxlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.TimerTask;
import org.havi.ui.HState;
import org.havi.ui.HStaticText;

/**
 *
 * @author student
 */
public class Stopwatch extends TimerTask{
    int ms;
    int seconds;
    int minutes;
    //int milliseconds;
    HStaticText secondsText;
    HStaticText minutesText;
    HStaticText msText;
    String secondsString = "00";
    String minutesString = "00";
    String msString = "00";

    
    public void setStopwatchText(HStaticText minutesText, HStaticText secondsText, HStaticText msText){
        this.secondsText = secondsText;
        this.minutesText = minutesText;
        this.msText = msText;
    }
    
    public void run(){
        ms++;
        
        if(ms < 10)
        {
            msString = "0" + ms;
        }
        else if(ms >= 10)
        {
            msString = Integer.toString(ms);
        }
        
        if(ms >= 99)
        {
           ms = 0;
           seconds++;
           
        }
        
        if(seconds < 10){
            secondsString = "0" + seconds;
        }
        else if(seconds >= 10)
        {
            secondsString = Integer.toString(seconds);
        }
        
        if(seconds > 60)
        {
            minutes++;
            seconds = 0;
        }
        
        minutesString = Integer.toString(minutes);
        
        minutesText.setTextContent(minutesString + "'", HState.NORMAL_STATE);
        secondsText.setTextContent(secondsString + "\"", HState.NORMAL_STATE);
        msText.setTextContent(msString, HState.NORMAL_STATE);
    }
}
