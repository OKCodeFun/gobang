import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Cell extends JPanel implements MouseListener{

public static int SIZE=19;

 private Image imgW;
 private Image imgB;
 private ImageIcon imconW;
 private ImageIcon imconB;
 private int x;
 private int y;

 private  int whoTake;
   {
    whoTake=3;
   }
 public void Cell(int w,int h, int x,int y){
  
      setSize(w,h);
      this.x = x;
      this.y=y;
      setBackground(new Color(153,102,0));
      addMouseListener(this);

      imconB=new ImageIcon("image/black.png");
      imconW=new ImageIcon("image/white.png");

      imgW=imconW.getImage();
      imgB=imconB.getImage();
  
  }
   public void paintComponent(Graphics g){
  super.paintComponent(g);
  g.setColor(new Color(153,102,0));
  g.drawRect(0,0,getWidth(),getHeight());

  g.setColor(Color.darkGray);
  drawLine(g);
    
 
  if (whoTake==1)
    {//g.setColor(new Color(21,21,21));
     //g.fillOval(getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6);

     // π”√Õº∆¨
     g.drawImage(imgB,getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6,this);

    }
  if (whoTake==0)
    {//g.setColor(new Color(225,225,225));
     //g.fillOval(getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6);

     g.drawImage(imgW,getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6,this);

    }
  if (whoTake==3)
   
    {
     g.setColor(Color.darkGray);
     drawLine(g);
    }
 if (whoTake==4)
   {
  /* g.setColor(Color.darkGray);
   g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
   g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
   g.drawString("message",getWidth(),getHeight()/2);
 */
    //g.setColor(new Color(208,255,242));
   // g.fillOval(getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6); 
   
    g.drawImage(imgW,getWidth()/8,getHeight()/8,getWidth()*5/6,getHeight()*5/6,this);
   }
    
 }

public void drawLine(Graphics g){
//
 if((0 == x)&&(y==0)){
    g.drawLine(getWidth()/2,getHeight()/2,getWidth(),getHeight()/2);
    g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight());
    return;
}

if((0 == x)&&(y==SIZE-1)){
    g.drawLine(getWidth()/2,getHeight()/2,0,getHeight()/2);
    g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight());
    return;
}
if((0 == y)&&(x==SIZE-1)){
    g.drawLine(getWidth()/2,getHeight()/2,getWidth(),getHeight()/2);
    g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,0);
    return;
}

if((x ==SIZE-1)&&(y==SIZE-1)){
    g.drawLine(getWidth()/2,getHeight()/2,0,getHeight()/2);
    g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,0);
    return;
}
//µ⁄0––
 if(0 == x){
   //∫·’’ª≠
    g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
  // ˙œﬂª≠“ª∞Î
   g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight());
    return;
  }

//µ⁄size-1––
 if(x == (SIZE-1)){
   //∫·’’ª≠
    g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
  // ˙œﬂª≠…œ“ª∞Î
   g.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/2);
    return;
  }

//µ⁄0 lie
 if(0 == y){
   // ˙œﬂ’’ª≠
   g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
  //∫·ª≠”“±ﬂ“ª∞Î
   g.drawLine(getWidth()/2,getHeight()/2,getWidth(),getHeight()/2);
    return;
  }

//µ⁄SIZE-1 lie
 if(y == (SIZE-1)){
   // ˙œﬂ’’ª≠
   g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
  //∫·ª≠◊Û±ﬂ“ª∞Î
   g.drawLine(getWidth()/2,getHeight()/2,0,getHeight()/2);
    return;
  }

//default full draw...
     g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
     g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
}
 
 public void setWhoTake(int x){
  
   whoTake=x;
   repaint();

 }

public int getWhoTake(){
   return whoTake; 
 }

public void regreat(){
  setWhoTake(3);
  }

 public void mouseClicked(MouseEvent e){
   //if(e.getClickCount()==1)
  /*  if(whoTake==3)
     {
       if(Wzq.rank!=4)
          setWhoTake(1);
        else if((Wzq.turn%2)==0)
                setWhoTake(0);
              else setWhoTake(1);
   
       
      }


   // Wzq.turn++;
   */   
   
 }
   
 public void mouseExited(MouseEvent e){
}
 public void mouseEntered(MouseEvent e){
}

public void mouseReleased(MouseEvent e){
}
public void mousePressed(MouseEvent e){
}
}