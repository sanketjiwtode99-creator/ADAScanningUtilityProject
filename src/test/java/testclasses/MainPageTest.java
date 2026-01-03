package testclasses;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclasses.MainPage;

import java.io.IOException;


class MainPageTest extends Testbase
{
	MainPage mainpage;

    @BeforeMethod
    public void setup() throws IOException {
    	initialization();
    	mainpage=new MainPage(driver);
        
    }
    @Test(enabled=true)
    public void testCurrencyDropdownVisibility() throws InterruptedException
    {
    	Thread.sleep(3000);
    	Assert.assertTrue(mainpage.currencyDropdownVisibilityStatus());
    }
    @Test(enabled=true)
    public void testCurrencyTypePreSelected()
    {
    	mainpage.validateCurrencyTypeSelected("$");
    	
    }
    @Test(enabled=true)
    public void testCurrencyTypeSelection() throws InterruptedException {
    	mainpage.selectCurrencyType("Pound");
    	mainpage.validateCurrencyTypeSelected("£");
    }
    @Test(enabled=true)
    public void testNavDropdownsVisibility() throws InterruptedException
    {
    	mainpage.validateNavDropdownsSize(4);
    	String[] expectedNavDropdowns = {"Desktops", "Laptops & Notebooks", "Components", "MP3 Players"};
    	mainpage.validateNavDropdownsTitle(expectedNavDropdowns);
    	mainpage.validateNavDropdownOptions(driver);
    }
    @Test(enabled=true)
    public void testFeaturedProducts()
    {
    	String expectedFeaturedProductsList[]= {"MacBook","iPhone","Apple Cinema 30\"","Canon EOS 5D"};
    	mainpage.validateFeaturedProducts(expectedFeaturedProductsList);
    }
    @Test
    public void testSearchBoxFunctionality()
    {
    	mainpage.sendSearchBoxInput(properties.getProperty("searchinput"));
    	mainpage.validateSearchBoxResults(properties.getProperty("searchinput"));
    }
   
    @AfterMethod
    public void teardown()
    {
    	driver.quit();
    }
}
