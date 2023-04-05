package adminUI.database;

import java.sql.*;
public class databaseConnect {

    public Connection databaseLink;

    public Connection getDatabaseLink(){
        String databaseName = "adminaccess";
        String databaseUser = "root";
        String databasePassword ="0327";
        String url = "jdbc:mysql://localhost/"+databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }


}
