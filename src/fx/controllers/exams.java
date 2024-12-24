package fx.controllers;

import fx.Main;
import fx.classes.SessionManager;
import fx.classes.User;
import javafx.fxml.FXML;
import java.io.IOException;

import javafx.scene.layout.AnchorPane;



public class exams {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public void initialize() {
        User user = SessionManager.getInstance().getCurrentUser();

    }
 @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {
     Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");

 }
    @FXML
    public void med(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/med.fxml");


    }

    @FXML
    public void se(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/se.fxml");


    }

    @FXML
    public void cs(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/cs.fxml");


    }

     @FXML
    public void eco(javafx.event.ActionEvent event) throws IOException {
         Main.getInstance().setRoot("/fx/fxmlFiles/eco.fxml");

     } @FXML
    public void art(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/art.fxml");

    } @FXML
    public void media(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/media.fxml");

    }
    @FXML
    public void eng(javafx.event.ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/eng.fxml");
    }



}
