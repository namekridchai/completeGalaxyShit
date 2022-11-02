//6313123 chanachon
//6313215 tanakrit
//6313171 kridchai
//6313209 kittayut

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.xml.sax.Attributes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.sound.sampled.*;     // for sounds
import java.util.Random;

public class MainApplication extends JFrame {

    private JFrame Dialog;
    private JPanel contentpane;
    private JFrame Game_frame = null , lead_frame = null, tutor_frame = null;
    private JTextField tf;
    private JLabel drawpane;
    private JLabel letlabel, tutorlabel, leadlabel, textlabel1, textlabel2, textlabel,
            namelabel0, namelabel1, namelabel2, namelabel3, name_label1, name_label2, name_label3;
    private MySoundEffect sound;

    private MyImageIcon backImg, Text1, Text2, leaderboard, tutorial, letshit,
            jare, sub, jow, fiat, name_jow, name_sub, name_jare, name_fiat;
    private int frameWidth = 1100, frameHeight = 700;
    private int textWidth = 550, textHeight = 300;
    private int buttonWidth = 470, buttonHeight = 130;
    private int textspeed = 100;
    private int t = 0;
    

    //private int buttonCurX=;
    public static void main(String[] args) {
        new MainApplication();
    }

    public MainApplication() {
        
        setTitle("Login");
        setBounds(50, 50, frameWidth, frameHeight);     
        setResizable(false);
        setVisible(true);
        
        
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane = (JPanel) getContentPane();

        contentpane.setLayout(new BorderLayout());
        component();
    }
    
    //
    public void action(ActionEvent e){
       
        lead_frame= new New_Frame(); 
 
    }

    public void component() {
        tf = new javax.swing.JTextField();
        drawpane = new JLabel();
        backImg = new MyImageIcon("image/Background.jpg").resize(frameWidth, frameHeight);

        drawpane.setIcon(backImg);
        drawpane.setLayout(null);
        JTextField txt = new JTextField("Enter Your Name");
        txt.setFont(new Font("Play", Font.BOLD + Font.ITALIC, 25));
        txt.setBounds(380, 220, 350, 40);
        contentpane.add(txt);
        txt.setColumns(10);
        
        //button1
        JButton button1 = new JButton("Button1");

        button1.setLocation(430, 280);
        button1.setSize(280, 130);
        button1.setIcon(new ImageIcon("image/Let Start.png"));
        button1.setBorderPainted(false); 
        button1.setOpaque(false);
        button1.setContentAreaFilled(false); 
        button1.setFocusPainted(false);
        
        //move next layout of bt1
        //button1.addActionListener(e->action(e));

        //button2
        JButton button2 = new JButton("Button2");

        button2.setLocation(280, 420);
        button2.setSize(280, 130);
        button2.setIcon(new ImageIcon("image/Tutorial.png"));
        button2.setBorderPainted(false); 
        button2.setOpaque(false);
        button2.setContentAreaFilled(false); 
        button2.setFocusPainted(false);

        //button3
        JButton button3 = new JButton("Button3");

        button3.setLocation(590, 420);
        button3.setSize(290, 130);
        button3.setIcon(new ImageIcon("image/leader.png"));
        button3.setBorderPainted(false); 
        button3.setOpaque(false);
        button3.setContentAreaFilled(false); 
        button3.setFocusPainted(false);
        
        //move next layout of bt3
        button3.addActionListener(e->action(e)); 

        //action bt1
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
             
                    sound.stop();
                    Game_frame = new Frame(txt.getText(),sound); 
                    
                
            }
        });

        //action bt2
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(tutor_frame == null)
               tutor_frame = new TutorialFrame();
                
                else
                    tutor_frame.setVisible(true);
            }
        });

        //action bt3
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JButton button = (JButton) event.getSource();
                button.setEnabled(true);
            }
        });

        contentpane.add(button1);
        contentpane.add(button2);
        contentpane.add(button3);

        Text1 = new MyImageIcon("image/Galaxy shit.png").resize(900, 130);
        Text2 = new MyImageIcon("image/Galaxy shit2.png").resize(900, 130);

        //textlabel2 = new JLabel(Text2);
        textlabel = new JLabel();
        textlabel.setIcon(Text1);
        changetext();

        sound = new MySoundEffect("image/sound.wav");
        sound.playLoop();
        //Creator
        jare = new MyImageIcon("image/jare.png").resize(220, 200);
        jow = new MyImageIcon("image/jow.png").resize(220, 200);
        sub = new MyImageIcon("image/sub.png").resize(200, 170);
        fiat = new MyImageIcon("image/fiat.png").resize(200, 200);
        
        

        JLabel namelabel0 = new JLabel();
        JLabel namelabel1 = new JLabel();
        JLabel namelabel2 = new JLabel();
        JLabel namelabel3 = new JLabel();

        JLabel name_label0 = new JLabel();
        JLabel name_label1 = new JLabel();
        JLabel name_label2 = new JLabel();
        JLabel name_label3 = new JLabel();


        namelabel0 = new JLabel(jare);
        namelabel1 = new JLabel(jow);
        namelabel2 = new JLabel(sub);
        namelabel3 = new JLabel(fiat);
        namelabel0.setBounds(50, 400, 200, 200);
        namelabel0.setIcon(jare);
        namelabel1.setBounds(100, 200, 220, 200);
        namelabel1.setIcon(jow);
        namelabel2.setBounds(850, 450, 200, 170);
        namelabel2.setIcon(sub);
        namelabel3.setBounds(750, 200, 200, 200);
        namelabel3.setIcon(fiat);
        drawpane.add(namelabel0);
        drawpane.add(namelabel1);
        drawpane.add(namelabel2);
        drawpane.add(namelabel3);
         //jow
        name_jow = new MyImageIcon("image/name_jow.png").resize(200, 20);
        namelabel1.addMouseListener(new MouseAdapter() {
            JLabel name_label1 = new JLabel(name_jow);
            public void mouseEntered(MouseEvent e) {
                name_label1.setBounds(70, 160, 300, 20);
                drawpane.add(name_label1);
                   repaint();
                validate();
            }
           public void mouseExited(MouseEvent e) {
                drawpane.remove(name_label1);
                    repaint();
                validate();
            }
        });
        //jare
            name_jare = new MyImageIcon("image/name_jare.png").resize(200, 20);
        namelabel0.addMouseListener(new MouseAdapter() {
            JLabel name_label0 = new JLabel(name_jare);
            public void mouseEntered(MouseEvent e) {
                name_label0.setBounds(30, 610, 300, 20);
                drawpane.add(name_label0);
                   repaint();
                validate();
            }
           public void mouseExited(MouseEvent e) {
                drawpane.remove(name_label0);
                    repaint();
                validate();
            }
        });

        //sub
        name_sub = new MyImageIcon("image/name_sub.png").resize(200, 20);
        namelabel2.addMouseListener(new MouseAdapter() {
            JLabel name_label2 = new JLabel(name_sub);
            public void mouseEntered(MouseEvent e) {
                 // name_label2.setBounds(70, 160, 300, 20);
                name_label2.setBounds(770, 620, 300, 20);
                drawpane.add(name_label2);
                repaint();
                validate();
            }
            public void mouseExited(MouseEvent e) {
                drawpane.remove(name_label2);
                  repaint();
                validate();
            }
        });
        //fiat
        name_fiat = new MyImageIcon("image/name_fiat.png").resize(200, 20);
        namelabel3.addMouseListener(new MouseAdapter() {

            JLabel name_label3 = new JLabel(name_fiat);

            public void mouseEntered(MouseEvent e) {

                name_label3.setBounds(740, 400, 200, 20);
                drawpane.add(name_label3);
                repaint();
                validate();
            }

            public void mouseExited(MouseEvent e) {
                drawpane.remove(name_label3);
    repaint();
                validate();
            }
        });

        drawpane.add(textlabel);
        JPanel control = new JPanel();

        contentpane.add(drawpane, BorderLayout.CENTER);

        validate();

    }

    public void changetext() {
        Thread textthread = new Thread() {
            public void run() {

                int k = 0;
                textlabel.setBounds(110, 50, 900, 130);
                while (true) {

                    if (k % 2 == 0) {
                        textlabel.setIcon(Text2);
                        k++;
                    } else {
                        textlabel.setIcon(Text1);
                        k++;
                    }

                    try {

                        Thread.sleep(textspeed);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    textlabel.validate();

                }

            }
        };
        textthread.start();
    }

    class MyImageIcon extends ImageIcon {

        public MyImageIcon(String fname) {
            super(fname);
        }

        public MyImageIcon(Image image) {
            super(image);
        }

        public MyImageIcon resize(int width, int height) {
            Image oldimg = this.getImage();
            Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            return new MyImageIcon(newimg);
        }

    };

}
/*
class MySoundEffect {

    private Clip clip;

    public MySoundEffect(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
*/