package HW2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HW2 {
    private static Questions questions = new Questions();
    private static Answers answers = new Answers();
    private static int questionIdCounter = 1;
    private static int answerIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- HW2 CRUD Application ---");
            System.out.println("1. Create Question");
            System.out.println("2. Read Question");
            System.out.println("3. Update Question");
            System.out.println("4. Delete Question");
            System.out.println("5. Create Answer");
            System.out.println("6. Read Answers for a Question");
            System.out.println("7. Update Answer");
            System.out.println("8. Delete Answer");
            System.out.println("9. Search Questions");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    createQuestion(scanner);
                    break;
                case 2:
                    readQuestion(scanner);
                    break;
                case 3:
                    updateQuestion(scanner);
                    break;
                case 4:
                    deleteQuestion(scanner);
                    break;
                case 5:
                    createAnswer(scanner);
                    break;
                case 6:
                    readAnswers(scanner);
                    break;
                case 7:
                    updateAnswer(scanner);
                    break;
                case 8:
                    deleteAnswer(scanner);
                    break;
                case 9:
                    searchQuestions(scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting application.");
    }
    
    private static void createQuestion(Scanner scanner) {
        System.out.print("Enter question text: ");
        String text = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter tags (comma separated): ");
        String tagsInput = scanner.nextLine();
        List<String> tags = Arrays.asList(tagsInput.split(","));
        
        try {
            Question q = new Question(questionIdCounter++, text, category, tags);
            questions.addQuestion(q);
            System.out.println("Question created successfully:\n" + q);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating question: " + e.getMessage());
        }
    }
    
    private static void readQuestion(Scanner scanner) {
        System.out.print("Enter question ID to read: ");
        int id = Integer.parseInt(scanner.nextLine());
        Question q = questions.getQuestion(id);
        if (q != null) {
            System.out.println("Question details:\n" + q);
        } else {
            System.out.println("Question not found.");
        }
    }
    
    private static void updateQuestion(Scanner scanner) {
        System.out.print("Enter question ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Question q = questions.getQuestion(id);
        if (q != null) {
            System.out.print("Enter new question text: ");
            String newText = scanner.nextLine();
            System.out.print("Enter new category: ");
            String newCategory = scanner.nextLine();
            System.out.print("Enter new tags (comma separated): ");
            String tagsInput = scanner.nextLine();
            List<String> newTags = Arrays.asList(tagsInput.split(","));
            
            try {
                q.update(newText, newCategory, newTags);
                System.out.println("Question updated successfully:\n" + q);
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating question: " + e.getMessage());
            }
        } else {
            System.out.println("Question not found.");
        }
    }
    
    private static void deleteQuestion(Scanner scanner) {
        System.out.print("Enter question ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean deleted = questions.deleteQuestion(id);
        if (deleted) {
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Question not found.");
        }
    }
    
    private static void createAnswer(Scanner scanner) {
        System.out.print("Enter question ID to answer: ");
        int questionId = Integer.parseInt(scanner.nextLine());
        Question q = questions.getQuestion(questionId);
        if (q == null) {
            System.out.println("Question not found. Cannot add answer.");
            return;
        }
        System.out.print("Enter answer text: ");
        String text = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        
        try {
            Answer a = new Answer(answerIdCounter++, questionId, text, author);
            answers.addAnswer(a);
            System.out.println("Answer created successfully:\n" + a);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating answer: " + e.getMessage());
        }
    }
    
    private static void readAnswers(Scanner scanner) {
        System.out.print("Enter question ID to view its answers: ");
        int questionId = Integer.parseInt(scanner.nextLine());
        List<Answer> ansList = answers.getAnswersForQuestion(questionId);
        if (ansList.isEmpty()) {
            System.out.println("No answers found for this question.");
        } else {
            for (Answer a : ansList) {
                System.out.println(a);
                System.out.println("--------------------");
            }
        }
    }
    
    private static void updateAnswer(Scanner scanner) {
        System.out.print("Enter question ID for the answer: ");
        int questionId = Integer.parseInt(scanner.nextLine());
        List<Answer> ansList = answers.getAnswersForQuestion(questionId);
        if (ansList.isEmpty()) {
            System.out.println("No answers found for this question.");
            return;
        }
        System.out.print("Enter answer ID to update: ");
        int answerId = Integer.parseInt(scanner.nextLine());
        Answer selectedAnswer = null;
        for (Answer a : ansList) {
            if (a.getId() == answerId) {
                selectedAnswer = a;
                break;
            }
        }
        if (selectedAnswer != null) {
            System.out.print("Enter new answer text: ");
            String newText = scanner.nextLine();
            try {
                selectedAnswer.update(newText);
                System.out.println("Answer updated successfully:\n" + selectedAnswer);
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating answer: " + e.getMessage());
            }
        } else {
            System.out.println("Answer not found.");
        }
    }
    
    private static void deleteAnswer(Scanner scanner) {
        System.out.print("Enter question ID for the answer: ");
        int questionId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter answer ID to delete: ");
        int answerId = Integer.parseInt(scanner.nextLine());
        boolean deleted = answers.deleteAnswer(answerId, questionId);
        if (deleted) {
            System.out.println("Answer deleted successfully.");
        } else {
            System.out.println("Answer not found.");
        }
    }
    
    private static void searchQuestions(Scanner scanner) {
        System.out.print("Enter keyword to search in questions: ");
        String keyword = scanner.nextLine();
        List<Question> found = questions.searchQuestions(keyword);
        if (found.isEmpty()) {
            System.out.println("No questions match your search.");
        } else {
            for (Question q : found) {
                System.out.println(q);
                System.out.println("--------------------");
            }
        }
    }
}
