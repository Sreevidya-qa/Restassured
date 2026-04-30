package day1;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import requestbody.json;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Deserialization {

	public static void main(String[] args) {
		
		RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
	
		
		
		Response response = given().spec(reqspec)
				.when().get("/Library/GetBook.php?ID=78955555");
		
		List<BookDetails> details= response.as(new TypeRef<List<BookDetails>>() {});
		
	System.out.println(details.get(0).getAuthor());
	System.out.println(details.get(0).getBook_name());

	}

}
