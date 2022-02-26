package tests.day10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C03_IFrame
{

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void iFrameTest(){
      // https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");

      // Bir metod olusturun: iframeTest
      // “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda    yazdirin.
        WebElement girisYazisiElementi = driver.findElement(By.xpath("//h3[text()='An iFrame containing the TinyMCE WYSIWYG Editor']"));

        SoftAssert softAssert = new SoftAssert(); // hemen method sonuna gidip softAssertAll de, unutma
        softAssert.assertTrue(girisYazisiElementi.isEnabled(),"IFrame Yazisi Gorunmuyor");

        System.out.println(girisYazisiElementi.getText());

        // Text Box’a “Merhaba Dunya!” yazin.
        // WebElement textBoxElementi = driver.findElement(By.xpath("//p")); // bu sekilde bulamadi
        // yeniden incele deyip html kodlarinda biraz yukarisina bakarsak bu kismin iFrame oldugunu goruruz
        // bu yuzden bizim driver'imiz onu goremez,

        // yazi yazmak istedigimiz text box iframe'in icinde oldugundan direct ulasamiyoruz
        // once iFrame'i locate edip onun icine switch yapmaliyiz

        WebElement iFrame = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iFrame);

        WebElement textBoxElementi = driver.findElement(By.xpath("//p"));

        textBoxElementi.clear();
        textBoxElementi.sendKeys("Merhaba Dunya!");

        // TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu     dogrulayin ve  konsolda yazdirin.
        // yukarida iframe icine switch yaptigimizdan, yeniden disari cikmak istedigimizde ana sayfaya donmeliyiz

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[text()='Elemental Selenium']")).click();
        // burayi switch yapmadan click yapamadik cunku iframe icine girmistik ama cikmadik, bu bolge ise iframe disinda

        // yeni sayfada "Sponsored by Sauce Labs" textinin gorunur oldugunu dogrulayiniz
        WebElement sponsoredYazisiElementi = driver.findElement(By.xpath("//p[text()='Sponsored by ']"));
        softAssert.assertTrue(sponsoredYazisiElementi.isDisplayed(),"Sponsored Yazisi Gorunmuyor");
        System.out.println(sponsoredYazisiElementi.getText());

        // FAILED
        // sponsored yazisi yeni sayfada oldugundan ve driver eki sayfada kaldigindan yaziyi locate  edemedik
        // yeni sayfa ve yani sekme acildigi icin driver diger sekmede kaldi, cozumu yariiiiiin --> window handle

        softAssert.assertAll();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }


}
