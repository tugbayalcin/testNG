package tests.QA4Sirius;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import javax.swing.*;
import java.util.List;

public class GenelTekrar extends TestBase
{
    WebElement aramaButonuElementi;
    WebElement dropdownElementi;
    Select select;
    WebElement birinciUrunElementi;
    String birinciUrunFiyati;
    WebElement addToCartElementi;
    WebElement eklemeBasariliElementi;
    WebElement sepetElementi;
    SoftAssert softAssert;



    @Test
    public void test01() throws InterruptedException {

        //test01
        //Amazon sayfasına gidelim
        driver.get("https://www.amazon.com");

        // Ana sayfanın açıldıgını kontrol edelim
        String actualTitle = driver.getTitle();
        String expectedTitle = "Amazon";
        boolean kosul = actualTitle.contains(expectedTitle);
        Assert.assertTrue(kosul,"Title " + expectedTitle + " kelimesini icermemektedir");

        //doprdown menusu locate edip yazdıralım
        dropdownElementi = driver.findElement(By.xpath("//select[@id='searchDropdownBox']")); // adim 1
        select = new Select(dropdownElementi);
        List<WebElement> dropdownListi = select.getOptions();
        dropdownListi.stream().forEach(t -> System.out.println(t.getText()));

        //books bölümünü secip korluk diye aratıp
        select.selectByVisibleText("Books");
        aramaButonuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaButonuElementi.sendKeys("korluk" + Keys.ENTER);
        // aramaButonuElementi.submit(); --> enter'a bastirmanin ikinci yolu

        // sonucyazısını locate edip korluk içerip içermediginin kontorolünü yapalım
        String sonucYazisiStringi = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]")).getText();
        String arananKelime = "korluk";
        Assert.assertTrue(sonucYazisiStringi.contains(arananKelime),"Sonuc Yazisi " + arananKelime + "kelimesini icermmemektedir");

        //sol framede ki özelliklerden aşagıdakileri secelim
        // ust bardan textbox secenegini seciniz ve acildigini teyit ediniz
        driver.findElement(By.xpath("(//span[@class='nav-a-content'])[6]")).click();
        String actualUrl = driver.getCurrentUrl();
        String arananKelime1 = "Textbooks";
        Assert.assertTrue(actualUrl.contains(arananKelime1),"Textbooks menusu acilamadi");

        // biyology ürünü relative locater'larla locate edelim
        // --> ekran acildiginda locate etmek istedigim kisimlar gorunmedigi icin sayfayi action yardimi ile once asagiya kaydirmaliyiz
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement ustSatirElementi = driver.findElement(By.xpath("(//h2[@class='a-spacing-mini acswidget-carousel__header'])[1]"));
        WebElement altSatirElementi = driver.findElement(By.xpath("(//h2[@class='a-spacing-mini acswidget-carousel__header'])[2]"));

        //????
        //WebElement biologyKitabiElementi = driver.findElement(RelativeLocator.with(By.xpath("//img[@alt='Campbell Biology (Campbell Biology Series)']")).below(ustSatirElementi).above(altSatirElementi));
        WebElement biologyKitabiElementi = driver.findElement(By.id("acs-product-block-4"));
        biologyKitabiElementi.click();


        birinciUrunElementi = driver.findElement(By.xpath("//span[text()=' Campbell Biology (Campbell Biology Series) ']"));
        String birinciUrunYazisi = birinciUrunElementi.getText();

        String arananKelime3 = "Campbell Biology";
        Assert.assertTrue(birinciUrunElementi.getText().contains(arananKelime3),"Birinci Ururn Goruntulenemedi");

        // birinci urunun fiyatini da alalim, ileride lazim olur
        birinciUrunFiyati = driver.findElement(By.id("rentPrice")).getText();

        // kitabi sepete ekleyelim
        addToCartElementi = driver.findElement(By.xpath("//a[@id='rentButton']"));
        addToCartElementi.click();

        eklemeBasariliElementi = driver.findElement(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']"));
        boolean eklendiMi = eklemeBasariliElementi.isDisplayed();
        Assert.assertTrue(eklendiMi,"Urun Sepete Eklenemedi");

        sepetElementi = driver.findElement(By.id("nav-cart-count-container"));
        //

        // dinamiclestirip method haline getir
        if(eklendiMi){
            sepetElementi.click();
            Thread.sleep(2000);
            String sepettekiUrunAdi = driver.findElement(By.xpath("//span[text()='Campbell Biology (Campbell Biology Series)']")).getText();
            boolean gorunuyorMu = birinciUrunYazisi.contains(sepettekiUrunAdi);
            softAssert = new SoftAssert();
            softAssert.assertTrue(gorunuyorMu,"Ekleme Islemi Hatalidir. Lutfen Tekrar Deneyiniz!");
            if (gorunuyorMu){
                String sepettekiUrunFiyati = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText();
                softAssert.assertEquals(sepettekiUrunFiyati,birinciUrunFiyati,"Urun Fiyatlari Eslesmemektedir, Lutfen Teknik Servisi Arayiniz.");
            }
            else {
                System.out.println("Aradiginiz Urun Sepette Bulunamamistir");
            }

        }
        else
        {
            System.out.println("Urununuz Sepete Eklenemedi. Lutfen Ekleme Islemini Kontrol Ediniz.");
        }

        System.out.println("methodum calisacak mi");
        Assert.assertTrue(sepetKontrol(birinciUrunYazisi),"Aradiginiz Urun Sepette Bulunmamaktadir");
        System.out.println("methodum nasil calisiyor");
        //Assert.assertTrue(sepetFiyatKontrol(birinciUrunYazisi,birinciUrunFiyati),"Ururn Fiyati Eslesmemektedir");

        sepetFiyatKontrol(birinciUrunYazisi,birinciUrunFiyati);




        softAssert.assertAll();

    }



    //test02
    //yeni bir tap açalım
    //yeniden amazon sayfasına baglanalım
    //dropmenuden elektronik bölümü seçilip iphone 13 aratalım
    //kenardaki frame den aşagıdaki checkbox'lara tıklayalım
    //          apple
    //        seller amazon.com
    //beşinci relative locater ile locate edip secelim.
    //beşinci ürüne tıkalayarak ürünün title ve fiyat bilgilerini assign edelim
    //ürünü sepete ekleyelim
    //mouse hello,sign in kısmına görürüp açılan menuden music Library linkine tıklayalım
    // test03
    // sepete tıklayalım
    //sepetteki ürünlerle ekledigimiz ürünlerin dogrulugunu test edelim
    //sepet sayfasının en altına gidip help linkine tıklayın

    public boolean sepetKontrol(String urunAdi)
    {
        // parametre olarak string urun adi ve urun fiyati alalim

        boolean iceriyorMu = false;
        List<WebElement> alisverisSepeti = driver.findElements(By.className("a-truncate-cut"));
        for (WebElement each : alisverisSepeti
        ) {
            if(each.getText().equals(urunAdi))
                iceriyorMu = true;

        }
        return iceriyorMu;

    }

    public void sepetFiyatKontrol(String urunAdi,String fiyat){
        boolean esitMi = false;
        List<WebElement> bırımUrurnElementleriListesi = driver.findElements(By.xpath("//div[@class='sc-list-item-content']"));
        System.out.println("**********");
        System.out.println("Birim satır list: " + bırımUrurnElementleriListesi);
        System.out.println("**********");
        for (WebElement each : bırımUrurnElementleriListesi
        ) {
            if(each.getAttribute("class") == "a-truncate-cut")
            {
                List<String> urunIsımleriElementleriListesi = null;
                urunIsımleriElementleriListesi.add(each.getText());
                System.out.println("**********");
                System.out.println("urun isimleri: " + urunIsımleriElementleriListesi);
                System.out.println("**********");
                for (String isim : urunIsımleriElementleriListesi) {
                    if((isim.equals(urunAdi)) && (each.getAttribute("class") == "a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold"))
                    {
                        String sepetFiyat = each.getText();
                        sepetFiyat = sepetFiyat.substring(1);
                        esitMi= fiyat.equals(sepetFiyat) ? true : false;
                        System.out.println("sepet fiyat: " + sepetFiyat);
                        System.out.println("urun fiyat: " + fiyat);
                    }
                }
            }

        }
        //return esitMi;


    }



}



