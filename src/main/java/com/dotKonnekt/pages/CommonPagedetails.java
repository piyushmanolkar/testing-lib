package com.dotKonnekt.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class CommonPagedetails extends BaseClass {
	
	String searchBox = "//input[@placeholder='Search']";
	String clickButton=  "//button[@id='sg-autoCompleteSearchBarSubmitBtn']";
	String welcomeTxt1 = "//p[@id='sg-iconsHeaderWelcomeTitle']";
	String accessTxt ="(//p[@id='sg-iconsHeaderWelcomeDesc'])[1]";
	String loginTxt = "(//button[normalize-space()='LOGIN/SIGNUP'])[1]";
	String categoryElements = "//button[@id='sg-categoryItem']";
	//String Author = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 css-1r6qczh']";
	//String P_Date = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 css-3odfiv']";
	String bd_Home = "(//a[@id='sg-breadcrum0 sg-BreadCrumbLink'])";
	String loginPageTxt = "//input[@placeholder='Email']";
	String likeIcon = "(//*[name()='svg'][@id='sg-thumbUpButtonOutline'])[1]";
	String cart = "//img[@id='iconsHeaderCartImg']";
	String user = "(//img[@id='sg-iconsHeaderActiveUser'])";
	String logo = "(//img[@id='sg-headerLogoImg'])";
	
	public CommonPagedetails() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean valaidateLogo() {
		boolean result  = getDriver().findElement(By.xpath(logo)).isDisplayed();
		return result;
	}
	
	public boolean validateSearchBox() {
		boolean result  = getDriver().findElement(By.xpath(searchBox)).isDisplayed();
		return result;
	}
	
	public boolean validateCartButton() {
		boolean result  = getDriver().findElement(By.xpath(cart)).isDisplayed();
		return result;
	}
	public boolean validateUserButton() {
		boolean result  = getDriver().findElement(By.xpath(user)).isDisplayed();
		return result;
	}
	
	//String cart = "//div[@class='MuiBox-root css-1p3qk0r']//img[@alt='logo']";
	
	public void validateCartIconFunctionality() {
		WebElement cartIcon  =  getDriver().findElement(By.xpath(cart));
		Action.click(getDriver(), cartIcon);
		String totalItmesTxt = getDriver().findElement(By.xpath("//p[@id='sg-floatingCartTotalText']")).getText();
		Assert.assertEquals(totalItmesTxt, "Total item(s):");
		String shippingtxt = getDriver().findElement(By.xpath("//p[@id='sg-orderWarningText']")).getText();
		Assert.assertEquals(shippingtxt, "Shipping and tax will be calculated on checkout");
		boolean result = getDriver().findElement(By.xpath("(//button[@id='sg-orderCommonButton'])")).isEnabled();
		if(result) 
			Assert.assertTrue(false, "Checkout is enabled it should be disabled ");
	}
	
	
	String recentSearch = "(//p[@id='sg-autoCompleteSearchTypography'])";
	String trending = "//p[@id='sg-autoCompleteSearchTypography3']";
	public void validateSeachFunctionality(String searchData, String ClickedBy,String Title) throws InterruptedException {
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
		
		Action.explicitWaitbyTitle(getDriver(), "Search Page", Duration.ofSeconds(10));
		String title1 = getDriver().getTitle();
		Assert.assertEquals(title1, "Search Page"); 
		
		getDriver().navigate().back();
		Action.explicitWaitbyTitle(getDriver(), Title, Duration.ofSeconds(5));
		
	}
	//String welcomeTxt = "(//p[@class='MuiTypography-root MuiTypography-body1 css-k1juyd'])[1]";
	public void UserButtonFunctionality(String Title) throws InterruptedException {
		WebElement userButton = getDriver().findElement(By.xpath(user));
		Action.click(getDriver(), userButton);
		String welcomeText = getDriver().findElement(By.xpath(welcomeTxt1)).getText();
		Assert.assertEquals(welcomeText, "Welcome", "Welcome Text is not present");
		Log.info("Successfully verified the Welcome Text Presence");
		String AccessMsg = getDriver().findElement(By.xpath(accessTxt)).getText();
		Assert.assertEquals(AccessMsg, "To access account and manage orders");
		Log.info("Successfully verified the access text presence");
		String login_signupMsg =getDriver().findElement(By.xpath(loginTxt)).getText();
		Assert.assertEquals(login_signupMsg, "LOGIN/SIGNUP");
		Log.info("Login/Signup presence verified");
		getDriver().findElement(By.xpath(loginTxt)).click();
		Log.info("Login button is active and working perfectly");
		Thread.sleep(1000);
		WebElement login = getDriver().findElement(By.xpath(loginPageTxt));
		Action.explicitWait(getDriver(), login, Duration.ofSeconds(10));
		String loginTitle = getDriver().getTitle();
		Assert.assertEquals(loginTitle, "Login");
		Log.info("Successfully verified the presence of the Login PAge Title");
		getDriver().navigate().back();
		Action.explicitWaitbyTitle(getDriver(), Title, Duration.ofSeconds(5));
		Log.info("Successfully verified the click on the back button");
	}
	
	public void logoFunctionality(String title) {
		
	WebElement pageLogo = 	getDriver().findElement(By.xpath(logo));
	String url  = getDriver().getCurrentUrl();
	if(url.contains("dotbeauty")) {
	Action.click(getDriver(), pageLogo);
		Action.explicitWaitbyTitle(getDriver(), "dot beauty", Duration.ofSeconds(10));
		String recipeTitle = getDriver().getTitle();
		Assert.assertEquals(recipeTitle, "dot beauty"); }
	else {
		Action.click(getDriver(), pageLogo);
		Action.explicitWaitbyTitle(getDriver(), "dotfashion", Duration.ofSeconds(10));
		String recipeTitle = getDriver().getTitle();
		Assert.assertEquals(recipeTitle, "dotfashion");
	}
		Log.info("LogoFunctionalityVerification Works perfectly");
		
		getDriver().navigate().back();
		Action.explicitWaitbyTitle(getDriver(), title, Duration.ofSeconds(10));
	}
	
	
	  public String getTitle() 
	  { String title = getDriver().getTitle();
	  return title; }
	 
	
	String socialMediacIcon = "(//div[@id='sg-footerSubBox3']/a)";
	String newsLetter = "//*[name()='svg'][@data-testid = 'ClearIcon']";
	String elements = "(//p[@id='sg-footerNewsLatterText'])";
	String pagefooter = "//div[@id='sg-footerBox']";
	public void PageFooterLinks() throws InterruptedException {
		
		WebElement Pagefooter = getDriver().findElement(By.xpath(pagefooter));
		Action.scrollByVisibilityOfElement(getDriver(), Pagefooter);
		Thread.sleep(1000);
		List<WebElement> element = getDriver().findElements(By.xpath(elements));
				
			String aboutUs = elements +"[" + 1 + "]";
					String title = getDriver().getTitle();
					WebElement AboutUs = getDriver().findElement(By.xpath(aboutUs));
					Action.scrollByVisibilityOfElement(getDriver(), AboutUs);
					Action.explicitWait(getDriver(), AboutUs, Duration.ofSeconds(5));
					Assert.assertEquals(AboutUs.getText(), "About Us", "About Us Text is not correct" );
					Action.click(getDriver(), AboutUs);
					Action.explicitWaitbyTitle(getDriver(), "about-us", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title, Duration.ofSeconds(3));
					Log.info("Successfully verified the About Us from Page Footer");
			
				
				String privacyPolicy = elements +"[" + 2 + "]";
					WebElement NewsLetter2 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter2);
					Log.info("Successfully clicked on the newsLetter");
					String title3 = getDriver().getTitle();
					WebElement PrivacyPolicy = getDriver().findElement(By.xpath(privacyPolicy));
					Action.scrollByVisibilityOfElement(getDriver(), PrivacyPolicy);
					Action.explicitWait(getDriver(), PrivacyPolicy, Duration.ofSeconds(5));
					Assert.assertEquals(PrivacyPolicy.getText(), "Privacy Policy", "Privacy Policy Text is not correct" );
					Action.click(getDriver(), PrivacyPolicy);
					Action.explicitWaitbyTitle(getDriver(), "privacy-policy", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Privacy Policy from Page Footer");
					
					
			 String terms_Conditions = elements +"[" + 3 + "]";
					WebElement NewsLetter3 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter3);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Terms_Conditions = getDriver().findElement(By.xpath(terms_Conditions));
					Action.scrollByVisibilityOfElement(getDriver(), Terms_Conditions);
					Action.explicitWait(getDriver(),Terms_Conditions, Duration.ofSeconds(5));
					Assert.assertEquals(Terms_Conditions.getText(), "Terms & Conditions", "Terms & Conditions Text is not correct" );
					Action.click(getDriver(), Terms_Conditions);
					Action.explicitWaitbyTitle(getDriver(), "terms-conditions", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Terms_Conditions from Page Footer");
					
					
			String cookie_Policy = elements +"[" + 4 + "]";
					WebElement NewsLetter4 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter4);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Cookie_Policy = getDriver().findElement(By.xpath(cookie_Policy));
					Action.scrollByVisibilityOfElement(getDriver(), Cookie_Policy);
					Action.explicitWait(getDriver(), Cookie_Policy, Duration.ofSeconds(5));
					Assert.assertEquals(Cookie_Policy.getText(), "Cookie Policy", "Cookie Policy Text is not correct" );
					Action.click(getDriver(), Cookie_Policy);
					Action.explicitWaitbyTitle(getDriver(), "cookie-policy", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Cookie Policy from Page Footer");
				
			String contact_Us = elements +"[" + 5 + "]";
					WebElement NewsLetter5 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter5);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Contact_Us = getDriver().findElement(By.xpath(contact_Us));
					Action.scrollByVisibilityOfElement(getDriver(), Contact_Us);
					Action.explicitWait(getDriver(), Contact_Us, Duration.ofSeconds(5));
					Assert.assertEquals(Contact_Us.getText(), "Contact Us", "Contact_Us Text is not correct" );
					Action.click(getDriver(), Contact_Us);
					Action.explicitWaitbyTitle(getDriver(), "contact-us", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Contact_Us from Page Footer");
					
				
				String discussion_Forum = elements +"[" + 6 + "]";
					WebElement NewsLetter6 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter6);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Discussion_Forum = getDriver().findElement(By.xpath(discussion_Forum));
					Action.scrollByVisibilityOfElement(getDriver(), Discussion_Forum);
					Action.explicitWait(getDriver(), Discussion_Forum, Duration.ofSeconds(5));
					Assert.assertEquals(Discussion_Forum.getText(), "Discussion Forum", "Discussion Forum Text is not correct" );
					Action.click(getDriver(), Discussion_Forum);
					Action.explicitWaitbyTitle(getDriver(), "DiscussionForum", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Discussion Forum from Page Footer");
					
				
				String shoppable_Video = elements +"[" + 7 + "]";
					//WebElement NewsLetter7 = getDriver().findElement(By.xpath(newsLetter));
					//Action.click(getDriver(), NewsLetter7);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Shoppable_Video = getDriver().findElement(By.xpath(shoppable_Video));
					Action.scrollByVisibilityOfElement(getDriver(), Shoppable_Video);
					Action.explicitWait(getDriver(), Shoppable_Video, Duration.ofSeconds(5));
					Assert.assertEquals(Shoppable_Video.getText(), "Shoppable Video", "Shoppable Video Text is not correct" );
					Action.click(getDriver(), Shoppable_Video);
					Action.explicitWaitbyTitle(getDriver(), "Shoppable Video", Duration.ofSeconds(5));
					getDriver().navigate().back();
					Action.explicitWaitbyTitle(getDriver(), title3, Duration.ofSeconds(3));
					Log.info("Successfully verified the Shoppable Video from Page Footer");
					
				
				String social = elements +"[" + 8 + "]";
					WebElement NewsLetter7 = getDriver().findElement(By.xpath(newsLetter));
					Action.click(getDriver(), NewsLetter7);
					Log.info("Successfully clicked on the newsLetter");
					
					WebElement Social = getDriver().findElement(By.xpath(social));
					Action.scrollByVisibilityOfElement(getDriver(), Social);
					Action.explicitWait(getDriver(), Social, Duration.ofSeconds(5));
					Assert.assertEquals(Social.getText(), "Social", "Social Text is not correct" );
					List<WebElement> icons = getDriver().findElements(By.xpath(socialMediacIcon));
					Assert.assertEquals(icons.size(), 5, "5 Social media icons are not present");
					
					Log.info("Successfully verified the Social from Page Footer");
		
	}
	
	public void BrokenLinks(String url1) {
		Log.info("BrokenLinks Valiadation Starts");
		 String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
		List<WebElement> links= getDriver().findElements(By.tagName("a"));
		
		Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            
            System.out.println(url);
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!url.startsWith(url1)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
		
	}
	
}
