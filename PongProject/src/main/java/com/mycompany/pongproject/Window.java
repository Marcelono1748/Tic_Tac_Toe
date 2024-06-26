package com.mycompany.pongproject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;




public class Window extends JFrame implements Runnable {
    public static Window input;
    public Input Klawisz = new Input();
    public Graphics2D grap;
    
    public Ksztauty P1;
    public Ksztauty P2;
    public Ksztauty Pilka;
    
    public Gracz1 Gracz1;
    public Gracz2 Gracz2;
    public Pilka ball;

    
    public String PL1_Name;
    public String PL2_Name;

    
    public Scorer Scorer;
    
    public int timer = 0;
    public int ticker = 0;
    public double speed=Stałe.BALL_SPEED;
    public double FinScore=0;
    
    
    public int tick = 0;
    public int tickerest = 0;
    public int more = 0;
    public int moreCheck=0;
    
    public Window(){
    
        this.setSize(Stałe.OKNO_SZE,Stałe.OKNO_WYS);
        this.setTitle(Stałe.OKNO_TYT);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(Klawisz);
        Stałe.GRANICA = this.getInsets().top;
        Stałe.BOTTOM = this.getInsets().bottom;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
        
        grap = (Graphics2D)this.getGraphics();
        
        P1 = new Ksztauty(Stałe.BREAK,205,Stałe.ODB_SZE,Stałe.ODB_WYS,Stałe.P1_KOLOR);
        Gracz1 = new Gracz1(P1,Klawisz);
        
        
        P2 = new Ksztauty(Stałe.OKNO_SZE-Stałe.ODB_SZE-Stałe.BREAK,205,Stałe.ODB_SZE,Stałe.ODB_WYS,Stałe.P2_KOLOR);
        Gracz2 = new Gracz2(P2,Klawisz);
        
        Pilka = new Ksztauty(Stałe.OKNO_SZE/2,Stałe.OKNO_WYS/2,Stałe.PILKA,Stałe.PILKA,Stałe.PILKA_KOLOR);
        ball = new Pilka(Pilka,P1,P2);
        
        input=this;
        
        Scorer = new Scorer(0,new Font("Roboto",Font.BOLD,20),Stałe.OKNO_SZE/2,70);
        
    }
    
    public void updat(double T){

        
        Image Buffor = createImage(getWidth(),getHeight());
        Graphics buff = Buffor.getGraphics();
        this.Out(buff);
        grap.drawImage(Buffor,0,0,this);
       
        
        Gracz1.updat(T);
        Gracz2.updat(T);
        ball.updat(T);
       
        
        if (tick == 0){
          timer ++;  
        }
        
        if (ball.checker == 1){
            timer = timer - (timer * (90-ticker) / 100);
            ball.checker = 0;
            ticker = ticker +2;
        }
        if(ball.pointer == 1){
            timer = timer + (timer*(0+ticker)/100);
            ball.pointer =0;
            ticker = ticker +10;

        }
        
        
        if (speed<ball.CS){
            speed = ball.CS;
        }
        moreCheck = moreCheck+1;
        if(moreCheck >=5){
            more=more+1;
            moreCheck = 0;
        }     
        FinScore =((10*ball.Point_L+10*ball.Point_R+ball.Ball_Bounce)*(speed-150))+more;
        Scorer.Score = "" + FinScore;
        System.out.println(""+FinScore);
        
        //ScoreString = ""+ FinScore;
        // 20 to 1 sekunda
        if(timer >= 1200){
            try{
                if(timer >= 30){
                tick = 1;    
                if (tickerest == 0){
                tickerest = 1;
                SQLcon myCon = new SQLcon();
                
                System.out.println("Left: "+ball.Point_L);
                System.out.println("Right: "+ball.Point_R);
                System.out.println("Bounces: "+ball.Ball_Bounce);
                String toSQL = "insert into scoreboard(Player_1_Name,Player_2_Name,Ball_Speed,Points_1,Points_2,Ball_Bounce,Score) value('"+PL1_Name+"','"+PL2_Name+"','"+speed+"','"+ball.Point_L+"','"+ball.Point_R+"','"+ball.Ball_Bounce+"','"+FinScore+"')";
                myCon.myStmt.executeUpdate(toSQL);
                myCon.myCon.commit();
                
                    
                }
                        Scoreboard score = new Scoreboard();
                        timer=0;
                        ticker=0;
                        speed=0;
                        FinScore=0;
                        tick=0;
                        tickerest=0;
                        ball.CS=Stałe.BALL_SPEED;
                        ball.checker=0;
                        ball.pointer=0;
                        ball.Point_L=0;
                        ball.Point_R=0;
                        ball.Ball_Bounce=0;
                        more = 0;
                        moreCheck = 0;
                        this.dispose();
                }
            } catch (Exception e) {
                
            }
        }
    }

    public void Out(Graphics gr){
            Graphics2D grap = (Graphics2D)gr;
            grap.setColor(Color.BLACK);
            grap.fillRect(0, 0, Stałe.OKNO_SZE, Stałe.OKNO_WYS);
           
            P1.out(grap);
            P2.out(grap);
            Pilka.out(grap);
            Scorer.out(grap);
                    
            
        }
    

    @Override
    public void run(){
       double LF = 0.0;
       while(true){
           double fps=Czas.Timer();
           double DT = fps - LF;
           LF = fps;
           
           updat(DT);
           
           try{
               Thread.sleep(30);
           }
           catch(Exception e){
               
           }
       }
    }
    
}
