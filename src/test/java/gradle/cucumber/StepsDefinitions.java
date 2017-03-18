package gradle.cucumber;

import common.WebDriverUtils;

import org.junit.Assert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.ApiHelper;
import helpers.LinkHelper;
import pageobjects.GamePage;
import pageobjects.UserGamesPage;


public class StepsDefinitions extends WebDriverUtils {

    // PageObjects
    private UserGamesPage userGamesPage;
    private GamePage gamePage;

    // Parameters
    private String gameLink;

    @Before
    public void setUp() {
        initializeLocalBrowser("chrome");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @When("^Open the game collection of a user \"([^\"]*)\"$")
    public void openUserGameCollection(String username) throws Throwable {
        userGamesPage = new UserGamesPage(driver, username).get();
    }

    @When("^Go to the page of one of the games \\(chosen at random\\)$")
    public void openRandomGame(){
        List<String> games = userGamesPage.getGamesForUser();
        gameLink = games.get(ThreadLocalRandom.current().nextInt(games.size()));
        gamePage = new GamePage(driver, gameLink);
    }

    @Then("^Verify that the most voted Language Dependence level is presented on the game's page$")
    public void verifyLanguageDependency() throws Throwable {
        int gameId = LinkHelper.getIdFromLink(gameLink);
        String languageDependencyText = ApiHelper.getMaximumDependencyLevelText(gameId);
        Assert.assertTrue("Game Language Dependency is not presented "
                          + "or presented with wrong text", gamePage.isLanguageDependencyDisplayed(languageDependencyText));
    }

}