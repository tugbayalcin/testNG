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

public class C01_Allerts
{
    // Bir class olusturun: Alerts
    // https://the-internet.herokuapp.com/javascript_alerts adresine gidin.

    WebDriver driver;
    WebElement sonucYazisiElementi;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void acceptAlertTesti(){
        // Bir metod olusturun: acceptAlert
        // 1. butona tıklayın, uyarıdaki OK butonuna tıklayın
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        // text'inin js alert olmasi kafani karistirmasin, burasi sitede yer alan basit bir buton
        // ve dolayisiyla inspect edilebiliyor, calistirinca gorebilirsin
        driver.switchTo().alert().accept();

        // ve result mesajının “You successfully clicked an alert” oldugunu test edin.
        sonucYazisiElementi = driver.findElement(By.xpath("//p[@id='result']"));

        String actualSonucYazisi = sonucYazisiElementi.getText();
        String expectedSonucYazisi = "You successfully clicked an alert";

        Assert.assertEquals(actualSonucYazisi,expectedSonucYazisi,"Sonuc Yazisi Testi Failed");
    }

    @Test
    public void DismissAllertTesti(){
        // Bir metod olusturun: dismissAlert
        // 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
        // “successfuly” icermedigini test edin.
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();

        // sonuc yazisi elementi yukarida locate edildi ama burada kullanilamaz
        sonucYazisiElementi = driver.findElement(By.xpath("//p[@id='result']"));

        String actualSonucYazisi = sonucYazisiElementi.getText();
        String istenmeyenKelime = "successfuly";

        Assert.assertFalse(actualSonucYazisi.contains(istenmeyenKelime));

    }
    // NOTE: hoca demisti ki
    // biz switch to ile alert' e gecip bir islem yaptirdigimizda driver orada kalmaz
    // bu sebeple alert uzerinden birden fazla islem yapacak isek her seferinde switch to kullanmak zorundayiz
    // dolayisiyla iframe de oldugu gibi isimiz bittikten sonra eski penceremize switch to yapmamiza gerek yoktur
    // cunku alert'e gecmek kalici gecis saglamaz, tek seferlik gecis saglar

    // switchTo().alert --> tek seferlik, kalisi degil, geri gecis gerektirmez
    // switchTO().iframe --> multi times, kalici, geri gecis gerektirir

    @Test
    public void sendKeysAllert(){
        // Bir metod olusturun: sendKeysAlert
        // 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna tıklayın
        // ve result mesajında isminizin görüntülendiğini doğrulayın.
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        driver.switchTo().alert().sendKeys("Tugba");
        driver.switchTo().alert().accept();

        sonucYazisiElementi = driver.findElement(By.xpath("//p[@id='result']"));

        String actualSonucYazisi = sonucYazisiElementi.getText();
        String arananKelime = "Tugba";

        Assert.assertTrue(actualSonucYazisi.contains(arananKelime));

    }

    @AfterClass
    public void teardown(){
        driver.close();
    }

}
