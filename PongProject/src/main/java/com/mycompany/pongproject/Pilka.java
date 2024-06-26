package com.mycompany.pongproject;



import java.util.Random;



public class Pilka {
    public Ksztauty pilka;
    public Ksztauty P1L;
    public Ksztauty P2R;
    public PongProject PL;

    int array[]=new int[]{-1,1};
    
    private Random Los = new Random();  
    


    int LosX=array[Los.nextInt(array.length)];
    int LosY=array[Los.nextInt(array.length)];
  
    private double velX = LosX * Stałe.BALL_SPEED;
    private double velY = LosY * velX/2;
    private double velXb = velX;
    private double velYb = velY;
    private double velYfix = velY;
    private final double UpX = Stałe.BALL_SPEED/30;
    private final double UpY = UpX/2;
    public double CS = Stałe.BALL_SPEED;
    public int Point_L = 0;
    public int Point_R = 0;
    public double Ball_Speed;
    public int Ball_Bounce=0;
    public int Score;
    public boolean Check = false;
    public int checker = 0;
    public int pointer = 0;
   
    
    
    
    public Pilka(Ksztauty pilka,Ksztauty P1L, Ksztauty P2R){
        this.pilka=pilka;
        this.P1L=P1L;
        this.P2R=P2R;
        
        
    }
    public void updat(double T){
        

    
        
        
        
       if(velX<0){
            if (this.pilka.x <= this.P1L.x+this.P1L.szer &&
                this.pilka.x + this.pilka.szer >= this.P1L.x &&
                this.pilka.y >= this.P1L.y &&
                this.pilka.y <= this.P1L.y+this.P1L.wys){
                
                this.velX *= -1;
                if (this.velX < 0){
                    this.velX = velX -UpX;

     
                }
                if (this.velX > 0){
                    this.velX = velX +UpX;


                }
                CS = CS + UpX;
                checker = 1;
                Ball_Bounce++;

                
            }
       }
       if(this.pilka.x + this.pilka.szer < this.P1L.x){
            Point_R ++;
            pointer=1;
            CS = Stałe.BALL_SPEED; 
            pilka.x=Stałe.OKNO_SZE/2;
            pilka.y=Stałe.OKNO_WYS/2;
            
            LosX=array[Los.nextInt(array.length)];
            LosY=array[Los.nextInt(array.length)];
            
            this.velX=velXb*LosX;
            this.velY=velYb*LosY;
            
            
            
        }
       if(velX>=0){
            if (this.pilka.x +this.P2R.szer >= this.P2R.x &&
                this.pilka.x <= this.P2R.x + this.P2R.szer &&
                this.pilka.y >= this.P2R.y &&
                this.pilka.y <= this.P2R.y+this.P2R.wys){
                
                this.velX *= -1;
                if (this.velX < 0){
                    this.velX = velX -UpX;


 
                }
                if (this.velX > 0){
                    this.velX = velX +UpX;


                    
                }
                CS = CS + UpX;
                checker = 1;
                Ball_Bounce++;
                


            }
       }
        if(this.pilka.x + this.pilka.szer > this.P2R.x+this.P2R.szer){
            Point_L ++;
            pointer=1;
            CS = Stałe.BALL_SPEED;
            pilka.x=Stałe.OKNO_SZE/2;
            pilka.y=Stałe.OKNO_WYS/2;
            LosX=array[Los.nextInt(array.length)];
            LosY=array[Los.nextInt(array.length)];

            this.velX=velXb*LosX;
            this.velY=velYb*LosY;
            }
        
            
        if(velY>=0){
            if (this.pilka.y + this.pilka.wys>Stałe.OKNO_WYS){
                this.velY *= -1;
                
                
                
                if (this.velY < 0){
                    this.velY = velY -UpY;
                    
                }
                if (this.velY > 0){
                    this.velY = velY +UpY;
                }
                CS = CS + UpY;
                
                              //  System.out.println(CS);

            }
        }
        if(velY<0){
                if (this.pilka.y < Stałe.GRANICA){
                this.velY *= -1;
                
               
                if (this.velY < 0){
                    this.velY = velY -UpY;
                }
                if (this.velY > 0){
                    this.velY = velY +UpY;
                    
                }
                 CS = CS + UpY;
                           //      System.out.println(CS);


            }
                
        
       }
        
        
       
                
       this.pilka.x+=velX*T;
       this.pilka.y+=velY*T;
       
    }  
}
