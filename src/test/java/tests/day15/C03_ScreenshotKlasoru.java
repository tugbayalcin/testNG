package tests.day15;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.IOException;

public class C03_ScreenshotKlasoru extends TestBase
{
    // amazon'a gidiniz
    // 3 farkli test methodu ile java, nutella ve elma aratip sonuc sayfasinin screenshot'ini kaydedin

    WebElement aramaKutusuElementi;
    @Test
    public void test01() throws IOException {
        driver.get("https://www.amazon.com/");

        aramaKutusuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusuElementi.sendKeys("Java" + Keys.ENTER);
        tumSayfaScreenshot();
    }

    @Test
    public void test02() throws IOException {
        aramaKutusuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusuElementi.clear();
        aramaKutusuElementi.sendKeys("Nutella" + Keys.ENTER);
        tumSayfaScreenshot();
    }

    @Test
    public void test03() throws IOException {
        aramaKutusuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusuElementi.clear();
        aramaKutusuElementi.sendKeys("Elma" + Keys.ENTER);
        tumSayfaScreenshot();
    }
}
