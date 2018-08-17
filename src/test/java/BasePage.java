import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver browser;

    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }

    public abstract boolean isLoaded();
}
