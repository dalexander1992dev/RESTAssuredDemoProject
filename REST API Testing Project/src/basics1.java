import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class basics1 {

/**
 * Get API sample test
 */
@Test
public void getAPI(){

	//BaseURL or Host
	RestAssured.baseURI="https://maps.googleapis.com";
		
	given().
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
       header("Server","scaffolding on HTTPServer2");
	}
}
