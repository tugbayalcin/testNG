package tests.day14;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_Faker extends TestBase
{


    @Test
    public void facebookKayitTesti() throws InterruptedException {
        // "https://facebook.com"  Adresine gidin
        driver.get("https://facebook.com");

        // “create new account”  butonuna basin
        WebElement yeniHesapOlusturElementi = driver.findElement(By.xpath("//a[@class= '_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"));
        yeniHesapOlusturElementi.click();

        // “firstName” giris kutusuna bir isim yazin
        WebElement isimKutusuElementi = driver.findElement(By.xpath("(//input[@class='inputtext _58mg _5dba _2ph-'])[1]"));

        Actions actions = new Actions(driver);
        Faker faker = new Faker();
        String email=faker.internet().emailAddress(); // bunu iki kere yazmam gerekiyor ve ben faker ile iki kere ayni degeri uretemem
        // bu yuzden bir tane uretip bunu stringe atiyorum, yani elimde tutuyorum, sonra istedigim adar kullanabilirim

        actions.click(isimKutusuElementi)
                .sendKeys(faker.name().name())
                .sendKeys(Keys.TAB) // soyadi kutusuna gectik
                .sendKeys(faker.name().lastName()) // “surname” giris kutusuna bir soyisim yazin
                .sendKeys(Keys.TAB) // mail kutusuna gectik
                .sendKeys(email) // “email” giris kutusuna bir email yazin
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .perform();

                //Tarih icin gun secin --> tarih islemlerini birebir locate et daha guvenilir, tab ile ugrasma
                driver.findElement(By.name("birthday_day")).sendKeys("29");
                driver.findElement(By.name("birthday_month")).sendKeys("Mart");
                driver.findElement(By.name("birthday_year")).sendKeys("1997");
                driver.findElement(By.xpath("//label[text()='Erkek']")).click(); // cinsiyeti erkek sectirdik
                Thread.sleep(2000);

        // “email” onay kutusuna emaili tekrar yazin
        // Bir sifre girin
        // Tarih icin gun secin
        // Tarih icin ay secin

        // isaretlediginiz cinsiyetin secili, diger cinsiyet kutusunun secili olmadigini test ediniz
        WebElement erkekSecimElementi=driver.findElement(By.xpath("//input[@value='2']"));
        WebElement kadinSecimElementi=driver.findElement(By.xpath("//input[@value='1']"));
        Assert.assertTrue(erkekSecimElementi.isSelected());
        Assert.assertFalse(kadinSecimElementi.isSelected());

        // sayfayi kapatin
    }
}
