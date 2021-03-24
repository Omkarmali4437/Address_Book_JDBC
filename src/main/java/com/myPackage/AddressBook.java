package com.myPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddressBook {
    private List<AddressBook> addressBookList;

    private Connection getConnection() throws SQLException {
        String jdbcurl="jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String usernam="root";
        String  password="Madnimisha@4437";
        Connection con;
        System.out.println("Connecting to database: "+jdbcurl);
        con= DriverManager.getConnection(jdbcurl,usernam,password);
        System.out.println("Connection successfull: "+con);
        return con;
    }

    public List<AddressBookData> readData() {
        String sql_query="Select * from address_book; ";
        List<AddressBookData> addressBookList=new ArrayList<>();
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql_query);
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String firstname=resultSet.getString(2);
                String lastname=resultSet.getString(3);
                String address=resultSet.getString(4);
                String city=resultSet.getString(5);
                String state=resultSet.getString(6);
                int zip=resultSet.getInt(7);
                int phonenumber=resultSet.getInt(8);
                String email=resultSet.getString(9);
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("Id: "+id);
                System.out.println("FirstName: "+firstname);
                System.out.println("LastName: "+lastname);
                System.out.println("Adress: "+address);
                System.out.println("City: "+city);
                System.out.println("State: "+state);
                System.out.println("Zip: "+zip);
                System.out.println("PhoneNumber: "+phonenumber);
                System.out.println("Email: "+email);

                AddressBookData addressBook=new AddressBookData(resultSet.getInt(1),resultSet.getString(2)
                        ,resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),
                        resultSet.getString(9));

                addressBookList.add(addressBook);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressBookList;
    }

    public void updateData(){
        String sql_query="Update address_book set state='Bihar' where id='3'; ";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            long resultSet=statement.executeLargeUpdate(sql_query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
