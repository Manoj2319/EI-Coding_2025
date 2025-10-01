package com.smarthome.trigger;

import com.smarthome.service.SmartHubService;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class Trigger {
    private final String description;
    private final Predicate<SmartHubService> conditionFn;
    private final Consumer<SmartHubService> actionFn;
    private boolean fired = false; // flag to prevent repeated firing

    public Trigger(String description, Predicate<SmartHubService> conditionFn,
                   Consumer<SmartHubService> actionFn) {
        this.description = description;
        this.conditionFn = conditionFn;
        this.actionFn = actionFn;
    }


    public boolean evaluate(SmartHubService hub) {
        if (fired) return false; // already fired, skip
        try {
            boolean result = conditionFn.test(hub);
            if (result) fired = true; // mark as fired
            return result;
        } catch (Exception e) {
            return false;
        }
    }

  
    public void executeAction(SmartHubService hub) {
        if (!fired) return; 
        try {
            actionFn.accept(hub);
        } catch (Exception e) {
            System.err.println("Error executing action for trigger '" + description + "': " + e.getMessage());
        }
    }


    public void reset() {
        fired = false;
    }

    @Override
    public String toString() {
        return "Trigger(" + description + ")" + (fired ? "[FIRED]" : "");
    }
}
