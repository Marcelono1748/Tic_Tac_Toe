package com.mycompany.pongproject;


public class PongProject {
    
    public static void main(String[] args) {
       Start okno = new Start();
        //Scoreboard okno = new Scoreboard();
        Thread Start = new Thread(okno);
        Start.start();
        
     
    }
}
