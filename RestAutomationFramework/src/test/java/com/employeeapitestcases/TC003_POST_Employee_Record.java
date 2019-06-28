package com.employeeapitestcases;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_POST_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
@BeforeClass
	
	void createEmployee() throws InterruptedException
	{
   RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
   httpRequest = RestAssured.given();
   JSONObject requestParams=new JSONObject();
	  //JSONObject is aclass that represents a simple JSON. we can add key,value pairs using out method
	  requestParams.put("name","empName");
	  requestParams.put("salary","empSalary");
	  requestParams.put("age","empAge");
	  
	  //Add header stating the request body is JSON
       httpRequest.header("Content-Type","application/json");
	  //Add JSON to the body of request
	  httpRequest.body(requestParams.toJSONString()); 
	  
	  //Response object
	   response=httpRequest.request(Method.POST,"/create");
	   
	    
	  Thread.sleep(5000);
	}
	 
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		 System.out.println("Response Body is:" +responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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
    void checkContentEncoding() {
    	String contentEncoding= response.header("Content-Encoding");
    	Assert.assertEquals(contentEncoding,"gzip");
    }
    
}



	
	
	

