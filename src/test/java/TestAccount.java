import PageObjects.HeaderPage;
import PageObjects.LoginPage;
import PageObjects.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestAccount extends BaseClass{


    @Description("Validate login succesful")
    @Test
    public void Test_Login_Succesful(){
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String user = "jazp1980@gmail.com";
        String pass = "1234";
        String PathToDriver = Test.class.getResource("/chromedriver.exe").getPath();

        //driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
        //driver.findElement(By.linkText("Login")).click();
        headerPage.clickOnMyAccount();
        headerPage.clickOnLoginButton();

        //Llenar formulario
        //driver.findElement(By.id("input-email")).sendKeys(user);
        loginPage.EnterEmail(user);
        //driver.findElement(By.id("input-password")).sendKeys(pass);
        loginPage.EnterPassword(pass);
        //driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
        loginPage.ClickSubmitButton();

        WebElement LogoutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(LogoutButton.isDisplayed());



    }

    @Description("Validate login unsuccesful, with non valid credentials")
    @Test
    public void Test_Login_Unsuccesful() {
        String user = "jazp1981@gmail.com";
        String pass = "1234";
        String expectedmessage = "warning: no match for e-mail address and/or password.";
        LoginPage loginPage = new LoginPage(driver);

        String PathToDriver = Test.class.getResource("/chromedriver.exe").getPath();

        //Go to login page
        //driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
        //driver.findElement(By.linkText("Login")).click();
        //Llenar formulario
        loginPage.GoTo();
        loginPage.login(user, pass);
        // driver.findElement(By.id("input-email")).sendKeys(user);
        // driver.findElement(By.id("input-password")).sendKeys(pass);
        // driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();

        WebElement Alerta = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedmessage.toLowerCase(), Alerta.getText().toLowerCase().trim());

    }
    @Test
    public void Test_Create_New_Account(){
        //SETUP
        String firstName = "Jorge";
        String lastName = "Zamora";
        String email = "jazp@gmail.com";
        String telephone = "11111";
        String password = "1234";
        String expectedMessage = "Your Account Has Been Created!";
        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetConfirmationMessage(), expectedMessage);
    }

}


