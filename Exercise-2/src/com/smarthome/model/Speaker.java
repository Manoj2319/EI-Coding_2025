package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;

public class Speaker extends AbstractDevice {
    private int volume = 50; // 0-100

    public Speaker(int id) {
        super(id, "speaker");
        this.status = "OFF";
    }

    public void setVolume(int v) throws DeviceOperationException {
        if (v < 0 || v > 100) throw new DeviceOperationException("Volume must be 0-100");
        this.volume = v;
        logger.info("Speaker " + id + " volume set to " + volume);
    }

    @Override
    public void performAction(String action, Object... params) throws DeviceOperationException {
        if (action == null) return;
        String a = action.toLowerCase();
        if ("setvolume".equals(a) || "setvolume".equals(a.replaceAll("\\s",""))) {
            if (params.length>0 && params[0] instanceof Integer) setVolume((Integer)params[0]);
            else throw new DeviceOperationException("setVolume requires integer parameter");
        } else if ("play".equals(a) || "turnon".equals(a)) {
            turnOn();
        } else if ("stop".equals(a) || "turnoff".equals(a)) {
            turnOff();
        } else super.performAction(action, params);
    }

    @Override
    public String getStatus() {
        return String.format("Speaker %d is %s (volume=%d)", id, status, volume);
    }
}