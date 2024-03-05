import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {

    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class User {

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class OnlineExam {

    static List<Question> questions = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static User loggedInUser;
    static boolean sessionOpen = false;

    public static void main(String[] args) {

        initializeQuestions();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {

                case 1:
                    login(scanner);
                    break;

                case 2:
                    register(scanner);
                    break;

                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option!");
            }
        }
    }

    public static void initializeQuestions() {

        List<String> options1 = new ArrayList<>();
        options1.add("Guido van Rossum");
        options1.add("James Gosling");
        options1.add("Dennis Ritchie");
        options1.add("Bjarne Stroustrup");

        List<String> options2 = new ArrayList<>();
        options2.add("6");
        options2.add("7");
        options2.add("8");
        options2.add("9");

        List<String> options3 = new ArrayList<>();
        options3.add("Byte to Int");
        options3.add("Int to Long");
        options3.add("Long to Int");
        options3.add("Short to Int");

        List<String> options4 = new ArrayList<>();
        options4.add("The reference of array");
        options4.add("Copy of array");
        options4.add("Length of array");
        options4.add("Copy of first element");

        List<String> options5 = new ArrayList<>();
        options5.add("int[] A = {}");
        options5.add("int[] A = {1,2,3}");
        options5.add("int[] A = (1,2,3)");
        options5.add("int[][] A = {1,2,3}");

        List<String> options6 = new ArrayList<>();
        options6.add("Object references");
        options6.add("objects");
        options6.add("Primitive datatypes");
        options6.add("None");

        List<String> options7 = new ArrayList<>();
        options7.add("At run time");
        options7.add("At compile time");
        options7.add("Depends on code");
        options7.add("None");

        List<String> options8 = new ArrayList<>();
        options8.add("Collection of editing tool");
        options8.add("Collection of classes");
        options8.add("Collection of classes and interfaces");
        options8.add("Collection of interfaces");

        List<String> options9 = new ArrayList<>();
        options9.add("java.lang.Object");
        options9.add("java.lang.String");
        options9.add("java.lang.util");
        options9.add("None");

        List<String> options10 = new ArrayList<>();
        options10.add("True");
        options10.add("False");
        options10.add("An Int Value");
        options10.add("None");

    
        Question question1 = new Question("Who invented Java Programming?", options1, 1);
        Question question2 = new Question("Number of primitive data types in Java are?", options2, 3);
        Question question3 = new Question("Automatic type conversion is possible in which of the possible cases?", options3, 2);
        Question question4 = new Question("When an array is passed to a method, what does the method receive?", options4, 1);
        Question question5 = new Question("Select the valid statement to declare and initialize an array.", options5, 2);
        Question question6 = new Question("Arrays in java are?", options6, 2);
        Question question7 = new Question("When is the object created with new keyword?", options7, 1);
        Question question8 = new Question("Identify the corrected definition of a package.", options8, 3);
        Question question9 = new Question("In which of the following is toString() method defined?", options9, 1);
        Question question10 = new Question("compareTo() returns?", options10, 3);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
    }

    public static void register(Scanner scanner) {

        System.out.print("Enter a Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a Password: ");
        String password = scanner.nextLine();

        // Check if the username is already taken
        for (User user : users) {
            if (user.username.equals(username)) {
                System.out.println("Username already taken. Please choose another one.");
                return;
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Registration successful! You can now log in now.");
    }

    public static void login(Scanner scanner) {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                sessionOpen = true;
                startExam(scanner);
                return;
            }
        }

        System.out.println("Invalid credentials. Please try again!");
    }

    public static void startExam(Scanner scanner) {

        System.out.println("\nWelcome, " + loggedInUser.username + "!");
        for (int i = 0; i < questions.size(); i++) {

            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.questionText);
            for (int j = 0; j < question.options.size(); j++) {
                System.out.println((j + 1) + ". " + question.options.get(j));
            }

            System.out.print("Select your answer (1-" + question.options.size() + "): ");
            int userChoice = scanner.nextInt();
            if (userChoice == question.correctOption) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Exam completed!");
        sessionOpen = false;
    }
}