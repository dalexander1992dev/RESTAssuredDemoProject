import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.*;

import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics4 {

/**
 * Get API sample test
 */
@Test
public void getAPI(){

	//BaseURL or Host
	RestAssured.baseURI="https://maps.googleapis.com";
		
	Response response=given().log().all().
       param("location","-33.8670522,151.1957362").
       param("radius","500").
       param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
       when().
       get("/maps/api/place/nearbysearch/json").
       //Status code of the response
       then().assertThat().statusCode(200).and().
       //Content type 
       contentType(ContentType.JSON).and(). 
       //Body
       body("results[0].name",equalTo("Sydney")).and().
       body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
       //Header responses
       header("Server","scaffolding on HTTPServer2").log().body()
       .extract().response();
	 
	String responseData = response.asString();
	System.out.println("* Response data: * " + responseData);
	
	JsonPath js=reusableMethods.rawToJson(response);
	Integer count = js.get("results.size()");
	
	System.out.println("* Result size: * " + count);
	 
	System.out.println("* Names of the result array * ");
	
	for(int i=0; i<count;i++) {
		String name = js.get("results["+i+"].name");
		System.out.println(name);
	}
 
	}
}
