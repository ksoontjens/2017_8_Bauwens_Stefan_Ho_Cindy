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
public class Background extends HComponent {

    Image background;
    MediaTracker mtrack;
    
    public Background(int x1, int y1, int x2, int y2){
        this.setBounds(x1, y1, x2, y2);
        mtrack = new MediaTracker(this);
        background = this.getToolkit().getImage("background-pixelated.png");
        mtrack.addImage(background, 0);
    }

    
    public void paint(Graphics g){
        g.drawImage(background,0,0,null);
    }
}
