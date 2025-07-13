package fx.classes;

public class Answer {
    private  int questionId;
    private  int selectedAnswer;
    private String material;


    public Answer(int questionId, int selectedAnswer, String material) {
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
        this.material = material;
    }

    public  int getQuestionId() {
        return questionId;
    }

    public  int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getMaterial() {
        return material;
    }
}

