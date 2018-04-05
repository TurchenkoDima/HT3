package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindUserPage extends AbstractPage {

    private final String BASE_URL = "https://github.com/search";
    private String nameUser;

    @FindBy(xpath = "//input[@aria-label = 'Search GitHub']")
    private WebElement inputDataSearch;

    @FindBy(xpath = "//button[text() = 'Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//a[text() = 'Users']")
    private WebElement linkUsers;

    @FindBy(xpath = "//div[@class = 'user-list-info ml-2']/a/em")
    private WebElement labelUser;

    public FindUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void inputSerchData(String searchData) {
        this.nameUser = searchData;
        if(inputDataSearch.isDisplayed()){
            inputDataSearch.clear();
            inputDataSearch.sendKeys(searchData);
        }
    }

    public void clickSearch() {
        if(buttonSearch.isDisplayed()){
            buttonSearch.click();
        }
    }

    public void chooseFindUser() {
        if(linkUsers.isDisplayed())
            linkUsers.click();
    }

    public boolean isFindUser() {
        chooseFindUser();
        if(labelUser.isDisplayed())
            return labelUser.getText().equals(nameUser);
        return false;
    }
}
