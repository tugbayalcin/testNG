package tests.day08;

import org.testng.annotations.Test;

public class C01_Notations
{
    // JUnit'ten farkli olarak:
    // 1.JUnit'teki @Before ve @After methodlari her test methodundan once ve sonra calisan yapilardi
    //   TestNG'de ise daha anlasilabilir olsun diye bu yapilar @BeforeMethod ve @AfterMethod olarak adlandirilir
    //   Tipki @BeforeClass @AfterClass gibi
    // 2.JUnit'te @BeforeClass @After class yapilarini kullaniyor isek, bunlar static olmak zorundaydi
    //   Ancak TestNG'de boyle bir zorunluluk soz konusu degildir

    @Test(priority = -9)
    public void youTubeTesti() {
        System.out.println("YouTube Testi Calisti");
    }

    @Test(priority = 8) // eger priority atanmaz ise priority 0 olarak kabul edilir (default priority=0)
    public void amazonTesti() {
        System.out.println("Amazon Testi Calisti");
    }

    @Test // eger priority atanmaz ise priority 0 olarak kabul edilir (default priority=0)
    public void amazonTesti2() {
        System.out.println("Amazon Testi 2 Calisti");
    }

    @Test // birden fazla priority'si olmayan varsa tum hepsi 0 olarak alinir ve kendi icinde alfabetik olarak calistirlir
    // diger methodlar yine kendi priority'sine gore bu 0'larin saginda veya solunda yer alirlar
    public void amazonTesti3() {
        System.out.println("Amazon Testi 3 Calisti");
    }

    @Test(priority = 0) // gri yakiyor bak, yazmasan da ben zaten onu 0 kabul ediyorum diyür
    public void amazonTesti4() {
        System.out.println("Amazon Testi 4 Calisti");
    }

    @Test(priority = 5)
    public void bestBuyTesti() {
        System.out.println("BestBuy Testi Calisti");
    }
}
