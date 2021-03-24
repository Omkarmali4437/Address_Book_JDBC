package com.myPackage;

import java.sql.*;
import java.util.Enumeration;

public class AddressBook {
    public static void main(String[] args) {
        String jdbcurl="jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String usernam="root";
        String  password="Madnimisha@4437";
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        }catch (ClassNotFoundException e){
            throw new IllegalStateException("Class not found",e);
        }
        listdrivers();
        try{
            System.out.println("Connecting to database: "+jdbcurl);
            con=DriverManager.getConnection(jdbcurl,usernam,password);
            System.out.println("Connection successfull: "+con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void listdrivers(){
        Enumeration<Driver> driverlist=DriverManager.getDrivers();
        while(driverlist.hasMoreElements()){
            Driver driverClass=(Driver) driverlist.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}
