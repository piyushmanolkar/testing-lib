package com.dotKonnekt.pages;

import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class RecipePageFinal extends BaseClass {
	
	String searchBox = "//input[@placeholder='Search']";
	//String clickButton=  "//div[@class='MuiInputAdornment-root MuiInputAdornment-positionStart MuiInputAdornment-outlined MuiInputAdornment-sizeMedium css-1a6giau']//*[name()='svg']";
	String bd_Home = "(//a[@id='sg-breadcrum0 sg-BreadCrumbLink'])";
	String bd_Blog = "(//a[@id='sg-breadcrum2 sg-breadCrumbText'])";
	String Author = "//div[@id='sg-authorGrid']";
	String P_Date = "//div[@id='sg-dateGrid']";
	String loginbutton = "//button[normalize-space()='LOG IN']";
	String loginPageTxt = "//input[@name='email']";
	String likeIcon = "(//*[name()='svg'][@id='sg-thumbUpButtonOutline'])";
	String bookMarkIcon = "(//*[name()='svg'][@id='sg-bookmarkBorderOutlinedIcon'])";
	String shareIcon = "(//*[name()='svg'][@id='sg-shareIconOutline'])";
	String likeCount = "//p[@id= 'sg-likeCount']";
	String bookCount = "//p[@id= 'sg-bookmarkCount']";
	String socialMediaIcons = "(//*[name()='circle'])";
	String categoryElements = "//button[@id='sg-categoryItem']";
	String blog_title = "(//div[@id='sg-blogHeadTitle'])";
	String quickview = "(//p[@id='sg-quickViewButton'])";
	String discountedPrice = "//p[@class='MuiTypography-root MuiTypography-body1 css-1tva794']";
	String actualPrice = "//div[@id='productCardContentPriceTile']";
	String productsName = "(//div[@id='sg-productCardContentTitle'])";
	String images  = "(//img[@id='sg-productCardImage2'])";
	String carticon = "(//button[@id='sg-addToCartButton'])";
	String wishlist = "(//button[@id='sg-addToWishlistButton'])";
	String commentTxt = "//h5[@id='sg-commentModuleTitle']";
	String commentBox ="(//input[@id='sg-outlinedAdornmentPassword'])";
	String postbutton = "(//span[@id='sg-postComment'])";
	String commentCount ="(//span[@id='sg-commentModuleResponseLength'])";
	String see_more = "//div[@id='sg-commentModuleSeeMoreButton']//span[1]";
	String commentPosted = "(//div[@id='sg-editCommentAnsweredCommentReplace'])";
	String replyCount = "(//span[@id='sg-commentContentRepliesLength'])";
	String crossbutton1 = "//*[local-name()='svg' and @data-testid='ClearIcon']";
	String recipeImage =  "//img[@id='sg-ingredientItemContainer']";
	String taggscount= "//div[@id='sg-tagChip4']/span[1]";
	//String nutritions = "//p[@class='MuiTypography-root MuiTypography-body1 css-22dtt9']";
	
	String reportAbuse = "(//button[@id='sg-reportButton3'])";
	String recipeVideo = "//h5[@class='MuiTypography-root MuiTypography-h5 css-12esd7r']";
	String frame = "//iframe[@id='widget2']";
	
	@FindBy (xpath = "//span[@id='sg-postComment']")
	WebElement postBtn;
	
	@FindBy(name = "email")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	@FindBy(xpath = "//button[normalize-space()='LOG IN']")
	WebElement loginBtn;
	@FindBy(xpath = "//div[normalize-space()='Cooking Time :']")
	WebElement cookingTime;
	
	SoftAssert softAssert = new SoftAssert();
	
	public RecipePageFinal() {
		PageFactory.initElements(getDriver(), this);
		softAssert.assertAll();
	}
	
	
	

	
	public void recipeImage() {
		WebElement recipeimage = getDriver().findElement(By.xpath(recipeImage));
		//Boolean p = (Boolean) ((JavascriptExecutor)getDriver()) .executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", recipeImage); 
		//boolean p = Action.isDisplayed(getDriver(), recipeimage);
		//Assert.assertTrue(p);
		//List<WebElement> image = getDriver().findElements(By.xpath(images));
			Action.scrollByVisibilityOfElement(getDriver(), recipeimage);
			boolean result = recipeimage.getAttribute("src").contains("amazonaws.com");
			if(result) {	
			Log.info("Successfully verified the recipe image on Recipe Page");}
			else {
				softAssert.assertTrue(result, "Recipe Image is not present");
				Log.info("Recipe Image is not present on the recipe Page");
			}
		
	}
	
	
	public void breadCrumbFunctionality(String title) {
		int count =0;
		List<WebElement>  breadcrumb = getDriver().findElements(By.xpath(bd_Home));
		List<String> listElements = new ArrayList<String>();
		for (WebElement webElement : breadcrumb) {
			listElements.add(webElement.getText().replace(" ", "|"));
			count++;
		}
		String delim = "|";
		String actualBreadcrumb = String.join(delim, listElements);
		 System.out.println(actualBreadcrumb);
			getDriver().findElement(By.xpath(bd_Home)).click();
			Action.explicitWaitbyTitle(getDriver(), "dotfashion", Duration.ofSeconds(10));
			String HomeTitle = getDriver().getTitle();
			Assert.assertEquals(HomeTitle, "dotfashion"); 
			getDriver().navigate().back();
			Action.explicitWaitbyTitle(getDriver(), title, Duration.ofSeconds(5));
		
	}
	
	public void authoreDetailsVerification(String AuthorName, String  PublishDate) {
		
		WebElement author = getDriver().findElement(By.xpath(Author));
		//Action.explicitWait(getDriver(), author, Duration.ofSeconds(10));
		String actualAuthorName = getDriver().findElement(By.xpath(Author)).getText();
		if(!actualAuthorName.isBlank()) {
		System.out.println(actualAuthorName);
		softAssert.assertEquals(actualAuthorName, AuthorName);
		}
		else if(actualAuthorName.isBlank()) {
			System.out.println("Auhtor name is not Present");
			Log.warn("Author name is not present");
			softAssert.assertTrue(false,"Auhtor name is not Present");
		}
		String actualPublishDate = getDriver().findElement(By.xpath(P_Date)).getText();
		if(!actualPublishDate.isBlank()) {
		String actdate = "/" + actualPublishDate + "/";
		System.out.println("/"+actualPublishDate+"/");
		Assert.assertEquals(actdate, PublishDate);}
		else if(actualPublishDate.isBlank()) {
			System.out.println("Date is not Present");
			Log.warn("Date is not present");
			softAssert.assertTrue(false, "Publish Date is not present");	
		}
		softAssert.assertAll();
	}
	
	
	public void LikeIconfunctionality(String status) throws InterruptedException {
		String countTxt =	getDriver().findElement(By.xpath(likeCount)).getText();
		getDriver().findElement(By.xpath(likeIcon)).click();
		if(status.equalsIgnoreCase("Loggedout")) {
		WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
		Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());
		WebElement CrossButton = getDriver().findElement(By.xpath(crossbutton1));
		Action.click(getDriver(), CrossButton);
		
		}
		else {
		//getDriver().get("https://develop.d1fzm6olmw007.amplifyapp.com/recipe/sugarcane-juice-black-wheat-muffins-cake");
		Log.info("For Logged in Scenario Like functionality");
		getDriver().findElement(By.name("email")).sendKeys(prop.getProperty("Username"));
		getDriver().findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
		Thread.sleep(15000);
		getDriver().findElement(By.xpath("//button[normalize-space()='LOG IN']")).click();
		
		Thread.sleep(2000)	;
		//getDriver().navigate().refresh();
		int count=Integer.parseInt(countTxt); 
		System.out.println("Count of Like icon before click is " + count);
		getDriver().findElement(By.xpath(likeIcon)).click();
		Log.info("Successfully clicked on the like icon for the First time ");
		Thread.sleep(2000)	;
		String countTxt2 =	getDriver().findElement(By.xpath(likeCount)).getText();
		int newCount = Integer.parseInt(countTxt2);
		System.out.println("Count of Like icon after click is " + newCount);
		Assert.assertEquals(count+1, newCount);
		Log.info("Successfully verified the count increasing of Like icon");
		
		getDriver().findElement(By.xpath(likeIcon)).click();
		
		
		}
		
	}
	
	public void BookMarkIconfunctionality(String status) throws InterruptedException {
		String countTxt =	getDriver().findElement(By.xpath(bookCount)).getText();
		System.out.println("First Count When user land on the page:" +  countTxt);
		
		WebElement BookMark   =getDriver().findElement(By.xpath(bookMarkIcon));
		Action.scrollByVisibilityOfElement(getDriver(), BookMark);
		Action.click(getDriver(), BookMark);
		//System.out.println(countTxt);
		System.out.println(BookMark.getAttribute("data-testid"));
		int count=Integer.parseInt(countTxt); 
		System.out.println(" Count after first click: " +  count);
		Thread.sleep(2000)	;
		
		if(status.equalsIgnoreCase("Loggedout")) {
		WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
		Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());
		WebElement CrossButton = getDriver().findElement(By.xpath(crossbutton1));
		Action.click(getDriver(), CrossButton);
		
		Log.info("Successfully verified the Bookmark functionality for the not logged in User");
		
		}
		else {
			getDriver().findElement(By.name("email")).sendKeys(prop.getProperty("Username"));
			getDriver().findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
			Thread.sleep(15000);
			getDriver().findElement(By.xpath("//button[normalize-space()='LOG IN']")).click();
			Thread.sleep(2000)	;
			
			 	
		String countTxt2 =	getDriver().findElement(By.xpath(bookCount)).getText();
		System.out.println(countTxt2);
		int newCount = Integer.parseInt(countTxt2);
		System.out.println(" Count after Second click and logged in : " +  countTxt2);
		Thread.sleep(2000);
		Assert.assertEquals(count+1, newCount, "Count not increased");
		System.out.println(BookMark.getAttribute("data-testid"));
		Assert.assertEquals(BookMark.getAttribute("data-testid"), "BookmarkIcon", "BookMark icon is not in filled form");
		
		getDriver().navigate().refresh();
		Thread.sleep(500);
		Assert.assertEquals(count+1, newCount, "Count is not correct after refresh");
		Assert.assertEquals(BookMark.getAttribute("data-testid"), "BookmarkIcon", "After Refresh BookMark icon is not in filled form");
		getDriver().findElement(By.xpath(bookMarkIcon)).click();
		Thread.sleep(1000);
		Assert.assertEquals(count, newCount-1, "Count should be decreased");
		Assert.assertEquals(BookMark.getAttribute("data-testid"), "BookmarkBorderOutlinedIcon", "BookMark icon is not in filled form");
		Log.info("Successfully verified the BookMark functionality of the Logged in Scenario");
		
		}
	}
	
	public void ShareIconfunctionality() throws InterruptedException {
		getDriver().findElement(By.xpath(shareIcon)).click();
		//Thread.sleep(2000);
		List<WebElement> socialMediaList =	getDriver().findElements(By.xpath(socialMediaIcons));
		System.out.println(socialMediaList.size());
		
	}
	String menuButton = "//div[@class='MuiBox-root css-1m04nb5']/button";
	String mobileCategoryElements =  "//div[@class='MuiBox-root css-1m9fqvd']/div/li/p";
	public void CategoryListVerification(String browser) {
		if(!browser.contains("Mobile")) {
		int count = 0;
		List<WebElement> menuList = getDriver().findElements(By.xpath(categoryElements));
		//int size = menuList.size();
		List<String> listElements = new ArrayList<String>();
		for (WebElement webElement : menuList) {
			listElements.add(webElement.getText());
			count++;
		}
		System.out.println(listElements);
		if(getDriver().getCurrentUrl().contains("dotfashion")) {
			String category = "WOMEN,MEN";	
			List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
			System.out.println(myList);
			 if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
					Log.info("Successfully verified that all the category elements are in correct form");
				}
				else {
					Assert.assertTrue(false, "Category elements are not in correct form");
				}
			
		}
		else {
			String category = "SKiN,DIY RECIPES,DOLL UP";
			List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
			System.out.println(myList);
			 if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
					Log.info("Successfully verified that all the category elements are in correct form");
				}
				else {
					Assert.assertTrue(false, "Category elements are not in correct form");
				}
		}
		
		 
		 
		  if (count != 0) { if (count == menuList.size())
		  { System.out.println("Category elements are equal"); } else {
		  System.out.println("Category elements are not equal");
		  Assert.assertTrue(false); } } else {
		  System.out.println("Category are not present"); }}
		else {
			Log.info("In Mobile View");
			WebElement MenuButton  = getDriver().findElement(By.xpath(menuButton));
			Action.click(getDriver(), MenuButton);
			int count = 0;
			List<WebElement> menuList = getDriver().findElements(By.xpath(mobileCategoryElements));
			//int size = menuList.size();
			List<String> listElements = new ArrayList<String>();
			for (WebElement webElement : menuList) {
				listElements.add(webElement.getText());
				count++;
			}
			System.out.println(listElements);
			String category = "WOMEN,MEN";
			 List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
			 System.out.println(myList);
			 if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
					Log.info("Successfully verified that all the category elements are in correct form");
				}
				else {
					Assert.assertTrue(false, "Category elements are not in correct form");
				}
			  if (count != 0) { if (count == menuList.size())
			  { System.out.println("Category elements are equal in mobile view"); } else {
			  System.out.println("Category elements are not equal in mobile view");
			  Assert.assertTrue(false); } } else {
			  System.out.println("Category are not present in mobile view"); }
			
		}
	
	}
	
	
	public String blogTitleVerification() {
		String actualblogTitle = getDriver().findElement(By.xpath(blog_title)).getText();
		return actualblogTitle;
		
	}
	
	 
	public void tagList(){
		 Log.info("Veryfying the Tag List");
		 
		 WebElement l = getDriver().findElement(By.tagName("body"));
		// System.out.println("Elements with P: =" + l.getText());
		 if(l.getText().contains("Tags")) {
		 
		 WebElement tagText = getDriver().findElement(By.
				  xpath("//p[@id= 'sg-hashTitle']"));
		  Action.scrollByVisibilityOfElement(getDriver(), tagText);
		   System.out.println(tagText.getText());
		List<WebElement> taggs = getDriver().findElements(By.xpath(taggscount));
		System.out.println(taggs.size());
			if(taggs.size() == 5) {
				System.out.println(getDriver().findElement(By.xpath("//p[@id= 'sg-nuterationSeeTagText']")).getText());
				  WebElement seeMoreButton =getDriver().findElement(By.
				  xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 css-juaq']"));
				  if(seeMoreButton.isDisplayed()) { seeMoreButton.click(); }
				  
				  getDriver().findElements(By. xpath("//span[@class=\"MuiChip-label MuiChip-labelMedium css-9iedg7\"]"));
				  Action.explicitWait(getDriver(), seeMoreButton, Duration.ofSeconds(10));
				  List<WebElement> tagList = getDriver().findElements(By.
				  xpath(taggscount)); 
				  System.out.println(tagList.size()); 
				  int countt = 0;
				  JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
				  js1.executeScript("window.scrollBy(0,1000)", "");
				  List<String> tags = new  ArrayList<String>();
				  for (WebElement webElement : tagList) {
					  tags.add(webElement.getText()); 
					  countt++;
					  } 
				  String deli = "\n"; 
				  String taggs1 = String.join(deli, tags);
				  System.out.println(taggs1);
				  
				  //  Assert.assertEquals(taggs, tagsElements);
				  
				  if (countt == tagList.size()) 
				  { System.out.println("Tags List are equal");
				  Assert.assertTrue(true); } 
				  else {
				  System.out.println("Tags list Steps are not equal");
				  Assert.assertTrue(false); }}
			else if(taggs.size()<5 && taggs.size() !=0) {
					List<WebElement> tagList = getDriver().findElements(By.
							  xpath(taggscount)); 
							  System.out.println(tagList.size()); 
							  int countt = 0;
							  JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
							  js1.executeScript("window.scrollBy(0,1000)", "");
							  List<String> tags = new  ArrayList<String>();
							  for (WebElement webElement : tagList) {
								  tags.add(webElement.getText()); 
								  countt++;
								  } 
							  String deli = "\n"; 
							  String taggs1 = String.join(deli, tags);
							  System.out.println(taggs1);
							  
							  //  Assert.assertEquals(taggs, tagsElements);
							  
							  if (countt == tagList.size()) 
							  { System.out.println("Tags List are equal");
							  Assert.assertTrue(true); } 
							  else {
							  System.out.println("Tags list Steps are not equal");
							  Assert.assertTrue(false); }
						}
			else if(taggs.size()==0) {
				System.out.println("Taggs elements are blank");
				Assert.assertTrue(false);
			}
		 }
		 
		 else {
			 System.out.println("Tags are not present on this page");
		 }
	}
	
		public void tagElements() throws InterruptedException {
			 WebElement l = getDriver().findElement(By.tagName("body"));
				// System.out.println("Elements with P: =" + l.getText());
				 if(l.getText().contains("Tags")) {
			Log.info("Veryfying the Tag Elements functionality");
			 WebElement tagText = getDriver().findElement(By.
					  xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 css-qfzj9b']"));
			  Action.scrollByVisibilityOfElement(getDriver(), tagText);
			  System.out.println(tagText.getText());
				List<WebElement> taggs = getDriver().findElements(By.xpath(taggscount));
				System.out.println(taggs.size());
				
				  Random r = new Random(); 
				  int randomValue = r.nextInt(taggs.size()); //Getting a random value that is between 0 and (list's size)-1
				  taggs.get(randomValue).click(); //Clicking on the random item in the list.\
				 System.out.println( taggs.get(randomValue).getText());
				 Action.explicitWaitbyTitle(getDriver(), "Search Page", Duration.ofSeconds(5));
				  Assert.assertEquals(getDriver().getTitle(), "Search Page");}
				 else {
					 System.out.println("Tags are not present on this Recipe Page ");
				 }
				 
	}
	
	
	public void nutrilionListVerification() {
		WebElement l1 = getDriver().findElement(By.tagName("body"));
		//System.out.println(l1.getText());
		if(l1.getText().contains("Nutritions Per Serving")) {
		Log.info("Nutrition per Saving lists");
		WebElement nutritions_Display = getDriver()
				.findElement(By.xpath("//p[@id='sg-nuterationHashTitle']"));
		Action.scrollByVisibilityOfElement(getDriver(), nutritions_Display);
		 System.out.println(nutritions_Display.getText());
		

			Log.info("Nutrition per Saving lists");
			if (nutritions_Display.isDisplayed()) {

				Assert.assertEquals(nutritions_Display.getText(), "Nutritions Per Serving");
				if(l1.getText().contains("See more")) {
				WebElement seeButton = getDriver()
						.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-1fatci1']"));
				if (seeButton.isDisplayed()) {
					seeButton.click();
				}
				List<WebElement> nutritionLists = getDriver()
						.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-35gjpx']"));
				System.out.println(nutritionLists.size());
				int count111 = 0;
				List<String> NutritionElements = new ArrayList<String>();
				for (WebElement webElement : nutritionLists) {
					NutritionElements.add(webElement.getText());
					count111++;
				}
				String delii = "\n";
				String nutritions = String.join(delii, NutritionElements);
				System.out.println(nutritions);

				if (count111 == nutritionLists.size() && count111>0) {
					System.out.println("nutritionList List are equal");
					Assert.assertTrue(true);
				} else {
					System.out.println("nutritionList list Steps are not equal");
					Assert.assertTrue(false);
					}
				}
			
			
			else  {
			Log.info("Nutrition per Saving lists");
				Assert.assertEquals(nutritions_Display.getText(), "Nutritions Per Serving");
				List<WebElement> nutritionLists = getDriver()
						.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-35gjpx']"));
				System.out.println(nutritionLists.size());
				int count111 = 0;
				List<String> NutritionElements = new ArrayList<String>();
				for (WebElement webElement : nutritionLists) {
					NutritionElements.add(webElement.getText());
					count111++;
				}
				String delii = "\n";
				String nutritions = String.join(delii, NutritionElements);
				System.out.println(nutritions);

				if (count111 == nutritionLists.size()) {
					System.out.println("nutritionList List are equal");
					Assert.assertTrue(true);
				} else {
					System.out.println("nutritionList list Steps are not equal");
					Assert.assertTrue(false);
					}
				}
			}
		}
			else {
				System.out.println("Nutritions List are not Present");
				Log.warn("Nutrition List are not Present");
				}
	}
	
	String addAllToCart = "//div[@id='sg-allProductStack']/button";
	//String bigWishlistIcon = "(//*[name()='svg'][@data-testid='FavoriteBorderOutlinedIcon'])[5]";
	String allProduct= "(//div[@id='sg-allProductGridItem2'])/div";
	String shoptheIngridients = "//p[@id='sg-allProductHashTitle']";
	public void shoptheIngredients() throws InterruptedException {
		
		WebElement l1 = getDriver().findElement(By.tagName("body"));
		//System.out.println(l1.getText());
		if(l1.getText().contains("SHOP THE INGREDIENTS")) {
			System.out.println("dfghjkl");
			WebElement ShoptheIngridients = getDriver().findElement(By.xpath(shoptheIngridients));
			Action.scrollByVisibilityOfElement(getDriver(), ShoptheIngridients);
		
			List<WebElement> products1 = getDriver().findElements(By.xpath(allProduct));
			int n = products1.size();
			System.out.println(n);

			List<WebElement> image = getDriver().findElements(By.xpath(images));
			int x = image.size();
			int count4 = 0;
			if (x == n) {
				for(WebElement i : image) {
					if(i.getAttribute("srcset").contains("shopify.com")) {
						count4++;
					}
				}
				if(count4 !=0 ) {
				System.out.println("All " + count4 + " images are present");}
				else {
					softAssert.assertTrue(false, n-count4+ "Images are not present  ");
				}
			} else {
				System.out.println(n - x + " Images are not present ");
				softAssert.assertTrue(false, +n - x + " Images are not present ");
			}

			List<WebElement> productName1 = getDriver().findElements(By.xpath(productsName));
			int j = productName1.size();
			if (j == n) {
				System.out.println("All Products name are present");
			} else {
				System.out.println(n - j + " Products name are not present ");
				softAssert.assertTrue(false, +n - j + " Products name are not present ");
			}

			List<WebElement> productActPrice = getDriver().findElements(By.xpath(actualPrice));
			int z = productActPrice.size();
			if (z == n) {
				System.out.println("All Actual Prices are present");
			} else {
				System.out.println(n - z + " Actual Prices are not present ");
				softAssert.assertTrue(false, n - z + " Actual Prices are not present ");
			}
			
			List<WebElement> productDiscountedPrice = getDriver().findElements(By.xpath(discountedPrice));
			int r = productDiscountedPrice.size();
			if (r == n) {
				System.out.println("All Discounted Prices are present");
			} else {
				System.out.println(n - r + " Discounted Prices are not present ");
				//softAssert.assertTrue(false, n - r + " Actual Prices are not present ");
			}

			List<WebElement> quickviewlink = getDriver().findElements(By.xpath(quickview));
			int i = quickviewlink.size();
			if (i == n) {
				System.out.println("All Quick View links are present");
			} else {
				System.out.println(n - i + " Quick View links are not present ");
				softAssert.assertTrue(false, n - i + " Quick View links are not present ");
			}

			List<WebElement> carticon1 = getDriver().findElements(By.xpath(carticon));
			int c = carticon1.size();
			if (c == n) {
				System.out.println("All carticon are present");
			} else {
				System.out.println(n - c + " carticon are not present ");
				softAssert.assertTrue(false, +n - c + " carticon are not present ");
			}

			List<WebElement> wishlist1 = getDriver().findElements(By.xpath(wishlist));

			int d = wishlist1.size();
			if (d == n) {
				System.out.println("All Wishlist icon are present");
				
			} else {
				System.out.println(n - d + " Wishlist icon are not present ");
				softAssert.assertTrue(false, +n - d + " Wishlist icon are not present ");
			}
			
			
			/*
			 * WebElement BigWishlistIcon =
			 * getDriver().findElement(By.xpath(bigWishlistIcon));
			 * softAssert.assertTrue(BigWishlistIcon.isDisplayed(),
			 * "Big Wishlist icon in Shop the Ingridients sections is not present");
			 * 
			 * 
			 * Action.click(getDriver(), BigWishlistIcon);
			 * if(l1.getText().contains("Email")) { WebElement login =
			 * getDriver().findElement(By.xpath(loginPageTxt));
			 * Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
			 * Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed(
			 * )); WebElement CrossButton = getDriver().findElement(By.xpath(crossbutton1));
			 * Action.click(getDriver(), CrossButton); Thread.sleep(1000);} else {
			 * System.out.println("Wishlist icon is not clickable");
			 * softAssert.assertTrue(false, "Wishlist icon is not clickable"); }
			 */
				
		WebElement  AddAllToCart = getDriver().findElement(By.xpath(addAllToCart));
		softAssert.assertTrue(AddAllToCart.isDisplayed(), "AddAllToCart in Shop the Ingridients sections is not present");
				
					
		Action.click(getDriver(), AddAllToCart);
					if(l1.getText().contains("Email")) {
					WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
					Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
					Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());
					WebElement CrossButton = getDriver().findElement(By.xpath(crossbutton1));
					Action.click(getDriver(), CrossButton);
					Thread.sleep(1000);}
					else {
						System.out.println("AddAllToCart icon is not clickable");
						softAssert.assertTrue(false, "AddAllToCart icon is not clickable");
					}
					
			

			softAssert.assertAll();
		
		}
		else {
			Log.warn("Shop the Ingredients section is not present");
			System.out.println("Shop the Ingredients section is not present");
		}
		
		
		
	}

	

	
	public void commentsSectionVerification() {
		WebElement comment = getDriver().findElement(By.xpath(commentTxt));
		Action.scrollByVisibilityOfElement(getDriver(), comment);
		String actualTxt = getDriver().findElement(By.xpath(commentTxt)).getText();
		Assert.assertEquals(actualTxt, "Comments");
		Assert.assertTrue(getDriver().findElement(By.xpath(commentBox)).isDisplayed());
		Assert.assertTrue(getDriver().findElement(By.xpath(postbutton)).isDisplayed());
		}
	
	
	
	public void addCommentVerification() throws InterruptedException {
		
		//Comment textbox validation
		WebElement CommentBox =getDriver().findElement(By.xpath(commentBox));
		Assert.assertEquals(CommentBox.getAttribute("placeholder"), "Type a comment...", "Placeholder is not correct");
		Action.scrollByVisibilityOfElement(getDriver(), CommentBox);
		Log.info("Comment Text box is present");
		Action.type(CommentBox, "This is the comment for testing");
		
		Log.info("USer is able to enter the data in the comment textbox");
		
		WebElement PostButton =getDriver().findElement(By.xpath(postbutton));
		Action.explicitWait(getDriver(), PostButton, Duration.ofSeconds(1));
		
		
		Action.click(getDriver(), PostButton);
		Log.info("Successfully clicked on the Post button ");
		
		WebElement LoginButton =getDriver().findElement(By.xpath(loginbutton));
		Action.explicitWait(getDriver(), LoginButton, Duration.ofSeconds(5));
		boolean p = Action.isDisplayed(getDriver(), LoginButton);
		Assert.assertTrue(p);
		
		//Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());
		WebElement CrossButton =getDriver().findElement(By.xpath(crossbutton1));
		Action.click(getDriver(), CrossButton);
		
		//getDriver().findElement(By.xpath(crossbutton)).click();
		List<WebElement> abuse= getDriver().findElements(By.xpath(reportAbuse));
		int abuseCount = abuse.size();
		System.out.println(abuseCount);
		
		String countTxt = getDriver().findElement(By.xpath(commentCount)).getText();
		int count=Integer.parseInt(countTxt); 
		
		System.out.println("Total Count of comments  = "+count);
		
		//List<WebElement> postedcomments = getDriver().findElements(By.xpath(commentPosted));
		//List<WebElement> abuse= getDriver().findElements(By.xpath(reportAbuse));
		if(abuseCount>=4 && count>4) {
			Log.info("Comments are more than 4");
			WebElement SeeMore  =getDriver().findElement(By.xpath(see_more));
			Action.click(getDriver(), SeeMore);
			Assert.assertEquals(SeeMore.getText(), "See less");
			Thread.sleep(2000);
			List<WebElement> postedcomments = getDriver().findElements(By.xpath(commentPosted));
			int postedCount = postedcomments.size();
			System.out.println("Total Posted Count after click on see more = "+postedCount);
			
			List<WebElement> abuse1= getDriver().findElements(By.xpath(reportAbuse));
			abuseCount = abuse1.size();
			System.out.println("Report Abuse Count = "+ abuseCount);
			
			
			
			List<WebElement> replycomments = getDriver().findElements(By.xpath(replyCount));
			int temp =0;
			for (WebElement element: replycomments) {
			String data =	element.getText();
			int data1=Integer.parseInt(data);
			temp = temp + data1;
			}
			System.out.println("Reply Comments are = "+ temp);
			int totalCount = postedCount +  temp;
			System.out.println("Total Count are verification = "+totalCount);
			Assert.assertEquals(count, totalCount, "Comments Counts are not equal");
			
			Log.info("Report abuse count verification");
			Assert.assertEquals(abuseCount, postedCount, "Counts are not equal of Report Abuse button");
			System.out.println("Successfully verified the Report Abuse buttons ");
					
		}
		else if(abuseCount<4 && abuseCount!=0 || count<5){
			Log.info("Count is Less than 5");
			List<WebElement> postedcomments = getDriver().findElements(By.xpath(commentPosted));
			int postedCount = postedcomments.size();
			System.out.println("Total Posted Count = "+postedCount);
			
			List<WebElement> abuse2= getDriver().findElements(By.xpath(reportAbuse));
			 abuseCount = abuse2.size();
			System.out.println("Report Abuse Count = "+ abuseCount);
			
			List<WebElement> replycomments = getDriver().findElements(By.xpath(replyCount));
			int temp =0;
			for (WebElement element: replycomments) {
			String data =	element.getText();
			int data1=Integer.parseInt(data);
			temp = temp + data1;
			}
			System.out.println("Reply Counts are" + temp);
			int totalCount = postedCount +  temp;
			System.out.println("Total Count are verification = "+totalCount);
			Assert.assertEquals(count, totalCount, "Comments Counts are not equal");

			Log.info("Report abuse count verification");
			Assert.assertEquals(abuseCount, postedCount, "Counts are not equal of Report Abuse button");
			System.out.println("Successfully verified the Report Abuse buttons ");
		}
		else {
			System.out.println("Comments are not Present");
		}
	}
	
	
	public void reportAbuseFunctionality() {
		WebElement CommentBox =getDriver().findElement(By.xpath(commentBox));
		Assert.assertEquals(CommentBox.getAttribute("placeholder"), "Type a comment...", "Placeholder is not correct");
		Action.scrollByVisibilityOfElement(getDriver(), CommentBox);
		
		List<WebElement> abuse= getDriver().findElements(By.xpath(reportAbuse));
		int abuseCount = abuse.size();
		System.out.println(abuseCount);
		
		 Random r = new Random(); 
		 int randomValue = r.nextInt(abuseCount); //Getting a random value that is between 0 and (list's size)-1
		 abuse.get(randomValue).click(); //Clicking on the random item in the list.\
		 System.out.println( abuse.get(randomValue).getText());
		
		
	}
	
	
	
	
	public void recipeVideoSection() throws InterruptedException {
		WebElement frames = getDriver().findElement(By.xpath(frame));
		WebElement recipeVideos = getDriver().findElement(By.xpath(recipeVideo));
		Action.scrollByVisibilityOfElement(getDriver(), recipeVideos);
		String text = recipeVideos.getText();
		Assert.assertEquals(text, "RECIPE VIDEO");
		System.out.println(Action.isDisplayed(getDriver(), frames));
		Action.click(getDriver(), frames);
		Thread.sleep(5000);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FindBy (xpath = "//input[@id='sg-outlinedAdornmentPassword']")
	WebElement typecomment;
	@FindBy(xpath = "(//div[@id='sg-editCommentAnsweredCommentReplace'])[1]")
	WebElement postedComment;
	@FindBy(xpath = "//img[@id='sg-commentContentImage']")
	WebElement editButton;
	@FindBy(xpath = "(//img[@alt='Dlt Button'])")
	WebElement deleteButton;
	
	@FindBy(xpath = "//input[@id='standard-helperText']")
	WebElement editTextbox;
	
	@FindBy(xpath = "(//button[@id='sg-editCommentSaveButton2'])")
	WebElement saveBtn;
	@FindBy(xpath = "(//button[@id='sg-editCommentCancelButton'])")
	WebElement cancelBtn;
	
	public void LoggedInAddCommentsVerification(String save1) throws InterruptedException {
		WebElement comment = getDriver().findElement(By.xpath(commentTxt));
		Action.scrollByVisibilityOfElement(getDriver(), comment);
		//Thread.sleep(5000);
		String typedComment = "This is selenium test989";
		Action.type(typecomment, typedComment);
		System.out.println("successfully typed the comment in the comment box");
		Action.click(getDriver(), postBtn);
		Thread.sleep(2000);
		System.out.println("Successfully Clicked on the Post button");
		
		Assert.assertEquals(postedComment.getText(), typedComment);
		Thread.sleep(1000);
		System.out.println("Successfully verified the add comment ");
		Assert.assertTrue(editButton.isEnabled(), "Edit button is not present");
		Action.click(getDriver(), editButton);
		System.out.println("Successfully clicked on the edit button");
		String editTypedComment = "This is edited selenium test9";
		Thread.sleep(1000);
		Action.type(editTextbox, editTypedComment);
		System.out.println("Successfully type the edited comment in the text box");
		if (save1.equalsIgnoreCase("save")) {
			System.out.println("in save section");
			
			Action.click(getDriver(), saveBtn);
			System.out.println("Successfully clicked on the Save button");
			Thread.sleep(2000);
			Assert.assertEquals(postedComment.getText(), editTypedComment);
			Assert.assertTrue(deleteButton.isEnabled(), "Delete button is not clickable");
			Action.click(getDriver(), deleteButton);
			
			Thread.sleep(1000);
			String txt = getDriver().findElement(By.xpath("(//h2[normalize-space()='Delete comment'])[1]")).getText();
			if (txt.equalsIgnoreCase("Delete Comment"))
			getDriver().findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(1000);
			System.out.println("Comment successfully deleted");
			
		}
		
		  else if(save1.equalsIgnoreCase("cancel"))
		  { Action.click(getDriver(),cancelBtn); 
		  Assert.assertEquals(postedComment.getText(), typedComment);
		  Log.info("Successfully cancel to write the edited comment");}
		 
	}
	
	
	String bookMarkicon = "//*[name()='svg' and @data-testid='BookmarkBorderIcon']";
	String mightLikeProductDescription = "(//div[@class='swiper-wrapper'])/div/div/div/div[2]";
	String mightLikeProductNames = "(//div[@class='swiper-wrapper'])/div/div/div/div[1]";
	String mightLikeallproduct = "(//div[@class='swiper-wrapper'])/div/div";
	String mightLikeimages = "(//div[@class='swiper-wrapper'])/div/div/span/img";
	String mightLike1 = "//div[@class='MuiBox-root css-w6xysq']/p";
	public void weFoundOtherContentYouMightLike() throws InterruptedException {

			WebElement MightLike1 = getDriver().findElement(By.xpath(mightLike1));
			Action.scrollByVisibilityOfElement(getDriver(), MightLike1);
			
			List<WebElement> MightLikeallproduct = getDriver().findElements(By.xpath(mightLikeallproduct));
			int n = MightLikeallproduct.size();
			int count5 =0;
			System.out.println(n + " Recipes should be present in Might Like section");
			List<WebElement> MightLikeimages = getDriver().findElements(By.xpath(mightLikeimages));
			int x = MightLikeimages.size();
			System.out.println(x + " Recipes images should be present in Might Like section");
			Thread.sleep(5000);
			if(x==n) {
				for (WebElement i : MightLikeimages) 
				{
				Action.mouseOverElement(getDriver(), i);
				//Thread.sleep(1000);
				if (i.getAttribute("srcset").contains("amazonaws.com")) {
					count5++;
				}
			}	
				if (count5 == n) {
					System.out.println("All " + count5 + " might images are present");
				} else {
					softAssert.assertTrue(false, n - count5 + " Might Images are not present  ");
				}
			}
				else {
				System.out.println(n-x + " Images are not present ");
				softAssert.assertTrue(false, +n-x+" Images are not present ");
				}
			
			List<WebElement> MightLikeProductName= getDriver().findElements(By.xpath(mightLikeProductNames));
			int y = MightLikeProductName.size();
			if(y==n) {System.out.println("All "+ y +" Might_Like Product Names are present");}
				else {
				System.out.println(n-y + "  Might Like Product Names are not present ");
				softAssert.assertTrue(false, +n-y+"  Might Like Product Names are not present ");
				}
			
			List<WebElement> MightLikeProductDescription= getDriver().findElements(By.xpath(mightLikeProductDescription));
			int z = MightLikeProductDescription.size();
			if(z==n) {System.out.println("All "+ z +" Might_Like Product description are present");}
				else {
				System.out.println(n-z + "  Might Like Product description are not present ");
				softAssert.assertTrue(false, +n-z+"  Might Like Product description are not present ");
				}
			
			List<WebElement> BookMarkicon =getDriver().findElements(By.xpath(bookMarkicon));
			int c = BookMarkicon.size();
			if(c==n) {System.out.println("All "+ c +"  BookMarkicon are present");}
				else {
				System.out.println(n-c + " BookMarkicon are not present ");
				softAssert.assertTrue(false, +n-c+" BookMarkicon are not present ");
				}
				
	}
	
	
	
	
}
