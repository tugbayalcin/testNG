package tests.day10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_BasicAuthotentication
{
    //1- Bir class olusturun : BasicAuthentication

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void authotenticationTesti(){
        //2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin
        //driver.get("https://the-internet.herokuapp.com/basic_auth");
        // bununla gidersek authotentication ile karsilasiyoruz ve manuel giris yapmak zorunda kaliyoruz

        //3- asagidaki yontem ve test datalarini kullanarak authentication’i yapin
        //    Html komutu : https://username:password@URL --> nasil sifreleme yapacagimin tarifi
        //    Username    : admin
        //    password    : admin
        // basic authotentication isteyen web service'leri bize nasil ve hangi bilgilerle authotentication yapabilecegimiz
        // bilgisini de vermek zorundadir.
        // biz de bize tarif edilen yontem ve bize verilen bilgileri birebir uygulayarak istedigimiz web service'e giris saglayabiliriz
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth"); // tarifteki gibi yaptik, iki kere https:// yazma !!!


        //4- Basarili sekilde sayfaya girildigini dogrulayin
        WebElement girisYazisiElementi = driver.findElement(By.tagName("p"));

        Assert.assertTrue(girisYazisiElementi.isDisplayed());
    }

    @AfterClass
    public void teardown(){
        driver.close();
    }

}
