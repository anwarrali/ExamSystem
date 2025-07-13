package fx.controllers;

import fx.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


import javax.print.DocFlavor;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class admin {


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
        Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");

    }

    @FXML
    public void feed(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/feedback.fxml");

    }





}
