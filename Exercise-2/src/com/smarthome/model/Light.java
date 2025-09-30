package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;

public class Light extends AbstractDevice {
    private int brightness = 100; // 0-100

    public Light(int id) {
        super(id, "light");
        this.status = "OFF";
    }

    public void setBrightness(int b) throws DeviceOperationException {
        if (b < 0 || b > 100) throw new DeviceOperationException("Brightness must be 0-100");
        this.brightness = b;
        logger.info("Light " + id + " brightness set to " + brightness);
    }

    @Override
    public void performAction(String action, Object... params) throws DeviceOperationException {
        if (action == null) return;
        String a = action.toLowerCase();
        if ("setbrightness".equals(a) || "setbrightness".equals(a.replaceAll("\\s",""))) {
            if (params.length>0 && params[0] instanceof Integer) setBrightness((Integer)params[0]);
            else throw new DeviceOperationException("setBrightness requires integer parameter");
        } else super.performAction(action, params);
    }

    @Override
    public String getStatus() {
        return String.format("Light %d is %s (brightness=%d)", id, status, brightness);
    }
}