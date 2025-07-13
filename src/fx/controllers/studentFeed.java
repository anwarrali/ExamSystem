package fx.controllers;

import fx.Main;
import fx.classes.SessionManager;
import fx.classes.utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class studentFeed {
    String username = SessionManager.getInstance().getCurrentUser().getUsername();

    @FXML
    private ListView<String> studentListView;

    public void initialize() {
        String username = SessionManager.getInstance().getCurrentUser().getUsername();

        String sql = """
        SELECT ea.exam_date, ea.feedback
        FROM exam_attempts ea
        JOIN students s ON ea.student_id = s.student_id
        WHERE s.username = ?
        ORDER BY ea.exam_date DESC
    """;

        try {
            Connection myConn = utility.getConnection();
            PreparedStatement stmt = myConn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            studentListView.getItems().clear();
            Set<String> shownFeedbacks = new HashSet<>();

            while (rs.next()) {
                String date = rs.getString("exam_date");
                String feedback = rs.getString("feedback");

                if (feedback == null || shownFeedbacks.contains(feedback)) continue;

                String row = date + "\t\t\t\t" + feedback;
                studentListView.getItems().add(row);
                shownFeedbacks.add(feedback);  // Mark this feedback as shown
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void back(ActionEvent event) throws Exception {

        Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");


    }
}
