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
  private DatagramSocket socket;
  private List<String> listQuotes = new ArrayList<String>();
  private Random random;
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;
  
  Thread thread;
  public UDPListener udp;
  public Main(int port) throws SocketException {
    new Thread(new UDPListener(4960)).start();
    new Thread(new TCPListener(45340)).start();
  }
  
  public static void main(String[] args) {
    System.out.println("Started Server");
    int port = 4960; //FIXME
    
    System.out.println("Port: " + port);
    
    try {
      Main server = new Main(port);
    } catch (Exception ex) {
      System.out.println("Socket error: " + ex.getMessage());
    }
  }
}