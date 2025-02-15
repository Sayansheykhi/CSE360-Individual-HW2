package HW2;

import java.util.Arrays;
import java.util.List;

/**
 * HW2AnswersTestingAutomation
 * 
 * A semi-automated test harness for testing the CRUD operations
 * on the Answer class and the Answers collection.
 */
public class HW2AnswersTestingAutomation {

    static int numPassed = 0; 
    static int numFailed = 0;  

    public static void main(String[] args) {
        System.out.println("______________________________________");
        System.out.println("\nHW2 Answers Testing Automation\n");

        
        Questions qs = new Questions();
        try {
            Question validQuestion = new Question(1, "How does Java handle memory?", "Programming", Arrays.asList("Java", "Memory"));
            qs.addQuestion(validQuestion);
        } catch (Exception e) {
            System.out.println("Setup error (Questions): " + e.getMessage());
        }

        
        Answers ansCollection = new Answers();

     
       
        performTestCreateAnswer(
                1, 
                "Java has automatic memory management.", 
                1,               
                "Alice", 
                ansCollection, 
                qs, 
                true
        );

        
        performTestCreateAnswer(
                2, 
                "Java has automatic memory management.", 
                999,             
                "Alice", 
                ansCollection, 
                qs, 
                false
        );

        
        performTestReadAnswers(
                3, 
                1, 
                ansCollection, 
                true
        );

    
        performTestReadAnswers(
                4, 
                2, 
                ansCollection, 
                false
        );

      
        performTestUpdateAnswer(
                5, 
                1,             
                1,                
                "Garbage collection in Java prevents memory leaks.", 
                ansCollection, 
                true
        );

       
        performTestUpdateAnswer(
                6, 
                1, 
                1, 
                "",               
                ansCollection, 
                false
        );


        performTestDeleteAnswer(
                7, 
                1,                
                1,               
                ansCollection, 
                true
        );

     
        performTestDeleteAnswer(
                8, 
                999,             
                1,               
                ansCollection, 
                false
        );

        
        System.out.println("____________________________________________________________________________");
        System.out.println("Number of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }


    private static void performTestCreateAnswer(int testCase, String text, int questionId, String author, 
                                                  Answers ansCollection, Questions qs, boolean expectedPass) {
        System.out.println("____________________________________________________________________________");
        System.out.println("Test Case " + testCase + ": Create Answer");
        System.out.println("Input: \"" + text + "\", Question ID = " + questionId + ", Author = \"" + author + "\"");

        try {
            
            Question q = qs.getQuestion(questionId);
            if (q == null) {
                throw new IllegalArgumentException("Question not found.");
            }
            Answer a = new Answer(testCase, questionId, text, author);
            ansCollection.addAnswer(a);
            if (expectedPass) {
                System.out.println("***Success***: Answer created successfully:\n" + a);
                numPassed++;
            } else {
                System.out.println("***Failure***: Expected failure, but answer was created:\n" + a);
                numFailed++;
            }
        } catch (Exception e) {
            if (!expectedPass) {
                System.out.println("***Success***: Expected failure occurred: " + e.getMessage());
                numPassed++;
            } else {
                System.out.println("***Failure***: Unexpected error: " + e.getMessage());
                numFailed++;
            }
        }
        System.out.println();
    }

  
    private static void performTestReadAnswers(int testCase, int questionId, Answers ansCollection, boolean expectedPass) {
        System.out.println("____________________________________________________________________________");
        System.out.println("Test Case " + testCase + ": Read Answers for Question ID = " + questionId);
        List<Answer> answerList = ansCollection.getAnswersForQuestion(questionId);
        if (!answerList.isEmpty() && expectedPass) {
            System.out.println("***Success***: Found answers:");
            for (Answer a : answerList) {
                System.out.println(a);
            }
            numPassed++;
        } else if (answerList.isEmpty() && !expectedPass) {
            System.out.println("***Success***: No answers found as expected.");
            numPassed++;
        } else {
            System.out.println("***Failure***: Unexpected result while reading answers.");
            numFailed++;
        }
        System.out.println();
    }

    // Test for updating an answer
    private static void performTestUpdateAnswer(int testCase, int answerId, int questionId, String newText, 
                                                  Answers ansCollection, boolean expectedPass) {
        System.out.println("____________________________________________________________________________");
        System.out.println("Test Case " + testCase + ": Update Answer (ID = " + answerId + ")");
        // Retrieve the answer for the given questionId.
        List<Answer> answerList = ansCollection.getAnswersForQuestion(questionId);
        Answer targetAnswer = null;
        for (Answer a : answerList) {
            if (a.getId() == answerId) {
                targetAnswer = a;
                break;
            }
        }
        try {
            if (targetAnswer == null) {
                throw new IllegalArgumentException("Answer not found.");
            }
            targetAnswer.update(newText);
            if (expectedPass) {
                System.out.println("***Success***: Answer updated successfully:\n" + targetAnswer);
                numPassed++;
            } else {
                System.out.println("***Failure***: Expected failure, but update succeeded:\n" + targetAnswer);
                numFailed++;
            }
        } catch (Exception e) {
            if (!expectedPass) {
                System.out.println("***Success***: Expected failure occurred during update: " + e.getMessage());
                numPassed++;
            } else {
                System.out.println("***Failure***: Unexpected error during update: " + e.getMessage());
                numFailed++;
            }
        }
        System.out.println();
    }

    // Test for deleting an answer
    private static void performTestDeleteAnswer(int testCase, int answerId, int questionId, 
                                                  Answers ansCollection, boolean expectedPass) {
        System.out.println("____________________________________________________________________________");
        System.out.println("Test Case " + testCase + ": Delete Answer (ID = " + answerId + ")");
        boolean deleted = ansCollection.deleteAnswer(answerId, questionId);
        if (deleted && expectedPass) {
            System.out.println("***Success***: Answer deleted successfully.");
            numPassed++;
        } else if (!deleted && !expectedPass) {
            System.out.println("***Success***: Answer deletion failed as expected.");
            numPassed++;
        } else {
            System.out.println("***Failure***: Unexpected result during deletion.");
            numFailed++;
        }
        System.out.println();
    }
}
