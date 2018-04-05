package com.epam.ta.pages;

import com.epam.ta.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewGistPage extends AbstractPage{

    private final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//input[@name = 'gist[description]' and @aria-label = 'Gist description']")
    private WebElement inputDescription;

    @FindBy(xpath = "//input[@name = 'gist[contents][][name]' and @aria-label = 'Filename including extension…']")
    private WebElement inputName;

    @FindBy(xpath = "//pre[@class = ' CodeMirror-line ']")
    private WebElement inputText;

    @FindBy(xpath = "//div[@class = 'CodeMirror-lines']")
    private WebElement inputText1;

    @FindBy(xpath = "//button[@name = 'gist[public]' and text() = 'Create public gist']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//strong[@class = 'gist-header-title css-truncate-target' and @itemprop = 'name']/a")
    private WebElement labelName;

    public CreateNewGistPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public String createNewGist(String gistName, String gistDescription, String gistText) {
        String FullName = gistName + Utils.getRandomString(6)+".txt";
        if(inputDescription.isDisplayed()){
          inputDescription.clear();
          inputDescription.sendKeys(gistDescription);
        }
        if(inputName.isDisplayed()){
            inputName.clear();
            inputName.sendKeys(FullName);
        }
        if(inputText.isDisplayed()){

            //inputText1.click();

            //driver.switchTo().frame("");
            //inputText.clear();
            inputText1.sendKeys(gistText);
        }
        if (buttonCreate.isDisplayed())
            buttonCreate.click();
        return FullName;
    }

    public boolean getNameGist(String equal) {
        if(equal.equals(labelName))
            return true;
        return false;
    }
}
