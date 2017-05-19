/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;
import org.havi.ui.HStaticText;

/**
 *
 * @author student
 */
public class Track extends HComponent implements UserEventListener {

    int xoff=100;
    int yoff=100;
    
    int curx=0;
    int cury=0;
    public int tim=0;
    //boolean stopCurve = false;
    int multiplier = 0;
    int speed = 50;
    int addToX = 0;
    boolean turnRight = true;
    boolean isTurning = false;
    
    int iterate = 0;
    int counter = 0;
    int kcounter=0;
    int xCoord = 10;
    int yCoord = 10;
    int carpos=200;
    
    Image bluecar = this.getToolkit().getImage("blueCar-middle.png");
            
    MediaTracker mtrack;
    
    //HStaticText tekstlabel;
    
    int[] map = {0,-2,4,-4,4,-4,2,0,0};//,-1,1,1,-1,-1,1,-1,1,0,0}; //0 is no change 
                                                                //+ is turn left
                                                                //- is turn right
    
    public Track()
    {
        this.setBounds(0,0,720,576); // full screen
        
        mtrack = new MediaTracker(this);
        mtrack.addImage(bluecar,0);
        
        try
        {
            mtrack.waitForAll();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        MyTimerTask objMyTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        objMyTimerTask.setChessBoard(this);
        timer.scheduleAtFixedRate(objMyTimerTask, 0, speed); // timer runs every 25ms
       
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
        
        //int previousValue = 1;
        for (int z=5;z<81;z++)
        {
            hy[z]=(int) ((int) (400 + 40 * Math.sin((z+tim) / 15.0)) - ( 40 * Math.sin((5 + tim) / 15.0))); // makes the hills
            //bx[z]=(int) (40 * Math.sin((z + tim) / 20.0)); //makes the curves
            //if (!stopCurve)
                         
            if ((z+tim) >= iterate* 100)// && (z+tim) <= iterate*302)
            {
                counter++;
                multiplier +=map[iterate];
                if (counter==200)
                {
                    counter = 0;
                    iterate++;
                    System.out.println("Iteration:"+iterate+" Multiplier:"+multiplier);
                }
            }
            /*
            if ((z+tim)>302 && multiplier<400)
            {
                multiplier ++;
                addToX =-multiplier/2;
                //addToX = -0.25;
            }*/
            
            /*for (int i=0; i < map.length;i++)
            {
                if ((z+tim)/20.0 >= (i*50) && (z+tim)/20.0 < (i+1)*50)
                {
                    multiplier += map[i];
                    //addToX = -multiplier/4;
                }
            }*/
            //multiplier = 200;
            /*if (multiplier>0)
            {
                multiplier--;
            }*/

            bx[z]=(int) (multiplier * Math.sin((z)/20.0)); //makes the curves
            /*if ((bx[z]/Math.abs(bx[z])) != (previousValue/Math.abs(previousValue)))
            {
                System.out.println(multiplier);
                //multiplier = 2;
                 //bx[z] = 0;
            }*/
            
            //previousValue = bx[z];
            //System.out.println(bx[z]);
            //System.out.println(z+tim);
           // bx[z]=(int) (multiplier *Math.sin((z+tim)));

        }
        
        for (int z=79;z>=5;z--) //this for-loop is used to draw every line of our road
        {
            g.setColor(Color.GREEN);
            int x[]=new int[5];
        
            int y[]=new int[5];
            x[0]=transformX(-2500+addToX,hy[z],z);
            y[0]=transformY(-2500,hy[z],z);

            x[1]=transformX(2500+addToX,hy[z],z);
            y[1]=transformY(2500,hy[z],z);

            x[2]=transformX(2500+addToX,hy[z+1],z+1);
            y[2]=transformY(2500,hy[z+1],z+1);

            x[3]=transformX(-2500+addToX,hy[z+1],z+1);
            y[3]=transformY(-2500,hy[z+1],z+1);   

            x[4]=transformX(-2500+addToX,hy[z],z);
            y[4]=transformY(-2500,hy[z],z);   

            g.fillPolygon(x,y,5);

            g.setColor(Color.GRAY);



            x[0]=transformX(100+bx[z]+addToX,hy[z],z);
            y[0]=transformY(100+bx[z],hy[z],z);

            x[1]=transformX(620+bx[z]+addToX,hy[z],z);
            y[1]=transformY(620+bx[z],hy[z],z);

            x[2]=transformX(620+bx[z]+addToX,hy[z+1],z+1);
            y[2]=transformY(620+bx[z],hy[z+1],z+1);

            x[3]=transformX(100+bx[z]+addToX,hy[z+1],z+1);
            y[3]=transformY(100+bx[z],hy[z+1],z+1);   

            x[4]=transformX(100+bx[z]+addToX,hy[z],z);
            y[4]=transformY(100+bx[z],hy[z],z);   


            g.fillPolygon(x,y,5);



            g.setColor(Color.WHITE);
            if ((z+tim)%5==0) //modulo 5 to create the vertical spaces between the white lines
            {
                for (int dx=5;dx<=510;dx+=100) //for loop to create the 6 stripes horizontally
                {
                    x[0]=transformX(100+dx+bx[z]+addToX,hy[z],z);
                    y[0]=transformY(100+dx+bx[z],hy[z],z);

                    x[1]=transformX(110+dx+bx[z]+addToX,hy[z],z);
                    y[1]=transformY(110+dx+bx[z],hy[z],z);

                    x[2]=transformX(110+dx+bx[z]+addToX,hy[z+1],z+1);
                    y[2]=transformY(110+dx+bx[z],hy[z+1],z+1);

                    x[3]=transformX(100+dx+bx[z]+addToX,hy[z+1],z+1);
                    y[3]=transformY(100+dx+bx[z],hy[z+1],z+1);   

                    x[4]=transformX(100+dx+bx[z]+addToX,hy[z],z);
                    y[4]=transformY(100+dx+bx[z],hy[z],z);   


                    g.fillPolygon(x,y,5);
                }
            }
        }
        
        int curposbegin=kcounter;
        int curposend=curposbegin+75;
        int relcarpos=carpos-curposbegin;
        System.out.println("relcarpos="+curposbegin);
        if ((carpos>curposbegin )&& (carpos < curposend))
        {
            int scalef=200; //int)(relcarpos/10.0);
            System.out.println("scalef="+scalef);
           // Image scaled=bluecar.getScaledInstance(scalef, scalef, Image.SCALE_SMOOTH);
        g.drawImage(bluecar,transformX(120+bx[relcarpos],hy[relcarpos],relcarpos+5),transformY(120+bx[relcarpos],hy[relcarpos],relcarpos+5),null);
        }
    }

    public void userEventReceived(UserEvent e) {
       if (e.getType()==HRcEvent.KEY_PRESSED)
       {
           if (e.getCode()==HRcEvent.VK_RIGHT)
           {
               /*addToX-=10*/ turnRight = true; 
               isTurning = true;    
               xCoord++;
               
           }
           if (e.getCode()==HRcEvent.VK_LEFT) 
           {
               turnRight = false;//addToX+=10;
               isTurning = true;
           }
           
           //this.repaint();
       }
    }
    
    public void drawBg(){
        tim++;
        kcounter++;
        if (isTurning)
        {
            if (turnRight)
            {
                addToX-=10;
            }
            else
            {
                addToX+=10;
            } 
            isTurning = false;
        }

        this.repaint();
    }
}
