<p align="center">
  <img src="app/src/main/res/drawable/tracer_logo.png" width="180"/>
</p>

<h1 align="center">Tracer Kiosk</h1>

<p align="center">
  Smart Information & Indoor Navigation Kiosk for Android Tablets
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.2.0-7F52FF?logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4"/>
  <img src="https://img.shields.io/badge/Architecture-MVVM-00C853"/>
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Status-v1.0.0-success"/>
</p>

---

# 📍 Tracer Kiosk

**Tracer Kiosk** is an Android tablet application developed as part of the **Tracer Smart Campus Navigation System**.

Designed for installation at the entrance of a department or campus building, the kiosk enables visitors to quickly browse faculty information, explore department facilities, search destinations, and receive indoor navigation guidance through an intuitive touch-friendly interface.

Unlike a personal mobile application, Tracer Kiosk serves as a **fixed digital information terminal**, providing visitors with fast access to campus information and indoor wayfinding.

> **Note**
>
> This repository contains only the **Tracer Kiosk** application.
> The BLE scanner, RSSI fingerprint collection, indoor localization engine, and machine learning modules are developed separately as part of the overall **Tracer** ecosystem.

---

# ✨ Features

- 🏠 Modern Home Dashboard
- 🏛️ Department Information
- 👨‍🏫 Faculty & Staff Directory
- 🔍 Smart Destination Search
- 🧭 Interactive Indoor Navigation
- 🗺️ Floor Map Visualization
- 📍 Animated Route Rendering
- 🚶 A* Pathfinding Navigation
- 📌 "You Are Here" Navigation Marker
- 🎨 Material 3 User Interface
- 📱 Optimized for Android Tablets
- 🧩 Modular Jetpack Compose Components

---

# 📸 Screenshots

## 🚀 Splash Screen

<p align="center">
  <img src="screenshots/splash.png" width="900"/>
</p>

---

## 🏠 Home Dashboard

<p align="center">
  <img src="screenshots/home.png" width="900"/>
</p>

---

## 🧭 Indoor Navigation

<p align="center">
  <img src="screenshots/navigation.png" width="900"/>
</p>

---

## 👨‍🏫 Faculty & Staff Directory

<p align="center">
  <img src="screenshots/faculty.png" width="900"/>
</p>

---

## 🏢 Department Information

<p align="center">
  <img src="screenshots/department.png" width="900"/>
</p>

---

## ℹ️ About Tracer

<p align="center">
  <img src="screenshots/about.png" width="900"/>
</p>

---

# 📱 Application Modules

- Home
- Faculty & Staff
- Department Information
- Indoor Navigation
- About Tracer

---

# 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- MVVM Architecture
- Repository Pattern
- Clean Architecture
- Coroutines
- StateFlow

---

# 🧭 Indoor Navigation

The kiosk includes a complete indoor navigation module featuring:

- Graph-based indoor map representation
- A* shortest path algorithm
- Interactive destination search
- Animated path visualization
- Smooth curved route rendering
- Animated navigation indicator
- Responsive floor map scaling
- Calibrated node positioning

---

# 📂 Project Structure

```text
app/
├── presentation/
│   ├── components/
│   ├── feature/
│   ├── navigation/
│   ├── theme/
│   └── utils/
├── data/
├── domain/
└── ui/
```

---

# 🚀 Project Status

## ✅ Version 1.0.0

Completed modules:

- Splash Screen
- Home Dashboard
- Faculty & Staff Directory
- Department Information
- Indoor Navigation
- Interactive Floor Map
- Destination Search
- Route Visualization
- About Tracer
- Sidebar Navigation
- Material 3 Design System

---

# 🎯 Project Objective

Tracer Kiosk provides visitors with an intuitive self-service kiosk for accessing department information and navigating indoor environments without requiring prior knowledge of the building layout.

The application forms the user-facing kiosk component of the broader **Tracer Smart Campus Navigation System**.

---

# 👨‍💻 Developer

**Shrihari N B Goudru**

Student Android Developer

Designed and developed the complete Tracer Kiosk Android application, including application architecture, modern Jetpack Compose user interface, interactive indoor navigation, A* pathfinding implementation, floor map visualization, and overall user experience.

---

# 📚 Academic Project

Developed as part of the Bachelor of Engineering Major Project.

**Project Title**

**Tracer – Smart Indoor Navigation System**

---

# 📄 License

This project is developed for academic and research purposes.

© 2026 Team Tracer. All Rights Reserved.
