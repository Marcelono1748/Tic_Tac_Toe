
package com.mycompany.pongproject;
import java.awt.event.KeyEvent;

public class Gracz1 {
    public Ksztauty Odbitka;
    public Input wcisk;

    public Gracz1(Ksztauty Odbitka,Input wcisk){
        this.Odbitka=Odbitka;
        this.wcisk = wcisk;
    }
    public void updat(double T){
        if(wcisk.iswcisk(KeyEvent.VK_S)){
           if((Odbitka.y+Stałe.SPEED*T)+Odbitka.wys < Stałe.OKNO_WYS-Stałe.BOTTOM){
                this.Odbitka.y += Stałe.SPEED*T;
           }
        }
        if(wcisk.iswcisk(KeyEvent.VK_W)){
            if(Odbitka.y-Stałe.SPEED*T > Stałe.GRANICA){
                this.Odbitka.y -= Stałe.SPEED*T;
            }
        }
        
    }
}
