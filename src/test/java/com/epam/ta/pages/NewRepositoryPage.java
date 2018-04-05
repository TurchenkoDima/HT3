package com.epam.ta.pages;

import com.epam.ta.GitHubAutomationTest;
import com.sun.scenario.effect.impl.prism.PrImage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewRepositoryPage extends AbstractPage{

    private final String BASE_URL = "https://github.com/";
    private String nameProject;

    @FindBy(xpath = "//svg[@class = 'octicon octicon-gear']")
    private WebElement buttonSettings;

    @FindBy(xpath = "//button[@class = 'btn btn-danger boxed-action' and text() = '\n" +
            "  \n" +
            "  Delete this repository\n']")
    private WebElement buttonDeleteThisRepository;

    @FindBy(xpath = "//input[@id = 'confirm_repository_name']")
    private WebElement input1ToDelete;

    @FindBy(xpath = "//input[@id = 'confirm_new_owner']")
    private WebElement input2ToDelete;

    @FindBy(xpath = "//input[@aria-label = 'Type in the name of the repository to confirm that you want to delete this repository.']")
    private WebElement input3ToDelete;

    @FindBy(xpath = "//button[@type = 'submit' and text() = 'I understand the consequences, delete this repository']")
    private WebElement buttonToDelete;

    @FindBy(xpath = "//div[@class = 'container']")
    private WebElement divWithTextToDelete;

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL+nameProject);
    }

    public NewRepositoryPage(WebDriver driver, String name)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.nameProject = name;
    }

    public void clickOnSettings() {
        driver.get(driver.getCurrentUrl()+"/settings");
    }

    public void clickOnDeleteThisRepository() {
        buttonDeleteThisRepository.click();
        if(input1ToDelete.isDisplayed()) {
            input1ToDelete.clear();
            input1ToDelete.sendKeys(nameProject);
        }else if(input3ToDelete.isDisplayed()){
            input3ToDelete.clear();
            input3ToDelete.sendKeys(nameProject);
        }
        if(input2ToDelete.isDisplayed()){
            input2ToDelete.clear();
            input2ToDelete.sendKeys("testautomationuser");
        }

        buttonToDelete.click();
    }

    public boolean isDeleteRepository() {
        return divWithTextToDelete.isDisplayed();
    }
}
