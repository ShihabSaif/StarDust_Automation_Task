package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.DataRead;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TicketCreateAndFilterFeature extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public TicketCreateAndFilterFeature() throws Exception, IOException, InterruptedException {
    }

    public void helpdesk() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println(driver.getPageSource());
        driver.findElement(By.className("sidebar-menu-item")).click();
    }

    public void ticket() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a[href='/pages/helpdesk']")).click();
    }

    public void addTicketButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement addTicket = driver.findElement(By.cssSelector("button.btn.btn-primary.btn-simple"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addTicket);

        // Scroll a bit more up manually (e.g., 100 pixels)
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");

        // Optional: small delay to allow scroll animation to complete
        Thread.sleep(1000);

        // Click the button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addTicket);
    }

    public void newTicketCreation() throws InterruptedException {
        Thread.sleep(5000);

        List<String[]> ticketData = DataRead.readTicketDataFromCSV();

        for (String[] newTicketData : ticketData) {
            // --- 1. Select Service Type ---
            List<WebElement> dropdowns = driver.findElements(By.cssSelector("select.form-control"));

            // Get the second dropdown (index 1)
            WebElement serviceTypeDropdown = dropdowns.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", serviceTypeDropdown);
            // Scroll a bit more up manually (e.g., 100 pixels)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");

            Select select = new Select(serviceTypeDropdown);
            select.selectByValue(newTicketData[0]);

            Thread.sleep(1500);

            // --- 2. Select Problem ---
            WebElement problemDropdown = dropdowns.get(1);
            Select selectProblem = new Select(problemDropdown);
            selectProblem.selectByValue(newTicketData[1]);

            Thread.sleep(1500);

            // --- 3. Select Pending reason ---
            WebElement pendingReasonDropdown = dropdowns.get(2);
            Select selectPendingReason = new Select(pendingReasonDropdown);
            selectPendingReason.selectByValue(newTicketData[2]);

            Thread.sleep(1500);

            // --- 4. Select Pending reason ---
            WebElement customerTypeDropdown = dropdowns.get(3);
            Select selectCustomerType = new Select(customerTypeDropdown);
            selectCustomerType.selectByValue(newTicketData[3]);

            Thread.sleep(1500);

            // --- 5. Select Customer ---
            driver.findElement(By.xpath("//input[@placeholder='Search ...']")).sendKeys(newTicketData[4]);

            // Wait until the first visible dropdown option appears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement secondDropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//ul[contains(@class,'el-select-dropdown__list')])[2]//li[not(contains(@class,'is-disabled'))][1]")
            ));
            // Click the first visible option
            secondDropdownOption.click();

            Thread.sleep(1500);

            // --- 6. Select Assigned to ---
            driver.findElement(By.xpath("//input[@placeholder='Choose here']")).sendKeys(newTicketData[5]);

            Thread.sleep(1500);

            // --- 7. Select Description ---
            driver.findElement(By.xpath("//input[@placeholder='Description']")).sendKeys(newTicketData[6]);

            Thread.sleep(1500);

            // --- 8. Select priority ---
            WebElement priorityDropdown = dropdowns.get(4);
            Select selectPriority = new Select(priorityDropdown);
            selectPriority.selectByValue(newTicketData[7]);

            Thread.sleep(1500);

            // --- 9. select submit button ---
            driver.findElement(By.xpath("//button[normalize-space(text())='Save']")).click();

            Thread.sleep(10000);

            // add ticket button
            WebElement addTicketButton = driver.findElement(By.xpath("//button[normalize-space(text())='Add Ticket']"));
            // Scroll up to the element using JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addTicketButton);

            // Scroll a bit more up manually (e.g., 100 pixels)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");

            // Optional: small delay to allow scroll animation to complete
            Thread.sleep(1000);

            // Click the button
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addTicketButton);

            Thread.sleep(5000);


        }
        // Scroll to top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        // Optional: wait briefly to ensure scroll completes
        Thread.sleep(1000);

        // Wait until the "Ticket List" button is clickable
        WebDriverWait waitTicketList = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ticketListButton = waitTicketList.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='Ticket List']"))
        );

        // Click the button using JavaScript (recommended if intercept issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ticketListButton);
    }

    public void FilteringTicket() throws InterruptedException {
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Step 1: Click the dropdown wrapper to open the dropdown
        WebElement dropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.el-select.filter_select_input.problem")));
        dropdownWrapper.click();

// Optional wait for dropdown animation
        Thread.sleep(500);

// Step 2: Locate the actual first non-placeholder option in the 5th dropdown
        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//ul[contains(@class,'el-select-dropdown__list')])[5]//li[normalize-space(string()) != '-- select problem --']")));

// Step 3: Scroll to and click the option using JavaScript to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);



    }
}