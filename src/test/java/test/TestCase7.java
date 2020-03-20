package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DriversFactory;

public class TestCase7 {

    private WebDriver driver;
    private String url="https://practice-cybertekschool.herokuapp.com";
    private By clickBy=By.linkText("File Upload");
    private By fileUploadBy=By.id("file-upload");
    private By fileSubmitBy=By.id("file-submit");
    private By textBy=By.tagName("h3");
    private By uploadBy=By.id("uploaded-files");


    @BeforeTest
    public void start(){
        driver= DriversFactory.newDriver();
        driver.get(url);
    }

    @Test
    public void testCase7(){
        driver.get(url);
        driver.findElement(clickBy).click();
        driver.findElement(fileUploadBy).sendKeys("C:\\Users\\WSUSER\\Documents\\G_S.txt");
        driver.findElement(fileSubmitBy).click();
        Assert.assertEquals(driver.findElement(textBy).getText(),"File Uploaded!");
        Assert.assertEquals(driver.findElement(uploadBy).getText(),"G_S.txt");

    }


    @AfterTest
    public void close(){
        driver.quit();
    }
    /**
     * Step 1. Go to “https://practicecybertekschool.
     * herokuapp.com”
     * Step 2. And click on “File Upload".
     * Step 3. Upload any file with .txt extension from your
     * computer.
     * Step 4. Click “Upload” button.
     * Step 5. Verify that subject is: “File Uploaded!”
     * Step 6. Verify that uploaded file name is displayed.
     */

}
