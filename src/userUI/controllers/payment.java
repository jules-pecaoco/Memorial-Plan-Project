package userUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userUI.model.Plan;

import userUI.main.mainUser;



public class payment {

    @FXML
    private Button calculateButton;

    @FXML
    private Label contracOrSellPrice;

    @FXML
    private Label contractOrSellPriceLabel;

    @FXML
    private TextField downOrAmountField;

    @FXML
    private Label downOrAmountTitle;

    @FXML
    private AnchorPane fourBox;

    @FXML
    private Label interestOrOffTitle;

    @FXML
    private Label interestorOffLabel;

    @FXML
    private Label monthlyOrChangeLabel;

    @FXML
    private Label monthlyOrChangeTitle;

    @FXML
    private Label nameFour;

    @FXML
    private Label nameOne;

    @FXML
    private Label nameThree;

    @FXML
    private Label nameTwo;

    @FXML
    private AnchorPane oneBox;

    @FXML
    private VBox paymentCard;

    @FXML
    private Label planNameLabel1;

    @FXML
    private Label priceFour;


    @FXML
    private Button selectButton;

    @FXML
    private AnchorPane threeBox;

    @FXML
    private AnchorPane twoBox;

    @FXML
    private TextField yearField;

    @FXML
    private Label yearTitle;

    @FXML
    private Label selectedPlan;

    @FXML
    private Label selectedPrice;

    @FXML
    private Label warningLabel;

    @FXML
    private Label paymentTitle;

    @FXML
    private HBox yearFieldBox;


    @FXML
    private HBox yearTitleBox;


    Parent root;
    Stage stage;
    Scene scene;

    Plan plan;



    public void setPlan(Plan plan){
        this.plan = plan;
        selectedPlan.setText(plan.getCategoryName());
        selectedPrice.setText(mainUser.Currency+plan.getSellingPrice());
        plan.setSpotDiscount(15);
        toSpotCashCard();
        toSpotUpdate();

    }



    public void setPaymentCard(MouseEvent e){
        if(e.getSource()==oneBox){
            plan.setSpotDiscount(15);
            toSpotCashCard();
            toSpotUpdate();

        }else if(e.getSource()==twoBox){
            plan.setSpotDiscount(10);
            toSpotCashCard();
            toSpotUpdate();

        }else if(e.getSource()==threeBox){
            plan.setSpotDiscount(5);
            toSpotCashCard();
            toSpotUpdate();

        }else {
            try{
                toInstallmentCard();
            }catch (Exception ex){
                warningLabel.setText("Already in Installment");;
            }

        }
    }

    public void toSpotUpdate(){
        warningLabel.setText(null);
        interestorOffLabel.setText(plan.getSpotDiscount()+"%");
        contractOrSellPriceLabel.setText(mainUser.Currency+plan.getSpotPrice());
    }

    public void toSpotCashCard(){
        plan.setPayment("Spot Cash");
        downOrAmountField.setText(null);
        contracOrSellPrice.setText("Selling Price:");
        interestOrOffTitle.setText("Discount:");
        monthlyOrChangeTitle.setText("Change:");
        paymentCard.getChildren().remove(yearFieldBox);
        paymentCard.getChildren().remove(yearTitleBox);
        downOrAmountTitle.setText("Amount:");
        paymentTitle.setText("Spot Cash");

    }

    public void toInstallmentCard(){
        plan.setPayment("Installment");
        downOrAmountField.setText(null);
        interestorOffLabel.setText(null);
        contractOrSellPriceLabel.setText(null);
        paymentCard.getChildren().add(4,yearTitleBox);
        paymentCard.getChildren().add(5,yearFieldBox);
        downOrAmountTitle.setText("Down Payment:");
        paymentTitle.setText("Installment");
        contracOrSellPrice.setText("Contract Price:");
        interestOrOffTitle.setText("Interest:");
        monthlyOrChangeTitle.setText("Monthly:");
        yearTitle.setVisible(true);
        yearField.setVisible(true);
        calculateButton.setVisible(true);

    }


    public void toCalculate() {
        try{
            if(downOrAmountField.getText().isBlank()){
                warningLabel.setText("Amount is Blank");
            }else {
                int amount = Integer.parseInt(downOrAmountField.getText());
                int dis = plan.getSpotPrice();
                int sell = plan.getSellingPrice();
                if(paymentTitle.getText().equals("Spot Cash")){
                    if(amount<dis){
                        warningLabel.setText("Insufficient Amount");
                    }else {
                        warningLabel.setText(null);
                        int change = amount - dis;
                        plan.setChange(change);
                        plan.setDownPayment(amount);
                        monthlyOrChangeLabel.setText(mainUser.Currency+change);
                    }

                    //Installment
                }else {
                    if(yearField.getText().isBlank()){
                        warningLabel.setText("Year is Blank");
                    }else {
                        if (amount > sell) {
                            warningLabel.setText("Amount is greater than Selling Price");
                        } else {
                            int year = Integer.parseInt(yearField.getText());
                            warningLabel.setText(null);
                            if (year > 40) {
                                warningLabel.setText("Year cannot exceeded 40!");
                            } else {
                                warningLabel.setText(null);
                                plan.setYear(year);
                                plan.setDownPayment(amount);
                                contractOrSellPriceLabel.setText(mainUser.Currency + plan.getContractPrice());
                                interestorOffLabel.setText(plan.getInterest() + "%");
                                monthlyOrChangeLabel.setText(mainUser.Currency + plan.getMonthlyPayment());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            warningLabel.setText("Field is Empty");
        }

    }




    public void toMarket(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/market.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean toCheckPayment(){
        try{
            if(paymentTitle.getText().equals("Spot Cash")){
                if(downOrAmountField.getText().isBlank()||monthlyOrChangeLabel.getText().isBlank()){
                    return false;
                }else {
                    return true;
                }
            }else {
                if(downOrAmountField.getText().isBlank()&&yearField.getText().isBlank()&&monthlyOrChangeLabel.getText().isBlank()){
                    return false;
                }else {
                    return true;
                }
            }
        }catch (Exception e){
            warningLabel.setText("A Field is Blank");
        }
        return false;
    }
    public void toReceipt(ActionEvent e){
        if(!toCheckPayment()){
            warningLabel.setText("Please Input Field");
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/receipt.fxml"));
                root = loader.load();

                receipt setReceipt = loader.getController();
                setReceipt.setPlan(plan);

                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }



}
