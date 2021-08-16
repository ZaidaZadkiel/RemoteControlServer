import java.io.*;
import java.net.*;
import java.util.*;
import java.net.ServerSocket;

/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author www.codejava.net
 */
public class Main {
  //TODO add config file
  
  public Main() throws SocketException {
    int port_udp = 4960;
    int port_tcp = 45340;
    System.out.println("Started Server");
  
    System.out.println("UDP Port: " + port_udp);
    new Thread(new UDPListener(port_udp)).start();
    
    System.out.println("TCP Port: " + port_tcp);
    new Thread(new TCPListener(port_tcp)).start();
  }
  
  public static void main(String[] args) {
    try {
      Main server = new Main();
    } catch (Exception ex) {
      System.out.println("Socket error: " + ex.getMessage());
    }
  }
}