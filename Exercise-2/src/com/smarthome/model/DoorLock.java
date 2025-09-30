package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;


public class DoorLock extends AbstractDevice {
    public DoorLock(int id) {
        super(id, "doorlock");
        this.status = "UNLOCKED";
    }

    public void lock() throws DeviceOperationException {
        if ("LOCKED".equalsIgnoreCase(status)) {
            logger.info("DoorLock " + id + " is already LOCKED");
            return;
        }
        status = "LOCKED";
        logger.info("DoorLock " + id + " LOCKED");
    }

    public void unlock() throws DeviceOperationException {
        if ("UNLOCKED".equalsIgnoreCase(status)) {
            logger.info("DoorLock " + id + " is already UNLOCKED");
            return;
        }
        status = "UNLOCKED";
        logger.info("DoorLock " + id + " UNLOCKED");
    }

    @Override public void turnOn() throws DeviceOperationException { unlock(); }
    @Override public void turnOff() throws DeviceOperationException { lock(); }

    @Override public void performAction(String action, Object... params) throws DeviceOperationException {
        if (action == null) return;
        String a = action.toLowerCase();
        if ("lock".equals(a)) lock();
        else if ("unlock".equals(a)) unlock();
        else super.performAction(action, params);
    }

    @Override public String getStatus() { return String.format("DoorLock %d is %s", id, status); }
}