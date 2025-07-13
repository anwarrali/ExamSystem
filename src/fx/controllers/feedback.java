package fx.controllers;

import fx.Main;
import fx.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class feedback {
    private Map<String, String> studentIdMap = new HashMap<>();
    private Map<String, String> attemptIdMap = new HashMap<>();


    @FXML
    private ListView<String> studentListView;

    @FXML
    public void initialize() {
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        String db_user="C##EXAMNEW";
        String db_pass="EXAM123";

        try{
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch(Exception e){
                e.printStackTrace();
            }

            Connection myConn=DriverManager.getConnection(url,db_user,db_pass);
            String sql="select * from students s join exam_attempts e on s.student_id=e.student_id";

            PreparedStatement stmt=myConn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            studentListView.getItems().add("name"+"\t\t\t\t"+"attempt_id"+"\t\t\t\t"+"score");

            while (rs.next()) {
                String name = rs.getString("FULL_NAME");
                String attempt_id = rs.getString("ATTEMPT_ID");
                String score = rs.getString("SCORE");
                String student_id = rs.getString("STUDENT_ID");
                String row=name+"\t\t\t\t"+attempt_id+"\t\t\t\t\t\t"+score;
                studentListView.getItems().add(row);
                studentIdMap.put(row, student_id);
                attemptIdMap.put(row, attempt_id);

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        studentListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedStudent = studentListView.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    String studentIdStr = studentIdMap.get(selectedStudent);
                    if (studentIdStr != null) {
                        try {
                            int studentId = Integer.parseInt(studentIdStr);
                            int attemptId = Integer.parseInt(attemptIdMap.get(selectedStudent));
                            openStudentDetails(studentId,attemptId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
;
    }


    private void openStudentDetails(int student_id,int attempt_id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/fxmlFiles/newFeed.fxml"));
        Parent root = loader.load();

        newFeed controller = loader.getController();
        controller.setStudentId(student_id);
        controller.setAttemptId(attempt_id);
        controller.loadData();


        Stage stage = (Stage) studentListView.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void home(ActionEvent event) throws Exception {
        if(Main.getInstance().getRole().equals("admin")){
            Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");
        }else {
            Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");}
    }
    @FXML
    public void profile(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/profile.fxml");

    }
    @FXML
    public void about(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/about.fxml");

    }
    @FXML
    public void logout(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/start.fxml");
    }
    @FXML
    public void exams(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/exams.fxml");

    }

    @FXML
    public void add(javafx.event.ActionEvent event) {
        Main.getInstance().setRoot("/fx/fxmlFiles/addquestion.fxml");

    }



}
