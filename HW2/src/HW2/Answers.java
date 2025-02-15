package HW2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answers {
    // Maps a questionId to a list of its answers.
    private Map<Integer, List<Answer>> answersByQuestion;

    public Answers() {
        answersByQuestion = new HashMap<>();
    }

    public void addAnswer(Answer a) {
        answersByQuestion.computeIfAbsent(a.getQuestionId(), k -> new ArrayList<>()).add(a);
    }

    public List<Answer> getAnswersForQuestion(int questionId) {
        return answersByQuestion.getOrDefault(questionId, new ArrayList<>());
    }

    public boolean deleteAnswer(int id, int questionId) {
        List<Answer> ansList = answersByQuestion.get(questionId);
        if (ansList != null) {
            for (Answer a : ansList) {
                if (a.getId() == id) {
                    ansList.remove(a);
                    return true;
                }
            }
        }
        return false;
    }
}
