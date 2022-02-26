package tests.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q08 extends TestBase
{
    /*
// https://www.jqueryscript.net/demo/Easy-iFrame-based-Twitter-Emoji-Picker-Plugin-jQuery-Emoojis/ sitesine gidin
// web sitesini maximize yapin
// ikinci emojiye tıklayın
// tüm ikinci emoji öğelerini tıklayın
// parent iframe e geri donun
// formu doldurun,(Formu istediğiniz metinlerle doldurun)
//  apply button a basin
 */

    @Test
    public void test01() throws InterruptedException {
        driver.get("https://www.jqueryscript.net/demo/Easy-iFrame-based-Twitter-Emoji-Picker-Plugin-jQuery-Emoojis/");

        WebElement iframe=driver.findElement(By.xpath("//iframe[@id='emoojis']"));
        driver.switchTo().frame(iframe);

       // driver.switchTo().frame("emoojis"); --> yukaridaki 2 satir yerine, bu sekilde tek satirda da iframe'e gecis yapilabilir

        WebElement secondIconElementi = driver.findElement(By.xpath("(//span[@class='mdl-tabs__ripple-container mdl-js-ripple-effect'])[2]"));
        secondIconElementi.click();
        // tum emojileri once bir listeye atalim
        List<WebElement> emojilerListesi = driver.findElements(By.xpath("//div[@id='nature']/div/img"));
        // tum emojileri iceren div'e kadar cikip onu locate ettik, icindekileri listeye atacagiz ve teker teker tiklayacagiz

        for (WebElement each : emojilerListesi)
        {
            each.click();
        }
        //  emojiOgeler.stream().forEach(x-> x.click());  bu sekilde de lambda ile yapabilirdik
        System.out.println("Tum ogeler tiklandi");
        Thread.sleep(2000);

        // parent iframe'e geri donunuz
        driver.switchTo().defaultContent();


        // formu doldurun,(Formu istediğiniz metinlerle doldurun)
        List<WebElement> metinGirisListesi = driver.findElements(By.xpath("//input[@class='mdl-textfield__input']"));
        // bizden istedigi kutucukalrin hepsi ayni class icerisinde oldugu icin class value'su kulanbarak onlari list'e koyduk
        List<String> metinler = new ArrayList<>(Arrays.asList("Bir", "iframe", "sorusu", "bu","kadar","eglenceli",
                                                                "olabilir","sizce de","oyle degil mi?","",""));

        for(int i=0; i<metinGirisListesi.size(); i++) {
            metinGirisListesi.get(i).sendKeys(metinler.get(i));
        }

        //  apply button a basin
        driver.findElement(By.xpath("//input[@class='mdl-textfield__input']")).click();

        Thread.sleep(3000);




    }
}
