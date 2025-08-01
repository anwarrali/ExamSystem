package fx.controllers;
import java.sql.*;
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


import java.sql.DriverManager;
import java.util.List;
public class signup {
    @FXML
    private TextField first;
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
            first.setOnAction(e -> userup.requestFocus());
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
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/XE";
    static final String DB_USER = "C##EXAMNEW";
    static final String DB_PASSWORD = "EXAM123";
    @FXML
    public void signup(ActionEvent event) throws Exception {
       // Main.getInstance().setRoot("/fx/fxmlFiles/signUp.fxml");

        if ( first == null || userup == null || passup == null ||email==null||phonenum==null || passin1==null ) {
            System.out.println("Error: One or more TextField components are not initialized!");
        }

        String firstName = first.getText();
        String username = userup.getText();
        String emailaddress = email.getText();
        String num = phonenum.getText();
        String password = passup.getText();
        String verifyPassword = passin1.getText();
        String role = Main.getInstance().getRole();
        System.out.println("first: " + first);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String checkUsernameSql = "SELECT COUNT(*) FROM students WHERE username = ? UNION SELECT COUNT(*) FROM admins WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkUsernameSql)) {
                stmt.setString(1, username);
                stmt.setString(2, username);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    signupstatus.setText("Signup failed: Username already exists.");
                    signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                    return;
                }
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

        if ( firstName.isBlank() || username.isBlank() || password.isBlank()) {
           signupstatus.setText("Signup failed: All fields are required.");
            return;
       }

        if (!password.equals(verifyPassword)) {
            signupstatus.setText("Signup failed: Passwords do not match.");
            return;
        }

            String checkPhoneSql = "SELECT COUNT(*) FROM students WHERE phone = ? UNION SELECT COUNT(*) FROM admins WHERE phone = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkPhoneSql)) {
                stmt.setString(1, num);
                stmt.setString(2, num);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    signupstatus.setText("Signup failed: Phone number already exists.");
                    signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                    return;
                }
            }

            String checkEmailSql = "SELECT COUNT(*) FROM students WHERE email = ? UNION SELECT COUNT(*) FROM admins WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkEmailSql)) {
                stmt.setString(1, emailaddress);
                stmt.setString(2, emailaddress);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    signupstatus.setText("Signup failed:email address already exists.");
                    signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                    return;
                }
            }

            signupstatus.setText("Signup successful!");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
        } catch (SQLException e) {
            signupstatus.setText("Database error: " + e.getMessage());
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            e.printStackTrace();
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            signupstatus.setText("Signup failed: Password must be at least 8 characters, including letters, numbers, and symbols");
            signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            return;
        }

        User newUser = new User(firstName,username,emailaddress,num,password,role);
        utility.saveUsers(newUser);
        System.out.println("Username: " + newUser.getUsername());
        System.out.println("Password: " + newUser.getPassword());
        System.out.println("Full Name: " + newUser.getFullName());
        System.out.println("Email: " + newUser.getEmailaddress());
        System.out.println("Phone: " + newUser.getNumber());

        signupstatus.setText("Signup successful!");
        signupstatus.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");

    }
}
