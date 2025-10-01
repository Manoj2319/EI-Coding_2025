# Coding Exercise 2025–26  

This repository contains Java implementations and demos for **classic software design patterns** and a **Smart Home Application** simulation system. The content is organized into two main sections:  

---

## Exercise 1: Design Patterns  

System design is the process of planning, structuring and defining the architecture of Software System.  

### Structure  

- **Behavioural Design Pattern**  
Behavioral design pattern focus on the interactions and communication between objects.
  - **Iterator Pattern**: Custom iterator for a book collection.  
  - **Template Pattern**: Game loader using the template method pattern.  

- **Creational Design Pattern**  
Creational Design Patterns focus on the process of object creation or problems related to object creation.
  - **Builder Pattern**: Flexible computer builder for gaming PCs and workstations.  
  - **Factory Pattern**: Notification system supporting Email and SMS.  

- **Structural Design Pattern**  
Structural Design Patterns are solutions in software design that focus on how classes and objects are organized to form larger, functional structures.
  - **Facade Pattern**: Home theater system with a unified interface.  
  - **Proxy Pattern**: Lazy-loading image viewer using the proxy pattern.  



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

