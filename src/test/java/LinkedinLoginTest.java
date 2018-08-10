import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver browser;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        linkedinLoginPage.
                login("linkedin.tst.yanina@gmail.com", "Yanina123");

        sleep(3000);

        String pageTitle = browser.getTitle();
        String pageUrl = browser.getCurrentUrl();
        WebElement profileNavigationItem =
                browser.findElement(By.xpath("//*[@id='profile-nav-item']"));

        Assert.assertEquals(pageTitle, "LinkedIn",
                "Home page Title is wrong.");
        Assert.assertEquals(pageUrl, "https://www.linkedin.com/feed/",
                "Home page URL is wrong.");
        Assert.assertTrue(profileNavigationItem.isDisplayed(),
                "'profileNavigationItem' is not displayed on Home page.");
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        WebElement userEmailField = browser
                .findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = browser
                .findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = browser
                .findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        sleep(3000);

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");
    }



}
