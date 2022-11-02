import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;     // for sounds
import java.util.Random;
import java.util.*;
import java.text.DecimalFormat;
import javax.swing.Timer;
import java.io.*;
public class Frame extends JFrame  implements KeyListener,MouseListener{ 
    
    private MySoundEffect sound;
    private JPanel contentpane,control;
    private JComboBox  combo;
    private JFrame  newFrame;
    private MyImageIcon indoorImg,blockImg,shitImg,bulletImg ;
    private MyImageIcon []bg;
    private JLabel drawpane=new JLabel(),ball,timeLabel = new JLabel();
    private MySoundEffect      hitSound,poopSound,gunSound;
    private JLabel [][]block = new JLabel[3][11];
    private BaseLabel base;
    private JTextField scoreText,healthText;
    private ButtonGroup bgroup1;
    private JToggleButton []   tb;
    
    private int frameWidth  = 1100,frameHeight = 700,test = 0,blockWidth = 100,blockHeight = 75;
    private int ballx  =frameWidth/2,bally = 300;
    private int baseWidth = 200,baseHeight = 50,score = 0;
    private int health = 3;
    private ArrayList<bulletThread>Al;
    private int level,end = 0,wait = 0;
    private int second = 0,min = 0;
    private Timer timer;
    private String ddSecond ,ddMinute;
    private DecimalFormat dFormat = new DecimalFormat("00");
    private String PlayerName = "PlayerName1";
    private Thread bt;
    private MyArrayList key = new MyArrayList();
   
    
    public Frame(String n ,MySoundEffect sound){
        PlayerName = n;
        this.sound = sound;
        setTitle("Test Frame");
        setBounds(50, 0, frameWidth, frameHeight);
        setResizable(false);
	setVisible(true);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        Al = new ArrayList<bulletThread>();
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        String []name = {"image/bg2.jpg","image/space.jpg","image/bg3.jpg","image/bg4.jpg","image/bg5.jpg"};
        bg = new MyImageIcon[name.length];
        for(int i = 0;i<name.length;i++)
             bg[i]    = new MyImageIcon(name[i]).resize(frameWidth, frameHeight); 
        AddComponent();
        addKeyListener( this );
        addMouseListener(this);
         addWindowListener( new WindowAdapter(){
        public void windowClosing( WindowEvent e )		
        {
           wait = 1;
            if(end==0){
                int op = JOptionPane.showConfirmDialog( new JFrame(), 
                        "are you sure you want to close this frame", "Galaxy shit", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
           if(op==1){setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);wait = 0;bt.interrupt();}
                
           else{
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
                sound.playLoop();
           }
       
           }
            else{
               setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
                sound.playLoop();
            }
           
           
        }
        } );
    }  
    public void action(ActionEvent e){
        if(newFrame==null)
                    newFrame= new New_Frame(); 
                 else
                     newFrame.setVisible(true);
    
    
    }
    
    public void AddComponent(){

        tb = new JToggleButton[5];
        bgroup1 = new ButtonGroup();
       
        tb[0] = new JRadioButton("Easy");   tb[0].setName("Easy");
        tb[1] = new JRadioButton("Medium");  tb[1].setName("Medium"); 
        tb[2] = new JRadioButton("Hard");   tb[2].setName("Hard");
        tb[3] = new JRadioButton("Harder");  tb[3].setName("Harder");
        tb[4] = new JRadioButton("Hardest");   tb[4].setName("Hardest");
                
         for(int i =0;i<tb.length;i++){
             
            tb[i].addItemListener(new ItemListener(){
                public void itemStateChanged ( ItemEvent e ){
                  
                      requestFocusInWindow();
                    if(tb[0].isSelected())
                       level = 0;    
                    else if(tb[1].isSelected())
                       level = 1;
                    else if(tb[2].isSelected())
                       level = 2;
                    else if(tb[3].isSelected())
                       level = 3;
                    else if(tb[4].isSelected())
                       level = 4;
                    
                }
            });
        }
	tb[0].setSelected(true); 
        
         for(int i = 0;i<tb.length;i++)
            bgroup1.add(tb[i]);
        String[] name = {"bg1","bg2","bg3","bg4","bg5"};
        combo = new JComboBox(name);
        combo.setSelectedIndex(0);
        
         combo.addItemListener(new ItemListener(){
            public void itemStateChanged ( ItemEvent e ){
                String name = (String)e.getItem();
                 requestFocusInWindow();
                if(name.equals("bg1"))
                   drawpane.setIcon(bg[0]);
                else if(name.equals("bg2"))
                    drawpane.setIcon(bg[1]);
                else if(name.equals("bg3"))
                    drawpane.setIcon(bg[2]);
                else if(name.equals("bg4"))
                    drawpane.setIcon(bg[3]);
                else
                    drawpane.setIcon(bg[4]);
                validate();
                   
            }
        
        });
        control = new JPanel();
        healthText = new JTextField(""+health, 5);		
	healthText.setEditable(true);
        scoreText = new JTextField(""+score, 5);		
	scoreText.setEditable(true);
        
        control.add(new JLabel("Mode : "));
        for(int i = 0;i<tb.length;i++)
            control.add(tb[i]);
        
        timeLabel.setText("00:00");
        normalTimer();
        timer.start();
        
        hitSound   = new MySoundEffect("image/beep.wav");
        poopSound =  new MySoundEffect("image/pood.wav");
        gunSound = new MySoundEffect("image/pew.wav");
        
        
        control.add(combo);
        control.add(new JLabel("Score : "));
        control.add(scoreText);
        control.add(new JLabel("HP:"));
        control.add(healthText);
       
        control.add(new JLabel("Time: "));
        control.add(timeLabel);
        indoorImg    = new MyImageIcon("image/bg2.jpg").resize(frameWidth, frameHeight); 
        drawpane.setIcon(indoorImg);
        drawpane.setLayout(null);
        drawBlock();
        setBallThread();
        contentpane.add(control,BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);  
         validate();
    
    } 
    public void drawBlock(){
        String[] name = {"image/greenn.jpg","image/redd.jpg","image/yelloww.jpg"};
         Random rand = new Random();
        int x = 0;int y = 30;
         for(int i = 0;i<3;i++){
             for(int j = 0;j<11;j++){
                  int r = rand.nextInt(3);
                  blockImg    = new MyImageIcon(name[r]).resize(blockWidth,blockHeight); 
                  block[i][j] = new JLabel(blockImg);
                  block[i][j].setBounds(x,y, blockWidth,blockHeight);
                  drawpane.add(block[i][j]);
                  x+=blockWidth;
             }
             x = 0;
             y+=blockHeight;
         }
          base = new BaseLabel("image/bluee.jpg",baseWidth,baseHeight);
          base.setMove(frameWidth/2-50,frameHeight-baseHeight-50);
          drawpane.add(base);
          
          blockImg    = new MyImageIcon("image/redd.jpg").resize(15,15);
          ball = new JLabel(blockImg);
          ball.setBounds(ballx,bally,15,15);
          drawpane.add(ball);
          
          shitImg = new MyImageIcon("image/shitt.jpg").resize(100,100);
          setPoopThread();
          
          bulletImg = new MyImageIcon("image/bullet2.jpg").resize(20,20); 
    }
    public void keyReleased( KeyEvent e )	{ }
    public void keyTyped( KeyEvent e ){ }
    public void keyPressed( KeyEvent e )
        {   
            if ( e.getKeyCode() == KeyEvent.VK_RIGHT ){base.moveRight(frameWidth); key.add('r');} 
            if ( e.getKeyCode() == KeyEvent.VK_LEFT ){base.moveLeft(frameWidth);key.add('l');}
            if ( e.getKeyCode() == KeyEvent.VK_UP ){key.add('u');}  
            if ( e.getKeyCode() == KeyEvent.VK_DOWN ){key.add('d');}
            if( (e.getKeyChar())=='a'||(e.getKeyChar()=='A')) key.add('a');
            if( (e.getKeyChar())=='b'||(e.getKeyChar()=='B')) key.add('b');
            
            if(key.checkWin()==1){
                
                synchronized(this){ score+=1000000;}
                scoreText.setText(""+score);
               
            }
            
                
        }
    public void setBallThread(){
        Thread BallThread = new Thread(){
           int speedx = 0,speedy = 10,speedx2 = 10,r = 0;
           int []range = {100,60,40,20,10};
           
            public void run(){
              
                while(true){
                     synchronized (this){
                           if(wait==1){try { this.wait(); } catch (InterruptedException e) { }}
                     }
                    Random rand = new Random();
                    r = rand.nextInt(range[level]);
                    if(r==1)
                        setPoopThread();
                      bally = bally+speedy;
                      ballx+=speedx;
                     ball.setBounds(ballx,bally,15,15);
                      try { Thread.sleep(50); } 
                    catch (InterruptedException e) { e.printStackTrace(); }  
                   
                     if ( ball.getBounds().intersects(base.getBounds()) ){
                         
                         speedy*=-1;
                         int p1 = base.getCurx()+baseWidth/2;
                        
                         
                         if(ballx==p1)
                             speedx = 0;
                         if(ballx>p1)
                             speedx = speedx2;
                         if(ballx < p1)
                             speedx = -speedx2;
                     }
                    if(ballx<0||ballx>frameWidth-20)
                         speedx *=-1;
                    else if(bally<0)
                        speedy*=-1;
                    
                    if(bally>frameHeight-baseHeight-40){
                        EndGame();
                        break;
                        
                    }
                    
                    for(int i= 0;i<3;i++){
                        for(int j = 0;j<11;j++){
                            if(ball.getBounds().intersects(block[i][j].getBounds())){
                                hitSound.playOnce();
                                updateScore();
                                drawpane.remove(block[i][j]);
                                block[i][j].setLocation(-500,-500);
                                speedy*=-1;                 
                            }       
                        }
                    }
                       repaint();
                       validate();                
                }
            }
        };
        bt = BallThread;
        BallThread.start();
    }
    public void setPoopThread(){
        Thread PoopThread = new Thread(){
            public void run(){
                  int y = 0;
                  int check = 0;
                  Random rand = new Random();
                  int r = rand.nextInt(frameWidth);
                  JLabel shit = new JLabel(shitImg);
                  boolean condition = true;
                  while(condition){
                      
                      shit.setBounds(r,y,100,100);
                      y+=10;
                      drawpane.add(shit);
                
                      repaint();
                      try { Thread.sleep(75); } 
                      catch (InterruptedException e) { e.printStackTrace(); }
                      if(y>frameHeight-baseHeight-40)
                          condition = false;
                      if(shit.getBounds().intersects(base.getBounds())){
                           updateHealth();
                           break;
                      }
                       synchronized(this){
                      
                       try{  for(int i =0;i<Al.size();i++){
                                if(shit.getBounds().intersects(Al.get(i).getBullet().getBounds())){
                                     poopSound.stop();
                                    poopSound.playOnce();
                                    updateScore();
                                    Al.get(i).setCondition();
                                    drawpane.remove(Al.get(i).getBullet());
                                    drawpane.remove(shit);
                                    repaint();
                                    validate(); 
                                    // Al.remove(Al.get(i));
                                    check = 1;
                                    break;
                        }}}
                       catch(Exception e){
                       }    
                      int check2 = 0;
                      boolean condition1 = true;
                 try{while(condition1){
                          for(int i =0;i<Al.size();i++){
                              if(!Al.get(i).getCondition()){
                                  Al.remove(Al.get(i));
                                  check2 = 1;
                                  break;
                              }
                           
                          }
                          if(check2==0)
                              condition1 = false;
                          check2=0;
                      
                      }}
                 catch(Exception e){}
                      if(check==1)
                          break;
                       }   
                  }
                     synchronized(this){drawpane.remove(shit);
                   validate();
                   repaint();}
            }
        };
       PoopThread.start();
    } 
    synchronized void updateHealth()
    { 
        health-=1;
        healthText.setText(""+health);
        if(health==0)
            EndGame();   
    }
     synchronized void updateScore()
    {
        score+=1;
        scoreText.setText(""+score);
    }
    
    public void setBulletThread(){
       bulletThread b = new bulletThread(base,drawpane,bulletImg,baseWidth,Al);
       b.start();
       Al.add(b);
       validate();
       repaint();
    }
    public void EndGame(){
        end  =1;
        String message = "your score is "+score;
        ArrayList<Player> Al2 = new ArrayList<Player>();
        try{
            Scanner inFile = new Scanner(new File("rank.txt"));
            while(inFile.hasNext()){
                String line = inFile.nextLine();
                String[] buff = line.split(",");
                String name  = buff[0];
                int score = Integer.parseInt(buff[1].trim());
                int min = Integer.parseInt(buff[2].trim());
                int seconds = Integer.parseInt(buff[3].trim());
                
                Player f = new Player(name,score,min,seconds);
                Al2.add(f);
            }
            inFile.close();
            Al2.add(new  Player(PlayerName,score,min,second));
            Collections.sort(Al2);
        }
        catch(Exception e){
            System.out.println(e);
        }
          try{ PrintWriter out = new PrintWriter("rank.txt");
            
                for(int i =0;i<Al2.size();i++){
                    out.flush();
                    out.printf(Al2.get(i).toString());
                    out.println();   
                }   
        out.close();
         }
        catch(Exception e){System.err.println(e);}
        
        
          
        JOptionPane.showMessageDialog(new JFrame(), message, "GalaxyShit",
			              JOptionPane.INFORMATION_MESSAGE );
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
   
    public void mouseClicked(MouseEvent e) {
        gunSound.stop();  
        gunSound.playOnce();
        setBulletThread();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) { }
    
    
    public void normalTimer(){
        timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(min);
                
                timeLabel.setText(ddMinute+":"+ddSecond);
                
                if(second==60){
                    second = 0;
                    min++;
                    ddMinute = dFormat.format(min);
                    timeLabel.setText(ddMinute+":"+ddSecond);            
                } 
            }
        });
            
    }    
}


