package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class C02_SoftAssertion
{

    // burada onemli olan assertionlarimizin tek bir test methodunun icinde olmasidir !!!

    WebDriver driver;
    //Yeni bir Class Olusturun : C03_SoftAssert

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Test(){
    // 1. “http://zero.webappsecurity.com/” Adresine gidin
        driver.get("http://zero.webappsecurity.com/");

    // 2. Sign in butonuna basin
        driver.findElement(By.xpath("//button[@id='signin_button']")).click();

    // 3. Login kutusuna “username” yazin
        WebElement loginKutusuElementi = driver.findElement(By.xpath("//input[@id='user_login']"));
        loginKutusuElementi.sendKeys("username");

    // 4. Password kutusuna “password” yazin
        WebElement passwordKutusuElementi = driver.findElement(By.xpath("//input[@id='user_password']"));
        passwordKutusuElementi.sendKeys("password");

    // 5. Sign in tusuna basin
        driver.findElement(By.xpath("//input[@name='submit']")).click();

    // 6. Pay Bills sayfasina gidin
        //driver.findElement(By.xpath("//button[@id='details-button']")).click(); --> guvenli degil uyarisi icin
        //driver.findElement(By.xpath("//a[@id='proceed-link']")).click();  --> guvenli degil uyarisi icin
        // veya
        driver.navigate().back();
        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        driver.findElement(By.xpath("//span[@id='pay_bills_link']")).click();

    // 7. “Purchase Foreign Currency” tusuna basin
        driver.findElement(By.xpath("//a[text()='Purchase Foreign Currency']")).click();

    // 8. “Currency” drop down menusunden Eurozone’u secin
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='pc_currency']"));
        Select select = new Select(dropdown);
        select.selectByValue("EUR");

    // 9. soft assert kullanarak "Eurozone (Euro)" secildigini test edin
        SoftAssert softAssert = new SoftAssert(); // adim 1:
        String actualSecim = select.getFirstSelectedOption().getText();
        String expected = "Eurozone (euro)";
        softAssert.assertEquals(actualSecim,expected,"Dropdown Dogru Secilemedi");
        System.out.println("Hard assertion'da ilk assertion failed olsaydi, bu satir calismazdi");

    // 10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
    // "Select One", "Australia (dollar)", "Canada (dollar)","Switzerland (franc)","China (yuan)",
    // "Denmark (krone)","Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)",
    // "Japan (yen)","Mexico (peso)","Norway (krone)","New Zealand (dollar)","Sweden (krona)",
    // "Singapore (dollar)","Thailand (baht)"

        List<WebElement> optionList= select.getOptions();
        String actualOptionListesiString="";
        for (WebElement each: optionList)
        {
            actualOptionListesiString += "\""  +   each.getText()  +   "\", ";
        }

/*
        for (int i=0; i<optionList.size()-1; i++)
        {
            actualOptionListesiString += "\""  +   optionList.get(i).getText() +   "\", ";
            // ben siralamayi garantiye almak icin foreach yerine for kullanirdim
            // burada unutmaman gereken sey bu bir arrayd egil liste
            // dolayisiyle bir ibdexteki elemana optionList[i] ile erisemezsin, get(i) ile erisebilirsin
        }

 */



        //actualOptionListesiStringi = actualOptionListesiStringi.trim(); // sona eklemek zorunda oldugumuz boslugu sildik, ama alt satir ikisini de kapsiyor aslinda
        actualOptionListesiString = actualOptionListesiString.substring(0,actualOptionListesiString.length()-2); //  sona eklemek zorunda oldugumuz virgulu sildik
        String expectedListeString="\"Select One\", \"Australia (dollar)\", \"Canada (dollar)\", \"Switzerland (franc)\", \"China (yuan)\", \"Denmark (krone)\", \"Eurozone (euro)\", \"Great Britain (pound)\", \"Hong Kong (dollar)\", \"Japan (yen)\", \"Mexico (peso)\", \"Norway (krone)\", \"New Zealand (dollar)\", \"Sweden (krona)\", \"Singapore (dollar)\", \"Thailand (baht)\"";
        softAssert.assertEquals(actualOptionListesiString,expectedListeString);

        softAssert.assertAll(); // bunu yazmaz isek bize rapor vermez
        // istedigimiz yere kadar calismaya devam ederi diyoruz ya
        // istedigimiz yer assertAll'un yazdigi yerdir
        // failed olan methoddan sonra assertAll gorulene kadar program calismaya devam eder,// gorunce durur

        System.out.println("Testlerden failed olan varsa softAsser.assertAll'dan sonrasi calismaz");

    }

    @AfterMethod
    public void teardown(){
        driver.close();
    }
}
