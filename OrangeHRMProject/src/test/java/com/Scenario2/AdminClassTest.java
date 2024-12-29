package com.Scenario2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import Scenario2utility.Propertiesreader;

public class AdminClassTest {
	Propertiesreader prop;
  	WebDriver driver;
  	LoginClass login=new LoginClass(driver);
  	AdminClass ad=new AdminClass(driver);	
  	 @BeforeClass
  	  public void login() {
  		login.login(prop.getData("username"), prop.getData("password"));
  	     }

  	 @BeforeTest
  	  public void setUp() {
  		 prop=new Propertiesreader();
  		 driver=new ChromeDriver();
  		 driver.get(prop.getData("url"));
  		login.addWait();
  		 driver.manage().window().maximize();
  		login=new LoginClass(driver);
  		 ad=new AdminClass(driver);
  	  }
  	   
  	  @Test(priority=1)
  	  public void getLeftSidemenuOptions() {
  		  System.out.println("Left side Menu options");
  		login.addWait();
  		  Assert.assertEquals(ad.getOptionsCount(),12,"Count Not matched .Test failed!!");
  		  System.out.println("Left side count Matched to 12.  Test Passed!!!");
  		login.addWait();
  		  System.out.println("Click on Admin Option from left side Menu");
  		  ad.clickElement(ad.adminOption);
  		login.addWait();
  		  System.out.println("Clicked on Admin page");
  		  Assert.assertEquals(ad.adminHeader.getText(),"Admin","Failed to launch Admin Page . Test Failed");
  		  System.out.println("Launched the Admin pager succesfully . Test passed");
  		  	  
  	  }
  	 
  	  @Test(priority=2)
  	  public void searchByUsername()
  	  {
  		  ad.searchTextBox.sendKeys("Admin");
  		login.addWait();
  		  System.out.println("Search with Employ: Admin");
  		  ad.clickElement(ad.searchBtn);
  		login.addWait();
  		  String output=ad.recordfoundHeader.getText();
  		  System.out.println(output);
  		login.refreshPage();
  		login.addWait();
  	  }
  	  
  	  @Test(priority=3)
  	  public void searchByRole()
  	  {
  		  System.out.println("Search employee with Role: Admin");
  		  ad.searchWithAdminRole();
  		  System.out.println(ad.recordfoundHeader.getText());
  		login.addWait();
  		login.refreshPage();
  		login.addWait();
  	  }
  	  
  	  @Test(priority=4)
  	  public void searchByUserStatus()
  	  {
  		  System.out.println("Search employee with Satus: Enabled");
  		  ad.searchWithStatus();
  		  System.out.println(ad.recordfoundHeader.getText());
  		login.addWait();
  		login.refreshPage();
  		login.addWait();
  	  }
  	  
  	  @AfterClass
  	  public void afterClass() {
  		  driver.quit();
  	  }

  }
