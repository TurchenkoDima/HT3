package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewGistPage extends AbstractPage{

    private final String BASE_URL = "https://gist.github.com/";

    @FindBy(xpath = "//a[text() = 'See all of your gists']")
    private WebElement linkSeeAllGist;

    @FindBy(xpath = "//span[@class = 'creator']/a[2]/strong[@class = 'css-truncate-target']")
    private WebElement linkToOpenCurrentGist;

    @FindBy(xpath = "//button[@aria-label = 'Delete this Gist']")
    private WebElement buttonToDelete;

    public NewGistPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void clickOnLinkToSeeAllGits() {
        linkSeeAllGist.click();
    }

    public void clickToOpenCurrentGist(String name) {
        linkToOpenCurrentGist.click();
    }

    public boolean clickOnDeleteGist() {
        if(buttonToDelete.isDisplayed()){
            buttonToDelete.click();
            return true;
        }
        return false;
    }
}
