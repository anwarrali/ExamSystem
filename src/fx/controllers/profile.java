package fx.controllers;

import fx.Main;
import fx.classes.SessionManager;
import fx.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class profile {
    @FXML
    private Label fullName;
    @FXML
    private Label role;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label UserName;

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
    public void initialize() {
        User user = SessionManager.getInstance().getCurrentUser();

        fullName.setText("name: " + user.getFirst()+" "+ user.getLastName());
        email.setText("email-address: " + user.getEmailaddress());
        phone.setText("phone number: "+user.getNumber());
        UserName.setText("username: " + user.getUsername());
        role.setText(user.getRole());



    }

}
