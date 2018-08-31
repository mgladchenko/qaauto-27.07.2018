import org.openqa.selenium.WebDriver;
import util.GMailService;

public abstract class BasePage {
    protected WebDriver browser;
    protected static GMailService gMailService = new GMailService();

    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }

    public abstract boolean isLoaded();
}
