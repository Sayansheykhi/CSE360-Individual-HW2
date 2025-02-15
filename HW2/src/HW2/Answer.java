package HW2;

public class Answer {
    private int id;
    private int questionId;
    private String text;
    private String author;

    public Answer(int id, int questionId, String text, String author) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.author = author;
    }

    // Getters
    public int getId() {
        return id;
    }
    public int getQuestionId() {
        return questionId;
    }
    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }

    // Update method
    public void update(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty.");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer ID: " + id + "\nQuestion ID: " + questionId + "\nText: " + text + "\nAuthor: " + author;
    }
}
