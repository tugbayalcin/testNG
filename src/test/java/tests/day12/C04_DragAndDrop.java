package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import javax.swing.*;

public class C04_DragAndDrop extends TestBase
{
    //Yeni bir class olusturalim: MouseActions2

    @Test
    public void test01() throws InterruptedException {
        //1- https://demoqa.com/droppable adresine gidelim
        driver.get("https://demoqa.com/droppable");

        //2- “Drag me” butonunu tutup “Drop here” kutusunun ustune birakalim
        Actions actions = new Actions(driver);
        WebElement dragMeElementi = driver.findElement(By.xpath("//div[@id= 'draggable']"));
        WebElement dropHereElementi = driver.findElement(By.xpath("(//div[@id= 'droppable'])[1]"));
        actions.dragAndDrop(dragMeElementi,dropHereElementi).perform();

        Thread.sleep(3000);

        //3- “Drop here” yazisi yerine “Dropped!” oldugunu test edin
        String actualDroppedYazisi = dropHereElementi.getText();
        String expectedDroppedYazisi = "Dropped!";
        Assert.assertEquals(actualDroppedYazisi,expectedDroppedYazisi);

    }
}
