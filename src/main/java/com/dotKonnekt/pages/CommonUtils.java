package com.dotKonnekt.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.dotKonnekt.actionDrivers.Action;
import com.dotKonnekt.base.BaseClass;
import com.dotKonnekt.utility.Log;

public class CommonUtils extends BaseClass {
	
	CommonUtils commonUtils;
	public WebDriver driver;
	
	
	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1y9mfc5']")
	List<WebElement> skinCategory;
	
	@FindBy(xpath = "//p[normalize-space()='All']")
	WebElement Alltag;
	
	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 MuiGrid-wrap-xs-nowrap css-1p5hpgl']//img[@alt='logo']")
	WebElement preprationLogo;
	
	@FindBy(xpath = "//button[normalize-space()='Skin']")
	WebElement skinElement;
	
	public CommonUtils() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void validateMenuLists() {
		//Action.implicitWait(getDriver(), 5);
		//Action.explicitWait(getDriver(), preprationLogo, Duration.ofSeconds(20));
		int count = 0;
		List<WebElement> menuList = getDriver().findElements(By.xpath("//div[@class='MuiBox-root css-dgk9vs']/button"));
		System.out.println(menuList.size());
		

		for (WebElement webElement : menuList) {
			String text = webElement.getText();
			System.out.print(text+", ");
			count++;
		}
		
		
		if(count!=0) {
			if (count == menuList.size()) {
				System.out.println("menuLists elements are equal");
			} else {
				System.out.println("menuLists elements are not equal");
				Assert.assertTrue(false);
			} 
		}
		else {
			System.out.println("MenusLists are not present");
		}
	}
	
	public void validatePageHeader() throws InterruptedException {
				Log.info("-------SEARCHBOX FUNCTIONALITY START ------");

				WebElement searchBox = getDriver().findElement(By.xpath("//input[@id='free-solo-2-demo']"));
				if (searchBox.isDisplayed()) {
					System.out.println("Search Box is visible. Return: " + searchBox.isDisplayed());
				} else {
					System.out.println("Search Box is not visible. Return: " + searchBox.isDisplayed());
					Assert.assertTrue(searchBox.isDisplayed());
				}

				// that the “Search” Box is enabled
				if (searchBox.isEnabled()) {
					System.out.println("Search Box is enabled. Return: " + searchBox.isEnabled()); // Thread.sleep(5000);
					Action.explicitWait(getDriver(), searchBox, Duration.ofSeconds(10));																			// searchBox.sendKeys("Selenium");
				
				} else {
					System.out.println("Search Box is not enabled. Return: " + searchBox.isEnabled());
					Assert.assertTrue(searchBox.isEnabled());
				}
				Log.info("-------SEARCHBOX FUNCTIONALITY END ------");

				Log.info("-------CARTButton FUNCTIONALITY START ------");

				WebElement cart = getDriver().findElement(By.xpath("(//img[@alt ='logo'])[3]"));
				if (cart.isDisplayed()) {
					System.out.println("Search Box is visible. Return: " + cart.isDisplayed());
				} else {
					System.out.println("Search Box is not visible. Return: " + cart.isDisplayed());
				}

				// verify that the “Cart” Button is enabled
				if (cart.isEnabled()) {
					System.out.println("Search Box is enabled. Return: " + cart.isEnabled());
				} else {
					System.out.println("Search Box is not enabled. Return: " + cart.isEnabled());
					Assert.assertTrue(cart.isEnabled());
				}
				Log.info("-------CARTButton FUNCTIONALITY END ------");

				Log.info("-------userButton FUNCTIONALITY START ------");

				WebElement userButton = getDriver().findElement(By.xpath("(//img[@alt='logo'])[4]"));
				if (userButton.isDisplayed()) {
					System.out.println("Search Box is visible. Return: " + userButton.isDisplayed());
				} else {
					System.out.println("Search Box is not visible. Return: " + userButton.isDisplayed());
				}

				// verify that the “Search” Box is enabled
				if (userButton.isEnabled()) {
					System.out.println("Search Box is enabled. Return: " + userButton.isEnabled());
				} else {
					System.out.println("Search Box is not enabled. Return: " + userButton.isEnabled());
					Assert.assertTrue(userButton.isEnabled());
				}
				Log.info("-------userButton FUNCTIONALITY END ------");

				Log.info("-------Logo FUNCTIONALITY START ------");

				WebElement Logo = getDriver().findElement(By.xpath("(//img[@alt='logo'])[1]"));
				if (Logo.isDisplayed()) {
					System.out.println("Search Box is visible. Return: " + Logo.isDisplayed());
				} else {
					System.out.println("Search Box is not visible. Return: " + Logo.isDisplayed());
					Assert.assertTrue(false);
				}

				// verify that the “LOGO” Box is enabled
				if (Logo.isEnabled()) {
					System.out.println("Search Box is enabled. Return: " + Logo.isEnabled());
	
				} else {
					System.out.println("Search Box is not enabled. Return: " + Logo.isEnabled());
					Assert.assertTrue(Logo.isEnabled());
				}
				Log.info("-------Logo FUNCTIONALITY END ------");

	}
	
	
	public void validateSkinLists(){
		Action.implicitWait(getDriver(), 10);
		Log.info("Click on Skin category from menulist");
		//getDriver().findElement(By.xpath("//button[normalize-space()='Skin']")).click();
		Action.click(getDriver(), skinElement);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Action.explicitWait(getDriver(), Alltag, Duration.ofSeconds(10));
		System.out.println(skinCategory.size());
				int count =0;
				for(WebElement element: skinCategory) {
					System.out.println(element.getText());
					count++;
				}
				if(count!=0) {
					if(count==skinCategory.size()) {
						System.out.println("Skin Lists are FINE");
					} else {
						System.out.println("Skin Lists are not CORRECT");
						Assert.assertTrue(false);
					}
				}
				else {
					System.out.println("SkinLists are not present");
					Assert.assertTrue(false);
				}
			}
}
