package com.dotKonnekt.tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.dataProviders.DataProviders;
import com.dotKonnekt.pages.BlogPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CheckoutPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;

public class HomePageTest extends BaseClass{
	
	RecipePageFinal recipePage;
	BlogPage blogPage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	HomePage homePage;
	CommonPagedetails commonPagedetails;
	CheckoutPage checkoutPage;
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	public void TitleVerification(String page, String title, String browser, String url) throws InterruptedException, MalformedURLException {
		Log.startTestCase("TitleVerification");
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		String actualTitle = commonPagedetails.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, "drupalpoc", "Title Not Verified");
		commonPagedetails.BrokenLinks(url);
		
		Log.endTestCase("TitleVerification");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	
	public void HomePage_PageHeaderVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("HomePage_PageHeaderVerification");
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
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
		Log.endTestCase("HomePage_PageHeaderVerification");
	}
	
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_SearchFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		
		Log.startTestCase("-----------HomePage_SearchFucntionalityVerification    Starts---------");
		
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		commonPagedetails.validateSeachFunctionality("Black","Keyboard",title);
		
		Log.info("SearchFucntionality Works perfectly");
		Log.endTestCase("-----------HomePage_SearchFucntionalityVerification    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_CartFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_CartFucntionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		commonPagedetails.validateCartIconFunctionality();
		Log.info("CartFucntionality Works perfectly");
		Log.endTestCase("-----------HomePage_CartFucntionalityVerification    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_UserFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

		Log.startTestCase("-----------HomePage_UserFunctionalityVerification    Starts---------");
		recipePage = new RecipePageFinal();
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		commonPagedetails.UserButtonFunctionality(title);
		Log.endTestCase("-----------HomePage_UserFunctionalityVerification    Ends---------");
	}

	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_NewArrival(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_NewArrival    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		//homePage.NewsletterPopup__Alert();
		homePage.newArrival();
		Log.endTestCase("-----------HomePage_NewArrival    Ends---------");
	}
	
	
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_ProductQuickviewVerification (String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_ProductQuickviewVerification    Starts---------");
		blogPage = new BlogPage();
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.ByQuickView();
		
		Log.endTestCase("-----------HomePage_ProductQuickviewVerification    Ends---------");
	}
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_shoutOutFromCustomers(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_shoutOutFromCustomers    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.shoutOut();
		Log.endTestCase("-----------HomePage_shoutOutFromCustomers    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_askOurExpertSection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_askOurExpert    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.askOurExpert();
		Log.endTestCase("-----------HomePage_askOurExpert    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_SendAMessageFunctionalityGuestUser(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_SendAMessageFunctionality    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.sendAMessage("Guest");   //enter type of User -  Logged, Guest
		Log.endTestCase("-----------HomePage_SendAMessageFunctionality    Ends---------");
	}
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "LoggedIn")
	public void HomePage_SendAMessageFunctionalityLoggedInUser(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_SendAMessageFunctionality    Starts---------");
		homePage = new HomePage();
		loginPage = new LoginPage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		loginPage.loginSetup1(prop.getProperty("Username"), prop.getProperty("Password"));
		homePage.onlyNewsLetter();
		
		homePage.sendAMessage("Logged");
		Log.endTestCase("-----------HomePage_SendAMessageFunctionality    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_OurStorySection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_OurStorySection    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.ourStory_AboutUs();
		Log.endTestCase("-----------HomePage_OurStorySection    Ends---------");
	}
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_aboveAboutUsSection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_aboveAboutUsSection    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.aboveAboutSection();
		Log.endTestCase("-----------HomePage_aboveAboutUsSection    Ends---------");
	}
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_discountVoucherSection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_discountVoucherSection    Starts---------");
		homePage = new HomePage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.discountVoucher();
		Log.endTestCase("-----------HomePage_discountVoucherSection    Ends---------");
	}
	
	/*
	 * @Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class,
	 * enabled = true, groups = "NotLoggedIn") public void
	 * HomePage_HeroImage/VideoSection(String page, String title, String browser,
	 * String url) throws InterruptedException {
	 * Log.startTestCase("-----------HomePage_HeroImage    Starts---------");
	 * homePage = new HomePage(); launchApp_EB(browser, url);
	 * homePage.NewsletterPopup__Alert(); homePage.discountVoucher();
	 * Log.endTestCase("-----------HomePage_HeroImage    Ends---------"); }
	 */
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Starts---------");
		homePage = new HomePage();
		checkoutPage = new CheckoutPage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.endToEndScenarioHomePageByClickingProductTile();
		checkoutPage.availabiltyStock();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoGuest();
		checkoutPage.shippingAddressGuest();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		Log.endTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Ends---------");
	}
	
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_endToEndGuestScenarioByClickingProductCartIconGuestUSer(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Starts---------");
		homePage = new HomePage();
		checkoutPage = new CheckoutPage();
		launchApp_EB(browser, url);
		homePage.newsletterPopupAlert();
		homePage.ByCartIcon();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoGuest();
		checkoutPage.shippingAddressGuest();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		Log.endTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Ends---------");
	}
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = false, groups = "LoggedIn")
	public void HomePage_endToEndGuestScenarioByClickingProductCartIconLoggedUSer(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Starts---------");
		homePage = new HomePage();
		loginPage = new LoginPage();
		checkoutPage = new CheckoutPage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		loginPage.loginSetup1(prop.getProperty("Username"), prop.getProperty("Password"));
		homePage.onlyNewsLetter();
		
		Thread.sleep(1000);
		homePage.ByCartIcon();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoGuest();
		checkoutPage.shippingAddressGuest();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		Log.endTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileGuestUSer    Ends---------");
	}
	
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void HomePage_endToEndGuestScenarioByClickingQuickViewGuestUSer(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_endToEndGuestScenarioByClickingQuickViewGuestUSer    Starts---------");
		homePage = new HomePage();
		checkoutPage = new CheckoutPage();
		blogPage = new BlogPage();
		launchApp_EB(browser, url);
		
		homePage.newsletterPopupAlert();
		homePage.ByQuickView();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoGuest();
		checkoutPage.shippingAddressGuest();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		Log.endTestCase("-----------HomePage_endToEndGuestScenarioByClickingQuickViewGuestUSer    Ends---------");
	}
	
	 
	
	@Test(dataProvider = "HomePage", dataProviderClass = DataProviders.class, enabled = false, groups = "LoggedIn")
	public void HomePage_endToEndGuestScenarioByClickingProductTileLoggedIn(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileLoggedIn    Starts---------");
		homePage = new HomePage();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		loginPage.loginSetup1(prop.getProperty("Username"), prop.getProperty("Password"));
		homePage.onlyNewsLetter();
		
		Thread.sleep(1000);
		homePage.endToEndScenarioHomePageByClickingProductTile();
		checkoutPage.availabiltyStock();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoLogged();
		checkoutPage.shippingAddressLogged();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		Log.endTestCase("-----------HomePage_endToEndGuestScenarioByClickingProductTileLoggedIn    Ends---------");
	}
	

	
	
	@Test
	public void BrowserStack() {
		
	}
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void tearDown() {
		getDriver().close();
	}
}
