package adminUI.database;

public class tableModel {
    String category;
    int lotprice,pcf,vat;
    public tableModel(String category, int lotprice, int pcf, int vat){
        this.category = category;
        this.lotprice = lotprice;
        this.pcf = pcf;
        this.vat = vat;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public int getLotprice(){
        return this.lotprice;
    }

    public void setLotprice(int lotprice){
        this.lotprice = lotprice;
    }

    public int getPcf(){
        return this.pcf;
    }

    public void setPcf(int pcf){
        this.pcf = pcf;
    }

    public int getVat(){
        return this.vat;
    }

    public void setVat(int vat){
        this.vat = vat;
    }
}
