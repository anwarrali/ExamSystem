package fx.controllers;

import fx.Main;
import fx.classes.utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class newFeed {
    private int studentId;
    private int AttemptId;
    @FXML
    private ListView<String> studentListView;

    @FXML
    private Label mess;

    @FXML
    private TextField feed;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setAttemptId(int AttemptId) {
        this.AttemptId = AttemptId;
    }

    public void loadData() {
        String sql = """
            SELECT q.question_text, o.option_text
            FROM exam_answers ea
            JOIN exam_attempts ea2 ON ea.attempt_id = ea2.attempt_id
            JOIN questions q ON ea.question_id = q.question_id
            JOIN options o ON ea.selected_option_id = o.option_id
            WHERE ea2.attempt_id = ?
        """;

        try {
            Connection myConn = utility.getConnection();
            PreparedStatement stmt = myConn.prepareStatement(sql);
            stmt.setInt(1, AttemptId);
            ResultSet rs = stmt.executeQuery();

            studentListView.getItems().clear();
            studentListView.getItems().add("selected answers" + "\t\t\t\t" + "Question Text");

            while (rs.next()) {
                String Q = rs.getString("QUESTION_TEXT");
                String OPTION = rs.getString("OPTION_TEXT");
                String row = "Answer: " + OPTION +"\t\t\t" + Q;
                studentListView.getItems().add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void send(javafx.event.ActionEvent event) throws IOException {
        String sqlFeed = "UPDATE exam_attempts SET feedback = ? WHERE attempt_id = ?";

        String feedback = feed.getText();
        try (Connection Conn = utility.getConnection();
             PreparedStatement stmt = Conn.prepareStatement(sqlFeed)) {

            stmt.setString(1, feedback);
            stmt.setInt(2, AttemptId);

            int rowsUpdated = stmt.executeUpdate();
            //im here


            if (rowsUpdated > 0) {
                if(feed == null) {
                    mess.setText("No feedback to send!");
                }else
                {mess.setText("Feedback sent successfully!");}
            } else {
                mess.setText("Feedback not sent!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");
    }

    @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {
        System.out.println("im here");
        Main.getInstance().setRoot("/fx/fxmlFiles/feedback.fxml");
        System.out.println("im here2");
    }

}
