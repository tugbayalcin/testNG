package tests.day11;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C03_TestBaseIlkTest extends TestBase
{
    // bizim TestBase'imizle test class'imiz ayni package icinde degil
    // o yuzden TestBase class'indaki WebDriver'i protected yapalim
    // !!! bu sekilde diger package'dan test alabilmek TestNG'nin ozelligidir !!!

    @Test
    public void test01(){
        driver.get("https://www.amazon.com");

    }
}
