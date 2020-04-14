package test.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BrowserUnit;
import utilities.DriversFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.net.HttpURLConnection;


public class TestCase1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private String url1="http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox";
    private String url2="http://practice.cybertekschool.com/dropdown";
    private By daysBy=By.className("gwt-CheckBox");
    private Random random = new Random();
    private String amazon="https://www.amazon.com/";
    private String amazon_2="https://www.amazon.com/gp/site-directory";
    private String schoolsLink="https://www.w3schools.com/";
    private String selenium="https://www.selenium.dev/documentation/en/";
    private String validUrl="";
    private HttpURLConnection huc = null;
    private int respCode = 200;

    @BeforeMethod
    protected void setup(){
        driver= DriversFactory.newDriver();
        wait= new WebDriverWait(driver,5);
        driver.manage().window().maximize();
    }
    @Test(testName = "Days_Test",description = "Randomly selecting a checkbox and print the name of the day When it checks Friday for the third time quit")
    protected void daysSelect(){
        driver.get(url1);
        BrowserUnit.wait(3);
        List<WebElement> listCheckBoxes=driver.findElements(daysBy);
        int count=0;
        Random random=new Random();
        while(count<3){
            int index=random.nextInt(7);
            WebElement inputBox=listCheckBoxes.get(index).findElement(By.tagName("input"));
            if(inputBox.isEnabled()){
                listCheckBoxes.get(index).click();
                String day=listCheckBoxes.get(index).getText();
                System.out.println("Randomly selected day: " +day);
                listCheckBoxes.get(index).click();
                if (day.equalsIgnoreCase("Friday")) {
                    count++;
                }
            }
        }

    }
    /**1. go to http://practice.cybertekschool.com/dropdown
     2. verify that dropdowns under Select your date of birth display current year, month, day*/
//    @Test
//    protected void todaysDate(){
//        driver.get(url2);
//        Select year = new Select(driver.findElement(By.id("year")));
//        String value_year = year.getFirstSelectedOption().getText();
//        Select month = new Select(driver.findElement(By.id("month")));
//        String value_month = month.getFirstSelectedOption().getText();
//        Select day = new Select(driver.findElement(By.id("day")));
//        String value_day = day.getFirstSelectedOption().getText();
//        String actual_date = value_year+" "+value_month+" "+value_day;
//        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd"));
//        Assert.assertEquals(actual_date,expected);
//    }
    @DataProvider(name = "elements")
    public Object[][] testElements(){
        return new Object[][]{
                {"year",String.valueOf(LocalDate.now().getYear())},
                {"month",String.valueOf(LocalDate.now().getMonth()).toLowerCase()},
                {"day",String.valueOf(LocalDate.now().getDayOfMonth())}
        };
    }
    @Test (dataProvider = "elements",testName = "Today's_Date_Test",description = "verify that dropdown displaying current year, month, day")
    public void daysTest(String by, String expected){
        String actual=(new Select(driver.findElement(By.id(by))).getFirstSelectedOption().getText());
        actual=actual.toLowerCase();
        Assert.assertEquals(actual,expected);
    }

    @Test(testName = "Years_Months_Days_Test",description = "Selects a random year then checks for all months and verifying the chosen year if its a leap year ")
    protected void yearsMonthsDate(){
        driver.get(url2);
        Select yearRandom=new Select(driver.findElement(By.id("year")));
        List<WebElement> listYears=yearRandom.getOptions();
        int randomNumber=random.nextInt(listYears.size());
        String selected=listYears.get(randomNumber).getText();
        yearRandom.selectByVisibleText(selected);

        Select monthRandom=new Select(driver.findElement(By.id("month")));
        List<WebElement> listMonths=monthRandom.getOptions();

        LocalDate date=LocalDate.of(Integer.parseInt(selected),1,1);

        for (WebElement eachMonth:listMonths){
            monthRandom.selectByVisibleText(eachMonth.getText());

            Select dayRandom=new Select(driver.findElement(By.id("day")));
            List<WebElement> listDays=dayRandom.getOptions();

            int actualDay=YearMonth.of(Integer.parseInt(selected),listMonths.indexOf(eachMonth)+1).lengthOfMonth();

            if (date.isLeapYear() &&  listMonths.indexOf(eachMonth)==1){
                Assert.assertEquals(29,actualDay);
            }else {
                Assert.assertEquals(listDays.size(), actualDay);
            }
        }

    }

    @Test(testName = "Departments_Sort_Test",description = "verifying All departments dropdown and check if its sorted")
    protected void departmentsSort(){
        driver.get(amazon);
        Assert.assertEquals("All",driver.findElement(By.className("nav-search-label")).getText());
        Select menu=new Select(driver.findElement(By.id("searchDropdownBox")));
        List<WebElement> listOption=menu.getOptions();

        char prev=65;
        char temp=65;
        for (WebElement options:listOption){
            prev=options.getText().trim().charAt(0);
            if (prev<temp) {
                Assert.assertTrue(true," All departments dropdown are not sorted alphabetically");
                temp=prev;
            }
//            System.out.println(options.getText().trim().charAt(0));
        }
    }

    @Test(testName = "Main_Departments_Test",description = "checks every main department and compares with All departments")
    protected void mainDepartments(){
        driver.get(amazon_2);
        List<WebElement> departments=driver.findElements(By.className("fsdDeptTitle"));
        List<String> depNames=new ArrayList<>();
        departments.forEach(n->depNames.add(n.getText().trim()));

        Select drop= new Select(driver.findElement(By.id("searchDropdownBox")));
        List<WebElement>dropDeps=drop.getOptions();
        List<String> dropNames=new ArrayList<>();
        dropDeps.forEach(n->dropNames.add(n.getText().trim()));

        for (String name:depNames){
            Assert.assertTrue(dropNames.contains(name),("Expected: " + name+" doesn't appear in least"));
        }

    }

    @Test(testName = "Links_Test",description = "finds all displayed links and verify")
    protected void links(){
        driver.get(schoolsLink);
        List<WebElement> linksList = driver.findElements(By.tagName("a"));
        for (WebElement allElements : linksList) {
            if (allElements.isDisplayed()) {
                System.out.println(allElements.getText() + "-->>" + allElements.getAttribute("href"));
            }
        }
    }

    @Test(testName = "Valid_Links_Test",description = "finds all displayed links and verify")
    protected void validLinks(){
        driver.get(selenium);
        List<WebElement> linksList=driver.findElements(By.tagName("a"));
        for (WebElement webElement : linksList) {
            validUrl = webElement.getAttribute("href");
            if (validUrl==null || validUrl.isEmpty()){
                System.out.println("Url is not configured");
            }try{
                huc=(HttpURLConnection)(new URL(validUrl).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode=huc.getResponseCode();
                if (respCode>=400){
                    System.out.println(validUrl+" is a broken link");
                }else {
                    System.out.println(validUrl+" is a valid link");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @AfterMethod
    protected void tearDown(){
        driver.quit();
    }

}
