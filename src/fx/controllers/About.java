package fx.controllers;

import fx.Main;
import fx.classes.SessionManager;
import fx.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class About {
    @FXML
    public void initialize() {
        User user = SessionManager.getInstance().getCurrentUser();

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
}
