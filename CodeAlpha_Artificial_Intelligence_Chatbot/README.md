# 📈 Artificial Intelligence Chatbot

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge)
![NLP](https://img.shields.io/badge/NLP-Basic%20Processing-green?style=for-the-badge)
![AI](https://img.shields.io/badge/AI-Rule%20Based-orange?style=for-the-badge)


Welcome to the AI Chatbot, a robust and interactive Java application featuring a native GUI, developed as part of the CodeAlpha Internship. This project combines core programming logic with Natural Language Processing (NLP) techniques to create a seamless communication experience between a human user and an AI assistant.

✨ Features
---
Interactive Swing GUI: A sleek desktop interface featuring a scrollable chat area and a real-time input field for fluid communication.


NLP Text Normalization: Advanced input processing that strips punctuation and converts text to lowercase, ensuring the bot understands user intent regardless of grammar or styling.


Keyword Extraction: The bot intelligently identifies core keywords within complex sentences to fetch the most relevant answers from its training data.


Rule-Based Intelligence: Powered by a robust HashMap dictionary, the bot provides accurate responses to frequently asked questions regarding Java, internships, and more.


Dynamic Response System: Includes a "fallback" logic that handles unrecognized queries gracefully, maintaining a professional conversational flow.


Asynchronous Simulation: Utilizes Swing Timers to introduce artificial delays, making the conversation feel more natural and human-like.

🏗️ Architecture & Design
---
This project emphasizes the separation of UI design and logical processing to ensure the application remains modular and maintainable.

Project Structure

AIChatbot.java: The central controller and UI class that handles window rendering and event listening.


Knowledge Base: A dictionary-based matching system that acts as the bot's "brain" by storing predefined rules.


Normalization Engine: A built-in logic sequence that prepares raw user strings for analysis by standardizing text.

🚀 Setup & Installation
---
Prerequisites

Java Development Kit (JDK) 8+ 


An IDE (IntelliJ, Eclipse, VS Code) or a standard terminal 

Execution Steps
Clone the Repository: Download the project files to your local machine.


Create the File: Ensure your main file is named AIChatbot.java.

Compile & Run: Run the main method. No external .jar files are required as it uses standard Java libraries.


Interact: Type questions like "What is Java?" or "Tell me about the internship" to start chatting.

💡 Learning Outcomes
---
This project was engineered to master the intersection of software logic and user experience:


GUI Development: Building responsive interfaces with Java Swing.


String Manipulation: Implementing NLP techniques like regex-based punctuation removal.


Data Structures: Using HashMaps for efficient keyword-to-response mapping.


Pattern Matching: Developing rule-based algorithms to simulate intelligence.

 

**Developed with ❤️ as part of the CodeAlpha Java Programming Internship.
