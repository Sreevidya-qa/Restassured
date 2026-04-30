package day1;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import requestbody.json;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SpecBuilder {

	public static void main(String[] args) {
		
		BookDetails data=new BookDetails();
		data.setBook_name("Science WorkBook");
		data.setIsbn("3077");
		data.setAisle("5555");
		data.setAuthor("Prarthana");
		
		RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
			
		
		given().log().all().spec(reqspec).body(data)
		
		.when().post("/Library/Addbook.php");
		
		//.then().log().all().statusCode(200).assertThat().body("Msg", equalTo("successfully added"));
	
	
		

	}

}
