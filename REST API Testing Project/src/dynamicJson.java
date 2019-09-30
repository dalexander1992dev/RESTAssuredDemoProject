import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;
import files.reusableMethods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class dynamicJson {
	
	/**
	 * Add book API test
	 */
	@Test(dataProvider="booksData") //Receiving the multiple array of objects to run the test
	public void addBookAPI(String aisleParam, String isbnParam) {
		//BaseURL or Host
		RestAssured.baseURI="http://216.10.245.166";
		  
		Response res=given().
		    header("Content-Type","application/json").
		    body(payLoad.addBook(aisleParam,isbnParam))
		    .when()
		    .post("/Library/Addbook.php")
		    .then().assertThat().statusCode(200).and()
		    .contentType(ContentType.JSON).and() 
		    .extract().response();

		JsonPath js=reusableMethods.rawToJson(res);
		String bookId = js.get("ID");

		System.out.println("* Book Id * " + bookId);
	}

	/**
	 * Data provider array
	 * @return
	 */
	@DataProvider(name="booksData") //Definition of data provider multiple array of objects
	public Object getBooksData() {
		return new Object [][] {
						{"abc","123"},{"cde","456"},{"fgh","789"} 
					};
	}

}
