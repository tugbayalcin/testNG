package tests.seleniumCodingChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.List;

public class Day01 extends TestBase
{
    // Selenium Coding Challenge # Day 1
    //
    //Open chrome/firefox browser
    //Launch the URL : https://www.worldometers.info/world-population/
    //
    //Keep getting the count of:
    //- Current World Population
    //- Today:  Births, Deaths and population growth today
    //- This Year:  Births, Deaths and population growth today
    //
    //while(true){
    //
    // keep getting the element text using selenium
    // print it on console
    //
    // //break the loop after few secs (20 secs)
    //}
    //
    //submit the assignment :
    //Send your source code and screenshot of the console output.

    @Test
    public void test01(){
        driver.get("https://www.worldometers.info/world-population/");

        //- Current World Population
        WebElement currentWorldPopulationElementi = driver.findElement(By.xpath("//div[@class='maincounter-number']"));
        System.out.println("Current World Population: " + currentWorldPopulationElementi.getText());
        // videoda soyle bir sey yapiyor, sanirim soruyu tam anlamadim
        /*
        List<WebElement> populationList = driver.findElements(By.xpath("//div[@class='maincounter-number']/span/span"));
        while(true) {
            for (WebElement each : populationList) {
                System.out.print(each.getText());
            }
        }

         */

        //- Today:  Births, Deaths and population growth today
        WebElement birthsTodayElementi = driver.findElement(By.xpath("//span[@rel='births_today']"));
        System.out.println("Births Today: " + birthsTodayElementi.getText());

        WebElement deathsTodayElementi = driver.findElement(By.xpath("//span[@rel='dth1s_today']"));
        System.out.println("Deaths Today: " + deathsTodayElementi.getText());

        WebElement populationGrowthTodayElementi = driver.findElement(By.xpath("//span[@rel='absolute_growth']"));
        System.out.println("Population Growth Today: " + populationGrowthTodayElementi.getText());

        //- This Year:  Births, Deaths and population growth today
        WebElement birthsThisYearElementi = driver.findElement(By.xpath("//span[@rel='births_this_year']"));
        System.out.println("Births This Year: " + birthsThisYearElementi.getText());

        WebElement deathsThisYearElementi = driver.findElement(By.xpath("//span[@rel='dth1s_this_year']"));
        System.out.println("ThisYear Today: " + deathsThisYearElementi.getText());

        WebElement populationGrowthThisYearElementi = driver.findElement(By.xpath("//span[@rel='absolute_growth_year']"));
        System.out.println("Population Growth ThisYear: " + populationGrowthThisYearElementi.getText());


    }
}
