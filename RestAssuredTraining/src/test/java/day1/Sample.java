package day1;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import requestbody.json;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Sample {

	public static void main(String[] args) {

		String response= given().log().all().header("Content-Type", "application/json")
				.body(json.jsonBody())

				.when().post("https://rahulshettyacademy.com/Library/Addbook.php")

				.then().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added"))
				.body("ID", equalTo("7870888")).extract().response().asString();
		
//	System.out.println(response);
		
		JsonPath js= new JsonPath(response);
		String Id= js.getString("ID");
		
		System.out.println(Id);
		
		//For update
		
		//given().header("Content-Type", "application/json").body(null)
		

	}

}
