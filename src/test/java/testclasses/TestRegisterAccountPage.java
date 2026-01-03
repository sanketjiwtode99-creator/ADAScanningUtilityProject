package testclasses;

import baseclasses.Testbase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pomclasses.MainPage;
import pomclasses.RegisterAccountPage;
import utility.UtilityHelper;

import java.io.IOException;

public class TestRegisterAccountPage extends Testbase
{
    MainPage mainpage;
    RegisterAccountPage registeraccountpage;

    @BeforeMethod
    public void setup() throws InterruptedException, IOException {
        initialization();
        mainpage=new MainPage(driver);
        registeraccountpage=new RegisterAccountPage(driver);
        mainpage.clickMyAccountDropdown();
        mainpage.openRegisterAccountPage();


    }
    @Test
    public void testAccountRegister()
    {
        registeraccountpage.enterFirstName("tesst");
        registeraccountpage.enterLastName("tesst");
        registeraccountpage.enteremail("test"+UtilityHelper.getCurrentDateAndTime("hhss")+"@gmail.com");
        registeraccountpage.entertelephone(UtilityHelper.getCurrentDateAndTime("ddmmyyhhss"));
        registeraccountpage.enterPassword("1234567890");
        registeraccountpage.enterConfirmPassword("1234567890");
        registeraccountpage.selectNewsletterSubscriptionAsYes();
        registeraccountpage.clickPrivacyPolicyCheck();
        registeraccountpage.clickContinueButton();
        Assert.assertTrue(registeraccountpage.validateAccountCreated(driver,"Your Account Has Been Created!"));

    }
    @Test
    public void testAccountRegisterWithInvalidData()
    {
        registeraccountpage.enterFirstName("test1");
        registeraccountpage.enterLastName("test1");
        registeraccountpage.enteremail("test"+UtilityHelper.getCurrentDateAndTime("hhss")+"@gmail.com");
        registeraccountpage.entertelephone(UtilityHelper.getCurrentDateAndTime("ddmmyyhhss"));
        registeraccountpage.enterPassword("1234567890");
        registeraccountpage.enterConfirmPassword("1234667890");
        registeraccountpage.selectNewsletterSubscriptionAsYes();
        registeraccountpage.clickPrivacyPolicyCheck();
        registeraccountpage.clickContinueButton();
        Assert.assertFalse(registeraccountpage.validateAccountCreated(driver,"Your Account Has Been Created!"));
        registeraccountpage.validateAccountDetailsValidation(driver, properties.getProperty("passworderrormessage"));
    }
    @Test
    public void testPrivacyPolicyLink() throws InterruptedException {
        registeraccountpage.clickPrivacyPolicyLink(driver);
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
