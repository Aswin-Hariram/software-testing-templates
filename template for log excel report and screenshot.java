package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Base;
import utils.ExcelReader;
import utils.LoggerHandler;
import utils.Reporter;
import utils.Screenshot;
import utils.WebDriverHelper;

public class TestRunner extends Base{

    WebDriverHelper helper;
    ExtentReports report;
    ExtentTest test;

    @BeforeMethod
    public void launchBrowser() {
      try {
        openBrowser();

        helper = new WebDriverHelper(driver);

        report = Reporter.generateExtentReport("Demo Report");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    public void testOne() {
       try {

        test = report.createTest("Test one");
        test.log(Status.PASS, "Test process Started");
        LoggerHandler.logInfo("Test process Started");

        String UserFirstname = ExcelReader.readdata("/home/coder/project/workspace/Project/testdata/test.xlsx", "Sheet1", 0, 0);
        String UserSurname = ExcelReader.readdata("/home/coder/project/workspace/Project/testdata/test.xlsx", "Sheet1", 0, 1);
        
        helper.sendKeys(By.xpath(Xpath.firstnameBox), UserFirstname);
        helper.sendKeys(By.xpath(Xpath.SurnameBox), UserSurname);
        helper.sendKeys(By.xpath(Xpath.emailBox), "user002@email.com");

     
        WebElement rolEle = driver.findElement(By.xpath(Xpath.roleBox));
        Select s = new Select(rolEle);
        s.selectByValue("Director");
        WebElement comEle = driver.findElement(By.xpath(Xpath.conBox));
        Select s1 = new Select(comEle);
        s1.selectByValue("India");


        String path = Screenshot.getScreenShot(driver, "Slack_screenshot");
        test.addScreenCaptureFromPath(path);
        
        test.log(Status.PASS, "Test process completed");
        LoggerHandler.logInfo("Test process Completed");

        Thread.sleep(4000);

       } catch (Exception e) {
        e.printStackTrace();
       }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        report.flush();
    }
}
