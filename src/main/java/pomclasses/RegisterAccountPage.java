package pomclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.UtilityHelper;

import java.util.List;

public class RegisterAccountPage
{
    @FindBy(xpath="//input[@name='firstname']")private WebElement firstNameTextBox;
    @FindBy(xpath="//input[@name='lastname']")private WebElement lastNameTextBox;
    @FindBy(xpath="//input[@name='email']")private WebElement emailTextBox;
    @FindBy(xpath="//input[@name='telephone']")private WebElement telephoneTextBox;
    @FindBy(xpath="//input[@name='password']")private WebElement passwordTextBox;
    @FindBy(xpath="//input[@name='confirm']")private WebElement ConfirmPasswordTextBox;
    @FindBy(xpath = "(//label[@class='radio-inline']//input)[1]")private WebElement newsletterYesOption;
    @FindBy(xpath = "(//label[@class='radio-inline']//input)[2]")private WebElement newsletterNoOption;
    @FindBy(xpath = "//div[@class='pull-right']//input[@type='checkbox']")private WebElement privacyPolicyCheckbox;
    @FindBy(xpath = "//input[@value='Continue']")private WebElement ContinueButton;
    @FindBy(xpath="//div[@class='text-danger']")private List<WebElement> errorTexts;
    @FindBy(xpath = "//div//a//b[text()='Privacy Policy']")private WebElement privacyPolicyLink;
    @FindBy(xpath = "//button[@class='close']")private WebElement closePopupButton;

    public RegisterAccountPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    public void enterFirstName(String firstName)
    {
        firstNameTextBox.click();
        firstNameTextBox.sendKeys(firstName);
    }
    public void enterLastName(String lastname)
    {
        lastNameTextBox.sendKeys(lastname);
    }
    public void enteremail(String email)
    {
        emailTextBox.sendKeys(email);
    }
    public void entertelephone(String telephone)
    {
        telephoneTextBox.sendKeys(telephone);
    }
    public void enterPassword(String password)
    {
        passwordTextBox.sendKeys(password);
    }
    public void enterConfirmPassword(String ConfirmPassword)
    {
        ConfirmPasswordTextBox.sendKeys(ConfirmPassword);
    }
    public void selectNewsletterSubscriptionAsYes()
    {
        newsletterYesOption.click();
    }
    public void selectNewsletterSubscriptionAsNo()
    {
        newsletterNoOption.click();
    }
    public void clickPrivacyPolicyCheck()
    {
        privacyPolicyCheckbox.click();
    }
    public void clickContinueButton()
    {
        ContinueButton.click();
    }
    public Boolean validateAccountCreated(WebDriver driver, String expectedPageTitle)
    {
        String ActualPageTitle=driver.getTitle();
        System.out.println(ActualPageTitle);
        Boolean AccountCreated;
         if(ActualPageTitle.equals(expectedPageTitle))
         {
              AccountCreated=true;
         }
         else
         {
             AccountCreated=false;
         }
         return AccountCreated;
    }
    public void validateAccountDetailsValidation(WebDriver driver, String errorMessage)
    {
        boolean ErrorMessageStatus = driver.findElement(By.xpath("//div[@class='text-danger'][text()='" + errorMessage + "']")).isDisplayed();
        System.out.println(errorMessage);
        Assert.assertTrue(ErrorMessageStatus);
    }
    public void clickPrivacyPolicyLink(WebDriver driver) throws InterruptedException {
        privacyPolicyLink.click();
        Thread.sleep(2000);
        boolean sts = closePopupButton.isEnabled();
        closePopupButton.click();
        System.out.println(sts);

    }

}
