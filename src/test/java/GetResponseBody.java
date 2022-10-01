import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetResponseBody {
	
	@Test
	void getActivities() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/Activities");
		
		String reponseBody =response.getBody().asString();
		System.out.println("Body: "+ reponseBody);
		
		Assert.assertEquals(reponseBody.contains("Activity 4"), true);
		
		//Extracting individual fields from json body, jsonpath.get("Node-name")
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("title"));
		System.out.println("id is: "+ jsonpath.get("[2].id"));
		Assert.assertEquals(jsonpath.get("[2].title"), "Activity 3");
	}
	
	@Test
	void validateBody() {
		RestAssured.baseURI= "https://fakerestapi.azurewebsites.net/api/v1/Activities";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET,"/2");
		
		JsonPath jsonpath = response.jsonPath();
		
		Assert.assertEquals(jsonpath.get("title"), "Activity 2");
	}

}
