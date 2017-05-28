/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class BackgroundImage extends HComponent {

    Image skyBackground = this.getToolkit().getImage("skyBackground.png");

    MediaTracker mtrack;
    
    public BackgroundImage( int x1, int y1)
    {
        this.setBounds(0,0,x1,y1);
        mtrack = new MediaTracker(this);
        mtrack.addImage(skyBackground,0);
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(skyBackground,0,0,800,315,null);
    }
}


