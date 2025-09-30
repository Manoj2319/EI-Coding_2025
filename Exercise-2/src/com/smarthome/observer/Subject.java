package com.smarthome.observer;
public interface Subject {
    void registerObserver(Observer o);
    void unregisterObserver(Observer o);
    void notifyObservers(String event, Object data);
}