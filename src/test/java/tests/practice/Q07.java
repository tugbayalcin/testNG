package tests.practice;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class Q07 extends TestBase
{
    /*
     // 1. "http://webdriveruniversity.com/Popup-Alerts/index.html" adresine gidin
     // 2. CLICK ME of JavaScript Alert e tıklayın
     // 3. pop up text i alın
     // 4. Mesajın "I am an alert box!"  olduğunu doğrulayın.
     // 5. pop up i kabul edin

     // Yine ayni class da baska test methodu olusturun
     // 1. "http://webdriveruniversity.com/Popup-Alerts/index.html" adresine gidin
     // 2.  CLICK ME of JavaScript Confirm Box i  TIKLAYIN
     // 3.  pop up text i alın
     // 4. Mesajın "Press a button!" olduğunu doğrulayın
     // 5. Açılır pencereyi kapat
     // 6. pop up i iptal edin,
     // 7. "You pressed Cancel!" yazisinin goruntulendigini dogrulayin
     // 8. alert1'e göre dependsOnMethods kullanın
 */

    @Test
    public void alert1 () throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // 1. "http://webdriveruniversity.com/Popup-Alerts/index.html" adresine gidin
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");
        Thread.sleep(2000);
        // 2. CLICK ME of JavaScript Alert e tıklayın
        driver.findElement(By.id("button1")).click();
        Thread.sleep(2000);
        // 3. pop up text i alın
        String message = driver.switchTo().alert().getText();
        // 4. Mesajın "I am an alert box!"  olduğunu doğrulayın.
        String expMessage = "I am an alert box!";
        softAssert.assertTrue(message.equals(expMessage));
        Thread.sleep(2000);
        // 5. pop up i kabul edin
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        softAssert.assertAll();
    }
    @Test (dependsOnMethods = "alert1")
    public void alert2() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // 1. "http://webdriveruniversity.com/Popup-Alerts/index.html" adresine gidin
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");
        Thread.sleep(2000);
        // 2.  CLICK ME of JavaScript Confirm Box i  TIKLAYIN
        driver.findElement(By.xpath("//span[@id='button4']")).click();
        Thread.sleep(2000);
        // 3.  pop up text i alın
        Alert alert = driver.switchTo().alert();  //bu sekilde de kullanimi mevcuttur 2.way
        String message = driver.switchTo().alert().getText();
        // alert.getText(); 2.way
        // 4. Mesajın "Press a button!" olduğunu doğrulayın
        String expMessage = "Press a button!";
        softAssert.assertTrue(message.equals(expMessage));
        Thread.sleep(2000);
        // 5. Açılır pencereyi kapat,  pop up i iptal edin,
        // alert.dismiss();  2.way
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
        // 6. "You pressed Cancel!" yazisinin goruntulendigini dogrulayin
        softAssert.assertTrue(driver.findElement(By.xpath("//p[@id='confirm-alert-text']")).isDisplayed());
        softAssert.assertAll();
        // 7. alert1'e göre dependsOnMethods kullanın
    }
}
/*
Alert alert = driver.switchTo().alert();  //bu sekilde de kullanimi mevcuttur
driver.switchTo().alert();     yerine    Alert data turundeki alert variable ini kullandik.
alert variable ile methodlara ulasilabilir
    alert.dismiss();
    alert.accept();  vb
     alert.getText();
    bu kullanim sayesinde switchto() yazmamiza gerek kalmaz
 */

