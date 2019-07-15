
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@Test
public class API_tests {
	
	@BeforeSuite(alwaysRun = true)
	public void configure() {

		RestAssured.baseURI="https://reqres.in/api/users";
		
	}
	@Test
	void getRequest()
	{
		
		RequestSpecification httpRequest=RestAssured.given();
		
		httpRequest.header("Content-type", "application/json");
		Response response=httpRequest.get();
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
		ResponseBody body=response.getBody();
		UsersData responseJsonBody=body.as(UsersData.class);
		Data[] users = response.jsonPath().getObject("data", Data[].class );
		
		for(Data data : users)
		 {
		 System.out.println("User " + data.dataString());
		 }
		
		Assert.assertEquals(1, responseJsonBody.page);
		System.out.println("page is: " +responseJsonBody.page);
		Assert.assertEquals(3, responseJsonBody.per_page);
		System.out.println("per_page is: " +responseJsonBody.per_page);
		Assert.assertEquals(12, responseJsonBody.total);
		System.out.println("total are: " +responseJsonBody.total);
		Assert.assertEquals(4, responseJsonBody.total_pages);
		System.out.println("total_pages are :" +responseJsonBody.total_pages);
	}

	@Test
	void postRequest() {
		
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");
		httpRequest.header("Content-type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		Response response=httpRequest.post();
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		ResponseBody body=response.getBody();
		UsersDataUpdated responseJsonBody=body.as(UsersDataUpdated.class);
		
		String name=response.jsonPath().get("name");
		Assert.assertEquals(name, "morpheus");
		
		String job=response.jsonPath().get("job");
		Assert.assertEquals(job, "leader");
			
	}
	@Test
	void putRequest() {
		
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name", "morpheus");
		requestParams.put("job", "zion resident");
		
		httpRequest.header("Content-type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		Response response=httpRequest.put("/2");
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		ResponseBody body=response.getBody();
		UsersDataUpdated responseJsonBody=body.as(UsersDataUpdated.class);
		
		String name=response.jsonPath().get("name");
		Assert.assertEquals(name, "morpheus");
		
		String job=response.jsonPath().get("job");
		Assert.assertEquals(job, "zion resident");
}
	@Test
	void patchRequest() {
		
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name", "morpheus");
		requestParams.put("job", "zion resident");
		
		httpRequest.header("Content-type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		Response response=httpRequest.patch("/2");
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		ResponseBody body=response.getBody();
		UsersDataUpdated responseJsonBody=body.as(UsersDataUpdated.class);
		
		String name=response.jsonPath().get("name");
		Assert.assertEquals(name, "morpheus");
		
		String job=response.jsonPath().get("job");
		Assert.assertEquals(job, "zion resident");
	
		}
	@Test
	void deleteRequest() {
		
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestParams=new JSONObject();
		
		httpRequest.header("Content-type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		Response response=httpRequest.delete("/2");
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is "+statusCode);
		Assert.assertEquals(statusCode, 204);
	}
	
}
