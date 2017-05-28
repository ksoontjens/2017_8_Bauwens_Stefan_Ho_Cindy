/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;
/**
 *
 * @author student
 */
public class MyTimerTask extends TimerTask {

    Track cb;
    
    public void setChessBoard(Track cb)
    {
        this.cb = cb;
    }
    
    public void run() {
        if (!Stopwatch.gameEnds)
        {
            cb.drawBg();
        }
    }

}
