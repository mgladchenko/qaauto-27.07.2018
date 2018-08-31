package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinPasswordResetSubmitPage extends BasePage {
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("request-password-reset-submit");
    }

    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "linkedin.tst.yanina@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink =
                StringUtils.substringBetween(message,
                        "To change your LinkedIn password, click <a href=\"<a href=&quot;",
                        "&quot;>[text]</a>").replace("amp;","");

        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);
        return new LinkedinSetNewPasswordPage(browser);
    }
}
