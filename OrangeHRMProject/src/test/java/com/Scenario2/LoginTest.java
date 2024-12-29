package com.Scenario2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Scenario2utility.Propertiesreader;
public class LoginTest {
	WebDriver driver;
	LoginClass login=new LoginClass(driver);
	Propertiesreader prop;
	AdminClass ad=new AdminClass(driver);
	
  

  @BeforeTest
  public void beforeTest() {
	  prop=new Propertiesreader();
	  driver=new ChromeDriver();
	  driver.get(prop.getData("url"));
	  login.addWait();
	  driver.manage().window().maximize();
	  login=new LoginClass(driver);
  }
  
       @Test(priority=1)
      public void loginTest()
       {
    	   login.login(prop.getData("username"), prop.getData("password"));
    	   
       }
      
       @Test(priority=2)
       public void validateUrl()
       {
    	   String url=login.appUrl();
    	   Assert.assertEquals(url,"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "URL mismatched.");
    	   System.out.println("Url Matched launch success. Test Passed");
       }
       
       
       @Test(priority=3)
      public void validateTitle()
       {
    	   System.out.println("Page Tilte: "+login.appTitle());
    	   Assert.assertEquals(login.appTitle().contains("OrangeHRM"),true, "Title mismatched.");
    	   System.out.println("Title matched successfully.");
       }
       
     @AfterClass
     public void tearDown() {
    	 if(driver!=null)
    		 driver.quit();
    	 else
    		 System.out.println("Driver is null");
     }

     
}
