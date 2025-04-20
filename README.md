# ✅ Task Tracker CLI (https://roadmap.sh/projects/task-tracker)

## 🎯 Overview

A simple, lightweight Task Tracker application implemented in **Java** with a **Command Line Interface (CLI)**. This project helps users manage and track their tasks, offering features like adding, updating, viewing, and deleting tasks — all while storing data in a local `tasks.json` file. It’s a great exercise in building CLI tools, working with files, handling user input, and applying OOP design principles (Model, Repository, Service, Exception).

---

## ✨ Features

- **Task Management**: Add, update, delete tasks.
- **Status Tracking**: Mark tasks as `todo`, `in-progress`, or `done`.
- **Persistent Storage**: Tasks are saved to `tasks.json` between sessions.
- **Minimal Dependencies**: No external libraries used.
- **Formatted Output**: Display tasks clearly in the terminal.

---

## 🚀 How to Run

### 📦 Set Up Your Environment

1. Make sure you have **Java 17+** installed.
2. Use a Java IDE (e.g., IntelliJ) or any code editor you prefer.
3. Use the terminal to compile and run the application.

### 🔧 Compile and Run

```bash
# Navigate to the project root
cd task-tracker-cli

# Compile Java source files (adjust if using different structure)
javac -d out src/main/java/org/cuongse/*.java src/main/java/org/cuongse/model/*.java src/main/java/org/cuongse/repository/*.java src/main/java/org/cuongse/service/*.java src/main/java/org/cuongse/exception/*.java

# Run the application
java -cp out org.cuongse.MainApplication add "Buy groceries"

 
