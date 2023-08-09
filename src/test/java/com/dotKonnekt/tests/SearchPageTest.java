package com.dotKonnekt.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.pages.BlogPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.pages.SearchPage;
import com.dotKonnekt.utility.Log;

public class SearchPageTest extends BaseClass{
	RecipePageFinal recipePage;
	BlogPage blogPage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	HomePage homePage;
	CommonPagedetails commonPagedetails;
	SearchPage searchPage;
	
	
	@Test
	public void SearchPage_validate_RecentlyAdded_SearchFunctionalityforShopTab() throws InterruptedException {
		
		Log.startTestCase("-----------SearchPage_validate_RecentlyAdded_SearchFunctionalityforShopTab    Starts---------");
		searchPage = new SearchPage();
		homePage = new HomePage();
		launchApp_EB("Edge", prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		searchPage.validateSeachFunctionalityForShopTab("yoga", "Keyboard", "Search Page");
		searchPage.ShopProducts();
		searchPage.FilterByRecentlyAdded("HighToLow");
		searchPage.FilterByRecentlyAdded("LowToHigh");// Select string either HighToLow Or LowToHigh
		Log.endTestCase("-----------SearchPage_validate_RecentlyAdded_SearchFunctionalityforShopTab    Ends---------");
	}
	
	@Test
	public void SearchPage_validateSearchFunctionalityforArticleTab() throws InterruptedException {
		
		Log.startTestCase("-----------SearchPage_validateSearchFunctionalityforArticleTab    Starts---------");
		searchPage = new SearchPage();
		homePage = new HomePage();
		launchApp_EB("Chrome", prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		searchPage.SearchFunctionalityForArticleTab("yoga","Keyboard","Search Page");
		Log.endTestCase("-----------SearchPage_validateSearchFunctionalityforArticleTab    Ends---------");
	}
	
	
	@Test
	public void SearchPage_validateFilterFunctionality() throws InterruptedException {
		
		Log.startTestCase("-----------SearchPage_validateSearchFunctionalityforArticleTab    Starts---------");
		searchPage = new SearchPage();
		homePage = new HomePage();
		launchApp_EB("Chrome", prop.getProperty("HomePageurl"));
		//homePage.NewsletterPopup__Alert();
		searchPage.validateSeachFunctionalityForShopTab("yoga","Keyboard","Search Page");
		searchPage.filterOptions();
		Log.endTestCase("-----------SearchPage_validateSearchFunctionalityforArticleTab    Ends---------");
	}
	
	
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void tearDown() {
		getDriver().close();
	}
}
