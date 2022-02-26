package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C06_Synchronization extends TestBase
{
    @Test
    public void test01() throws InterruptedException {
        /*
        driver.get("https://the-internet.herokuapp.com/upload");
        // TestBase'deki setup methodundaki implicitly wait i yoruma aldik deniyoruz
        //Thread.sleep(1000); // daha dusuk saniye yazdigimda calismiyor,
        // aslinda burasi basit bir site oldugu icin thread.sleep koymadan da hemen calisti
        // cunku sayfa daha acilamadi ki buttonu bulup click yapsin
        driver.findElement(By.id("file-submit")).click();

         */

        // youtube'a bakalim
        /*
        driver.get("https://www.youtube.com/");
        driver.findElement(By.xpath("//yt-formatted-string[@title='Müzik']")).click();
        Thread.sleep(2000);

         */

        // techProEd'e bakalim
        /*
        driver.get("https://www.techproeducation.com/");
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']")).click();
        Thread.sleep(2000);
        // valla hic beklemeden basti

         */

        // burada denedigimiz sey, biz bir sayfayi actigimizda title yaninda donen sey var ya
        // o aslinda sitede kullanilan yogun aktif icerikli gorsel video yardim chat'i gibi
        // yapilarin html ile gez yuklenmesinden kaynaklaniyor
        // ve onlar yuklenmeden, yani tum html kodlari yuklenmeden benim yaptigim locate sonrasi click islemi calismaz
        // bu yuzden implicitly wait kullaniriz

        // https://the-internet.herokuapp.com/dynamic_controls
        // sayfasina gidin
        // disable butonuna basin
        // disabled yazisinin ciktigini test edin

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

        WebElement enabledYaziElementi=driver.findElement(By.xpath("//p[.=\"It's enabled!\"]"));
        // ta daaa bu ayziyi goremedi, bekledigimiz de buydu zaten
        // simdi buraya bekleme koyalim ve yeniden calistiralim
        Thread.sleep(3000); // hard wait --> implicitly wait de dynamic waittir
        Assert.assertTrue(enabledYaziElementi.isDisplayed());

        // bir de TestBase'deki implicitly wait i acarak deneyelim


    }
}
