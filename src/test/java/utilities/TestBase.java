package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.SimpleTimeZone;

public abstract class TestBase
{
    protected WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void teardown(){

        driver.quit();
    }

    public void tumSayfaScreenshot() throws IOException {
        TakesScreenshot tss = (TakesScreenshot) driver;
        String tarih = new SimpleDateFormat("yyMMddhhmmss").format(new Date()); // yil ay gun saat dakika saniye
        // fotograf adresi her seferinde degisik olsun ki cektigim fotograflar ayni isimde olup kaydedilirken
        // birbirini ezip bir oncekini kaybetmesin diye cektigimiz fotograflara tarih ekliyoruz
        File tumSayfa = new File("target/screenshot/tumSayfa"+tarih+".jpg");
        File geciciResim = tss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(geciciResim,tumSayfa);
    }


}
