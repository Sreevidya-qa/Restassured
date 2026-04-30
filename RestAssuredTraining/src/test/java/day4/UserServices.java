package day4;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserServices {
	
	public static Response getUser(int id) {
		
		return given().baseUri("https://reqres.in")
				.when()
				.get("/api/users/" +id);			
	}

	

}
