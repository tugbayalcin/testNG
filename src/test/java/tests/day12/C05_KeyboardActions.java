package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.swing.*;

public class C05_KeyboardActions extends TestBase
{
    //1- Bir Class olusturalim KeyboardActions1

    @Test
    public void test01() throws InterruptedException {
        //2- https://www.amazon.com sayfasina gidelim
        driver.get("https://www.amazon.com");

        //3- Arama kutusuna actions method’larine kullanarak S  amsung A71 yazdirin ve Enter’a basarak arama yaptirin
        WebElement aramaKutusu = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));

       // aramaKutusu.sendKeys("Samsung A71"); bildigimiz yol bu ama biz actions class'i ile insan gibi yapicaz
        Actions actions = new Actions(driver);
        actions.click(aramaKutusu)
                .keyDown(Keys.SHIFT) // tusa basti, parmagini tusun ustune koydu ama daha cekmedi, ben soyleyene kadar cekmeyecek
                .sendKeys("s") // tusun ustune basti ve birakti, parmagini koyup kaldirdi
                .keyUp(Keys.SHIFT) // tusa parmagi zaten basili idi, o parmagini tusun uzerinden kaldirdi
                .sendKeys("amsung ")
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .sendKeys("71")
                .sendKeys(Keys.ENTER).perform(); // insan davranislarini taklit etme
        // lambda gibi arka arkaya yazabiliyoruz

        //4- aramanin gerceklestigini test edin
        WebElement ilkUrunElementi = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
        // arama gerceklesirse bu urun gorunur olacak
        Assert.assertTrue(ilkUrunElementi.isDisplayed());


    }
}
