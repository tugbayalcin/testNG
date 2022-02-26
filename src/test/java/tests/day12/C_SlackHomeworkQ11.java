package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.Set;

public class C_SlackHomeworkQ11 extends TestBase
{
     /*
      go to url :http://demo.guru99.com/popup.php
      get the first window handle value
      clicking on click here button
      get all the window in the set
      switch to window
      input email id (somepne@gmail.com) and type something in that input
      Clicking on the submit button
      verify title as expected
      switch to first window
     */

    @Test
    public void test01() throws InterruptedException {
        driver.get("http://demo.guru99.com/popup.php");
        String windowHandleDegeri1 = driver.getWindowHandle();

        WebElement clickHereButtonElementi = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickHereButtonElementi.click();
        Set<String> windowHandleSet = driver.getWindowHandles();
        String windowHandleDegeri2 = "";
        for (String each : windowHandleSet)
        {
            if(!each.equals(windowHandleDegeri1))
                windowHandleDegeri2 = each;
        }
        driver.switchTo().window(windowHandleDegeri2);

        WebElement emailElementi = driver.findElement(By.name("emailid"));
        emailElementi.sendKeys("someone@gmail.com");
        Thread.sleep(2000);
        WebElement submitButtonElementi = driver.findElement(By.name("btnLogin"));
        submitButtonElementi.click();
        Thread.sleep(2000);

        WebElement girisYazisiElementi = driver.findElement(By.xpath("//h2[text()='Access details to demo site.']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(girisYazisiElementi.isDisplayed());

        driver.switchTo().window(windowHandleDegeri1);
        Thread.sleep(2000);
    }

}
