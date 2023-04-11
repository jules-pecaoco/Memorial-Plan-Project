package userUI.model;


import adminUI.database.databaseConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Plan {



    databaseConnect connect = new databaseConnect();
    Connection connectData = connect.getDatabaseLink();
    String query;

    int idPromoPlan;

    public void setID(int idPromoPlan){
        this.idPromoPlan=idPromoPlan;
    }

    public int getID(){
        return idPromoPlan;
    }

    //Getting Values From Database;

    public String getCategoryName() {
        String categoryName = "";
        query =  "SELECT Category FROM promoplan WHERE idpromoplan = '"+idPromoPlan+"'";
        try{
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                categoryName = queryResult.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryName;
    }

    public int getVAT(){
        int vat = 0;
        query =  "SELECT VAT FROM promoplan WHERE idpromoplan = '"+idPromoPlan+"'";
        try{
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                vat = queryResult.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vat;
    }

    public int getPCF(){
        int vat = 0;
        query =  "SELECT PCF FROM promoplan WHERE idpromoplan = '"+idPromoPlan+"'";
        try{
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                vat = queryResult.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vat;
    }
    public int getLotPrice(){
        int vat = 0;
        query =  "SELECT LotPrice FROM promoplan WHERE idpromoplan = '"+idPromoPlan+"'";
        try{
            Statement statement = connectData.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()){
                vat = queryResult.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vat;
    }

    public int getVATPrice(){
        double vat = getVAT()/100.0;
        return (int)(getLotPrice()*vat);
    }


    public int getSellingPrice(){
        return getVATPrice()+getPCF()+getLotPrice();
    }



    int year;
    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return year;
    }

    int downPayment;

    public void setDownPayment(int downPayment){
        this.downPayment = downPayment;
    }

    public int getDownPayment(){
        return downPayment;
    }

    public int getInterest(){
        int interest = 0;
        if(getYear()>5){
            interest = 60;
        }
        switch (getYear()){
            case 1 -> interest = 15;
            case 2 -> interest = 20;
            case 3 -> interest = 30;
            case 4 -> interest = 40;
            case 5 -> interest = 50;
        }
        return interest;
    }



    public int getBalancePrice(){
        return getSellingPrice() - getDownPayment();
    }
    public int getCalculatedInterest(){
        double interest = getInterest()/100.0;
        return (int) Math.round(getBalancePrice()*interest);
    }

    public int getContractPrice(){
        return getSellingPrice()+getCalculatedInterest();
    }

    public int getMonthlyPayment(){
        return (getBalancePrice()+getCalculatedInterest())/(getYear()*12);
    }


    //Spot Cash
    int spotDiscount;
    public void setSpotDiscount(int spotDiscount){
        this.spotDiscount = spotDiscount;
    }

    public int getSpotDiscount(){
        return spotDiscount;
    }

    public int getSpotPrice(){
        double discount = getSpotDiscount()/100.0;
        int price = (int) (getSellingPrice() * discount);
        int spotPrice = getSellingPrice() -price;

        return spotPrice;
    }



    String payment;
    public void setPayment(String payment){
        this.payment = payment;
    }

    public String getPayment(){
        return payment;
    }



    int change;

    public void setChange(int change) {
        this.change = change;
    }

    public int getChange() {
        return change;
    }

}
