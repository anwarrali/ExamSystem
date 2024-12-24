package fx.controllers;

import fx.Main;
import fx.classes.Score;
import fx.classes.SessionManager;
import fx.classes.User;
import fx.classes.utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

public class result2 {
    @FXML
    private Label scorelabel;
    @FXML
    private Label perecntage;
    @FXML
    private Label examdsc;
    @FXML
    private ImageView matimg;

    private static Score resultData;

    public static void setResultData(Score data) {
        resultData = data;
    }

    @FXML
    public void initialize() {
        List<Score> results = utility.loadResults();
        User user = SessionManager.getInstance().getCurrentUser();

        if (!results.isEmpty()) {
            Score latestResult = results.getLast();

            scorelabel.setText("score :"+(latestResult.getScore())+"/30");
            perecntage.setText("percentage :"+String.format("%.2f%%", latestResult.getPercentage()));
    if(latestResult.getPercentage()>75){
    examdsc.setText("amazing! \n" +
            "You should join this field");
    matimg.setImage(new Image("/img/mark.png"));
    } else if (latestResult.getPercentage()<75 && latestResult.getPercentage()>50) {
        examdsc.setText("good, \n" +
                "But I advise you to pursue other fields that suit your mind");
        matimg.setImage(new Image("/img/mark.png"));
    }else{
        examdsc.setText("I'm sorry,\n" +
                        "But I advise you to choose another field and not join it ");
        matimg.setImage(new Image("/img/cancel.png"));
    }

        }
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