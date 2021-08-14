import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DemoAccount {
   @BeforeTest
    public void setup(){
       WebDriverManager.chromedriver().setup();
    }


    @Ignore
    @Test
    public void test_capabilities(){
        ChromeOptions Options = new ChromeOptions();
        Options.addArguments("--windows-size-1700,800");
        Options.addArguments("--headless");
        Options.setHeadless(true);
        Options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(Options);
        driver.get("https://expired.badssl.com/");
        Assert.assertTrue(driver.findElement(By.id("content")).isDisplayed());
        //driver.manage().window().maximize();
    }

    @Test
    public void test_waits(){
        ChromeOptions Options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(Options);
        WebDriverWait Wait = new WebDriverWait(driver, 20);

       // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://www.seleniumeasy.com/test/jquery-download-progress-bar-demo.html");
        driver.findElement(By.id("downloadButton")).click();

        boolean result = false;
        //Manejo de excepciones
        try{
            result = Wait.until(
                    ExpectedConditions.textToBe(By.className("progress-label"),"Complete!"));
        }
        catch (WebDriverException exception){
        System.out.println("Duro demasiado");
        }

        driver.quit();
        driver.close();
    }

}
