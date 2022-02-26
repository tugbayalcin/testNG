package tests.day09;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.List;

public class C_SlackHomeworkQ8 extends TestBase
{
    // 1) "https://www.facebook.com/" SAYFASINA GiDiN
    // 2) YENi HESAP OLUSTUR BUTONUNA TIKLAYIN
    // 3) DOGUM TARiHi BOLUMUNDEKi GUNLERiN LiSTESiNi ALIN
    // 4) DOGUM TARiHi BOLUMUNDEKi AYLARIN LiSTESiNi ALIN
    // 5) DOGUM TARiHi BOLUMUNDEKi YILLARIN LiSTESiNi ALIN

    @Test
    public void test01(){

        // 1) "https://www.facebook.com/" SAYFASINA GiDiN
        driver.get("https://www.facebook.com/");

        // 2) YENi HESAP OLUSTUR BUTONUNA TIKLAYIN
        WebElement yeniHesapOlusturElementi = driver.findElement(By.xpath("//a[@class= '_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"));
        yeniHesapOlusturElementi.click();

        // 3) DOGUM TARiHi BOLUMUNDEKi GUNLERiN LiSTESiNi ALIN
        WebElement gunElementi = driver.findElement(By.name("birthday_day"));
        Select selectGun = new Select(gunElementi);
        List<WebElement> gunlerListesi = selectGun.getOptions();
        for (WebElement each : gunlerListesi) {
            System.out.println(each.getText());
        }
        // 4) DOGUM TARiHi BOLUMUNDEKi AYLARIN LiSTESiNi ALIN
        WebElement ayElementi = driver.findElement(By.name("birthday_month"));
        Select selectAy = new Select(ayElementi);
        List<WebElement> aylarListesi = selectAy.getOptions();
        for (WebElement each : aylarListesi) {
            System.out.println(each.getText());
        }

        // 5) DOGUM TARiHi BOLUMUNDEKi YILLARIN LiSTESiNi ALIN
        WebElement yilElementi = driver.findElement(By.name("birthday_year"));
        Select selectYil = new Select(yilElementi);
        List<WebElement> yillarListesi = selectYil.getOptions();
        for (WebElement each : yillarListesi) {
            System.out.println(each.getText());
        }
    }
}
