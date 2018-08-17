import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage extends BasePage {

    private WebElement alertBox;
    private WebElement userEmailValidationText;
    private WebElement userPasswordValidationText;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        userEmailValidationText = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        userPasswordValidationText = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String getAlertBoxText() {
        return alertBox.getText();
    }

    public boolean isLoaded() {
        return alertBox.isDisplayed()
                && getCurrentPageTitle().contains("Sign In to LinkedIn")
                && getCurrentPageUrl().contains("/uas/login-submit");
    }

    public String getUserEmailValidationText() {
        return userEmailValidationText.getText();
    }

    public String getUserPasswordValidationText() {
        return userPasswordValidationText.getText();
    }
}
