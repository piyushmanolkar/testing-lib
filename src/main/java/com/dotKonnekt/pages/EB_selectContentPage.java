package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class EB_selectContentPage extends BaseClass {

	public EB_selectContentPage() {
		PageFactory.initElements(getDriver(), this);
	}
	CommonPagedetails commonPagedetails;
	ProductPage productPage;
	RecipePageFinal recipePage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	HomePage homePage;
	
	SoftAssert softAssert = new SoftAssert();

	String continuebtn = "//span[@class='MuiButton-label']";

	String eb_button = "//span[normalize-space()='Experience Builder']";
	String userEmail = "emailId";
	String password1 = "";
	
	String logo =  "//div[@class='MuiBox-root jss21']/img";
	String forgotPassword= "//a[normalize-space()='Forgot Password?']";
	String remeberMe = "//label[@id='demo-form-control-label-placement']";
	String letsText = "//p[@class='MuiTypography-root jss5 MuiTypography-body1']";
	public void ebLoginPage() {
		String pageTitle  = getDriver().getTitle();
		Assert.assertEquals(pageTitle, "Sangria Command Center");
		Log.info("successfully verified the Title of the Experience builder");
		WebElement LetsText = getDriver().findElement(By.xpath(letsText));
		Assert.assertEquals(LetsText.getText(), "Let's get started", "Let's get started is not similar");
		Log.info("successfully verified the Let's get started of the Experience builder");
		WebElement RememberMe = getDriver().findElement(By.xpath(remeberMe));
		Assert.assertEquals(RememberMe.getText(), "Remember Me", "Remember Me is not similar");
		Log.info("successfully verified the presence of Remember me of the Experience builder Login Page");
		WebElement ForgotPassword = getDriver().findElement(By.xpath(forgotPassword));
		Assert.assertEquals(ForgotPassword.getText(), "Forgot Password?", "Forgot Password? is not similar");
		Log.info("Forgot Passsword is succssfully verified ");
		
		
		WebElement Logo = getDriver().findElement(By.xpath(logo));
		Assert.assertTrue(Logo.isDisplayed(), "Logo is not present on the Login Page");
		Log.info("Forgot Passsword is succssfully verified ");
		

	}

	public void EB_login() throws InterruptedException {
		Log.info("Login of Expeience Builder Started Successfully");
		System.out.println("fdghjkl;'");
		WebElement UserEmail = getDriver().findElement(By.xpath("//input[@id='emailId']"));
		Action.type(UserEmail, "dotkonnektdev@gmail.com");
		WebElement UserPassword = getDriver().findElement(By.id("password"));

		Action.type(UserPassword, "Dotkonnekt*123");
		Thread.sleep(25000);

		WebElement continueBtn = getDriver().findElement(By.xpath(continuebtn));
		Action.click(getDriver(), continueBtn);
		Thread.sleep(2000);
		Action.explicitWaitbyTitle(getDriver(), "Sangria Command Center", Duration.ofSeconds(10));
		WebElement eb_btn = getDriver().findElement(By.xpath(eb_button));
		Action.click(getDriver(), eb_btn);
		Thread.sleep(2000);
		Log.info("Successfully Clicked on the Experience Builder button");

	}

	String noResultTxt = "//div[@class='MuiBox-root jss129']/p";
	String searchIcon = "//button[@aria-label='Search']//span[@class='MuiIconButton-label']";
	String searchBar = "//input[@placeholder='Search content, product or community']";

	public void EB_UniversalSearch(String searchTxt, String ClickType) {

		WebElement SearchBar = getDriver().findElement(By.xpath(searchBar));
		Action.click(getDriver(), SearchBar);
		Action.type(SearchBar, searchTxt);
		Log.info("Successfully Entered the value in the search bar");
		if (ClickType.equalsIgnoreCase("mouse")) {
			WebElement SearchIcon = getDriver().findElement(By.xpath(searchIcon));
			Action.click(getDriver(), SearchIcon);
			Log.info("Successfully Clicked the Search icon");
		} else if (ClickType.equalsIgnoreCase("keyboard")) {
			getDriver().findElement(By.xpath(searchIcon)).sendKeys(Keys.ENTER);
			Log.info("Successfully clicked the Enter to key to search the keyword");
		}

		WebElement NoResultTxt = getDriver().findElement(By.xpath(noResultTxt));
		System.out.println(NoResultTxt.getText());
		Assert.assertEquals(NoResultTxt.getText(), "No results found", "No Result found message is not visible");
		Log.info("Successfully tested the No Result Found Scenario");
	}

	public void NoResultFound() throws InterruptedException {
		System.out.println("dfghjk");
		Thread.sleep(1000);
		WebElement NoResultTxt = getDriver().findElement(By.xpath(noResultTxt));
		Assert.assertEquals(NoResultTxt.getText(), "No results found", "No Result found message is not visible");
		Log.info("Successfully tested the No Result Found Scenario");
	}

	String recipesCheckbox = "//input[@name='Recipes']";
	String blogCheckbox = "//input[@name='Self Cure Blogs']";
	String filterBy = "//p[@class='MuiTypography-root jss296 MuiTypography-body1']";
	String Engage = "//span[normalize-space()='Engage Customer']";
	String addProduct = "//span[normalize-space()='Add Product']";
	String selectContent = "//span[normalize-space()='Select Content']";
	String buildText = "//p[@class='MuiTypography-root jss276 MuiTypography-body1']";

	public void buildAnExperienceBoX() throws InterruptedException {

		WebElement BuildText = getDriver().findElement(By.xpath(buildText));
		Assert.assertEquals(BuildText.getText(), "Let's Build an Experience", "Text is not Equal");
		Log.info("Successfully verified the build text");

		WebElement SearchBar = getDriver().findElement(By.xpath(searchBar));
		Assert.assertTrue(SearchBar.isDisplayed());
		Log.info("Successfully verified the Search box");

		WebElement SelectContent = getDriver().findElement(By.xpath(selectContent));
		Assert.assertTrue(SelectContent.isDisplayed());
		Log.info("Successfully verified the SearchContent");

		WebElement AddProductTab = getDriver().findElement(By.xpath(addProduct));
		Assert.assertTrue(AddProductTab.isDisplayed());
		Log.info("Successfully verified the AddProductTab");

		WebElement EngageTab = getDriver().findElement(By.xpath(Engage));
		Assert.assertTrue(EngageTab.isDisplayed());
		Log.info("Successfully verified the EngageTab");

		WebElement filterByOption = getDriver().findElement(By.xpath(filterBy));
		Assert.assertTrue(filterByOption.isDisplayed());
		Log.info("Successfully verified the filterByOption");

		Thread.sleep(1000);
		WebElement BlogCheckbox = getDriver().findElement(By.xpath(blogCheckbox));
		Assert.assertTrue(!BlogCheckbox.isSelected(), "BlogCheckbox is not present");
		Log.info("Successfully verified the BlogCheckbox");

		WebElement RecipesCheckbox = getDriver().findElement(By.xpath(recipesCheckbox));
		Assert.assertTrue(!RecipesCheckbox.isSelected());
		Log.info("Successfully verified the RecipesCheckbox");

	}

	

	String selectBtn = "(//div[@class='MuiCardActions-root MuiCardActions-spacing']//div[@role='button'])";
	String totalProducts = "//p[@class='MuiTypography-root jss284 MuiTypography-body1']";
	String allproduct = "//div[@class='MuiBox-root jss315 jss275']/div[1]/div/div/div/div";
	String images = "//button[@class='MuiButtonBase-root MuiCardActionArea-root']//div[1]";
	String productName = "//div[@class='MuiBox-root jss315 jss275']/div[1]/div/div/div/div/button/div[2]/h2";

	public void ContentTileVerification() {

		List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
		int n = products1.size();
		System.out.println("Total Products on blog Page are " + n);

		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int x = image.size();
		int count4 = 0;
		System.out.println(x + " ProductImage should be present");
		if (x == n) {
			for (WebElement i : image) {
				Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.getAttribute("style").contains("drupal")) {
					count4++;
				}
			}
			// System.out.println(count4);
			if (count4 == n) {
				System.out.println("All " + count4 + " images are present");
			} else {
				softAssert.assertTrue(false, n - count4 + " Images are not present  ");
			}
		} else {
			System.out.println(n - x + " Images are not present ");
			softAssert.assertTrue(false, +n - x + " Images are not present ");
		}

		List<WebElement> productName1 = getDriver().findElements(By.xpath(productName));
		int j = productName1.size();
		int count1 = 0;
		System.out.println(j + " ProductName should be present");
		if (j == n) {
			for (WebElement i : productName1) {
				// Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.isDisplayed()) {
					//Log.info("ProductName is successfully verified");
					count1++;
				}
			}
			if (count1 == n) {
				System.out.println("All Products name are present");
			} else {
				softAssert.assertTrue(false, n - count1 + " Products name are not present  ");
			}
		} else {
			System.out.println(n - j + " Products name are not present ");
			softAssert.assertTrue(false, +n - j + " Products name are not present ");
		}

		List<WebElement> SelectBtn = getDriver().findElements(By.xpath(selectBtn));
		int z = SelectBtn.size();
		int count2 = 0;
		System.out.println(z + " SelectBtn  should be present");
		if (z == n) {
			for (WebElement i : SelectBtn) {
				// Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.isDisplayed()) {
					//Log.info("SelectBtn is successfully verified");
					count2++;
				}
			}
			if (count2 == n) {
				System.out.println("All SelectBtn are present");
			} else {
				softAssert.assertTrue(false, n - count2 + " SelectBtn are not present  ");
			}
		} else {
			System.out.println(n - z + " SelectBtn are not present ");
			softAssert.assertTrue(false, n - z + " SelectBtn are not present ");
		}

		softAssert.assertAll();

	}
	String elementsName = "//div[@id='root']//ul[2]/a/div[2]/span";
	String elementsIcon=  "//div[@id='root']//ul[2]/a/div[1]/img";
	public void leftMenus() {
		List<WebElement> ElementIcons=  getDriver().findElements(By.xpath(elementsIcon));
		int  z= ElementIcons.size();
		int n =6;
		int count2 = 0;
		System.out.println(z + " ElementIcons  should be present");
		if (z == n) {
			for (WebElement i : ElementIcons) {
				// Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.isDisplayed()) {
					//Log.info("SelectBtn is successfully verified");
					count2++;
				}
			}
			if (count2 == n) {
				System.out.println("All ElementIcons are present");
			} else {
				softAssert.assertTrue(false, n - count2 + " ElementIcons are not present  ");
			}
		} else {
			System.out.println(n - z + " ElementIcons are not present ");
			softAssert.assertTrue(false, n - z + " ElementIcons are not present ");
		}
		
		
		
		List<WebElement> ElementsName = getDriver().findElements(By.xpath(elementsName));
		int j = ElementsName.size();
		int count1 = 0;
		int n1 =7;
		System.out.println(j + " ElementsName should be present");
		if (j == n1) {
			for (WebElement i : ElementsName) {
				// Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.isDisplayed()) {
					//Log.info("ProductName is successfully verified");
					count1++;
				}
			}
			if (count1 == n) {
				System.out.println("All ElementsName name are present");
			} else {
				softAssert.assertTrue(false, n1 - count1 + " ElementsName name are not present  ");
			}
		} else {
			System.out.println(n - j + " ElementsName name are not present ");
			softAssert.assertTrue(false, +n1 - j + " ElementsName name are not present ");
		}
		
		
		int count =0;
		List<String> listElements = new ArrayList<String>();
		for (WebElement webElement : ElementsName) {
			listElements.add(webElement.getText());
			count++;
		}
		System.out.println(listElements);
		
		String category = "Analytics,Experience Builder,Apps,Orders,Products,Customers,Help";
		 List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
		 System.out.println(myList);
		 if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
				Log.info("Successfully verified that all the Left Menus  elements are in correct form");
			}
			else {
				Assert.assertTrue(false, " Left Menus elements are not in correct form");
			}
		  if (count != 0) { if (count == ElementsName.size())
		  { System.out.println(" Left Menus elements are equal"); } else {
		  System.out.println(" Left Menus elements are not equal");
		  Assert.assertTrue(false); } } else {
		  System.out.println(" Left Menus are not present"); }
		
	}
	
	
	String contentTitle = "//div[@class='MuiBox-root jss233']/span/div/div[1]/p";
	String dropContentImage = "//div[@class='MuiBox-root jss233']/span/div/div[1]/img";
	String embedIcon= "//span[@class='MuiBadge-root']//*[name()='svg']";
	String embedcount = "(//div[@class='MuiBox-root jss315 jss275']/div[1]/div/div/div/div/div/div[2]/div/span/span)";
	public void Search(String searchTxt, String ClickType) throws InterruptedException {
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
		int n = products1.size();
		System.out.println("Total Content after search are " + n);
		
		
		List<WebElement> SelectBtn = getDriver().findElements(By.xpath(selectBtn));
		int z = SelectBtn.size();
		int count2 = 0;
		System.out.println(z + " SelectBtn  should be present");
		if (z == n) {
			for (WebElement i : SelectBtn) {
				// Action.scrollByVisibilityOfElement(getDriver(), i);
				if (i.isDisplayed()) {
					//Log.info("SelectBtn is successfully verified");
					count2++;
				}
			}
			if (count2 == n) {
				System.out.println("All SelectBtn are present");
			} else {
				softAssert.assertTrue(false, n - count2 + " SelectBtn are not present  ");
			}
		} else {
			System.out.println(n - z + " SelectBtn are not present ");
			softAssert.assertTrue(false, n - z + " SelectBtn are not present ");
		}
		
		
		Random r = new Random(); 
		  int randomValue = r.nextInt(SelectBtn.size()); //Getting a random value that is between 0 and (list's size)-1
		  System.out.println(randomValue);
		  
		  String ele1 =  selectBtn + "[" + randomValue + "]";
		if(randomValue>0) {  
		  WebElement ELe1 = getDriver().findElement(By.xpath(ele1));
		  Action.click(getDriver(), ELe1);
		  String pCount = embedcount + "["+randomValue+"]";
		  
		  WebElement count =  getDriver().findElement(By.xpath(pCount));
		  System.out.println("Total Product embedded for this product = " + count.getText());
		  
		  
		  WebElement PreviewBtn = getDriver().findElement(By.xpath(previewBtn));
		  Action.click(getDriver(), PreviewBtn);
		  Thread.sleep(2000);
		  Log.info("Successfully clicked on the Preview button");
		  
		  WebElement frame  = getDriver().findElement(By.xpath("//iframe[@title='sitePreview']"));
		  getDriver().switchTo().frame(frame);
		}
		else {
			System.out.println("selected random value is zero");
		}
		
		
	}
	
	String cancelBtn = "//span[normalize-space()='CANCEL']"; 
	String previewBtn = "//span[normalize-space()='PREVIEW']";
	String publishBtn = "//span[normalize-space()='PUBLISH']";
	String enagementBox = "//p[normalize-space()='DROP COMMUNITY HERE']";
	String enagagment = "//p[normalize-space()='ENGAGEMENT']";
	String productBox = "//p[normalize-space()='DROP PRODUCT(S) HERE']";
	String products = "//p[normalize-space()='PRODUCTS']";
	String content = "//p[normalize-space()='CONTENT']";
	String text = "//h2[normalize-space()='Preview the Magic']";
	String dropcontentBox = "//p[normalize-space()='DROP CONTENT HERE']";
	public void previewTheMagicSection() {
		WebElement Text = getDriver().findElement(By.xpath(text));
		Assert.assertEquals(Text.getText(), "Preview the Magic", "Text is not Equal");
		Log.info("Successfully Verified the presence of the Preview the Magic section");
		
		WebElement Content = getDriver().findElement(By.xpath(content));
		Assert.assertEquals(Content.getText(), "CONTENT", "CONTENT is not Equal");
		Log.info("Successfully Verified the presence of the CONTENT box");
		
		WebElement DropcontentBox = getDriver().findElement(By.xpath(dropcontentBox));
		Assert.assertEquals(DropcontentBox.getText(), "DROP CONTENT HERE", "DROP CONTENT HERE is not Equal");
		Log.info("Successfully Verified the presence of the DROP CONTENT HERE box");
		
		WebElement Products = getDriver().findElement(By.xpath(products));
		Assert.assertEquals(Products.getText(), "PRODUCTS", "Products is not Equal");
		Log.info("Successfully Verified the presence of the Products box");
		
		WebElement ProductBox = getDriver().findElement(By.xpath(productBox));
		Assert.assertEquals(ProductBox.getText(), "DROP PRODUCT(S) HERE", "DROP PRODUCT(S) HERE is not Equal");
		Log.info("Successfully Verified the presence of the DROP PRODUCT(S) HERE box");
		
		WebElement Enagagment = getDriver().findElement(By.xpath(enagagment));
		Assert.assertEquals(Enagagment.getText(), "ENGAGEMENT", "ENGAGEMENT is not Equal");
		Log.info("Successfully Verified the presence of the ENGAGEMENT box");
		
		WebElement EnagementBox = getDriver().findElement(By.xpath(enagementBox));
		Assert.assertEquals(EnagementBox.getText(), "DROP COMMUNITY HERE", "DROP COMMUNITY HERE is not Equal");
		Log.info("Successfully Verified the presence of the DROP COMMUNITY HERE box");
		
		WebElement PublishBtn = getDriver().findElement(By.xpath(publishBtn));
		Assert.assertEquals(PublishBtn.getText(), "PUBLISH", "PUBLISH text is not Equal");
		Log.info("Successfully Verified the presence of thePublishBtn");
		
		WebElement PreviewBtn = getDriver().findElement(By.xpath(previewBtn));
		Assert.assertEquals(PreviewBtn.getText(), "PREVIEW", "PUBLISH text is not Equal");
		Log.info("Successfully Verified the presence of thePublishBtn");
		
		WebElement CancelBtn = getDriver().findElement(By.xpath(cancelBtn));
		Assert.assertEquals(CancelBtn.getText(), "CANCEL", "PUBLISH text is not Equal");
		Log.info("Successfully Verified the presence of thePublishBtn");
	}
	
	
	String filterBy1 = "//p[normalize-space()='Filter by:']";
	String addProducts = "//span[normalize-space()='Add Product']";
	public void addProductsSection() {
		
		WebElement AddProducts = getDriver().findElement(By.xpath(addProducts));
		Assert.assertEquals(AddProducts.getText(), "Add Product", "Add Product is not Equal");
		Log.info("Successfully Verified the presence of the Add Product");
		
		Action.click(getDriver(), AddProducts);
		
		WebElement FilterBy1 = getDriver().findElement(By.xpath(filterBy1));
		Assert.assertEquals(FilterBy1.getText(), "Filter by:", "Add Product is not Equal");
		Log.info("Successfully Verified the presence of the Add Product");

	}
	
	
	
	String community = "//div[@class='MuiBox-root jss501 jss203']/div";
	String engageCustomer = "//span[normalize-space()='Engage Customer']";
	public void engageSection() {
		
		WebElement EngageCustomer = getDriver().findElement(By.xpath(engageCustomer));
		Assert.assertEquals(EngageCustomer.getText(), "Engage Customer", "Add Product is not Equal");
		Log.info("Successfully Verified the presence of the Engage Customer Section");
		
		Action.click(getDriver(), EngageCustomer);
		
		List<WebElement> Community = getDriver().findElements(By.xpath(community));
		int n = Community.size();
		System.out.println("Total Community are " + n);
		Assert.assertEquals(n, 3, "All 3 Community are not present on the Page");
		
		
		
		
		
		
		
		

	}

}

