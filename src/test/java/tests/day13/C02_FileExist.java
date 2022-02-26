package tests.day13;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C02_FileExist
{
    @Test
    public void Test01(){

        System.out.println(System.getProperty("user.home")); // --> /Users/tugbayalcin

        // masaustundeki bir klasorun path'ini istesek sag clic vsvsv bulabilirsin
        // bir path'deki user.path'ten sonraki kisim tum bilgisayarlar icin aynisir
        // yani dinamic olarak masaustumdeki klasorun path'ini yazmak istesem:
        String path = System.getProperty("user.home") + "/Desktop/TechProEd";
        System.out.println(path); // --> /Users/tugbayalcin/Desktop/TechProEd

        System.out.println("user.dir : " + System.getProperty("user.dir"));
        // --> user.dir : /Users/tugbayalcin/IdeaProjects/com.TestNGBatch44

        // user.home  : bizim bilgisayarimiza ozel kismi verir
        // user.dir : projenin yolunu verir


        // masaustunde TechProEd isimli klasorun icinde ISTQB iimli bir klasor oldugunu dogrulayin
        //********** masaustunde TechProEd klasoru ve icinde aranan file yoksa calismaz !!!!
        // 1- once bu dosyaya ulasmak icin gerekli path'i yazalim
        String dosyaYolu = System.getProperty("user.home") + "/Desktop/TechProEd";

        System.out.println(Files.exists(Paths.get(dosyaYolu))); // true yazdi demek ki boolean donduruyormus
        // file var mi yok mu diye baktirdik, true dedi yani var dedi

        // projemizde pom.xml oldugunu test edin
        // /Users/tugbayalcin/IdeaProjects/com.TestNGBatch44/pom.xml

        System.out.println(System.getProperty("user.dir")); // projemin yolunu verir
        // /Users/tugbayalcin/IdeaProjects/com.TestNGBatch44

        // dosya yolu ile proje yolu arasindaki tek fark proje adi, o zaman ben dosyamin dynamic
        // path'ini su sekilde yazabilirim
        String pomPath = System.getProperty("user.dir") + "/pom.xml";

        // bu dosya burada var mi diye bakalim
        Assert.assertTrue(Files.exists(Paths.get(pomPath)));
    }
}
