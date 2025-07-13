package fx.classes;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<String> options;
    private String correctAnswer;
    private String material;

    public Question() {
        this.options = new ArrayList<>();
    }

    // Constructor with all parameters
    public Question(int id, String text, List<String> options, String correctAnswer, String material) {
        this.id = id;
        this.text = text;
        this.options = (options != null) ? new ArrayList<>(options) : new ArrayList<>();
        this.correctAnswer = correctAnswer;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = (options != null) ? new ArrayList<>(options) : new ArrayList<>();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
