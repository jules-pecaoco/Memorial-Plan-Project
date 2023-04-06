package userUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userUI.model.Plan;

import java.net.URL;
import java.util.ResourceBundle;
import userUI.main.*;

import javax.swing.*;

public class market implements Initializable{



    @FXML
    private Label nameFive;

    @FXML
    private Label nameFour;

    @FXML
    private Label nameThree;

    @FXML
    private VBox choosenPlanCard;

    @FXML
    private Label nameOne;

    @FXML
    private Label nameTwo;

    @FXML
    private Label pcfLabel;

    @FXML
    private ImageView planImage;

    @FXML
    private Label planLotPriceLabel;

    @FXML
    private Label planNameLabel;

    @FXML
    private Label planSellingPriceLabel;

    @FXML
    private Label priceFive;

    @FXML
    private Label priceFour;

    @FXML
    private Label priceOne;

    @FXML
    private Label priceThree;

    @FXML
    private Label priceTwo;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button selectButton;

    @FXML
    private Label vatLabel;



    @FXML
    private AnchorPane threeBox;

    @FXML
    private AnchorPane twoBox;

    @FXML
    private AnchorPane fiveBox;
    @FXML
    private AnchorPane fourBox;

    @FXML
    private AnchorPane oneBox;

    @FXML
    private Label warningMessage;

    Parent root;
    Stage stage;
    Scene scene;

    Plan plan1 = new Plan();
    Plan plan2 = new Plan();
    Plan plan3 = new Plan();
    Plan plan4 = new Plan();
    Plan plan5 = new Plan();

    Plan selectedPlan;
    public void setChosenPlan(Plan plan){
        planNameLabel.setText(plan.getCategoryName());
        planLotPriceLabel.setText(mainUser.Currency + plan.getLotPrice());
        pcfLabel.setText(String.valueOf(plan.getPCF()));
        vatLabel.setText(plan.getVAT() +"%");
        planSellingPriceLabel.setText(mainUser.Currency + plan.getSellingPrice());
    }


    public void getButtonChoose(MouseEvent e){
        warningMessage.setText(null);
        if(e.getSource()==oneBox){
            setChosenPlan(plan1);
            selectedPlan = plan1;
        } else if (e.getSource()==twoBox) {
            setChosenPlan(plan2);
            selectedPlan = plan2;
        } else if (e.getSource()==threeBox) {
            setChosenPlan(plan3);
            selectedPlan = plan3;
        } else if (e.getSource()==fourBox) {
            setChosenPlan(plan4);
            selectedPlan = plan4;
        }else {
            setChosenPlan(plan5);
            selectedPlan = plan5;
        }

    }



    public  void setLabels(){
        plan1.setID(1);
        nameOne.setText(plan1.getCategoryName());
        priceOne.setText(mainUser.Currency+ plan1.getSellingPrice());

        plan2.setID(2);
        nameTwo.setText(plan2.getCategoryName());
        priceTwo.setText(mainUser.Currency+ plan2.getSellingPrice());

        plan3.setID(3);
        nameThree.setText(plan3.getCategoryName());
        priceThree.setText(mainUser.Currency+ plan3.getSellingPrice());

        plan4.setID(4);
        nameFour.setText(plan4.getCategoryName());
        priceFour.setText(mainUser.Currency+ plan4.getSellingPrice());


        plan5.setID(5);
        nameFive.setText(plan5.getCategoryName());
        priceFive.setText(mainUser.Currency+ plan5.getSellingPrice());

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();
    }

    public void toFront(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("/userUI/fxmls/home.fxml"));
            scene = new Scene(root);

            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void toPayment(ActionEvent e){
        if(selectedPlan==null){
            warningMessage.setText("Select a Plan to Proceed");
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/payment.fxml"));
                root = loader.load();

                payment setID  = loader.getController();
                setID.setID(selectedPlan);

                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }



}
