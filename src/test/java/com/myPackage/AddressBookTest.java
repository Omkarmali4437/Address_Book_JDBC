package com.myPackage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    @Test
    public void given_select_statement_should_return_list(){
        AddressBook addressBook=new AddressBook();
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(3,addressBookList.size());
    }

    @Test
    public void update_table_should_return_true(){
        AddressBook addressBook=new AddressBook();
        addressBook.updateData();
        List<AddressBookData> employeePayrollDataList=addressBook.readData();
        Assert.assertEquals(3,employeePayrollDataList.size());
    }
}
