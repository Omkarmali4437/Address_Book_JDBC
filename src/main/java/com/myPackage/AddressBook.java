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

    public List<AddressBookData> readData() throws SQLException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from address_book; ");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                AddressBookData addressBook=new AddressBookData(resultSet.getInt(1),resultSet.getString(2)
                        ,resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),
                        resultSet.getString(9),resultSet.getDate(10));

                addressBookList.add(addressBook);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }

    public void updateData(String state,int id) throws SQLException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("Update address_book set state=? where id=? ; ");
            preparedStatement.setString(1,state);
            preparedStatement.setInt(2,3);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public void updateContactDetails(String lastname,String address,String city,String state,int zip,int phonenumber,String email,String firstname) throws SQLException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("Update address_book set lastname=?,address=?,city=?,state=?,zip=?,phonenumber=?,email=? where firstname=? ; ");
            preparedStatement.setString(1,lastname);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,city);
            preparedStatement.setString(4,state);
            preparedStatement.setInt(5,zip);
            preparedStatement.setInt(6,phonenumber);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,firstname);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public List<AddressBookData> returnValuesForApaticularDateRange(String date) throws SQLException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from address_book where entry_date>=? ; ");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                AddressBookData addressBook=new AddressBookData(resultSet.getInt(1),resultSet.getString(2)
                        ,resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),
                        resultSet.getString(9),resultSet.getDate(10));

                addressBookList.add(addressBook);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }

    public String countofContactbyCity(String city) throws SQLException {
        Connection connection=this.getConnection();
        String result=null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("select count(*) from address_book where city=? ; ");
            preparedStatement.setString(1,city);
            ResultSet resultSet=preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                result=resultSet.getString(1);
                System.out.println(resultSet.getString(1));
            }
            return result;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
        return result;
    }

    public String countByContactbyState(String state) throws SQLException {
        Connection connection=this.getConnection();
        String result=null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("select count(*) from address_book where state=? ; ");
            preparedStatement.setString(1,state);
            ResultSet resultSet=preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                result=resultSet.getString(1);
                System.out.println(resultSet.getString(1));
            }
            return result;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
        return result;
    }

    public void insertNewContact(String firstname,String lastname,String address,String city,String state,int zip,int phonenumber,String email,String entry_date) throws SQLException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into address_book(firstname,lastname," +
                    "address,city,state,zip,phonenumber,email,entry_date) values(?,?,?,?,?,?,?,?,?); ");
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,city);
            preparedStatement.setString(5,state);
            preparedStatement.setInt(6,zip);
            preparedStatement.setInt(7,phonenumber);
            preparedStatement.setString(8,email);
            preparedStatement.setDate(9,Date.valueOf(entry_date));
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }
}
