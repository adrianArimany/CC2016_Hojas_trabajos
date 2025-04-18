# Program Info:

Universidad de valle de Guatemala

Authors: Adrian Arimany - 211063 (Part 1- java code)
Authors: Rodrigo 

# Note:

The first type of priorityQueque was built primarly from  moises.alonso (Original author) 

(source code: https://github.com/malonso-uvg/uvg2025ed/blob/main/e14_BinaryHeap/src/TreeNode.java)

You can also find the directory for this code in github under:

https://github.com/adrianArimany/CC2016_Hojas_trabajos/tree/master/hojatrabajo8/hoja8

# Emergency Care System

A simple Java application to manage patient triage using a priority queue. 

## Features
- **Patient** records with name, symptom, and emergency code (A–E).
- Two interchangeable queue implementations:
  - **Custom heap** (`HeapUsingIterativeBinaryTree` implementing `IHeap`).
  - **JDK PriorityQueue** (built‑in collection framework).
- Interactive console menu to admit and call next patient.
- Unit tests for both heap implementations.

## Prerequisites
- Java 21
- Maven 3.x

## Project Structure
```
src/
├─ main/java
│  ├─ com/example
│  │  └─ App.java           # Entry point with implementation switch
│  ├─ com/example/emergency
│  │  ├─ model/Patient.java
│  │  ├─ app/
│  │  │  ├─ EmergencyCareSystem.java    # Custom-heap version
│  │  │  └─ EmergencyCareSystemCF.java  # JCF PriorityQueue version
│  │  └─ comparator/PatientComparator.java
│  └─ com/example/orderobject
│     ├─ IHeap.java
│     └─ HeapUsingIterativeBinaryTree.java
└─ test/java
   ├─ com/example/orderobject/HeapUsingIterativeBinaryTreeTest.java
   └─ com/example/emergency/heap/VectorHeapTest.java
```

## Build & Run
1. Clone the repo and navigate to project root (where `pom.xml` lives).
2. Place `pacientes.txt` (CSV with `Name, Symptom, Code`) in the project root.

```bash
# Compile and test
mvn clean test

# Run the app (interactive console)
mvn exec:java -Dexec.mainClass=com.example.App
```

## Usage
1. On startup, choose implementation (1 = JCF, 2 = custom heap).
2. The app loads patients from `pacientes.txt`.
3. Press **ENTER** to call the next patient, or type **Q** to quit.


