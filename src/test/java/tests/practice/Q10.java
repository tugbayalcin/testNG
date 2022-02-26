package tests.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.TestBase;

public class Q10 extends TestBase
{
    // http://amazon.com adresine gidiniz
    // araba, ev, anahtarlik, ayakkabi, gomlek  kelimelerini arayiniz

    @Test(dataProvider = "urunler")
    public void amazonTest(String kelime){
        driver.get("http://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(kelime + Keys.ENTER);

    }
    @DataProvider(name="urunler")
    public Object[][] getUrunler()
    {
        Object[][] urunler = {{"araba"},{"ev"},{"anahtarlik"},{"ayakkabı"}, {"gomlek"}};
        return urunler;
    }

    // https://www.gittigidiyor.com/ adresine gidiniz
    // java, javascript ve python kelimelerini arayiniz

    @Test(dataProvider = "aranacakKelimeler")
    public void gittigidiyorTest(String aranacakKelime)
    {
        driver.get("https://www.gittigidiyor.com/");
        driver.findElement(By.xpath("//input[@name ='k']")).sendKeys(aranacakKelime + Keys.ENTER);

    }
    @DataProvider
    public static Object[][] aranacakKelimeler()
    {
        String[][] data = {{"java"},{"javascript"},{"python"}};
        return data;
    }

    // data provider'a isim atamasi yaparsak,
    // o provider'dan data alacak test methodunun yanina bu ismi yazarak veriyi nereden alacagini belirtebiliriz
    // data provider'a isim atamasi yapmazsak,
    // o provider'dan data alacak test methodunun yanina provider icerisindeki method adini yazarak
    // veriyi nereden alacagini belirtebiliriz ancak bu durumda bu method isminden tuuuuum class icerisinde yalnizca
    // bir tane olmasi gerekir ki karisiklik olmasin

}
