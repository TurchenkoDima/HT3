package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	private String nameUser = "mrzvv";//Пользователь для поиска

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		Assert.assertTrue(steps.deleteRepository());
		// do not use lots of asserts
	}

	@Test
	public void oneCanCreateGist(){
		Assert.fail("Can`t input the text in Gist [org.openqa.selenium.ElementNotInteractableException: Element <span> is not reachable by keyboard]");
		steps.loginGithub(USERNAME,PASSWORD);
		Assert.assertTrue(steps.createNewGist("testGist","auto-generated test repo","I want to be a tester ;)"));
		Assert.assertTrue(steps.deleteGist());
	}

	@Test
	public void oneCanFindUser(){
		steps.loginGithub(USERNAME,PASSWORD);
		Assert.assertTrue(steps.findUser(nameUser));
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	@Test
	public void oneCanTransferOnPage(){
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isTransferOnPage());
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
