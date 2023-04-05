package adminUI.controllers;



import adminUI.database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;



public class addPlan{


    @FXML
    private TextField categoryField;

    @FXML
    private TextField lotPriceField;
    @FXML
    private TextField pcfField;
    @FXML
    private TextField vatField;

    @FXML
    private Label messageLabel;


    @FXML
    private Label titleLabel;

    private boolean update=false;

    String categoryPlan;
    String query = null;

    databaseConnect dataConnect = new databaseConnect();
    Connection connect = dataConnect.getDatabaseLink();
    ;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    databaseConnect DBconnect = new databaseConnect();

    private String dataTitle;




    @FXML
    public void setTitle(String name){
        titleLabel.setText(name);
    }

    public void setDataTitle(String data){
        this.dataTitle = data;
    }





    @FXML
    private void saveInput(ActionEvent event) {
        if (categoryField.getText().isEmpty() || lotPriceField.getText().isEmpty()
                || pcfField.getText().isEmpty() || vatField.getText().isEmpty()) {
            messageLabel.setText("Please Input Data");
        } else {
            getQuery();
            insert();
            clean();
        }
    }



    @FXML
    private void clean() {
        categoryField.setText(null);
        lotPriceField.setText(null);
        pcfField.setText(null);
        vatField.setText(null);
    }

    public void getQuery(){
        if(update == false) {
            query = "INSERT INTO `promoplan`( `Category`, `LotPrice`, `PCF`, `VAT`) VALUES (?,?,?,?)";
        }else{
            query = "UPDATE `promoplan` SET "
                    + "`Category`=?,"
                    + "`LotPrice`=?,"
                    + "`PCF`=?,"
                    + "`VAT`= ? WHERE Category = '"+categoryPlan+"'";
        }
    }



    private void insert() {
        String category = categoryField.getText();
        int lotprice = Integer.parseInt(lotPriceField.getText());
        int pcf = Integer.parseInt(pcfField.getText());
        int vat = Integer.parseInt(vatField.getText());
        try {
            preparedStatement  = connect.prepareStatement(query);
            preparedStatement.setString(1,category);
            preparedStatement.setInt(2,lotprice);
            preparedStatement.setInt(3,pcf);
            preparedStatement.setInt(4,vat);

            int k = preparedStatement.executeUpdate();
            if(k==1){
                messageLabel.setText(dataTitle);
                messageLabel.setTextFill(Color.BLACK);
            }else {
                messageLabel.setText("Error Occurred");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void backOnClick(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }



    //Later Use
    public void setTextField(String category, int lotPrice, int pcf, int vat) {
        categoryPlan = category;
        categoryField.setText(categoryPlan);
        lotPriceField.setText(String.valueOf(lotPrice));
        pcfField.setText(String.valueOf(pcf));
        vatField.setText(String.valueOf(vat));
    }


    public void setUpdate(boolean b){
        this.update = b;
    }


}
