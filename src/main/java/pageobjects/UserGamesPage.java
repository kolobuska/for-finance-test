package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;
import java.util.stream.Collectors;

import static common.WebDriverUtils.LOGGER;

public class UserGamesPage extends LoadableComponent<UserGamesPage> {

    private WebDriver driver;
    private String link;

    final String USER_URL_START = "https://boardgamegeek.com/collection/user/";
    final String USER_URL_END = "?own=1&subtype=boardgame&ff=1";

    public UserGamesPage(WebDriver driver, String userId) {
        this.driver = driver;
        link = USER_URL_START + userId + USER_URL_END;
        driver.get(link);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[starts-with(@id, 'results_objectname')]/a")
    List<WebElement> games;

    /**
     * Get games for user.
     * @return get the list of games for user
     */
    public List<String> getGamesForUser(){
        List<String> links = games.stream().map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
        return links;

    }

    @Override
    protected void load() {
        LOGGER.info("UserGames.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on right page: " + url, url.equals(link));
    }
}