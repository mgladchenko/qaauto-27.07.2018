import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {

    private WebDriver browser;

    private WebElement alertBox;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
    }

    public String getAlertBoxText() {
        return alertBox.getText();
    }
}
