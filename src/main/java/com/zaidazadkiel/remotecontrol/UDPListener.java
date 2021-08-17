package com.zaidazadkiel.remotecontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPListener implements Runnable {
  DatagramSocket socket;
  private int port;
  
  public UDPListener(int port) throws SocketException {
    this.port =port;
    socket = new DatagramSocket(port);
    System.out.println("UDP Port: " + port);
  }
  
  @Override
  public void run() {
  
    try {
      serve();
    } catch (IOException e) {
      e.printStackTrace();
    }
  
  }
  
  private void serve() throws IOException {
    while (true) {
      byte[] data = new byte[255];
      DatagramPacket request = new DatagramPacket(data, 255);
      
      socket.receive(request);
      InetAddress clientAddress = request.getAddress();
      int clientPort = request.getPort();
      
      String datastring = new String(data, StandardCharsets.UTF_8).trim();
      System.out.println("udp "+clientAddress+":"+clientPort+"\n> "+datastring);
  
      byte[] buffer = "server_online".getBytes();
      DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
      socket.send(response);
    }
  }
  
}
