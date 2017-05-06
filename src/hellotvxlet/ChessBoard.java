/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class ChessBoard extends HComponent implements UserEventListener {

    int xoff=100;
    int yoff=100;
    
    int curx=0;
    int cury=0;
    public int tim=0;
    
    public ChessBoard()
    {
        this.setBounds(0,0,720,576); // full screen
        
        MyTimerTask objMyTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        objMyTimerTask.setChessBoard(this);
        timer.scheduleAtFixedRate(objMyTimerTask, 0, 25); // timer runs every 25ms
    }
    
    int transformX(int x,int y,int z) //this method converts 3D point to a projected 2D point (x)
    {
        double fz=z/10.0;
        double fx=x-360;
        return (int)(fx/fz)+360;
    }
    int transformY(int x,int y,int z) //this method converts 3D point to a projected 2D point (y)
    {
        double fz=z/10.0;
        double fy=y-280;
        return (int)(fy/fz)+280;
    }    
    public void paint(Graphics g)
    {
        int hy[]=new int[81];
        int bx[]=new int[81];
        
        
        for (int z=5;z<81;z++)
        {
            hy[z]=(int) ((int) (400 + 40 * Math.sin((z+tim) / 15.0)) - ( 40 * Math.sin((5 + tim) / 15.0))); // makes the hills
            bx[z]=(int) (40 * Math.sin((z + tim) / 20.0)); //makes the curves
        }
        
        for (int z=79;z>=5;z--) //this for-loop is used to draw every line of our road
        {
            g.setColor(Color.GREEN);
            int x[]=new int[5];
        
            int y[]=new int[5];
            x[0]=transformX(-2500,hy[z],z);
            y[0]=transformY(-2500,hy[z],z);

            x[1]=transformX(2500,hy[z],z);
            y[1]=transformY(2500,hy[z],z);

            x[2]=transformX(2500,hy[z+1],z+1);
            y[2]=transformY(2500,hy[z+1],z+1);

            x[3]=transformX(-2500,hy[z+1],z+1);
            y[3]=transformY(-2500,hy[z+1],z+1);   

            x[4]=transformX(-2500,hy[z],z);
            y[4]=transformY(-2500,hy[z],z);   

            g.fillPolygon(x,y,5);

            g.setColor(Color.GRAY);



            x[0]=transformX(100+bx[z],hy[z],z);
            y[0]=transformY(100+bx[z],hy[z],z);

            x[1]=transformX(620+bx[z],hy[z],z);
            y[1]=transformY(620+bx[z],hy[z],z);

            x[2]=transformX(620+bx[z],hy[z+1],z+1);
            y[2]=transformY(620+bx[z],hy[z+1],z+1);

            x[3]=transformX(100+bx[z],hy[z+1],z+1);
            y[3]=transformY(100+bx[z],hy[z+1],z+1);   

            x[4]=transformX(100+bx[z],hy[z],z);
            y[4]=transformY(100+bx[z],hy[z],z);   


            g.fillPolygon(x,y,5);



            g.setColor(Color.WHITE);
            if ((z+tim)%5==0) //modulo 5 to create the vertical spaces between the white lines
            {
                for (int dx=5;dx<=510;dx+=100)
                {
                    x[0]=transformX(100+dx+bx[z],hy[z],z);
                    y[0]=transformY(100+dx+bx[z],hy[z],z);

                    x[1]=transformX(110+dx+bx[z],hy[z],z);
                    y[1]=transformY(110+dx+bx[z],hy[z],z);

                    x[2]=transformX(110+dx+bx[z],hy[z+1],z+1);
                    y[2]=transformY(110+dx+bx[z],hy[z+1],z+1);

                    x[3]=transformX(100+dx+bx[z],hy[z+1],z+1);
                    y[3]=transformY(100+dx+bx[z],hy[z+1],z+1);   

                    x[4]=transformX(100+dx+bx[z],hy[z],z);
                    y[4]=transformY(100+dx+bx[z],hy[z],z);   


                    g.fillPolygon(x,y,5);
                }
            }
        }
        
    }

    public void userEventReceived(UserEvent e) {
       if (e.getType()==HRcEvent.KEY_PRESSED)
       {
           if (e.getCode()==HRcEvent.VK_RIGHT) tim++;
           //if (e.getCode()==HRcEvent.VK_LEFT) tim--;
           
           //this.repaint();
       }
    }
    
    public void drawBg(){
        tim++;
        this.repaint();
    }
}
