package adminUI.controllers;
import adminUI.database.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class plan implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView <tableModel> promoTable;
    @FXML
    private TableColumn <tableModel, String> category;
    @FXML
    private TableColumn <tableModel, Integer> lotprice;
    @FXML
    private TableColumn <tableModel, Integer> pcf;
    @FXML
    private TableColumn <tableModel,Integer> vat;

    @FXML
    private TableColumn<tableModel, String> editCol;

    @FXML
    private Label warningLabel;


    //toDatabase Variables
    databaseConnect on = new databaseConnect();
    Connection connect = on.getDatabaseLink();

    String query = null;

    Statement statement = null;
    
    PreparedStatement preStatement = null;

    ResultSet result = null;

    tableModel promo = null;


    //View Table
    ObservableList<tableModel> promolist = FXCollections.observableArrayList();





    @FXML
    public void toAddPlan(ActionEvent e){
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/adminUI/fxmls/addPlan.fxml"));
            Parent root = loader.load();

            addPlan call = loader.getController();
            call.setTitle("Add Plan");
            call.setDataTitle("Data has been Added!");

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setX(500);
            stage.setY(100);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void toEditPlan(ActionEvent e){
        try{
            Parent root = null;
            promo = promoTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/adminUI/fxmls/addPlan.fxml"));
            try {
                root = loader.load();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            addPlan call = loader.getController();
            call.setTitle("Edit Plan");
            call.setUpdate(true);
            call.setDataTitle("Data has been Edited!");
            call.setTextField(promo.getCategory(),promo.getLotprice(),promo.getPcf(),promo.getVat());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setX(500);
            stage.setY(100);
            stage.setScene(scene);
            stage.show();
        }catch (Exception notSelected){
            warningLabel.setText("Please Select a Row");
        }
    }

    @FXML
    public void toDeletePlan(){
        try {
            promo = promoTable.getSelectionModel().getSelectedItem();
            int warning = JOptionPane.showConfirmDialog(null,"Are you sure to delete "+promo.getCategory(),"Warning", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if(warning==0){
                try{
                    query = "DELETE FROM promoplan WHERE Category ='" +promo.getCategory()+"'";
                    connect = on.getDatabaseLink();
                    preStatement = connect.prepareStatement(query);
                    preStatement.execute();
                    refreshTable();
                }catch (Exception notSelected){
                    notSelected.printStackTrace();
                }
            }
        }catch (Exception e){
            warningLabel.setText("Please Select a Row");
        }


    }



    @FXML
    public void refreshTable(){
        try {
            promolist.clear();
            query = "SELECT * FROM promoplan";
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            while (result.next()){
                promolist.add(new tableModel(
                        result.getString("Category"),
                        result.getInt("LotPrice"),
                        result.getInt("PCF"),
                        result.getInt("VAT")
                ));
                promoTable.setItems(promolist);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        warningLabel.setText("");

    }

    @FXML
    public void loadTable(){
        refreshTable();
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        lotprice.setCellValueFactory(new PropertyValueFactory<>("lotprice"));
        pcf.setCellValueFactory(new PropertyValueFactory<>("pcf"));
        vat.setCellValueFactory(new PropertyValueFactory<>("vat"));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }



    @FXML
    public void setWelcomeLabel(String name){
        welcomeLabel.setText("Welcome "+name+"!");
    }
    @FXML
    public void backOnClick(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminUI/fxmls/front.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
