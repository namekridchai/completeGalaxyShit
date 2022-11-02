import java.util.*;
import javax.swing.JLabel;
public class bulletThread extends Thread {
    private BaseLabel base;
    private JLabel drawpane,bullet;
    private MyImageIcon bulletImg;
    private int baseWidth;
    private boolean condition;
    private ArrayList<bulletThread> al;
    public bulletThread(BaseLabel b,JLabel l,MyImageIcon m,int width,ArrayList<bulletThread> al){
        base = b;
        drawpane = l;
        bulletImg = m;
        baseWidth = width;
        condition = true;
        this.al = al;
    }
    
    public void run(){
        int speed = 10;
        int x = base.getCurx()+(baseWidth/2), y =base.getCurY()-20;
        bullet = new JLabel(bulletImg);
        while(condition){
            bullet.setBounds(x,y,20,20);
            drawpane.add(bullet); 
            y-=speed;
            if(y<0){
                condition = false;
            }
            try { Thread.sleep(50); } 
            catch (InterruptedException e) { e.printStackTrace(); }  
        }
         synchronized(this) {
            drawpane.remove(bullet);
            bullet.setLocation(-500,-500);
            drawpane.validate();
            drawpane.repaint();
          //  al.remove(this);
         }
        
    }
    public synchronized void setCondition(){
        condition = false;
    }
    public JLabel getBullet(){
        return bullet;
    }
      public synchronized boolean getCondition(){
        return condition;
    }
    
}
