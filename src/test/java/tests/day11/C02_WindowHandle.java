package tests.day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Set;

public class C02_WindowHandle
{
    // Tests package’inda yeni bir class olusturun: WindowHandle2

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    // VERIFY DENDIGINDE SOFT ASSERTION AKLA GELIR
    // SOFT ASSERT'TE MESAJ KIYMETLIDIR

    @Test
    public void windowHandleTest(){

        // https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        // Sayfadaki textin “Opening a new window” olduğunu doğrulayın.(verify)
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        String expectedText = "Opening a new window";
        SoftAssert softAssert = new SoftAssert();
        // hemen en sona gidip assertAll yap
        softAssert.assertEquals(actualText,expectedText,"Text Dogrulamasi Basarisiz.");

        // Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String actualTitle = driver.getTitle();
        String expectedTitle = "The Internet";
        softAssert.assertEquals(actualTitle,expectedTitle,"Sayfa Title'i Beklenenden Farkli");

        // Click Here butonuna basın.
        // soruda window handle degerini bilmedigim bir window aciliyor
        // o sayfanin window handle degerini bulmak icin gectigim sayfalardaki window handle degerlerini kaydetmeliyim
        String windowHandleDegeri1 = driver.getWindowHandle();
        driver.findElement(By.xpath("(//a[@target='_blank'])[1]")).click();

        // Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.

        // 2. sayfa acildi ama driver 1. sayfada kaldi buraya gecmedi, gecebilmesi icin bu sayfanin window
        // handle degerine ihtiyac avr ve biz bunu bilmiyoruz
        // once acilan yeni sayfanin handle degerini elde etmeliyiz
        // once tum handle degerlerini alip bir set'e koyalim
        Set<String> handleDegerleriSeti = driver.getWindowHandles(); // bu driver'in actigi tum windowlarin handle degerlerini alacak
        String windowHandleDegeri2 = "";
        for(String each : handleDegerleriSeti){
            if(!each.equals(windowHandleDegeri1)){
                windowHandleDegeri2 = each;
            }
        }
        // artik yeni sayfaya gecis yapabilirim
        driver.switchTo().window(windowHandleDegeri2);
        String actualTitle1 = driver.getTitle();
        String expectedTitle1 = "New Window";
        softAssert.assertEquals(actualTitle1,expectedTitle1,"New Window Title Dogrulama Testi Failed");

        // Sayfadaki textin “New Window” olduğunu doğrulayın.
        String ikinciSayfadakiYazi = driver.findElement(By.tagName("h3")).getText();
        String ikinciSayfadakiBeklenenYazi = "New Window";
        softAssert.assertEquals(ikinciSayfadakiYazi,ikinciSayfadakiBeklenenYazi,"New Window Text Dogrulama Testi Failed");

        // Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(windowHandleDegeri1);
        softAssert.assertEquals(driver.getTitle(),"The Internet","Old Window Title Dogrulama Testi Failed");

        softAssert.assertAll();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
