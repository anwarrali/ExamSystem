package fx.controllers;


import fx.Main;

import fx.classes.SessionManager;
import fx.classes.User;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Label;


public class home {
    @FXML
    private ImageView examimg;
   @FXML
    Label namestd;

    @FXML
    public void initialize() {
        User user = SessionManager.getInstance().getCurrentUser();

        if (examimg != null) {
            Image image = new Image(getClass().getResource("/img/test.png").toExternalForm());
            examimg.setImage(image);
        } else {
            System.out.println("Icon is null");
        }
        if (namestd != null) {
            namestd.setText("Hello, " + user.getFirst());
        }

    }
    @FXML
    public void exam(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/GenExam.fxml");

    }
    @FXML
    public void home(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");

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
