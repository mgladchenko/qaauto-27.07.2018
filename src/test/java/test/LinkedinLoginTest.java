package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinLoginSubmitPage;

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
                { "linkedin.tst.yanina@gmail.com", "Test123!" },
                { "linkedin.TST.yanina@gmail.com", "Test123!" }
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        LinkedinHomePage linkedinHomePage =
                linkedinLoginPage.loginReturnHomePage(userEmail, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(),
                "Home page is not loaded.");
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
        linkedinLoginPage.loginReturnLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(),
                "User is not on Login page.");
    }

    @DataProvider
    public Object[][] invalidDataFieldsCombination() {
        return new Object[][]{
                { "a",
                        "a",
                        "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                        "The password you provided must have at least 6 characters."}
        };
    }

    @Test(dataProvider = "invalidDataFieldsCombination")
    public void validateUserEmailAndPassword(String userEmail,
                                                  String userPass,
                                                  String userEmailValidationText,
                                                  String userPassValidationText) {
        LinkedinLoginSubmitPage linkedinLoginSubmitPage =
                linkedinLoginPage.loginReturnLoginSubmitPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(),
                "User is not on LoginSubmit page.");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),
                userEmailValidationText,
                "userEmail field has wrong validation message text.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(),
                userPassValidationText,
                "userEmail field has wrong validation message text.");

    }



}
