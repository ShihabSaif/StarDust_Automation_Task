package org.example.features;

import org.example.browserOpen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginFeature extends BasePage {
    public LoginFeature() throws Exception, IOException, InterruptedException {
        PageFactory.initElements(driver, this);
    }

    public void LaunchBrowser() throws InterruptedException {
        driver.navigate().to("http://103.117.192.70:8080/");
        Thread.sleep(2000);
    }

    public void login()
    {
        driver.findElement(By.id("username")).sendKeys("qa_user3");
        driver.findElement(By.id("password")).sendKeys("Asdf1234#");
        driver.findElement(By.id("login")).click();
    }
}
