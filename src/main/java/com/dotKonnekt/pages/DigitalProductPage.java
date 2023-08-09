package com.dotKonnekt.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class DigitalProductPage extends BaseClass {

	SoftAssert softAssert = new SoftAssert();
	public DigitalProductPage() {
		PageFactory.initElements(getDriver(), this);
	}

	String productName = "//div[@id='sg-productDetailsCardProductTitle']";
	String Bigimage = "(//img[@id='sg-imageMagnifierImage'])";
	String smallImage = "(//img[@id='sg-imageStackImage2'])";
	String video = "//div[@id='sg-componentSelectedImageContainer']//video//source";

	// SoftAssert softAssert = new SoftAssert();
	public void productDetailVerification() throws InterruptedException {
		WebElement smallProductImage = getDriver().findElement(By.xpath(smallImage));
		String value2 = smallProductImage.getAttribute("alt");
		System.out.println(value2);
		String pagesource = getDriver().getPageSource();
		// System.out.println(pagesource);
		Log.info("Successfully get the pagesource");
		if (pagesource.contains("video")) {
			WebElement ProductVideo = getDriver().findElement(By.xpath(video));
			String value1 = ProductVideo.getAttribute("src");
			// System.out.println(value1);

			if (value1.contains("shopify.com")) {

				Action.mouseOverElement(getDriver(), ProductVideo);
				Thread.sleep(500);
				Log.info("Successfully verified the presence of the video");
			}
		} else {
			WebElement ProductImages = getDriver().findElement(By.xpath(Bigimage));
			String value1 = ProductImages.getAttribute("alt");
			System.out.println(value1);

			if (value1.equals(value2)) {
				boolean result = Action.isDisplayed(getDriver(), ProductImages);
				if (result) {
					Action.mouseOverElement(getDriver(), ProductImages);
					Thread.sleep(400);
				} else {
					Assert.assertTrue(result, "Product is not visible or present");
				}
			} else {
				Assert.assertTrue(false, "The zommed image and side image are not same");
			}

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
					if (value2.contains(actualName)) {
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

	public void availabiltyStock() {

		Log.info("AddToCart of Product Page verification");
		WebElement addToCartIcon = getDriver().findElement(By.xpath(cartButton));
		boolean result = Action.isDisplayed(getDriver(), addToCartIcon);
		String elementTxt = addToCartIcon.getText();
		if (elementTxt.equalsIgnoreCase("ADD TO CART") && result) {
			Assert.assertTrue(addToCartIcon.isEnabled(), "Out of Stock button is not enabled");
			System.out.println("AddTOCart is enabled");
			Log.info("Successfully verified AddToCart Message");
		} else if (elementTxt.equalsIgnoreCase("OUT OF STOCK") && result) {
			Assert.assertTrue(!addToCartIcon.isEnabled(), "Out of Stock button is enabled");
			Log.info("Product is Out of Stock");
		}

		else {
			System.out.println("Cart Icon is not present");
			Assert.assertTrue(result, "Cart Icon is not present");
		}
		Log.info("Successfully ends the availabiltyStock");
	}

	/*
	 * String wishlistsTxt =
	 * "(//p[@class='MuiTypography-root MuiTypography-body1 css-uog56n'])[2]";
	 * String productnames = "//div[@class='MuiBox-root css-d8xk2t']/p";
	 * 
	 * // String iconn = "(//*[local-name()='svg' and
	 * // @data-testid='FavoriteIcon'])[1]";
	 * 
	 * public void wishlistIconFunctionalityForLoggedIn() throws
	 * InterruptedException { WebElement iconFunctionality =
	 * getDriver().findElement(By.xpath(icon));
	 * Action.scrollByVisibilityOfElement(getDriver(), iconFunctionality);
	 * Action.click(getDriver(), iconFunctionality);
	 * 
	 * WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
	 * System.out.println("dfghjk"); Action.explicitWait(getDriver(), login,
	 * Duration.ofSeconds(10));
	 * Assert.assertTrue(getDriver().findElement(By.xpath(loginbutton)).isDisplayed(
	 * ));
	 * 
	 * getDriver().findElement(By.name("email")).sendKeys(prop.getProperty(
	 * "Username")); ;
	 * getDriver().findElement(By.name("password")).sendKeys(prop.getProperty(
	 * "Password")); Thread.sleep(20000);
	 * getDriver().findElement(By.xpath("//button[normalize-space()='LOG IN']")).
	 * click(); System.out.
	 * println("successfully entered the username and clicked on the Login button");
	 * Thread.sleep(10000); JavascriptExecutor j = (JavascriptExecutor) getDriver();
	 * j.executeScript("window.scrollBy(0,450)");
	 * //Action.scrollByVisibilityOfElement(getDriver(), iconFunctionality);
	 * System.out.println("Scrolled to the Wishlist Icon"); //
	 * getDriver().navigate().refresh();
	 * 
	 * WebElement iconFunctionality1 = getDriver().findElement(By.xpath(icon));
	 * Action.explicitWait(getDriver(), iconFunctionality1,
	 * Duration.ofSeconds(5000)); Action.click(getDriver(), iconFunctionality1);
	 * 
	 * 
	 * Thread.sleep(5000); System.out.println("fdghjkcgvhj");
	 * 
	 * 
	 * WebElement wish = getDriver().findElement(By.xpath(iconn));
	 * Assert.assertTrue(wish.isDisplayed());
	 * 
	 * 
	 * 
	 * Thread.sleep(5000);
	 * 
	 * // getDriver().navigate().refresh(); // Action.pageLoadTimeOut(getDriver(),
	 * 10); JavascriptExecutor j1 = (JavascriptExecutor) getDriver();
	 * j1.executeScript("window.scrollBy(0,50)"); WebElement productName1 =
	 * getDriver().findElement(By.xpath(productName)); String text =
	 * productName1.getText(); System.out.println(text);
	 * Log.info("Click on user account button"); getDriver().findElement(By.
	 * xpath("//div[@class='MuiBox-root css-tap1yw']//img[@alt='logo']")).click();
	 * Action.explicitWaitbyTitle(getDriver(), "My Account",
	 * Duration.ofSeconds(10)); // Assert.assertEquals(getDriver().getTitle(),
	 * "My Account"); System.out.println("dfghgjkl");
	 * getDriver().navigate().refresh(); Thread.sleep(2000); WebElement wishlistText
	 * = getDriver().findElement(By.xpath(wishlistsTxt));
	 * Action.scrollByVisibilityOfElement(getDriver(), wishlistText);
	 * System.out.println("dfghgjkl"); List<WebElement> Wishlists =
	 * getDriver().findElements(By.xpath(productnames));
	 * System.out.println(Wishlists.size()); boolean flag = false; for (WebElement
	 * webElement : Wishlists) { String name = webElement.getText();
	 * System.out.println(name); String specialCharactersString =
	 * "!@#$%&*()'+,-./:;<=>?[]^_`{|}"; for (int i = 0; i < name.length(); i++) {
	 * char ch = name.charAt(i); // System.out.println("test1"); String s =
	 * String.valueOf(new char[] { ch }); // System.out.println(s); if
	 * (specialCharactersString.contains(Character.toString(ch))) {
	 * System.out.println(s + " contains special character");
	 * 
	 * if (name.contains(s)) { String splits1[] = name.split(s);
	 * System.out.println(splits1[0]); String actualName = splits1[0]; if
	 * (text.contains(actualName)) {
	 * System.out.println("Product is present in Wishlist tab "); flag = true; }
	 * else { Assert.assertTrue(false, " Not Present in Wishlist Tab ");
	 * 
	 * }
	 * 
	 * } break;
	 * 
	 * } } if (flag) { break; }
	 * 
	 * } }
	 */

	/*
	 * String descrtiption =
	 * "//div[@class='MuiTypography-root MuiTypography-body1 css-9p1hyi']";
	 * 
	 * public void readMoreFunctionality() { WebElement readMoreBtn = getDriver()
	 * .findElement(By.
	 * xpath("//div[@class='MuiTypography-root MuiTypography-body1 css-rkcvek']"));
	 * String text = getDriver().findElement(By.xpath(descrtiption)).getText(); if
	 * (!text.isEmpty()) { if (readMoreBtn.isDisplayed()) { String text1 =
	 * getDriver().findElement(By.xpath(descrtiption)).getText(); //
	 * System.out.println(text); String smallDes = text1.replace("...", "");
	 * System.out.println(smallDes); Action.click(getDriver(), readMoreBtn); if
	 * ((readMoreBtn.getText()).equalsIgnoreCase("Read Less")) {
	 * Assert.assertTrue(true);
	 * 
	 * String FullDes = getDriver().findElement(By.xpath(descrtiption)).getText();
	 * System.out.println(FullDes); if (FullDes.contains(smallDes)) {
	 * Assert.assertTrue(true); } else Assert.assertTrue(false,
	 * "Small description are not present in Full description"); }
	 * 
	 * else { Assert.assertTrue(false,
	 * "After Clicking on Read More button Read Less button is not visible"); } } }
	 * else { System.out.println("Description for the product are not present");
	 * Assert.assertTrue(false, "Description for the product are not present"); } }
	 */

	/*
	 * String allproduct = "(//div[@class='swiper-wrapper'])[1]/div/div"; String
	 * carticon = "//*[name()='svg' and @data-testid='ShoppingCartOutlinedIcon']";
	 * String wishlist =
	 * "//*[name()='svg' and @data-testid='FavoriteBorderOutlinedIcon']"; String
	 * quickview =
	 * "//p[@class='MuiTypography-root MuiTypography-body1 css-xrfgiq']"; String
	 * discountedPrice = "//div[@class='MuiBox-root css-70qvj9']/p"; String
	 * actualPrice =
	 * "//div[@class='MuiTypography-root MuiTypography-body1 css-lgaoco']"; String
	 * productsName = "(//div[@class='swiper-wrapper'])[1]/div/div/div/div[1]";
	 * String images = "(//div[@class='swiper-wrapper'])[1]/div/div/span/img";
	 * SoftAssert softAssert = new SoftAssert();
	 * 
	 * public void wefoundOtherSections() { Log.info("sdfghjkl"); String
	 * courseYouMightLikeSection =
	 * "/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[5]/div[1]";
	 * WebElement courses =
	 * getDriver().findElement(By.xpath(courseYouMightLikeSection));
	 * Action.scrollByVisibilityOfElement(getDriver(), courses);
	 * 
	 * List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
	 * int n = products1.size(); System.out.println(n);
	 * 
	 * List<WebElement> image = getDriver().findElements(By.xpath(images)); int x =
	 * image.size(); int count4 = 0; if (x == n) { for (WebElement i : image) {
	 * Action.mouseOverElement(getDriver(), i); if
	 * (i.getAttribute("srcset").contains("shopify.com")) { count4++; } } if (count4
	 * == n) { System.out.println("All " + count4 + " images are present"); } else {
	 * softAssert.assertTrue(false, n - count4 + " Images are not present  "); } }
	 * else { System.out.println(n - x + " Images are not present ");
	 * softAssert.assertTrue(false, +n - x + " Images are not present "); }
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

	/*
	 * String faq = "//div[normalize-space()='FAQ']"; String recentQuestions =
	 * "(//div[@class='MuiBox-root css-1hm6ahe'])"; String viewAll =
	 * "(//div[@class='MuiBox-root css-1hm6ahe'])/a"; String Questions =
	 * "(//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation0 MuiAccordion-root MuiAccordion-rounded css-iu7gzq'])"
	 * ; String totalQue =
	 * "(//div[@class='MuiButtonBase-root MuiAccordionSummary-root css-st02k6'])";
	 * String queText =
	 * "//div[@class='MuiTypography-root MuiTypography-body1 css-zc3yas']"; String
	 * plusIcon = "//*[name()='svg' and @data-testid='AddIcon']"; String removeIcon
	 * = "//*[name()='svg' and @data-testid='RemoveIcon']"; String ansText =
	 * "(//div[@class='MuiTypography-root MuiTypography-body1 css-otef3t'])";
	 * 
	 * public void faqSections() throws InterruptedException { WebElement faqs =
	 * getDriver().findElement(By.xpath(faq));
	 * Action.scrollByVisibilityOfElement(getDriver(), faqs);
	 * Log.info("Successfully Scroll to the FAQ section"); WebElement
	 * RecentQuestions = getDriver().findElement(By.xpath(recentQuestions));
	 * softAssert.assertEquals(RecentQuestions.getText(), "Recent Questions");
	 * Log.info("Successfully veriied the recent questions text");
	 * 
	 * List<WebElement> Question = getDriver().findElements(By.xpath(Questions));
	 * int size = Question.size();
	 * 
	 * WebElement PlusIcon = getDriver().findElement(By.xpath(plusIcon)); WebElement
	 * QueText = getDriver().findElement(By.xpath(queText));
	 * 
	 * List<WebElement> Que = getDriver().findElements(By.xpath(totalQue)); int
	 * queCount = Que.size(); if (size == queCount) { for (WebElement i : Que) {
	 * String text = QueText.getText(); if (text.equals(null)) {
	 * System.out.println("Question text are not present");
	 * softAssert.assertTrue(false, " Question text are not present");
	 * 
	 * if (!PlusIcon.isDisplayed()) {
	 * System.out.println("Plus Icon are not present"); softAssert.assertTrue(false,
	 * " Plus Icon are not present"); } } else { Action.click(getDriver(), i);
	 * System.out.println("Successfully verified the FAQ Sections");
	 * Thread.sleep(2000); WebElement AnsText =
	 * getDriver().findElement(By.xpath(ansText)); WebElement RemoveIcon =
	 * getDriver().findElement(By.xpath(removeIcon)); if (!RemoveIcon.isDisplayed())
	 * { System.out.println("Remove Icon are not present");
	 * softAssert.assertTrue(false, " Remove Icon are not present");
	 * 
	 * if (!AnsText.equals(null)) {
	 * System.out.println("Question text  after click are not present");
	 * softAssert.assertTrue(false, " Question text after click are not present"); }
	 * } } } } else { softAssert.assertTrue(false,
	 * " Total faq questions are not present"); }
	 * 
	 * }
	 */

	/*
	 * String bookMarkicon =
	 * "//*[name()='svg' and @data-testid='BookmarkBorderIcon']"; String
	 * mightLikeProductDescription =
	 * "(//div[@class='swiper-wrapper'])/div/div/div/div[2]"; String
	 * mightLikeProductNames =
	 * "(//div[@class='swiper-wrapper'])/div/div/div/div[1]"; String
	 * mightLikeallproduct = "(//div[@class='swiper-wrapper'])/div/div"; String
	 * mightLikeimages = "(//div[@class='swiper-wrapper'])/div/div/span/img"; String
	 * mightLike1 =
	 * "(//div[normalize-space()='We found other content you might like'])";
	 * 
	 * public void weFoundOtherContentYouMightLike() throws InterruptedException {
	 * Action.implicitWait(getDriver(), 10); WebElement MightLike1 =
	 * getDriver().findElement(By.xpath(mightLike1));
	 * Action.scrollByVisibilityOfElement(getDriver(), MightLike1);
	 * 
	 * List<WebElement> MightLikeallproduct =
	 * getDriver().findElements(By.xpath(mightLikeallproduct)); int n =
	 * MightLikeallproduct.size(); System.out.println(n +
	 * " Articles should be present in Might Like section"); int count5 = 0;
	 * List<WebElement> MightLikeimages =
	 * getDriver().findElements(By.xpath(mightLikeimages)); int x =
	 * MightLikeimages.size(); System.out.println(x +
	 * " Articles should be present in Might Like section"); // Thread.sleep(2000);
	 * if (x == n) { for (WebElement i : MightLikeimages) {
	 * Action.mouseOverElement(getDriver(), i); // Action.explicitWait(getDriver(),
	 * i, Duration.ofSeconds(5)); // Thread.sleep(1000); if
	 * (i.getAttribute("srcset").contains("amazonaws.com")) { count5++; } } if
	 * (count5 == n) { System.out.println("All " + count5 +
	 * " might images are present"); } else { softAssert.assertTrue(false, n -
	 * count5 + " Might Images are not present  "); } } else { System.out.println(n
	 * - x + " Images are not present "); softAssert.assertTrue(false, +n - x +
	 * " Images are not present "); }
	 * 
	 * List<WebElement> MightLikeProductName =
	 * getDriver().findElements(By.xpath(mightLikeProductNames)); int y =
	 * MightLikeProductName.size(); if (y == n) { System.out.println("All " + y +
	 * " Might_Like Product Names are present"); } else { System.out.println(n - y +
	 * "  Might Like Product Names are not present "); softAssert.assertTrue(false,
	 * +n - y + "  Might Like Product Names are not present "); }
	 * 
	 * List<WebElement> MightLikeProductDescription =
	 * getDriver().findElements(By.xpath(mightLikeProductDescription)); int z =
	 * MightLikeProductDescription.size(); if (z == n) { System.out.println("All " +
	 * z + " Might_Like Product description are present"); } else {
	 * System.out.println(n - z +
	 * "  Might Like Product description are not present ");
	 * softAssert.assertTrue(false, +n - z +
	 * "  Might Like Product description are not present "); }
	 * 
	 * List<WebElement> BookMarkicon =
	 * getDriver().findElements(By.xpath(bookMarkicon)); int c =
	 * BookMarkicon.size(); if (c == n) { System.out.println("All " + c +
	 * "  BookMarkicon are present"); } else { System.out.println(n - c +
	 * " BookMarkicon are not present "); softAssert.assertTrue(false, +n - c +
	 * " BookMarkicon are not present "); }
	 * 
	 * }
	 */
	String allProducts ="(//div[@id='sg-productCardWrapper' and @type ='Products'])";
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
		if(bodyTag.getText().contains("WE FOUND OTHER COURSES YOU MIGHT LIKE")) {
			
		String productYouMightLikeSection = "//div[normalize-space()='We found other courses you might like']";
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
			System.out.println("WE FOUND OTHER COURSES YOU MIGHT LIKE section is not present");
			Assert.assertTrue(false, "WE FOUND OTHER COURSES YOU MIGHT LIKE section is not present");
			
		}
	}
	
	
	String highlightContent = "(//div[@class='MuiBox-root css-1umfg2p'])/div";
	String highlightImages = "//div[@class='MuiBox-root css-12hir4b']/span/img";
	String courses = "//div[@class='MuiBox-root css-12hir4b']";
	String courseHighlight = "(//div[contains(@class,'css-bp10qf')])[1]";

	public void CourseHighlightSections() {
		WebElement CourseHighlight = getDriver().findElement(By.xpath(courseHighlight));
		softAssert.assertEquals(CourseHighlight.getText(), "COURSE HIGHLIGHTS",
				"Course Highlights text is not present on the UI");
		Log.info("Course Highlights is successfully present on the UI");

		List<WebElement> Courses = getDriver().findElements(By.xpath(courses));
		int c = Courses.size();
		System.out.println(c);
		List<WebElement> HighlightImages = getDriver().findElements(By.xpath(highlightImages));
		int d = HighlightImages.size();
		System.out.println(d);
		if (d == c) {
			int count5 = 0;
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
		} else {
			System.out.println(c - d + " Highlight images are not present ");
			softAssert.assertTrue(false, +c - d + " Highlight Images are not present ");
		}

		List<WebElement> HighlightContent = getDriver().findElements(By.xpath(highlightContent));
		int e = HighlightContent.size();
		System.out.println(e);
		if (d == e) {
			for (WebElement i : HighlightContent) {
				Action.mouseOverElement(getDriver(), i);
				if (!i.getText().equals(null)) {
					Assert.assertTrue(i.isDisplayed());
					System.out.println(i.getText());
					Log.info("Successfully verified the content of the highlights");
				} else {
					softAssert.assertTrue(i.getText().equals(null));
					Log.info("Content is not present in the highlight images");
				}
			}
		}

		softAssert.assertAll();
	}

}
