package com.zaidazadkiel.remotecontrol;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPListener implements Runnable{
  
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;
  private int port;
  RobotControl control;
  public TCPListener(int port) {
    this.port=port;
    System.out.println("TCP Port: " + port);
  
    try {
      serverSocket = new ServerSocket(port);
      control = new RobotControl();
    } catch (AWTException | IOException e) {
      System.err.println("Error in TCPListener");
      e.printStackTrace();
    }
  }
  
  public void start(int port) throws IOException, AWTException {
    clientSocket = serverSocket.accept();
    clientSocket.setTcpNoDelay(true);

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
    System.out.println("tcp "+addressStr+" connection received");
    String inputLine;
    
    boolean debug = true;
    while ( (inputLine = in.readLine()) != null ) {
      String[] command = inputLine.split(" ");
      
      if(debug){
        System.out.print("tcp:"+addressStr+" > ");
        System.out.print(String.join(",", command));
        System.out.print("\r");
      }
  
      if (".".equals(inputLine)) {
        out.println("good bye");
        break;
      }
      
      control.sendCommand(command);
      
      out.println(inputLine);
    }
    stop();
  }
  
  public void stop()  {
    try {
      System.out.println("closing socket");
      in.close();
      out.close();
      clientSocket.close();
//      serverSocket.close();
    } catch (SocketException e) {
      System.out.println("TCP server SocketException");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("TCP server IOException");
      e.printStackTrace();
    }
  }
  
  @Override
  public void run() {
    while(true){
      try {
        System.out.println("waiting for socket");
        start(port);
        System.out.println("didnt get socket");
      } catch (SocketException e){
        stop();
//        e.printStackTrace();
      } catch (IOException e) {
        stop();
//        e.printStackTrace();
      } catch (AWTException e) {
        System.out.println("Failed with AWT exception from Robot");
        e.printStackTrace();
      } catch (Exception e) {
        System.out.println("Unknwon exception");
        e.printStackTrace();
      }
    }
  }
}
