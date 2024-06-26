package com.mycompany.pongproject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Ksztauty {
    public double x,y,szer,wys;
    private Color kolor;
    
    public Ksztauty(double x, double y, double szer, double wys, Color kolor){
        this.x=x;
        this.y=y;
        this.szer=szer;
        this.wys=wys;
        this.kolor=kolor;
    }
    
    public void out(Graphics2D grap) {
        grap.setColor(kolor);
        grap.fill(new Rectangle2D.Double(x,y,szer,wys));
    }
}
