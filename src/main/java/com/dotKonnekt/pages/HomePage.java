 package com.dotKonnekt.pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;


public class HomePage extends BaseClass{

	
	@FindBy(xpath = "//span[text()='My wishlists']")
	WebElement myWishList;
	
	@FindBy(xpath = "//span[text()='Order history and details']")
	WebElement orderHistory;
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	String skin = "//button[normalize-space()='Skin']";
	public void clickOnCategory() {
		getDriver().findElement(By.xpath(skin)).click();
	}
	
	String quickview = "//*[@id='sg-quickViewButton']";
	String allProduct = "//*[@id='sg-carouselSwiperSlide']"; 
	String carticon = "//*[@id='sg-productCardBlogBoxes']/button";
	String wishlist = "//*[name()='svg' and @data-testid='FavoriteBorderOutlinedIcon']";
	String addToCartButton = "(//button[@id='sg-productCardQuickViewText'])";
	//String discountedPrice = "//div[@id='productCardContentPriceTile']";
	String actualPrice = "//div[@id='productCardContentPriceTile']";
	String productsName = "(//div[@id='sg-productCardContentTitle'])";
	String images = "//*[@id='sg-carouselSwiperSlide']/div/div[1]";
	SoftAssert softAssert = new SoftAssert();
	String newArrivalSection = "//*[@id='sg-homeComponentRecomendationWrapper']";
	public void newArrival() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,750)", "");
		WebElement NewArrivalSection  = getDriver().findElement(By.xpath(newArrivalSection));
		Action.scrollByVisibilityOfElement(getDriver(), NewArrivalSection);
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allProduct));
		int n = products1.size();
		System.out.println("Total Products Present in New Arrival are " + n);

		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int x = image.size();
		int count4 = 0;
		if (x == n) {
			for(WebElement i : image) {
				Action.scrollByVisibilityOfElement(getDriver(), i);
				Thread.sleep(500);
				if(i.getAttribute("style").contains("shopify.com")) {
					count4++;
				}
				
			}
			if(count4 ==n && count4!=0 ) {
			System.out.println("All " + count4 + " images are present");}
			else {
				//System.out.println(n - x + " Images are not present ");
				softAssert.assertTrue(false, n-count4+ "Images are not present  ");
			}
		} else {
			//System.out.println(n - x + " Images are not present ");
			softAssert.assertTrue(false, +n - x + " Images are not present ");
		}

		List<WebElement> productName1 = getDriver().findElements(By.xpath(productsName));
		int j = productName1.size();
		if (j == n) {
			System.out.println("All " +j+ " Products name are present");
		} else {
			System.out.println(n - j + " Products name are not present ");
			softAssert.assertTrue(false, +n - j + " Products name are not present ");
		}

		List<WebElement> productActPrice = getDriver().findElements(By.xpath(actualPrice));
		int z = productActPrice.size();
		if (z == n) {
			System.out.println("All " +z+ " Actual Prices are present");
		} else {
			System.out.println(n - z + " Actual Prices are not present ");
			softAssert.assertTrue(false, n - z + " Actual Prices are not present ");
		}

		List<WebElement> quickviewlink = getDriver().findElements(By.xpath(quickview));
		int i = quickviewlink.size();
		if (i == n) {
			System.out.println("All " +i+ " Quick View links are present");
		} else {
			System.out.println(n - i + " Quick View links are not present ");
			softAssert.assertTrue(false, n - i + " Quick View links are not present ");
		}

		List<WebElement> carticon1 = getDriver().findElements(By.xpath(carticon));
		int c = carticon1.size();
		if (c == n) {
			System.out.println("All " +c+ "carticon are present");
		} else {
			System.out.println(n - c + " carticon are not present ");
			softAssert.assertTrue(false, +n - c + " carticon are not present ");
		}

		List<WebElement> wishlist1 = getDriver().findElements(By.xpath(wishlist));

		int d = wishlist1.size();
		if (d == n) {
			System.out.println("All " +d+ "Wishlist icon are present");
		} else {
			System.out.println(n - d + " Wishlist icon are not present ");
			softAssert.assertTrue(false, +n - d + " Wishlist icon are not present ");
		}

		softAssert.assertAll();
		
	}
	
	
	
	public void productQuickView() {
		WebElement NewArrivalSection  = getDriver().findElement(By.xpath(newArrivalSection));
		Action.scrollByVisibilityOfElement(getDriver(), NewArrivalSection);
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allProduct));
		int n = products1.size();
		System.out.println("Total Products Present in New Arrival are " + n);
		
		
		List<WebElement> quickviewlink = getDriver().findElements(By.xpath(quickview));
		int i = quickviewlink.size();
		if (i == n) {
			System.out.println("All " +i+ " Quick View links are present");
		} else {
			System.out.println(n - i + " Quick View links are not present ");
			softAssert.assertTrue(false, n - i + " Quick View links are not present ");
		}

	}
	
	
	
	
	
	
	String thumbnailImage = "//div[@id='sg-testimonialCardAvatar']";
	String thumbnail = "//*[name()='svg' and @data-testid='PersonIcon']";
	String username = "//p[@id='sg-testimonialCardTitle']";
	String quoted = "//*[name()='svg' and @data-testid='FormatQuoteIcon']";
	String testmonialsText ="//p[@id='sg-testimonialCardContent']";
	String testimonials= "//div[contains(@id,'sg-testimonialCardMainWrapper')]";
	String shoutOutSection= "//div[contains(@id,'sg-homeTestimonialShoutOut')]";
	String socialMediaIcon = "//img[@id='sg-testimonialCardSocialImg']";
	public void shoutOut() {
		WebElement ShoutOutSection  = getDriver().findElement(By.xpath(shoutOutSection));
		Action.scrollByVisibilityOfElement(getDriver(), ShoutOutSection);
		
		List<WebElement> TestiMonials = getDriver().findElements(By.xpath(testimonials));
		int size = TestiMonials.size();
		Log.info("Successfully Verified the Testimonials presence");
		
		List<WebElement> TestiMonialText = getDriver().findElements(By.xpath(testmonialsText));
		Assert.assertEquals(TestiMonialText.size(), size, "All 3 testimonial Text are not present");
		for (WebElement i : TestiMonialText) {
			if(i.getText().equals(null)) {
				softAssert.assertTrue(false, "Testimonial Text is/are not present");
			}
		}
		Log.info("Successfully Verified the Testimonials Text presence");
		
		List<WebElement> Quoted = getDriver().findElements(By.xpath(quoted));
		Assert.assertEquals(Quoted.size(), size, "All 3 Quote sign are not present");
		for (WebElement i : Quoted) {
			if(i.getText().equals(null)) {
				softAssert.assertTrue(false, "Quote sign is/are not present");
			}
		}
		Log.info("Successfully Verified the Quote sign presence");
		
		List<WebElement> Username = getDriver().findElements(By.xpath(username));
		Assert.assertEquals(Username.size(), size, "All 3 Username  are not present");
		for (WebElement i : Username) {
			if(i.getText().equals(null)) {
				softAssert.assertTrue(false, "Username is/are not present");
			}
		}
		Log.info("Successfully Verified the Username presence");
		
		
		List<WebElement> SocialMediaIcon = getDriver().findElements(By.xpath(socialMediaIcon));
		System.out.println(SocialMediaIcon.size() + " Social Media icons are present in testimonials Section");
		for (WebElement i : SocialMediaIcon) {
			System.out.println(i.getAttribute("alt"));
		}
		Log.info("Successfully Verified the Social Media presence");
		
	
		
		
		
		softAssert.assertAll();
	}
	
	String eastIcon = "(//*[name()='svg' and @data-testid='EastIcon'])[2]";
	String askour = "sg-askExpertH2Bold";
	String askDesc = "sg-askExpertTypography";
	String experts = "sg-askExpertSwiperSlide";
	String expertsImage = "(//img[@id='sg-askExpertCardImage'])";
	String sendAMessage = "(//div[@id='sg-askExpertCardHoverButton'])";
	String expertName = "(//div[@id='sg-askExpertCardDetailsContainer'])";
	String crossbutton1 = "//*[local-name()='svg' and @data-testid='ClearIcon']";
	public void askOurExpert() throws InterruptedException {
		WebElement AskOur  =  getDriver().findElement(By.id(askour));
		Action.scrollByVisibilityOfElement(getDriver(), AskOur);
		softAssert.assertEquals(AskOur.getText(), "ASK OUR EXPERTS");
		Log.info("Successfully Verified the Ask Our Experts Presence on UI");
		
		
		WebElement AskDesc  =  getDriver().findElement(By.id(askDesc));
		softAssert.assertTrue(AskDesc.isDisplayed(), "Ask Desc is not present");
		String desc= "We're all about real connection, and our store ambassadors make it happen. With endless passion and leadership, they motivate their communities to get sweaty and make a difference.";
		softAssert.assertEquals(AskDesc.getText(), desc, "Ask our Expert Description text are not same");
		Log.info("Succesfully verified the text Presence on the UI for the Ask our Expert section");
		
		List<WebElement> Experts = getDriver().findElements(By.id(experts));
		int n= Experts.size();
		
		
		List<WebElement> ExpertName = getDriver().findElements(By.xpath(expertName));
		
		
		if(n==ExpertName.size()) {
			int q = 0;
			for(WebElement i : ExpertName) {
			Action.mouseOverElement(getDriver(), i);
				Action.isDisplayed(getDriver(), i);
				
				if(i.getText().equals("")) {
					q++;
					softAssert.assertTrue(false, q + " Name is/are not present");
				}
				else {
					System.out.println(i.getText());
				}
				
			}
			
			
		}
		Log.info("Succesfully Verified the Presence of the name and role");
		
		
		WebElement EastIcon = getDriver().findElement(By.xpath(eastIcon));
		if(n<=3) {
			
			Assert.assertEquals(EastIcon.getAttribute("style"),"color: rgb(0,0,0);", "East icon is enabled it should be disabled");
			Log.info("successfully veriied the functionlaity of the East Icon");
		}
		if(n>3) {
			Assert.assertEquals(EastIcon.getAttribute("style"),"color: rgb(255, 255, 255);", "East icon is disabled it should be enabled");
			
		}
		
			
		
		
		softAssert.assertAll();
	}
	
	
	
	String AskOurExperts = "(//p[@id='sg-askExpertModalTypography'])";
	String image = "//*[@id='sg-askExpertModalCustomImage']";
	String name = "//p[@id='sg-askExpertModalTypography2']";
	String category = "//p[@id='sg-askExpertModalTypography3']";
	String elements  = "//div[@id='sg-askExpertModalWrapperRight']/div/div/label";
	
	
	public void sendAMessage(String type) throws InterruptedException {
		
		int n =3;
		List<WebElement> SendAMessage = getDriver().findElements(By.xpath(sendAMessage));
		int y = SendAMessage.size();
		System.out.println(y);
		int count6 = 0;
		if (y == 3) {
			for (WebElement i : SendAMessage) {
				Action.mouseOverElement(getDriver(), i);
				if (i.getText().equals("Send a Message")) {
					Action.isEnabled(getDriver(), i);
					System.out.println(" Send Message is present");
					count6++;
				}
			}
			if (count6 == n) {
				System.out.println("All " + count6 + " Send Message are present");
			} else {
				softAssert.assertTrue(false, n - count6 + " Send Message are not present  ");
			}
		} else {
			System.out.println(n - y + " Send Message are not present ");
			softAssert.assertTrue(false, +n - y + " Send Message are not present ");
		}
		
		if (y >0) {
			
			Random r = new Random(); 
			  int randomValue = r.nextInt(SendAMessage.size()); //Getting a random value that is between 0 and (list's size)-1
			 WebElement  i =  SendAMessage.get(randomValue); //Clicking on the random item in the list.\
			 System.out.println( SendAMessage.get(randomValue).getText());
			//for (WebElement i : SendAMessage) {
				//Thread.sleep(2000);
				Action.mouseOverElement(getDriver(), i);
				if (i.getText().equals("Send a Message")) {
					Action.isEnabled(getDriver(), i);
					Thread.sleep(500);
					System.out.println(" Send Message is present");
					
					i.click();
					Thread.sleep(3000);
				Log.info("Clicked on the Send a message button");
					WebElement askOurExperts = getDriver().findElement(By.xpath(AskOurExperts));
					Assert.assertEquals(askOurExperts.getText(), "Ask Our Experts!", "Ask Our Experts! text is not correct");

					WebElement Name = getDriver().findElement(By.xpath(name));
					System.out.println(Name.getText());
					Assert.assertTrue(Name.isDisplayed(), "Expert Name is not present in the Ask Our Experts Section");
					
					WebElement ExpertCategory = getDriver().findElement(By.xpath(category));
					Assert.assertTrue(ExpertCategory.isDisplayed(), "Expert Category is not present in the Ask Our Experts Section");
					
					List<String> listElements = new ArrayList<String>();
					List<WebElement> Elements =  getDriver().findElements(By.xpath(elements));
					for(WebElement j :Elements) {
						listElements.add(j.getText());
					}
					Log.info("elements Verification");
					String category = "Name *,Email *,Query *";
					 List<String> myList = new ArrayList<String>(Arrays.asList(category.split(",")));
					 System.out.println(myList);
					 System.out.println(listElements);
					 if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
							Log.info("Successfully verified that all the elements are in Ask our Expert");
						}
						else {
							Assert.assertTrue(false, "elements are not in correct form");
						}
					
					 WebElement NameTextBox=  getDriver().findElement(By.xpath("//input[@id='name']"));
					 WebElement EmailTextBox=  getDriver().findElement(By.xpath("//input[@id='email']"));
					 if(!type.equals("Logged")) {
					 Action.type(NameTextBox, prop.getProperty("name"));
					 Action.type(EmailTextBox, prop.getProperty("Username"));
					 }
					 if(type.equals("Logged")) {
						 
					 }
					 
					 WebElement QueryTextBox=  getDriver().findElement(By.xpath("//textarea[@id='query']"));
					 Action.type(QueryTextBox, prop.getProperty("Query"));
					 
					 Thread.sleep(10000);
					 
					 WebElement submitButton=  getDriver().findElement(By.xpath("//button[@id='sg-askExpertModalMessageSubmitBtn']"));
					 Action.click(getDriver(), submitButton);
					 
					 WebElement SuccessfullMessage = getDriver().findElement(By.xpath("//*[@id='sg-askExpertModalTypography4']"));
					 
					 Action.explicitWait(getDriver(), SuccessfullMessage, Duration.ofSeconds(3));
					 
					 System.out.println(SuccessfullMessage.getText());
					 
					 WebElement askOurExperts1 = getDriver().findElement(By.xpath(AskOurExperts));
						Assert.assertEquals(askOurExperts1.getText(), "Ask Our Experts!", "Ask Our Experts! text is not correct");
						WebElement Name1 = getDriver().findElement(By.xpath(name));
						System.out.println(Name1.getText());
						Assert.assertTrue(Name1.isDisplayed(), "Expert Name is not present in the Ask Our Experts Section");
						Assert.assertEquals(Name1.getText(), Name.getText(), "Name of the the expert is not same.");
					 
					 
				}
		}
		else {
			Log.info("Send a Message button is not present");
		}
		Log.info("Successfully Verified the Presence of the Experts Send A Message & Click on the Send a Message");
		 
	
	}
	
	
	String img = "//img[@id='sg-ourStoryImagePaths']";
	String aboutUsDesc  = "//p[@id='sg-ourStoryDesc']";
	String aboutUS = "//div[@id='sg-ourStoryLinkText']";
	public void ourStory_AboutUs() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("javascript:window.scrollBy(0,4600)");
        Thread.sleep(3000);
        Log.info("Successfully scrolled to the 0,4600 coordinates");
        WebElement AboutUs = getDriver().findElement(By.xpath(aboutUS));
        Assert.assertTrue(AboutUs.isDisplayed(), "About Us is not deisplayed");
        Log.info("Successfully verified the Our Story Presence");
        WebElement AboutUsDesc = getDriver().findElement(By.xpath(aboutUsDesc));
        Assert.assertTrue(AboutUsDesc.isDisplayed());
        Log.info("Successfully verified the Our Story Description Presence");
        WebElement Image = getDriver().findElement(By.xpath(img));
        if(Image.isDisplayed()) {
        	// Assert.assertTrue(Image.getAttribute("srcset").contains("amazonaws.com"),"Image is not present");
        	Log.info("Successfully verified the presence of the Image");
        }
        
	}
	
	String category1 = "//div[@id='sg-courseStoryPreTitle']";
	String learnMoreBtn = "//button[@id='btnAddToCart']";
	String price ="//p[@id='sg-courseStoryFinalPrice']";
	String date ="//p[@id='sg-courseStoryCreatedOn']";
	String courseInstructor = "//p[@id='sg-courseStoryInstructorName']";
	String productname = "//div[@id='sg-courseStoryTitle']";
	String categoryN = "//div[@id='sg-courseStoryPreTitle']";
	String img1 = "//img[@id='sg-courseStoryImage']";
	public void aboveAboutSection() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("javascript:window.scrollBy(0,3600)");
        Thread.sleep(3000);
		
		 WebElement Image = getDriver().findElement(By.xpath(img1));
	        if(Image.isDisplayed()) {
	        	Assert.assertTrue(Image.getAttribute("srcset").contains("shopify.com"),"Image is not present");
	        	Log.info("Successfully verified the presence of the Image");
	        }
	        else {
	        	Assert.assertTrue(false, "Image is not present in aboveAboutUsSection");
	        }
	        WebElement Category1 = getDriver().findElement(By.xpath(category1));
	        Assert.assertTrue(Category1.isDisplayed());
	        Log.info("Successfully verified the BottomWear Description Presence");
	        WebElement ProductName = getDriver().findElement(By.xpath(productname));
	        Assert.assertTrue(ProductName.isDisplayed(), "Product Name is not present");
	        String name = ProductName.getText();
	        Log.info("Successfully verified the ProductName Presence");
	        WebElement CourseInstructor = getDriver().findElement(By.xpath(courseInstructor));
	        Assert.assertTrue(CourseInstructor.isDisplayed(), "Course Intructor name is not present");
	        System.out.println(CourseInstructor.getText());
	        Log.info("Successfully verified the Course Intructor name Presence");
	        WebElement Date = getDriver().findElement(By.xpath(date));
	        Assert.assertTrue(Date.isDisplayed(), "Date is not present");
	        System.out.println(Date.getText());
	        Log.info("Successfully verified the Date Presence");
	        WebElement Price = getDriver().findElement(By.xpath(price));
	        Assert.assertTrue(Price.isDisplayed(), "Price is not present");
	        System.out.println(Price.getText());
	        Log.info("Successfully verified the Price Presence");
	        WebElement LearnMoreBtn  = getDriver().findElement(By.xpath(learnMoreBtn));
	        Assert.assertTrue(LearnMoreBtn.isDisplayed(), "Price is not present");
	        System.out.println(LearnMoreBtn.getText());
	        Log.info("Successfully verified the Price Presence");
	      
	        
	}
	String copyLogo  = "//img[@id='sg-adBannerCopyImg']";
	String couponCode ="//p[contains(@id,'sg-adBannerCouponCode')][1]";
	String copyHoliday ="//p[contains(@id,'sg-adBannerCouponTitle')][1]"; 
	String giftTextDesc = "//p[contains(@id,'sg-adBannerSectionDesc1')]";
	String giftText = "//p[contains(@id,'sg-adBannerSectionTitle1')]";
	String voucherImage = "//img[@id='sg-adBannerImgPath']";
	public void discountVoucher() {
		WebElement VoucherImage = getDriver().findElement(By.xpath(voucherImage));
		Action.scrollByVisibilityOfElement(getDriver(), VoucherImage);
		if(VoucherImage.isDisplayed()) {
        	softAssert.assertTrue(VoucherImage.getAttribute("srcset").contains("cloudfront.net"),"VoucherImage is not present");
        	Log.info("Successfully verified the presence of the VoucherImage");
        }
        else {
        	softAssert.assertTrue(false, "VoucherImage is not present in aboveAboutUsSection");
        }
		
		WebElement GiftText = getDriver().findElement(By.xpath(giftText));
		softAssert.assertTrue(GiftText.isDisplayed(), "gift Text is not present");
		softAssert.assertEquals(GiftText.getText(), "Gift Something Extra Special", "Gift Text is not Correct");
		Log.info("Succesfully Verified the presene of the Gift Text");
		
		WebElement GiftDesc= getDriver().findElement(By.xpath(giftTextDesc));
		softAssert.assertTrue(GiftDesc.isDisplayed(), "gift Text description is not present");
		String actualDesc= "Find the perfect holiday gifts, from DotFashion store to exclusive limited edition of Dotfashion Holiday Season Gifts.";
		softAssert.assertEquals(GiftDesc.getText(), actualDesc, "Gift Description is not Correct");
		Log.info("Succesfully Verified the presene of the Gift Text Descripition");
		
		WebElement CopyHoliday= getDriver().findElement(By.xpath(copyHoliday));
		softAssert.assertTrue(CopyHoliday.isDisplayed(), "Copy holiday description is not present");
		String CopyDesc= "Copy Holiday coupon to avail extra 20% OFF";
		softAssert.assertEquals(CopyHoliday.getText(), CopyDesc, "Copy holiday Description is not Correct");
		Log.info("Succesfully Verified the presene of the Copy holiday Text Descripition");
		
		WebElement CouponCode= getDriver().findElement(By.xpath(couponCode));
		softAssert.assertTrue(CouponCode.isDisplayed(), "CouponCode is not present");
		String Copuon= "#DOTCHRIS2665";
		softAssert.assertEquals(CouponCode.getText(), Copuon, "CouponCode is not Correct");
		softAssert.assertTrue(CouponCode.isEnabled(), "Coupon code is not enabled");
		Log.info("Succesfully Verified the presene of the CouponCode");
		
		WebElement CopyLogo = getDriver().findElement(By.xpath(copyLogo));
		//Action.scrollByVisibilityOfElement(getDriver(), VoucherImage);
		if(CopyLogo.isDisplayed()) {
        	softAssert.assertTrue(CopyLogo.getAttribute("srcset").contains("static"),"VoucherImage is not present");
        	softAssert.assertTrue(CopyLogo.isEnabled(), "CopyLogo is not enabled");
        	Log.info("Successfully verified the presence of the VoucherImage");
        }
        else {
        	softAssert.assertTrue(false, "VoucherImage is not present in aboveAboutUsSection");
        }
		
	}
	
	
	public void endToEndScenarioHomePageByClickingProductTile() throws InterruptedException {
		Log.info("endToEndScenarioHomePageByClickingProductTile ---  Starts");
		WebElement NewArrivalSection  = getDriver().findElement(By.xpath(newArrivalSection));
		Action.scrollByVisibilityOfElement(getDriver(), NewArrivalSection);
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allProduct));
		int n = products1.size();
		System.out.println(n);
		Random r = new Random(); 
		int randomValue = r.nextInt(products1.size());//Getting a random value that is between 0 and (list's size)-1
		System.out.println(randomValue);
		WebElement Product = products1.get(randomValue);
		Action.click(getDriver(), Product);
		System.out.println(Product.getText());
		Action.explicitWaitbyTitle(getDriver(), "Product", Duration.ofSeconds(5));
		Assert.assertEquals(getDriver().getTitle(), "Product");
	 Log.info("Successfully verified the title of the Product page from search Page");
		  
		  Log.info("endToEndScenarioHomePageByClickingProductTile- -- Ends ");
	}
	
	String checkoutBtn = "//div[@class='MuiBox-root css-1rtfqxi']/button";
	String cart2 = "//div[@class='MuiBox-root css-1p3qk0r']//img[@alt='logo']";
	public void ByCartIcon() throws InterruptedException {
		Log.info("endToEndScenarioHomePageByClickingProductTile ---  Starts");
		WebElement NewArrivalSection  = getDriver().findElement(By.xpath(newArrivalSection));
		Action.scrollByVisibilityOfElement(getDriver(), NewArrivalSection);
		
		List<WebElement> image = getDriver().findElements(By.xpath(images));
		int n = image.size();
		System.out.println(n);
		 Random r = new Random(); 
		  int randomValue = r.nextInt(image.size());//Getting a random value that is between 0 and (list's size)-1
		  System.out.println("Random Value = " +randomValue);
		  List<WebElement> cartIcons = getDriver().findElements(By.xpath(carticon));
		  int count = 0;
		  for(WebElement i : image) {
			  Action.mouseOverElement(getDriver(), i);
			  count++;
			  Thread.sleep(300);
			  if(count == randomValue) {
				  WebElement Cart1 = cartIcons.get(randomValue);
				  Assert.assertTrue(!Cart1.isEnabled(), "Cart icon is not enabled");
				  Action.click(getDriver(), Cart1);
				  break;
			  }
			  if(randomValue==0){
				  Assert.assertTrue(false, "Random value selects is wrong");
			  }
			  
		  }
		 
		  Log.info("sdfgsdfghj");
		 // Thread.sleep(2000);
			  JavascriptExecutor js = (JavascriptExecutor) getDriver();
			  js.executeScript("javascript:window.scrollBy(0,0)"); Thread.sleep(2000);
			 // Thread.sleep(2000);
			  Log.info("Scroll to top");;
		  
			  WebElement cartIcon  =  getDriver().findElement(By.xpath(cart2));
			  Action.scrollByVisibilityOfElement(getDriver(), cartIcon);
			  Action.click(getDriver(), cartIcon);
			  Log.info("Successfully Clicked on the Cart button");
			  String totalItmesTxt = getDriver().findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-t8ihhx']")).getText();
				if(totalItmesTxt.equals("0")) {
					Assert.assertTrue(false, "Product is not added to the floating cart, MAy be product is out of Stock");
					System.out.println("Product is not present in floating cart");
				}
				
			WebElement CheckoutBtn = getDriver().findElement(By.xpath(checkoutBtn));
			Action.explicitWait(getDriver(), CheckoutBtn, Duration.ofSeconds(10));
			Action.click(getDriver(), CheckoutBtn);
			Action.explicitWaitbyTitle(getDriver(), "Checkout", Duration.ofSeconds(10));
		  Log.info("endToEndScenarioHomePageByClickingProductTile- -- Ends ");
	}
	
	
	String stockCount = "//*[@id='sg-counterCountInput']";
	String cartButton = "//*[@id='sg-addToCartButton']";
	public void ByQuickView() throws InterruptedException {
		Log.info("endToEndScenarioHomePageByClickingQuickView ---  Starts");
		WebElement NewArrivalSection  = getDriver().findElement(By.xpath(newArrivalSection));
		Action.scrollByVisibilityOfElement(getDriver(), NewArrivalSection);
		
		List<WebElement> products1 = getDriver().findElements(By.xpath(allProduct));
		int n = products1.size();
		System.out.println(n);
		Random r = new Random(); 
		int randomValue = r.nextInt(products1.size());//Getting a random value that is between 0 and (list's size)-1
		System.out.println(randomValue);
		List<WebElement> quickViewButtons = getDriver().findElements(By.xpath(quickview));
		WebElement QuickView = quickViewButtons.get(randomValue);
		Action.click(getDriver(), QuickView);
		Log.fatal("ID OF THE QUICKVIEW - "+QuickView.getDomProperty("id"));
		Thread.sleep(10000);
		
		Log.info("Successfully clicked on the quick text of product" + randomValue);
		 
		Log.info("AddToCart of Product Page verification");
		WebElement StockCount = getDriver().findElement(By.xpath(stockCount));
		Assert.assertEquals(StockCount.getAttribute("value"), "1", "Count is not set to 1");
		WebElement addToCartIcon = getDriver().findElement(By.xpath(cartButton));
		boolean result = Action.isDisplayed(getDriver(), addToCartIcon);
		String elementTxt = addToCartIcon.getText();
		if (elementTxt.equalsIgnoreCase("ADD TO CART") && result){
			Assert.assertTrue(addToCartIcon.isEnabled(), "ADD TO CART button is not enabled");
			System.out.println("AddTOCart is enabled");
			Log.info("Successfully verified AddToCart Message");
			//Thread.sleep(2000);
			Action.click(getDriver(), addToCartIcon);
			Thread.sleep(2000);
			 JavascriptExecutor js = (JavascriptExecutor) getDriver();
			  js.executeScript("javascript:window.scrollBy(0,0)");
			  Log.info("Scroll to top");
			  WebElement cartIcon  =  getDriver().findElement(By.xpath(cart2));
			  Action.scrollByVisibilityOfElement(getDriver(), cartIcon);
			  Action.click(getDriver(), cartIcon);
			  Log.info("Successfully Clicked on the Cart button");
			  	Thread.sleep(2000);
				WebElement CheckoutBtn = getDriver().findElement(By.xpath(checkoutBtn));
				Action.explicitWait(getDriver(), CheckoutBtn, Duration.ofSeconds(10));
				Action.click(getDriver(), CheckoutBtn);
				Action.explicitWaitbyTitle(getDriver(), "Checkout", Duration.ofSeconds(10));
			//Action.explicitWaitbyTitle(getDriver(), "ProductPage", Duration.ofSeconds(5));
				  
		  Log.info("Successfully verified the title of the Product page from search Page");
		}
		else if(elementTxt.equalsIgnoreCase("OUT OF STOCK") && result){
			Assert.assertTrue(!addToCartIcon.isEnabled(), "Out of Stock button is enabled");
			Log.info("Product is Out of Stock");
		}
			
		
		else {
			System.out.println("Cart Icon is not present");
			Assert.assertTrue(result, "Cart Icon is not present");
		}
		//Log.info("Successfully ends the availabiltyStock");
		
	  
	  Log.info("endToEndScenarioHomePageByClickingQuickView- -- Ends ");
	}

	// Newsletter Pop-up Variables
	String newsLetter = "//*[@id='sg-newsLatterCancelButton']";
	String acceptCookiesButton = "//*[@id='rcc-confirm-button']";
	
	public void newsletterPopupAlert () {
		// In new front-end code the newsletter appears on scroll fixing old code
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,750)", "");
		WebElement NewsLetter = getDriver().findElement(By.xpath(newsLetter));
		Action.click(getDriver(), NewsLetter);
		js.executeScript("window.scrollBy(0,0)", "");
		
		// If the button is found click it or else do nothing
		WebElement acceptButton = null;
		try {
			acceptButton = getDriver().findElement(By.xpath(acceptCookiesButton));
		}
		catch(org.openqa.selenium.NoSuchElementException e) {}
		if(acceptButton != null) {
			acceptButton.click();
		}
		Log.info("Successfully clicked on the newsLetter");
	}
	
	public void onlyNewsLetter() {
		WebElement NewsLetter = getDriver().findElement(By.xpath(newsLetter));
		Action.click(getDriver(), NewsLetter);
		Log.info("Successfully clicked on the newsLetter");
	}
	
}
