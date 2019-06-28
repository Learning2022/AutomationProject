package com.employeeapitestcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_Employee_Record extends RestUtils {
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
@BeforeClass
	
	void deleteEmployee() throws InterruptedException
	{
   RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
   httpRequest = RestAssured.given();
  
   response=httpRequest.request(Method.GET,"/employees");
	   
	//First get the Jsonpath object instance from the response interface
    JsonPath jsonpathEvaluator = response.jsonPath();
    
    //capture id
    String empID=jsonpathEvaluator.getString("[0].id");
    response=httpRequest.request(Method.DELETE,"/delete/"+empID);
    
	  Thread.sleep(5000);
	}
	 
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		 System.out.println("Response Body is:" +responseBody);
		Assert.assertEquals(responseBody.contains("sucessfully! deleted records"), true);
		
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



	
	
	

