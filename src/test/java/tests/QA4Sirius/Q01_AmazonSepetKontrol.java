package tests.QA4Sirius;

import jdk.jfr.Name;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.List;

public class Q01_AmazonSepetKontrol extends TestBase
{
    WebElement dropMenuBox;
    Select select;
    WebElement aramaBox;
    WebElement aramaSonucYazisi;
    WebElement ilkUrun,ikinciUrun,ucuncuUrun;
    WebElement ilkUrunFiyat,ikinciUrunFiyat;
    WebElement title1,title2;
    String urunBir,urunIki,urunBirFiyat,urunIkiFiyat;
    String handle1,handle2;
    WebElement kartEkle;


    @Test(enabled=false)//bu test calismaz-yesil ok bile kalkti - JUnit-> @Ignore
    public void test01(){
        // amazon git
        driver.navigate().to("https://www.amazon.com");
        //dropmenuyu handle edip listesini ekrana yazdırın
        dropMenuBox= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select=new Select(dropMenuBox);
        List<WebElement> dropList=select.getOptions();
        for (WebElement t : dropList) {
            System.out.println(t.getText());
        }
//dropmenude 40 eleman oldugunu dogrulayın
        int actualdropmenuSize=dropList.size();
        int expectedSize=40;//actual:28
        Assert.assertTrue(actualdropmenuSize==expectedSize,"expectedSize:"+expectedSize+" - actualSize:"+actualdropmenuSize);

    }
    @Test(testName = "Amazon Iphone13 Testi - arama sonuclari validation'i, sepete ekleme islemleri")
    public void test02(){
        //  test02
// elektronik bölümü secelim
        select.selectByVisibleText("Electronics");
//iphone aratıp bulunan sonuç sayısını yazdırıp sonuc satırında iphone yazısı oldugunu dogrulayalım
        aramaBox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        aramaBox.sendKeys("iphone 13"+ Keys.ENTER);
        aramaSonucYazisi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        handle1=driver.getWindowHandle();
        System.out.println("Sonuc texti: " + aramaSonucYazisi.getText());
        boolean result=aramaSonucYazisi.getText().contains("iphone 13");
        Assert.assertTrue(result,"arama sonuclarına iphone ibaresi yok");
        //ikinci ürüne relativelocater kullanarak tıklayalım
        ilkUrun=driver.findElement(By.xpath("//img[@data-image-index='1']"));
        ucuncuUrun=driver.findElement(By.xpath("//img[@data-image-index='3']"));
        ikinciUrun=driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(ilkUrun).toLeftOf(ucuncuUrun));
        ikinciUrun.click();
//ürünün title'ni ve fiyatını assign edip ürünü sepete ekleyelim
        title1=driver.findElement(By.xpath("//span[@id='productTitle']"));
        ilkUrunFiyat=driver.findElement(By.xpath("//span[@id='price_inside_buybox']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
        urunBir=title1.getText();
        System.out.println("ilk ürün title: " + title1.getText());
        urunBirFiyat=ilkUrunFiyat.getText();
        System.out.println("ilk ürün fiyat: " + ilkUrunFiyat.getText());
        kartEkle=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        kartEkle.click();
    }
    @Test(testName ="Bebek urunleri arama Testi" )
    public void test03() throws InterruptedException {
        //test03
        //yeni bir sekme acarak gene amazona gidelim
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.amazon.com");
 /*   Set<String> handleList=driver.getWindowHandles();
    for (String each:handleList) {
        if (!each.equals(handle1)){
            handle2=each;
        }
    }*/
        handle2=driver.getWindowHandle();
        driver.switchTo().window(handle2);
        //baby bölümüne gidelim
        dropMenuBox= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select=new Select(dropMenuBox);
        aramaBox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        select.selectByVisibleText("Baby");
        //bebek puset aratıp bulundan sonuç sayısını yazdırın
        aramaBox.sendKeys("puset"+Keys.ENTER);
        //sonuç yazsının puset içerdigini teyit edelim
        aramaSonucYazisi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        System.out.println("Baby"+aramaSonucYazisi.getText());
        boolean sonuc= aramaSonucYazisi.getText().contains("puset");
        Assert.assertTrue(sonuc,"sonuc yazısında puset yok");
        //üçüncü ürüne relative locater kullanarak tıklayalım
        ilkUrun=driver.findElement(By.xpath("//img[@data-image-index='1']"));
        ucuncuUrun=driver.findElement(By.xpath("//img[@data-image-index='3']"));
        ikinciUrun=driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(ilkUrun).toLeftOf(ucuncuUrun));
        ikinciUrun.click();
        //title ve fiyat bilgilerini assign edelim
        title2=driver.findElement(By.xpath("//span[@id='productTitle']"));
        ikinciUrunFiyat=driver.findElement(By.xpath("//span[@id='price_inside_buybox']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
        urunIki=title2.getText();
        System.out.println("ikinci ürün title: " + title2.getText());
        urunIkiFiyat=ikinciUrunFiyat.getText();
        System.out.println("ikinci ürün fiyat: " + ikinciUrunFiyat.getText());
        //ürünü sepete ekleyelim
        kartEkle=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        kartEkle.click();
        if (driver.findElement(By.xpath("//div[@id='ewc-content']")).isDisplayed()){
            driver.findElement(By.xpath("//a[@id='attach-close_sideSheet-link']")).click();
        }
        Thread.sleep(5000);
    }
    @Test
    public void test04(){
        WebElement sepet=driver.findElement(By.xpath("//div[@id='nav-cart-count-container']"));
        sepet.click();
        List<WebElement> sepetList=driver.findElements(By.xpath("//span[@class='a-truncate-cut']"));
        String sepetilkUrun=sepetList.get(1).getText();
        String sepetikinciUrun=sepetList.get(0).getText();
        Assert.assertEquals(sepetilkUrun,urunBir,"\"ilk ürün title eşlemiyor\"");
        Assert.assertTrue(sepetikinciUrun.equals(urunIki),"ikinci ürün title eşlemiyor");
        List<WebElement> sepetListFiyat=driver.findElements(By.xpath("//p[@class='a-spacing-mini']"));
        String sepetilkUrunFiyat=sepetListFiyat.get(1).getText();
        String sepetikinciUrunFiyat=sepetListFiyat.get(0).getText();
        Assert.assertTrue(sepetilkUrunFiyat.equals(urunBirFiyat),"ilk ürün title eşlemiyor");
        Assert.assertTrue(sepetikinciUrunFiyat.equals(urunIkiFiyat),"ikinci ürün title eşlemiyor");
    }

}
