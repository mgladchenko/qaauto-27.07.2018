import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSetNewPasswordPage extends BasePage{
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        //Fixme
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().equals("Please check your mail for reset password link.  | LinkedIn")
                && getCurrentPageUrl().contains("checkpoint/rp/request-password-reset-submit");
    }
}
