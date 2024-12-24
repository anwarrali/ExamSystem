package fx.controllers;
import fx.Main;
import fx.classes.Answer;
import fx.classes.Question;
import fx.classes.Score;
import fx.classes.utility;
import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class media {
    @FXML
    private Pagination pane;
    @FXML
    private Label questionLabel;

    private List<Question> questions;
    @FXML
    private Label score;
    @FXML
    private Label perecntage;
    @FXML
    private Label examdsc;
    @FXML
    private ImageView matimg;

    private static Score resultData;

    @FXML
    public void initialize () {
        if (resultData != null) {
            System.out.println("Score: " + resultData.getScore());
            System.out.println("Percentage: " + resultData.getPercentage());
            score.setText(String.valueOf(resultData.getScore()));
            perecntage.setText(String.format("%.2f%%", resultData.getPercentage()));
        } else {
            System.out.println("resultData is null. Cannot update labels!");
        }


        questions = utility.loadRandomQuestions("src/fx/db/media.json");


        if (!questions.isEmpty()) {
            pane.setPageCount(30);
            pane.setPageFactory(this::createPage);
        } else {
            System.out.println("No questions available!");
        }

    }

    private AnchorPane createPage ( int pageIndex){
        if (pageIndex >= 30) return null;

        AnchorPane page = new AnchorPane();
        Question currentQuestion = questions.get(pageIndex);
        ToggleGroup toggleGroup = new ToggleGroup();

        questionLabel.setText(currentQuestion.getText());
        questionLabel.setLayoutX(50);
        questionLabel.setLayoutY(50);

        RadioButton op1 = new RadioButton(currentQuestion.getOptions().get(0));
        op1.setLayoutX(50);
        op1.setLayoutY(100);
        op1.setUserData(currentQuestion.getMaterial());
        op1.getStyleClass().add("radio-button");
        op1.setToggleGroup(toggleGroup);

        RadioButton op2 = new RadioButton(currentQuestion.getOptions().get(1));
        op2.setLayoutX(50);
        op2.setLayoutY(150);
        op2.setUserData(currentQuestion.getMaterial());
        op2.getStyleClass().add("radio-button");
        op2.setToggleGroup(toggleGroup);

        RadioButton op3 = new RadioButton(currentQuestion.getOptions().get(2));
        op3.setLayoutX(50);
        op3.setLayoutY(200);
        op3.setUserData(currentQuestion.getMaterial());
        op3.getStyleClass().add("radio-button");
        op3.setToggleGroup(toggleGroup);

        RadioButton op4 = new RadioButton(currentQuestion.getOptions().get(3));
        op4.setLayoutX(50);
        op4.setLayoutY(250);
        op4.setUserData(currentQuestion.getMaterial());
        op4.getStyleClass().add("radio-button");
        op4.setToggleGroup(toggleGroup);
        op1.setOnAction(event -> captureAnswer(pageIndex, op1));
        op2.setOnAction(event -> captureAnswer(pageIndex, op2));
        op3.setOnAction(event -> captureAnswer(pageIndex, op3));
        op4.setOnAction(event -> captureAnswer(pageIndex, op4));
        Answer existingAnswer = userAnswers.stream()
                .filter(answer -> answer.getQuestionId() == currentQuestion.getId())
                .findFirst()
                .orElse(null);

        if (existingAnswer != null) {
            if (existingAnswer.getSelectedAnswer().equals(op1.getText())) {
                op1.setSelected(true);
            } else if (existingAnswer.getSelectedAnswer().equals(op2.getText())) {
                op2.setSelected(true);
            } else if (existingAnswer.getSelectedAnswer().equals(op3.getText())) {
                op3.setSelected(true);
            } else if (existingAnswer.getSelectedAnswer().equals(op4.getText())) {
                op4.setSelected(true);
            }
        }

        op1.setOnAction(event -> captureAnswer(pageIndex, op1));
        op2.setOnAction(event -> captureAnswer(pageIndex, op2));
        op3.setOnAction(event -> captureAnswer(pageIndex, op3));
        op4.setOnAction(event -> captureAnswer(pageIndex, op4));

        page.getChildren().addAll(questionLabel, op1, op2, op3, op4);
        return page;
    }

    List<Answer> userAnswers = new ArrayList<>();
    public void captureAnswer ( int pageIndex, RadioButton selectedOption){
        Question currentQuestion = questions.get(pageIndex);

        Answer existingAnswer = userAnswers.stream()
                .filter(answer -> answer.getQuestionId() == currentQuestion.getId())
                .findFirst()
                .orElse(null);

        if (existingAnswer != null) {
            existingAnswer.setSelectedAnswer(selectedOption.getText());
        } else {
            Answer userAnswer = new Answer(
                    currentQuestion.getId(),
                    selectedOption.getText(),
                    selectedOption.getUserData().toString()
            );
            userAnswers.add(userAnswer);
        }


        System.out.println("Captured Answer: Question ID = " + currentQuestion.getId() +
                ", Selected Option = " + selectedOption.getText());
    }


    public int calculateScore () {
        int score = 0;

        for (Answer userAnswer : userAnswers) {
            Question question = findQuestionById(userAnswer.getQuestionId());
            if (question != null && userAnswer.getSelectedAnswer().equals(question.getCorrectAnswer())) {
                score++;
            }
        }

        return score;
    }


    public Question findQuestionById ( int questionId){
        for (Question question : questions) {
            if (question.getId() == questionId) {
                return question;
            }
        }
        return null;
    }

    public String getMostAnsweredMaterial () {
        Map<String, Integer> materialCounts = new HashMap<>();

        for (Answer userAnswer : userAnswers) {
            materialCounts.put(userAnswer.getMaterial(),
                    materialCounts.getOrDefault(userAnswer.getMaterial(), 0) + 1);
        }

        String mostAnsweredMaterial = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : materialCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostAnsweredMaterial = entry.getKey();
            }
        }

        return mostAnsweredMaterial;
    }
    @FXML
    public void submit (javafx.event.ActionEvent event) throws Exception {

        int score = calculateScore();
        double percentage = (score * 100.0) / 30;
        String mostAnsweredMaterial = getMostAnsweredMaterial();

        Score resultData = new Score(score, percentage, null);

        List<Score> results = utility.loadResults();
        results.add(resultData);

        utility.saveResults(results);
        Main.getInstance().setRoot("/fx/fxmlFiles/result2.fxml");
    }

}

