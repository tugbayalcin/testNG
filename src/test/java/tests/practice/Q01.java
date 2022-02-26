package tests.practice;

import org.testng.annotations.Test;
import utilities.TestBase;

/*
  ●TestNG (default ) olarak @Test methodları ni alfabetik sıraya gore run eder ..(Yukardan asagi degil)
  ●priority annotation Testlere öncelik vermek icin kullanilir, Kucuk olan Numara daha once calisir.
  priority:  TestNG testlerinde, testler konsola alfabetik sira ile yazdirilir.
  priority'nin default degeri 0'dir

  enabled = false  methodu : Testi gormezden gelmek icin @Test in yanina    '(enabled = false)' fonksiyonunu kullaniriz.
 */

public class Q01 extends TestBase
{
    @Test(priority = 3000)
    public void b(){
        System.out.println("b");
    }
                                            // interview sorusu
    @Test(priority = 2001,enabled = false) // junitte ignore var, urada ignore tam olarak ayni islevi yapmaz, enabled=false kullaniriz
    public void a(){
        System.out.println("a");
    }

    @Test(priority = 2000)
    public void c(){
        System.out.println("c");
    }

    @Test
    public void test01(){
        System.out.println("Test01...");
    }

    @Test
    public void test02(){
        System.out.println("Test02...");
    }


    @Test
    public void test03(){
        System.out.println("Test03...");
    }


    @Test(enabled = false)
    public void test04(){
        System.out.println("Test04...");
    }


    @Test(enabled = false)
    public void test05(){
        System.out.println("Test05...");
    }


    @Test
    public void test06(){
        System.out.println("Test06...");
    }


}
