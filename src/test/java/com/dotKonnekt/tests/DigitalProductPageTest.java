package com.dotKonnekt.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.dataProviders.DataProviders;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.CommonUtils;
import com.dotKonnekt.pages.DigitalProductPage;
import com.dotKonnekt.pages.ProductPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;

public class DigitalProductPageTest extends BaseClass{
	
CommonUtils commonUtils;
RecipePageFinal recipePage;
CommonPagedetails commonPagedetails;
ProductPage productPage;
DigitalProductPage digitalProduct;


String category = "//div[@class='css-1hvic5s']/div/button";
String bd_Home = "(//li[@class='MuiBreadcrumbs-li'])/a";


	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_TitleVerification(String page, String title, String browser, String url) throws InterruptedException {
		
		
		Log.startTestCase("ProductPage_TitleVerification");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		String actualTitle = commonPagedetails.getTitle();
		Assert.assertEquals(actualTitle, title, "Title Not Verified");
		Log.endTestCase("ProductPage_TitleVerification");
	}
		
		
	
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
		public void DigitalProductPage_UserFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

			Log.startTestCase("-----------ProductPage_UserFunctionalityVerification    Starts---------");
			commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.UserButtonFunctionality(title);
			Log.endTestCase("-----------ProductPage_UserFunctionalityVerification    Ends---------");
	 }
	 
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
		public void DigitalProductPage_categoryElements(String page, String title, String browser, String url) throws InterruptedException {

		 	Log.startTestCase("ProductPage_categoryElements....RecipePage3");
			Log.info("Verfying the Category List");
			recipePage = new RecipePageFinal();
			launchApp_V1(browser, url);
			//WebElement login = getDriver().findElement(By.xpath(category));
			//Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
			recipePage.CategoryListVerification(browser);	
			Log.endTestCase("-----------ProductPage_categoryElements    Ends---------");
	 }
	
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	public void DigitalProductPage_SearchFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		 Log.startTestCase("-----------ProductPage_SearchFucntionalityVerification    Starts---------");
		 	commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.validateSeachFunctionality("Black","Keyboard",title);
			Log.info("SearchFucntionality Works perfectly");
			Log.endTestCase("-----------ProductPage_SearchFucntionalityVerification    Ends---------");
	}
	 
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
		public void DigitalProductPage_CartFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
			Log.startTestCase("-----------ProductPage_CartFucntionalityVerification    Starts---------");
			commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.validateCartIconFunctionality();
			Log.info("CartFucntionality Works perfectly");
			Log.endTestCase("-----------ProductPage_CartFucntionalityVerification    Ends---------");
		}
		
	 
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
		public void DigitalProductPage_LogoFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

			Log.startTestCase("-----------ProductPage_LogoFunctionalityVerification    Starts---------");
			commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.logoFunctionality(title);
			Log.endTestCase("-----------ProductPage_LogoFunctionalityVerification    Ends---------");
	 }
	 
	 
	 @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
		public void DigitalProductPage_BreadCrumbVerification(String page, String title, String browser, String url) throws InterruptedException {

		 Log.startTestCase("-----------ProductPage_BreadCrumbVerification    Starts---------");
		 recipePage = new RecipePageFinal();
		 launchApp_V1(browser, url);
		 recipePage.breadCrumbFunctionality(title);
		 Log.endTestCase("-----------ProductPage_BreadCrumbVerification    Ends---------");
	 }
	
	
	// Image verification and product name verification are happening in this function.
	
	  @Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
	  public void DigitalProductPage_ProductDetailVerification(String page, String title, String browser, String url) throws InterruptedException {
		  Log.startTestCase("ProductPage_ProductDetailVerification");
		  digitalProduct = new DigitalProductPage();
		  launchApp_V1(browser, url);
		  digitalProduct.productDetailVerification();
		  Log.endTestCase("ProductPage_ProductDetailVerification"); }
	 
	
	// Add To Cart button verification and default count of quantity verification
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_ProductAddtoCartVerification(String page, String title, String browser, String url) throws InterruptedException {
		
		Log.startTestCase("ProductPage_ProductAddtoCartVerification");
		 digitalProduct = new DigitalProductPage();
		launchApp_V1(browser, url);
		digitalProduct.availabiltyStock();
		Log.endTestCase("ProductPage_ProductAddtoCartVerification");
	}
	
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_WishlistIconfunctionality(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_WishlistIconfunctionality");
		
		productPage = new ProductPage();
		launchApp_V1(browser, url);
		productPage.wishlistIconFunctionalityforNotLoggedIn();
		Log.endTestCase("ProductPage_WishlistIconfunctionality");
	}
	
	/*
	 * @Test(dataProvider = "DigitalProduct", dataProviderClass =
	 * DataProviders.class, enabled = true, groups = "NotLoggedIn") public void
	 * DigitalProductPage_ReadMorefunctionality(String page, String title, String
	 * browser, String url) {
	 * Log.startTestCase("ProductPage_ReadMorefunctionality");
	 * 
	 * productPage = new ProductPage(); launchApp_V1(browser, url);
	 * productPage.readMoreFunctionality();
	 * Log.endTestCase("ProductPage_ReadMorefunctionality"); }
	 */
	
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_weFoundOtherProducts(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_weFoundOtherProducts");
		
		productPage = new ProductPage();
		launchApp_V1(browser, url);
		productPage.wefoundOtherSections();
		Log.endTestCase("ProductPage_weFoundOtherProducts");
	}
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_FAQSections(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_FAQSections");
		
		productPage = new ProductPage();
		launchApp_V1(browser, url);
		productPage.faqSections();
		Log.endTestCase("ProductPage_FAQSections");
	}
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_weFoundOtherContentYouMightLike(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_weFoundOtherContentYouMightLike");
		productPage = new ProductPage();
		digitalProduct = new DigitalProductPage();
		launchApp_V1(browser, url);
		productPage.weFoundOtherContentYouMightLike();
		Log.endTestCase("ProductPage_weFoundOtherContentYouMightLike");
	}
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_ProductElements(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_ProductElements");
		
		productPage = new ProductPage();
		launchApp_V1(browser, url);
		productPage.productElementsVerification();
		Log.endTestCase("ProductPage_ProductElements");
	}
	
	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void DigitalProductPage_courseHighlightSection(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("DigitalProductPage_courseHighlightSection");
		
		digitalProduct = new DigitalProductPage();
		launchApp_V1(browser, url);
		digitalProduct.CourseHighlightSections();
		Log.endTestCase("DigitalProductPage_courseHighlightSection");
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Test(dataProvider = "DigitalProduct", dataProviderClass = DataProviders.class, enabled = false, groups = "LoggedIn")
	public void DigitalProductPage_WishlistIconfunctionality1(String page, String title, String browser, String url) throws InterruptedException {
	Log.startTestCase("ProductPage_WishlistIconfunctionality1");
		
		productPage = new ProductPage();
		launchApp_V1(browser, url);
		productPage.wishlistIconFunctionalityForLoggedIn();
		Log.endTestCase("ProductPage_WishlistIconfunctionality1");
	}
	
	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void tearDown() {
		getDriver().close();
	}
}
