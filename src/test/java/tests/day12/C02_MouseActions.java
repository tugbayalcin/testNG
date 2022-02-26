package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class C02_MouseActions extends TestBase
{
    // amazon ana sayfaya gidin
    // sag ustte Hello,Sign In elementinin uzerinde mause'u bekletin ve acilan menuden create a list linkine tiklayin
    // ve new list sayfasinin acildigini test edin

    @Test
    public void amazonList() throws InterruptedException {
        driver.get("https://www.amazon.com");

        Actions actions = new Actions(driver); // Adim 1:
        WebElement helloSignInElementi = driver.findElement(By.id("nav-link-accountList-nav-line-1")); // Adim 2:
        actions.moveToElement(helloSignInElementi).perform(); // Adim 3: --> !!!! perform() koymazsan calismaz

        WebElement listElementi = driver.findElement(By.xpath("//span[text()='Create a List']"));
        actions.click(listElementi).perform(); // bildigimiz eski yoldan da click yapabiliriz
        // action class'ini kullanmak icin bu yontemden yaptik


        // sayfaya gittigimizi title'dan dogrulayalim
        String actualTitle = driver.getTitle();
        String arananMetin = "Your List";


        // NOTE:
        /*
            !!!
            Hard assert'te assert methodlarina class ismini kullanarak erisiyoruz, yani bu methodlar statictir
            Ancak soft assert'te obje olusturmadan erisemiyorum, yani soft assert methodlari static degildir
            !!!
         */
        Assert.assertTrue(actualTitle.contains(arananMetin));









    }
}
