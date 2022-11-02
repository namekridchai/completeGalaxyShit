import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;     // for sounds
import java.util.Random;


public class BaseLabel extends JLabel {
    private String mode = "key";
    private int speed = 10;
    protected MyImageIcon   icon;
    protected int           curX, curY, width, height;
    public BaseLabel(){super();}
    public void ChangMode(){
        if(mode=="key")
            mode = "mouse";
        else
            mode = "key";
    }
    public BaseLabel(String file1, int w, int h)				
    { 
        width = w; height = h;
        icon = new MyImageIcon(file1).resize(width, height);
	setHorizontalAlignment(JLabel.CENTER);
	setIcon(icon);
       
    }
    public void setMove(int x, int y)
    {
        curX = x; curY = y;
	setBounds(curX, curY, width, height);
      
    }
    public void moveRight(int frameWidth){
        if(mode.equals("key")){
           
            if(frameWidth-curX<width)
            {  // curX = 0;  
                
            }
            else
                 curX+=speed;
            setBounds(curX, curY, width, height);
        }
        
        else{      
        }
    }
    public int getCurx(){
        return curX;
    }
    public int getCurY(){
        return curY;
    
    }
    public void moveLeft(int frameWidth){
        if(mode.equals("key")){
           
            if(curX<0)
            {   //curX = frameWidth-width;
            }
            else
                curX-=speed;
            setBounds(curX, curY, width, height);
        }
    }
}
