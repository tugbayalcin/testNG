package tests.day15;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C04_JsExecuterClick extends TestBase
{
    // amazon anasayfasina gidip sell linkine js executer ile tiklayalim ve ilgili sayfaya gittigimizi test edelim
    @Test
    public void test01(){
        driver.get("https://www.amazon.com/");
        // adim 1: JsExecuter objesi olusturalim ve driver'i cast edelim
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // adim 2: ilgili webElementi locate edelim
        WebElement sellElementi = driver.findElement(By.xpath("//a[normalize-space()='Sell']"));

        // adim 3: ilgili script ve argument(bizim web elementimiz) ile objemiz uzerinden executeScript methodunu calistiralim
        jse.executeScript("arguments[0].click()",sellElementi);
    }
}
