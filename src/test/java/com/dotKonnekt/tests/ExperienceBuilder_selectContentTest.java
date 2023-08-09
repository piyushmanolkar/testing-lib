package com.dotKonnekt.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.dataProviders.DataProviders;
import com.dotKonnekt.pages.BlogPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.EB_selectContentPage;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.ProductPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;

public class ExperienceBuilder_selectContentTest extends BaseClass {
	EB_selectContentPage ebPage;
	
	BlogPage blogPage;
	CommonPagedetails commonPagedetails;
	ProductPage productPage;
	RecipePageFinal recipePage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	HomePage homePage;
	BlogPageTest blogPageTest;
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void LoginPage(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------ContentTileVerificationPresence    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		//ebPage.EB_login();
		ebPage.ebLoginPage();
		Log.endTestCase("-----------ContentTileVerificationPresence    Ends---------");
	}
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void universalSearchBarFunctionality(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------universalSearchBarFunctionality    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		//ebPage.buildAnExperienceBoX();
		ebPage.ContentTileVerification();
		//ebPage.EB_UniversalSearch("blavk","mouse");
		Log.endTestCase("-----------universalSearchBarFunctionality    Ends---------");
	}
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void noResultFoundTest(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------noResultFoundTest    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.EB_UniversalSearch("blavk","mouse");
		ebPage.NoResultFound();
		Log.endTestCase("-----------noResultFoundTest    Ends---------");
	}
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void buildAnExperienceBoXElements(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------buildAnExperienceBoXElements    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.buildAnExperienceBoX();
		Log.endTestCase("-----------buildAnExperienceBoXElements    Ends---------");
	}
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void ContentTileVerificationPresence(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------ContentTileVerificationPresence    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.ContentTileVerification();
		Log.endTestCase("-----------ContentTileVerificationPresence    Ends---------");
	}
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void LeftMenusIcon(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------ContentTileVerificationPresence    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.leftMenus();
		Log.endTestCase("-----------ContentTileVerificationPresence    Ends---------");
	}
	
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void Searchfunctionality(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------ContentTileVerificationPresence    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.Search("black","mouse");
		Thread.sleep(2000);
		commonPagedetails = new CommonPagedetails();
		Log.info("-----------Logo Verification Starts----------");
		boolean logoResult = commonPagedetails.valaidateLogo();
		Assert.assertTrue(logoResult);
		Log.info("-----------Logo Verification End successfully----------");
		Log.info("-----------SearchBox Verification Starts----------");
		boolean searchResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(searchResult);
		Log.info("-----------SearchBox Verification End Successfully----------");
		Log.info("-----------Cartbutton Verification Starts----------");
		boolean cartResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(cartResult);
		Log.info("-----------Cartbutton Verification End Successfully----------");
		Log.info("-----------Userbutton Verification Starts----------");
		boolean userResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(userResult);
		Log.info("-----------Userbutton Verification End Successfully----------");
		Log.endTestCase("BlogPage_PageHeaderVerification");
	
		Log.startTestCase("-----------BlogPage_addCommentVerification    Starts---------");
		recipePage = new RecipePageFinal();
		recipePage.addCommentVerification();
		Log.endTestCase("-----------BlogPage_addCommentVerification    Ends---------");
		
		Log.startTestCase("-----------BlogPage_Image_dotFashion    Starts---------");
		blogPage = new BlogPage();
		blogPage.imageVerification();
		Log.endTestCase("-----------BlogPage_Image_dotFashion    Ends---------");
		
		
		
		
		Log.endTestCase("-----------ContentTileVerificationPresence    Ends---------");
	}
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void PreviewtheMagic(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------ContentTileVerificationPresence    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.previewTheMagicSection();
		Log.endTestCase("-----------ContentTileVerificationPresence    Ends---------");
	}
	
	
	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void addProductSectionverification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------addProductSectionverification    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.addProductsSection();
		Log.endTestCase("-----------addProductSectionverification    Ends---------");
	}
	
	
	

	@Test(dataProvider = "ExperienceBuilder", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void engageCustomerSection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------engageCustomerSection    Starts---------");
		ebPage = new EB_selectContentPage();
		launchApp_EB(browser, url);
		ebPage.EB_login();
		ebPage.engageSection();
		Log.endTestCase("-----------engageCustomerSection    Ends---------");
	}
	
	
	
	
	
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void teardown() {
		getDriver().close();
	}
	
}
