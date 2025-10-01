# Proxy Pattern – Image Viewer with Lazy Loading

## Overview
This project demonstrates the **Proxy Design Pattern** in Java using an Image Viewer system.  
The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.  
Here, the proxy delays the creation and loading of a `RealImage` until it is actually needed.

## Components
- **Subject Interface (Image)**  
  Declares the common method `display()` for images.

- **Real Subject (RealImage)**  
  Represents the actual object that loads an image from disk and displays it.

- **Proxy (ProxyImage)**  
  Controls access to `RealImage`. It initializes the `RealImage` only when the `display()` method is first called, implementing **lazy loading**.

- **Client (ProxyPatternDemo)**  
  Demonstrates how the proxy is used instead of directly working with the `RealImage`.

## How It Works
1. The client creates a `ProxyImage` with a filename.  
2. When `display()` is called for the first time, the proxy creates a `RealImage` and loads it from disk.  
3. On subsequent `display()` calls, the already created `RealImage` is reused, avoiding costly reloads.  

## Benefits
- **Performance Optimization**: Avoids expensive object creation until necessary.  
- **Encapsulation**: Client interacts with the proxy instead of directly with the heavy object.  
- **Follows SOLID Principles**: Each class has a single responsibility.  

## Usage
1. Compile and run the program.  
   ```bash
   javac src/*.java
   cd src
   java ProxyPatternDemo
2. Enter an image filename (e.g., `photo.jpg`).  
3. Observe that the image loads from disk only on the first call.  


