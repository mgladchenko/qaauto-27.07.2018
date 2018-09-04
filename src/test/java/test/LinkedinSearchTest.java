package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinSearchPage;

public class LinkedinSearchTest {

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

    /**
     * Verify successful search by searchTerm.
     *
     * Preconditions:
     * - Navigate to https://www.linkedin.com/
     *
     * Steps:
     * - Verify Login page is loaded.
     * - Login as registered user.
     * - Verify Home page is loaded.
     * - Search for "HR" searchTerm
     * - Verify Search page is loaded.
     * - Verify 10 results displayed on page.
     * - Verify each result contains searchTerm.
     */
    @Test
    public void basicSearchTest() {
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginReturnHomePage(
                        "linkedin.tst.yanina@gmail.com",
                        "Yanina123");
        Assert.assertTrue(linkedinHomePage.isLoaded(),
                "Home page is not loaded.");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search("HR");

        Assert.assertTrue(linkedinSearchPage.isLoaded(),
                "Search page is not loaded.");

        Assert.assertEquals(linkedinSearchPage.getSearchResultsCount(), 10,
                "Search results count is wrong.");







    }
}
