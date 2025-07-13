package fx.controllers;

import fx.Main;
import fx.classes.Score;
import fx.classes.utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static fx.classes.utility.getConnection;

public class result {
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
        int limit = 0;

        Score latestResult = resultData;

        if (latestResult == null) {
            List<Score> results = utility.loadResults();
            if (!results.isEmpty()) {
                latestResult = results.getLast();
            }
        }

        if (latestResult != null) {
            try (Connection con = getConnection()) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM questions WHERE exam_id = " + latestResult.getExamId());
                if (rs.next()) {
                    limit = rs.getInt(1);
                    System.out.println("limit is " + limit);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            scorelabel.setText("score : " + latestResult.getScore() + "/" + limit);
            perecntage.setText("percentage : " + String.format("%.2f%%", latestResult.getPercentage()));
            String material=determineMaterialByPercentage(latestResult.getPercentage());

            System.out.println("material: " + material);
            System.out.println("score: " + latestResult.getScore());
            System.out.println("percentage: " + latestResult.getPercentage());
            setMaterialDetails(material);
        } else {
            scorelabel.setText("No result found");
            perecntage.setText("");
        }
    }
    private String determineMaterialByPercentage(double percentage) {
        if (percentage >= 90) {
            return "Mathematics";
        } else if (percentage >= 80) {
            return "Physics";
        } else if (percentage >= 70) {
            return "Technology";
        } else if (percentage >= 60) {
            return "Chemistry";
        } else if (percentage >= 50) {
            return "Biology";
        } else if (percentage >= 40) {
            return "Geography";
        } else if (percentage >= 30) {
            return "History";
        } else if (percentage >= 20) {
            return "Art";
        } else if (percentage >= 10) {
            return "Music";
        } else if(percentage>0){
            return "Economics";
        }
        else{
            return "Other";
        }
    }

    private void setMaterialDetails(String material) {
        if (material.equals("Mathematics")) {
            Image image = new Image("/img/calculating.png");
            matimg.setImage(image);
            examdsc.setText("Mathematics is the subject where you excel the most!\n" +
                    "Congratulations on your exceptional mathematical mindset. Consider fields like Applied Mathematics, Data Science, Artificial Intelligence, or Statistics. These specialties align well with your skills and analytical thinking.");
        } else if (material.equals("Physics")) {
            Image image1 = new Image("/img/atom.png");
            matimg.setImage(image1);
            examdsc.setText("Physics is your top-performing subject! \n" +
                    "Your logical and curious mind stands out. Explore fields like Engineering, Space Sciences, or Medical Physics, where your skills can lead to groundbreaking innovations.");
        } else if (material.equals("Chemistry")) {
            Image image2 = new Image("/img/chemistry.png");
            matimg.setImage(image2);
            examdsc.setText("Chemistry is your forte! \n" +
                    "You have a talent for understanding molecular interactions. Consider fields like Pharmacy, Chemical Engineering, or Materials Science to transform your passion into impactful contributions.");
        } else if (material.equals("Biology")) {
            Image image3 = new Image("/img/bacteria.png");
            matimg.setImage(image3);
            examdsc.setText("Biology is where you shine! \n" +
                    "Your grasp of life sciences is remarkable. Fields like Medicine, Biotechnology, or Environmental Sciences can help you achieve great things.");
        } else if (material.equals("Geography")) {
            Image image4 = new Image("/img/map.png");
            matimg.setImage(image4);
            examdsc.setText("Geography is your strength! \n" +
                    "Your understanding of the physical world is impressive. Explore fields like GIS, Urban Planning, or Environmental Sciences, where your skills can solve global challenges.");
        } else if (material.equals("Astronomy")) {
            Image image5 = new Image("/img/galaxy.png");
            matimg.setImage(image5);
            examdsc.setText("Astronomy is your passion! \n" +
                    "Your interest in celestial bodies and the universe is inspiring. Fields like Astrophysics, Space Sciences, or Aerospace Engineering are excellent paths for your talents.");
        } else if (material.equals("Geology")) {
            Image image6 = new Image("/img/earth.png");
            matimg.setImage(image6);
            examdsc.setText("Geology is your strongest subject! \n" +
                    "Your understanding of Earth’s structure is outstanding. Consider Earth Sciences, Mining Engineering, or Petroleum Engineering to build a career in this field.");
        } else if (material.equals("Technology")) {
            Image image7 = new Image("/img/digital.png");
            matimg.setImage(image7);
            examdsc.setText("Technology is where you shine! \n" +
                    "Your technical skills and innovative thinking are impressive. Explore Computer Science, Software Engineering, or Cybersecurity to make cutting-edge contributions.");
        } else if (material == "Economics" || material == "Literature") {
            Image image8 = new Image("/img/economic.png");
            matimg.setImage(image8);
            examdsc.setText("Economics is your strongest area! \n" +
                    "Your analytical skills in understanding financial systems are remarkable. Fields like Economics, Business Administration, or Accounting could align perfectly with your abilities.");
        } else if (material.equals("History")) {
            Image image9 = new Image("/img/history.png");
            matimg.setImage(image9);
            examdsc.setText("History is your forte! \n" +
                    "Your ability to analyze past events is exceptional. Explore fields like History, Archaeology, or International Relations to create a meaningful career.");
        } else if (material.equals("Music")) {
            Image image11 = new Image("/img/musical-notes.png");
            matimg.setImage(image11);
            examdsc.setText("Music is your passion! \n" +
                    "Your talent for musical expression is extraordinary. Fields like Music Arts, Sound Design, or Music Education could help you develop your artistic abilities further.");
        } else if (material.equals("Art")) {
            Image image12 = new Image("/img/palette.png");
            matimg.setImage(image12);
            examdsc.setText("Art is your strength! \n" +
                    "Your creative skills and visual storytelling are impressive. Consider Fine Arts, Graphic Design, or Interior Design to bring your artistic vision to life.");
        } else if(material.equals("Other")) {
            examdsc.setText("No specific subject detected. Keep exploring and discovering your strengths!");
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
