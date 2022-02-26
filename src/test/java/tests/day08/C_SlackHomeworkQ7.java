package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C_SlackHomeworkQ7
{
    // 4 TEST METODU OLUSTURUN,oncelik vererek sirasiyla
    // https://www.n11.com/ SAYFASINA GiDİN.
    // https://www.gittigidiyor.com/ SAYFASINA GiDiN
    // https://getir.com/ SAYFASINA GiDiN
    // https://www.sahibinden.com/ SAYFASINA GiDiN

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test (priority = 1)
    public void Test01(){
        driver.get("https://www.n11.com/");
    }

    @Test (priority = 2)
    public void Test02(){
        driver.get("https://www.gittigidiyor.com/");
    }

    @Test (priority = 3)
    public void Test03(){
        driver.get("https://getir.com/");
    }

    @Test (priority = 4)
    public void Test04(){
        driver.get("https://www.sahibinden.com/");
    }

    @AfterClass
    public void teardown(){
        driver.close();
    }
}
