package com.epam.ta.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://github.com/";


	@FindBy(xpath = "//summary[contains(@aria-label, 'Create new…')]")
	private WebElement buttonCreateNew;

	@FindBy(xpath = "//a[contains(text(), 'New repository')]")
	private WebElement linkNewRepository;

	@FindBy(xpath = "//a[contains(text(), '\n" +
			"  New gist\n')]")
	private WebElement linkNewGist;

	@FindBy(xpath = "//a[text() = '\n" +
			"                Pull requests\n']")
	private WebElement linkPullRequests;

	@FindBy(xpath = "//a[text() = '\n" +
			"                Issues\n']")
	private WebElement linkIssues;

	@FindBy(xpath = "//a[text() = '\n" +
			"                    Marketplace\n']")
	private WebElement linkMarketplace;

	@FindBy(xpath = "//a[text() = '\n" +
			"                Explore\n']")
	private WebElement linkExplore;


	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public void clickOnCreateNewRepositoryButton()
	{
		buttonCreateNew.click();
		linkNewRepository.click();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}


	public void clickOnCreateNewGist() {
		buttonCreateNew.click();
		linkNewGist.click();
	}

	public void clickOnPullRequests() {
		if(linkPullRequests.isDisplayed())
			linkPullRequests.click();
	}

	public boolean isPullrequest() {
		return driver.getTitle().equals("Pull Requests");
	}

	public void clickOnIssues() {
		if(linkIssues.isDisplayed())
			linkIssues.click();
	}

	public boolean isIssues() {
		return driver.getTitle().equals("Issues");
	}

	public void clickOnMarketplace() {
		if(linkMarketplace.isDisplayed())
			linkMarketplace.click();
	}

	public boolean isMarketplace() {
		return driver.getTitle().equals("Marketplace · Tools to improve your workflow");
	}

	public void clickOnExplore() {
		if(linkExplore.isDisplayed())
			linkExplore.click();
	}

	public boolean isExplore() {
		return driver.getTitle().equals("Explore");
	}
}
