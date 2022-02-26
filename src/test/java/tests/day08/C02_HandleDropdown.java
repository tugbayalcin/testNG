package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

// dropdown icin acilir pencere diyebiliriz ancak her acilir pencere de dropdown degildir
// yuzde 99 olarak select tag'i ve option attribute'u ile olusturulur
// aksi taktirde developer giciklik yapiyordur
//

public class C02_HandleDropdown
{
    WebDriver driver;
    @BeforeMethod
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void dropdownTesti() throws InterruptedException {
        // Dropdown'da var olan seceneklerden birini secmek icin
        // 1. adim: Dropdown web elementini locate edip bir degiskene atiyoruz

        driver.get("https://www.amazon.com");
        WebElement dropdownElementi = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        // 2. adim: Select class'indan bir obje olusturalim ve parametre olarak locate ettigimiz web elementi yazalim
         Select select = new Select(dropdownElementi);

         // Adim 3: select objesini kullanarak, Select class'inda var olan 3 secim methodundan
        // istedigimizi kullanarak dropdown'da var olan option'lardan birini secebiliriz
        // Select class'inin 3 faydali emthodunu kullaniriz
        // selectByVisibleText, selectByValue, selectByIndex(index 0'dan baslar!!!)
        // secim yapmamiza yardim eden bu 3 method void'dir, dolayisiyla bize bir sey dondurmezler

         select.selectByIndex(3); // indexler burada da 0'dan basliyor

        // System.out.println(select.selectByIndex(5)); --> hata verir, void olan bir seyi nasil yazdirayim diyür

        // eger sectigimiz option degerini yazdirmak istersek
        System.out.println(select.getFirstSelectedOption().getText()); // Baby
        // select.getFirstSelectedOption() bize bir web element dondurur,
        // eger elementin ustundeki yazyi istiyorsak .getText() kullanmak zorundayiz
        Thread.sleep(3000);

        select.selectByVisibleText("Deals");
        Thread.sleep(3000);

        select.selectByValue("search-alias=arts-crafts-intl-ship");

        // tum listeyi yazdiralim
        List<WebElement> optionList =select.getOptions();
        for (WebElement each : optionList)
        {
            System.out.println(each.getText());
        }


    }
    @AfterMethod
    public void teardown() {
        driver.close();
    }
}
