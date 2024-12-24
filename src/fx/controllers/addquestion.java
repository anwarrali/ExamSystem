package fx.controllers;

import fx.Main;
import fx.classes.Question;
import fx.classes.utility;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class addquestion {

    @FXML
    private Label saveSt;
    private adminHome q;
    public void setAdminHome(adminHome adminHome) {
        this.q = adminHome;
    }

    public void initialize() {}
    public int generateRandomId() {
        int id = ThreadLocalRandom.current().nextInt(0, 1000);
        return id;
    }
    @FXML
    public void se(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error: Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial(" Science");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/science.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void cs(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("Computer Science");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/cs.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void eco(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("economy");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/economy.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void eng(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("engineering");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/eng.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void med(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("medicine");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/medicine.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void art(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("art and design");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/art.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void media(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("media");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/media.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }
    @FXML
    public void gen(ActionEvent event) {
        if (q == null) {
            saveSt.setText("Error:Question is not set.");
            saveSt.setStyle("-fx-text-fill: red;");
            return;
        }
        String qs=q.getQs().getText();
        String op1=q.getOp1().getText();
        String op2=q.getOp2().getText();
        String op3=q.getOp3().getText();
        String op4=q.getOp4().getText();
        boolean ans1=q.getAns1().isSelected();
        boolean ans2=q.getAns2().isSelected();
        boolean ans3=q.getAns3().isSelected();
        boolean ans4=q.getAns4().isSelected();
        Question question = new Question();
        question.setId(generateRandomId());
        question.setText(qs);
        question.setOptions(new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
        question.setCorrectAnswer(ans1 ? op1 :
                ans2 ? op2 :
                        ans3 ? op3 :
                                ans4 ? op4 : null);
        question.setMaterial("general question");

        utility.saveQuestions(Arrays.asList(question), "src/fx/db/Question.json");

        saveSt.setText("Question Saved Successfully");
        saveSt.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");
    }

}
