package tests.day09;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class C03_SoftAssertion
{
    @Test
    public void test(){
        int a=10;
        int b=20;
        int c=30;

        // Adim 1: soft assertion objesi olusturulur
        SoftAssert softAssert = new SoftAssert();

        //Adim2: istedigimiz kadar soft assertion yazilir
        softAssert.assertEquals(a,b,"1.Test Basarisiz"); // failed
        softAssert.assertTrue(a>b,"2.Test Basarisiz"); // failed
        softAssert.assertTrue(a<c,"3.Test Basarisiz"); // passed
        softAssert.assertTrue(c>b,"4.Test Basarisiz"); // passed
        softAssert.assertTrue(c<a,"5.Test Basarisiz"); // failed

        // assertAll demezsek class calisir ve pass yazar
        // cunku aslinda raporlama yapmaz sadece kodlar calismis olur

        softAssert.assertAll();
        // hard assertion olsa idi yukaridan calismaya baslayacak ve ilk failed'da exception verip
        // asagidakileri calistirmayacakti ve ben o ilk hatayi duzeltip bir daha calistirip ikinci hatayla karsilasacaktim
        // soft assert'te ise tum fail ve pass'leri hafizasina yazarak assertAll gorene kadar calismaya devam eder
        // ve ne zaman ki assertAll gordu, hafizasindaki seyleri bana raporlayacak
        // raporda 3 tane hata gorunecek ama hangisinin hangisi oldugunu bilmiyoruz

        // bu yuzden soft assert'te hata mesaji yazmak onemlidir
        // soft assertionda, tuuum soft assertionlar passed ise kod calismaya devam eder






    }
}
