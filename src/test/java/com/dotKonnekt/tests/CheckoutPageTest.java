package com.dotKonnekt.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.dataProviders.DataProviders;
import com.dotKonnekt.pages.CheckoutPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.CheckoutPage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.ProductPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.pages.SearchPage;
import com.dotKonnekt.utility.Log;

public class CheckoutPageTest extends BaseClass {
	
	RecipePageFinal recipePage;
	HomePage homePage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	CheckoutPage checkoutPage;
	CommonPagedetails commonPagedetails;
	ProductPage productPage;
	SearchPage searchPage;
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	public void TitleVerification(String page, String title, String browser, String url) {
		Log.startTestCase("TitleVerification");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		String actualTitle = commonPagedetails.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, "Checkout", "Title Not Verified");
		Log.endTestCase("TitleVerification");
	}
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	
	public void CheckoutPage_PageHeaderVerification(String page, String title, String browser, String url) {
		Log.startTestCase("CheckoutPage_PageHeaderVerification");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
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
		Log.endTestCase("CheckoutPage_PageHeaderVerification");
	}
	
	
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_SearchFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		
		Log.startTestCase("-----------CheckoutPage_SearchFucntionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		checkoutPage = new CheckoutPage();
		launchApp_V1(browser, url);
		
		commonPagedetails.validateSeachFunctionality("Black","Keyboard",title);
		
		Log.info("SearchFucntionality Works perfectly");
		Log.endTestCase("-----------CheckoutPage_SearchFucntionalityVerification    Ends---------");
	}
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_CartFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CheckoutPage_CartFucntionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		commonPagedetails.validateCartIconFunctionality();
		Log.info("CartFucntionality Works perfectly");
		Log.endTestCase("-----------CheckoutPage_CartFucntionalityVerification    Ends---------");
	}
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_UserFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

		Log.startTestCase("-----------CheckoutPage_UserFunctionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		commonPagedetails.UserButtonFunctionality(title);
		Log.endTestCase("-----------CheckoutPage_UserFunctionalityVerification    Ends---------");
	}

	
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_LogoFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

		Log.startTestCase("-----------CheckoutPage_LogoFunctionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		commonPagedetails.logoFunctionality(title);
		Log.info("LogoFunctionalityVerification Works perfectly");
		Log.endTestCase("-----------CheckoutPage_LogoFunctionalityVerification    Ends---------");
	}
	
	
//Lotus Antioxidant Night Moisturizer
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_GuestUseraddItemTotheCartforShopTab(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Starts---------");
		checkoutPage = new CheckoutPage();
		productPage = new ProductPage();
		homePage = new HomePage();
		searchPage = new SearchPage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		Log.info("1");
		searchPage.validateSeachFunctionalityForShopTab("pant","Mouse","Search Page");
		Log.info("2");
		searchPage.ShopProducts();
		Log.info("3");
		checkoutPage.selectAProduct();
		Log.info("4");
		checkoutPage.availabiltyStock();
		Log.info("5");
		checkoutPage.CheckoutPageStatus();
		Log.info("6");
		checkoutPage.contactInfoGuest();
		Log.info("7");
		checkoutPage.shippingAddressGuest();
		Log.info("8");
		checkoutPage.quantityVerification();
		Log.info("9");
		checkoutPage.shippingMethod();
		Log.info("10");
		
		
		
		Log.endTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Ends---------");
	}
	
	@Test(dataProvider = "CheckoutPage1", dataProviderClass = DataProviders.class, enabled = false, groups = "LoggedIn")
	public void CheckoutPage_LoggedUseraddItemTotheCartforShopTab(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("----CheckoutPage_LoggedUseraddItemTotheCartforShopTab    Starts---------");
		checkoutPage = new CheckoutPage();
		productPage = new ProductPage();
		homePage = new HomePage();
		loginPage = new LoginPage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		loginPage.loginSetup1(prop.getProperty("Username"), prop.getProperty("Password"));
		homePage.onlyNewsLetter();
		searchPage.validateSeachFunctionalityForShopTab("jojoba","Mouse","Search Page");
		searchPage.ShopProducts();
		checkoutPage.selectAProduct();
		checkoutPage.availabiltyStock();
		checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoLogged();
		checkoutPage.shippingAddressLogged();
		checkoutPage.quantityVerification();
		checkoutPage.shippingMethod();
		
		
		
		Log.endTestCase("-----CheckoutPage_LoggedUseraddItemTotheCartforShopTab    Ends---------");
	}
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CheckoutPage_GuestUseraddItemTotheCartForArticleTab(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Starts---------");
		checkoutPage = new CheckoutPage();
		homePage = new HomePage();
		launchApp_EB(browser, prop.getProperty("HomePageurl"));
		homePage.newsletterPopupAlert();
		checkoutPage.SearchFunctionalityForArticleTab("Baring the Bra: ","Keyboard","Search Page");
		checkoutPage.articlesPresence();
		Log.endTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Ends---------");
	}
	
	
	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	public void CheckoutPage_PageElements(String page, String title, String browser, String
	  url) throws InterruptedException {
		Log.startTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Starts---------"); 
		checkoutPage = new CheckoutPage(); 
		launchApp_V1(browser, url);
	  //checkoutPage.SearchFunctionalityForArticleTab("aloe","Keyboard","Search Page");
		//checkoutPage.CheckoutPageStatus(); 
		checkoutPage.contactInfoGuest();
		Log.endTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Ends---------"); 
		}
	
	

	@Test(dataProvider = "CheckoutPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	public void CheckoutPage_Shipping(String page, String title, String browser, String
	  url) throws InterruptedException {
		Log.startTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Starts---------"); 
		checkoutPage = new CheckoutPage(); 
		launchApp_V1(browser, url); 
		checkoutPage.contactInfoGuest();
		checkoutPage.shippingAddressGuest();
		//checkoutPage.shippingMethod();
		Log.endTestCase("-----------CheckoutPage_GuestUseraddItemTotheCart    Ends---------"); 
		}
	 
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void tearDown() {
		getDriver().close();
	}

}
