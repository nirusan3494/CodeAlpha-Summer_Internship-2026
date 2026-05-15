//TASK 3:Artificial Intelligence Chatbot
        /*
        ->Create a Java based chatbot for interactive communication
        ->Use natural language Processing NLP techniques
        ->Implement ML logic or rule based answers
        ->Train the bot to respond to frequently asked questions
        ->Integrate with a GUI or web interface for real-time interaction
         */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AIChatbot extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private Map<String, String> knowledgeBase;

    public AIChatbot() {
        //  Explains The GUI(Graphical User Interface) Window
        setTitle("AI Chatbot [CodeAlpha Internship]");
        setSize(450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 30));

        // Designing Chat Display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        chatArea.setBackground(new Color(45, 45, 45));
        chatArea.setForeground(Color.WHITE);
        chatArea.setCaretColor(Color.WHITE);

        chatArea.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));

        add(scrollPane, BorderLayout.CENTER);

        // Design of bottom input panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // 2. Initialize FAQ Training Data
        /*
        Creating predefined question-answer pairs
        Loading chatbot responses
        Storing FAQs in memory
        Preparing the chatbot before users interact with it
         */
        initKnowledgeBase();


        // 3. Action Listeners for interactivity
        /*
        It listens for an action like:
          clicking a button
          pressing Enter in a text field
         */
        ActionListener sendAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserInput();
            }
        };
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);


        // Welcome Screen  Bot Greeting
        appendMessage("Bot", "Hello! I am your AI Assistant. Try asking me about Java, this internship, or type 'help'!");
    }


    // Training the bot with rules and FAQs
    /*
    creating chatbot memory
    storing FAQs/rules
    mapping user messages to bot responses
     */
    private void initKnowledgeBase() {
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hello", "Hi there! How can I assist you today?");
        knowledgeBase.put("hi", "Hello! What's on your mind?");
        knowledgeBase.put("hey", "Hey! Nice to chat with you.");
        knowledgeBase.put("good morning", "Good morning! Hope you have a productive day.");
        knowledgeBase.put("good afternoon", "Good afternoon! How can I help you?");
        knowledgeBase.put("good evening", "Good evening! What would you like to know?");
        knowledgeBase.put("how are you", "I'm just a Java program, but I'm doing great! How about you?");
        knowledgeBase.put("your name", "I'm a Java-based chatbot created for the CodeAlpha Internship project.");
        knowledgeBase.put("who created you", "I was created using Java, Swing, and basic NLP logic.");
        knowledgeBase.put("what can you do", "I can answer simple questions, respond to greetings, and provide basic programming information.");

        knowledgeBase.put("internship", "The CodeAlpha Internship is a great opportunity to build real-world development skills.");
        knowledgeBase.put("codealpha", "CodeAlpha provides practical internship projects for students and beginners.");
        knowledgeBase.put("project", "This project is a GUI-based chatbot using Java Swing and rule-based NLP responses.");
        knowledgeBase.put("chatbot", "A chatbot is a software application that interacts with users through text or voice.");
        knowledgeBase.put("ai", "Artificial Intelligence enables machines to simulate human intelligence and decision-making.");
        knowledgeBase.put("nlp", "NLP stands for Natural Language Processing, which helps computers understand human language.");

        knowledgeBase.put("java", "Java is a powerful object-oriented programming language widely used for software development.");
        knowledgeBase.put("oops", "OOPS stands for Object-Oriented Programming System.");
        knowledgeBase.put("swing", "Java Swing is used to build graphical user interfaces in desktop applications.");
        knowledgeBase.put("jdbc", "JDBC is a Java API used to connect and interact with databases.");
        knowledgeBase.put("mysql", "MySQL is a popular relational database management system.");
        knowledgeBase.put("database", "A database stores and manages application data efficiently.");
        knowledgeBase.put("compiler", "A compiler converts source code into machine-readable code.");
        knowledgeBase.put("debugging", "Debugging is the process of finding and fixing errors in a program.");

        knowledgeBase.put("html", "HTML is used to structure web pages.");
        knowledgeBase.put("css", "CSS is used to style and design web pages.");
        knowledgeBase.put("javascript", "JavaScript adds interactivity to websites.");
        knowledgeBase.put("python", "Python is a beginner-friendly programming language popular in AI and automation.");
        knowledgeBase.put("c language", "C is a foundational programming language used in system programming.");
        knowledgeBase.put("app development", "App development involves creating applications for mobile or desktop platforms.");

        knowledgeBase.put("motivate me", "Keep learning consistently. Small improvements every day create big results.");
        knowledgeBase.put("career", "Building projects and improving problem-solving skills are important for a strong tech career.");
        knowledgeBase.put("resume", "A good resume should highlight projects, skills, internships, and achievements.");
        knowledgeBase.put("github", "GitHub is a platform used to host and manage code repositories.");
        knowledgeBase.put("coding", "Coding is the process of writing instructions for computers.");
        knowledgeBase.put("developer", "A developer creates software applications and solves technical problems.");

        knowledgeBase.put("thank you", "You're welcome!");
        knowledgeBase.put("thanks", "Happy to help!");
        knowledgeBase.put("bye", "Goodbye! Happy coding!");
        knowledgeBase.put("see you", "See you again! Keep practicing.");
    }

    // Handle the user's message
    /*
    Reads the user's message
    Removes unnecessary spaces
    Ignores empty input
    Prevents blank chatbot messages
     */
    private void processUserInput() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        // Display user text
        appendMessage("You", userText);
        inputField.setText("");

        // Basic NLP: Normalize text (lowercase, remove punctuation)
        /*
        converts text to lowercase
        removes punctuation/special symbols
        standardizes user input
        improves chatbot understanding
         */
        String normalizedInput = userText.toLowerCase().replaceAll("[^a-z0-9\\s]", "");

        // Generate and display response
        String response = generateResponse(normalizedInput);

        // Add a slight delay simulation (optional, makes it feel more "real")
        /*
        creates a short delay
        waits 400 milliseconds
        shows the bot response afterward
        runs only once
        makes the chatbot feel more realistic
         */
        Timer timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendMessage("Bot", response);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Logic to determine the bot's answer
    /*
    checks chatbot keywords
    compares them with user input
    finds matching FAQ/rule
    returns the corresponding bot reply
     */
    private String generateResponse(String input) {
        for (String keyword : knowledgeBase.keySet()) {
            // If the normalized user input contains one of our trained keywords
            if (input.contains(keyword)) {
                return knowledgeBase.get(keyword);
            }
        }
        // Fallback ML/Rule response if no keywords match
        return "I'm sorry, I don't quite have the data for that yet. Could you rephrase or ask something else?";
    }

    // Utility to format chat text
    private void appendMessage(String sender, String message) {
        chatArea.append(sender + ": " + message + "\n\n");
        // Auto-scroll to the bottom
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    // Main entry point
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            new AIChatbot().setVisible(true);
        });
    }
}