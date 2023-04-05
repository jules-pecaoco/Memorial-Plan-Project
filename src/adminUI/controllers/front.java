package adminUI.controllers;

import adminUI.database.databaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class front {

    private Parent root;

    private Stage stage;

    private Scene scene;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label messageLabel;


    databaseConnect connect = new databaseConnect();
    Connection connectData = connect.getDatabaseLink();


    //when the user clicks on the close button of the login form.
    public void toHome(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/home.fxml"));
            root = loader.load();

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    @FXML
    // verifies the user's login credentials by querying a adminUI.database table called adminaccounts
    public void checkInput(ActionEvent e){
        if(!usernameInput.getText().isBlank()&&!passwordInput.getText().isBlank()){
            verifyLogin(e);
        }else {
            messageLabel.setText("Please Input Field");
        }
        messageLabel.setAlignment(Pos.CENTER);
    }



    @FXML
    //Verifies the input if it exists in adminUI.database.databaseConnect;
    public void verifyLogin(ActionEvent e){
        String query ="SELECT count(1) FROM adminaccounts WHERE userName = '"+usernameInput.getText()+"' AND password = '"+passwordInput.getText()+"'";
        try {
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            if (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminUI/fxmls/plan.fxml"));
                    root = loader.load();

                    plan call = loader.getController();
                    call.setWelcomeLabel(getName());

                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }else {
                    messageLabel.setText("You are not Admin");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public String getName(){
        String name="";
        String query ="SELECT firstName FROM adminaccounts WHERE userName = '"+usernameInput.getText()+"' AND password = '"+passwordInput.getText()+"'";
        try {
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            if (queryResult.next()) {
                name = queryResult.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return name;
    }




}
