package userUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class front{

    @FXML
    public void toMarket(MouseEvent e){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/userUI/fxmls/market.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void toAdmin(ActionEvent e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/adminUI/fxmls/front.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/adminUI/main/style.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void toClose(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }




}
