package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class CheckoutPage extends BaseClass{

	

	public CheckoutPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	SoftAssert softAssert = new SoftAssert();
	public void addItemTotheCart() {
		
	}
	
	
	String shopProducts = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-4 css-1vybv8z']/div";
	String showingresult = "(//div[@class='MuiBox-root css-0'])[2]/p";
	String noresult = "//p[@id='sg-no-results']";
	String shoptab = "//div[@aria-label='basic tabs example']/button[1]";
	String articletab = "//div[@aria-label='basic tabs example']/button[2]";
	String clickButton=  "//*[name()='svg' and @data-testid='SearchOutlinedIcon']";
	String searchBox = "//input[@placeholder='Search']";
	String recentSearch = "//p[@id='sg-autoCompleteSearchTypography']";
	String trending = "//p[@id='sg-autoCompleteSearchTypography3']";

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
			Log.info("inside the Article section");
			ArticleTab.click();
			//Action.click(getDriver(), ArticleTab);
			Thread.sleep(4000);
			Log.info("Successfully Clicked the Article Tab button ");
			String n = text.replaceAll("[^0-9]", "");
			System.out.println("Count Text present is Artcile(x) = " + n);
			WebElement Showingresult = getDriver().findElement(By.xpath(showingresult));
			String ss = (Showingresult.getText()).replaceAll("[^0-9]", "");
			System.out.println("Total Product Present on Ui "+ss);
			if(ss.equals(n)) {
				List<WebElement> ShopProducts = getDriver().findElements(By.xpath(shopProducts));
				int count  = ShopProducts.size();
				//System.out.println(count);
				if(count == Integer.parseInt(ss)) {
					Log.info("Successfully Verified the Count of the Articles");
				}
				else {
					Assert.assertTrue(false, "Articles count are not equals");
				}
			
		}
		else {
			Assert.assertTrue(false, "article tab is not present on the ui");
			}
		}
		
		
		
	}
	
	
	String allproduct = "(//div[contains(@class,'css-i25sow')])/div";
	public void selectAProduct() throws InterruptedException {
		Log.info("Starting the SelectAProduct");
		List<WebElement> products1 = getDriver().findElements(By.xpath(allproduct));
		int n = products1.size();
		System.out.println(n);
		//Thread.sleep(2000);
		 Random r = new Random(); 
		  int randomValue = r.nextInt(products1.size()); //Getting a random value that is between 0 and (list's size)-1
		  products1.get(randomValue).click(); //Clicking on the random item in the list.\
		 System.out.println( products1.get(randomValue).getText());
		 Action.explicitWaitbyTitle(getDriver(), "Product", Duration.ofSeconds(5));
		  Assert.assertEquals(getDriver().getTitle(), "Product");
		  Log.info("Successfully verified the title of the Product page from search Page");
	}
	
	String checkoutBtn = "//*[@id='sg-orderCommonButton']";
	String cartButton = "(//button[@id='sg-addToCartButton'])[1]";
	String stockCount = "//input[@id='sg-counterCountInput']";

	public void availabiltyStock() throws InterruptedException {

		Log.info("AddToCart of Product Page verification");
		WebElement StockCount = getDriver().findElement(By.xpath(stockCount));
		Assert.assertEquals(StockCount.getAttribute("value"), "1", "Count is not set to 1");
		WebElement addToCartIcon = getDriver().findElement(By.xpath(cartButton));
		boolean result = Action.isDisplayed(getDriver(), addToCartIcon);
		String elementTxt = addToCartIcon.getText();
		if (elementTxt.equalsIgnoreCase("ADD TO CART") && result){
			Assert.assertTrue(addToCartIcon.isEnabled(), "Add To Cart button is not enabled");
			System.out.println("AddTOCart is enabled");
			Action.doubleClick_SendValue(StockCount, "4");
			//Action.type(StockCount, "4");
			Thread.sleep(3000);
			
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
		
		WebElement cartButton = getDriver().findElement(By.xpath("//*[@id='sg-iconsHeaderMainBox']"));
		
		Action.click(getDriver(), cartButton);
		
		Thread.sleep(2000);
		
		WebElement CheckoutBtn = getDriver().findElement(By.xpath(checkoutBtn));
		Action.click(getDriver(), CheckoutBtn);
		Action.explicitWaitbyTitle(getDriver(), "Checkout", Duration.ofSeconds(5));
		
	}
	
	
	String statusInfo = "(//div[@id='sg-customStepper'])[2]/div/span/span[2]";
	public void CheckoutPageStatus() {
		Log.info("CheckoutPageElements verification starts");
		List<WebElement> StatusInfo = getDriver().findElements(By.xpath(statusInfo));
		if(StatusInfo.size()==5) {
			List<String> listElements = new ArrayList<String>();
			for(WebElement i :  StatusInfo) {
				listElements.add(i.getText());
			}
			//String deli = "\n"; 
			  //String status = String.join(deli, listElements);
			  System.out.println(listElements);
			String elements = "CART,CUSTOMER INFORMATION,SHIPPING INFORMATION,PAYMENT INFORMATION,ORDER CONFIRMATION";
			List<String> myList = new ArrayList<String>(Arrays.asList(elements.split(",")));
			System.out.println(myList);
			if(listElements.containsAll(myList) && myList.containsAll(listElements)) {
				Log.info("Successfully verified that all the status are in correct form");
			}
			else {
				Assert.assertTrue(false, "Status are not in correct form");
			}
		}
	}
	String emailBox = "(//input[@id='sg-GuestAddressEmailInput'])[2]";
	String loginLink = "(//p[contains(text(),'Log-in')])[2]";
	String already = "(//p[contains(text(),'Already have an account ?')])[2]";
	String emailText = "(//p[contains(text(),'Email*')])[2]";
	//String contact = "(//div[@class='MuiBox-root css-npgrt2'])/fieldset/form/div[1]/div[1]/div[1]/p";
	String contact = "//*[@id='sg-checkoutAccordion']";
	public void contactInfoGuest() throws InterruptedException {
		
		Log.info("ContactInfo Starts");
		WebElement Contact = getDriver().findElement(By.xpath(contact));
		System.out.println(Contact.getText());
		Assert.assertTrue(Contact.isDisplayed(), "Contact Information is not present");
		Action.JSClick(getDriver(), Contact);
		Thread.sleep(1000);
		WebElement EmailText = getDriver().findElement(By.xpath(emailText));
		Assert.assertEquals(EmailText.getText(), "Email*", "Email Text is not Present");
		WebElement Already = getDriver().findElement(By.xpath(already));
		Assert.assertEquals(Already.getText(), "Already have an account ?", "Already have an account?  Text is not Present");
		WebElement LoginLink = getDriver().findElement(By.xpath(loginLink));
		Assert.assertEquals(LoginLink.getText(), " Log-in", " Log-in  Text is not Present");
		Assert.assertTrue(LoginLink.isEnabled(), "Login link is not Enabled");
		WebElement EmailBox = getDriver().findElement(By.xpath(emailBox));
		Assert.assertEquals(EmailBox.getAttribute("placeholder"), "Email", " Placeholder  Text is not Present");
		Action.type(EmailBox, "garvitofficial97@gmial.com");
		Log.info("ContactInfo Ends");
			
	}
	
	String emailbox1 = "(//input[@id='sg-webInputEmail'])";
	
	public void contactInfoLogged() throws InterruptedException {
		WebElement Contact = getDriver().findElement(By.xpath(contact));
		System.out.println(Contact.getText());
		Assert.assertTrue(Contact.isDisplayed(), "Contact Information is not present");
		Action.JSClick(getDriver(), Contact);
		Thread.sleep(1000);
		WebElement EmailText = getDriver().findElement(By.xpath(emailText));
		Assert.assertEquals(EmailText.getText(), "Email*", "Email Text is not Present");
		WebElement EmailBox = getDriver().findElement(By.xpath(emailbox1));
		Assert.assertEquals(EmailBox.getAttribute("value"), prop.getProperty("Username"), " Placeholder  Text is not Present");
		Action.type(EmailBox, "garvitofficial97@gmail.com");
			
	}
	
	String makePayment = "(//button[contains(@id,'sg-checkoutPaymentButton')])";
	String validateBtn = "(//button[@id='sg-GuestPhoneButton'])[2]";
	String phoneBox = "(//input[@id='sg-GuestPhoneInputField'])[2]";
	String phonecode = "(//div[@id='sg-GuestPhoneInputSelect'])[2]";
	String phone = "(//p[@id='sg-GuestPhoneLabelInput'])[2]";
	String country = "(//p[@id='sg-GuestCountryLabelInput'])[2]";
	String stateDropdown = "//div[@id = 'menu-state']/div[3]/ul/li";
	String stateBox = "(//div[@id='sg-GuestStateInputSelect'])[2]";
	String state = "(//p[@id='sg-GuestStateLabelInput'])[2]";
	String postalZipBox = "(//input[@id='sg-GuestAddressPostalInput'])[2]";
	String postalZip = "(//p[@id='sg-inputFieldLabel'])[13]";
	String cityBox = "(//input[@id='sg-GuestAddressCityInput'])[2]";
	String city = "(//p[@id='sg-inputFieldLabel'])[12]";
	String addressLine2Box = "(//input[@id='sg-GuestAddressApartmentInput'])[2]";
	String addressLine2 = "(//p[@id='sg-inputFieldLabel'])[11]";
	String addressLine1Box = "(//input[@id='sg-GuestAddressPhoneInput'])[2]";
	String addressLine1 = "(//p[@id='sg-inputFieldLabel'])[10]";
	String shipping =  "(//p[@id='sg-checkoutAccordionTitle'])[5]";
	String name = "(//p[@id='sg-inputFieldLabel'])[9]";
	String nameBox = "(//input[@id='sg-GuestAddressNameInput'])[2]"		;
	public void shippingAddressGuest() throws InterruptedException {
		WebElement Shipping = getDriver().findElement(By.xpath(shipping));
		Action.scrollByVisibilityOfElement(getDriver(), Shipping);
		Assert.assertTrue(Shipping.isDisplayed(), "Shipping Address is not present");
		Action.JSClick(getDriver(), Shipping);
		Thread.sleep(1000);
		WebElement Name = getDriver().findElement(By.xpath(name));
		System.out.println(Name.getText());
		Assert.assertTrue(Name.isDisplayed(), "Name is not present");
		Assert.assertEquals(Name.getText(), "Name*", "Name Text is not Present");
		WebElement NameBox = getDriver().findElement(By.xpath(nameBox));
		Assert.assertEquals(NameBox.getAttribute("placeholder"), "Name", " Placeholder  Text is not Present");
		Action.type(NameBox, "Shipping Testing");
		Log.info("Successfully entered the value in Name box");
		
		WebElement AddressLine1 = getDriver().findElement(By.xpath(addressLine1));
		System.out.println(AddressLine1.getText());
		Assert.assertTrue(AddressLine1.isDisplayed(), "AddressLine1  is not present");
		Assert.assertEquals(AddressLine1.getText(), "Address Line 1*", "Name Text is not Present");
		WebElement AddressLine1Box = getDriver().findElement(By.xpath(addressLine1Box));
		Assert.assertEquals(AddressLine1Box.getAttribute("placeholder"), "Street Address", " Placeholder  Text of Address box 1 is not Present");
		Action.type(AddressLine1Box, "Testing address ");
		Log.info("Successfully entered the value in Address box 1");
		
		
		WebElement AddressLine2 = getDriver().findElement(By.xpath(addressLine2));
		System.out.println(AddressLine2.getText());
		Assert.assertTrue(AddressLine2.isDisplayed(), "AddressLine2  is not present");
		Assert.assertEquals(AddressLine2.getText(), "Address Line 2", "Name Text is not Present");
		WebElement AddressLine2Box = getDriver().findElement(By.xpath(addressLine2Box));
		Assert.assertEquals(AddressLine2Box.getAttribute("placeholder"), "Apartment", " Placeholder  Text of Address box 1 is not Present");
		Action.type(AddressLine2Box, "Karnataka");
		Log.info("Successfully entered the value in Address box 2");
		
		
		WebElement City = getDriver().findElement(By.xpath(city));
		System.out.println(City.getText());
		Assert.assertTrue(City.isDisplayed(), " City  is not present");
		Assert.assertEquals(City.getText(), "City*", "Name Text is not Present");
		WebElement CityBox = getDriver().findElement(By.xpath(cityBox));
		//Assert.assertEquals(AddressLine2Box.getAttribute("placeholder"), "Apartment", " Placeholder  Text of Address box 1 is not Present");
		Action.type(CityBox, "Denver");
		Log.info("Successfully entered the value in City Box");
		
		WebElement PostalZip = getDriver().findElement(By.xpath(postalZip));
		System.out.println(PostalZip.getText());
		Assert.assertTrue(PostalZip.isDisplayed(), " PostalZip is not present");
		Assert.assertEquals(PostalZip.getText(), "Postal/ZIP*", "PostalZip Text is not Present");
		WebElement PostalZipBox = getDriver().findElement(By.xpath(postalZipBox));
		//Assert.assertEquals(AddressLine2Box.getAttribute("placeholder"), "Apartment", " Placeholder  Text of Address box 1 is not Present");
		Action.type(PostalZipBox, "80014");
		Log.info("Successfully entered the value in PostalZipBox Box");
		
		
		WebElement State = getDriver().findElement(By.xpath(state));
		System.out.println(State.getText());
		Assert.assertTrue(State.isDisplayed(), " State is not present");
		Assert.assertEquals(State.getText(), "State*", "State Text is not Present");
		WebElement StateBox = getDriver().findElement(By.xpath(stateBox));
		//Assert.assertEquals(AddressLine2Box.getAttribute("placeholder"), "Apartment", " Placeholder  Text of Address box 1 is not Present");
		Action.click(getDriver(), StateBox);
		Thread.sleep(1000);
		List<WebElement> states = getDriver().findElements(By.xpath(stateDropdown));
		for(WebElement i : states) {
			System.out.println();
			if(i.getText().equals("Colorado")) {
				Action.click(getDriver(), i);
				break;
			}
		}
		Log.info("Successfully entered the value in StateBox Box");
		
		WebElement Country = getDriver().findElement(By.xpath(country));
		System.out.println(Country.getText());
		Assert.assertTrue(Country.isDisplayed(), " Country is not present");
		Assert.assertEquals(Country.getText(), "Country*", "Country Text is not Present");
		Log.info("Successfully entered the value in Country Box");

		WebElement Phone = getDriver().findElement(By.xpath(phone));
		System.out.println(Phone.getText());
		Assert.assertTrue(Phone.isDisplayed(), " Phone is not present");
		Assert.assertEquals(Phone.getText(), "Phone", "Phone Text is not Present");
		WebElement Phonecode = getDriver().findElement(By.xpath(phonecode));
		Assert.assertEquals(Phonecode.getText(), "+1", " Phone Code Text is not correct");
		WebElement PhoneBox = getDriver().findElement(By.xpath(phoneBox));
		//Assert.assertEquals(AddressLine2Box.getAttribute("placeholder"), "Apartment", " Placeholder  Text of Address box 1 is not Present");
		Action.type(PhoneBox, "8009311749");
		Log.info("Successfully entered the value in Phone  Box");
		
		WebElement ValidateBtn = getDriver().findElement(By.xpath(validateBtn));
		System.out.println(ValidateBtn.getText());
		Assert.assertTrue(ValidateBtn.isDisplayed(), " VALIDATE is not present");
		Assert.assertEquals(ValidateBtn.getText(), "VALIDATE", "VALIDATE Text is not Present");
		Log.info("Successfully entered the value in Validate Box");
		Action.click(getDriver(), ValidateBtn);
		Thread.sleep(5000);
		Assert.assertEquals(ValidateBtn.getText(), "VALIDATED", "VALIDATED Text is not Present");
		Log.info("Successsfully clicked on the Validate button");
		

		
	}
	String change_add = "(//p[@id='sg-selectedAddressChange'])[2]";
	String address = "(//div[@id='sg-selectedAddressBoxBody'])[2]";
	String shippingBoxUsername = "(//p[@id='sg-selectedAddressName'])[2]";
	public void shippingAddressLogged() throws InterruptedException {
		WebElement Shipping = getDriver().findElement(By.xpath(shipping));
		Action.scrollByVisibilityOfElement(getDriver(), Shipping);
		Assert.assertTrue(Shipping.isDisplayed(), "Shipping Address is not present");
		Action.JSClick(getDriver(), Shipping);
		Thread.sleep(1000);
		
		WebElement Name = getDriver().findElement(By.xpath(shippingBoxUsername));
		System.out.println(Name.getText());
		
		WebElement Address = getDriver().findElement(By.xpath(address));
		WebElement Change_add = getDriver().findElement(By.xpath(change_add));
		
		if(!Name.getText().equals("Add an Address")) {
			if(!Address.getText().equals(null)) {
				System.out.println("Address is " + Name.getText());
				Assert.assertEquals(Change_add.getText(), "Change/Add", "Change/Add Button is not visible");
				Log.info("Address is present");
			}
			
		}
		else if(Name.getText().equals("Add an Address")){
			Assert.assertTrue(true, "Have to add address");
		}
	
		Log.info("Successfully passed  the value in Name box");
	}
		
	String shippingMethod = "(//p[@id='sg-checkoutAccordionTitle'])[6]";			
	public void shippingMethod() throws InterruptedException {
			WebElement ShippingMethod = getDriver().findElement(By.xpath(shippingMethod));
			Action.scrollByVisibilityOfElement(getDriver(), ShippingMethod);
			Assert.assertTrue(ShippingMethod.isDisplayed(), "Shipping Address is not present");
			Action.JSClick(getDriver(), ShippingMethod);
			Thread.sleep(1000);
			System.out.println("sdfghjk");
			WebElement m = getDriver().findElement(By.xpath(makePayment));
			System.out.println(m.isEnabled());
			Action.click(getDriver(), m);
			Action.explicitWaitbyTitle(getDriver(), "Payment", Duration.ofSeconds(5));
		}
	
	
	
		
		String bookMarkicon = "//*[name()='svg' and @data-testid='BookmarkBorderIcon']";
		String mightLikeProductDescription = "(//div[contains(@class,'css-3delsl')])";
		String mightLikeProductNames = "(//div[contains(@class,'css-kgu7cg')])";
		String mightLikeallproduct = "(//div[contains(@class,'css-169jllx')])";
		String mightLikeimages = "(//div[contains(@class,'css-169jllx')])/span/span/img";
		// String mightLike="(//div[normalize-space()='We found other content you might like'])";

		public void articlesPresence() throws InterruptedException {
		  Action.implicitWait(getDriver(), 10); 
		  //WebElement MightLike1 =getDriver().findElement(By.xpath(mightLike1));
		  //Action.scrollByVisibilityOfElement(getDriver(), MightLike1);
		  
		  List<WebElement> MightLikeallproduct = getDriver().findElements(By.xpath(mightLikeallproduct));
		  int n = MightLikeallproduct.size(); System.out.println(n +" Articles should be present in Might Like section"); 
		  int count5 =0;
		  List<WebElement> MightLikeimages = getDriver().findElements(By.xpath(mightLikeimages));
		  int x = MightLikeimages.size(); System.out.println(x+ " Articles should be present in Might Like section"); //Thread.sleep(2000);
		  if(x==n) 
		  {for (WebElement i : MightLikeimages) {
		  Action.mouseOverElement(getDriver(), i); 
		  //Action.explicitWait(getDriver(),i, Duration.ofSeconds(5)); 
		  //Thread.sleep(1000); 
		  if(i.getAttribute("srcset").contains("amazonaws.com")) { count5++; } } 
		  if (count5 == n) { 
		System.out.println("All " + count5 +  " Articles images are present"); } 
		  else { softAssert.assertTrue(false, n - count5 + " Article Images are not present  "); } } else {
		  System.out.println(n-x + " Images are not present ");
		  softAssert.assertTrue(false, +n-x+" Images are not present "); }
		  
		  List<WebElement> MightLikeProductName=
		  getDriver().findElements(By.xpath(mightLikeProductNames)); int y =
		  MightLikeProductName.size(); 
		  if(y==n)
		  {System.out.println("All "+ y +" Article Product Names are present");} 
		  else { System.out.println(n-y + "   Article Product Names are not present ");
		  softAssert.assertTrue(false, +n-y+"  Article Product Names are not present "); }
		  
		  List<WebElement> MightLikeProductDescription=
		  getDriver().findElements(By.xpath(mightLikeProductDescription)); int z =
		  MightLikeProductDescription.size(); if(z==n) {System.out.println("All "+ z
		  +" Article Product description are present");} else { System.out.println(n-z
		  + "  Article Product description are not present ");
		  softAssert.assertTrue(false,
		  +n-z+"  Articleff Product description are not present "); }
		  
		  List<WebElement> BookMarkicon
		  =getDriver().findElements(By.xpath(bookMarkicon)); int c =
		  BookMarkicon.size(); if(c==n) {System.out.println("All "+ c
		  +"  BookMarkicon are present");} else { System.out.println(n-c +
		  " BookMarkicon are not present "); softAssert.assertTrue(false,
		  +n-c+" BookMarkicon are not present "); }
		  
		  }
	
	
	String grandTotal = "(//p[@id='sg-checkoutGrandTotalPrice'])[1]";
	String subtotal = "(//p[@id='sg-checkoutSubtotalPrice'])[1]";
	String totalQty = "(//p[@id='sg-yourCartItemsNumber'])[2]";
	String expandIcon = "(//*[name()='svg' and @data-testid='ExpandMoreIcon'])[8]";
	String qty = "(//p[@id='sg-cartCardCartProductTitle'])";
	String cartPrdPrice ="(//p[@id='sg-cartCardOfferPrice'])[2]"; 
	public void quantityVerification() throws InterruptedException {
		
		WebElement ExpandIcon =getDriver().findElement(By.xpath(expandIcon));
		Action.scrollByVisibilityOfElement(getDriver(), ExpandIcon);
		Action.click(getDriver(), ExpandIcon);
		Thread.sleep(2000);
		
		List<WebElement> CartPrdPrice =getDriver().findElements(By.xpath(cartPrdPrice));
		int c = CartPrdPrice.size();
		
		List<WebElement> Qty =getDriver().findElements(By.xpath(qty));
		int d = Qty.size();
		System.out.println( d);
		//Log.info("gfhjkl;");
		
		
		WebElement TotalQty =getDriver().findElement(By.xpath(totalQty));
		int e = Integer.parseInt(TotalQty.getText());
		System.out.println("Quantity Present= "+e);
		//Log.info("djklhgdjhjbd");
		int sum =0;
		
		//System.out.println(d/2);
		List<Integer> l1 = new ArrayList<Integer>();
		
		for(int i=((d/2)+1);i<=d;i++) {
			String l = qty + "["+i+ "]";
			WebElement p =getDriver().findElement(By.xpath(l));	
			Assert.assertTrue(p.isDisplayed(), "Quanity is not visble");
			int eachQty = Integer.parseInt(p.getText());
			l1.add(eachQty);
			 sum = sum+ eachQty;
		}
		System.out.println("Qunatity in list format = " + l1);
		
		
		
		List<Double> l2 = new ArrayList<Double>();
		for(int i=(c/2)+1;i<=c;i++) {
			String l = cartPrdPrice+ "["+i+ "]";
			WebElement p =getDriver().findElement(By.xpath(l));	
			Assert.assertTrue(p.isDisplayed(), "Price  is not visble");
			String q = p.getText().replaceAll("[^0-9.]", "");
			Double q1 = Double.parseDouble(q);
			l2.add(q1);
		}
		
		System.out.println("Price in list format"+l2);
		
		Assert.assertEquals(e, sum, "Quantity of the products are not same");
		Log.info("Successfully verified the quantity of the products");
		
		double totalPrice = 0;
		 
			  for(int q=0;q<l2.size();q++) 
			  { 
				  double value = (l1.get(q) * l2.get(q));
			    totalPrice = totalPrice + value; 
		  }
		 System.out.println("Total Price =" + totalPrice);
		 float total = (float)totalPrice;
		 
		 WebElement Subtotal = getDriver().findElement(By.xpath(subtotal));
		 System.out.println(Subtotal.getText());
		 String s = Subtotal.getText().replaceAll("[^0-9.]","");
		 System.out.println(s);
		 float Subtotal1 = Float.parseFloat(s);
		 Assert.assertEquals(Subtotal1, total, "total Price is not same");
		 
		 WebElement GrandTotal = getDriver().findElement(By.xpath(grandTotal));
		 System.out.println(GrandTotal.getText());
		 String g = GrandTotal.getText().replaceAll("[^0-9.]","");
		 float GrandT = Float.parseFloat(g);
		 Assert.assertEquals(Subtotal1, GrandT, "Grand total & Sub total is not same"); 
		 
		 
		Log.info("Successfully verified the Total of the products");
		
	}
	
	
	
	
}
