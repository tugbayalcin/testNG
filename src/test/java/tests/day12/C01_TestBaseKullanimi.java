package tests.day12;

import org.testng.annotations.Test;
import tests.day10.C01_Allerts;
import utilities.TestBase;

public class C01_TestBaseKullanimi
{
    @Test
    public void test01(){
        // projemiz icerisindeki herhangi bir class'tan obje olusturabilir ve o obje sayesinde ait oldugu class'daki
        // tum variable ve methodlara (accesss modifier izin verdigi surece) ulasabiliriz

        C01_Allerts obj = new C01_Allerts(); // C01.. classindan obje olusturduk
        // obj.  --> yaptigim zaman C01.. class'inin icindeki methodlari gorebiliyorum (ayni package icindeyim)
        // eger proje kapsaminda bir class'tan obje olusturulmasini engellemke istersek,
        // 1.ya o class'in constructor'ini private yapariz
        // 2. ya da class'in kendisini abstract yapabiliriz ( yine de constructor'i vardir)
        // 1. method cok tercih edilmez cunku OOP consept'e uymaz(cok sinirli sayida kullanimi vardir)
        // 2. methodu kullanabiliriz. Boylece o class'taki abstract olmayan (concrete) methodlari override etmek zorunda olmadan
        // kullanabiliriz ama obje olusturamayiz

        // ornegin biz test base class'imizi abstract yaptigimizdan obje olusturamayiz ama child class'lardan
        // test base'deki setup ve teardown methodlarini kullanabiliriz

        // TestBase obj1 = new TestBase();
    }
}
