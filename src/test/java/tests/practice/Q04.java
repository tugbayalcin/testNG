package tests.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q04
{
    /*
     * Navigate to  https://www.saucedemo.com/
     * Enter the user name  as standard_user
     * Enter the password as   secret_sauce
     * Click on login button
     *     T1 : Choose price low to high
     *     T2 : Verify item prices are sorted from low to high with soft Assert
     */

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test1(){
        driver.get("https://www.saucedemo.com/");
        WebElement usernameElementi = driver.findElement(By.id("user-name"));
        usernameElementi.sendKeys("standard_user");

        WebElement passwordElementi = driver.findElement(By.id("password")); // //input[@id='password']
        passwordElementi.sendKeys("secret_sauce");

        WebElement loginButtonElementi = driver.findElement(By.id("login-button"));
        loginButtonElementi.click();

        WebElement dropdownMenuElementi = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdownMenuElementi);
        select.selectByVisibleText("Price (low to high)");

        String expected = "PRICE (LOW TO HIGH)";
        String actual= driver.findElement(By.className("active_option")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,expected,"Fiyatlar Istenilen Sekilde Siralanmamistir");

        softAssert.assertAll();

    }

    @Test
    public void test2(){
        driver.get("https://www.saucedemo.com/");
        WebElement usernameElementi = driver.findElement(By.id("user-name"));
        usernameElementi.sendKeys("standard_user");

        WebElement passwordElementi = driver.findElement(By.id("password")); // //input[@id='password']
        passwordElementi.sendKeys("secret_sauce");

        WebElement loginButtonElementi = driver.findElement(By.id("login-button"));
        loginButtonElementi.click();

        WebElement dropdownMenuElementi = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdownMenuElementi);
        select.selectByIndex(2); // index 0'dan baslar

        List<WebElement> fiyatlar = driver.findElements(By.className("inventory_item_price"));
        ArrayList<Double> fiyatlarDouble = new ArrayList<>();

        for (WebElement fiyat : fiyatlar)
        {
            //String fiyatStr = fiyat.getText().replaceAll("$","");
            String fiyatStr = fiyat.getText().replaceAll("^\\D",""); // digit olamayanlari sil demek
            // ^ isareti  oncesini kasteder, digit kisimdan oncesini degitir demek
            fiyatlarDouble.add(Double.parseDouble(fiyatStr));
        }

        ArrayList<Double> kontrolList = new ArrayList<>(fiyatlarDouble);
        Collections.sort(kontrolList);

        Assert.assertEquals(kontrolList,fiyatlarDouble);



    }

    @AfterClass
    public void teardown(){

        //driver.quit();
    }
}
