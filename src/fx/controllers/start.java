package fx.controllers;

import fx.Main;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class start {
    @FXML
    private ImageView stdd;
    @FXML
    private ImageView Admin;

    public void initialize() {
        String role = Main.getInstance().getRole();
        System.out.println("Current role: " + role);
        if (stdd != null) {
            Image stdimg = new Image(getClass().getResource("/img/graduated.png").toExternalForm());
            stdd.setImage(stdimg);
        }

        if (Admin != null) {
            Image Adminimg = new Image(getClass().getResource("/img/admin.png").toExternalForm());
            Admin.setImage(Adminimg);
        }
    }
    @FXML
    public void std(ActionEvent e) {
        try {
            Main.getInstance().setRole("student");
            Main.getInstance().setRoot("/fx/fxmlFiles/mainfx.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void admin(ActionEvent e) throws IOException {

        try {
            Main.getInstance().setRole("admin");
            Main.getInstance().setRoot("/fx/fxmlFiles/mainfx.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
