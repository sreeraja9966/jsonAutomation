package sysnik;

import java.awt.print.Book;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTesting {
	@Test

	
		public void RegistrationSuccessful()
		{		
			//RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RestAssured.baseURI ="https://api.jsonbin.io/b/5c5ebe37e9e7c118390f9ef0";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			/*requestParams.put("FirstName", "Virender"); // Cast
			requestParams.put("LastName", "Singh");
			requestParams.put("UserName", "sdimpleuser2dd2011");
			requestParams.put("Password", "password1");
			requestParams.put("Email",  "sample2ee26d9@gmail.com");
			request.body(requestParams.toJSONString());*/
			Response response = request.post("/register");

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			Assert.assertEquals(statusCode, 200);
			String successCode = response.jsonPath().get("SuccessCode");
			//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
		}
}
