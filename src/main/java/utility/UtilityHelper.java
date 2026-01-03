package utility;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityHelper 
{
	static WebDriver driver;
	
	public static void takeScreenshot() throws IOException
	{
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Opencart\\OC\\target");
		FileHandler.copy(source, dest);
	}

	public static String importdataFromExcel(String path,String Sheet,int rownum,int cellnum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fileinput=new FileInputStream(path);
		String output=WorkbookFactory.create(fileinput).getSheet(Sheet).getRow(rownum).getCell(cellnum).getStringCellValue();
		return output;
	}
	public static void explicitWaituntilVisibility(WebDriver driver, WebElement element, Duration waitInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void implicitWait(long waitinSeconds)
	{
		driver.manage().timeouts().implicitlyWait(waitinSeconds, TimeUnit.SECONDS);
	}
	public static String getCurrentDateAndTime(String setdateformat)
	{
		DateFormat dateformat=new SimpleDateFormat(setdateformat);
		Date date=new Date();

		String expectedDate=dateformat.format(date);
		return expectedDate;
	}
}
