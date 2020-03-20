package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DriversFactory;

public class TestCase8 {

    private WebDriver driver;
    private String url="https://practice-cybertekschool.herokuapp.com";
    private By autoCompleteBy=By.linkText("Autocomplete");
    private By myCountryBy=By.id("myCountry");
    private By submitBy=By.className("btn");
    private By resultBy=By.id("result");

    @BeforeTest
    public void start(){
        driver= DriversFactory.newDriver();

    }

    @Test
    public void testCase8(){
        driver.get(url);
        driver.findElement(autoCompleteBy).click();
        driver.findElement(myCountryBy).sendKeys("Turkey");
        driver.findElement(submitBy).click();
        Assert.assertEquals(driver.findElement(resultBy).getText()
                ,"You selected: Turkey");


    }



    @AfterTest
    public void close(){
        driver.close();
    }



    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. And click on “Autocomplete”.
     * Step 3. Enter “United States of America” into
     * country input box.
     * Step 4. Verify that following message is displayed:
     * “You selected: United States of America”
     * Optional: If you want to to be a real selenium hero,
     * use @DataProvider for for tests cases from 9
     * through 12.
     * Please use following documentation: https://
     * testng.org/doc/documentationmain.
     * html#parameters-dataproviders
     */
}
