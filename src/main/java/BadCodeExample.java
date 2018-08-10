import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello world!!!");
        WebDriver browser = new FirefoxDriver();
        browser.get("https://google.com");
        WebElement queryField = browser.findElement(By.name("q"));
        queryField.sendKeys("selenium");
        queryField.sendKeys(Keys.ENTER);

        //Verify that results list contains 10 elements
        sleep(3000);
        List<WebElement> searchResults =
                browser.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        System.out.println("Results count: "+searchResults.size());

        if (searchResults.size() == 10) {
            System.out.println("Results count is correct.");
        }
        else {
            System.out.println("Results count is incorrect.");
        }

        //Verify that each result item contains searchterm
        //for each searchResults in searchResult List
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            System.out.println(searchResultText);

            if (searchResultText.toLowerCase().contains("selenium")) {
                System.out.println("SearchTerm Found.");
            }
            else {
                System.out.println("SearchTerm Not Found.");
            }
        }

        sleep(3000);
        browser.close();
    }
}
