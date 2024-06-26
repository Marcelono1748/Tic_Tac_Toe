package com.mycompany.pongproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Start extends JFrame implements Runnable, ActionListener {
    
    JButton START;
    JTextField PL1NameInput,PL2NameInput;
    String PL1_Name, PL2_Name;

    Start(){
        this.setSize(Stałe.OKNO_SZE,Stałe.OKNO_WYS);
        this.setTitle(Stałe.OKNO_TYT);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Stałe.GRANICA = this.getInsets().top;
        Stałe.BOTTOM = this.getInsets().bottom;
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 80));
        this.add(panel);
        
        JLabel Title = new JLabel("PONG!");
        Title.setFont(new Font("Ayuthaya", Font.BOLD | Font.ITALIC,38));
        Title.setBounds(Stałe.OKNO_SZE/2-75,25, 250, 80);
        Title.setForeground(Color.white);
        panel.add(Title);
        
        JLabel PL1 = new JLabel("Gracz 1");
        PL1.setFont(new Font("Ayuthaya", Font.PLAIN,Stałe.font_size));
        PL1.setBounds(100,100, 250, 80);
        PL1.setForeground(Color.white);
        panel.add(PL1);
        
        JLabel PL2 = new JLabel("Gracz 2");
        PL2.setFont(new Font("Ayuthaya", Font.PLAIN,Stałe.font_size));
        PL2.setBounds(100,300, 250, 80);
        PL2.setForeground(Color.white);
        panel.add(PL2);
        
        PL1NameInput = new JTextField();
        PL1NameInput.setBounds (350,120, 250, 40);
        PL1NameInput.setFont(new Font("Roboto", Font.BOLD, 16));
        PL1NameInput.setBackground(Color.white);
        PL1NameInput.setForeground(Color.BLACK);
        PL1NameInput.setVisible(true);
        panel.add(PL1NameInput);
        
        
        PL2NameInput = new JTextField();
        PL2NameInput.setBounds (350,320, 250, 40);
        PL2NameInput.setFont(new Font("Roboto", Font.BOLD, 16));
        PL2NameInput.setBackground(Color.white);
        PL2NameInput.setForeground(Color.BLACK);
        PL2NameInput.setVisible(true);
        panel.add(PL2NameInput);
        
        START = new JButton("START");
        START.setBounds (Stałe.OKNO_SZE/2-75,400, 120, 50);
        START.setFont(new Font("Ayuthaya", Font.PLAIN,Stałe.font_size));
        START.setBackground(Color.white);
        START.setForeground(Color.BLACK);
        
        START.addActionListener(this);
        panel.add(START);
        revalidate();
        repaint();
        
        
    }
    

    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == START){
        
        PL1_Name = PL1NameInput.getText();
        PL2_Name = PL2NameInput.getText();
        
        
            try{
                if(PL1_Name.equals("")||PL2_Name.equals("")){
                    JOptionPane.showMessageDialog(null, "Podaj nazwe gracza!");
                }
                else {
                    Window window = new Window();
                    Window.input.PL1_Name =this.PL1_Name;
                    Window.input.PL2_Name =this.PL2_Name;
                    
                    
                    Thread Window = new Thread(window);
                    Window.start();
                    this.PL1_Name = null;
                    this.PL2_Name = null;
                    this.dispose();
                }
            } catch (Exception e) {
                
            }
            
            
        }
    }
    
    public void run(){
       double LF = 0.0;
       while(true){
           double fps=Czas.Timer();
           double DT = fps - LF;
           LF = fps;
           
           
           
           try{
               Thread.sleep(30);
           }
           catch(Exception e){
               
           }
       }
    }

    
}
