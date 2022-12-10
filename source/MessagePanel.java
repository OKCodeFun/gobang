import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;


public class MessagePanel extends JPanel{
 private int token;
 private int x;
 private int y;
 private String message="jjj";
 {token=1;}
 public void MessagePanel(){
   x=0;
   y=0;
  setVisible(true);
  setBackground(Color.lightGray);
  setSize(350,600);
 }

public void setMessage(String s){
  message=s;
 
  
  
}

 public void paintComponet(Graphics g){
     super.paintComponent(g);
    g.setColor(new Color(153,102,0));
   g.drawRect(0,0,getWidth(),getHeight());

   g.setColor(Color.darkGray);
   g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
   g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
     
    
    /* FontMetrics fm=g.getFontMetrics();
     int w=fm.stringWidth(message);
     int h=fm.getAscent();
     x=getWidth()/2-w/2;
     y=getHeight()/2-h/2;*/

     g.setColor(Color.green);
       g.drawRect(getWidth()/2-15,getHeight()/2-15,getWidth()/2+15,getHeight()/2+15);  

    if (token==1)
     //g.drawString(message,50,100);
     { System.out.println("you lose");
          
       g.setColor(Color.black);
       g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
       g.drawString(message,50,100);
    }
    if(token==0)
     g.drawString(message,x,y);
 }
public void showMessage(int x){
  token=x;
  repaint();
}
public Dimension getPreferredSize(){
 return new Dimension(150,100);
}
public Dimension getMinimumSize(){

return new Dimension(100,100);
}
 }