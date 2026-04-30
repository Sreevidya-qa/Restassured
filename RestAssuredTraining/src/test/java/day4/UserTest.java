package day4;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import requestbody.json;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;

public class UserTest {

	@Test
	
	public void testGetUser() {
		
		Response res= UserServices.getUser(2);
		
		res.then().statusCode(200)
		.body("data.id", equalTo(2));
	}
}
