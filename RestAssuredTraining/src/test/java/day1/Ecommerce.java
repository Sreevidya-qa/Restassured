package day1;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.Order;
import pojo.OrdersDetails;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Ecommerce {

	public static void main(String[] args) {
		
	RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
	
	LoginRequest data= new LoginRequest();
	data.setUserEmail("sylvia@gmail.com");
	data.setUserPassword("Sylvia@123");
	
	RequestSpecification reqLogin= given().log().all().spec(req).body(data);
	
	
	LoginResponse response = reqLogin.when().post("/api/ecom/auth/login")
			.then().log().all().extract().response().as(LoginResponse.class);
	
	String token= response.getToken();
			System.out.println(token);
	String userId=	response.getUserId();	
			System.out.println(userId);
	System.out.println(response.getMessage());
	
	
	//Create a product
	
	RequestSpecification createProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token)
			.build();
	RequestSpecification createProductRequest= given().log().all().spec(createProductReq).param("productName", "qwerty new")
	.param("productAddedBy", "69e9c0e4f86ba51a657ee5b2")
	.params("productCategory", "fashion")
	.param("productSubCategory", "shirts")
	.param("productPrice", "17500")
	.param("productDescription", "Addias Originals ")
	.param("productFor", "women")
	.multiPart("productImage",new File ("/Users/hari/Desktop/Screenshot 2026-04-23 at 2.13.07 PM.png"));
	
String	createProductResponse= createProductRequest.when().post("/api/ecom/product/add-product")
	.then().log().all().extract().asString();
JsonPath js=new JsonPath(createProductResponse);
String productID = js.get("productId");
	
//Create an Order

RequestSpecification createOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.addHeader("Authorization", token).setContentType(ContentType.JSON).build();



OrdersDetails orderDetails= new OrdersDetails();
orderDetails.setCountry("India");
orderDetails.setProductOrderedId(productID);

List<OrdersDetails> OrdersDetailsList=new ArrayList<OrdersDetails> ();
OrdersDetailsList.add(orderDetails);

Order order= new Order();
order.setOrders(OrdersDetailsList);

RequestSpecification createOrderReq= given().log().all().spec(createOrder).body(order);
String createResponse= createOrderReq.when().post("/api/ecom/order/create-order")
.then().log().all().extract().response().asString();
System.out.println(createResponse);

//Delete order

RequestSpecification deleteOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

RequestSpecification deleteRequest= given().log().all().spec(deleteOrder).pathParam("productId",productID);

String deleteproduct= deleteRequest.when().delete("/api/ecom/product/delete-product/{productId}")
.then().log().all().extract().response().asString();

JsonPath js1= new JsonPath(deleteproduct);
Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
 

}
}
