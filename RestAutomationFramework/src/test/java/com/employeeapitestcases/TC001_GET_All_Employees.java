package com.employeeapitestcases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_All_Employees extends TestBase{
	
	@BeforeClass
	
	void getAllemployees() throws InterruptedException
	{
		//logger.info("Started TC001_GET_ALL_EMPLOYEES");
   RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
   httpRequest = RestAssured.given();
   response = httpRequest.request(Method.GET, "/employees");	
	Thread.sleep(5000);
	}
	@Test
	void checkResponseBody()
	{
		//logger.info("Checking RESponse body");
		String responseBody = response.getBody().asString();
		//logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
    
	@Test
   void checkstatuscode()
   {
  int statusCode = response.getStatusCode();
  Assert.assertEquals(statusCode, 200);
   }
	
	@Test
	void checkResponseTime()
	{
		long responseTime=response.getTime();
		if(responseTime>2000)
		Assert.assertTrue(responseTime<2000);
		
	}
	
    @Test
    void checkstatusLine()
    {
    String statusLine = response.getStatusLine();
    Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    
    }
    @Test
    void checkContentType()
    {
    String contentType= response.header("Content-Type");
    Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }
    
    @AfterClass
    void tearDown()
    
    {
    	System.out.println("finished TestCase");
    }
    }

	
	
