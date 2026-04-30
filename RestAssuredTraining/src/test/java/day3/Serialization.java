package day3;

import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import requestbody.json;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Serialization {
	
	@Test
	public void testSerialization() {
		
		User data=new User();
		data.setName("John");
		data.setJob("QA Engineer");
		
		given().contentType("application/json")
		.body(data); // serailization happens in this part. 
		//.when()
		
	}
	@Test
	public void testDeserialization() {
		Response response= given()
				.when().get();
		
		User data= response.as(User.class);
		
		System.out.println(data.getName());
		System.out.println(data.getJob());
		
	}

	

}
