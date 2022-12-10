import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JOptionPane;
//import java.applet.*;

public class Wzq extends JFrame implements ActionListener,MouseListener{
  //Declare local Viriaties
private JMenuBar jmb;
private JMenu fileMenu;
private JMenu optionMenu;
private JMenu helpMenu;
private Board chessboard;
private MessagePanel mp;
private Label h1,h2,h3,h4;

public static int SIZE=19;
public static int POINT=SIZE*SIZE;

public static int CELL_SIZE=800;
//���� ���

public static Cell cells[][]= new Cell[SIZE][SIZE] ;


  

//?����������
public static Step  steps[]= new Step[POINT];

public static int turn;
Random r=new Random();

public static int state_value[]=new int[12];           //״̬ ��ֵ �Ķ�Ӧ
public static int boardMatrix[][]=new int[SIZE][SIZE];     //���̾���
public static int boardMatrixTemp[][]=new int[SIZE][SIZE]; //����ʱʹ�õ����̾��� 

    { for(int i=0;i<SIZE;i++)                           //��ʼ��
          for(int j=0;j<SIZE;j++)
             {
              boardMatrix[i][j]=3;
              boardMatrixTemp[i][j]=3;
              
              }
       turn=0;
       
    }

    {for(int i=0;i<POINT;i++)
      { steps[i]=new Step();     //it is neccessary
         steps[i].Step();
       }
    }

   {state_value[0]=0;
    state_value[1]=1;
     /*for(int i=1;i<12;i++)
      { 
        state_value[i]=state_value[i-1]*4+1;
       }
    */

    state_value[2]=state_value[1]*4+1;
    state_value[3]=state_value[2]+1;
    state_value[4]=state_value[3]*4+1;
    state_value[5]=state_value[4]+1;
    state_value[6]=state_value[5]*4+1;
    state_value[7]=state_value[6]+1;
    state_value[8]=state_value[7]*4+1;


    state_value[9]=state_value[8]*7+1;
    state_value[10]=state_value[9]*8+1;
    state_value[11]=state_value[10]*7+1;
    }
  

private static boolean computerFirst=false;
public static int rank=1;
        
 public void Wzq(){
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    //setSize(700,600);
    //setSize(600,500);
    setSize(SIZE*50,SIZE*45);
    
    setTitle("��ӭʹ�ÿ���������");
    jmb=new JMenuBar();
    fileMenu=new JMenu("�ļ�");
    optionMenu=new JMenu("ѡ��");
    helpMenu=new JMenu("����");
    JMenuItem hp=new JMenuItem("����...");
    hp.addActionListener(this);
    helpMenu.add(hp);

    JMenuItem newGame =  new JMenuItem("����Ϸ");
     newGame.addActionListener(this);
     fileMenu.add(newGame);

    JMenuItem huiqi=new JMenuItem("����");
    huiqi.addActionListener(this);
     fileMenu.add(huiqi);


    fileMenu.addSeparator();
    JMenuItem exit= new JMenuItem("�˳�");
    exit.addActionListener(this);
    fileMenu.add(exit);

    JMenuItem first= new JMenuItem("����");
    first.addActionListener(this);
    optionMenu.add(first);
    

    JMenuItem last=new JMenuItem("����");
    last.addActionListener(this);
    optionMenu.add(last);

    JMenuItem twomen=new JMenuItem("˫����Ϸ");
    twomen.addActionListener(this);
    optionMenu.add(twomen);

    JMenu diffict= new JMenu("�Ѷ�");
    JRadioButtonMenuItem easy=new JRadioButtonMenuItem("�򵥵ĵ���");
    easy.addActionListener(this);
    JRadioButtonMenuItem middle=new JRadioButtonMenuItem("�еȵĵ���");
    middle.addActionListener(this);
    JRadioButtonMenuItem crazy= new JRadioButtonMenuItem("���˷���ĵ���");
    crazy.addActionListener(this);
    
    ButtonGroup group = new ButtonGroup();
    group.add(easy);
    group.add(middle);
    group.add(crazy);
    diffict.add(easy);
    diffict.add(middle);
    diffict.add(crazy);


    
    optionMenu.add(diffict);
    jmb.add(fileMenu);
    jmb.add(optionMenu);
    jmb.add(helpMenu);

    setLayout(new BorderLayout());
    add(jmb,BorderLayout.NORTH);
   //menu definition over
   
   chessboard=new Board();
   chessboard.setLayout(new GridLayout(SIZE,SIZE,0,0));

   //chessboard.setSize(300,300);
   chessboard.setBackground(Color.lightGray);
    chessboard.setVisible(true);

    for(int i=0;i<SIZE;i++)
      for(int j=0;j<SIZE;j++)
           {cells[i][j]=new Cell();
            cells[i][j].Cell(CELL_SIZE,CELL_SIZE,i,j);// It takes me so many time !!!!!! 
            
            //cells[i][j].setSize(CELL_SIZE,CELL_SIZE);
            cells[i][j].setVisible(true);
          // cells[i][j].addMouseListener(this);
            chessboard.add(cells[i][j]);
            }
   

 add(chessboard,BorderLayout.CENTER);

 
 mp=new MessagePanel();
 mp.setFont(new Font("Serif",Font.BOLD,16));
 mp.MessagePanel();
 mp.setLayout(new FlowLayout(FlowLayout.LEFT));
 mp.setSize(150,SIZE*30);  
  h1=new Label("��Ϸ����:           ");
  h2=new Label("  ���ӳ���ʤ        ");
  h3=new Label("����������Ϸ����:    "); 
  h4=new Label("   �򵥵ĵ���   ");
  
 
 mp.add(h1);
 mp.add(h2);
 mp.add(h3);
 mp.add(h4);
    //mp.add(h,BorderLayout.NORTH);
     //mp.add(h1,BorderLayout.CENTER);
   mp.setBackground(Color.lightGray);
   mp.setVisible(true);
  add(mp,BorderLayout.EAST);    //Show message
  setVisible(true);
  }
public static int isWin(int x){
  int count,i,j,k;
  for(i=0;i<SIZE;i++)  //�м��
   {
     count=0;
     
     for(j=0;j<SIZE;j++)
      {if(boardMatrix[i][j]==x)
         { count++;
                     //���������� ��
           if (count==5) return 1;
          }
         else {count=0;  }
       }
  
    }
   for(j=0;j<SIZE;j++)  //�м��
   {
     count=0;
     for(i=0;i<SIZE;i++)
      {if(boardMatrix[i][j]==x)
         { count++;
                    //���������� ��
           if (count==5) return 1;
          }
         else {count=0;  }
       }
  
      } 
   //�������б��
  for(i=4;i<SIZE;i++)
   {   count=0;
       k=i;
       j=0;
      while((j<=i)&&(k>=0))
       { if(boardMatrix[k][j]==x)
          { count++;
            
            if (count==5)
              return 1;
           }
          else {count=0; }
        k--;
        j++;
       }//end of while  
    }
//�������б��
 
 for(j=1;j<11;j++)
  {   i=SIZE-1;
      count=0;
      k=j;
    while((k<SIZE)&&(i>=0))
      {if(boardMatrix[i][k]==x)
          { count++;
            
            if (count==5)
              return 1;
           }
          else {count=0;  }
        k++;
        i--;
      }//end of while
   }
// left below check
for(j=4;j<SIZE;j++)
   {   count=0;
       k=SIZE-j;
       i=0;
      while((i<=j)&&(k<SIZE))
       { if(boardMatrix[k][i]==x)
          { count++;
            
            if (count==5)
              return 1;
           }
          else {count=0; }
        k++;
        i++;
       }//end of while  
    }

//����
for(i=SIZE-1;i>=4;i--)
  {   j=SIZE-1;
      count=0;
      k=i;                 // k������
    while((k>=0)&&(j>=0))
      {if(boardMatrix[k][j]==x)
          { count++;
            
            if (count==5)
              return 1;
           }
          else {count=0; }
        k--;
        j--;
      }//end of while
   }


   return 0;
}


public int getState(int x,int y,int side,int direction){
 
 int count[]=new int[3];
 int i,j,ii,jj,countSide,result;
 boolean block1,block2,endAlive1,endAlive2; // ���� �� ���յ� 
 int count2;
 boolean end;   //�÷����Ƿ��Ѿ������ս�
 for(i=0;i<3;i++)
   count[i]=0;

 i=x;
 j=y;
 result=0;
 block1=false;
 block2=false;
 endAlive1=false;
 endAlive2=false;
 end=false;
 count2=0;

 if(direction==1)   //ˮƽ����
   {
     jj=y+1;  //�������ж�
     end=false;

     while(((jj)<SIZE)&&(!block1)&&(!endAlive1))
      { if((boardMatrix[i][jj]==side)&&(!end))
          count[side]++;
         else
           if(boardMatrix[i][jj]==3)
             if((count[side]==0)&&((count[side]+count[2])<5))
              {                         //�÷���û��������������,�������ո�
                end=true;
                count[2]++;
              }
               else  endAlive1=true;  //������������������,�������ո� :Ϊ���ս�
        if((boardMatrix[i][jj]!=side)&&(boardMatrix[i][jj]!=3))
           block1=true;               //�����Է�����
        jj++;
      }
     if((jj==SIZE)&&(count[2]==0)) block1=true;

    count2=count2+count[2];

    jj=y-1;  countSide=0; end=false; count[2]=0 ;//�����ж�

     while(((jj)>=0)&&(!block2)&&(!endAlive2))
      { if((boardMatrix[i][jj]==side)&&(!end))
          {count[side]++;countSide++;}
         else
           if(boardMatrix[i][jj]==3)
              if((countSide==0)&&((countSide+count[2])<5))//�÷���û��������������,�������ո�
                {
                 end=true;                      //�����ս�
                 count[2]++;
                }
               else  endAlive2=true;  //������������������,�������ո� :Ϊ���ս�
        if((boardMatrix[i][jj]!=side)&&(boardMatrix[i][jj]!=3))
           block2=true;               //�����Է�����
        jj--;
      }
     
    if((jj<0)&&(count[2]==0)) block2=true;
    count2=count2+count[2];
   }                              //end of if (derection==1)

 if(direction==2){      //��ֱ����

   ii=x+1; end=false;
   while(((ii)<SIZE)&&(!block1)&&(!endAlive1))
      { if((boardMatrix[ii][j]==side)&&(!end))
          count[side]++;
         else
           if(boardMatrix[ii][j]==3)
              if((count[side]==0)&&((count[side]+count[2])<5))      
                {
                 end=true;
                 count[2]++;
                }
               else  endAlive1=true;  
        if((boardMatrix[ii][j]!=side)&&(boardMatrix[ii][j]!=3))
           block1=true; 
        ii++;              
      }
   if((ii==SIZE)&&(count[2]==0)) block1=true; 

    count2=count2+count[2];
    ii=x-1;  countSide=0; end=false; count[2]=0 ;

     while(((ii)>=0)&&(!block2)&&(!endAlive2))
      { if((boardMatrix[ii][j]==side)&&(!end))
          {count[side]++;countSide++;}
         else
           if(boardMatrix[ii][j]==3)
              if((countSide==0)&&((countSide+count[2])<5))   
                {
                 end=true;   
                 count[2]++;
                }
               else  endAlive2=true;  
        if((boardMatrix[ii][j]!=side)&&(boardMatrix[ii][j]!=3))
           block2=true; 
       ii--;
      }
   
   if((ii<0)&&(count[2]==0)) block2=true;
   count2=count2+count[2];
 }
 if(direction==3){   //45�Ƚ� 

  ii=x+1;  jj=y-1;  end=false;
   while(((ii)<15)&&((jj)>=0)&&(!block1)&&(!endAlive1))
      { if((boardMatrix[ii][jj]==side)&&(!end))
          count[side]++;
         else
           if(boardMatrix[ii][jj]==3)
              if((count[side]==0)&&((count[side]+count[2])<5))      
                {
                 end=true;   
                 count[2]++;
                }
               else  endAlive1=true;  
        if((boardMatrix[ii][jj]!=side)&&(boardMatrix[ii][jj]!=3))
           block1=true;   
        ii++;
        jj--;            
      }
   if(((ii==SIZE)||(jj<0))&&(count[2]==0)) block1=true;
  count2=count2+count[2];

  ii=x-1;  jj=y+1;  countSide=0;  end=false; count[2]=0 ;
   while(((ii)>=0)&&((jj)<SIZE)&&(!block2)&&(!endAlive2))
      { if((boardMatrix[ii][jj]==side)&&(!end))
          {count[side]++;countSide++;}
         else
           if(boardMatrix[ii][jj]==3)
              if((countSide==0)&&((countSide+count[2])<5))     
                {
                 end=true;   
                 count[2]++;
                }
               else  endAlive2=true;  
        if((boardMatrix[ii][jj]!=side)&&(boardMatrix[ii][jj]!=3))
           block2=true; 
       ii--;
       jj++;              
      }
   if(((ii<0)||(jj==SIZE))&&(count[2]==0)) block2=true;
  count2=count2+count[2];
 }


 if(direction==4){   //135 �Ƚ�

   ii=x-1;  jj=y-1;  end=false;
   while(((ii)>=0)&&((jj)>=0)&&(!block1)&&(!endAlive1))
      { if((boardMatrix[ii][jj]==side)&&(!end))
          count[side]++;
        
           if(boardMatrix[ii][jj]==3)
              if((count[side]==0)&&((count[side]+count[2])<5))      
                {
                 end=true;   
                 count[2]++;
                }
               else  endAlive1=true;  
        if((boardMatrix[ii][jj]!=side)&&(boardMatrix[ii][jj]!=3))
           block1=true;  
        ii--;
        jj--;             
      }
   if(((ii<0)||(jj<0))&&(count[2]==0)) block1=true;
   count2=count2+count[2];

  ii=x+1;  jj=y+1;  countSide=0; end=false; count[2]=0 ;
   while(((ii)<SIZE)&&((jj)<SIZE)&&(!block2)&&(!endAlive2))
      { if((boardMatrix[ii][jj]==side)&&(!end))
          {count[side]++; countSide++;}
        
           if(boardMatrix[ii][jj]==3)
              if((countSide==0)&&((countSide+count[2])<5))      
                {
                 end=true;   
                 count[2]++;
                }
               else  endAlive2=true;  
        if((boardMatrix[ii][jj]!=side)&&(boardMatrix[ii][jj]!=3))
           block2=true; 
        ii++;
        jj++;              
      }
    if(((ii==SIZE)||(jj==15))&&(count[2]==0)) block2=true;
   count2=count2+count[2];
 }

//������,����ĳһ��״̬
 if(((count[side]+count2)<4)&&(block1)&&(block2))
   {
     result=0;
     return result;                 //���״̬
   }
 
  switch(count[side]){
    case 0: if(block1||block2) result=1;
              else result=2;
            break;
    case 1: if(block1||block2) result=3;  //��1
              else result=4;             //��1
            break;
    case 2: if(block1||block2) result=5;
              else result=6;
            break;
    case 3: if(block1||block2) result=7; 
              else result=8;           //��3 
            break;
    case 4: if(block1||block2) result=9;
              else result=10;         //��4
            break;
    default: result=11;
            break;
  }
return result;
}

public void refreshTempMatrix(){
  for(int i=0;i<SIZE;i++)
    for(int j=0;j<SIZE;j++)
      boardMatrixTemp[i][j]=boardMatrix[i][j];
 }

public int benefit(int x,int y,int side){     //side ����x y λ�õ�����
  int result;
  result=0;
  for(int i=1;i<5;i++)
   {
     result=result+state_value[getState(x,y,side,i)];
   }

  return result;
}

public int caculateValue(int x,int y){

  int result;
  result=0;

  for(int i=1;i<5;i++)
   {
     result=result+state_value[getState(x,y,0,i)]+state_value[getState(x,y,1,i)];
   }

  return result;
}

public  void computerGo1(){

  int tempi,tempj,value,tempValue;
   tempi=0;
   tempj=0;
   tempValue=0;
   value=0;

    for(int i=0;i<SIZE;i++)
      for(int j=0;j<SIZE;j++)
        
         if(boardMatrix[i][j]==3)
          {
            tempValue=caculateValue(i,j);
            if(tempValue>value)
              {
                value=tempValue;
                tempi=i;
                tempj=j;
              }
          }

    boardMatrix[tempi][tempj]=0;
    cells[tempi][tempj].setWhoTake(4);
    if((turn-2)>=0)
        cells[steps[turn-2].getI()][steps[turn-2].getJ()].setWhoTake(0);
    if(turn<POINT)                                
            {steps[turn].setI(tempi);
             steps[turn].setJ(tempj);
             steps[turn].setWhoTake(0);
             turn++;
            }
      else JOptionPane.showMessageDialog(this,"     ���壡\n �嵽��  "+turn+"   ��.  ","��������",JOptionPane.INFORMATION_MESSAGE);
    
 }
public void computerGo2(){

 int roadi,roadj,roadm,roadn;
 int i,j,m,n;
 int totalValue,tempTotal;

 totalValue=-999999999;
 tempTotal=0;
 roadi=0;
 roadj=0;
 roadm=0;
 roadn=0;

 for(i=0;i<SIZE;i++)
  for(j=0;j<SIZE;j++)
   for(m=0;m<SIZE;m++)
     for(n=0;n<SIZE;n++)

      if((boardMatrix[i][j]==3)&&(boardMatrix[m][n]==3))
        {
         tempTotal=0;
         if((i!=m)||(j!=n))
          {
            tempTotal=tempTotal+caculateValue(i,j);
            //tempTotal=tempTotal+benefit(i,j,0);
            boardMatrix[i][j]=0;
            tempTotal=tempTotal-benefit(m,n,1);
            boardMatrix[i][j]=3;
          }
           
          
         if(totalValue<tempTotal)
          {
            totalValue=tempTotal;
            roadi=i;
            roadj=j;
            roadm=m;
            roadn=n;

          }
       }
    boardMatrix[roadi][roadj]=0;
    cells[roadi][roadj].setWhoTake(4);
    if((turn-2)>=0)
        cells[steps[turn-2].getI()][steps[turn-2].getJ()].setWhoTake(0);
    if(turn<POINT)                                
            {steps[turn].setI(roadi);
             steps[turn].setJ(roadj);
             steps[turn].setWhoTake(0);
             turn++;
            }
      else JOptionPane.showMessageDialog(this,"     ���壡\n �嵽��  "+turn+"   ��.  ","��������",JOptionPane.INFORMATION_MESSAGE);
 }
public void computerGo3(){
//����ʵ�ֵ������е��Ѷȵ�,ʵ��ʱ Ӧ���ų� ����������� �㷨 .��Ƴ�ѧϰ�ͻ� ��ֽ����

  int roadi,roadj,roadm,roadn;
 int i,j,m,n;
 int totalValue,tempTotal;

 totalValue=-999999999;
 tempTotal=0;
 roadi=0;
 roadj=0;
 roadm=0;
 roadn=0;

 for(i=0;i<SIZE;i++)
  for(j=0;j<SIZE;j++)
   for(m=0;m<SIZE;m++)
     for(n=0;n<SIZE;n++)

      if((boardMatrix[i][j]==3)&&(boardMatrix[m][n]==3))
        {
         tempTotal=0;
         if((i!=m)||(j!=n))
          {
            tempTotal=tempTotal+caculateValue(i,j);
            //tempTotal=tempTotal+benefit(i,j,0);
            boardMatrix[i][j]=0;
            tempTotal=tempTotal-4*caculateValue(m,n);
            boardMatrix[i][j]=3;
          }
           
          
         if(totalValue<tempTotal)
          {
            totalValue=tempTotal;
            roadi=i;
            roadj=j;
            roadm=m;
            roadn=n;

          }
       }
    boardMatrix[roadi][roadj]=0;
    cells[roadi][roadj].setWhoTake(4);
    if((turn-2)>=0)
        cells[steps[turn-2].getI()][steps[turn-2].getJ()].setWhoTake(0);
    if(turn<POINT)                                
            {steps[turn].setI(roadi);
             steps[turn].setJ(roadj);
             steps[turn].setWhoTake(0);
             turn++;
            }
      else JOptionPane.showMessageDialog(this,"     ���壡\n �嵽��  "+turn+"   ��.  ","��������",JOptionPane.INFORMATION_MESSAGE);
}

public void actionPerformed(ActionEvent e){
   int i,j;
   
   if (e.getActionCommand()=="����")
       {
        if((isWin(1)==1)||(isWin(0)==1))
          for(i=0;i<SIZE;i++)
             for(j=0;j<SIZE;j++)
                cells[i][j].addMouseListener(this);
        if (turn>0)
         { turn--;
            i=steps[turn].getI();
            j=steps[turn].getJ();
           cells[i][j].regreat();
           boardMatrix[i][j]=3;
         }
        if (turn>0)
         { turn--;
            i=steps[turn].getI();
            j=steps[turn].getJ();
           cells[i][j].regreat();
           boardMatrix[i][j]=3;
         }
       }


  if (e.getActionCommand()=="�˳�")
     System.exit(0);
  if (e.getActionCommand()=="����Ϸ")
   {for(i=0;i<SIZE;i++)
      for(j=0;j<SIZE;j++)
        {cells[i][j].setWhoTake(3);
          boardMatrix[i][j]=3; 
           cells[i][j].addMouseListener(this);
          

        }
     turn=0;
     //score[7][7]=1;
     if(rank==1)
       h4.setText("   �򵥵ĵ���       "); 
    }
 if (e.getActionCommand()=="����") 
   { 
    for(i=0;i<SIZE;i++)
      for(j=0;j<SIZE;j++)
        {cells[i][j].setWhoTake(3);
          boardMatrix[i][j]=3; 
           cells[i][j].addMouseListener(this);
          

        }
     turn=0;
    
           computerFirst=true;
           boardMatrix[7][7]=0;
           cells[7][7].setWhoTake(0);
          // rank=1;
         // h4.setText("   �򵥵ĵ���       ");
     
   }

   //}
 if (e.getActionCommand()=="����") 
   { 
    for(i=0;i<SIZE;i++)
      for(j=0;j<SIZE;j++)
        {cells[i][j].setWhoTake(3);
          boardMatrix[i][j]=3; 
           cells[i][j].addMouseListener(this);
          

        }
     turn=0;
    
           computerFirst=false;
          
     
   }
if (e.getActionCommand()=="���˷���ĵ���") 
  { rank=3; h4.setText("  ���˷���ĵ���     ");}
    
if (e.getActionCommand()=="�еȵĵ���") 
   {rank=2;h4.setText("   �еȵĵ���       ");}
   
if (e.getActionCommand()=="�򵥵ĵ���") 
   {rank=1; 
    
     h4.setText("   �򵥵ĵ���       ");
   }
if (e.getActionCommand()=="˫����Ϸ") 
  {
     rank=4; 
     h4.setText("   ����       ");  
     for(i=0;i<SIZE;i++)
      for(j=0;j<SIZE;j++)
        {cells[i][j].setWhoTake(3);
          boardMatrix[i][j]=3; 
           cells[i][j].addMouseListener(this);
          

        }
     turn=0;
   
  }

if (e.getActionCommand()=="����...") 
 JOptionPane.showMessageDialog(this,"������:  ��������  QQ:   149398234\n    Copyright   2006    All Rights Reserved","����������",JOptionPane.INFORMATION_MESSAGE);
    
  
  }

public void mouseClicked(MouseEvent e){
  //mp.setMessage("Y");
 // mp.showMessage(1);
  

 if(rank!=4)
  { for(int i=0;i<SIZE;i++)
     for(int j=0;j<SIZE;j++)
        if ((cells[i][j]==e.getSource())&&(boardMatrix[i][j]==3))
       
         {  boardMatrix[i][j]=1;
            cells[i][j].setWhoTake(1);
                //score[i][j]=0;

            //ii=i;
            //jj=j;
            if(turn<POINT)                                // person has take a step
             {steps[turn].setI(i);
               steps[turn].setJ(j);
               steps[turn].setWhoTake(1);
               turn++;
              
             }
          
        
   

           if(isWin(1)==1) {
           //System.out.println("you are winner");
                JOptionPane.showMessageDialog(this,"    ��ϲ�㣬��ʤ��\n �嵽��    "+turn+"    ��","��ϲ",JOptionPane.INFORMATION_MESSAGE);
                
              // add code to remove actionListener here
                for(int i1=0;i1<SIZE;i1++)
                  for(int j1=0;j1<SIZE;j1++)
                    {
                            
                       cells[i1][j1].removeMouseListener(this);
           
                     }




            }else
                {if (rank==1)
                   computerGo1();
                   if (rank==2)
                    computerGo2();
                  if (rank==3)
                    computerGo3();
                  }
         if(isWin(0)==1){
                         JOptionPane.showMessageDialog(this,"     ʧ�ܣ�\n �嵽��  "+turn+"   ��.  ","������",JOptionPane.INFORMATION_MESSAGE);
    
                         for(int i1=0;i1<SIZE;i1++)
                             for(int j1=0;j1<SIZE;j1++)
                               {
                            
                                cells[i1][j1].removeMouseListener(this);
           
                               }

                        }
       
       }//end of if
  }
     else {        // rank==4 two men

           for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                if ((cells[i][j]==e.getSource())&&(boardMatrix[i][j]==3))
                   
                      { if((turn%2)==0)
                          {
                            boardMatrix[i][j]=0;
                            cells[i][j].setWhoTake(0);
                          }
                        if((turn%2)==1) 
                           {
                             boardMatrix[i][j]=1;
                             cells[i][j].setWhoTake(1);
                           }
                         if(turn<POINT)
                            { steps[turn].setI(i);
                              steps[turn].setJ(j);
                              steps[turn].setWhoTake(turn%2);
                               turn++;
                              
                             }
                       }
               if(isWin(1)==1){
                  JOptionPane.showMessageDialog(this,"       �ڷ���ʤ��\n �嵽��  "+turn+"   ��","��ϲ",JOptionPane.INFORMATION_MESSAGE);
                  for(int i1=0;i1<SIZE;i1++)
                     for(int j1=0;j1<SIZE;j1++)
                         {
                            
                             cells[i1][j1].removeMouseListener(this);
           
                          }
                 
                }
              if(isWin(0)==1){
                  JOptionPane.showMessageDialog(this,"       �׷���ʤ��\n �嵽��  "+turn+"   ��","��ϲ",JOptionPane.INFORMATION_MESSAGE);
              
                  for(int i1=0;i1<SIZE;i1++)
                             for(int j1=0;j1<SIZE;j1++)
                               {
                            
                                cells[i1][j1].removeMouseListener(this);
           
                               }
   
             }
        }
   
 }      
   
 public void mouseExited(MouseEvent e){
}
 public void mouseEntered(MouseEvent e){
}

public void mouseReleased(MouseEvent e){
}
public void mousePressed(MouseEvent e){
}

 public static void main (String[] args){
    Wzq frame=new Wzq(); 
    frame.Wzq();
   // if (frame.computerFirst)
   //   frame.computerGo1();
 
  }    
} 