package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps
{
	private WebDriver driver;

	private String nameProject;

	private String nameGist;
	private String textGist;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		this.nameProject = createNewRepositoryPage.getCurrentRepositoryName();
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public boolean deleteRepository() {
		NewRepositoryPage page = new NewRepositoryPage(driver, nameProject);
		page.clickOnSettings();
		page.clickOnDeleteThisRepository();
		return page.isDeleteRepository();
	}

	public boolean createNewGist(String gistName, String gistDescription, String gistText) {
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewGist();
		CreateNewGistPage createNewGistPage = new CreateNewGistPage(driver);
		this.textGist = gistText;
		this.nameGist = createNewGistPage.createNewGist(gistName, gistDescription, gistText);
		return createNewGistPage.getNameGist(nameGist);
	}

	public boolean deleteGist() {
		NewGistPage newGistPage = new NewGistPage(driver);
		newGistPage.clickOnLinkToSeeAllGits();
		newGistPage.clickToOpenCurrentGist(nameGist);
		return newGistPage.clickOnDeleteGist();
	}

	public boolean findUser(String nameUser) {
		FindUserPage findUserPage = new FindUserPage(driver);
		findUserPage.openPage();
		findUserPage.inputSerchData(nameUser);
		findUserPage.clickSearch();
		return findUserPage.isFindUser();
	}

	public boolean isTransferOnPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnPullRequests();
		if(mainPage.isPullrequest())
			mainPage.openPage();
		else return false;
		mainPage.clickOnIssues();
		if(mainPage.isIssues())
			mainPage.openPage();
		else return false;
		mainPage.clickOnMarketplace();
		if(mainPage.isMarketplace())
			mainPage.openPage();
		else return false;
		mainPage.clickOnExplore();
		if(mainPage.isExplore())
			mainPage.openPage();
		else return false;
		return true;
	}
}
