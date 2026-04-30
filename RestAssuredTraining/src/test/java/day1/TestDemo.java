package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

//given will include all prerequet script like header, cookies,content
//when() we will give specific content like get put post
//then() all validation will be done here

public class TestDemo {
	
	@Test (priority=1)
	void getUser() {
		given()
		
		
		.when()//request part is fiven in when that is the url and the which http method
		.get("https://jsonplaceholder.typicode.com/posts/1")
		
		.then()
		.statusCode(200)
		.body("id",equalTo(1))
		.body("userId", equalTo(1))
		.body("title", notNullValue())
		.body("body", notNullValue())
		.log().all();//this method is used to print it in the console
		
		//HashMap<keytype,valuetype> map= new HashMap<>();
	}
	@Test (priority=2)
	void createUser() {
		HashMap<String,Object> data= new HashMap();
		data.put("title", "API TESTING");
		data.put("userId", 3);
		data.put("body", "learning restassured");
		
		given()
		.header("content-type","application/json")
		.body(data)
		
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		
		.then()
		.statusCode(201)
		
		.body("userId", equalTo(3))
		.body("title", equalTo("API TESTING"))
		.body("body", equalTo("learning restassured"))
		.log().all();
		
		
	}
	@Test(priority=3)
	void updateUser() {
		
		HashMap<String,Object> data= new HashMap();
		data.put("title", "API TESTING Class");
		data.put("userId", 3);
		data.put("body", "learning restassured from tutor");
		
		given()
		.header("content-type","application/json")
		.body(data)
		
		.when()
		.put("https://jsonplaceholder.typicode.com/posts/1")
		
		.then()
		//.statusCode(500)
		.statusCode((anyOf(is(200),is(201))))
		.body("userId", equalTo(3))
		.body("title", equalTo("API TESTING Class"))
		.body("body", equalTo("learning restassured from tutor"))
		.log().all();
	}
	@Test(priority=4)
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://jsonplaceholder.typicode.com/posts/101")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
