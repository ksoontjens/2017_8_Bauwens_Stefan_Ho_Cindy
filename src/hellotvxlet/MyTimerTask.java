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

    ChessBoard cb;
    
    public void setChessBoard(ChessBoard cb)
    {
        this.cb = cb;
    }
    
    public void run() {
        cb.drawBg();
    }

}
