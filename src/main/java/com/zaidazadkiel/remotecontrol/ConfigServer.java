package com.zaidazadkiel.remotecontrol;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ConfigServer implements Runnable {

  ConfigServer(String[] args) throws Exception {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8888), 0);
    httpServer.setExecutor(Executors.newCachedThreadPool());
    
    httpServer.createContext("/", new StaticFileHandler("/","gui"));
    
    httpServer.createContext("/home", new HttpHandler() {
      @Override
      public void handle(HttpExchange httpExchange) throws IOException {
        final byte[] out = "Hello, world!".getBytes("UTF-8");
        
        httpExchange.sendResponseHeaders(200, out.length);
        
        OutputStream os = httpExchange.getResponseBody();
        os.write(out);
        os.close();
      }
    });
    
    httpServer.createContext("/apps", new HttpHandler() {
      @Override
      public void handle(HttpExchange httpExchange) throws IOException {
        final byte[] out = "Apps Page!".getBytes("UTF-8");
        
        httpExchange.sendResponseHeaders(200, out.length);
        
        OutputStream os = httpExchange.getResponseBody();
        os.write(out);
        os.close();
      }
    });
    httpServer.createContext("/apps/foo", new HttpHandler() {
      @Override
      public void handle(HttpExchange httpExchange) throws IOException {
        final byte[] out = "Foo Page!".getBytes("UTF-8");
        
        httpExchange.sendResponseHeaders(200, out.length);
        
        OutputStream os = httpExchange.getResponseBody();
        os.write(out);
        os.close();
      }
    });
    
    httpServer.start();
    
    System.out.println("HttpServer Test Start!");
  }
  
  @Override
  public void run() {
    System.out.println("hi");
  }
}