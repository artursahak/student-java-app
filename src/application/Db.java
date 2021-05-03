package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Db{
    /*
    private static Connection connection = null;
    public static Connection connector() throws Exception{
        Class.forName("jdbc.mysql.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/courses","root","root");
        return connection;
    }
    */
    private static Configs config = new Configs();
    private static Connection dbConnection;
    public static Connection connector(){
        String connectionString = "jdbc:mysql://" + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName()
                + "?autoReconnect=true&useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            dbConnection = DriverManager.getConnection(connectionString,config.getDbUser(),config.getDbPass());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dbConnection;
    }

        // connection establishedc

    /*
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/courses","root","root");
       // return connection;
        System.out.println("Connected to database.");

    }

     */

}



