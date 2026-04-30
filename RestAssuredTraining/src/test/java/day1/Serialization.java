package day1;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import requestbody.json;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Serialization {

	public static void main(String[] args) {
		
		BookDetails data=new BookDetails();
		data.setBook_name("Science WorkBook");
		data.setIsbn("7895");
		data.setAisle("5555");
		data.setAuthor("Prarthana");
		
		given().log().all().contentType("application/json").body(data)
		
		.when().post("https://rahulshettyacademy.com/Library/Addbook.php")
		
		.then().log().all().statusCode(200).assertThat().body("Msg", equalTo("successfully added"))
		.body("ID",equalTo("78955555"));
	
		

	}

}
