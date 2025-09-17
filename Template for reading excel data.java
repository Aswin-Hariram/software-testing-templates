package runner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Base;
import utils.ExcelReader;

public class TestRunner extends Base{

    @BeforeMethod
    public void launchBrowser() {
      try {
        openBrowser();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    public void testOne() {
       try {

        
        String UserFirstname = ExcelReader.readdata("/home/coder/project/workspace/Project/testdata/test.xlsx", "Sheet1", 0, 0);
        System.out.println(UserFirstname);


       } catch (Exception e) {
        e.printStackTrace();
       }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
