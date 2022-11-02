import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;     // for sounds
import java.util.Random;
import java.util.Scanner;

public class New_Frame extends JFrame {
    private JTable table; 
    private JPanel contentpane;
    JScrollPane s;
    public New_Frame(){
       setTitle("Leader Board");
        setBounds(100, 50, 700, 400);
        setResizable(false);
	setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(new FlowLayout());
        
        String []colum = {"name","score","min","sec"};
        ArrayList<Player> Al2 = new ArrayList<Player>();
        int row = 0;
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
                row++;
                System.out.println(row);
            }
            inFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        Object[][] data = new Object[row][4];
        for(int i = 0;i<row;i++){
            for(int j = 0;j<4;j++){
                if(j==0)
                    data[i][j] = Al2.get(i).getName();
                else if(j==1)
                    data[i][j] = Al2.get(i).getScore();
                else if(j==2)
                    data[i][j] = Al2.get(i).getMin();
                else if(j==3)
                    data[i][j] = Al2.get(i).getSec();
            
            }
        }
        table = new JTable(data,colum);
        table.setPreferredScrollableViewportSize(new Dimension(500,350));
        table.setFillsViewportHeight(true);
        
         s = new JScrollPane(table);
        contentpane.add(s);
         validate();
    }
     
     public static void main(String[] args){    
         New_Frame  n =new New_Frame();
     
     } 
}
