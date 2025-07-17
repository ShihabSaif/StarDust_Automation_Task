package org.example.testClasses;

import org.example.features.LoginFeature;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassForLogin {
    LoginFeature loginFeature;

    @BeforeTest
    public void init() throws Exception {
        loginFeature = new LoginFeature();
    }
    @Test(priority = 0)
    public void testBrowserLaunch() throws InterruptedException {
        loginFeature.LaunchBrowser();
    }

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        loginFeature.login();
    }
}
