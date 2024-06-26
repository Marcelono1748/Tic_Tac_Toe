package com.mycompany.pongproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;



public class Scoreboard extends JFrame implements Runnable, ActionListener {
    String PL1, PL2, Point1, Point2, Score, Bounce, Speed;
    JButton Reset;
    
    Scoreboard(){
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
        

        
        JTable table = new JTable ();
        
        
       
        table.setBounds (200,25, 660, 415);
        table.setFont(new Font("Roboto", Font.PLAIN, 10));
      
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setVisible(true);
        table.setRowHeight((390/10));
        table.setEnabled(false);       
        table.getTableHeader().setReorderingAllowed(false);
        
        JLabel First = new JLabel("★★★");
        First.setFont(new Font("Ayuthaya", Font.BOLD,Stałe.font_size));
        First.setBounds(130,20, 250, 80);
        First.setForeground(Color.yellow);
        panel.add(First);
        
        JLabel Second = new JLabel("★★");
        Second.setFont(new Font("Ayuthaya", Font.BOLD,Stałe.font_size));
        Second.setBounds(150,60, 250, 80);
        Second.setForeground(Color.gray);
        panel.add(Second);
        
        JLabel Third = new JLabel("★");
        Third.setFont(new Font("Ayuthaya", Font.BOLD,Stałe.font_size));
        Third.setBounds(170,100, 250, 80);
        Third.setForeground(Color.ORANGE);
        panel.add(Third);
        
        JLabel The = new JLabel("THE");
        The.setFont(new Font("Ayuthaya", Font.BOLD|Font.ITALIC,Stałe.font_size));
        The.setBounds(30,35, 250, 80);
        The.setForeground(Color.WHITE);
        panel.add(The);
        
        JLabel TOP = new JLabel("TOP!");
        TOP.setFont(new Font("Ayuthaya", Font.BOLD|Font.ITALIC,38));
        TOP.setBounds(30,70, 250, 80);
        TOP.setForeground(Color.WHITE);
        panel.add(TOP);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds (200,25, 660, 415);
        scrollPane.setFont(new Font("Roboto", Font.BOLD, 16));
        
        scrollPane.setBackground(Color.white);
        scrollPane.setForeground(Color.BLACK);
        scrollPane.setVisible(true);
        scrollPane.setEnabled(false);
        
        DefaultTableModel List = (DefaultTableModel) table.getModel();
        
        List.addColumn("Player 1");
        
        List.addColumn("Points");
        List.addColumn("Player 2");
        
        List.addColumn("Points");
        List.addColumn("Total Baounces");
        List.addColumn("Max Ball Speed");
        List.addColumn("Total Score");
        
      

        Reset = new JButton("AGAIN?");
        Reset.setBounds (40,390, 120, 50);
        Reset.setFont(new Font("Ayuthaya", Font.PLAIN,Stałe.font_size));
        Reset.setBackground(Color.white);
        Reset.setForeground(Color.BLACK);
        
        Reset.addActionListener(this);
        panel.add(Reset);
        revalidate();
        repaint();
        

        try{
            SQLcon myCon = new SQLcon();
            
            
            
            String fromSQL = "select * from scoreboard order by Score DESC";
            ResultSet Ress = myCon.myStmt.executeQuery(fromSQL);
             
             while(Ress.next()){
                PL1 = Ress.getString("Player_1_Name");
                PL2 = Ress.getString("Player_2_Name");
                Point1 = String.valueOf(Ress.getInt("Points_1"));
                Point2 = String.valueOf(Ress.getInt("Points_2"));
                Score = String.valueOf(Ress.getInt("Score"));
                Bounce = String.valueOf(Ress.getInt("Ball_Bounce"));
                Speed = String.valueOf(Ress.getDouble("Ball_Speed"));
                
                String TableData[] = {PL1, Point1, PL2, Point2, Bounce, Speed, Score};
                
                
                List.addRow(TableData);

                
         
            }
            
        }catch(Exception e){
            
        }
        
        
        
        panel.add(scrollPane);
        this.add(panel);

    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
       if (ae.getSource() == Reset){
           Start okno = new Start();
           Thread Start = new Thread(okno);
           Start.start();
           this.dispose();
           
        }
    }
    
   
    
    @Override
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
