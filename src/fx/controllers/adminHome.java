package fx.controllers;

import fx.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class adminHome {
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
        Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");

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
        if (qs.getText() != null && !qs.getText().isEmpty() &&
                op1.getText() != null && !op1.getText().isEmpty() &&
                op2.getText() != null && !op2.getText().isEmpty() &&
                op3.getText() != null && !op3.getText().isEmpty() &&
                op4.getText() != null && !op4.getText().isEmpty()){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/fxmlFiles/addquestion.fxml"));
        Parent root = loader.load();

        addquestion controller = loader.getController();
        if (controller == null) {
            System.out.println("Controller is null");
            return;
        }
        controller.setAdminHome(this);

        Stage stage = (Stage) qs.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


        }else{
            st.setText("Add A question first!");
            st.setStyle(st.getStyle()+" -fx-text-fill: red;");
        }
    }




}
