package com.employeeapitestcases;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_single_Employee_Record extends TestBase {
	RequestSpecification httpRequest;
	Response response;
@BeforeClass
	
	void getEmployeeData() throws InterruptedException
	{
   RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
   httpRequest = RestAssured.given();
   response = httpRequest.request(Method.GET, "/employee/"+empID);	
   
	Thread.sleep(5000);
	}
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
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
    @Test
    void checkserverType()
    {
    	String serverType=response.header("Server");
    	Assert.assertEquals(serverType, "nginx/1.14.1");
    }
    @Test
    void checkContentLength() {
    	String contentLength= response.getHeader("Content-Length");
    	Assert.assertTrue(Integer.parseInt(contentLength)<1500);
    }
    
}



