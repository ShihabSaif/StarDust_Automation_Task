package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.DataRead;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddUserFeature extends BasePage {
    public AddUserFeature() throws Exception {
    }
    public void clickUsers() throws InterruptedException {
        Thread.sleep(8000);
        driver.findElement(By.cssSelector("a[href='/user/list']")).click();
    }

    public void addUserButton() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.btn.btn-primary.btn-simple")).click();
    }

    public void newUserCreation() throws InterruptedException {
        Thread.sleep(5000);

        List<String[]> userData = DataRead.readUserDataFromCSV();

        for (String[] newUserData : userData) {

//            System.out.println(driver.getPageSource());
            // --- 1. Select Email field ---
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(newUserData[0]);

            // --- 2. Select username field ---
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(newUserData[1]);

            // --- 3. Select Password field ---
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(newUserData[2]);


            // --- 3. Select Confirm Password field ---
            driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys(newUserData[3]);

            // --- 4. Select first name field ---
            driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(newUserData[4]);

            // --- 5. Select last name field ---
            WebElement element = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.sendKeys(newUserData[5]);


            // --- 6. Select department dropdown
            WebElement departmentDropdown = driver.findElement(
                    By.xpath("//select[option[contains(text(), 'Select Department')]]")
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departmentDropdown);
            Select select = new Select(departmentDropdown);
            select.selectByValue(newUserData[6]);

            // --- 7. Selecting role dropdown
            WebElement dropdown = driver.findElement(By.cssSelector("div.multiselect"));
            dropdown.click();

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departmentDropdown);

            // Waiting for dropdown options to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.multiselect__content")));

            // Clicking on desired option
            WebElement option = driver.findElement(By.xpath("//li[@role='option']//span[text()='" + newUserData[7] + "']"));
            option.click();

            // Send ESC to close dropdown
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();
            Thread.sleep(2000);

            // 8 . Address field
            WebElement addressTextarea = driver.findElement(By.xpath("//textarea[@placeholder='Address']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressTextarea);
            Thread.sleep(500);
            addressTextarea.sendKeys(newUserData[8]);

            // 8 . Contact No field
            WebElement contactNo = driver.findElement(By.id("mobile_primary"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contactNo);
            Thread.sleep(500);
            contactNo.sendKeys(newUserData[9]);

            // submit button
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
            Thread.sleep(5000);

            // add user button
            WebElement addUserButton = driver.findElement(By.xpath("//button[normalize-space(text())='ADD User']"));
// Scroll up to the element using JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addUserButton);

            // Scroll a bit more up manually (e.g., 100 pixels)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");

// Optional: small delay to allow scroll animation to complete
            Thread.sleep(1000);

// Click the button
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addUserButton);

            Thread.sleep(2000);
        }
    }
}
