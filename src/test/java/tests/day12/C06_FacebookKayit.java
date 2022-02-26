package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C06_FacebookKayit extends TestBase
{
    //Yeni bir class olusturalim: D15_MouseActions4
    @Test
    public void facebookKayit() throws InterruptedException {

        //1- https://www.facebook.com adresine gidelim
        driver.get("https://www.facebook.com ");

        //2- Yeni hesap olustur butonuna basalim
        // bu buton sag click olmuyor, herhangi bir yerde sag click yap, oteki yoldan bul
        // ayrica buranin id'si surekli degisiyor id'ye gore path alma
        WebElement kayitOlButtonElementi = driver.findElement(By.xpath("//a[@class= '_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"));
        kayitOlButtonElementi.click();

        //3- Ad, soyad, mail ve sifre kutularina deger yazalim ve kaydol tusuna basalim
        WebElement isimKutusuElementi = driver.findElement(By.xpath("(//input[@class='inputtext _58mg _5dba _2ph-'])[1]"));
        WebElement kaydolButtonElementi = driver.findElement(By.name("websubmit"));

        Actions actions = new Actions(driver);
        actions.click(isimKutusuElementi)
                .keyDown(Keys.SHIFT)
                .sendKeys("t")
                .keyUp(Keys.SHIFT)
                .sendKeys("ugba")
                .sendKeys(Keys.TAB)
                .keyDown(Keys.SHIFT)
                .sendKeys("Y")
                .keyUp(Keys.SHIFT)
                .sendKeys("alcin")
                .sendKeys(Keys.TAB)
                .sendKeys("abc@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("123456")
                .click(kaydolButtonElementi).perform();

        //4- Kaydol tusuna basalim
        kaydolButtonElementi.click();

        Thread.sleep(2000);
    }

}
