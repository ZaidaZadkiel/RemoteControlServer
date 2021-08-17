package com.zaidazadkiel.remotecontrol;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDB implements Runnable{
  ConfigDB(){
    System.out.println("start config");
  }
  
  @Override
  public void run() {
    System.out.println("run config");
    createConfig();
  }
  
  public static void createConfig() {
    File directory = new File("config");
    if (!directory.exists()){
      directory.mkdir();
    }
    
    // SQLite connection string
    String url = "jdbc:sqlite:config/SSSIT.db";
    
    // SQL statement for creating a new table
    String sql = "CREATE TABLE IF NOT EXISTS config (\n"
      + " id integer PRIMARY KEY,\n"
      + " name text NOT NULL,\n"
      + " capacity real\n"
      + ");";
    
    //there are different screens which can be configured
    // each screen is composed with different buttons, widgets, sliders, knobs etc
    // each widget has an ID
    // this ID is sent to the server which then executes the configured action
    
    try{
      Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement();
      stmt.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}