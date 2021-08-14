import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected WebDriver driver;

    @BeforeTest
    public  void BeforeTest()
    {
      // System.out.println("*Esto corre 1 vez");
    }

    @BeforeMethod
    public void BeforeMethod(){
       // System.out.println("**Esto corre 2 veces");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @AfterTest
    public void AfterTest(){

    }

    @AfterMethod
    public void AfterMethod(){
        TakeScreenshot();
        driver.close();
        driver.quit();
    }

    @Attachment(value="screenshot", type = "image/png")
    public byte[] TakeScreenshot(){
        return ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.BYTES);
    }
}
