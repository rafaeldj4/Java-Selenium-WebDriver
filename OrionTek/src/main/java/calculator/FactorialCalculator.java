package calculator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FactorialCalculator {

    public WebDriver driver;
    String driverPath = "C://chromedriver.exe";
    String baseUrl = "http://qainterview.pythonanywhere.com/";
    String caracteres = "Hello wordl";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void calculateNumber() throws InterruptedException {
        //Number input
        driver.findElement(By.id("number")).sendKeys("4");

        //Calculate Button
        driver.findElement(By.id("getFactorial")).click();
        Thread.sleep(1000);


        WebElement calculationResults = driver.findElement(By.xpath("//div[@class=\"row\"][2]//p"));
        System.out.println(calculationResults.getText());
        String[] numberArry = calculationResults.getText().split(":");

        //Validation
        Assert.assertEquals(Integer.parseInt(numberArry[1].trim()),24);

    }


    @AfterMethod
    public void tearDown() throws IOException {
        //Screenshot
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\Rafael Mejia\\Desktop\\OrionTek\\screenshot.png"));

        //End program
        driver.quit();

    }


}
