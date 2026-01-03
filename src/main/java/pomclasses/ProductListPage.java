package pomclasses;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utility.UtilityHelper;

public class ProductListPage {

	WebDriver driver;
	@FindBy(xpath="//select[@id='input-sort']")private WebElement SortByDropdown;
	@FindBy(xpath="//div//h4//a")private List<WebElement> productsShown;
	@FindBy(xpath="//div//p[@class='price']")private List<WebElement> Productsprice;
	@FindBy(xpath = "//select[@id='input-limit']")private WebElement showDropdownFilter;
	@FindBy(xpath="//button//span[text()='Add to Cart']")private List<WebElement> AddToCartButton;


	
	public ProductListPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void ChangeView(String viewType,WebDriver driver)
	{

		WebElement viewTypetoSelect = driver.findElement(By.xpath("//button[@id='" + viewType + "-view']"));
		UtilityHelper.explicitWaituntilVisibility(driver,viewTypetoSelect, Duration.ofSeconds(10));
		viewTypetoSelect.click();
	}

	public void validateViewSelected(String viewType, WebDriver driver)
	{
		WebElement viewTypeSelected = driver.findElement(By.xpath("//button[@id='"+viewType+"-view'][@class='btn btn-default active']"));
		boolean viewTypeStatus = viewTypeSelected.isEnabled();
		Assert.assertTrue(viewTypeStatus);
	}
	public void validateSortByFilterOptionsText(String[] ExpectedSortByOptionsTexts)
	{
		Select select=new Select(SortByDropdown);
		List<WebElement> SortByOptions = select.getOptions();
		
		String ActualSortByOptionsTexts[]=new String[SortByOptions.size()];
		
		for(int i=0;i<=SortByOptions.size()-1;i++) 
		{
			String ActualSortByOption = SortByOptions.get(i).getText();
			System.out.println(ActualSortByOption);
			ActualSortByOptionsTexts[i]=ActualSortByOption;
		}
		Assert.assertEquals(ActualSortByOptionsTexts, ExpectedSortByOptionsTexts);
	}
	public void selectSortByFilter(String filterTypeToSelect)
	{
		Select select=new Select(SortByDropdown);
		select.selectByVisibleText(filterTypeToSelect);
		
	}
	public void validateSortByNameFilter(String orderToApply)
	{
		String order=orderToApply;

		if(order.equals("Name (A - Z)"))
		{
			String ActualproductsShownNames[] = new String[productsShown.size()];
			for (int i = 0; i <= productsShown.size() - 1; i++)
			{
				String RawproductName = productsShown.get(i).getText();
				String productName=RawproductName.toLowerCase();
				System.out.println(productName);

				ActualproductsShownNames[i] = productName;
			}

			String[] sortedproductsShownNames = Arrays.copyOf(ActualproductsShownNames, ActualproductsShownNames.length);
			Arrays.sort(sortedproductsShownNames);
			Assert.assertEquals(ActualproductsShownNames, sortedproductsShownNames, "Sort by name A to Z filter is applied correctly.");
		}
		else if (order.equals("Name (Z - A)"))
		{
			String ActualproductsShownNames[] = new String[productsShown.size()];
			for (int i = 0; i <= productsShown.size() - 1; i++)
			{

				String RawproductName = productsShown.get(i).getText();
				String productName=RawproductName.toLowerCase();
				System.out.println(productName);

				ActualproductsShownNames[i] = productName;

			}

			String[] sortedproductsShownNames = Arrays.copyOf(ActualproductsShownNames, ActualproductsShownNames.length);
			Arrays.sort(sortedproductsShownNames, Collections.reverseOrder());
			Assert.assertEquals(ActualproductsShownNames, sortedproductsShownNames, "Sort by name Z to A filter is applied correctly.");
		}


	}
	public void validateSortByPriceFilter(String orderToApply)
	{
		String priceOrder = orderToApply;

		if (orderToApply.equals("Price (Low > High)"))
		{
			String ActualProductsPrices[] = new String[Productsprice.size()];
			for (int i = 0; i <= Productsprice.size() - 1; i++) {
				String fetchedpriceText = Productsprice.get(i).getText();
				if (fetchedpriceText.isEmpty()) {
					fetchedpriceText = driver.findElement(By.xpath("(//div//p[@class='price'])[" + i + "]//span[@class='price-new']")).getText();
				}
				int deciIndex = 0;
				for (int j = 0; j <= fetchedpriceText.length() - 1; j++) {
					char gff = fetchedpriceText.charAt(j);
					if (gff == '.') {
						deciIndex = j;
						break;
					}
				}
				String priceText = fetchedpriceText.substring(0, deciIndex).replace("$", "");
				ActualProductsPrices[i] = priceText;

			}
			String[] ProductsWithSortedPrices = Arrays.copyOf(ActualProductsPrices, ActualProductsPrices.length);
			Arrays.sort(ProductsWithSortedPrices);
			Assert.assertEquals(ActualProductsPrices, ProductsWithSortedPrices, "products sorted correctly");
		}
		else if (orderToApply.equals("Price (High > Low)"))
		{
			String ActualProductsPrices[] = new String[Productsprice.size()];
			for (int i = 0; i <= Productsprice.size() - 1; i++) {
				String fetchedpriceText = Productsprice.get(i).getText();
				if (fetchedpriceText.isEmpty()) {
					fetchedpriceText = driver.findElement(By.xpath("(//div//p[@class='price'])[" + i + "]//span[@class='price-new']")).getText();
				}
				int deciIndex = 0;
				for (int j = 0; j <= fetchedpriceText.length() - 1; j++) {
					char gff = fetchedpriceText.charAt(j);
					if (gff == '.') {
						deciIndex = j;
						break;
					}
				}
				String priceText = fetchedpriceText.substring(0, deciIndex).replace("$", "");
				System.out.println(priceText);
				ActualProductsPrices[i] = priceText;

			}
			String[] ProductsWithSortedPrices = Arrays.copyOf(ActualProductsPrices, ActualProductsPrices.length);
			Arrays.sort(ProductsWithSortedPrices, Collections.reverseOrder());
			Assert.assertEquals(ActualProductsPrices, ProductsWithSortedPrices, "products sorted correctly");

		}
	}
	public void selectShowFilterValue(String numberOfProductsToShow)
	{
		Select select=new Select(showDropdownFilter);
		select.selectByVisibleText(numberOfProductsToShow);
	}

	public void validateShowFilterApplied(int NumberOfProductsToBeShown) {
		int ActualNumberOfProductsShown = productsShown.size();
		boolean NumberOfProductsShownIsProper;
		if (ActualNumberOfProductsShown <= NumberOfProductsToBeShown) {
			NumberOfProductsShownIsProper = true;
		} else {
			NumberOfProductsShownIsProper = false;
		}
		Assert.assertTrue(NumberOfProductsShownIsProper, "products to show filter applied successfully and showing " + ActualNumberOfProductsShown + " products");
	}
	public void AddToCartProduct(String ProductTobeAddedToCart)
	{
		for(int i=0;i<=productsShown.size();i++)
		{
			String productName = productsShown.get(i).getText();
			if(productName.equals(ProductTobeAddedToCart))
			{
				AddToCartButton.get(i).click();
				break;
			}
		}

	}
	public void validateAddToCartSuccessMessage(String ProductAddedToCart, WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		WebElement SuccessMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible'][text()=' Success: You have added ']//a[text()='" + ProductAddedToCart + "']"));
		boolean SuccessMessageStatus = SuccessMessage.isDisplayed();
		Assert.assertTrue(SuccessMessageStatus);
	}
}
