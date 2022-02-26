package tests.day15;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;

public class C02_ScreenshotWebElement extends TestBase
{
    @Test
    public void nutellaTesti() throws IOException, InterruptedException {
        // amazon ana sayfasina gidiniz
        driver.get("https://www.amazon.com");

        // nutella icin arama yapalim
        WebElement aramaButonuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaButonuElementi.sendKeys("Nutella" + Keys.ENTER);

        // sonuclarin nutella icerdigini test edelim
        WebElement sonucyazisiElementi = driver.findElement(By.xpath("//div[@class='sg-col-inner']"));
        String sonucYazisiStr = sonucyazisiElementi.getText();
        Assert.assertTrue(sonucYazisiStr.contains("Nutella"));

        // testin calistiginin ispati icin sonuc yazisi WebElementinin screenshot'ini alalim

        // WebElement  screenshot'i icin 4 adim gerekiyor
        // adim 1: screenshot cekecegimiz WebElementi locate edelim
        // zaten yukarida etmistik
        // adim 2: screenshot'i kaydedecegimiz dosyayi olusturalim
        File webElementSS = new File("target/screenshot/webElement.jpeg");
        // adim 3: webelement ve screenshot methodu ile screenshot alalim
        File geciciResim = sonucyazisiElementi.getScreenshotAs(OutputType.FILE);
        // adim 4: son olarak gecisi resmi kaydetmek istedigimiz asil dosyaya copy yapalim
        FileUtils.copyFile(geciciResim,webElementSS);



        Thread.sleep(3000);

    }
}
