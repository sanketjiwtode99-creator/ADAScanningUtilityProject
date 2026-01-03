package baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    public static WebDriver driver;
    public static void clickAndWait(WebElement element) throws InterruptedException {
        element.click();
        Thread.sleep(2000);
    }

    public static void selectDropdownValueByVisibleText(WebElement element, String text)
    {
        Select s = new Select(element);
        s.selectByVisibleText(text);
    }

    public void clickUsingActionClass(WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.click(element).build().perform();
    }
}
