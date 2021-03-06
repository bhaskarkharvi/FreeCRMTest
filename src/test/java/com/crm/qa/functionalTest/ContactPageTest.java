package com.crm.qa.functionalTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.PageRepository.ContactPage;
import com.crm.qa.PageRepository.HomePage;
import com.crm.qa.PageRepository.LoginLandingPage;
import com.crm.qa.PageRepository.LoginPage;
import com.crm.qa.base.TestBase;
import com.crm.qa.utils.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ContactPageTest extends TestBase {
	public static LoginLandingPageTest LandingLoginTestObj;
	LoginLandingPage landingLoginPgOb;
	public  LoginPage LoginPage;
	HomePage homePg = new HomePage();
	ContactPage contactPg;
	TestUtil testUtil;

	String sheetName="AddContacts"; 

	public  ContactPageTest() {
		super();
	}


	@BeforeTest
	public void launchApplication() {
		try {
			initialization();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		testUtil= new TestUtil();
		contactPg= new ContactPage();
		//landingLoginPgOb=new LoginLandingPage();
		//landingLoginPgOb.LandingLogin();
	
		LandingLoginTestObj=new LoginLandingPageTest();
		LandingLoginTestObj.LandingLoginTest();
	 	//LoginPg=new LoginPage();
		//LoginPage.LoginToCRM(prop.getProperty("username"),prop.getProperty("password"));
		LoginPage= new LoginPage();
		homePg=new HomePage();
		homePg=LoginPage.LoginToCRM(prop.getProperty("username"),prop.getProperty("password"));
		contactPg=homePg.ClickContactLink();

	}

	//@Test(priority=1,retryAnalyzer=com.crm.qa.Analyzer.FailedReExecute.class)
	@Test(priority=1)
	@Severity(SeverityLevel.NORMAL)
	@Description("ContactLinkNavigationTest ")
	public void ContactLinkNavigationTest() {
		///String contactPgTitle= contactPg.ContactsPageLabel;
		contactPg=homePg.ClickContactLink();
		System.out.println("Contact Page - Header test start");
		Assert.assertTrue(contactPg.ConatctPgHeader(),"Contact Page header title is not displayed");
		System.out.println("Contact Page - Header test END");
	}

	@DataProvider
	
	
	public Object[][] getCRMTestData() {
		Object[][] data=TestUtil.getTestData(sheetName);
		return data;
	}

	//@Test(priority=2,dataProvider="getCRMTestData",retryAnalyzer=com.crm.qa.Analyzer.FailedReExecute.class)
	@Test(priority=2)
	@Severity(SeverityLevel.NORMAL)
	@Description("Enter contact details")
	public void NewContactTest(String FirstName, String LastName, String Company) {
		contactPg.NewBtn.click();
		contactPg.createNewContact(FirstName, LastName, Company);
		Assert.assertTrue(ContactPage.contactPhtoto.isDisplayed(),"Contact Avatar is not displayed");
		ContactLinkNavigationTest();
	}
	
}
