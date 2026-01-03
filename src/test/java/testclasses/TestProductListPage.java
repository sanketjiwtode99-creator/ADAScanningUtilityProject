package testclasses;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclasses.MainPage;
import pomclasses.ProductListPage;

public class TestProductListPage extends Testbase{
	
	MainPage mainpage;
	ProductListPage productlistpage;

	@BeforeMethod
	public void setup() throws IOException {
		initialization();
		mainpage=new MainPage(driver);
		productlistpage=new ProductListPage(driver);
		
		mainpage.OpenDropdownMenu(properties.getProperty("MenuOptionToSelect"),driver);
		//mainpage.clickDropDownMenuOption(properties.getProperty("OptionToSelect"),driver);
		
		}
	@Test
	public void TestViewChange() throws InterruptedException
	{
		productlistpage.ChangeView("grid", driver);

		productlistpage.validateViewSelected("grid", driver);
	}
	@Test
	public void TestSortByOptionsText()
	{
		String ExpectedSortByOptionsTexts[]= {"Default","Name (A - Z)","Name (Z - A)","Price (Low > High)","Price (High > Low)","Rating (Highest)","Rating (Lowest)","Model (A - Z)","Model (Z - A)"};
		productlistpage.validateSortByFilterOptionsText(ExpectedSortByOptionsTexts);
	}
	@Test
	public void TestSortByNameFilter()
	{
		productlistpage.selectSortByFilter("Name (Z - A)");
		productlistpage.validateSortByNameFilter("Name (Z - A)");
	}
	@Test
	public void TestSortByPriceFilter()
	{
		productlistpage.selectSortByFilter("Price (High > Low)");
		productlistpage.validateSortByPriceFilter("Price (High > Low)");
	}
	@Test
	public void TestShowFilter()
	{
		productlistpage.selectShowFilterValue("20");
		productlistpage.validateShowFilterApplied(20);
	}
	@Test
	public void TestAddToCart() throws InterruptedException {
		productlistpage.AddToCartProduct("iPhone");
		productlistpage.validateAddToCartSuccessMessage("iPhone", driver);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
