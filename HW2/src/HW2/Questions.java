package HW2;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questionList;

    public Questions() {
        questionList = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        questionList.add(q);
    }

    public Question getQuestion(int id) {
        for (Question q : questionList) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null; // Not found.
    }

    public List<Question> searchQuestions(String keyword) {
        List<Question> result = new ArrayList<>();
        for (Question q : questionList) {
            if (q.getText().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(q);
            }
        }
        return result;
    }

    public boolean deleteQuestion(int id) {
        for (Question q : questionList) {
            if (q.getId() == id) {
                questionList.remove(q);
                return true;
            }
        }
        return false;
    }

    public List<Question> getAllQuestions() {
        return questionList;
    }
}
