package fx.controllers;

import fx.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ResourceBundle;

import static fx.classes.utility.getConnection;

public class adminHome implements Initializable {
    @FXML
    private Label st;
    @FXML
    private TextField qs;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;
    @FXML
    private TextField op3;
    @FXML
    private TextField op4;
    @FXML
    private RadioButton ans1;
    @FXML
    private RadioButton ans2;
    @FXML
    private RadioButton ans3;
    @FXML
    private RadioButton ans4;
    private static adminHome instance;
    @FXML
    public ChoiceBox<String> field;
    private final String[] fields = {
            "General exam", "economy", "media", "engineering",
            "medicine", "sciences", "technology", "art"
    };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        field.getItems().addAll(fields);
    }

    public adminHome() {
        instance = this;
    }

    public static adminHome getInstance() {
        return instance;
    }
    public TextField getOp2() {
        return op2;
    }

    public RadioButton getAns1() {
        return ans1;
    }

    public RadioButton getAns2() {
        return ans2;
    }

    public RadioButton getAns3() {
        return ans3;
    }

    public RadioButton getAns4() {
        return ans4;
    }

    public TextField getOp1() {
        return op1;
    }

    public TextField getOp3() {
        return op3;
    }

    public TextField getOp4() {
        return op4;
    }

    public TextField getQs() {
        return qs;
    }

    @FXML
  public void add(javafx.event.ActionEvent event) {
      Main.getInstance().setRoot("/fx/fxmlFiles/addquestion.fxml");

  }
    @FXML
    public void home(javafx.event.ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/admin.fxml");

    }
    @FXML
    public void profile(javafx.event.ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/profile.fxml");

    }
    @FXML
    public void about(javafx.event.ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/about.fxml");

    }
    @FXML
    public void logout(javafx.event.ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/start.fxml");
    }

    @FXML
    public void addbtn(javafx.event.ActionEvent event) throws IOException {
        if (qs.getText().isEmpty() || op1.getText().isEmpty() || op2.getText().isEmpty()
                || op3.getText().isEmpty() || op4.getText().isEmpty() || field.getValue() == null) {
            st.setText("Please fill in all fields!");
            st.setStyle("-fx-text-fill: red;");
            return;
        }

        String questionText = qs.getText();
        String[] options = {op1.getText(), op2.getText(), op3.getText(), op4.getText()};
        int correctIndex = ans1.isSelected() ? 0 : ans2.isSelected() ? 1 : ans3.isSelected() ? 2 : ans4.isSelected() ? 3 : -1;

        if (correctIndex == -1) {
            st.setText("Please select the correct answer!");
            st.setStyle("-fx-text-fill: red;");
            return;
        }

        String examTitle = field.getValue();
        int adminId =10;

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "C##EXAMNEW", "EXAM123")) {

            int examId = -1;
            String examQuery = "SELECT exam_id FROM exams WHERE title = ?";
            PreparedStatement psExam = conn.prepareStatement(examQuery);
            psExam.setString(1, examTitle);
            ResultSet rsExam = psExam.executeQuery();
            if (rsExam.next()) {
                examId = rsExam.getInt("exam_id");
            } else {
                String insertExam = "INSERT INTO exams (title, created_by) VALUES (?, ?)";
                PreparedStatement psNewExam = conn.prepareStatement(insertExam, new String[]{"exam_id"});
                psNewExam.setString(1, examTitle);
                psNewExam.setInt(2, adminId);
                psNewExam.executeUpdate();
                ResultSet rsNewExam = psNewExam.getGeneratedKeys();
                if (rsNewExam.next()) {
                    examId = rsNewExam.getInt(1);
                }
            }

            String insertQuestion = "INSERT INTO questions (exam_id, question_text) VALUES (?, ?)";
            PreparedStatement psQuestion = conn.prepareStatement(insertQuestion, new String[]{"question_id"});
            psQuestion.setInt(1, examId);
            psQuestion.setString(2, questionText);
            psQuestion.executeUpdate();
            ResultSet rsQuestion = psQuestion.getGeneratedKeys();
            int questionId = -1;
            if (rsQuestion.next()) {
                questionId = rsQuestion.getInt(1);
            }

            int correctOptionId = -1;
            for (int i = 0; i < options.length; i++) {
                String insertOption = "INSERT INTO options (question_id, option_text) VALUES (?, ?)";
                PreparedStatement psOption = conn.prepareStatement(insertOption, new String[]{"option_id"});
                psOption.setInt(1, questionId);
                psOption.setString(2, options[i]);
                psOption.executeUpdate();
                ResultSet rsOption = psOption.getGeneratedKeys();
                if (rsOption.next()) {
                    int optionId = rsOption.getInt(1);
                    if (i == correctIndex) {
                        correctOptionId = optionId;
                    }
                }
            }

            String insertAnswer = "INSERT INTO answers (question_id, correct_option_id) VALUES (?, ?)";
            PreparedStatement psAnswer = conn.prepareStatement(insertAnswer);
            psAnswer.setInt(1, questionId);
            psAnswer.setInt(2, correctOptionId);
            psAnswer.executeUpdate();

            st.setText("Question added successfully!");
            st.setStyle("-fx-text-fill: green;");

            qs.clear();
            op1.clear(); op2.clear(); op3.clear(); op4.clear();
            ans1.setSelected(false); ans2.setSelected(false); ans3.setSelected(false); ans4.setSelected(false);

        } catch (SQLException e) {
            e.printStackTrace();
            st.setText("Database error: " + e.getMessage());
            st.setStyle("-fx-text-fill: red;");
        }
    }


}
