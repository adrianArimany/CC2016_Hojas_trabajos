# UVG HOja de trabajo 2 - Algoritmos y Estructuras de Datos

## Instrucciones para mover la clase 

Para mover la clase RPNCalculator.java se va requerir mover dos carpetas para que el codigo funcione adecuada mente, 

Esto es porque las operaciones se implemento un polimorfismo. 

Los directorios son:

/src/main/java/com/example/factory

src/main/java/com/example/operations

Factory maneja la traduccion de simbolo a operacion, la cual utiliza la interfaz operacion, donde se usa un polimorfismo para cada operacion del programa.

## Assigment descrition:

This program is meant to be a Calculator, however, rather than putting the program to operate in real time, it looks for a plain text and then prints the result from the plain text.

The plain text is suppose to be using Reverse Polish Notation (RPN), also known as reverse Arithmetic Expression. For this we also had to use Abstract Data Types, so that we could work with stacks or vector types. In this case we work with a vector type, who's type was generic T, but extended only to numeric (meaning only numbers). 

To make the assigment more fun and experienceable, we used Factory Method Design (FMD) on the operation logic, this way each operation had its own encapsulation and it was much easier to construct the logic using a polymorphism via the common interface "Operation".

However, it didn't stopped with FMD, we also implement a Graphical User Interface (GUI), this way is much easier for the user to find the file and insert it in the program.

Because we love logger, we thought it would be a nice idea to create a util for the Love Logger this way every class uses the same logger and stores the logger info in a app.log, so that we  have a better track on potential bugs the code might have.

Overall, the program has more potential than what the assigment originally expected, nevertheless, the clear idea behind adding all these additional tools is to gain expirience for fututre assignments that will be more complex and might require to use any of these tools that we have just mentioned. 


## Version:
Currently at version 1.0

## Contributors:
- Adrian Arimany 


## Information about the Program:

The Design Analysis and UML and other diagrams can be found in /root/Analysis/..

Highly recomend to have installed drawio in your Integrated Development Environment (IDE).



## IMPORTANT!!!

The system is meant not to close on its own, since is tecnically a car radio, so to fully turn off the system please use the following key-command in terminal:
```bash
CRLT + C
```

## Configurations:
1. Have version: [java version 21](https://www.oracle.com/java/technologies/downloads/) (If you don't have java 21 it might cause issues running the program on Maven.)

Make sure that you have Java 21, if you don't you can run the following lines depending on which Operating System you have.

If you are in Linux-based Debian, run the following line in terminal to install java version 21:

```bash
sudo apt install openjdk-21-jdk -y
```

Then to switch to this version run the following line,

```bash
sudo update-alternatives --config java
```
If you are in macOS, note that you need homebrew installed, run the following line in terminal to install java version 21:

```bash
brew install openjdk@21
```
Then to define java 21 as the default version run the following line,

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk
```

2. Clone repository:

```bash
git clone https://github.com/adrianArimany/Manejo_Hospitales.git 
```

2. Install [Maven](https://maven.apache.org/install.html)

If you are in VS Code you can install Maven directly through Extensions via (Ctrl+Shift+X)

Look for "Maven for Java" by Microsft 

or just use Ctrl+P, write down:

```bash
ext install vscjava.vscode-maven
```

This will automatically install maven onto your VS Code.

3. Install any package from maven:

```bash
mvn clean install -DskipTests
```

4. To execute the program:

```bash
mvn exec:java
```

In case the above doesn't run, try:

```bash
mvn exec:java -Dexec.mainClass="com.uvg.proyecto.App"
```

