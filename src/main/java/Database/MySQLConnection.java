package Database;

import Model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/voicesimulator";

    //  Database credentials
    private static final String USER = "aquanuestra";
    private static final String PASS = "aquanuestra";

    private Connection conn = null;

    public MySQLConnection(){
        connectWithDB();
    }

    private void connectWithDB(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runNoQueryStatement(String sql, String[] data){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int fieldNumber = 1;
            for(String field : data){
                preparedStatement.setString(fieldNumber++, field);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public void deleteWithId(String sql, Integer id){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void runSQL(String sql) throws SQLException{
        System.out.println(sql);
        Statement stmt;
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public List<String> getMessagesList() throws SQLException {
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "SELECT content FROM Message";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> messages = new ArrayList<String>();
        while(rs.next()){
            messages.add(rs.getString("content"));
        }

        rs.close();
        stmt.close();
        return messages;
    }

    public ResultSet runQueryStatement(String sql){
        try{
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res;
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }
}
