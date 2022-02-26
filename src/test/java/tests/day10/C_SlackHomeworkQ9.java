package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C_SlackHomeworkQ9 extends TestBase
{
    //   1- https://www.facebook.com adresine gidelim
    //    2- Yeni hesap olustur butonuna basalim
    //    3- Ad, soyad, mail ve sifre kutularina deger yazalim ve kaydol tusuna basalim

    @Test
    public void test01() throws InterruptedException {
        // 1) "https://www.facebook.com/" SAYFASINA GiDiN
        driver.get("https://www.facebook.com/");

        // 2) YENi HESAP OLUSTUR BUTONUNA TIKLAYIN
        WebElement yeniHesapOlusturElementi = driver.findElement(By.xpath("//a[@class= '_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"));
        yeniHesapOlusturElementi.click();

        WebElement adElementi = driver.findElement(By.name("firstname"));
        adElementi.sendKeys("polka");

        WebElement soyadElementi = driver.findElement(By.name("lastname"));
        soyadElementi.sendKeys("kizim");

        WebElement mailElementi = driver.findElement(By.name("reg_email__"));
        mailElementi.sendKeys("polka@polka.com");

        WebElement sifreElementi = driver.findElement(By.name("reg_passwd__"));
        sifreElementi.sendKeys("1234");

        Thread.sleep(10000);
    }

}
