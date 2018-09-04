package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedinLoginPage;

public class BaseTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(@Optional("firefox") String browserName) {
        if (browserName.toLowerCase().equals("firefox")) {
            browser = new FirefoxDriver();
        }
        if (browserName.toLowerCase().equals("chrome")) {
            browser = new ChromeDriver();
        } else {
            try {
                throw new Exception("browserName "+browserName+" is not supported.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }
}
