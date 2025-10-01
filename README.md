# Coding Exercise 2025–26  

This repository contains Java implementations and demos for **classic software design patterns** and a **Smart Home Application** simulation system. The content is organized into two main sections:  

---

## Exercise 1: Design Patterns  

System design is the process of planning, structuring and defining the architecture of Software System.  

### Structure  

- **Behavioural Design Pattern**  
  Behavioral patterns focus on communication and responsibility between objects.  
  - **Observer Pattern**: Defines a one-to-many dependency so when one object changes state, all dependents are notified.  
  - **Chain of Responsibility Pattern**: Passes a request along a chain of handlers, where each handler decides to process it or forward it.  

- **Creational Design Pattern**  
  Creational patterns deal with object creation mechanisms.  
  - **Singleton Pattern**: Ensures a class has only one instance and provides a global point of access.  
  - **Factory Pattern**: Provides an interface for creating objects without specifying the exact class.  

- **Structural Design Pattern**  
  Structural patterns define how classes and objects are composed to form larger structures.  
  - **Proxy Pattern**: Provides a surrogate or placeholder for another object to control access to it.  
  - **Bridge Pattern**: Decouples an abstraction from its implementation so the two can vary independently.  


## Exercise 2: Smart Home Application  

A small **command-line based Smart Hub** that manages smart devices (lights, thermostats, door locks, cameras, speakers).  
The system demonstrates common OOP and design patterns: **factory, proxy, observer, triggers, and scheduler**.  

### Features  

- Device abstraction via `IDevice` and `AbstractDevice`.  
- Concrete device types: `Light`, `Thermostat`, `DoorLock`, `SecurityCamera`, `Speaker`.  
- `DeviceFactory` to create devices by type.  
- `DeviceProxy` enforces access control before delegating to real devices.  
- `SmartHubService` manages devices, observers, scheduled actions, and triggers.  
- `Trigger` allows condition + action automation evaluated periodically.  
- Centralized logging via `LoggerConfig`.  


### Design Notes  

- **Inheritance & Polymorphism**: `AbstractDevice` provides base implementation; concrete devices override as needed.  
- **Interfaces as Contracts**: `IDevice`, `Observer`, `Subject`.  
- **Factory Pattern**: Centralized device creation.  
- **Proxy Pattern**: Access control wrapper for devices.  
- **Observer Pattern**: `SmartHubService` acts as `Subject`, devices register as observers.  
- **Trigger Pattern**: `Trigger` holds `Predicate` + `Consumer` evaluated by hub scheduler.  

## Purpose  

- **Learn and demonstrate** key software design patterns.  
- **Practice** modular and maintainable Java programming.  
- **Simulate real-world systems** (Smart Hub) using OOP and design principles.  

