package com.smarthome.factory;

import com.smarthome.model.*;

public class DeviceFactory {
    public static IDevice createDevice(int id, String type) {
        if (type == null) throw new IllegalArgumentException("type required");
        switch (type.toLowerCase()) {
            case "light": return new Light(id);
            case "thermostat": return new Thermostat(id, 70.0);
            case "doorlock": return new DoorLock(id);
            case "securitycamera": return new SecurityCamera(id);
            case "speaker": return new Speaker(id);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}