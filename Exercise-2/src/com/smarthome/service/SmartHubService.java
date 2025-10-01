package com.smarthome.service;

import com.smarthome.model.IDevice;
import com.smarthome.proxy.DeviceProxy;
import com.smarthome.observer.Observer;
import com.smarthome.observer.Subject;
import com.smarthome.trigger.Trigger;
import com.smarthome.exception.DeviceOperationException;
import com.smarthome.util.LoggerConfig;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;


public class SmartHubService implements Subject {
    private final Map<Integer, IDevice> devices = new ConcurrentHashMap<>();
    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
    private final List<Trigger> triggers = new CopyOnWriteArrayList<>();
    private final Logger logger = LoggerConfig.getLogger();

    public SmartHubService() {
        
        scheduler.scheduleAtFixedRate(this::checkTriggers, 1, 1, TimeUnit.SECONDS);
        logger.info("SmartHubService started: realtime trigger checking enabled (1s interval)");
    }

    public Logger getLogger() { return logger; }

    public void addDevice(IDevice device) { addDevice(device, "admin"); }

    public void addDevice(IDevice device, String ownerRole) {
        DeviceProxy proxy = new DeviceProxy(device, ownerRole);
        if (devices.containsKey(proxy.getId())) {
            logger.warning("Cannot add device: id " + proxy.getId() + " already exists");
            return;
        }
        devices.put(proxy.getId(), proxy);
        if (device instanceof Observer) registerObserver((Observer) device);
        logger.info("Added device: " + proxy.getType() + " id=" + proxy.getId());
    }

    public void removeDevice(int id) {
        IDevice removed = devices.remove(id);
        if (removed == null) {
            logger.warning("Cannot remove device: id " + id + " not found");
            return;
        }
        if (removed instanceof Observer) unregisterObserver((Observer) removed);
        logger.info("Removed device: " + id);
    }

    public IDevice getDevice(int id) { return devices.get(id); }
    public Collection<IDevice> getAllDevices() { return devices.values(); }

    // turn on/off by ID
    public void turnOnDevice(int id) {
        IDevice d = devices.get(id);
        if (d == null) { logger.warning("turnOn: Device " + id + " not found"); return; }
        try { d.turnOn(); } catch (DeviceOperationException e) { logger.severe("Failed to turn on device " + id + ": " + e.getMessage()); }
    }

    public void turnOffDevice(int id) {
        IDevice d = devices.get(id);
        if (d == null) { logger.warning("turnOff: Device " + id + " not found"); return; }
        try { d.turnOff(); } catch (DeviceOperationException e) { logger.severe("Failed to turn off device " + id + ": " + e.getMessage()); }
    }

    // schedule by delay seconds
    public void scheduleDevice(int id, long delaySeconds, boolean turnOn) {
        IDevice d = devices.get(id);
        if (d == null) { logger.warning("Cannot schedule: device " + id + " not found"); return; }
        scheduler.schedule(() -> {
            try { if (turnOn) d.turnOn(); else d.turnOff(); }
            catch (Exception e) { logger.severe("Scheduled action failed for device " + id + ": " + e.getMessage()); }
        }, delaySeconds, TimeUnit.SECONDS);
        logger.info("Scheduled device " + id + " to " + (turnOn?"ON":"OFF") + " in " + delaySeconds + "s");
    }

    // schedule at specific local time HH:mm[:ss] 
    public void scheduleDeviceAtTime(int id, String localTimeString, boolean turnOn) {
        IDevice d = devices.get(id);
        if (d == null) { logger.warning("Cannot schedule: device " + id + " not found"); return; }
        try {
            java.time.LocalTime lt = java.time.LocalTime.parse(localTimeString);
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            java.time.LocalDateTime target = java.time.LocalDateTime.of(now.toLocalDate(), lt);
            if (target.isBefore(now) || target.equals(now)) target = target.plusDays(1);
            long delaySeconds = java.time.Duration.between(now, target).getSeconds();
            scheduler.schedule(() -> {
                try { if (turnOn) d.turnOn(); else d.turnOff(); }
                catch (Exception e) { logger.severe("Scheduled (time) action failed for device " + id + ": " + e.getMessage()); }
            }, delaySeconds, TimeUnit.SECONDS);
            logger.info("Scheduled device " + id + " to " + (turnOn?"ON":"OFF") + " at " + target.toString());
        } catch (Exception e) {
            logger.warning("Invalid time format for scheduleDeviceAtTime: " + localTimeString);
        }
    }


    public void performDeviceAction(int id, String action, Object... params) {
        IDevice d = devices.get(id);
        if (d == null) { logger.warning("performAction: Device " + id + " not found"); return; }
        try { d.performAction(action, params); } catch (DeviceOperationException e) { logger.severe("Action failed for device " + id + ": " + e.getMessage()); }
    }

    // triggers management
    public void addTrigger(Trigger t) { 
        triggers.add(t); 
        logger.info("Added " + t); 
        try { 
            if (t.evaluate(this)) { 
                logger.info("Trigger fired immediately on add: " + t); 
                t.executeAction(this); 
            } 
        } catch (Exception e) { 
            logger.warning("Error while evaluating trigger on add: " + e.getMessage()); 
        } 
    }

    public void removeTrigger(Trigger t) { triggers.remove(t); logger.info("Removed " + t); }

   
    public void checkTriggers() {
        for (Trigger t : triggers) {
            try {
                if (t.evaluate(this)) {
                    logger.info("Trigger fired: " + t);
                    t.executeAction(this);
                }
            } catch (Exception e) { logger.warning("Trigger evaluation failed: " + e.getMessage()); }
        }
    }

    @Override public void registerObserver(Observer o) { observers.add(o); }
    @Override public void unregisterObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers(String event, Object data) {
        for (Observer o : observers) {
            try { o.update(event, data); } catch (Exception ignored) {}
        }
    }

    public void shutdown() {
        logger.info("Shutting down SmartHubService scheduler...");
        scheduler.shutdown();
    }
}
