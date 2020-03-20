package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BrowserUnit;
import utilities.DriversFactory;

import java.util.List;

public class TestCases1to5 {

    private WebDriver driver;
    private String url="https://practice-cybertekschool.herokuapp.com/";
    private By registration_Form=By.linkText("Registration Form");
    private By dOb=By.name("birthday");
    private By dob_Warning=By.xpath("//small[@data-bv-validator=\"date\"]");

    List<WebElement> allCheck;
    private By programLanguage=By.xpath("//div[@class ='form-check form-check-inline']/label");

    private By enterFirstName=By.xpath("//input[@name=\"firstname\"]");
    private By firstNameWarningMessage=By.xpath("//small[@data-bv-for=\"firstname\" and @data-bv-result=\"INVALID\"]");

    private By enterLastName=By.xpath("//input[@name='lastname']");
    private By lastNameWarningMessage=By.xpath("//small[@data-bv-for='lastname' and @data-bv-result='NOT_VALIDATED']");

    private By enterUsername=By.xpath("//input[@name='username']");
    private String username="GK_Taffa";
    private By enterEmail=By.xpath("//input[@name='email']");
    private By enterPassword=By.xpath("//input[@name='password']");
    private String password="Taffarel12345";
    private By enterPhoneNumber=By.xpath("//input[@name='phone']");
    private By selectGender=By.xpath("input[@value='male'");
    private By selectDepartment=By.name("department");
    private By selectJobTitle=By.name("job_title");
    private By languageJava=By.xpath("//input[@value='java']");
    private By signUp=By.id("wooden_spoon");
    private By message=By.xpath("//p");


    @BeforeTest
    protected void setup(){
        driver= DriversFactory.newDriver();
        driver.get(url);
        BrowserUnit.wait(3);

    }
    @Test
    protected void goToLink(){
        driver.findElement(registration_Form).click();
    }
    @AfterTest
    protected void tearDown(){
        driver.quit();
    }
    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter “wrong_dob” into date of birth input
     * box.
     * Step 4. Verify that warning message is displayed:
     * “The date of birth is not valid”
     */


    @Test
    protected void wrongDOB(){
        driver.findElement(registration_Form).click();
        driver.findElement(dOb).sendKeys("wrong_dob");
        Assert.assertEquals(driver.findElement(dob_Warning).getText(),"The date of birth is not valid");
    }

    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Verify that following options for
     * programming languages are displayed: c++, java,
     * JavaScript
     */
    @Test
    protected void checkText(){
        driver.findElement(registration_Form).click();
        allCheck=driver.findElements(programLanguage);
        System.out.println(allCheck.size());
        Assert.assertEquals(allCheck.get(0).getText(),"C++");
        Assert.assertEquals(allCheck.get(1).getText(),"Java");
        Assert.assertEquals(allCheck.get(2).getText(),"JavaScript");
    }

    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into first
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “first name must be more than 2 and less than 64
     * characters long”
     */
    @Test
    protected void firstNameMessage(){
        driver.findElement(registration_Form).click();
        driver.findElement(enterFirstName).sendKeys("a");
        driver.findElement(firstNameWarningMessage);
        Assert.assertEquals(driver.findElement(firstNameWarningMessage).getText(),"first name must be more than 2 and less than 64 characters long");
    }

    /**
     * Step 1. Go to https://practicecybertekschool.
     * herokuapp.com
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into last
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “The last name must be more than 2 and less than
     * 64 characters long”
     */
    @Test
    protected void lastNameMessage(){
        driver.findElement(registration_Form).click();
        driver.findElement(enterLastName).sendKeys("q");
        driver.findElement(lastNameWarningMessage);
        Assert.assertEquals(driver.findElement(lastNameWarningMessage).getText(),"The last name can only consist of alphabetical letters and dash");
    }
    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter any valid first name.
     * Step 4. Enter any valid last name.
     * Step 5. Enter any valid user name.
     * Step 6. Enter any valid password.
     * Step 7. Enter any valid phone number.
     * Step 8. Select gender.
     * Step 9. Enter any valid date of birth.
     * Step 10. Select any department.
     * Step 11. Enter any job title.
     * Step 12. Select java as a programming language.
     * Step 13. Click Sign up.
     * Step 14. Verify that following success message is
     * displayed: “You've successfully completed
     * registration!”
     */
    @Test
    protected void enterValidCredential(){
        driver.findElement(registration_Form).click();
        driver.findElement(enterFirstName).sendKeys("Cláudio");
        driver.findElement(enterLastName).sendKeys("Taffarel");
        driver.findElement(enterUsername).sendKeys(username);
        driver.findElement(enterEmail).sendKeys("GK_Taffa@galatasaray.com");
        driver.findElement(enterPassword).sendKeys(password);
        driver.findElement(enterPhoneNumber).sendKeys("123-321-1111");
        driver.findElement(selectGender).click();
        driver.findElement(dOb).sendKeys("08/05/1966");
        Select dropDown=new Select(driver.findElement(selectDepartment));
        dropDown.selectByValue("TO");
        dropDown=new Select(driver.findElement(selectJobTitle));
        dropDown.selectByVisibleText("Manager");
        driver.findElement(languageJava);
        driver.findElement(signUp).click();
        BrowserUnit.wait(5);
        Assert.assertEquals(driver.findElement(message).getText(),"You've successfully completed registration!");
    }


}
