package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class SearchPage extends BaseClass{

	

	public SearchPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	SoftAssert softAssert = new SoftAssert();
	String pagination = "(//div[@id='sg-paginationBox']/nav/ul/li/button)";
	String shopProducts = "//div[@id='sg-productCardWrapper']";
	String showingresult = "(//div[@id='sg-tabsComponentBox2'])/p";
	String noresult = "//p[@id='sg-no-results']";
	String shoptab = "(//button[@id='sg-tabsComponentTab'])[1]";
	String articletab = "(//button[@id='sg-tabsComponentTab'])[2]";
	String clickButton=  "//*[name()='svg' and @data-testid='SearchOutlinedIcon']";
	String searchBox = "//input[@placeholder='Search']";
	String recentSearch = "//p[@id='sg-autoCompleteSearchTypography']";
	String trending = "//p[@id='sg-autoCompleteSearchTypography3']";
	public void validateSeachFunctionalityForShopTab(String searchData, String ClickedBy,String Title) throws InterruptedException {
		getDriver().findElement(By.xpath(searchBox)).click();

		getDriver().findElement(By.xpath(searchBox)).sendKeys(searchData);
		if (ClickedBy.equalsIgnoreCase("Mouse")) {
			getDriver().findElement(By.xpath(clickButton)).click();
		} else if (ClickedBy.equalsIgnoreCase("Keyboard")) {
			getDriver().findElement(By.xpath(searchBox)).sendKeys(Keys.ENTER);
		}
		Action.explicitWaitbyTitle(getDriver(), "Search Page", Duration.ofSeconds(10));
		String title1 = getDriver().getTitle();
		Assert.assertEquals(title1, "Search Page");
		Thread.sleep(2000);
		WebElement ShopTab = getDriver().findElement(By.xpath(shoptab));
		// WebElement ArticleTab = getDriver().findElement(By.xpath(articletab));
		Action.explicitWait(getDriver(), ShopTab, Duration.ofSeconds(5));
		// WebElement ShopTab = getDriver().findElement(By.xpath(shoptab));
		if (ShopTab.getText().equals("Shop")) {

			WebElement NoResult = getDriver().findElement(By.xpath(noresult));
			System.out.println(NoResult.getText());
			System.out.println("Sorry! no result found for " + '"' + searchData + '"');
			if (NoResult.getText().equals("Sorry! no result found for " + '"' + searchData + '"')) {
				Log.info("Successfully verified the presence of the sorry things");
			} else {
				Assert.assertTrue(false, "Sorry No Result found is not present");

			}
			Log.info("No products are available for this search in Shop Tab");
		}

		if (ShopTab.getText().contains("Shop") && !ShopTab.getText().equals("Shop")) {

			String shopText = ShopTab.getText();
			String n = shopText.replaceAll("[^0-9]", "");
			System.out.println(n);
			WebElement Showingresult = getDriver().findElement(By.xpath(showingresult));
			System.out.println(Showingresult.getText());
			System.out.println("Showing " + n + " results for " + '"' + searchData + '"');
			if (Showingresult.isDisplayed()) {
				String ss = (Showingresult.getText()).replaceAll("[^0-9]", "");
				System.out.println("Total products are" + ss);

				
				if (ss.equals(n)) {
					WebElement bodyText = getDriver().findElement(By.tagName("body"));
					if (bodyText.getText().contains("Previous")) {
						int PaginationValue = getDriver().findElements(By.xpath(pagination)).size();
						Log.info("Inside pagination section");
						
						List<String> values = new ArrayList<String>();
						for (int i = 2; i <= PaginationValue - 1; i++) {
							Thread.sleep(1000);
							String paginationSelector = pagination + "[" + i + "]";
							WebElement paginationSelectorClick = getDriver().findElement(By.xpath(paginationSelector));
							Action.click(getDriver(), paginationSelectorClick);
							List<WebElement> ShopProducts = getDriver().findElements(By.xpath(shopProducts));
							for (WebElement sh : ShopProducts) {
								values.add(sh.getText());
								// Thread.sleep(200);
							}
						}

						int TotalProducts = values.size();
						System.out.println("Total products ARE " + TotalProducts);
						String firstPage = "(//div[@class='MuiBox-root css-1sfz9yc']/nav/ul/li/button)[2]";
						WebElement FirstPage = getDriver().findElement(By.xpath(firstPage));
						Action.click(getDriver(), FirstPage);
						Thread.sleep(700);
						if (TotalProducts == Integer.parseInt(ss)) {
							Log.info("Successfully Verified the Count of the Products");
						} else {
							Assert.assertTrue(false, "Products count are not equals");
						}
					}
					
					
					else {
						List<String> values = new ArrayList<String>();
						List<WebElement> ShopProducts = getDriver().findElements(By.xpath(shopProducts));
						for (WebElement sh : ShopProducts) {
							values.add(sh.getText());}
							int TotalProducts = values.size();
							System.out.println("Total products ARE " + TotalProducts);
							if (TotalProducts == Integer.parseInt(ss)) {
								Log.info("Successfully Verified the Count of the Products");
							}
							else {
								Assert.assertTrue(false, "No. of Products are not Equal");
							}
					}
					
					Log.info("Successfully verified the search functionality");
				} else {
					Assert.assertTrue(false, "Numbers are not equals");
				}

			} else {
				Assert.assertTrue(false, "Showing Result is not showing the correct numbers");
			}
		} else {
			Assert.assertTrue(false, "Shop tab is not present on the ui");
		}

	}
	
	
	
	public void SearchFunctionalityForArticleTab (String searchData, String ClickedBy,String Title) throws InterruptedException {
		getDriver().findElement(By.xpath(searchBox)).click();
		WebElement Trending = getDriver().findElement(By.xpath(trending));
		String trendingTxt = Trending.getText();
		Assert.assertEquals(trendingTxt, "Trending");

		getDriver().findElement(By.xpath(searchBox)).sendKeys(searchData);
		if(ClickedBy.equalsIgnoreCase("Mouse")) {
			getDriver().findElement(By.xpath(clickButton)).click();	
		}
		else if(ClickedBy.equalsIgnoreCase("Keyboard")) {
			getDriver().findElement(By.xpath(searchBox)).sendKeys(Keys.ENTER);
		}
		Log.info("Successfully performed the search action");
		Action.explicitWaitbyTitle(getDriver(), "Search Page", Duration.ofSeconds(10));
		String title1 = getDriver().getTitle();
		Assert.assertEquals(title1, "Search Page");
		Thread.sleep(2000);
		Log.info("Successfully verified the presence of the same title");
		WebElement ArticleTab = getDriver().findElement(By.xpath(articletab));
		String text = ArticleTab.getText();
		System.out.println(text);
		Action.explicitWait(getDriver(), ArticleTab, Duration.ofSeconds(5));
		Log.info("Successfully clicked on the Article Tab");
		//ArticleTab.click();
		if(ArticleTab.getText().equals("Article")) {
			Action.click(getDriver(), ArticleTab);
			WebElement NoResult = getDriver().findElement(By.xpath(noresult));
			if(NoResult.getText().equals("Sorry! no result found for " + '"'+searchData+'"')) {
				Log.info("Successfully verified the presence of the sorry things in article tab");
			}
			else {
				Assert.assertTrue(false, "Sorry No Result found is not present in article tab");
			}
			Log.info("No products/ Articles are available for this search in Article Tab");
		}

		if(ArticleTab.getText().contains("Article") && !ArticleTab.getText().equals("Article")) {
			Log.info("fghjk");
			ArticleTab.click();
			//Action.click(getDriver(), ArticleTab);
			Thread.sleep(2000);
			Log.info("Successfully Clicked the Article Tab button ");
			String n = text.replaceAll("[^0-9]", "");
			System.out.println(n);
			WebElement Showingresult = getDriver().findElement(By.xpath(showingresult));
			String ss = (Showingresult.getText()).replaceAll("[^0-9]", "");
			System.out.println(ss);
			if(ss.equals(n)) {
				WebElement bodyText = getDriver().findElement(By.tagName("body"));
				if (bodyText.getText().contains("Previous")) {
					int PaginationValue = getDriver().findElements(By.xpath(pagination)).size();

					List<String> values = new ArrayList<String>();
					for (int i = 2; i <= PaginationValue - 1; i++) {
						Thread.sleep(1000);
						String paginationSelector = pagination + "[" + i + "]";
						WebElement paginationSelectorClick = getDriver().findElement(By.xpath(paginationSelector));
						Action.click(getDriver(), paginationSelectorClick);
						List<WebElement> ShopProducts = getDriver().findElements(By.xpath(shopProducts));
						for (WebElement sh : ShopProducts) {
							values.add(sh.getText());
							// Thread.sleep(200);
						}
					}
					int TotalProducts = values.size();
					System.out.println("Total products ARE " + TotalProducts);
					String firstPage = "(//div[@class='MuiBox-root css-1sfz9yc']/nav/ul/li/button)[2]";
					WebElement FirstPage = getDriver().findElement(By.xpath(firstPage));
					Action.click(getDriver(), FirstPage);
					Thread.sleep(700);
					if (TotalProducts == Integer.parseInt(ss)) {
						Log.info("Successfully Verified the Count of the Products");
					}
				}
				else {
					List<String> values = new ArrayList<String>();
					List<WebElement> ShopProducts = getDriver().findElements(By.xpath(shopProducts));
					for (WebElement sh : ShopProducts) {
						values.add(sh.getText());}
						int TotalProducts = values.size();
						System.out.println("Total products ARE " + TotalProducts);
						if (TotalProducts == Integer.parseInt(ss)) {
							Log.info("Successfully Verified the Count of the Articles");
						}
						else {
							Assert.assertTrue(false, "No. of Articles are not Equal");
						}
				}
			}
			else {
				Assert.assertTrue(false, "No. of Articles are not Equal");
			}

		}
		else {
			Assert.assertTrue(false, "Showing Result is not showing the correct numbers");
			}
		}
	
	
	String lowToHigh = "(//li[@data-value='lowToHigh'])";
	String actualPrice = "(//div[@id='productCardContentPriceTile'])";
	String highToLow = "(//li[@data-value='highToLow'])";
	String recentlyAdded ="(//div[@id='demo-simple-select-standard'])[4]";
	public void FilterByRecentlyAdded(String FilterOptions) throws InterruptedException {
		WebElement RecentlyAdded = getDriver().findElement(By.xpath(recentlyAdded));
		Action.scrollByVisibilityOfElement(getDriver(), RecentlyAdded);
		
		
		WebElement ShopTab = getDriver().findElement(By.xpath(shoptab));
		String shopText = ShopTab.getText();
		String s = shopText.replaceAll("[^0-9]", "");
		System.out.println(s);
		int n = Integer.parseInt(s);
		List<WebElement> productActPrice = getDriver().findElements(By.xpath(actualPrice)); 
			int z = productActPrice.size();
			System.out.println(z);
		if(z>0) {
		//Thread.sleep(3000);
		if(FilterOptions.equals("HighToLow")) {
			Action.click(getDriver(), RecentlyAdded);
			Thread.sleep(1000);
			WebElement HighToLow = getDriver().findElement(By.xpath(highToLow));
			System.out.println(HighToLow.getText());
			Action.click(getDriver(), HighToLow);
			Thread.sleep(2000);
		
				
				//Thread.sleep(3000);
				Log.info("inside if condition");
					for(int i=1;i<=z-1;i++) {
						
						Log.info("inside loop condition");
						String s1 = actualPrice  + "["+i+"]";
						WebElement S1 = getDriver().findElement(By.xpath(s1));
						String t1 = S1.getText().replaceAll("[^0-9.]", "");
						float f1 = Float.parseFloat(t1); 
						System.out.println(f1);
						int x =i+1;
						String s2 = actualPrice  + "["+x+"]";
						WebElement S2 = getDriver().findElement(By.xpath(s2));
						String t2 = S2.getText().replaceAll("[^0-9.]", "");
						float f2 = Float.parseFloat(t2); 
						System.out.println(f2); 
						if(f1>f2 || f1==f2) {
							Assert.assertTrue(true);
							Log.info("Correct");
						}
						else{
							System.out.println("Price is not in correct");
							Assert.assertTrue(false, "High to Low  price is not correct");}
						}
					Log.info("Successfully verified the High To Low Filter functionality");
			
		}

		 if(FilterOptions.equals("LowToHigh")) {
			  Action.click(getDriver(), RecentlyAdded);
			  Thread.sleep(1000);
			  WebElement LowToHigh = getDriver().findElement(By.xpath(lowToHigh)); 
			  System.out.println(LowToHigh.getText());
			  Action.click(getDriver(),LowToHigh);
			  Thread.sleep(2000);
			  Log.info("inside if condition");
				for(int i=1;i<=z-1;i++) {
					
					Log.info("inside loop condition");
					String s1 = actualPrice  + "["+i+"]";
					WebElement S1 = getDriver().findElement(By.xpath(s1));
					String t1 = S1.getText().replaceAll("[^0-9.]", "");
					float f1 = Float.parseFloat(t1); 
					System.out.println(f1);
					int x =i+1;
					String s2 = actualPrice  + "["+x+"]";
					WebElement S2 = getDriver().findElement(By.xpath(s2));
					String t2 = S2.getText().replaceAll("[^0-9.]", "");
					float f2 = Float.parseFloat(t2); 
					System.out.println(f2); 
					if(f1<f2 || f1==f2) {
						Assert.assertTrue(true);
						Log.info("Correct");
					}
					else {System.out.println("Price is not in correct");
					Assert.assertTrue(false, "Low to High price is not correct");
						
					}
				}
		 	}
		}
				
		 else {
			  System.out.println(n - z + " Actual Prices are not present ");
			  Assert.assertTrue(false, n - z + " Actual Prices are not present "); }
	}
	//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 MuiGrid-spacing-md-2 css-1tg6332']/div
	String ratings = "(//span[@id='sg-starRatingStyledRating'])";
	String allproduct = "(//div[@id='sg-productCardWrapper'])";
	String carticon = "//*[name()='svg' and @data-testid='ShoppingCartOutlinedIcon']";
	String wishlist = "//*[name()='svg' and @data-testid='FavoriteBorderOutlinedIcon']";
	String quickview = "(//div[contains(text(),'Quick View')])";
//	String actualPrice = "//div[@class='MuiBox-root css-70qvj9']/p";
	String discountedPrice = "//div[@id='productCardContentPriceTile']";
	String productsName = "(//div[contains(@id,'sg-productCardContentTitle')])";
	String images = "//img[@id='sg-productCardImage2']";

	public void ShopProducts() throws InterruptedException {
		Log.info("Shop functionality starts"); 
		//getDriver().navigate().refresh();
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
		int n = products1.size();
		System.out.println("Total Products are in Search Page" + n);
		Thread.sleep(2000);
		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int x = image.size();
		System.out.println(x);
		int count4 = 0;
		if (x == n) {
			for(WebElement i : image) {
				try {
				Action.mouseOverElement(getDriver(), i);
				Thread.sleep(700);
				if(i.getAttribute("srcset").contains("shopify.com")) {	
					count4++;
					}
				}catch(StaleElementReferenceException e){	
				//if((Boolean) ((JavascriptExecutor)getDriver()) .executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", i)) {
				Action.mouseOverElement(getDriver(), i);
				Thread.sleep(700);
				if(i.getAttribute("srcset").contains("shopify.com")) {	
					count4++;
					}
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

		List<WebElement> productName1 = getDriver().findElements(By.xpath(productsName));
		int j = productName1.size();
		if (j == n) {
			System.out.println("All Products name are present");
		} else {
			System.out.println(n - j + " Products name are not present ");
			softAssert.assertTrue(false, +n - j + " Products name are not present ");
		}

		
		  List<WebElement> productActPrice =
		  getDriver().findElements(By.xpath(actualPrice)); int z =
		  productActPrice.size(); if (z == n) {
		  System.out.println("All Actual Prices are present"); } else {
		  System.out.println(n - z + " Actual Prices are not present ");
		  softAssert.assertTrue(false, n - z + " Actual Prices are not present "); }
		 

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

		int d = carticon1.size();
		if (d == n) {
			System.out.println("All Wishlist icon are present");
		} else {
			System.out.println(n - d + " Wishlist icon are not present ");
			softAssert.assertTrue(false, +n - c + " Wishlist icon are not present ");
		}
		
		
		List<WebElement> Ratings = getDriver().findElements(By.xpath(ratings));

		int e = Ratings.size();
		int count7=0;
		System.out.println(e+ "Ratings icon should be present");
		if (e == n) {
			for(WebElement i1 : Ratings) {
				//Action.scrollByVisibilityOfElement(getDriver(), i);
				if(i1.isDisplayed()) {
					softAssert.assertTrue(i1.isEnabled(), "Ratings icon is not Enabled");
					Log.info("Ratings icon is successfully verified");
					count7++;
				}
			}
			if(count7 ==n ) {
				System.out.println("All Ratings icon are present");}
				else {
					softAssert.assertTrue(false, n-count7+ " Ratings icon are not present  ");
				}	
			} 
		 else {
			System.out.println(n - e + " Ratings icon are not present ");
			softAssert.assertTrue(false, +n - e + " Ratings icon are not present ");
		}

		softAssert.assertAll();
	}
	
	String colorFilter = "//p[normalize-space()='Color']";
	String sizeFilter= "//p[normalize-space()='Size']";
	String priceFilter =  "//p[normalize-space()='Price']";
	public void filterOptions() {
		WebElement PriceFilter = getDriver().findElement(By.xpath(priceFilter));
		System.out.println(PriceFilter.isEnabled());
		
		WebElement SizeFilter = getDriver().findElement(By.xpath(sizeFilter));
		System.out.println(PriceFilter.isEnabled());
		
		WebElement ColorFilter = getDriver().findElement(By.xpath(colorFilter));
		System.out.println(PriceFilter.isEnabled());
		
		
	}
}
