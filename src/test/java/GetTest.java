import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GetTest {
	
	@Test
	void getBookFromID() {
		
		//Specify base URI
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Authors/authors/books";
		
		//Request Object
		RequestSpecification httprequest = RestAssured.given();
		
		//Response Object
		Response response = httprequest.request(Method.GET,"/22");
		
		//Print response
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		//Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//Capture details of header from response
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8; v=1.0");
		
		String server = response.header("Server");
		Assert.assertEquals(server, "Kestrel");
		
		//printing all the headers
		
		Headers headers = response.getHeaders(); //capture all headers from response
		for(Header hd : headers) {
			System.out.println("Header name = " + hd.getName()); //header name
			System.out.print(" value = " + hd.getValue()); //header value
			System.out.println();
		};
		
	}
	

}
