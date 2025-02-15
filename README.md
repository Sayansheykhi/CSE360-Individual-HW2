# CSE360-Individual-HW2
# Questions & Answers CRUD Application

This project is a Java console application that demonstrates CRUD (Create, Read, Update, Delete) operations and input validation for managing questions and answers. The application simulates a discussion platform similar to Ed Discussion, where users can post questions, provide answers, and manage both with validation rules.


## Table of Contents

- [Project Overview](#project-overview)
- [Project Structure](#project-structure)
- [Features](#features)
- [How to Run](#how-to-run)
- [Test Automation](#test-automation)
- [UML Diagrams](#uml-diagrams)
- [Grader Instructions](#grader-instructions)
- [Requirements](#requirements)
- [Author](#author)
- [License](#license)


## Project Structure

## Project Structure
```plaintext
HW2/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── hw2/
│   │           ├── Answer.java
│   │           ├── Answers.java
│   │           ├── Question.java
│   │           ├── Questions.java
│   │           └── HW2.java
│   └── test/
│       └── java/
│           └── hw2TestingAutomation/
│               ├── HW2TestingAutomation.java
│               └── HW2AnswersTestingAutomation.java
├── UML Diagrams/
│   ├── uml_class_diagram_corrected.png
│   └── uml_sequence_diagram_styled.png
└── README.mdence_diagram_styled.png
└── README.md
```
## Project Overview

This application allows users to:
- **Create, read, update, and delete questions** with attributes such as text, category, and tags.
- **Create, read, update, and delete answers** associated with questions.
- Validate input to ensure that questions and answers meet the required criteria (e.g., non-empty text).
- **Store data in memory** (using an `ArrayList` for questions and a `HashMap` for answers). Note that data is not persisted after the program closes.



## Features

- **CRUD Operations for Questions:**
  - Create a question with a text, category, and tags.
  - Read (display) a specific question by its ID.
  - Update a question’s details.
  - Delete a question.
  
- **CRUD Operations for Answers:**
  - Create an answer associated with an existing question.
  - Read (display) all answers for a given question.
  - Update an answer.
  - Delete an answer.
  
- **Input Validation:**
  - Ensures question and answer texts are non-empty.
  
- **Test Automation:**
  - Semi-automated test harnesses for verifying the functionality of questions and answers.
  
- **Design Documentation:**
  - UML Class and Sequence diagrams provided in the repository.

## How to Run

### Using an IDE (e.g., IntelliJ IDEA or Eclipse):

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/<your_username>/HW2.git
   cd HW2


