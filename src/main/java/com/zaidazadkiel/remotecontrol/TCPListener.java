package com.zaidazadkiel.remotecontrol;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPListener implements Runnable{
  
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;
  private int port;
  
  Robot control;
  PointerInfo mouseinfo;
  public TCPListener(int port) {
    this.port=port;
    System.out.println("TCP Port: " + port);
  }
  
  public void start(int port) throws IOException, AWTException {
    
    control = new Robot();
    mouseinfo = MouseInfo.getPointerInfo();
    
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept();
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
    byte[] address = clientSocket.getInetAddress().getAddress();
  
    String addressStr = "";
    for (int i = 0; i < 4; ++i)
    {
      int t = 0xFF & address[i];
      addressStr += "." + t;
    }
    addressStr = addressStr.substring(1);
    
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      String[] command = inputLine.split(" ");
      System.out.print("tcp:"+addressStr+" > ");
      System.out.print(String.join(",", command));
      System.out.print("\r");
      
      if("mouse".equals(command[0])){
//        System.out.println("moving mouse relative X:"+command[1]+" Y:"+command[2]);
        int x = Integer.parseInt(command[1]);
        int y = Integer.parseInt(command[2]);
        
        Point currentpos = MouseInfo.getPointerInfo().getLocation();
        control.mouseMove(currentpos.x + x, currentpos.y+y);
      }
      
      if (".".equals(inputLine)) {
        out.println("good bye");
        break;
      }
      
      out.println(inputLine);
    }
  }
  
  public void stop() throws IOException {
    in.close();
    out.close();
    clientSocket.close();
    serverSocket.close();
  }
  
  @Override
  public void run() {
    try {
      start(port);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (AWTException e) {
      System.out.println("Failed with AWT exception from Robot");
      e.printStackTrace();
    }
  }
}
