package fx.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fx.Main;
import fx.classes.SessionManager;
import fx.classes.User;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
public class login {
    @FXML
    private Label status;
    @FXML
    private ImageView iconn;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    Button loginbtn;
    @FXML
    public void initialize() {
        if(user != null) {
            user.setOnAction(e -> pass.requestFocus());
            pass.setOnAction(e ->loginbtn.fire() );
        }
        if (iconn != null) {
            Image image = new Image(getClass().getResource("/img/graduation.png").toExternalForm());
            iconn.setImage(image);
        }
    }
    public static User authenticateUser(String username, String password) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/fx/db/users.json");

        if (!file.exists()) {
            System.out.println("Error: users.json file not found at " + file.getAbsolutePath());
            return null;
        }

        try {
            JsonNode root = mapper.readTree(file);

            for (JsonNode userNode : root) {
                String storedUsername = userNode.get("username").asText();
                String storedPassword = userNode.get("password").asText();

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return new User(
                            userNode.get("username").asText(),
                            userNode.get("password").asText(),
                            userNode.get("role").asText(),
                            userNode.get("first").asText(),
                            userNode.get("lastName").asText(),
                            userNode.get("emailaddress").asText(),
                            userNode.get("number").asText()
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users.json: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    public void loginbtn(ActionEvent event) throws Exception {
        String username = user.getText();
        String password = pass.getText();

        User foundUser = authenticateUser(username, password);

        if (foundUser != null) {
            System.out.println("First Name: " + foundUser.getFirst());
            System.out.println("Email Address: " + foundUser.getEmailaddress());

            SessionManager.getInstance().setCurrentUser(foundUser);
            if(Main.getInstance().getRole().equals("student")){
                Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");
            }else if(Main.getInstance().getRole().equals("admin")){
                Main.getInstance().setRoot("/fx/fxmlFiles/adminHome.fxml");
            }
            //Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");
        } else {
            status.setText("Login failed: Invalid credentials.");
            status.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            status.setVisible(true);
        }

    }
    @FXML
    public void login(ActionEvent event) throws Exception {

        Main.getInstance().setRoot("/fx/fxmlFiles/mainfx.fxml");

    }
    @FXML
    public void back(ActionEvent event) throws Exception {

        Main.getInstance().setRoot("/fx/fxmlFiles/start.fxml");

    }
    @FXML
    public void signup(ActionEvent event) throws Exception {
        Main.getInstance().setRoot("/fx/fxmlFiles/signUp.fxml");}
}
