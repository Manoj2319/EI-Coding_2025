package com.smarthome.exception;

public class DeviceOperationException extends Exception {
    public DeviceOperationException(String msg) { super(msg); }
    public DeviceOperationException(String msg, Throwable t) { super(msg, t); }
}