package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.swing.*;
import java.util.Set;

public class C03_MouseActions extends TestBase
{
    //1- Yeni bir class olusturalim: MouseActions1

    @Test
    public void test01() throws InterruptedException {
        //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");

        //3- Cizili alan uzerinde sag click yapalim
        Actions actions = new Actions(driver);
        WebElement cizgiliAlanElementi = driver.findElement(By.id("hot-spot"));
        // inspect icin ustune sag click yapinca pop up cikiyor, bunun icin sayfanin herhangi bos bir yerinde sag click yapip
        // secme emojisine tiklayarak locasyonunu buluyoruz
        actions.contextClick(cizgiliAlanElementi).perform();
        // contextclick = sag click

        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edelim.
        String expectedYazi = "You selected a context menu";
        String actualYazi = driver.switchTo().alert().getText();
        Assert.assertEquals(actualYazi,expectedYazi);

        //5- Tamam diyerek alert’I kapatalim
        driver.switchTo().alert().accept();
        // kapattiktan sonra sag click menusu ekranda duruyor, bunu kapatmak icin herhangi bir yere click yaptiralim
        driver.findElement(By.tagName("h3")).click(); // --> kaldirmadi, actions ile deneyelim
        WebElement menuKaldir = driver.findElement(By.tagName("h3"));
        actions.click(menuKaldir).perform();
        Thread.sleep(1000);
        // iki yontemle de olmadi, sikinti yok, site problemli :)1420

        //6- Elemental Selenium linkine tiklayalim
        String ilkSayfaHandleDegeri = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Elemental Selenium']")).click();

        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim
        String ikinciSayfaHandleDegeri ="";
        Set<String> handleSeti = driver.getWindowHandles();
        for(String each : handleSeti){
            if(!each.equals(ilkSayfaHandleDegeri)){
                ikinciSayfaHandleDegeri = each;
            }
        }
        driver.switchTo().window(ikinciSayfaHandleDegeri);
        WebElement ikinciSayfaYazisiElementi = driver.findElement(By.tagName("h1"));
        String actualIkinciSayfaYazisi = ikinciSayfaYazisiElementi.getText();
        String expectedIkinciSayfaYazisi = "Elemental Selenium";

        Assert.assertEquals(actualIkinciSayfaYazisi,expectedIkinciSayfaYazisi);

    }
}
