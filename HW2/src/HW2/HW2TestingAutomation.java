package HW2;


import java.util.Arrays;
import java.util.List;

/**
 * HW2TestingAutomation class demonstrates a semi-automated test harness
 * for the Questions and Answers CRUD operations.
 */
public class HW2TestingAutomation {

    static int numPassed = 0;   
    static int numFailed = 0;  

    public static void main(String[] args) {
        System.out.println("______________________________________");
        System.out.println("\nHW2 Testing Automation\n");

       

        
        performTestCreateQuestion(1,
                "How does Java handle memory?",
                "Programming",
                Arrays.asList("Java", "Memory"),
                true);

        
        performTestCreateQuestion(2,
                "",
                "Programming",
                Arrays.asList("Java", "Memory"),
                false);

       
        performTestReadQuestion(3, 1, true);

       
        performTestReadQuestion(4, 999, false);

       
        performTestUpdateQuestion(5,
                1,
                "What is garbage collection in Java?",
                "Programming",
                Arrays.asList("Java", "Memory"),
                true);

       
        performTestUpdateQuestion(6,
                1,
                "",
                "Programming",
                Arrays.asList("Java", "Memory"),
                false);

        
        performTestDeleteQuestion(7, 1, true);

       
        performTestDeleteQuestion(8, 999, false);

        // Summary Report
        System.out.println("____________________________________________________________________________");
        System.out.println("Number of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }

    
    private static void performTestCreateQuestion(int testCase, String text, String category, List<String> tags, boolean expectedPass) {
        System.out.println("____________________________________________________________________________\nTest Case " + testCase + ": Create Question");
        System.out.println("Input: \"" + text + "\", \"" + category + "\", " + tags);
        try {
            Question q = new Question(testCase, text, category, tags);
           
            if (expectedPass) {
                System.out.println("***Success***: Question created successfully:\n" + q);
                numPassed++;
            } else {
                System.out.println("***Failure***: Expected failure, but question was created:\n" + q);
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

    
    private static void performTestReadQuestion(int testCase, int questionId, boolean expectedPass) {
        System.out.println("____________________________________________________________________________\nTest Case " + testCase + ": Read Question");
        Questions qs = new Questions();
        
        if (questionId == 1) {
            try {
                Question q = new Question(1, "How does Java handle memory?", "Programming", Arrays.asList("Java", "Memory"));
                qs.addQuestion(q);
            } catch (Exception e) {
                System.out.println("Setup error: " + e.getMessage());
            }
        }
        Question q = qs.getQuestion(questionId);
        if (q != null && expectedPass) {
            System.out.println("***Success***: Found question:\n" + q);
            numPassed++;
        } else if (q == null && !expectedPass) {
            System.out.println("***Success***: No question found as expected.");
            numPassed++;
        } else {
            System.out.println("***Failure***: Unexpected result while reading question.");
            numFailed++;
        }
        System.out.println();
    }

    // Test for updating a question
    private static void performTestUpdateQuestion(int testCase, int questionId, String newText, String newCategory, List<String> newTags, boolean expectedPass) {
        System.out.println("____________________________________________________________________________\nTest Case " + testCase + ": Update Question");
        Questions qs = new Questions();
        try {
            // For testing, create and add a question first.
            Question q = new Question(questionId, "Original Text", "Original Category", Arrays.asList("Original"));
            qs.addQuestion(q);
            q.update(newText, newCategory, newTags);
            if (expectedPass) {
                System.out.println("***Success***: Question updated successfully:\n" + q);
                numPassed++;
            } else {
                System.out.println("***Failure***: Expected failure, but update succeeded:\n" + q);
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

    // Test for deleting a question
    private static void performTestDeleteQuestion(int testCase, int questionId, boolean expectedPass) {
        System.out.println("____________________________________________________________________________\nTest Case " + testCase + ": Delete Question");
        Questions qs = new Questions();
        
        if (questionId == 1) {
            try {
                Question q = new Question(1, "How does Java handle memory?", "Programming", Arrays.asList("Java", "Memory"));
                qs.addQuestion(q);
            } catch (Exception e) { }
        }
        boolean deleted = qs.deleteQuestion(questionId);
        if (deleted && expectedPass) {
            System.out.println("***Success***: Question deleted successfully.");
            numPassed++;
        } else if (!deleted && !expectedPass) {
            System.out.println("***Success***: Question deletion failed as expected.");
            numPassed++;
        } else {
            System.out.println("***Failure***: Unexpected result during deletion.");
            numFailed++;
        }
        System.out.println();
    }
}
