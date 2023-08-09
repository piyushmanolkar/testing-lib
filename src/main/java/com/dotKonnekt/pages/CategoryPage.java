package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class CategoryPage extends BaseClass{

	/*
	 * String searchBox = "//input[@placeholder='Search']"; String clickButton=
	 * "//div[@class='MuiInputAdornment-root MuiInputAdornment-positionStart MuiInputAdornment-outlined MuiInputAdornment-sizeMedium css-1a6giau']//*[name()='svg']"
	 * ; String welcomeTxt1 =
	 * "(//p[@class='MuiTypography-root MuiTypography-body1 css-k1juyd'])[1]";
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

	public CategoryPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	String bd_Home = "//a[@id='sg-breadcrum0 sg-BreadCrumbLink']";
	String bd_Category = "(//a[@id='sg-breadcrum0 sg-BreadCrumbLink'])[3]";
	
	public String breadCrumbFunctionality() {
		String bd1text = getDriver().findElement(By.xpath(bd_Home)).getText();
		Assert.assertEquals(bd1text, "Home");
		String bdlast = getDriver().findElement(By.xpath(bd_Category)).getText();
		return bdlast;
		
	}
	
	String images1 = "//img[@id='sg-bannerImage']";
	String imageDescription = "//p[@id='sg-bannerContentTitle']";
	public void imageVerification() {
		WebElement imageDes = getDriver().findElement(By.xpath(imageDescription));
		Action.explicitWait(getDriver(), imageDes, Duration.ofSeconds(2));
		
		List<WebElement> image = getDriver().findElements(By.xpath(images1));
		int x = image.size();
		int count4 = 0;
		if (x == 1) {
			for(WebElement i : image) {
				Action.mouseOverElement(getDriver(), i);
				if(i.getAttribute("srcset").contains("amazonaws.com")) {
					count4++;
				}
			}
			if(count4 ==1 ) {
			System.out.println("All " + count4 + " images are present");}
			else {
				softAssert.assertTrue(false, " Images are not present  ");
			}
		} else {
			System.out.println(" Images are not present ");
			softAssert.assertTrue(false, " Images are not present ");
		}
		
		/*
		 * Boolean p = (Boolean) ((JavascriptExecutor)getDriver())
		 * .executeScript("return arguments[0].complete " +
		 * "&& typeof arguments[0].naturalWidth != \"undefined\" " +
		 * "&& arguments[0].naturalWidth > 0", imageDes); if(p) {
		 * System.out.println("Image is present and verified successfully"); } else {
		 * System.out.println("Image is not present"); Assert.assertTrue(false,
		 * "Image is not present"); }
		 */
	}
	
	String taggscount= "//span[@class='MuiChip-label MuiChip-labelMedium css-9iedg7']";
	public void tagList(){
		
		WebElement l = getDriver().findElement(By.tagName("body"));
		// System.out.println("Elements with P: =" + l.getText());
		 if(l.getText().contains("Tags")) {
		
		 Log.info("Veryfying the Tag List");
		  WebElement tagText = getDriver().findElement(By.
				  xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 css-qfzj9b']"));
		  Action.scrollByVisibilityOfElement(getDriver(), tagText);
		   System.out.println(tagText.getText());
		List<WebElement> taggs = getDriver().findElements(By.xpath(taggscount));
		System.out.println(taggs.size());
			if(taggs.size() == 4) {
				System.out.println(getDriver().findElement(By.xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 css-juaq']")).getText());
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
			else if(taggs.size()<4 && taggs.size() !=0) {
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
				Assert.assertTrue(false, "Taggs elements are blank");
			}	
			
		 }
		 else {
			 System.out.println("Tags are not present on this page");
		 }
	}
	
	String subtabs  = "//button[@id='sg-tabsComponentTab']";
	public void subTabsverification() {
		int count =0;
		List<WebElement>  tabs =getDriver().findElements(By.xpath(subtabs));
		int size = tabs.size();
		System.out.println(size);
		List<String> tabsElements = new ArrayList<String>();
		for (WebElement webElement : tabs) {
			tabsElements.add(webElement.getText());
			if(webElement.isEnabled()) {
			count++;}
		}
		 String deli = "\n"; 
		  String subtabss= String.join(deli, tabsElements);
		  System.out.println(subtabss);
		  
		  if (count == tabs.size() && count != 0 ) 
		  { System.out.println("subTabs List are equal");
		  Assert.assertTrue(true); } 
		  else  {
		  System.out.println("subTabs list Steps are not equal");
		  Assert.assertTrue(false, "subTabs list Steps are not equal" ); }
	}
	
	
	
	String allproduct ="//div[contains(@id,'sg-productCardWrapper')]";
	String quickview = "(//div[@id='sg-quickViewButton'])";
	String discountedPrice = "//p[@id='sg-productCardDisablePriceText']";
	String actualPrice = "//div[@id='productCardContentPriceTile']";
	String productsName = "//div[@id='sg-productCardContentTitle']";
	String images  = "//img[@id='sg-productCardImage2']";
	String carticon = "//*[name()='svg' and @data-testid='ShoppingCartOutlinedIcon']";
	String wishlist = "//*[name()='svg' and @data-testid='FavoriteBorderOutlinedIcon']";
	SoftAssert softAssert = new SoftAssert();
	
	public void newArrival() throws InterruptedException {
		WebElement bodyText = getDriver().findElement(By.tagName("body"));
		Log.info("Inside Body Tag");
		if(bodyText.getText().contains("Filter by:")){
			String filter = "//p[@class='MuiTypography-root MuiTypography-body1 css-1r2mskm']";
			WebElement filterBy = getDriver().findElement(By.xpath(filter));
			Action.scrollByVisibilityOfElement(getDriver(), filterBy);
			Log.info("Filter By is present");
			String pagination = "((//nav[@id='sg-pagination'])[2]/ul/li/button)";
			List<WebElement> Pagination = getDriver().findElements(By.xpath(pagination));
			
			if(Pagination.size()>=3 ) {
				
			
				//if (bodyText.getText().contains("Previous")) {
					Log.info("Pagination is present");
					int PaginationValue = getDriver().findElements(By.xpath(pagination)).size();
					System.out.println(PaginationValue);
					List<String> values = new ArrayList<String>();
					for (int q = 2; q <= PaginationValue - 1; q++) {
						Thread.sleep(700);
						String paginationSelector = pagination + "[" + q + "]";
						WebElement paginationSelectorClick = getDriver().findElement(By.xpath(paginationSelector));
						Action.click(getDriver(), paginationSelectorClick);
			
		
  
		Thread.sleep(2000);
		List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
		 int n = products1.size();
		System.out.println("Products Present on this page " + n);
	
					
		if(n>0) {
			
		List<WebElement> productName1 = getDriver().findElements(By.xpath(productsName));
		int j = productName1.size();
		if(j==n) {System.out.println("All Products name are present");}
			else {
			System.out.println(n-j + " Products name are not present ");
			softAssert.assertTrue(false, +n-j+" Products name are not present ");
			}
		
		List<WebElement> productActPrice = getDriver().findElements(By.xpath(actualPrice));
		int z = productActPrice.size();
		if(z==n) {System.out.println("All Actual Prices are present");}
			else {
			System.out.println(n-z + " Actual Prices are not present ");
			softAssert.assertTrue(false, n-z+" Actual Prices are not present ");
			}
		
		List<WebElement> quickviewlink = getDriver().findElements(By.xpath(quickview));
		int i = quickviewlink.size();
		if(i==n) {System.out.println("All Quick View links are present");}
			else {
			System.out.println(n-i + " Quick View links are not present ");
			softAssert.assertTrue(false, n-i+" Quick View links are not present ");
			}
		
		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int x = image.size();
		int count4 = 0;
		if (x == n) {
			for(WebElement j1 : image) {
				Action.mouseOverElement(getDriver(), j1);
				
				if(j1.getAttribute("srcset").contains("shopify.com")) {
					count4++;
				}
			}
			if(count4 ==n ) {
			System.out.println("All " + count4 + " images are present");}
			else {
				softAssert.assertTrue(false, n-count4+ " Images are not present  ");
			}
		} else {
			System.out.println(n - x + " Images are not present ");
			softAssert.assertTrue(false, +n - x + " Images are not present ");
		}
		
		List<WebElement> carticon1 =getDriver().findElements(By.xpath(carticon));
		int c = carticon1.size();
		if(c==n) {System.out.println("All carticon are present");}
			else {
			System.out.println(n-c + " carticon are not present ");
			softAssert.assertTrue(false, +n-c+" carticon are not present ");
			}
		
		List<WebElement> wishlist1 =getDriver().findElements(By.xpath(wishlist));
		
		int d = carticon1.size();
		if(d==n) {System.out.println("All Wishlist icon are present");}
			else {
			System.out.println(n-d + " Wishlist icon are not present ");
			softAssert.assertTrue(false, +n-c+" Wishlist icon are not present ");
			}
		Log.info(q+ " pagination complete");
		//softAssert.assertAll();
		}
		
		else {System.out.println("No Products are available");}
			}			
		}
			
				
			else if(Pagination.size()==0) {
					Log.info("Pagination is not present");
					Thread.sleep(2000);
					List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
					 int n = products1.size();
					System.out.println("Products Present on this page " + n);
				
								
					if(n>0) {
						
					List<WebElement> productName1 = getDriver().findElements(By.xpath(productsName));
					int j = productName1.size();
					if(j==n) {System.out.println("All Products name are present");}
						else {
						System.out.println(n-j + " Products name are not present ");
						softAssert.assertTrue(false, +n-j+" Products name are not present ");
						}
					
					List<WebElement> productActPrice = getDriver().findElements(By.xpath(actualPrice));
					int z = productActPrice.size();
					if(z==n) {System.out.println("All Actual Prices are present");}
						else {
						System.out.println(n-z + " Actual Prices are not present ");
						softAssert.assertTrue(false, n-z+" Actual Prices are not present ");
						}
					
					List<WebElement> quickviewlink = getDriver().findElements(By.xpath(quickview));
					int i = quickviewlink.size();
					if(i==n) {System.out.println("All Quick View links are present");}
						else {
						System.out.println(n-i + " Quick View links are not present ");
						softAssert.assertTrue(false, n-i+" Quick View links are not present ");
						}
					
					List<WebElement> image = getDriver().findElements(By.xpath(images));
					int x = image.size();
					int count4 = 0;
					if (x == n) {
						for(WebElement j1 : image) {
							Action.mouseOverElement(getDriver(), j1);
							Thread.sleep(500);
							if(j1.getAttribute("srcset").contains("shopify.com")) {
								count4++;
							}
						}
						if(count4 ==n ) {
						System.out.println("All " + count4 + " images are present");}
						else {
							softAssert.assertTrue(false, n-count4+ " Images are not present  ");
						}
					} else {
						System.out.println(n - x + " Images are not present ");
						softAssert.assertTrue(false, +n - x + " Images are not present ");
					}
					
					List<WebElement> carticon1 =getDriver().findElements(By.xpath(carticon));
					int c = carticon1.size();
					if(c==n) {System.out.println("All carticon are present");}
						else {
						System.out.println(n-c + " carticon are not present ");
						softAssert.assertTrue(false, +n-c+" carticon are not present ");
						}
					
					List<WebElement> wishlist1 =getDriver().findElements(By.xpath(wishlist));
					
					int d = carticon1.size();
					if(d==n) {System.out.println("All Wishlist icon are present");}
						else {
						System.out.println(n-d + " Wishlist icon are not present ");
						softAssert.assertTrue(false, +n-c+" Wishlist icon are not present ");
						}
				}
			}
		else {Assert.assertTrue(false, "Products are not present in the New Arrival Sections");}
		}
		
		else {System.out.println("No Products are available");}
		softAssert.assertAll();
	}
		
		
	String subCateg = "(//button[contains(@id, 'sg-verticalTabsTab2')])";
		public void subCategories() {
			List<WebElement> SubCategoriesValue =getDriver().findElements(By.xpath(subCateg));
			if(SubCategoriesValue.size()==7) {
				int count =0;
				List<String> listElements = new ArrayList<String>();
				for (WebElement webElement : SubCategoriesValue) {
					listElements.add(webElement.getText());
					count++;
				}
				String category = "All,Moisturizer,Toners,Face Oils,Body Scrubs,Cleansers & Face wash,Skincare Recipes";
				List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
				System.out.println(myList);
				System.out.println(listElements);
				if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
					Log.info("Successfully verified that all the sub category elements are in correct form");
				}
				else {
					Assert.assertTrue(false, "Sub Category elements are not in correct form");
				}
			}
			else {
				softAssert.assertTrue(false, "Sub_categories are not present perfectly");
			}
		}
		
		
		String inspect = "//div[@class='MuiBox-root css-1y4n82h']";
		String categoryElements1 = "//button[@id='sg-categoryItem']";
		public void subCategoriesVerification() {
			List<WebElement> menuList = getDriver().findElements(By.xpath(categoryElements1));
		
			for(WebElement i: menuList) {
				if(i.getText().contains("Skin")) {
				String l = "(//div[@class='MuiBox-root css-1y4n82h']/button)["+ 1+ "]";
				WebElement m= getDriver().findElement(By.xpath(l));
				Action.click(getDriver(), m);
				String subCategories = inspect + "/div";
				WebElement SubCategories = getDriver().findElement(By.xpath(subCategories));
				String subCategoriesText = SubCategories.getText();
				System.out.println(subCategoriesText);
				
			}
				
				else if(i.getText().contains("Skin")) {
					String l = "(//div[@class='MuiBox-root css-1y4n82h']/button)["+ 1+ "]";
					WebElement m= getDriver().findElement(By.xpath(l));
					Action.click(getDriver(), m);
					String subCategories = inspect + "/div";
					WebElement SubCategories = getDriver().findElement(By.xpath(subCategories));
					String subCategoriesText = SubCategories.getText();
					System.out.println(subCategoriesText);
			}
		}
	}
		
		
		
		String bookmark ="//*[name()='svg' and @data-testid='BookmarkBorderIcon']";
		String crumb = "//p[contains(@id,'sg-sideRepeatAuthorInfo')]";
		String title = "//p[contains(@id,'sg-sideRepeatFeatureDesc')]";
		String popularArticles = "//img[contains(@id,'sg-sideRepeatCardMedia')]";
		String forwardicon = "(//*[name()='svg'][@data-testid='ArrowForwardIcon'])[2]";
		String backwardicon = "(//*[name()='svg'][@data-testid='ArrowBackIcon'])";
		String popularReadText = "(//p[contains(@id, 'sg-popularReadTitle')])";
		public void popularReads () {
			WebElement PopularReadsText = getDriver().findElement(By.xpath(popularReadText));
			Assert.assertEquals(PopularReadsText.getText(), "Popular Reads");
			WebElement ForwardIcon = getDriver().findElement(By.xpath(forwardicon));
			Assert.assertTrue(ForwardIcon.isDisplayed());
			WebElement BackWard = getDriver().findElement(By.xpath(backwardicon));
			Assert.assertTrue(BackWard.isDisplayed());
			Log.info("Successfully Verified the presence of Forward & BackWard Icon");
			List<WebElement> PopularArticles =getDriver().findElements(By.xpath(popularArticles));
			for(WebElement i:  PopularArticles) {
				Assert.assertTrue(i.getAttribute("src").contains("drupal"), "Image is not present in the Popular reads section");
			}
			Log.info("Successfully verified the presence of the articles image");
			List<WebElement> Crumb =getDriver().findElements(By.xpath(crumb));
			for(WebElement i:  Crumb) {
				Assert.assertTrue(i.isDisplayed(), " Crumb title is not visible");
				Assert.assertTrue(!i.getText().equals(null), "Crumb Title is not present in the Popular reads section");
			}
			Log.info("Successfully verified the presence of the articles Crumb");
			
			List<WebElement> Title =getDriver().findElements(By.xpath(title));
			for(WebElement i:  Title) {
				Assert.assertTrue(i.isDisplayed(), "  title is not visible");
				Assert.assertTrue(!i.getText().equals(null), " Title is not present in the Popular reads section");
			}
			Log.info("Successfully verified the presence of the articles title");
			List<WebElement> BookMarkIcon =getDriver().findElements(By.xpath(bookmark));
			Assert.assertEquals(BookMarkIcon.size(), 7, "one of the bookmark is not present");
			Log.info("Successfully verified the presence of the bookmark icon ");
		}
		
		String readmore = "//button[contains(@id,'sg-featuredContentReadMoreButton')]";
		String writtenBy = "//div[contains(@id,'sg-featuredContentBox2')]";
		String bigImageDesc = "//p[contains(@id,'sg-featuredContentFeatureMainDesc')]/p";
		String blogTitle = "//div[contains(@id,'sg-blogHeadTitle')]";
		String bigImageTitle = "//div[contains(@id,'sg-featuredContentMainFeatureHeading')]";
		String bigImage = "//img[contains(@id,'sg-featuredContentCardMedia')]";
		public void bigImageBlog () {
			WebElement BigImage = getDriver().findElement(By.xpath(bigImage));
			Action.scrollByVisibilityOfElement(getDriver(), BigImage);
			if(BigImage.getAttribute("src").contains("drupal")) {
				Log.info("Big image blog is present on the category page");
				
				WebElement BigImageTitle = getDriver().findElement(By.xpath(bigImageTitle));
				softAssert.assertTrue(BigImageTitle.isDisplayed(), "Title is not present on the category page");
				String Title = BigImageTitle.getText();
				Log.info("Big image Title is present on the category page");
				WebElement BigImageDesc = getDriver().findElement(By.xpath(bigImageDesc));
				softAssert.assertTrue(BigImageDesc.isDisplayed(), "Description is not present on the category page of the Big Image size blog");
				Log.info("Big image Title is present on the category page");
				
				Action.click(getDriver(), BigImageTitle);
				Action.explicitWaitbyTitle(getDriver(), "BlogPage", Duration.ofSeconds(5));
				
				WebElement Blogtitle = getDriver().findElement(By.xpath(blogTitle));
				softAssert.assertEquals(Blogtitle.getText(), Title, "Blog Title is not same Category Page Big Image Blog" );
				Log.info("Successfully verified the title of the blog with the category title");
				
				getDriver().navigate().back();
				Action.explicitWaitbyTitle(getDriver(), "Category", Duration.ofSeconds(5));
				
				WebElement WrittenBy = getDriver().findElement(By.xpath(writtenBy));
				System.out.println(WrittenBy.getText());
				if(WrittenBy.getText().contains("Written by") && WrittenBy.getText().contains("Updated on")) {
					Log.info("Author name & update on date is present");
				}
				else {
					softAssert.assertTrue(false, "Author name & update on date is not present");
				}
				
				WebElement ReadMorebutton  = getDriver().findElement(By.xpath(readmore));
				softAssert.assertTrue(ReadMorebutton.isDisplayed() && ReadMorebutton.getText().equals("Read more"), "Read More button is not displayed");
				Action.click(getDriver(), ReadMorebutton);
				Action.explicitWaitbyTitle(getDriver(), ("BlogPage"), Duration.ofSeconds(5));
				getDriver().navigate().back();
				Action.explicitWaitbyTitle(getDriver(), "Category", Duration.ofSeconds(5));
				
				
				
				
			}
			else {
				Assert.assertTrue(false, "Big image blog is not present on this category Page");
			}
		}
			
}
