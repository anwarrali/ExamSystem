package fx.controllers;

import fx.Main;
import fx.classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.*;

import static fx.classes.utility.getConnection;

public class se {
    @FXML
    private Pagination pane;
    @FXML
    private Label questionLabel;
    @FXML
    private Label score;
    @FXML
    private Label perecntage;
    @FXML
    private Label examdsc;
    @FXML
    private ImageView matimg;

    private List<Question> questions;
    private List<Answer> userAnswers = new ArrayList<>();

    private static Score resultData;
    private String category = "art";
    private int limit = 0;
    private int studentId = -1;
    private int examId = -1;
    //im here
    User currentUser = SessionManager.getInstance().getCurrentUser();

    @FXML
    public void initialize() {
        if (currentUser == null) {
            System.out.println("No user logged in.");
            return;
        }

        System.out.println("Current user: " + currentUser.getUsername());

//        if (resultData != null) {
//            score.setText(String.valueOf(resultData.getScore()));
//            perecntage.setText(String.format("%.2f%%", resultData.getPercentage()));
//        } else {
//            System.out.println("resultData is null. Cannot update labels!");
//        }

        try (Connection con = getConnection()) {
            String query = "SELECT COUNT(*) FROM questions WHERE exam_id = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, 41);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    limit = rs.getInt(1);
                    System.out.println("limit is " + limit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        studentId = getStudentId(currentUser.getUsername());
        examId = getCurrentExamId();

        if (studentId == -1 || examId == -1) {
            System.out.println("Error: Unable to retrieve student ID or exam ID.");
            return;
        }

        questions = utility.loadRandomQuestions(limit, category);
        if (!questions.isEmpty()) {
            pane.setPageCount(limit);
            pane.setPageFactory(this::createPage);
        } else {
            System.out.println("No questions available!");
        }
    }

    private AnchorPane createPage(int pageIndex) {
        if (pageIndex >= questions.size()) return null;

        AnchorPane page = new AnchorPane();
        Question currentQuestion = questions.get(pageIndex);
        if (currentQuestion == null) return null;

        Label questionLabelLocal = new Label(currentQuestion.getText());
        questionLabelLocal.setLayoutX(50);
        questionLabelLocal.setLayoutY(50);
        questionLabelLocal.getStyleClass().add("question-label");

        ToggleGroup toggleGroup = new ToggleGroup();
        List<RadioButton> radioButtons = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            RadioButton rb = new RadioButton(currentQuestion.getOptions().get(i));
            rb.setLayoutX(50);
            rb.setLayoutY(100 + i * 50);
            rb.setUserData(currentQuestion.getMaterial());
            rb.getStyleClass().add("radio-button");
            rb.setToggleGroup(toggleGroup);
            int finalI = i;
            rb.setOnAction(event -> captureAnswer(pageIndex, rb));
            radioButtons.add(rb);
        }

        page.getChildren().add(questionLabelLocal);
        page.getChildren().addAll(radioButtons);

        return page;
    }

    public void captureAnswer(int pageIndex, RadioButton selectedOption) {
        Question currentQuestion = questions.get(pageIndex);
        calculateScore();
        System.out.println("Selected option text: " + selectedOption.getText());

        int optionId = getOptionIdFromText(currentQuestion.getId(), selectedOption.getText());
        if (optionId == -1) {
            System.out.println("Error: Option ID not found for selected answer.");
            return;
        }

        Answer existingAnswer = userAnswers.stream()
                .filter(answer -> answer.getQuestionId() == currentQuestion.getId())
                .findFirst().orElse(null);

        if (existingAnswer != null) {
            existingAnswer.setSelectedAnswer(optionId);
        } else {
            userAnswers.add(new Answer(currentQuestion.getId(), optionId, selectedOption.getUserData().toString()));
        }
    }
    int scores = 0;
    public void calculateScore() {

        System.out.println("Current user: " + currentUser.getUsername());
        for (Answer userAnswer : userAnswers) {
            int correctOptionId = getCorrectOptionIdFromDB(userAnswer.getQuestionId());
            //im here
            System.out.println("Checking QID: " + userAnswer.getQuestionId() + " | Correct: " + correctOptionId + " | Selected: " + userAnswer.getSelectedAnswer());

            if (correctOptionId == userAnswer.getSelectedAnswer()) {
                scores++;
            }
        }

    }

    private int getOptionIdFromText(int questionId, String selectedText) {
        String query = "SELECT option_id FROM options WHERE question_id = ? AND option_text = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            stmt.setString(2, selectedText);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("option_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getCorrectOptionIdFromDB(int questionId) {
        String query = "SELECT correct_option_id FROM answers WHERE question_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("correct_option_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getStudentId(String username) {
        String sql = "SELECT student_id FROM students WHERE username = ?";
        try (Connection conn = getConnection();
             //im here
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("student_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getCurrentExamId() {
        String sql = "SELECT exam_id FROM exams WHERE TITLE = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("exam_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String getExamFeedback(int studentId, int examId) {
        String sql = "SELECT feedback FROM exam_attempts WHERE student_id = ? AND exam_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, examId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("feedback");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getMostAnsweredMaterial() {
        Map<String, Integer> materialCounts = new HashMap<>();
        for (Answer userAnswer : userAnswers) {
            materialCounts.merge(userAnswer.getMaterial(), 1, Integer::sum);
        }
        return materialCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    @FXML
    public void submit(javafx.event.ActionEvent event) throws Exception {
        int scoreValue = scores;
        System.out.println("score"+scoreValue);
        double percentage = (scoreValue * 100.0) / limit;
        String mostMaterial = getMostAnsweredMaterial();
        String feedback = getExamFeedback(studentId, examId);

        try (Connection con = getConnection()) {
            String getAttemptIdSQL = "SELECT attempt_id FROM exam_attempts WHERE student_id = ? AND exam_id = ?";
            try (PreparedStatement stmt = con.prepareStatement(getAttemptIdSQL)) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, examId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int attemptId = rs.getInt("attempt_id");
                    String insertSQL = "INSERT INTO exam_answers (attempt_id, question_id, selected_option_id) VALUES (?, ?, ?)";

                    for (Answer answer : userAnswers) {
                        try (PreparedStatement insertStmt = con.prepareStatement(insertSQL)) {
                            insertStmt.setInt(1, attemptId);
                            insertStmt.setInt(2, answer.getQuestionId());
                            insertStmt.setInt(3, answer.getSelectedAnswer());
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
        }

        resultData = new Score(scoreValue, percentage, mostMaterial, studentId, examId, feedback, limit, scoreValue);
        utility.saveResults(resultData);
        result.setResultData(resultData);

        Main.getInstance().setRoot("/fx/fxmlFiles/result2.fxml");
    }
}
