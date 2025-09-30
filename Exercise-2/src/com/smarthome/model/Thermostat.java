package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;


public class Thermostat extends AbstractDevice {
    private double temperature;

    public Thermostat(int id, double initialTemp) {
        super(id, "thermostat");
        this.temperature = initialTemp;
        this.status = "OFF";
    }

    public double getTemperature() { return temperature; }

    public void setTemperature(double temp) throws DeviceOperationException {
        if (temp < 40.0 || temp > 100.0) throw new DeviceOperationException("Temperature out of range (40-100)");
        this.temperature = temp;
        logger.info("Thermostat " + id + " temperature set to " + temp);
    }

    @Override
    public void performAction(String action, Object... params) throws DeviceOperationException {
        if (action == null) return;
        String a = action.toLowerCase();
        if ("settemperature".equals(a) || "settemp".equals(a) || "settemp".equals(a.replaceAll("\\s",""))) {
            if (params.length>0 && params[0] instanceof Number) setTemperature(((Number)params[0]).doubleValue());
            else throw new DeviceOperationException("setTemperature requires numeric parameter");
        } else super.performAction(action, params);
    }

    @Override
    public String getStatus() {
        return String.format("Thermostat %d is %s (temp=%.1f°F)", id, status, temperature);
    }
}