package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static common.WebDriverUtils.LOGGER;

public class GamePage extends LoadableComponent<GamePage> {

    private WebDriver driver;
    private String link;

    @FindBy(xpath = "//span[@ng-bind-html='geekitemctrl.geekitem.data.item.polls.languagedependence|to_trusted']")
    private WebElement languageDependency;

    public GamePage(WebDriver driver, String link) {
        this.driver = driver;
        this.link = link;
        driver.get(link);
        PageFactory.initElements(driver, this);
    }

    /**
     * Is language Dependency is displayed or not.
     * @param text text that should be inside of level dependency span
     * @return displayed or not
     */
    public boolean isLanguageDependencyDisplayed(String text){
        return languageDependency.isDisplayed() && languageDependency.getText().equals(text);
    }

    @Override
    protected void load() {
        LOGGER.info("GamePage.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on right page: " + url, url.equals(link));
    }
}