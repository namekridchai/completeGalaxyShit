import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TutorialFrame extends JFrame {
    private JLabel label = new JLabel();
    private MyImageIcon tutor;
    private JPanel contentpane;
    JScrollPane s;
    public TutorialFrame(){
        
        
        setTitle("Tutorial");
        setBounds(150, 50, 800, 800);
        tutor = new MyImageIcon("image/tutor.png").resize(800, 800);
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(new FlowLayout());
        label.setIcon(tutor);
        contentpane.add(label);
        validate();
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
        
       
}
    }