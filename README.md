
# Traffic Light Controller API

## Overview

This project implements a **simple traffic light controller** for an intersection using **Java Spring Boot**.
It manages **traffic light states** for multiple directions, prevents conflicting greens, supports **pause/resume**, and maintains **state history**.

---

## Features

* Manage traffic lights: RED, YELLOW, GREEN
* Prevent conflicting directions from being GREEN
* Pause and resume the system
* Record timing and state history
* Thread-safe for concurrent access
* Test-driven design with unit and concurrency tests
* Easy to extend for **multiple intersections**

---

## Project Structure

```
src/main/java
 └── com.example.traffic
      ├── controller       # REST API endpoints
      ├── model            # Direction, LightState, IntersectionState, HistoryRecord
      └── service          # TrafficService (business logic)
      
src/test/java
 └── com.example.traffic
      ├── TrafficServiceTest          # Real state + concurrency tests


---

## Installation & Run

1. **Clone the repo**

```bash
git clone https://github.com/sakshitg/TrafficLightAPI.git
cd traffic-light-controller
```

2. **Build & run using Maven**

```bash
mvn clean install
mvn spring-boot:run
```

3. **API Endpoints**
   | Endpoint | Method | Description |
   |----------|--------|-------------|
   | `/traffic/state` | GET | Get current state of traffic lights |
   | `/traffic/history` | GET | Get history of light changes |
   | `/traffic/change/{direction}` | POST | Change light to specified direction |
   | `/traffic/pause` | POST | Pause system |
   | `/traffic/resume` | POST | Resume system |

---

## Design Decisions

* **Synchronized methods** ensure thread safety for `changeLight`, `pause`, and `resume`.
* **Constructor injection** is used for `TrafficService` dependencies.
* Separation of concerns:

    * **Service** → business logic
    * **Controller** → API exposure
    * **Model** → enums and state objects

---

## Testing

* **Real State Tests**: Validate actual light transitions and history
* **Mockito Behavior Tests**: Verify method calls, pause/resume, history interactions
* **Concurrency Tests**: Ensure thread safety under concurrent updates
* **Run tests**

```bash
mvn test
```

---

## Extensibility

* Can scale to **multiple intersections** using a map of `TrafficService` instances
* Can add **automatic timing** (e.g., GREEN → YELLOW → RED)
* Can persist **history in a database**
* Can add **distributed locking** for multi-node setups

---
