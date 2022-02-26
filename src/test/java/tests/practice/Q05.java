package tests.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.time.Duration;

public class Q05 extends TestBase
{
    // 1. soru :ingilizce
    //go to web site : https://www.jqueryscript.net/demo/bootstrap-alert-box/
    //maximize the window
    //click on action dialog button
    //if need use explicitly wait
    //click on the ok button
    //accept the iframe message

    @Test
    public void test01() throws InterruptedException {
        //1.soru :Turkce
        //web sitesine gidin: https://www.jqueryscript.net/demo/bootstrap-alert-box/
        driver.get("https://www.jqueryscript.net/demo/bootstrap-alert-box/");

        // pencereyi büyüt
        driver.manage().window().maximize();

        // eylem diyalog düğmesine tıklayın
        WebElement actionDialogButtonElementi = driver.findElement(By.xpath("//button[@id='action']"));
        actionDialogButtonElementi.click();
        Thread.sleep(3000);

        // kullanmanız gerekiyorsa açıkça bekleyin
        // gerekmiyor ama bekleyelim
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dialog-mycodemyway-action")));

        // tamam butonuna tıklayın
        WebElement textMessageOkElementi = driver.findElement(By.id("dialog-mycodemyway-action"));
        textMessageOkElementi.click();
        Thread.sleep(3000);

        // iframe mesajını kabul et
        driver.switchTo().alert().accept();

        Thread.sleep(10000);
    }

}
