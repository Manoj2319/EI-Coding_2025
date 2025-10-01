package com.smarthome;

import com.smarthome.factory.DeviceFactory;
import com.smarthome.model.*;
import com.smarthome.service.SmartHubService;
import com.smarthome.trigger.Trigger;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        SmartHubService hub = new SmartHubService();
        Logger logger = hub.getLogger();

        
        hub.addDevice(DeviceFactory.createDevice(1, "light"));
        hub.addDevice(DeviceFactory.createDevice(2, "thermostat"));
        hub.addDevice(DeviceFactory.createDevice(3, "doorlock"));
        hub.addDevice(DeviceFactory.createDevice(4, "securitycamera"));
        hub.addDevice(DeviceFactory.createDevice(5, "speaker"));

        Scanner sc = new Scanner(System.in);
        System.out.println("SmartHub CLI ready. Type commands or 'exit'. Type 'help' for examples.");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("exit")) break;
            if (line.equalsIgnoreCase("help")) {
               System.out.println("Commands:\n  status\n  addDevice(id,type)\n  removeDevice(id)\n  turnOn(id)\n  turnOff(id)\n  setBrightness(id,val)\n  setVolume(id,val)\n  setTemp(id,val)\n  schedule(id,seconds|HH:mm[:ss],on|off)\n  addTrigger(type,deviceId,op,value,actionDeviceId,on|off)");
                continue;
            }
            try {
                
                int p1 = line.indexOf('(');
                String cmd = (p1==-1)? line : line.substring(0,p1).trim();
                String argsStr = (p1==-1)? "" : line.substring(p1+1, line.lastIndexOf(')'));

                if (cmd.equalsIgnoreCase("status")) {
                    for (IDevice d : hub.getAllDevices()) System.out.println(d.getInfo());
                } else if (cmd.equalsIgnoreCase("addDevice")) {
                    String[] parts = argsStr.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String type = parts[1].trim();
                    hub.addDevice(DeviceFactory.createDevice(id, type));
                } else if (cmd.equalsIgnoreCase("removeDevice")) {
                    int id = Integer.parseInt(argsStr.trim());
                    hub.removeDevice(id);
                } else if (cmd.equalsIgnoreCase("turnOn")) {
                    int id = Integer.parseInt(argsStr.trim());
                    hub.turnOnDevice(id);
                } else if (cmd.equalsIgnoreCase("turnOff")) {
                    int id = Integer.parseInt(argsStr.trim());
                    hub.turnOffDevice(id);
                } else if (cmd.equalsIgnoreCase("setBrightness")) {
                    String[] parts = argsStr.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    int val = Integer.parseInt(parts[1].trim());
                    hub.performDeviceAction(id, "setBrightness", val);
                } else if (cmd.equalsIgnoreCase("setVolume")) {
                    String[] parts = argsStr.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    int val = Integer.parseInt(parts[1].trim());
                    hub.performDeviceAction(id, "setVolume", val);
                } else if (cmd.equalsIgnoreCase("setTemp") || cmd.equalsIgnoreCase("setTemperature")) {
                    String[] parts = argsStr.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    double t = Double.parseDouble(parts[1].trim());
                    hub.performDeviceAction(id, "setTemp", t);
                } else if (cmd.equalsIgnoreCase("schedule")) {
                    
                    String[] parts = argsStr.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String when = parts[1].trim();
                    boolean on = parts[2].trim().equalsIgnoreCase("on");
                    
                    if (when.contains(":")) {
                        hub.scheduleDeviceAtTime(id, when, on);
                    } else {
                        long sec = Long.parseLong(when);
                        hub.scheduleDevice(id, sec, on);
                    }
                } else if (cmd.equalsIgnoreCase("addTrigger")) {
                    
                    String[] parts = argsStr.split(",");
                    String type = parts[0].trim().toLowerCase();
                    int deviceId = Integer.parseInt(parts[1].trim());
                    String op = parts[2].trim().toLowerCase();
                    String val = parts[3].trim();
                    int actionDeviceId = Integer.parseInt(parts[4].trim());
                    boolean actionOn = parts[5].trim().equalsIgnoreCase("on");

                    if ("temp".equals(type)) {
                        double threshold = Double.parseDouble(val);
                        Trigger t = new Trigger("temp:"+deviceId+op+threshold, h -> {
                            IDevice dd = h.getDevice(deviceId);
                            if (dd == null) return false;
                            String s = dd.getStatus();
                            int idx = s.indexOf("temp=");
                            if (idx < 0) return false;
                            try {
                                double current = Double.parseDouble(s.substring(idx+5).replaceAll("[^0-9.]",""));
                                switch(op) {
                                    case "gt": return current > threshold;
                                    case "lt": return current < threshold;
                                    case "eq": return current == threshold;
                                    default: return false;
                                }
                            } catch (Exception e) { return false; }
                        }, h -> {
                            if (actionOn) h.turnOnDevice(actionDeviceId); else h.turnOffDevice(actionDeviceId);
                        });
                        hub.addTrigger(t);
                    } else {
                        System.out.println("Unsupported trigger type: " + type);
                    }

                } 
                else {
                    System.out.println("Unknown command. Type 'help' for examples.");
                }

                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Shutting down SmartHub...");
        hub.shutdown();
        sc.close();
    }
}