package tests.day15;

import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;

public class C01_Screenshot extends TestBase
{
    @Test
    public void nutellaTesti() throws InterruptedException, IOException {
        // amazon ana sayfasina gidiniz
        driver.get("https://www.amazon.com");

        // nutella icin arama yapalim
        WebElement aramaButonuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaButonuElementi.sendKeys("Nutella" + Keys.ENTER);

        // sonuclarin nutella icerdigini test edelim
        WebElement sonucyazisiElementi = driver.findElement(By.xpath("//div[@class='sg-col-inner']"));
        String sonucYazisiStr = sonucyazisiElementi.getText();
        Assert.assertTrue(sonucYazisiStr.contains("Nutella"));

        // testin calistiginin ispati icin tum sayfanin screenshot'ini alalim

        // tum sayfa screenshot'i icin 4 adim gerekiyor
        // adim 1: TakeScreenshot objesi olusturma
        TakesScreenshot tss = (TakesScreenshot) driver;
        // adim 2: screenshot'i kaydedecegimiz dosyayi olusturalim
        File tumSayfaSS = new File("target/screenshot/tumSayfa.png"); // --> tareget'in basinda / yok !!!
        // adim 3: bir dosya daha olusturup screenshot objesi ile screenshot'i alalim
        File geciciResim = tss.getScreenshotAs(OutputType.FILE);
        // adim 4: son olarak gecisi resmi kaydetmek istedigimiz asil dosyaya copy yapalim
        FileUtils.copyFile(geciciResim,tumSayfaSS);


        Thread.sleep(3000);

    }
}
