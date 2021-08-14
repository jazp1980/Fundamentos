import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected WebDriver driver;

    @BeforeTest
    public  void BeforeTest()
    {
      // System.out.println("*Esto corre 1 vez");
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void BeforeMethod(@Optional("chrome") String browser){
       // System.out.println("**Esto corre 2 veces");

        switch (browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
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
        try {
            driver.quit();
        }catch (WebDriverException ex){
            System.out.println("El navegador ya esta cerrado");
        }

    }

    @Attachment(value="screenshot", type = "image/png")
    public byte[] TakeScreenshot(){
        return ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.BYTES);
    }
}
