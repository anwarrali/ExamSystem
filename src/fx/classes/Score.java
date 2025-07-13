package fx.classes;

public class Score {

    private int score;
    private double percentage;
    private String mostAnsweredMaterial;
    private int studentId;
    private int examId;
    private String feedback;
    private int totalQuestions;
    private int correctAnswers;

    public Score(
           int score,
           double percent,
           String material,
           int studentId,
           int examId,
           String feedback,
           int totalQuestions,
           int correctAnswers
    ) {
        this.score = score;
        this.percentage = percent;
        this.mostAnsweredMaterial = material;
        this.studentId = studentId;
        this.examId = examId;
        this.feedback = feedback;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
    }

    public int getScore() {
        return score;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getMostAnsweredMaterial() {
        return mostAnsweredMaterial;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getExamId() {
        return examId;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
