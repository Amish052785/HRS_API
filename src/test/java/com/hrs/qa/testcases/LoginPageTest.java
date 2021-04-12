package com.hrs.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrs.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginPageTest extends TestBase{

	Response response;
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "https://cc.healthrecoverysolutions.com";
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.request(Method.GET,"/Login");
		
	}
	
	@Test(priority=1)
	public void validatePageTitle() {
		logger.info("************************ Validate Page Title ******************************");
		
		String strResponse = response.getBody().asString();
		
		//Validate Page Title - HRS | ClinicianConnect
		Assert.assertTrue(strResponse.contains("HRS | ClinicianConnect"));
		System.out.println("Title --> 'HRS | ClinicianConnect' - is correct");
		logger.info("***************************************************************************");
	}
	
	@Test(priority=2)
	public void validateStatusCode() {
		logger.info("************************ Validate Status Code *****************************");
		
		//Validate Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status code --> "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		logger.info("**************************************************************************");
	}
	
	@Test(priority=3)
	public void validateContentType() {
		logger.info("************************ Validate Content Type ***************************");
		
		Headers allHeaders = response.headers();
		
		for(Header header:allHeaders) {
			if(header.getName().equals("Content-Type")) {
				Assert.assertEquals(header.getValue(), "text/html");
				System.out.println(header.getName()+" --> "+header.getValue());
				break;
			}
		}
		
		logger.info("**************************************************************************");
	}
	
	@Test(priority=4)
	public void validateStatusLine() {
		logger.info("************************ Validate Status Line ****************************");
		
		//Validate Status Line 
		String statusLine = response.getStatusLine();
		System.out.println("Status line --> "+ statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		logger.info("**************************************************************************");
	}
}
