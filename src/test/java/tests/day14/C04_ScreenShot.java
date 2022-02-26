package tests.day14;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.TestNGAntTask;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.File;

public class C04_ScreenShot extends TestBase
{
    @Test
    public void screenTest(){
        driver.get("https://www.google.com");
        // butun sayfanin screenshot'ini almak icin:

        // 1. adim: screenshot almak icin obje olusturalim ve deger olarak driver'imizi atayalim, deger olarak driver'i
        // kaul etmesi icin type casting yapmamiz gerekir
        TakesScreenshot tss = (TakesScreenshot) driver; // screenshot objesi olusturamiyoruz cunku bu class bir interface

        // 2. adim: butun sayfanin screenshot'ini almak icin bir file olusturalim ve dosya yolunu belirtelim
        File tumSayfaSS = new File("src/tumSayfa.png");

        // 3. adim:  olusturdugumuz file ile takeSS objesini iliskilendirelim
        tumSayfaSS = tss.getScreenshotAs(OutputType.FILE); // output type'ini secmemiz gerekiyor

        // eger spesific bir web elementin screenshot'ini almak istiyorsak:
        // adim 1: file olustur
        WebElement logoElementi = driver.findElement(By.xpath("(//img[@alt='Google']([1]"));
        File logoResmi = new File("src/logo.png");
        logoResmi = logoElementi.getScreenshotAs(OutputType.FILE);

    }
}
