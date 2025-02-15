package HW2;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private String category;
    private List<String> tags;

    public Question(int id, String text, String category, List<String> tags) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.id = id;
        this.text = text;
        this.category = category;
        this.tags = tags;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getCategory() {
        return category;
    }
    public List<String> getTags() {
        return tags;
    }

    // Update method
    public void update(String text, String category, List<String> tags) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        this.text = text;
        this.category = category;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question ID: " + id + "\nText: " + text + "\nCategory: " + category + "\nTags: " + tags;
    }
}
