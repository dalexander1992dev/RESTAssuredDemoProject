import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.resources;
import files.payLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics3 {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis = new FileInputStream("/Users/dartavia/Desktop/REST API Testing Course/REST API Testing Project/src/files/environment.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}

	
	/**
	 * Delete API sample test
	 */
	@Test
	public void deleteAPI() {
		
		//BaseURL or Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response responseData = given().
			queryParam("key",prop.getProperty("KEY")).
			body(payLoad.getPostData()).
			when()
			.post(resources.placePostData()).
		    //Status code of the response
		    then().assertThat().statusCode(200).and().
		    //Content type 
		    contentType(ContentType.JSON).and(). 
		    //Assert the server status output
		    body("status",equalTo("OK")).
		    //Extract the response 
		    extract().response();
			
			//Cast the response to a String
			String responseDataString = responseData.asString();
			System.out.println("Delete API - Response Data: "+responseDataString);
			
			
			//Convert the string response to a JsonPath format
			JsonPath jsonPath = new JsonPath(responseDataString);
			
			//Extract the place_id value from the response data
			String placeIdValue = jsonPath.get("place_id");
			System.out.println("Delete API - Place Id Value: "+placeIdValue);
			
			//Delete request
			given().
		       queryParam("key",prop.getProperty("KEY")).
		       //sending the place_id value to the delete API
		       body("{\n" + 
		       		"    \"place_id\": \""+placeIdValue+"\""+ 
		       		"}").
		       when().
		       post("maps/api/place/delete/json").
		       then().assertThat().statusCode(200).and().
			    //Content type 
			    contentType(ContentType.JSON).and(). 
			    //Assert the server status output
			    body("status",equalTo("OK"));
	}
}
