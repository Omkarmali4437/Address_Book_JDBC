package com.myPackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
        Assert.assertEquals(6,addressBookList.size());
    }

    @Test
    public void update_table_should_return_true() throws SQLException {
        String state="Bihar";
        int id=3;
        addressBook.updateData(state,id);
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(4,addressBookList.size());
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
        Assert.assertEquals(3,addressBookList.size());
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

    @Test
    public void insert_into_address_book() throws SQLException {
        String firstname="Iron";
        String lastname="Man";
        String address="Tampa";
        String city="Malibu";
        String state="California";
        int zip=908818;
        int phonenumber=266155211;
        String email="iron@avenger.in";
        String entry_date="2020-11-01";

        addressBook.insertNewContact(firstname,lastname,address,city,state,zip,phonenumber,email,entry_date);
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(4,addressBookList.size());
    }

    @Test
    public void insert_using_threads() throws SQLException {
        List<AddressBookData> addressBookDataList=new ArrayList<>();

        addressBookDataList.add(new AddressBookData("Captain","America","Bay","Manhattan","NewYork",1999818,9918800,"captain@gmail.com", Date.valueOf("2019-05-19")));
        addressBookDataList.add(new AddressBookData("Mandar","Raote","BackBay","Mumbai","Maharastra",400009,88392911,"mandar@gmail.com", Date.valueOf("2020-06-21")));

        Instant start=Instant.now();
        addressBook.addEmployeeToPayrollWithThreads(addressBookDataList);
        Instant end=Instant.now();

        System.out.println("Duration of non thread process is: "+ Duration.between(start,end));

        List<AddressBookData> bookData=addressBook.readData();
        Assert.assertEquals(6,bookData.size());

    }
}
