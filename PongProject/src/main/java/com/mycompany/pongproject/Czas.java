package com.mycompany.pongproject;


public class Czas {
    public static double Start = System.nanoTime();
    
    public static double Timer() {
       return(System.nanoTime() - Start)*(1E-9); 
    }
   }
