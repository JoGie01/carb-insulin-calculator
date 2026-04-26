# Carb & Insulin Calculator

A Java console application that calculates carbohydrates and insulin dosage based on meals, ingredients, and recipes.

This project simulates a nutrition calculator that helps insulin-dependent users estimate carbohydrate intake and required insulin dosage.

It was built as a personal project to improve my skills in:
- Object-Oriented Programming in Java
- Software architecture and clean code design
- Unit testing (JUnit 5)
- JSON-based persistence (Jackson)
- CLI application development

---

## Features

- Add single foods with carbohydrate values
- Create meals from ingredients
- Calculate total carbohydrate intake
- Compute insulin dosage based on user factor
- Persistent storage using JSON files
- In-memory and file-based repository implementations
- Input validation and error handling

---

## Tech Stack

- Java 17+
- Gradle
- JUnit 5
- Jackson (JSON serialization)
- CLI (Scanner-based interface)

---

## How to run

```bash
./gradlew run
```
On Windows
```bash
gradlew.bat run
```
---

## Sample Data

A sample `foods.json` file is included with example entries to test the application.

---

## Future Improvements

- Add recipe management
- Replace CLI with a mobile application (Android)
- Add database persistence (SQLite/PostgreSQL)

---

## Author
Joachim Gierke | Games Engineering Student at TUM