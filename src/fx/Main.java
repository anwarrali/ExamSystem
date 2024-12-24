package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    private StackPane rootPane;
    private Stage primaryStage;
    private static Main instance;
    private String role;
    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.primaryStage = primaryStage;

        rootPane = new StackPane();

        Scene scene ;

        try {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("/fx/fxmlFiles/start.fxml"));
            rootPane.getChildren().add(mainScreen);
        } catch (Exception e) {
            System.err.println("Error loading FXML file (start.fxml): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        scene = new Scene(rootPane);
        primaryStage.setTitle("AchievePlus App");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icons1.png")));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static Main getInstance() {
        return instance;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setRoot(String fxmlFilePath) {
        try {

            Parent newRoot = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newRoot);
        } catch (Exception e) {
            System.err.println("Error loading FXML file (" + fxmlFilePath + "): " + e.getMessage());
            e.printStackTrace();
        }
    }

public static void main(String[] args) {
    launch(args);
}
            }
