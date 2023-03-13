/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo_java_postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolás
 */
public class Cconexion {
    
Connection conectar = null;

String usuario = "postgres";
String password = "root";
String bd = "ejemplo";
String ip = "localhost";
String puerto = "5432";

String cadena = "jdbc:postgresql://" +ip+":"+puerto+"/"+bd; 

public Connection establecerConexion(){
    
    try{
        Class.forName("org.postgresql.Driver");
        
        conectar = DriverManager.getConnection(cadena, usuario, password);
        //JOptionPane.showMessageDialog(null, "Kono Dio Da");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Masaka! "+e.toString());
    }
    
    return conectar;
}


    
}
