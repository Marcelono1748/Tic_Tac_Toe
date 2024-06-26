
package com.mycompany.pongproject;

import java.sql.*;
import java.util.Scanner;


public class SQLcon {
    Connection myCon;
    Statement myStmt;
    public SQLcon(){
        Scanner Sc = new Scanner(System.in);
         try{
            
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/myponggame", "root","Utajnione hasło bo dane prywatne");
            myStmt = myCon.createStatement();
            
            
            
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
    }
}
