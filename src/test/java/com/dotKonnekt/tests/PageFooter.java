package com.dotKonnekt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.pages.BlogPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;

public class PageFooter extends BaseClass {
	HomePage homePage;
	LoginPage loginPage;
	RecipePageFinal recipePage;
	CategoryPage categoryPage;
	CommonPagedetails commonPagedetails;
	
	@Test
	public void pageFooterLinks() throws InterruptedException {
		Log.startTestCase("-----------pageFooterLinks    Starts---------");
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB("Chrome", prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		commonPagedetails.PageFooterLinks();
		Log.endTestCase("-----------pageFooterLinks    Ends---------");
	}
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void teardown() {
		getDriver().close();
	}

}
