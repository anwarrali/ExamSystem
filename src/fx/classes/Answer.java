package fx.classes;

public class Answer {
    private  int questionId;
    private  String selectedAnswer;
    private String material;
    private String answer;
    public Answer(int questionId, String selectedAnswer, String material) {
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
        this.material = material;
    }

    public  int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public  String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getMaterial() {
        return material;
    }
}

