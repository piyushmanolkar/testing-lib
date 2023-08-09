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
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;

public class CategoryPageTest  extends BaseClass {
	LoginPage loginPage;
	RecipePageFinal recipePage;
	CategoryPage categoryPage;
	CommonPagedetails commonPagedetails;
	
	/*
	 * String category = "//div[@class='css-1hvic5s']/div/button"; String welcomeTxt
	 * = "(//p[@class='MuiTypography-root MuiTypography-body1 css-k1juyd'])[1]";
	 * String accessTxt
	 * ="(//p[@class='MuiTypography-root MuiTypography-body1 css-1yt7wtf'])[1]";
	 * String loginTxt = "(//button[normalize-space()='LOGIN/SIGNUP'])[1]"; String
	 * categoryElements = "//div[@class='MuiBox-root css-1y4n82h']/button"; String
	 * Author =
	 * "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 css-1r6qczh']"
	 * ; String P_Date =
	 * "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 css-3odfiv']"
	 * ; String bd_Home = "(//li[@class='MuiBreadcrumbs-li'])/a"; String
	 * loginPageTxt = "//input[@placeholder='Email']"; String likeIcon =
	 * "(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-fwkm60'])[1]"
	 * ;
	 */
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_TitleVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("CategoryPage_TitleVerification");
		
		Log.startTestCase("TitleVerification");
		recipePage = new RecipePageFinal();
		commonPagedetails = new CommonPagedetails();
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		String actualTitle = commonPagedetails.getTitle();
		Assert.assertEquals(actualTitle, title, "Title Not Verified");
		Log.endTestCase("TitleVerification");
		
		Log.startTestCase("PageHeaderVerification");
		Log.info("-----------Logo Verification Starts----------");
		boolean logoResult = commonPagedetails.valaidateLogo();
		Assert.assertTrue(logoResult, "Logo is not found");
		Log.info("-----------Logo Verification End successfully----------");
		Log.info("-----------SearchBox Verification Starts----------");
		boolean searchResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(searchResult, "SearchBox is not present");
		Log.info("-----------SearchBox Verification End Successfully----------");
		Log.info("-----------Cartbutton Verification Starts----------");
		boolean cartResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(cartResult, "CartButton is not present");
		Log.info("-----------Cartbutton Verification End Successfully----------");
		Log.info("-----------Userbutton Verification Starts----------");
		boolean userResult = commonPagedetails.validateSearchBox();
		Assert.assertTrue(userResult, "User icon is not present");
		Log.info("-----------Userbutton Verification End Successfully----------");
		Log.endTestCase("PageHeaderVerification");
		
	
		Log.endTestCase("CategoryPage_TitleVerification");
	}
	
	 @Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class,enabled = true, groups = "NotLoggedIn") 
		public void CategoryPage_UserFunctionalityVerification(String page, String title, String browser, String url) throws InterruptedException {

			Log.startTestCase("-----------CategoryPage_UserFunctionalityVerification    Starts---------");
			commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.UserButtonFunctionality(title);
			Log.endTestCase("-----------CategoryPage_UserFunctionalityVerification    Ends---------");
	 }
		
	 
	 @Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
		public void CategoryPage_LogoFunctionalityVerification (String page, String title, String browser, String url) throws InterruptedException {
			Log.startTestCase("-----------CategoryPage_LogoFunctionalityVerification    Starts---------");
			commonPagedetails = new CommonPagedetails();
			launchApp_V1(browser, url);
			commonPagedetails.logoFunctionality(title);
			Log.endTestCase("-----------CategoryPage_LogoFunctionalityVerification    Ends---------");
	 }
	 
	
		@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
		public void CategoryPage_SearchFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
			
			 	Log.startTestCase("-----------CategoryPage_SearchFucntionalityVerification    Starts---------");
			 	commonPagedetails = new CommonPagedetails();
				launchApp_V1(browser, url);
				commonPagedetails.validateSeachFunctionality("Black","Keyboard",title);
				Log.info("SearchFucntionality Works perfectly");
				Log.endTestCase("-----------CategoryPage_SearchFucntionalityVerification    Ends---------");
		}
		 
	
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_categoryElements(String page, String title, String browser, String url) throws InterruptedException {

		Log.startTestCase("ProductPage_categoryElements....RecipePage3");
		Log.info("Verfying the Category List");
		recipePage = new RecipePageFinal();
		launchApp_V1(browser, url);
		//WebElement login = getDriver().findElement(By.xpath(category));
		//Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		recipePage.CategoryListVerification(browser);
		Log.endTestCase("-----------ProductPage_categoryElements    Ends---------");
	}
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	  public void imageVerification(String page, String title, String browser, String url) throws InterruptedException {
		
	  Log.startTestCase("-----------imageVerification    Starts---------");
	  categoryPage = new CategoryPage(); launchApp_V1(browser, url);
	  categoryPage.imageVerification();
	  Log.endTestCase("-----------imageVerification    Ends---------"); }
	 
	 
	
	
		@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void tagsVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------tagsVerification    Starts---------");
		recipePage = new RecipePageFinal();
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		categoryPage.tagList();
		Log.endTestCase("-----------tagsVerification    Ends---------");
	}
	 
	String bd_Home = "(//li[@class='MuiBreadcrumbs-li'])/a";
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void BreadCrumbVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------BreadCrumbVerification    Starts---------");
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		String breadcrumbCategory = categoryPage.breadCrumbFunctionality();
		System.out.println(breadcrumbCategory);
		//Assert.assertEquals(breadcrumbCategory, categoryName);
		getDriver().findElement(By.xpath(bd_Home)).click();
		Action.explicitWaitbyTitle(getDriver(), "dot beauty", Duration.ofSeconds(10));
		String HomeTitle = getDriver().getTitle();
		Assert.assertEquals(HomeTitle, "dot beauty");
		Log.endTestCase("-----------BreadCrumbVerification    Ends---------");

	}
	 
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void subTabsVerification(String page, String title, String browser, String url) {
		Log.startTestCase("-----------subTabsVerification    Starts---------");
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		categoryPage.subTabsverification();
		Log.endTestCase("-----------subTabsVerification    Ends---------");
	}

	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_NewArrivalSection(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CategoryPage_NewArrivalSection    Starts---------");
		categoryPage = new CategoryPage();
		recipePage = new RecipePageFinal();
		launchApp_V1(browser, url);
		categoryPage.newArrival();
		Log.endTestCase("-----------CategoryPage_NewArrivalSection    Ends---------");
	}
	 
	
	
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_CartFucntionalityVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CategoryPage_CartFucntionalityVerification    Starts---------");
		commonPagedetails = new CommonPagedetails();
		launchApp_V1(browser, url);
		commonPagedetails.validateCartIconFunctionality();
		Log.info("CartFucntionality Works perfectly");
		Log.endTestCase("-----------CategoryPage_CartFucntionalityVerification    Ends---------");
	}
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_subCategoriesVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CategoryPage_subCategoriesVerification    Starts---------");
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		//categoryPage.subCategories();
		categoryPage.subCategoriesVerification();
		Log.endTestCase("-----------CategoryPage_subCategoriesVerification    Ends---------");
	}
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_popularReadsVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CategoryPage_subCategoriesVerification    Starts---------");
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		categoryPage.popularReads();
		Log.endTestCase("-----------CategoryPage_subCategoriesVerification    Ends---------");
	}
	
	
	@Test(dataProvider = "CategoryPage", dataProviderClass = DataProviders.class, enabled = true, groups = "NotLoggedIn")
	public void CategoryPage_BigImageBlogVerification(String page, String title, String browser, String url) throws InterruptedException {
		Log.startTestCase("-----------CategoryPage_BigImageBlogVerification    Starts---------");
		categoryPage = new CategoryPage();
		launchApp_V1(browser, url);
		categoryPage.bigImageBlog();
		Log.endTestCase("-----------CategoryPage_BigImageBlogVerification    Ends---------");
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	
	@AfterMethod(groups = {"LoggedIn","NotLoggedIn"})
	public void teardown() {
		getDriver().close();
	}
}
