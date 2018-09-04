package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;

import java.util.List;

public class LinkedinSearchTest extends BaseTest {

    /**
     * Verify successful search by searchTerm.
     *
     * Preconditions:
     * - Navigate to https://www.linkedin.com/
     *
     * Steps:
     * - Verify Login page is loaded.
     * - Login as registered user.
     * - Verify Home page is loaded.
     * - Search for "HR" searchTerm
     * - Verify Search page is loaded.
     * - Verify 10 results displayed on page.
     * - Verify each result contains searchTerm.
     */
    @Test
    public void basicSearchTest() {
        String searchTerm = "HR";

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(
                        "linkedin.tst.yanina@gmail.com",
                        "Test123!");
        Assert.assertTrue(linkedinHomePage.isLoaded(),
                "Home page is not loaded.");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search(searchTerm);

        Assert.assertTrue(linkedinSearchPage.isLoaded(),
                "Search page is not loaded.");

        Assert.assertEquals(linkedinSearchPage.getSearchResultsCount(), 10,
                "Search results count is wrong.");

        List<String> searchResults = linkedinSearchPage.getSearchResultsList();

        for (String searchResult: searchResults) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "searchTerm "+searchTerm+" not found in: \n"+searchResult);
        }







    }
}
