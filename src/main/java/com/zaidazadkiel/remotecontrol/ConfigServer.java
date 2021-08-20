package com.zaidazadkiel.remotecontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.zaidazadkiel.remotecontrol.json.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class ConfigServer implements Runnable {

    private boolean sendObjJson(Object src, HttpExchange http){
      try{
        String s = new ObjectMapper().writeValueAsString(src);
        http.sendResponseHeaders(200, s.length());
    
        OutputStream os = http.getResponseBody();
        os.write(s.getBytes(StandardCharsets.UTF_8));
        os.close();
        return true;
      }catch(Exception e){
        e.printStackTrace();
      }
      
      return false;
    }
    
    public List<ContextDescription> handlers = new ArrayList<>();
  
  static class ContextDescription{
      String path;
      String description;
      String[] params;
      HttpHandler handler;
  
    public ContextDescription(String path, String description, String[] params, HttpHandler handler) {
      this.path = path;
      this.description = description;
      this.params = params;
      this.handler = handler;
    }
  }
    
  
  ConfigServer(String[] args) throws Exception {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8888), 0);
    httpServer.setExecutor(Executors.newCachedThreadPool());
    
    httpServer.createContext("/", new StaticFileHandler("/","gui"));
  
    handlers.add(
      new ContextDescription(
        "/api/test",
        "test point for api test",
        new String[]{}, //empty
        new HttpHandler() {
          @Override
          public void handle(HttpExchange httpExchange) throws IOException {
            try {
              Test s = new Test(1, "zaida");
              if (!ConfigServer.this.sendObjJson(s, httpExchange)) {
                System.out.println("nopei");
              }
              System.out.println("sended");
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        
        }
      )
    );
    
    handlers.add(
      new ContextDescription(
        "/api",
        "Index point for api",
        new String[]{"one", "two"}, //empty
        new HttpHandler() {
          private List<ContextDescription> cd;
        
          public HttpHandler test(List<ContextDescription> cd) {
            this.cd = cd;
            return this;
          }
        
          @Override
          public void handle(HttpExchange httpExchange) throws IOException {
            try {
              List<Map> all = new ArrayList<>();
  
              for (ContextDescription description : cd) {
                Map<String, String> s = new HashMap<String, String>();
                s.put("path", description.path);
                s.put("description", description.description);
                s.put("params", String.join(",", description.params) );
                all.add(s);
              }
              
              if (!ConfigServer.this.sendObjJson(all, httpExchange)) {
                System.out.println("nopei");
              }
              System.out.println("sended");
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        
        }.test(handlers)
      )
    );
  

  
  
    for (ContextDescription cd : handlers) {
      System.out.println("setting handler for " + cd.path + "->" + cd.description) ;
      httpServer.createContext(cd.path, cd.handler);
    }
    
    httpServer.start();
    System.out.println("HttpServer Test Start!");
  }
  
  @Override
  public void run() {
    System.out.println("hi");
  }
}