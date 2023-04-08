package userUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userUI.model.Plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import userUI.main.mainUser;

public class receipt {
    @FXML
    private Label amountOrDownLabel;

    @FXML
    private Label amountOrDownText;

    @FXML
    private Label birdateLabel;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private Label changeOrMonthlyLabel;

    @FXML
    private Label changeOrMonthlyText;

    @FXML
    private TextField firstField;

    @FXML
    private TextField lastField;

    @FXML
    private TextField middleField;

    @FXML
    private Label nameLabel;

    @FXML
    private Button orderButton;

    @FXML
    private Label paymenLabel;

    @FXML
    private Label planLabel;

    @FXML
    private Label priceLabel;
    @FXML
    private Label yearText;
    @FXML
    private Label yearLabel;

    @FXML
    private TextField suffixField;

    private Parent root;
    private Stage stage;
    private Scene scene;
    Plan plan;


    public void setPlan(Plan plan){
        this.plan = plan;
    }


    public void installmentLabels(){
        yearLabel.setText(plan.getYear()+"");
        priceLabel.setText(mainUser.Currency+plan.getContractPrice());
        amountOrDownText.setText("DownPay:");
        amountOrDownLabel.setText(mainUser.Currency+plan.getDownPayment());
        changeOrMonthlyText.setText("Monthly: ");
        changeOrMonthlyLabel.setText(mainUser.Currency+plan.getMonthlyPayment());
    }


    public void spotCashLabels(){
        yearText.setVisible(false);
        priceLabel.setText(mainUser.Currency+plan.getSpotPrice());
        amountOrDownText.setText("Amount:");
        amountOrDownLabel.setText(mainUser.Currency+plan.getDownPayment());
        changeOrMonthlyText.setText("Change: ");
        changeOrMonthlyLabel.setText(mainUser.Currency+plan.getChange());
    }


    public void toOrder(){
        nameLabel.setText(firstField.getText() + " " + middleField.getText() + " " + lastField.getText() + " " + suffixField.getText());
        LocalDate selectedDate = birthdayField.getValue();
        planLabel.setText(plan.getCategoryName());
        paymenLabel.setText(plan.getPayment());
        if(selectedDate!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedDate = selectedDate.format(formatter);
            birdateLabel.setText(formattedDate);
        }else {
            birdateLabel.setText("Field is Empty");
        }
        if(plan.getPayment().equals("Spot Cash")){
            spotCashLabels();
        }else {
            installmentLabels();
        }
    }



    public void toBack(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/payment.fxml"));
            root = loader.load();
            scene = new Scene(root);

            payment setId = loader.getController();
            setId.setPlan(plan);

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




    public void toClose(ActionEvent e){
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void toOrderAgain(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userUI/fxmls/market.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
