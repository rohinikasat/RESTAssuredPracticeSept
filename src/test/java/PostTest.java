import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PostTest {
	
	@Test
	void postAuthor() {
		
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
		
		RequestSpecification httprequest = RestAssured.given();
		
		//Request payload sending along with post request
		JSONObject params = new JSONObject();
		
		params.put("id", "0");
		params.put("idBook", "0");
		params.put("firstName", "JhingaLaLa");
		params.put("lastName", "HuHu");
		
		httprequest.header("Content-type","application/json");
		
		httprequest.body(params.toJSONString()); //attach above data to the request
		
		Response response = httprequest.request(Method.POST,"/Authors");
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);
		
		int statuscode = response.getStatusCode();
		System.out.println("status code is: " + statuscode);
		Assert.assertEquals(statuscode, 200);
		
		String firstname = response.jsonPath().get("firstName");
		System.out.println("FirstName is: " + firstname);
		Assert.assertEquals(firstname, "JhingaLaLa");
	}

}
