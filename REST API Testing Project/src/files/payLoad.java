package files;

public class payLoad {
	
	public static String getPostData() {
		
		String bodyString = "{\n" + 
				"    \"location\":{\n" + 
				"    	 \"lat\" : -38.383494,\n" + 
				"        \"lng\" : 33.427362\n" + 
				"    },\n" + 
				"    \"accuracy\":50,\n" + 
				"    \"name\":\"Frontline house\",\n" + 
				"    \"phone_number\":\"(+91) 983 893 3937\",\n" + 
				"    \"address\" : \"29, side layout, cohen 09\",\n" + 
				"    \"types\": [\"shoe park\",\"shop\"],\n" + 
				"    \"website\" : \"http://google.com\",\n" + 
				"    \"language\" : \"French-IN\"\n" + 
			   "}"; 
		return bodyString;

	}
	
	public static String addBook(String aisleParam, String isbnParam) {
		
			String bodyString = "{" +
                "\"name\":\"Learn Davids book\"," +
                "\"isbn\":\""+isbnParam+"\"," +
                "\"aisle\":\""+aisleParam+"\"," +
                "\"author\":\"David Her\"" +
                "}"; 
			return bodyString;
			
		}
	
	public static String deleteBook(String isbnParam) {
		
		String bodyString = "{" +
            "\"ID\":\""+isbnParam+"\"," +
            "}"; 
		return bodyString;
		
	}

}
