package pomclasses;

import java.time.Duration;
import java.util.List;

import baseclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.UtilityHelper;

public class MainPage extends BasePage {
	WebDriver driver;
	
	@FindBy(xpath="//button[@class='btn btn-link dropdown-toggle']")private WebElement currencyDropdown;
	@FindBy(xpath="//button[@class='btn btn-link dropdown-toggle']//strong")private WebElement currencytype;
	@FindBy(xpath="(//ul//li//span[@class='hidden-xs hidden-sm hidden-md'])[1]")private WebElement mobileNumber;
	@FindBy(xpath="//a[@title='My Account']")private WebElement myAccountDropdown;
	@FindBy(xpath="//a[text()='Register']")private WebElement registerOption;
	@FindBy(xpath="//ul//li//a[text()='Login']")private WebElement loginOption;
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li//a[@class='dropdown-toggle']")private List<WebElement> NavbarDropdownOptions;
	@FindBy(xpath="//ul//li//a[text()='Desktops']")private WebElement desktopNavDropdown;
	@FindBy(xpath="//div[@class='row']//div[@class='caption']//a")private List<WebElement> ProductsDisplayed; 
	@FindBy(xpath="//input[@name='search']")private WebElement searchBox;
	@FindBy(xpath="//div[@id='search']//button")private WebElement searchButton;
	@FindBy(xpath="//div//ul//li//a[text()='Desktops']")private WebElement DesktopDropdown;

	public MainPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean currencyDropdownVisibilityStatus()
	{
		boolean currencyDropdownStatus = currencyDropdown.isDisplayed();
		return currencyDropdownStatus;
	}
	public void validateCurrencyTypeSelected(String expectedCurrencyType)
	{
		String actualCurrencyType = currencytype.getText();
		Assert.assertEquals(actualCurrencyType, expectedCurrencyType);
		System.out.println(actualCurrencyType);
	}
	public void selectCurrencyType(String currencyType ) throws InterruptedException {

		clickAndWait(currencyDropdown);
        WebElement currencyTypeToSelect
				= driver.findElement(By.xpath("//button[contains(text(),'"+currencyType+"')]"));
		clickAndWait(currencyTypeToSelect);
		currencyTypeToSelect.click();
	}
	public boolean validateMobileNumber()
	{
		boolean mobileNoStatus = mobileNumber.isDisplayed();
		return mobileNoStatus;		
	}
	public void clickMyAccountDropdown() throws InterruptedException {
		Thread.sleep(2000);
		//UtilityHelper.explicitWaituntilVisibility(driver,myAccountDropdown, Duration.ofSeconds(10));
		myAccountDropdown.click();
	}
	public RegisterAccountPage openRegisterAccountPage()
	{
		registerOption.click();
		return new RegisterAccountPage(driver);
	}
	public LoginPage openLoginPage()
	{
		loginOption.click();
		return new LoginPage();
	}
	public void validateNavDropdownsSize(int expectedNavDropdownSize)
	{
		int actualNavDropdownSize = NavbarDropdownOptions.size();
		Assert.assertEquals(actualNavDropdownSize, expectedNavDropdownSize);
	}
	public void validateNavDropdownsTitle(String[] expectedNavDropdowns )
	{
		int actualNavDropdownSize = NavbarDropdownOptions.size();		
		String actualNavDropdowns[]=new String[actualNavDropdownSize];
		
		
		for(int i=0;i<=actualNavDropdownSize-1;i++)
		{
			String actualNavDropdownsValue = NavbarDropdownOptions.get(i).getText();
			actualNavDropdowns[i] = actualNavDropdownsValue;
			
			Assert.assertEquals(actualNavDropdownsValue, expectedNavDropdowns[i], "Dropdown value match at index " + i + ". Expected: " + expectedNavDropdowns[i] + ", Actual: " + actualNavDropdownsValue);
			          
		}
		
	}
	public void validateNavDropdownOptions(WebDriver driver) throws InterruptedException// to be completed
	{
		for(int i=1;i<=NavbarDropdownOptions.size();i++)
		{
			int dropdownOptionsSize = driver.findElements(By.xpath("(//ul//li[@class='dropdown']//div[@class='dropdown-inner'])["+i+"]//ul//li")).size();
			for(int j=0;j<=dropdownOptionsSize-1;j++)
			{
				Thread.sleep(2000);
			String actualDropdownOptionsValue = driver.findElements(By.xpath("(//ul//li[@class='dropdown']//div[@class='dropdown-inner'])["+i+"]//ul//li//a")).get(j).getText();
	System.out.println(actualDropdownOptionsValue);	
	}
		}
	}
	public void validateFeaturedProducts(String[] expectedFeaturedProductsList)
	{
		String ActualFeaturedProductsList[]=new String[ProductsDisplayed.size()];
		
		for(int i=0;i<=ProductsDisplayed.size()-1;i++) 
		{
			String featuredProduct = ProductsDisplayed.get(i).getText();
			 ActualFeaturedProductsList[i]=featuredProduct;
	}
		Assert.assertEquals(ActualFeaturedProductsList, expectedFeaturedProductsList);
	}
	public void sendSearchBoxInput(String inputSearch)
	{
		searchBox.sendKeys(inputSearch);
		searchButton.click();
	}
	public void validateSearchBoxResults(String expectedProductText)
	{
		for(int i=0;i<=ProductsDisplayed.size()-1;i++)
		{
		String searchResultProduct = ProductsDisplayed.get(i).getText();
		if(searchResultProduct.contains(expectedProductText))
		{
			System.out.println(searchResultProduct+" is displayed as expected");
		}
		else {
			Assert.fail();
		}
	}
		}
	public void OpenDropdownMenu(String MenuToSelect,WebDriver driver)
	{
		
		WebElement MenuTobeSelected = driver.findElement(By.xpath("//div//ul//li//a[text()='"+MenuToSelect+"']"));
		//UtilityHelper.explicitWaituntilVisibility(10, MenuTobeSelected);
		MenuTobeSelected.click();
	}
	public ProductListPage clickDropDownMenuOption(String OptionToSelect, WebDriver driver)
	{
		WebElement OptionTobeSelected = driver.findElement(By.xpath("//div//ul//li//a[contains(text(),'"+OptionToSelect+"')]"));
		//UtilityHelper.explicitWaituntilVisibility(10, OptionTobeSelected);
		OptionTobeSelected.click();
		return new ProductListPage(driver);
	}
}
