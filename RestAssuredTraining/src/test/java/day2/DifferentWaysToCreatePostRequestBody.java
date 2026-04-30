package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class DifferentWaysToCreatePostRequestBody {

	@Test
	void testPostusingHashmap() {
		
//Inputting data with hashmap
	/*	HashMap data = new HashMap();
		data.put("name", "anu");
		data.put("age", 13);
		data.put("grade", "A");
		String courses[] = { "Math", "Physics", "Hindi" };
		data.put("subjects", courses);*/
		
		PojoDemo data=new PojoDemo();
		data.setName("anu");
		data.setAge(13);
		data.setGrade("A");
		String courses[] = {"Math", "Physics", "Hindi" };
		data.setCourses(courses);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then().statusCode(201)

				.body("name", equalTo("anu")).body("age", equalTo(13)).body("grade", equalTo("A"))
				.body("courses", notNullValue()).log().all();

	}

}
