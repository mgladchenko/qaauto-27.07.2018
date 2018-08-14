import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }


    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Yanina123" },
                { "linkedin.TST.yanina@gmail.com", "Yanina123" }
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),
                "Home page is not loaded.");
    }

    @Test
    public void negativeLoginTest() {
        linkedinLoginPage.login(
                "a@b.c",
                "wrong");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                { "", "" },
                { "", "P@ssword123" },
                { "someone@domain.com", "" }
        };
    }


    @Test(dataProvider = "emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword (String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(),
                "User is not on Login page.");
    }

    @Test
    public void validateShortUserEmailAndPassword() {
        linkedinLoginPage.login("a", "a");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(),
                "User is not on LoginSubmit page.");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "userEmail field has wrong validation message text.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "userEmail field has wrong validation message text.");

    }



}
