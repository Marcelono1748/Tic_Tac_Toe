
package com.mycompany.pongproject;
import java.awt.*;


public class Scorer {
    public String Score;
    public Font font;
    public double x,y;

    public Scorer(String Score,Font font,double x,double y){
        this.Score=Score;
        this.font = font;
        this.x=x;
        this.y=y;
    }
    
     public Scorer(int Score,Font font,double x,double y){
        this.Score=""+Score;
        this.font = font;
        this.x=x;
        this.y=y;
    }
    
    public void out(Graphics2D grap) {
        grap.setColor(Color.white);
        grap.setFont(font);
        grap.drawString(Score, (float)x, (float)y);
        
    }

}
