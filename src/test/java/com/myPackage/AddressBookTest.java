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

    @Test
    public void return_values_for_a_particular_date_range() throws SQLException {
        String date="2019-01-01";

        List<AddressBookData> addressBookList=addressBook.returnValuesForApaticularDateRange(date);
        Assert.assertEquals(2,addressBookList.size());
    }

    @Test
    public void count_of_contacts_in_a_city() throws SQLException {
        String city="Mumbai";
        String result=addressBook.countofContactbyCity(city);
        Assert.assertEquals("1",result);
    }

    @Test
    public void count_of_contacts_in_a_state() throws SQLException {
        String state="Maharastra";
        String result=addressBook.countByContactbyState(state);
        Assert.assertEquals("2",result);
    }
}
