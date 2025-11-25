import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.*;

 class CreateDB {
    public static void main(String[] args){
        try{
            String url="jdbc:mysql://localhost:3306/";
            String databaseName="dbC";
            String user = "root";
            String password ="Charu38@" ;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,password);
            System.out.println("Connection established Successfully");
            String query="CREATE DATABASE " +databaseName;
            Statement st=con.createStatement();
            st.executeUpdate(query);
            st.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
