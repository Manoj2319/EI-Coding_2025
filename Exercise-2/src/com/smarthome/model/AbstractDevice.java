package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;
import com.smarthome.observer.Observer;
import com.smarthome.util.LoggerConfig;

import java.util.logging.Logger;


public abstract class AbstractDevice implements IDevice, Observer {
    protected final int id;
    protected final String type;
    protected String status;
    protected final Logger logger = LoggerConfig.getLogger();

    public AbstractDevice(int id, String type) {
        this.id = id;
        this.type = type;
        this.status = "OFF";
    }

    @Override public int getId() { return id; }
    @Override public String getType() { return type; }
    @Override public String getStatus() { return status; }

    @Override
    public void turnOn() throws DeviceOperationException {
        if ("ON".equalsIgnoreCase(status)) {
            logger.info(getType() + " " + id + " is already ON");
            return;
        }
        status = "ON";
        logger.info("Turned ON: " + getType() + " (id=" + id + ")");
    }

    @Override
    public void turnOff() throws DeviceOperationException {
        if ("OFF".equalsIgnoreCase(status)) {
            logger.info(getType() + " " + id + " is already OFF");
            return;
        }
        status = "OFF";
        logger.info("Turned OFF: " + getType() + " (id=" + id + ")");
    }

    @Override public void update(String event, Object data) { logger.fine("Device " + id + " received event: " + event + ", data=" + data); }

    @Override
    public void performAction(String action, Object... params) throws DeviceOperationException {
        if (action == null) return;
        if ("turnOn".equalsIgnoreCase(action)) turnOn();
        else if ("turnOff".equalsIgnoreCase(action)) turnOff();
        else throw new DeviceOperationException("Unsupported action: " + action);
    }
}