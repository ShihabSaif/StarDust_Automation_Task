package org.example.testClasses;

import org.example.features.AddUserFeature;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassForAddUser {
    AddUserFeature addUser;

    @BeforeTest
    public void init() throws Exception {
        addUser = new AddUserFeature();
    }

    @Test(priority = 2)
    public void testUserMenu() throws InterruptedException {
        addUser.clickUsers();
    }

    @Test(priority = 3)
    public void testAddUserMenu() throws InterruptedException {
        addUser.addUserButton();
    }

    @Test(priority = 4)
    public void testNewUserCreation() throws InterruptedException {
        addUser.newUserCreation();
    }

}
