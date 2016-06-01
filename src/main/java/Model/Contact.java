package Model;

import Database.ContactMapper;
import Database.MySQLConnection;

import java.sql.SQLException;

public class Contact {
    private String first_name;
    private String last_name;
    private String number;
    private Integer id;

    public static void deleteContact(Integer id){
        try{
            MySQLConnection mySQLConnection = new MySQLConnection();
            mySQLConnection.runSQL("DELETE FROM Contact WHERE id = "+id+";");
            mySQLConnection.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contact(String first_name, String last_name, String number, Integer id){
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
        this.id = ((id != null) ? id : 0);
    }
    public String getName(){
        return first_name + " " + last_name;
    }

    public String getNumber(){
        return number;
    }

    public String[] getInsertDataArray(){
        String[] insertArray = {first_name, last_name, number};
        return insertArray;
    }

    public String[] getUpdateDataArray(){
        String[] updateArray = {first_name, last_name, number, id.toString()};
        return updateArray;
    }

    public Integer getDeleteData(){
        return id;
    }

    public String getNumberAndName(){
        return id + ") " + getName() + " " + number;
    }

    public void setFirstName(String name){
        this.first_name = name;
    }

    public void setLastName(String name){
        this.last_name = name;
    }

    public void saveToDB(){ ContactMapper.saveToDB(this); }

    public void updateInDB(){ ContactMapper.updateInDB(this); }

//    public void deleteFromDB(){ ContactMapper.deleteFromDB(this); }

    public void save(){
        try{
            MySQLConnection mySQLConnection = new MySQLConnection();
            mySQLConnection.runSQL("INSERT INTO Contact (first_name, last_name, phone_number) VALUES ('"+this.first_name+"', '"+this.last_name+"', '"+this.number+"');");
            mySQLConnection.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNumber(String number){
        this.number = number;
    }
}
