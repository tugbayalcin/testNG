package tests.QA4Sirius;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.List;

public class Q03_GenelTekrar extends TestBase
{
    WebElement dropdowBox;
    Select select;
    WebElement aramakutusu;
    WebElement SonucYazisi;
    WebElement ilkUrunTitle,ilkurunFiyat,ikinciUrunTitle,ikinciUrunfiyat;
    WebElement sepetekleElement;
    WebElement ilkUrun,ikinciUrun;
    String urunBirTitle,urunBirfiyat,urunIkiTitle,urunIkiFiyat;
    @Test
    public void test01() throws InterruptedException {
        //test01
        //Amazon sayfasına gidelim
        driver.navigate().to("https://www.amazon.com");
        // Ana sayfanın açıldıgını kontrol edelim
        String actualTitle= driver.getTitle();
        String expected="Amazon";
        Assert.assertTrue(actualTitle.contains(expected),"amazon sayfasında degiliz");
        //doprdown menusu ylocate edip yazdıralım,
        dropdowBox=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select=new Select(dropdowBox);
        //otomobil bölümünü secip volvo diye aratıp
        select.selectByVisibleText("Automotive");
        aramakutusu=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        aramakutusu.sendKeys("volvo"+ Keys.ENTER);
        // sonucyazısını locate edip volvo içerip içermediginin kontorolünü yapalım
        SonucYazisi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        String aranaDeger="volvo";
        Assert.assertTrue(SonucYazisi.getText().contains(aranaDeger),"arama volvo içermiyor");
        //sol framede ki özelliklerden aşagıdakileri secelim
        //    Climate Pledge Friendly  kısımlarını checkboxlarını işaretleyelim
        driver.findElement(By.xpath("//li[@id='p_n_cpf_eligible/21512497011']")).click();
  /*  Actions actions=new Actions(driver);
    actions.sendKeys(Keys.PAGE_DOWN);*/
        //    25-50$ arası
        //     driver.findElement(By.xpath("//li[@id='p_36/1253494011']")).click();
        // beşinci ürünü relative locater'larla locate edelim
        ilkUrun=driver.findElement(By.xpath("//img[@data-image-index='1']"));
        ikinciUrun=driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(ilkUrun));
        Thread.sleep(3000);
        //beşinci ürüne tıklayalım
        ikinciUrun.click();
        //gelen ürünün title ve fiyat bilgisini alıp ürünü sepete ekleyelim
        ilkUrunTitle=driver.findElement(By.xpath("//span[@id='productTitle']"));
        ilkurunFiyat= driver.findElement(By.xpath("//span[@id='price_inside_buybox']"));
        urunBirTitle=ilkUrunTitle.getText();
        System.out.println("Seçtigimiz ilk Ürün: " + ilkUrunTitle.getText());
        urunBirfiyat=ilkurunFiyat.getText();
        System.out.println("Sectigimiz ilk Ürün Fiyatı: " + ilkurunFiyat.getText());
        sepetekleElement=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        sepetekleElement.click();
        Thread.sleep(3000);
    }
    @Test
    public void test02() throws InterruptedException {
        //test02
        //yeni bir tap açalım
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.amazon.com");
        String newhandle=driver.getWindowHandle();
        driver.switchTo().window(newhandle);
        //yeniden amazon sayfasına baglanalım
        //dropmenuden elektronik bölümü seçilip iphone 13 aratalım
        dropdowBox=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select=new Select(dropdowBox);
        select.selectByValue("search-alias=electronics-intl-ship");
        aramakutusu=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        aramakutusu.sendKeys("Iphone 13"+ Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,400)");
        //kenardaki frame den aşagıdaki checkbox'lara tıklayalım
        //          apple
        driver.findElement(By.xpath("//span[.='Apple']")).click();
        //        seller amazon.com
        driver.findElement(By.xpath("//span[.='Amazon.com']")).click();
        //ikinci relative locater ile locate edip secelim.
        ilkUrun=driver.findElement(By.xpath("//img[@data-image-index='1']"));
        ikinciUrun=driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(ilkUrun));
        //ikinci ürüne tıkalayarak ürünün title ve fiyat bilgilerini assign edelim
        ikinciUrun.click();
        ikinciUrunTitle=driver.findElement(By.xpath("//span[@id='productTitle']"));
        System.out.println("ikinci Ürün Title: " + ikinciUrunTitle.getText());
        //ürünü sepete ekleyelim
        //mouse hello,sign in kısmına görürüp açılan menuden music Library linkine tıklayalım
        WebElement signInElement=driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(signInElement).perform();
        WebElement signElementi= driver.findElement(By.xpath("//span[text()='Sign in']"));
        actions.click(signElementi).perform();
        WebElement createWebElment=driver.findElement(By.xpath("//a[@class='a-button-text']"));
        actions.click(createWebElment).perform();
        WebElement nameElement=driver.findElement(By.xpath("//input[@type='text']"));
        actions.click(nameElement).
                keyDown(Keys.SHIFT).
                sendKeys("s").
                keyUp(Keys.SHIFT).
                sendKeys("uat").
                sendKeys(Keys.TAB).
                sendKeys("abs.hotmail.com").
                sendKeys(Keys.TAB).
                sendKeys("aa345!-").
                sendKeys(Keys.TAB).
                sendKeys("aa345!-").
                sendKeys(Keys.TAB).
                sendKeys(Keys.ENTER).perform();
        Thread.sleep(5000);
    }
    @Test
    public void test03() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.amazon.com");
        String newhandle = driver.getWindowHandle();
        driver.switchTo().window(newhandle);
        dropdowBox = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select = new Select(dropdowBox);
        aramakutusu = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        select.selectByVisibleText("Home & Kitchen");
        aramakutusu.sendKeys("termos" + Keys.ENTER);
        ilkUrun = driver.findElement(By.xpath("//img[@data-image-index='1']"));
        ilkUrun.click();
        ikinciUrunTitle = driver.findElement(By.xpath("//span[@id='productTitle']"));
        System.out.println("ikinci secilen urun:" + ikinciUrunTitle.getText());
        urunIkiTitle=ikinciUrunTitle.getText();
        ikinciUrunfiyat = driver.findElement(By.xpath("//span[@id='price_inside_buybox']"));
        System.out.println("ikinci ürün fiyatı: " + ikinciUrunfiyat.getText());
        urunIkiFiyat=ikinciUrunfiyat.getText();
        sepetekleElement = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        sepetekleElement.click();
    }
    @Test
    public void test04(){
        // test04
        // sepete tıklayalım
        driver.findElement(By.xpath("//div[@id='nav-cart-count-container']")).click();
        //sepetteki ürünlerle ekledigimiz ürünlerin dogrulugunu test edelim
        List<WebElement> sepetListTitle=driver.findElements(By.xpath("//span[@class='a-truncate-cut']"));
        String sepetIlkUrunTitle=sepetListTitle.get(1).getText();
        String sepetIkinciUrunTitle=sepetListTitle.get(0).getText();
        List<WebElement> sepetListFiyat=driver.findElements(By.xpath("//div[@class='a-column a-span2 a-text-right sc-item-right-col a-span-last']"));
        String sepetIlkUrunFiyat=sepetListFiyat.get(1).getText();
        String sepetIkinciUrunFiyat=sepetListFiyat.get(0).getText();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(urunBirTitle,sepetIlkUrunTitle,"ilk title testi:Failed");
        softAssert.assertEquals(urunIkiTitle,sepetIkinciUrunTitle,"İkinci Urun Title Test:failed");
        softAssert.assertEquals(urunBirfiyat,sepetIlkUrunFiyat," ilk ürün Fiyat Testi:Failed");
        softAssert.assertTrue(urunIkiFiyat.equals(sepetIkinciUrunFiyat),"ikinci ürün fiyat testi:Failed");
        softAssert.assertAll();
        System.out.println("listedeki ürün sayısı: " + sepetListTitle.size());
        System.out.println("sepetimizdeki ürünler");
        for (WebElement w : sepetListTitle) {
            System.out.println(w.getText());
        }
    }


    // Test 02
    // Yeni bir tab acalim
    // Yeniden amazon sayfasina baglanalim
    // dropdown menuden elektronik bolumunu secip iphone 13 aratalim
    // kenardaki filtreden su checkbox'lari secelim: apple, seller amazon.com
    // 5. urunu relative locator ile locate edip secelim
    // urune tiklayarak urunun title(taxt'inden bahsediyor) ve fiyat bilgilerini assign edip urunu sepete ekleyelim
    // mause'u hello,signIn kismina goturup acilan menuden music library linkine tiklayalim

    // Test 03
    // Sepete tiklayalimm
    // Sepetteki urunlerle ekledigimiz urunlerin dogrulugunu test edelim
    // Sepet Sayfasinin en altina gidip help butonuna tiklayalim
}
