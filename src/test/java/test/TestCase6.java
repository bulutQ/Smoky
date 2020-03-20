package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.BrowserUnit;
import utilities.DriversFactory;

public class TestCase6 {

    private WebDriver driver;
    private String url="https://practice-cybertekschool.herokuapp.com/";
    private String url_2="https://www.tempmailaddress.com";
    private String email;
    private By copyingBy=By.id("email");
    private By fullNameBy=By.name("full_name");
    private By emailBy=By.name("email");
    private By signUpBy=By.linkText("Sign Up For Mailing List");
    private By textEmailBy=By.id("odesilatel");
    private By thanksBy=By.id("predmet");

    @BeforeTest
    protected void setup(){
        driver= DriversFactory.newDriver();
        driver.get(url_2);
        driver.manage().window().maximize();


    }
    @AfterTest
    protected void tearDown(){
        driver.quit();
    }
    @Test
    protected void testCase6(){
        driver.get(url_2);
        email=driver.findElement(copyingBy).getText();

        driver.get(url);
        driver.findElement(signUpBy).click();
        BrowserUnit.wait(3);
        driver.findElement(fullNameBy).sendKeys("Cláudio Taffarel");
        BrowserUnit.wait(3);
        driver.findElement(emailBy).sendKeys(email, Keys.ENTER);

        BrowserUnit.wait(3);
        Assert.assertEquals(driver.findElement(By.tagName("h3")).getText(),"Thank you for signing up. Click the button below to return to the home page.");

        driver.get(url_2);
        BrowserUnit.wait(4);
        String verifyMail = driver.findElement(By.xpath("//td[@class='from']")).getText().trim();
        String expectedMail = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(verifyMail,expectedMail);

        driver.findElement(By.xpath("//td[@class='from']")).click();
        BrowserUnit.wait(3);


        Assert.assertEquals(driver.findElement(textEmailBy).getText().trim(),"do-not-reply@practice.cybertekschool.com");

        Assert.assertEquals(driver.findElement(thanksBy).getText(),"Thanks for subscribing to practice.cybertekschool.com!");

    }

    /**
     * Step 1. Go to "https://www.tempmailaddress.com/"
     * Step 2. Copy and save email as a string.
     * Step 3. Then go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 4. And click on “Sign Up For Mailing List".
     * Step 5. Enter any valid name.
     * Step 6. Enter email from the Step 2.
     * Step 7. Click Sign Up
     * Step 8. Verify that following message is displayed:
     * “Thank you for signing up. Click the button below to
     * return to the home page.”
     * Step 9. Navigate back to the “https://
     * www.tempmailaddress.com/”
     * Step 10. Verify that you’ve received an email from
     * “do-not-reply@practice.cybertekschool.com”
     * Step 11. Click on that email to open it.
     * Step 12. Verify that email is from: “do-notreply@
     * practice.cybertekschool.com”
     * Step 13. Verify that subject is: “Thanks for
     * subscribing to practice.cybertekschool.com!”
     */

}
