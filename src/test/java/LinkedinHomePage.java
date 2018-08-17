import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage extends BasePage {

    private WebElement profileNavigationItem;

    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        profileNavigationItem = browser.
                findElement(By.xpath("//*[@id='profile-nav-item']"));
    }

    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }
}
