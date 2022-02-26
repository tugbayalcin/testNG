package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_DependsOnMethods
{
    // Bir class oluşturun: DependsOnTest
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void logoTest(){
        // https://www.amazon.com/ adresine gidin.
        driver.get("https://www.amazon.com/");

        //    1. Test : Amazon ana sayfaya gittiginizi test edin
        // logodan test edelim
        WebElement logoElementi = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logoElementi.isDisplayed());

    }

    @Test (dependsOnMethods = "logoTest")
    public void aramaTesti(){
        //    2. Test : 1.Test basarili ise search Box’i kullanarak “Nutella” icin arama yapin
        //    ve aramanizin gerceklestigini Test edin
        WebElement aramaKutusuElementi = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusuElementi.sendKeys("Nutella" + Keys.ENTER);

        // title'da nutella ayziyor, buradan teyit edelim
        String actualTitle = driver.getTitle();
        String arananKelime = "Nutella";

        Assert.assertTrue(actualTitle.contains(arananKelime));
        // eger depends oldugum method failed olursa 2. calisacak method hic calistirilmaz, ignored olur
        // cunku calismasi ilk testin gecmesine bagli idi
        // buna ignored denir

    }

    @Test (dependsOnMethods = "aramaTesti")
    public void fiyatTesti(){
        //    3.Test : “Nutella” icin arama yapildiysa ilk urunu tiklayin ve fiyatinin    $14.99 oldugunu test edin
        driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
        WebElement urunFiyatElementi=driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[2]"));

        String urunFiyatiString=urunFiyatElementi.getText();
        String arananFiyat="$14.99";

        Assert.assertTrue(urunFiyatiString.contains(arananFiyat));

        // NOTE: Burada onemli bir durum var
        // ben yalnizca 2. testi calistirirsam, 2. test 1. teste bagli oldugu icin once 1. test calisir,
        // burasi before method/ before class mantiginda calisir
        // hatirla, tek basina test methodunu calistirmayi denemistin ve beforelar da calismisti
        // ancak bu sorudaki gibi birbirine bagli 3 test methodu varsa ve ben yalnizca 3. test methodunu run edersem,
        // 3 --> 2'ye bagli, 2'de --> 1'e bagli diyerek 1'den calismaya baslamaz
        // yalnizca kendinden bir oncekinden itibaren calismaya baslar ,
        // buna cok dikkat etmelisin









    }
}
