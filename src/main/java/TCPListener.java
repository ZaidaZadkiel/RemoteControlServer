import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPListener implements Runnable{
  
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;
  private int port;
  
  public TCPListener(int port) {
    this.port=port;
  }
  
  public void start(int port) throws IOException {
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
      System.out.println("tcp:"+addressStr+" > "+inputLine);
  
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
    }
  }
}
