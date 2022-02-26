package tests.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class Q06 extends TestBase
{
    //    2.soru :Ingilizce
    //    go to url :http://demo.automationtesting.in/Alerts.html
    //    click  "Alert with OK" and click 'click the button to display an alert box:'
    //    accept Alert(I am an alert box!) and print alert on console
    //    click "Alert with OK & Cancel" and click 'click the button to display a confirm box'
    //    cancel Alert  (Press a Button !)
    //    click "Alert with Textbox" and click 'click the button to demonstrate the prompt box'
    //    and then sendKeys 'TechProEducation' (Please enter your name)
    //    finally print on console this message "Hello TechproEducation How are you today"

    @Test
    public void test01() throws InterruptedException {
        //    2.soru:Turkce
        //    url'ye gidin :http://demo.automationtesting.in/Alerts.html
        driver.get("http://demo.automationtesting.in/Alerts.html");

        //    "Alert with OK"yi tıklayın ve 'click the button to display a confirm box' i tıklayın:'
        WebElement alertWithOkElementi = driver.findElement(By.xpath("(//a[@class='analystic'])[1]"));
        alertWithOkElementi.click();
        WebElement kirmiziButtonElementi = driver.findElement(By.xpath("//button[@onclick='alertbox()']"));
        kirmiziButtonElementi.click();

        //    Uyarıyı kabul et(I am an alert box!) ve uyarıyı konsolda yazdır
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        //     "Alert with OK & Cancel" ı tıklayın ve 'click the button to display a confirm box' i tıklayın
        WebElement okAndCancelElementi = driver.findElement(By.xpath("//a[text()='Alert with OK & Cancel ']"));
        okAndCancelElementi.click();
        WebElement maviButtonElementi = driver.findElement(By.xpath("//button[@onclick='confirmbox()']"));
        maviButtonElementi.click();

        //    Uyarıyı iptal et (Press a Button !)
        driver.switchTo().alert().dismiss();

        //    "Alert with Textbox" ı tıklayın ve 'click the button to demonstrate the prompt box' tıklayın'
        WebElement alertWithTextBoxElementi = driver.findElement(By.xpath("//a[text()='Alert with Textbox ']"));
        alertWithTextBoxElementi.click();
        WebElement turkuazButtonElementi = driver.findElement(By.xpath("//button[@onclick='promptbox()']"));
        turkuazButtonElementi.click();

        //    ve ardından sendKeys 'TechProEducation' (Lütfen adınızı girin)
        driver.switchTo().alert().sendKeys("TechProEducation");
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        //    sonunda "Hello TechproEducation How are you today" mesajını konsola yazdır (edited)
        WebElement consoleElementi = driver.findElement(By.xpath("//p[@id='demo1']"));
        System.out.println(consoleElementi.getText());

    }

}
