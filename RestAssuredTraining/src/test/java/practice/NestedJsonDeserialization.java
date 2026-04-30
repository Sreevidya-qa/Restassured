package practice;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import requestbody.json;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class NestedJsonDeserialization {

	public static void main(String[] args) {
		
		Response response= given()
				.contentType("application/json")
				.when().get("https://example.com/api/user/101");
		
		ResponseBody res= response.as(ResponseBody.class);
		
		System.out.println(res.getStatus());
				
	}

}
