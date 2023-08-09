package com.dotKonnekt.tests;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.pages.BlogPage;
import com.dotKonnekt.pages.CategoryPage;
import com.dotKonnekt.pages.CheckoutPage;
import com.dotKonnekt.pages.CommonPagedetails;
import com.dotKonnekt.pages.HomePage;
import com.dotKonnekt.pages.LoginPage;
import com.dotKonnekt.pages.RecipePageFinal;
import com.dotKonnekt.utility.Log;




public class Safari extends BaseClass{
	
	RecipePageFinal recipePage;
	BlogPage blogPage;
	CategoryPage categoryPage;	
	LoginPage loginPage;
	static HomePage homePage;
	static CommonPagedetails commonPagedetails;
	CheckoutPage checkoutPage;
	
	

	public static void main(String[] args) throws Exception {

        // Set up Sauce Labs capabilities.
    	ChromeOptions browserOptions = new ChromeOptions();
    	browserOptions.setPlatformName("macOS 10.15");
    	browserOptions.setBrowserVersion("latest");
    	Map<String, Object> sauceOptions = new HashMap<>();
    	sauceOptions.put("username", "Gsquare");
    	sauceOptions.put("accessKey", "cc037b2f-f1ad-4f29-ba97-8a12245e8fbd");
    	sauceOptions.put("build", "selenium-build-2J540");
    	sauceOptions.put("name", "<your test name>");
    	browserOptions.setCapability("sauce:options", sauceOptions);

        // Create a RemoteWebDriver instance.
    	URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
    	RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);

        // Navigate to the Swag Labs website.
        driver.get("https://uidotfashion.sangria.tech/");

        // Enter a username and password.
        
        // Click the login button.
        Log.startTestCase("TitleVerification");
		commonPagedetails = new CommonPagedetails();
		homePage = new HomePage();
		homePage.newsletterPopupAlert();
		String actualTitle = commonPagedetails.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, "dotfashion", "Title Not Verified");
		//commonPagedetails.BrokenLinks(url);
		
		Log.endTestCase("TitleVerification");

  
        // Close the browser.
        driver.quit();
    }


}
