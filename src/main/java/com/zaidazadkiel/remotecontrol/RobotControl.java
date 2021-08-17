package com.zaidazadkiel.remotecontrol;

import java.awt.*;
import java.awt.event.InputEvent;

public class RobotControl {
  Robot control;
  PointerInfo mouseinfo;
  
  RobotControl() throws AWTException {
    control = new Robot();
    mouseinfo = MouseInfo.getPointerInfo();
  }
  
  public void sendCommand(String[] command) {
    switch(command[0]) {
      case "mouse" : {
        int x = Integer.parseInt(command[1]);
        int y = Integer.parseInt(command[2]);
        Point currentpos = MouseInfo.getPointerInfo().getLocation();
        control.mouseMove(currentpos.x + x, currentpos.y+y);
        break;
      }
      case "leftClick":
      case "rightClick": {
        int mask = command[0].equals("rightClick") ?  InputEvent.BUTTON3_MASK : InputEvent.BUTTON1_MASK;
        control.mousePress  (mask);
        control.mouseRelease(mask);
      }
      break;
      default:
        System.out.println("unknown command: "+command[0]);
    }
  }
}
