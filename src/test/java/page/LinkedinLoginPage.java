package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Page Object class for LinkedinLoginPage
 */
public class LinkedinLoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor of LinkedinLoginPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
    }

    /**
     * Method that enters userEmail/userPass and click on signIn button.
     * @param userEmail - String with user email.
     * @param userPass - String with user password.
     * @param <T> - Generic type to return corresponding pageObject.
     * @return either LinkedinHomePage or LinkedinLoginSubmitPage or
     * LinkedinLoginPage pageObject.
     */
    public <T> T login(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        if (getCurrentPageUrl().contains("/feed")){
            return (T) new LinkedinHomePage(browser);
        }
        if (getCurrentPageUrl().contains("/uas/login-submit")) {
            return (T) new LinkedinLoginSubmitPage(browser);
        } else {
            return (T) new LinkedinLoginPage(browser);
        }
    }

    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up");
    }

    public LinkedinRequestPasswordResetPage clickOnForgotPasswordLink() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(browser);
    }
}
