package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C04_FileDownload extends TestBase
{
    // 1. Tests packagenin altina bir class oluşturalim : C04_FileDownload
    //2. Iki tane metod oluşturun : isExist( ) ve downloadTest( )
    //3. downloadTest ( ) metodunun icinde aşağıdaki testi yapalim:
    //        - https://the-internet.herokuapp.com/download adresine gidelim.
    //        - code.txt dosyasını indirelim
    //4. Ardından isExist( )  methodunda dosyanın başarıyla indirilip indirilmediğini test edelim

    @Test
    public void isExistTesti(){
       // 4. Ardından isExist( )  methodunda dosyanın başarıyla indirilip indirilmediğini test edelim
        String dosyaYolu = System.getProperty("user.home") + "/Downloads/download.png";
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));
        // klasor adi turkce olarak İndirilenler yaziyor ama path olarak kopyaladiginda Downloads oldugunu goreceksin
    }

    @Test
    public void downloadTesti() throws InterruptedException {
        //3. downloadTest ( ) metodunun icinde aşağıdaki testi yapalim:
        //        - https://the-internet.herokuapp.com/download adresine gidelim.
        //        - code.txt dosyasını indirelim
        driver.get("https://the-internet.herokuapp.com/download");
        WebElement downloadElementi = driver.findElement(By.xpath("//a[text()='download.png']"));
        downloadElementi.click();
        Thread.sleep(2000);

    }
}
