package baseclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Testbase {
	
	public static Properties properties;
	static FileInputStream fileinput;
	public static WebDriver driver;
	
 public static void initializeProperties() throws IOException
	{
		 properties=new Properties();
		 try
		 {
			 fileinput=new FileInputStream("C:\\OCIntellij\\src\\main\\java\\configurations\\configurations.properties");
     		 properties.load(fileinput);
		 }
		 catch(FileNotFoundException e)
		 {
			 e.printStackTrace();
		 }
	}
 public static void initialization() throws IOException {
	 initializeProperties();
	 String browsername=properties.getProperty("browser");
	 
	 if(browsername.equals("chrome"))
	 {
		 driver=new ChromeDriver();
		 driver.get(properties.getProperty("url"));
		 	 }
	 else if(browsername.equals("firefox"))
	 {
		 driver=new FirefoxDriver();
		 driver.get(properties.getProperty("url"));
	 }
	 driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
 }
 

}
