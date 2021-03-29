package com.myPackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressBookTest {

    AddressBook addressBook;
    @Before
    public void setup(){
        addressBook=new AddressBook();
    }

    @Test
    public void given_select_statement_should_return_list() throws SQLException {
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(3,addressBookList.size());
    }

    @Test
    public void update_table_should_return_true() throws SQLException {
        String state="Bihar";
        int id=3;
        addressBook.updateData(state,id);
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(3,addressBookList.size());
    }

    @Test
    public void update_full_contact_details() throws SQLException {
        String lastname="Dev";
        String address="Tampa";
        String city="Bay";
        String state="UP";
        int zip=400091;
        int phonenumber=781154415;
        String email="adwait@yahoo.in";
        String firstname="Adwait";

        addressBook.updateContactDetails(lastname,address,city,state,zip,phonenumber,email,firstname);
    }
}
