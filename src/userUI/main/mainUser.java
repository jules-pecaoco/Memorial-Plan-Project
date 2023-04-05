package userUI.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainUser extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    public static final String Currency = "₱";


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String css = getClass().getResource("/userUI/main/style.css").toExternalForm();
            scene.getStylesheets().add(css);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
