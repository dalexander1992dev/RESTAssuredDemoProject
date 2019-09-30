import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson {

	@Test
	public void addBook() throws IOException{
		
		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().
			header("Content-Type", "application/json").
			body(GenerateStringFromResource("/Users/dartavia/Desktop/REST API Testing Course/AddBookDetails.json")).
			when().
			post("/Library/Addbook.php").
			then().assertThat().statusCode(200).and()
		    .contentType(ContentType.JSON).and() 
		    .extract().response();

		JsonPath js = reusableMethods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
	}

	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
