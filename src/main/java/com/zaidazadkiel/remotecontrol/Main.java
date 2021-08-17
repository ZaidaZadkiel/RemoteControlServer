package com.zaidazadkiel.remotecontrol;

public class Main {
  //TODO add config file
  
  public Main() throws Exception {
    int port_udp = 4960;
    int port_tcp = 45340;
 
    System.out.println("Started Server");
    new Thread(new UDPListener(port_udp)).start();
    new Thread(new TCPListener(port_tcp)).start();
    new Thread(new ConfigDB()).start();
    new Thread(new ConfigServer(new String[]{"hi"})).start();
  }
  
  public static void main(String[] args) {
    try {
      Main server = new Main();
    } catch (Exception ex) {
      System.out.println("Socket error: " + ex.getMessage());
    }
  }
}