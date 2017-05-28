/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    float multiplier = 0;
    int speed = 50;
    int addToX = 0;
    int addToXCar = 350;
    boolean turnRight = true;
    boolean isTurning = false;
    
    int iterate = 0;
    int counter = 0;
    int xCoord = 10;
    int yCoord = 10;
    int carpos=200;
    float addToZ = 0;
    int carCount = 0;
    
    int[] lanes = {200, 350, 500};
    int margin = 5;
    //car[] test = new car[3];
    //List<car> cars = new ArrayList<car>();
    car[] cars = new car[20];
    Image[] carSprites ;//= new String[5];
    
    Random rand = new Random();

    Image bluecar = this.getToolkit().getImage("blueCar-middle.png"); 
    Image pinkcar = this.getToolkit().getImage("pinkCar-middle.png");
    Image greencar = this.getToolkit().getImage("greenCar-middle.png");
    Image orangecar = this.getToolkit().getImage("orangeCar-middle.png");
    Image redcar = this.getToolkit().getImage("redCar-middle.png");
    Image yellowcar = this.getToolkit().getImage("yellowCar-middle.png");
    Image palmtree = this.getToolkit().getImage("palmTree.png");

    MediaTracker mtrack;
    
    //HStaticText tekstlabel;
    
    int[] map = {0,-1,2,-2,2,-2,1,0,0-2,4,-4,4,-4,2,0,0};//,-1,1,1,-1,-1,1,-1,1,0,0}; //0 is no change 
                                                                //+ is turn left
                                                                //- is turn right
    

    public Track()
    {
        this.setBounds(0,0,720,576); // full screen
        
        mtrack = new MediaTracker(this);
        mtrack.addImage(bluecar,0);
        mtrack.addImage(pinkcar,1);
        mtrack.addImage(greencar,2);
        mtrack.addImage(orangecar,3);
        mtrack.addImage(redcar,4);
        mtrack.addImage(yellowcar,5);
        mtrack.addImage(palmtree,6);
        
        try
        {
            mtrack.waitForAll();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        for (int i = 0; i < cars.length; i++) //initialsing array
        {
            cars[i] = new car(0,0,-4, 0); 
        }
        
        carSprites = new Image[] {pinkcar, greencar, orangecar, redcar, yellowcar};
        //cars[0] = (new car(lanes[rand.nextInt(3)],0.5f,0));
        
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
                         
            if ((z+tim) >= iterate* 100)// && (z+tim) <= iterate*302)
            {
                counter++;
                multiplier +=(map[iterate]/10.0f);
                if (counter==2000)
                {
                    counter = 0;
                    iterate++;
                    if (iterate >= map.length-1)
                    {
                        iterate = 0; //loop forever
                        tim=0;
                    }
                }
            }

            bx[z]=(int) (multiplier * Math.sin((z)/20.0)); //makes the curves
        }
        
        for (int z=79;z>=5;z--) //this for-loop is used to draw every line of our road
        {   
            
            g.setColor(Color.YELLOW); //draws the grass
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



            x[0]=transformX(100+bx[z]+addToX,hy[z],z); //draws the road
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



            g.setColor(Color.WHITE); //draws the white road dividers
            if ((z+tim)%5==0) //modulo 5 to create the vertical spaces between the white lines
            {
                for (int dx=15;dx<=465;dx+=150) //for loop to create the 3 stripes horizontally
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
            
            if ((z+tim)%9==0)
            {
                int scalew = 940/z;
                int scaleh = 2220/z;
                g.drawImage(palmtree,transformX(100+bx[z], hy[z]-222, z),transformY(100+bx[z], hy[z]-222, z),-scalew,scaleh, this);
                g.drawImage(palmtree,transformX(600+bx[z], hy[z]-222, z),transformY(600+bx[z], hy[z]-222, z),scalew,scaleh, this);
            }
            
        }
        
        /*int curposbegin=kcounter;
        int curposend=curposbegin+75;
        int relcarpos=carpos-curposbegin;*/
        int relcarpos = 50;
        //System.out.println("relcarpos="+relcarpos);
        /*if ((carpos>curposbegin )&& (carpos < curposend))
        {*/
        
        /*
         * z = 50+(int)addToZ;
                scalef=70-(int)(z*1.5f);
                int xPos = 500;//200;//350;
                g.drawImage(pinkcar, transformX(bx[z]+xPos, hy[z], z)-scalef/2, transformY(bx[z]+xPos, hy[z], z)-scalef/2, scalef, scalef, this);
         * */
            int z;
            int scalef;
            //Enemy car:
            for (int i = 0; i < cars.length; i++)
            {
                z = 50+(int)cars[i].getZPos();
                scalef=70-(int)(z*1.5f);
                int xPos = cars[i].getLane();//200;//350;
                g.drawImage(carSprites[cars[i].getSpriteIndex()], transformX(bx[z]+xPos, hy[z], z)-scalef/2, transformY(bx[z]+xPos, hy[z], z)-scalef/2, scalef, scalef, this);
                if (checkIntersect(transformX(bx[10]+addToXCar, hy[10], 10)-27 + margin,transformY(bx[10]+addToXCar, hy[10], 10)-27+margin,55-margin,55-margin,transformX(bx[z]+xPos, hy[z], z)-(scalef/2)+margin,transformY(bx[z]+xPos, hy[z], z)-(scalef/2)+margin,scalef-margin,scalef-margin))
                {
                    System.out.println("GAME OVER");
                    //insert stopwatch
                }
            }
            
            //Your car:
            z = 10;
            scalef = 70-(int)(z*1.5f);
            g.drawImage(bluecar, transformX(bx[z]+addToXCar, hy[z], z)-scalef/2, transformY(bx[z]+addToXCar, hy[z], z)-scalef/2, scalef, scalef, this);
            
            /*g.setColor(Color.yellow); //for testing purposes:
            int x[]=new int[4];
            int y[]=new int[4];
            x[0]=transformX(bx[z]+addToXCar, hy[z], z);
            y[0]=transformY(bx[z]+addToXCar, hy[z], z);

            x[1]=transformX(bx[z]+10+addToXCar, hy[z], z);
            y[1]=transformY(bx[z]+10+addToXCar, hy[z], z);

            x[2]=transformX(bx[z]+10+addToXCar, hy[z+1], z+1);
            y[2]=transformY(bx[z]+10+addToXCar, hy[z+1], z+1);

            x[3]=transformX(bx[z]+addToXCar, hy[z+1], z+1);
            y[3]=transformY(bx[z]+addToXCar, hy[z+1], z+1);   
            g.fillPolygon(x,y,4);*/
            //}
    }

    public void userEventReceived(UserEvent e) {
       if (e.getType()==HRcEvent.KEY_PRESSED)
       {
           if (e.getCode()==HRcEvent.VK_RIGHT)
           {
               turnRight = true; 
               isTurning = true;                   
           }
           if (e.getCode()==HRcEvent.VK_LEFT) 
           {
               turnRight = false;
               isTurning = true;
           }
       }
    }
    
    protected boolean checkIntersect(int x, int y, int w, int h, int x1, int y1, int w1, int h1)
    {
        if (    (x1>x && x1<(x+w) && y1>y && y1<(y+h)) ||
                ((x1+w1)>x && (x1+w1)<(x+w) && y1>y && y1<(y+h)) ||
                (x1>x && x1<(x+w) && (y1+h1)>y && (y1+h1)<(y+h)) ||
                ((x1+w1)>x && (x1+w1)<(x+w) && (y1+h1)>y && (y1+h1)<(y+h))
                )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void drawBg(){
        tim+=2;
        if (rand.nextInt(50)==1) //random enemy car
        {
            cars[(carCount+1)%cars.length] = (new car(lanes[rand.nextInt(3)],0.5f,0,rand.nextInt(5)));
            carCount++;
        }
        for (int i = 0; i < cars.length; i++)
        {
            //cars.set(i.setZPos(cars[i].getZPos());
            if (cars[i].getZPos() > -49.0f)
            {
                cars[i].setZPos(cars[i].getZPos()-cars[i].getSpeed());
            }
            else
            {
                cars[i] = new car(0,0,-4,0); //hides the car
            }
        }
        if (isTurning)
        {
            if (turnRight)
            {
               //addToX-=10;
                addToXCar+=10;
            }
            else
            {
                //addToX+=10;
                addToXCar-=10;
            } 
            isTurning = false;
        }

        this.repaint();
    }
    
}
