package com.mycompany.pongproject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener, ActionListener {
    public boolean wcisk[] = new boolean[128];
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        wcisk[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        wcisk[e.getKeyCode()] = false;
    }
    
    public boolean iswcisk(int klaw){
        return wcisk[klaw];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
