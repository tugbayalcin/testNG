package tests.day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class C01_WindowHandle
{
    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void handleWindows() throws InterruptedException {
        driver.get("https://www.amazon.com");
        String windowHandleDegeri1 = driver.getWindowHandle();
        System.out.println("Ilk Sayfanin Window Handle Degeri: " + driver.getWindowHandle());
        // bu kodu benim bilgisayarim uretiyor, o yuzden herkeste farkli uretilir
        // selenium4 ile yeni bir window'u kendimiz acabiliyoruz

        driver.switchTo().newWindow(WindowType.WINDOW); // yeni bir pencere acilir ve driver o pencereye gider
        // burada . ya basinca window ve newWindow cikiyor
        // window: bana windowun handle degerini ver oraya geceyim diyor
        // newWindow: yeni bir penceree istiyprsun, bana yeni window'un type'ini vermelisin diyor, tab veya windpw verilir

        driver.get("https://www.bestbuy.com");
        String windowHandleDegeri2 = driver.getWindowHandle();
        System.out.println("Ikinci Sayfanin Window Handle Degeri: " + driver.getWindowHandle());

        Set<String> handleDegerleriSet = driver.getWindowHandles(); // geriye set dondurur
        System.out.println(handleDegerleriSet);

        driver.switchTo().newWindow(WindowType.TAB); // yeni bir tab acilir, ve driver o tab'a gecer
        driver.get("https://www.facebook.com");
        // ilk pencerede yani amazon'un yaninda acti !!! ilginc

        // amazon'un acik oldugu sayfaya gecin ve nutella icin arama yapin
        driver.switchTo().window(windowHandleDegeri1);
        WebElement aramaKutusu = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER);

        // bestbuy acik olan sayfaya gecin ve title'in bestbuy icerdigini test edin
        driver.switchTo().window(windowHandleDegeri2);
        String actualTitle = driver.getTitle();
        String arananKelime = "Best Buy";
        Assert.assertTrue(actualTitle.contains(arananKelime));

        // facebook'un acik oldugu sayfaya gecin ve url'in https://www.facebook.com oldugunu test edin
        // eger acik olan pencerelerden sadece 1 tanesinin window handle degeri bilinmiyorsa,
        // once tum handle degerlerini bulup bir set'e koyariz,
        handleDegerleriSet=driver.getWindowHandles(); // onceki atamalar oldu yeni degerler geldi set'e konuldu
        // bu soru icin su anda set'te 3 window handle degeri vardir
        // 1. ve 2. sayfanin window handle degerlerini biliyoruz
        // set'imizde olup ilk 2 sayfa olmayan handle degeri 3. sayfanin handle degeri olacaktir
        String windowHandleDegeri3 ="";
        for(String each : handleDegerleriSet){
            if(!(each.equals(windowHandleDegeri1) || each.equals(windowHandleDegeri2))){
                windowHandleDegeri3 = each;
            }
        }
        System.out.println("Ucuncu Sayfanin Window Handle Degeri: " + windowHandleDegeri3);
        System.out.println(handleDegerleriSet);

        driver.switchTo().window(windowHandleDegeri3);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        Thread.sleep(3000);

    }

    @AfterClass
    public void teardown(){
        driver.quit(); // acilan tuuuuum pencereleri kapatir
    }
}
