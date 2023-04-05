package userUI.model;


import adminUI.database.databaseConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Plan {


    String color;

    String imgscr;

    databaseConnect connect = new databaseConnect();
    Connection connectData = connect.getDatabaseLink();
    String query;

    int idPromoPlan;
    public String getImgscr() {
        return imgscr;
    }

    public void setImgscr(String imgscr) {
        this.imgscr = imgscr;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }










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

}
