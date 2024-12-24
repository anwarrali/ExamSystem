package fx.controllers;

import fx.Main;
import fx.classes.User;
import fx.classes.utility;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;


import java.util.List;
public class signup {
    @FXML
    private TextField first;
    @FXML
    private TextField last;
    @FXML
    private TextField passup;
    @FXML
    private TextField userup;
    @FXML
    private TextField passin1;
    @FXML
    private TextField email;
    @FXML
    private TextField phonenum;
    @FXML
    Button signup1;
    @FXML
    private Label signupstatus;
    @FXML
    private ImageView iconn;
    @FXML
    public void initialize() {
        if (iconn != null) {
           Image image = new Image(getClass().getResource("/img/graduation.png").toExternalForm());
            iconn.setImage(image);
       }
        if(first != null) {
            first.setOnAction(e -> last.requestFocus());
            last.setOnAction(e -> userup.requestFocus());
            userup.setOnAction(e -> email.requestFocus());
            email.setOnAction(e -> phonenum.requestFocus());
            phonenum.setOnAction(e -> passup.requestFocus());
            passup.setOnAction(e -> passin1.requestFocus());
            passin1.setOnAction(e ->signup1.fire() );

        }

    }
    @FXML
    public void login(ActionEvent event) throws Exception {

        Main.getInstance().setRoot("/fx/fxmlFiles/mainfx.fxml");

    }
    @FXML
    public void signup(ActionEvent event) throws Exception {
       // Main.getInstance().setRoot("/fx/fxmlFiles/signUp.fxml");

        if ( first == null ||last==null|| userup == null || passup == null ||email==null||phonenum==null || passin1==null ) {
            System.out.println("Error: One or more TextField components are not initialized!");
        }

        String firstName = first.getText();
        String lastName = last.getText();
        String username = userup.getText();
        String emailaddress = email.getText();
        String num = phonenum.getText();
        String password = passup.getText();
        String verifyPassword = passin1.getText();
        String role = Main.getInstance().getRole();
        System.out.println("first: " + first);


        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            signupstatus.setText("Signup failed: Password must be at least 8 characters, including letters, numbers, and symbols");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;
        }
        if (!password.equals(verifyPassword)) {
            System.out.println("upstatus updated: " + signupstatus.getText());

            signupstatus.setText("Signup failed: Passwords do not match");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;
        }

        if (!emailaddress.contains("@") || !emailaddress.contains(".")) {
            signupstatus.setText("Signup failed: Invalid email");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;
        }

        if (num.length() != 10 || !num.matches("\\d+")) {
            signupstatus.setText("Signup failed: Invalid phone number");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;
        }

//        if ( firstName.isBlank() || username.isBlank() || password.isBlank()) {
//            System.out.println("Signup failed: All fields are required.");
//            return;
//        }
//
//        if (!password.equals(verifyPassword)) {
//            System.out.println("Signup failed: Passwords do not match.");
//            return;
//        }
        List<User> users = utility.loadUsers();
        boolean userExists = users.stream().anyMatch(u -> u.getUsername().equals(username));
        boolean phoneExists = users.stream().anyMatch(u -> u.getNumber().equals(num));

        if (userExists) {
            signupstatus.setText("Signup failed: Username already exists.");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;

        }

        if (phoneExists) {
            signupstatus.setText("Signup failed: Phone number already exists.");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;

        }
        User newUser = new User(username, password, role, firstName,lastName, emailaddress, num);
        users.add(newUser);
        utility.saveUsers(users);
        signupstatus.setText("Signup successful!");
        signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");

    }
}
