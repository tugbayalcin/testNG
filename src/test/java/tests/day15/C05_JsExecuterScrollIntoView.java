package tests.day15;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_JsExecuterScrollIntoView extends TestBase
{
    // 1- Yeni bir class olusturun : ScroolInto
    // 2- hotelmycamp anasayfasina gidin
    // 3- 2 farkli test method’u olusturarak actions clasi ve Js Executor kullanarak
    //    asagidaki oda turlerinden ust sira ortadaki odayi tiklayin
    // 4- istediginiz oda inceleme sayfasi acildigini test edin

    @Test
    public void scrollAction() throws InterruptedException {
        driver.get("https://www.hotelmycamp.com/");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.PAGE_DOWN).perform();
        WebElement odaElementi = driver.findElement(By.xpath("(//a[@class='btn-custom'])[2]"));
        odaElementi.click();

        Thread.sleep(3000);
    }

    @Test
    public void scrollJsExecuter() throws InterruptedException {
        driver.get("https://www.hotelmycamp.com/");

        // adim 1:  JsExecuter objesi olusturup driver'i cast edelim
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // adim 2: calisacagimiz webElementi locate edelim
        WebElement odaInceleLinki = driver.findElement(By.xpath("(//a[@class='btn-custom'])[2]"));

        // adim 3: ilgili script ve argument ile jse.executeScript() methodunu calistiralim
        jse.executeScript("arguments[0].scrollIntoView()",odaInceleLinki);
        Thread.sleep(3000);
        jse.executeScript("arguments[0].click();", odaInceleLinki);
        Thread.sleep(3000);

    }
}
