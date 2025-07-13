package fx.controllers;


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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    static String role=Main.getInstance().getRole();
    public static User authenticateUser(String username, String password) {
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String dbUser = "C##EXAMNEW";
        String dbPassword = "EXAM123";
        String sql="";
        try {
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Connected to the database");
            if (role.equals("student")) {
                sql = "SELECT 'student' AS role, username, USER_PASSWORD AS password, full_name, email ,phone FROM students WHERE username = ?";
            } else if (role.equals("admin")) {
                sql = "SELECT 'admin' AS role, username, USER_PASSWORD AS password, full_name, email,phone FROM admins WHERE username = ?";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedpass = rs.getString("password");

                if (verifyPassword(password, storedpass)) {
                    return new User(
                            rs.getString("full_name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            storedpass
                    );

                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    private static boolean verifyPassword(String inputPassword, String storedpass) {
        return inputPassword.equals(storedpass);
    }

    @FXML
    public void loginbtn(ActionEvent event) throws Exception {
        String username = user.getText();
        String password = pass.getText();

        User foundUser = authenticateUser(username, password);
        System.out.println("Full User Info: " + foundUser);

        if (foundUser != null) {
            System.out.println("First Name: " + foundUser.getFullName());
            System.out.println("Email Address: " + foundUser.getEmailaddress());

            SessionManager.getInstance().setCurrentUser(foundUser);
            if(Main.getInstance().getRole().equals("student")){
                Main.getInstance().setRoot("/fx/fxmlFiles/home.fxml");
            }else if(Main.getInstance().getRole().equals("admin")){
                Main.getInstance().setRoot("/fx/fxmlFiles/admin.fxml");
            }
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
