package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BrowserUnit;
import utilities.DriversFactory;

public class TestCase9 {

    private WebDriver driver;
    private String url = "https://practice-cybertekschool.herokuapp.com";
    private By statusBy=By.linkText("Status Code");

    @DataProvider
    protected Object [][] getData()
    {
        return new Object[][]{{"200"},{"300"},{"404"},{"500"}};
    }
    @BeforeTest
    protected void setup(){
        driver= DriversFactory.newDriver();
    }
    @Test(description = "Test case #9" , dataProvider = "getData")
    protected void testCase9(String str) {
        driver.get(url);
        driver.findElement(statusBy).click();
        driver.findElement(By.linkText(str)).click();
        BrowserUnit.wait(5);
        String expected= "This page returned a " +str+ " status code.";
        String actual = driver.findElement(By.tagName("p")).getText().substring(0,39).trim();
        Assert.assertEquals(expected,actual);
    }
    @AfterTest
    protected void tearDown(){
        driver.quit();
    }
}
