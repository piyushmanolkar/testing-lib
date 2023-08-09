package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;


public class ProductPage extends BaseClass {

	public ProductPage() {
		PageFactory.initElements(getDriver(), this);
	}
	SoftAssert softAssert = new SoftAssert();
	String productName = "//div[@id = 'sg-productDetailsCardProductTitle']";
	String Bigimage = "//img[@id='sg-imageMagnifierImage']";
	String smallImage = "//img[@id='sg-imageStackImage']";

	// SoftAssert softAssert = new SoftAssert();
	public void productDetailVerification() throws InterruptedException {
		WebElement smallProductImage = getDriver().findElement(By.xpath(smallImage));
		String value2 = smallProductImage.getAttribute("alt");
		System.out.println(value2);

		WebElement ProductImages = getDriver().findElement(By.xpath(Bigimage));
		String value1 = ProductImages.getAttribute("alt");
		System.out.println(value1);

		if (value1.equals(value2)) {
			boolean result = Action.isDisplayed(getDriver(), ProductImages);
			if (result) {
				Action.mouseOverElement(getDriver(), ProductImages);
				Thread.sleep(2000);
			} else {
				Assert.assertTrue(result, "Product is not visible or present");
			}
		} else {
			Assert.assertTrue(false, "The zommed image and side image are not same");
		}

		String ProductName = getDriver().findElement(By.xpath(productName)).getText();
		System.out.println(ProductName);
		String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
		for (int i = 0; i < ProductName.length(); i++) {
			char ch = ProductName.charAt(i);
			String s = String.valueOf(new char[] { ch });
			// System.out.println(s);
			if (specialCharactersString.contains(Character.toString(ch))) {
				System.out.println(s + " contains special character");

				if (ProductName.contains(s)) {
					String splits1[] = ProductName.split(s);
					System.out.println(splits1[0]);
					String actualName = splits1[0];
					if (value1.contains(actualName)) {
						System.out.println("Product name verified");
					} else {
						Assert.assertTrue(false, "Product Name are not correct");
					}
				}
				break;
			}
		}
	}

	String cartButton = "//button[@id='sg-addToCartButton']";
	String stockCount = "//input[@id='sg-counterCountInput']";

	public void availabiltyStock() {

		Log.info("AddToCart of Product Page verification");
		WebElement StockCount = getDriver().findElement(By.xpath(stockCount));
		Assert.assertEquals(StockCount.getAttribute("value"), "1", "Count is not set to 1");
		WebElement addToCartIcon = getDriver().findElement(By.xpath(cartButton));
		boolean result = Action.isDisplayed(getDriver(), addToCartIcon);
		String elementTxt = addToCartIcon.getText();
		if (elementTxt.equalsIgnoreCase("ADD TO CART") && result){
			Assert.assertTrue(addToCartIcon.isEnabled(), "Out of Stock button is not enabled");
			System.out.println("AddTOCart is enabled");
			Log.info("Successfully verified AddToCart Message");
		}
		else if(elementTxt.equalsIgnoreCase("OUT OF STOCK") && result){
			Assert.assertTrue(!addToCartIcon.isEnabled(), "Out of Stock button is enabled");
			Log.info("Product is Out of Stock");
		}
			
		
		else {
			System.out.println("Cart Icon is not present");
			Assert.assertTrue(result, "Cart Icon is not present");
		}
		Log.info("Successfully ends the availabiltyStock");
	}

	String icon = "(//*[local-name()='svg' and @data-testid='FavoriteBorderOutlinedIcon'])[1]";
	String loginbutton = "//button[normalize-space()='LOG IN']";
	String loginPageTxt = "//input[@name='email']";
	String crossbutton1 = "//*[local-name()='svg' and @data-testid='ClearIcon']";

	public void wishlistIconFunctionalityforNotLoggedIn() throws InterruptedException {
		WebElement iconFunctionality = getDriver().findElement(By.xpath(icon));
		Action.scrollByVisibilityOfElement(getDriver(), iconFunctionality);
		Action.click(getDriver(), iconFunctionality);
		Thread.sleep(1000);
		WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
		Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());
		WebElement CrossButton = getDriver().findElement(By.xpath(crossbutton1));
		Action.click(getDriver(), CrossButton);
		// Thread.sleep(5000);
	}

	String wishlistsTxt = "(//p[@class='MuiTypography-root MuiTypography-body1 css-uog56n'])[2]";
	String productnames = "//div[@class='MuiBox-root css-d8xk2t']/p";

	// String iconn = "(//*[local-name()='svg' and
	// @data-testid='FavoriteIcon'])[1]";

	public void wishlistIconFunctionalityForLoggedIn() throws InterruptedException {
		WebElement iconFunctionality = getDriver().findElement(By.xpath(icon));
		Action.scrollByVisibilityOfElement(getDriver(), iconFunctionality);
		Action.click(getDriver(), iconFunctionality);

		WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
		System.out.println("dfghjk");
		Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed());

		getDriver().findElement(By.name("email")).sendKeys(prop.getProperty("Username"));
		;
		getDriver().findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
		Thread.sleep(20000);
		getDriver().findElement(By.xpath("//button[normalize-space()='LOG IN']")).click();
		System.out.println("successfully entered the username and clicked on the Login button");
		Thread.sleep(10000);
		JavascriptExecutor j = (JavascriptExecutor) getDriver();
		j.executeScript("window.scrollBy(0,450)");
		//Action.scrollByVisibilityOfElement(getDriver(), iconFunctionality);
		System.out.println("Scrolled to the Wishlist Icon");
		// getDriver().navigate().refresh();

		WebElement iconFunctionality1 = getDriver().findElement(By.xpath(icon));
		Action.explicitWait(getDriver(), iconFunctionality1, Duration.ofSeconds(5000));
		Action.click(getDriver(), iconFunctionality1);

		JavascriptExecutor j1 = (JavascriptExecutor) getDriver();
		j1.executeScript("window.scrollBy(0,50)");
		WebElement productName1 = getDriver().findElement(By.xpath(productName));
		String text = productName1.getText();
		System.out.println(text);
		Log.info("Click on user account button");
		getDriver().findElement(By.xpath("//div[@class='MuiBox-root css-tap1yw']//img[@alt='logo']")).click();
		Action.explicitWaitbyTitle(getDriver(), "My Account", Duration.ofSeconds(10));
		// Assert.assertEquals(getDriver().getTitle(), "My Account");
		System.out.println("dfghgjkl");
		getDriver().navigate().refresh();
		Thread.sleep(2000);
		WebElement wishlistText = getDriver().findElement(By.xpath(wishlistsTxt));
		Action.scrollByVisibilityOfElement(getDriver(), wishlistText);
		System.out.println("dfghgjkl");
		List<WebElement> Wishlists = getDriver().findElements(By.xpath(productnames));
		System.out.println(Wishlists.size());
		boolean flag = false;
		for (WebElement webElement : Wishlists) {
			String name = webElement.getText();
			System.out.println(name);
			String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
			for (int i = 0; i < name.length(); i++) {
				char ch = name.charAt(i);
				// System.out.println("test1");
				String s = String.valueOf(new char[] { ch });
				// System.out.println(s);
				if (specialCharactersString.contains(Character.toString(ch))) {
					System.out.println(s + " contains special character");

					if (name.contains(s)) {
						String splits1[] = name.split(s);
						System.out.println(splits1[0]);
						String actualName = splits1[0];
						if (text.contains(actualName)) {
							System.out.println("Product is present in Wishlist tab ");
							flag = true;
						} else {
							Assert.assertTrue(false, " Not Present in Wishlist Tab ");

						}

					}
					break;

				}
			}
			if (flag) {
				break;
			} 

		}
	}
	
	
	String descrtiption = "//div[@id='sg-productDetailsCardProductDescription']";       
	public void readMoreFunctionality() {
		WebElement readMoreBtn = getDriver()
				.findElement(By.xpath("//div[@id='sg-productDetailsCardReadAllLink']"));
		String text = getDriver()
				.findElement(By.xpath(descrtiption)).getText();
		if (!text.isEmpty()) {
			if (readMoreBtn.isDisplayed()) {
				String text1 = getDriver()
						.findElement(By.xpath(descrtiption))
						.getText();
				// System.out.println(text);
				String smallDes = text1.replace("...", "");
				System.out.println(smallDes);
				Action.click(getDriver(), readMoreBtn);
				if ((readMoreBtn.getText()).equalsIgnoreCase("Read Less")) {
					Assert.assertTrue(true);

					String FullDes = getDriver()
							.findElement(By.xpath(descrtiption))
							.getText();
					System.out.println(FullDes);
					if (FullDes.contains(smallDes)) {
						Assert.assertTrue(true);
					} else
						Assert.assertTrue(false, "Small description are not present in Full description");
				}

				else {
					Assert.assertTrue(false, "After Clicking on Read More button Read Less button is not visible");
				}
			}
		} else {
			System.out.println("Description for the product are not present");
			Assert.assertTrue(false, "Description for the product are not present");
		}
	}
	
	String allProducts ="(//div[@id='sg-productCardWrapper' and @type ='Products'])";
	public void productTiltProducts() {
		String productYouMightLikeSection = "//div[@id='sg-recomendationProductCustomWrapper']";
		WebElement products = getDriver().findElement(By.xpath(productYouMightLikeSection));
		Action.scrollByVisibilityOfElement(getDriver(), products);
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allProducts));
		int n = products1.size();
		System.out.println(n);
		if(n>0) {
		int count=1;
		int count1 = 1;
		for(WebElement j: products1) {
			String productname = "("+ allProducts +")["+ count + "]/div[1]/div[1]"; 
			WebElement ProductName1 = getDriver().findElement(By.xpath(productname));
			//Action.scrollByVisibilityOfElement(getDriver(), ProductName1);
			String PName = ProductName1.getText();
			System.out.println(PName);
			softAssert.assertTrue(ProductName1.isDisplayed());
			
			String productprice =  allProducts +"["+ count + "]/div/div[2]/div";
			WebElement ProductPrice = getDriver().findElement(By.xpath(productprice));
			String PPrice = ProductPrice.getText();
			System.out.println(PPrice);
			
			softAssert.assertTrue(ProductPrice.isDisplayed(), "Price is not present on the product Tile");
			
			String productRating =  allProducts +"["+ count + "]/div/div[4]/span";
			WebElement ProductRating = getDriver().findElement(By.xpath(productRating));
			String PRating = ProductRating.getAttribute("aria-label");
			System.out.println(PRating);
			
			String quickView = allProducts +"["+ count + "]/div/p";
			WebElement QuickView = getDriver().findElement(By.xpath(quickView));
			if(QuickView.isDisplayed()) {
				Action.click(getDriver(), QuickView);
				String quickPName = "(//div[@class='MuiTypography-root MuiTypography-body1 css-vcbw66'])[2]";
				WebElement QuickPName = getDriver().findElement(By.xpath(quickPName));
				softAssert.assertTrue(QuickPName.getText().contains(PName), "Quick view Link does not contain same name as product Tile");
				Log.info("Successfully verifying the product name with quick view Product name");
				
				String quickPPrice = "(//div[@class='MuiTypography-root MuiTypography-body1 css-1b9o26n'])[2]";
				WebElement QuickPPrice = getDriver().findElement(By.xpath(quickPPrice));
				System.out.println(QuickPPrice.getText());
				softAssert.assertEquals(QuickPPrice.getText(), PPrice, "Quick view Link does not contain same Price as product Tile");
				Log.info("Successfully verifying the product Price with quick view Product Price");
				
				String quikViewPDesc = "(//div[@class='MuiTypography-root MuiTypography-body1 css-vm4s28'])";
				WebElement QuikViewPDesc = getDriver().findElement(By.xpath(quikViewPDesc));
				softAssert.assertTrue(QuikViewPDesc.isDisplayed(), "Quick view Product does not contain Product description");
				Log.info("Successfully verifying the product Description on clicking the quick view ");
				
				String productDetail1 = "//div[contains(@class,'css-rkcvek')]/a";
				WebElement ProductDetail1 = getDriver().findElement(By.xpath(productDetail1));
				softAssert.assertTrue(ProductDetail1.isDisplayed() && ProductDetail1.getText().equals("View Product Details"), "Quick view Product does not contain View Product Details link");
				softAssert.assertTrue(ProductDetail1.isEnabled(), "View Product Detail is enabled ");
				Log.info("Successfully verifying the View Product Details on clicking the quick view ");
				
			count++;
			}
		}
		}
		else{
			Assert.assertTrue(false, "No Products are available");
			
		}
	}
	
	
	
	//String allproduct = "(//div[@id='sg-productCardWrapper' and @type ='Products'])";
	String carticon = "//*[name()='svg' and @data-testid='ShoppingCartOutlinedIcon']";
	String wishlist = "//*[name()='svg' and @data-testid='FavoriteBorderOutlinedIcon']";
	String quickview = "//p[@id='sg-quickViewButton']";
	String discountedPrice = "//div[@class='MuiBox-root css-70qvj9']/p";
	String actualPrice = "//div[@id='productCardContentPriceTile']";
	String productsName = "(//div[@id='sg-productCardWrapper' and @type ='Products'])/div[1]/div[1]";
	String images = "(//div[@id='sg-productCardWrapper' and @type ='Products'])/span/img";
	

	public void wefoundOtherSections() throws InterruptedException {
		
		WebElement bodyTag = getDriver().findElement(By.tagName("body"));
		Thread.sleep(1000);
		//System.out.println(bodyTag.getText());
		if(bodyTag.getText().contains("WE FOUND OTHER COURSES YOU MIGHT LIKE") || bodyTag.getText().contains("WE FOUND OTHER PRODUCTS YOU MIGHT LIKE") ) {
			
		String productYouMightLikeSection = "//div[@id='sg-recomendationProductTitle']";
		WebElement products = getDriver().findElement(By.xpath(productYouMightLikeSection));
		Action.scrollByVisibilityOfElement(getDriver(), products);

		List<WebElement> products1 = getDriver().findElements(By.xpath(allProducts));
		int n = products1.size();
		System.out.println(n);

		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int x = image.size();
		int count4 = 0;
		if (x == n) {
			for(WebElement i : image) {
				Action.mouseOverElement(getDriver(), i);
				if(i.getAttribute("srcset").contains("shopify.com")) {
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

		softAssert.assertAll();
		}
		else {
			System.out.println("We Found other Product/Courses you might like section is not present");
			Assert.assertTrue(false, "We Found other Product/Courses you might like section is not present");
			
		}
	}
	
	/*
	 * public void wefoundOtherSections1() { String productYouMightLikeSection =
	 * "/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[5]/div[1]";
	 * WebElement products =
	 * getDriver().findElement(By.xpath(productYouMightLikeSection));
	 * Action.scrollByVisibilityOfElement(getDriver(), products);
	 * 
	 * List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
	 * int n = products1.size(); System.out.println(n);
	 * 
	 * List<WebElement> image = getDriver().findElements(By.xpath(images)); int x =
	 * image.size(); int count4 = 0; if (x == n) { for(WebElement i : image) {
	 * Action.mouseOverElement(getDriver(), i);
	 * if(i.getAttribute("srcset").contains("shopify.com")) { count4++; } }
	 * if(count4 ==n ) { System.out.println("All " + count4 +
	 * " images are present");} else { softAssert.assertTrue(false, n-count4+
	 * " Images are not present  "); } } else { System.out.println(n - x +
	 * " Images are not present "); softAssert.assertTrue(false, +n - x +
	 * " Images are not present "); }
	 * 
	 * List<WebElement> productName1 =
	 * getDriver().findElements(By.xpath(productsName)); int j =
	 * productName1.size(); if (j == n) {
	 * System.out.println("All Products name are present"); } else {
	 * System.out.println(n - j + " Products name are not present ");
	 * softAssert.assertTrue(false, +n - j + " Products name are not present "); }
	 * 
	 * List<WebElement> productActPrice =
	 * getDriver().findElements(By.xpath(actualPrice)); int z =
	 * productActPrice.size(); if (z == n) {
	 * System.out.println("All Actual Prices are present"); } else {
	 * System.out.println(n - z + " Actual Prices are not present ");
	 * softAssert.assertTrue(false, n - z + " Actual Prices are not present "); }
	 * 
	 * List<WebElement> quickviewlink =
	 * getDriver().findElements(By.xpath(quickview)); int i = quickviewlink.size();
	 * if (i == n) { System.out.println("All Quick View links are present"); } else
	 * { System.out.println(n - i + " Quick View links are not present ");
	 * softAssert.assertTrue(false, n - i + " Quick View links are not present "); }
	 * 
	 * List<WebElement> carticon1 = getDriver().findElements(By.xpath(carticon));
	 * int c = carticon1.size(); if (c == n) {
	 * System.out.println("All carticon are present"); } else { System.out.println(n
	 * - c + " carticon are not present "); softAssert.assertTrue(false, +n - c +
	 * " carticon are not present "); }
	 * 
	 * List<WebElement> wishlist1 = getDriver().findElements(By.xpath(wishlist));
	 * 
	 * int d = carticon1.size(); if (d == n) {
	 * System.out.println("All Wishlist icon are present"); } else {
	 * System.out.println(n - d + " Wishlist icon are not present ");
	 * softAssert.assertTrue(false, +n - c + " Wishlist icon are not present "); }
	 * 
	 * softAssert.assertAll(); }
	 */
	
	
	String faq = "//div[normalize-space()='FAQ']";
	String recentQuestions = "(//div[@class='MuiBox-root css-1hm6ahe'])";
	String viewAll= "(//div[@class='MuiBox-root css-1hm6ahe'])/a";
	String Questions = "(//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation0 MuiAccordion-root MuiAccordion-rounded css-iu7gzq'])";
	String  totalQue = "(//div[@class='MuiButtonBase-root MuiAccordionSummary-root css-st02k6'])";
	String queText = "//div[@class='MuiTypography-root MuiTypography-body1 css-zc3yas']";
	String plusIcon = "//*[name()='svg' and @data-testid='AddIcon']";
	String removeIcon = "//*[name()='svg' and @data-testid='RemoveIcon']";
	String ansText = "(//div[@class='MuiTypography-root MuiTypography-body1 css-otef3t'])";
	public void faqSections() throws InterruptedException {
		
		WebElement bodyTag = getDriver().findElement(By.tagName("body"));
		Thread.sleep(1000);
		System.out.println(bodyTag.getText());
		if(bodyTag.getText().contains("FAQ")) {
		
		WebElement faqs = getDriver().findElement(By.xpath(faq));
		Action.scrollByVisibilityOfElement(getDriver(), faqs);
		Log.info("Successfully Scroll to the FAQ section");
		WebElement RecentQuestions = getDriver().findElement(By.xpath(recentQuestions));
		softAssert.assertEquals(RecentQuestions.getText(), "Recent Questions");
		Log.info("Successfully veriied the recent questions text");
		
		List<WebElement>Question = getDriver().findElements(By.xpath(Questions));
		int size = Question.size();
		
		WebElement PlusIcon = getDriver().findElement(By.xpath(plusIcon));
		WebElement QueText = getDriver().findElement(By.xpath(queText));
			
		List<WebElement>Que = getDriver().findElements(By.xpath(totalQue));
		int queCount  = Que.size();
		if(size ==  queCount) {
			for(WebElement i : Que) {
				String text = QueText.getText();
				if(text.equals(null)) {
					System.out.println("Question text are not present");
					softAssert.assertTrue(false, " Question text are not present");
				
					if (!PlusIcon.isDisplayed()){
					System.out.println("Plus Icon are not present");
					softAssert.assertTrue(false, " Plus Icon are not present");
					}
				}	
				else {
					Action.click(getDriver(), i);
					System.out.println("Successfully verified the FAQ Sections");
					Thread.sleep(2000);
					WebElement AnsText = getDriver().findElement(By.xpath(ansText));
					WebElement RemoveIcon = getDriver().findElement(By.xpath(removeIcon));
					if(!RemoveIcon.isDisplayed()) {
						System.out.println("Remove Icon are not present");
						softAssert.assertTrue(false, " Remove Icon are not present");
					
					 if(!AnsText.equals(null)) {
						System.out.println("Question text  after click are not present");
						softAssert.assertTrue(false, " Question text after click are not present");
					 }
					} 
				}
			}
		}
		else {
			softAssert.assertTrue(false, " Total faq questions are not present");
		}
		}
		else {
			System.out.println("FAQ section is not present on the product");
			Assert.assertTrue(false,"FAQ section is not present on the product");
		}
	}
	
	
	String bookMarkicon = "//*[name()='svg' and @data-testid='BookmarkBorderIcon']";
	String mightLikeProductDescription = "(//div[@id='sg-productCardWrapper' and @type ='article' ])/div/div[2]";
	String mightLikeProductNames = "(//div[@id='sg-productCardWrapper' and @type ='article' ])/div/div[1]";
	String mightLikeallproduct = "(//div[@id='sg-productCardWrapper' and @type ='article' ])";
	String mightLikeimages = "(//div[@id='sg-productCardWrapper' and @type ='article' ])/span/span/img";
	String mightLike1 = "(//div[normalize-space()='We found other content you might like'])";
	public void weFoundOtherContentYouMightLike() throws InterruptedException {
		
		WebElement bodyTag = getDriver().findElement(By.tagName("body"));
		Thread.sleep(2000);
		//System.out.println(bodyTag.getText());
		if(bodyTag.getText().contains("WE FOUND OTHER CONTENT YOU MIGHT LIKE")) {
		
		Action.implicitWait(getDriver(), 10);
			WebElement MightLike1 = getDriver().findElement(By.xpath(mightLike1));
			Action.scrollByVisibilityOfElement(getDriver(), MightLike1);
			
			List<WebElement> MightLikeallproduct = getDriver().findElements(By.xpath(mightLikeallproduct));
			int n = MightLikeallproduct.size();
			System.out.println(n + " Articles should be present in Might Like section");
			int count5 =0;
			List<WebElement> MightLikeimages = getDriver().findElements(By.xpath(mightLikeimages));
			int x = MightLikeimages.size();
			System.out.println(x+ " Articles should be present in Might Like section");
			//Thread.sleep(2000);
			if(x==n) {for (WebElement i : MightLikeimages) {
				Action.mouseOverElement(getDriver(), i);
				//Action.explicitWait(getDriver(), i, Duration.ofSeconds(5));
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
		else {
			System.out.println("We Found other content you might like section is not present");
			Assert.assertTrue(false, "We Found other content you might like section is not present");
			
		}
	}
	String readAllReviews = "//div[@id='productDetailsProductReviewWrapper']/a";
	String rating = "(//div[@id='sg-starRatingWrapper'])[1]";
	String reviewscount = "(//div[@id='sg-starRatingWrapper'])[1]/div";
	String actPrice = "//div[@id='sg-productDetailsCardProductPrice']";
	String basic_tabs = "//button[@id='sg-tabsComponentTab']";
	public void productElementsVerification() {
		WebElement ProductName = getDriver().findElement(By.xpath(productName));
		if(ProductName.isDisplayed()) {
			String pname = ProductName.getText();
			System.out.println(pname + " is the name of the product");
			Log.info("Successfully verified the presence of the product name");
		}
		else {
			softAssert.assertTrue(ProductName.isDisplayed(), "Product name is not present");
			Log.info("Product name is not present");
		}
		
		WebElement ProductPrice = getDriver().findElement(By.xpath(actPrice));
		if(ProductPrice.isDisplayed()) {
			String productPrice = ProductPrice.getText();
			System.out.println(productPrice + " is the price of the product");
			Log.info("Successfully verified the presence of the product Price");
		}
		else {
			softAssert.assertTrue(ProductName.isDisplayed(), "Product Price is not present");
			Log.info("Product Price is not present");
		}
		
		WebElement ProductRating = getDriver().findElement(By.xpath(rating));
		if(ProductRating.isDisplayed()) {
			WebElement Reviewscount = getDriver().findElement(By.xpath(reviewscount));
			String s = Reviewscount.getText().replaceAll("[()]", "");
			System.out.println(s);
			if(!s.equals("0")) {
				
				
				WebElement ReadAllReviews = getDriver().findElement(By.xpath(readAllReviews));
				if(ReadAllReviews.isDisplayed()) {
					Assert.assertTrue(ReadAllReviews.isEnabled(), "ReadAllReviews is not working properly");
					Log.info("Successfully verified the presence of the ReadAllReviews link");
				}
				
				else {
					Log.info("Product ReadAllReviews is not present");
				}
			}
			else {
				WebElement e = getDriver().findElement(By.tagName("body"));
				if(e.getText().contains("Read all reviews")) {
					Assert.assertTrue(false, "Read All Reviews is Present but not able to see it");
				}
				else {
					System.out.println("Read All Reviews button is not present");
				}
				
			}
			//System.out.println(ProductRating.getText() + " is the ratings of the product");
			Log.info("Successfully verified the presence of the product rating");
		}
		else {
			softAssert.assertTrue(ProductName.isDisplayed(), "Product rating is not present");
			Log.info("Product rating is not present");
		}
		
		
		
		String returnText = "//p[contains(@id,'sg-shippingDetailsCommonDesc')]";
		String shippingText = "//p[@id='sg-shippingDetailsCommonDesc']";
		List<WebElement> Values = getDriver().findElements(By.xpath(basic_tabs));
		System.out.println(Values.size());
		if(Values.size()==2) {
			for( WebElement i: Values) {
				if(i.getText().equals("Shipping & Delivery")) {
					Action.click(getDriver(), i);
					WebElement ShippingText = getDriver().findElement(By.xpath(shippingText));
					if(ShippingText.getText().equals(null)) {
						Assert.assertTrue(false, "Not Data is present in the Shipping & Delivery section");
					}
					Log.info("Successfully verified the presence of the Shipping & Delivery Text");
				}
				else if(i.getText().equals("Return")) {
					Action.click(getDriver(), i);
					WebElement ReturnText = getDriver().findElement(By.xpath(returnText));
					String text1 = ReturnText.getText();
					String actualtext = "Returns are accepted on this product within 30 days of receipt. Item must be returned unused, with tags, in its original packaging, along with a completed return form.";
					
					Assert.assertEquals(text1, actualtext, "Return Text is not similar");
					Log.info("Product is Returnable and successfully verified");
				}
				else if(i.getText().equals("Non-Returnable")) {
					Log.info("Product is non-returnable");
				}
				
			}
		}
				
	}
	//String Value = "//li[@role = 'option' and @data-value = '"+value+"']";
	String dropdown ="//div[contains(@id,'sg-productVariantSelect')]";
	public void productDropdown(String value) throws InterruptedException {
		WebElement Dropdown =  getDriver().findElement(By.xpath(dropdown));
		Action.click(getDriver(), Dropdown);
		
		Log.info("Successfully Clicked on the Dropdown");
		String Value = "//li[@role = 'option' and @data-value = '"+value+"']";
		
		WebElement values = getDriver().findElement(By.xpath(Value));
		Action.click(getDriver(), values);
		
		Log.info("Successfully Clicked on the desired value "+ value +" of the dropdwon");
		WebElement ProductName = getDriver().findElement(By.xpath(productName));
		System.out.println(ProductName.getText());
		if(ProductName.getText().contains(value)) {
			Log.info("Succssfully change the product to the desired Value");
		}
		else {
			Assert.assertTrue(false, "Not able to change the value from dropdown");
		}
	}
	
	
	String highlightContent = "(//div[@class='MuiBox-root css-1umfg2p'])/div";
	String highlightImages = "//div[@class='MuiBox-root css-12hir4b']/span/img";
	String courses = "//div[@class='MuiBox-root css-12hir4b']";
	String whatYouget = "(//div[contains(@class,'css-bp10qf')])[1]";
	public void whatYouGetSections() {
		WebElement WhatYouget  = getDriver().findElement(By.xpath(whatYouget));
		softAssert.assertEquals(WhatYouget.getText(), "WHAT YOU GET", "Course Highlights text is not present on the UI");
		Action.scrollByVisibilityOfElement(getDriver(), WhatYouget);
		Log.info("WhatYouget is successfully present on the UI");
		
		List<WebElement> Courses =getDriver().findElements(By.xpath(courses));
		int c = Courses.size();
		System.out.println(c);
		List<WebElement> HighlightImages =getDriver().findElements(By.xpath(highlightImages));
		int d = HighlightImages.size();
		System.out.println(d);
		if(d==c) {
			int count5=0;
			Log.info("sdfghjkl");
			for (WebElement i : HighlightImages) {
				Action.mouseOverElement(getDriver(), i);
				if (i.getAttribute("srcset").contains("amazonaws.com")) {
					count5++;
				}
			}	
				if (count5 == d) {
					System.out.println("All " + count5 + " highlight images are present");
				} else {
					softAssert.assertTrue(false, d - count5 + " Highlight Images are not present  ");
				}
			}
				else {
				System.out.println(c-d + " Highlight images are not present ");
				softAssert.assertTrue(false, +c-d+" Highlight Images are not present ");
				}
		
		List<WebElement> HighlightContent =getDriver().findElements(By.xpath(highlightContent));
		int e = HighlightContent.size();
		System.out.println(e);
		if(d==e) {
			for(WebElement i:  HighlightContent) {
				Action.mouseOverElement(getDriver(), i);
				if(!i.getText().equals(null)) {
					Assert.assertTrue(i.isDisplayed());
					System.out.println(i.getText());
					Log.info("Successfully verified the content of the highlights");
				}
				else {
					softAssert.assertTrue(i.getText().equals(null));
					Log.info("Content is not present in the highlight images");
				}
			}
		}
		
		softAssert.assertAll();
	}
}
