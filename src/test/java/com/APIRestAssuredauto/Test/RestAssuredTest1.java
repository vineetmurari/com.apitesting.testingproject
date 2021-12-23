package com.APIRestAssuredauto.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class RestAssuredTest1 {
	
	public static String URL ="PROD_URL.com";
	
	
	@BeforeTest
	@Parameters({"ENV"})
	public void test_before(String env){
		
		if(env.equals("QA")){
			URL = "QA.com";
		}
		else if(env.equals("DEV")){
			URL ="DEV.com";
		}
	}
	
	
		
  @Test(dataProvider="test1")
  public void f(String param1, String param2) {
	  
	  //TESTs
	 
	  baseURI="https://reqres.in";
	  
	  given().
	  	header("content-type","application/json").
	  	queryParam("page", param2).
	  	pathParam("pathparam1",param1 ).
	  when().
      	get("/api/{pathparam1}").
      then().assertThat().
      	statusCode(200). and().
      	body("data.findAll { it.id < 9 }.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"));
	  
	  
	  //String var - String response body Json Reponse and Response var - complete response and status code and text in status in string or int datatypes
	  
	 int statuscode= given().
	  	header("content-type","application/json").
	  	queryParam("page", "2").
	  	pathParam("pathparam1", "users").
	  when().
    	get("/api/{pathparam1}").
    	then().extract().statusCode();
	 
	 	Assert.assertEquals(statuscode, 200);
	 	
	 	String status_LINE= given().
	 		  	header("content-type","application/json").
	 		  	queryParam("page", "2").
	 		  	pathParam("pathparam1", "users").
	 		  when().
	 	    	get("/api/{pathparam1}").
	 	    	then().extract().statusLine();
	 	
	 	Assert.assertTrue(status_LINE.contains("OK"));
	 	
	 	String Response = given().
	 		  	header("content-type","application/json").
	 		  	queryParam("page", "2").
	 		  	pathParam("pathparam1", "users").
	 		  when().
	 	    	get("/api/{pathparam1}").
	 	    	then().extract().body().asString();
	 	
	 	System.out.println(Response); 
	 	
	 	String exp ="{\"id\":7,\"email\":\"michael.lawson@reqres.in\",\"first_name\":\"Michael\",\"last_name\":\"Lawson\",\"avatar\":\"https://reqres.in/img/faces/7-image.jpg\"}";
	 	
	 	if(Response.contains(exp)){
	 		System.out.println("PASS");
	 	}
	 	else{
	 		System.out.println("FAIL");
	 	}
	 	
	 	Response resp = given().
	 		  	header("content-type","application/json").
	 		  	queryParam("page", "2").
	 		  	pathParam("pathparam1", "users").
	 		  when().
	 	    	get("/api/{pathparam1}").
	 	    	then().extract().response();
	 	
	 	String pagecount =resp.jsonPath().getString("page");
	 	
	 	
	 	given().
		  	header("content-type","application/json").
		  	pathParam("pathparam1", "users").
		  	pathParam("pagecount", pagecount).
		  when().
	    	get("/api/{pathparam1}/{pagecount}").
	    	then().statusCode(200);
	 	
	 	// JSONPATH
	 	
	 	String json_data ="{\n" + 
	 			"    \"data\": {\n" + 
	 			"        \"id\": 2,\n" + 
	 			"        \"email\": \"janet.weaver@reqres.in\",\n" + 
	 			"        \"first_name\": \"Janet\",\n" + 
	 			"        \"last_name\": \"Weaver\",\n" + 
	 			"        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" + 
	 			"    },\n" + 
	 			"    \"support\": {\n" + 
	 			"        \"url\": \"https://reqres.in/#support-heading\",\n" + 
	 			"        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" + 
	 			"    }\n" + 
	 			"}";
	 	
	 	System.out.println(json_data);
	 	
	 	JsonPath js1 = new JsonPath(Response);
	 	
	 	List<String> emails = js1.get("data.email");
	 	
	 	System.out.println(emails);
	 	
	 	File f = new File("C:\\Users\\Dell\\Desktop\\API_Test_Suite\\RestAssured.json");
	 	
	 	JsonPath js2 = new JsonPath(f);
	 	
	 	System.out.println("******************************************************************************");
	 	
	 	System.out.println(js2.getString("data.email"));
	 	
	 	
	 	String json_data1 ="{\n" + 
	 			"    \"data\": {\n" + 
	 			"        \"id\": 2,\n" + 
	 			"        \"email\": \"janet.weaver@reqres.in\",\n" + 
	 			"        \"first_name\": \"Janet\",\n" + 
	 			"        \"last_name\": \"Weaver\",\n" + 
	 			"        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" + 
	 			"    },\n" + 
	 			"    \"support\": {\n" + 
	 			"        \"url\": \"https://reqres.in/#support-heading\",\n" + 
	 			"        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" + 
	 			"    }\n" + 
	 			"}";
	 	
	 	JsonPath js3 = new JsonPath(json_data1);
	 	
	 	System.out.println(js3.get("data.email"));
	 	
	 //POST
	 	
	 	String payload ="{\n" + 
	 			"    \"name\": \"morpheus\",\n" + 
	 			"    \"job\": \"leader\"\n" + 
	 			"}";
	 	
	 given().
		  	header("content-type","application/json").
		  	pathParam("pathparam1", "users").
		  	body(payload).
	when().
	    	post("/api/{pathparam1}").
	then().
			statusCode(201).
			body("name", is("morpheus"),"job",is("leader"));
	 
	 
	 //put
	 String payload_update = "{\n" + 
	 		"    \"name\": \"morpheus\",\n" + 
	 		"    \"job\": \"zion resident\"\n" + 
	 		"}";
	 
	 given().
	  	header("content-type","application/json").
	  	pathParam("pathparam1", "users").
	  	pathParam("pathparam2", "2").
	  	body(payload_update).
	when().
 		put("/api/{pathparam1}/{pathparam2}").
 	then().
		statusCode(200).
		body("name", is("morpheus"),"job",is("zion resident"));
	 
	 //delete
	 given().
	  	header("content-type","application/json").
	  	pathParam("pathparam1", "users").
	  	pathParam("pathparam2", "2").
	when().
		delete("/api/{pathparam1}/{pathparam2}").
	then().
		statusCode(204);
	 
	 
	 //Serialization 
	 
	 
	 System.out.println("**********************SERIALIZATION*****************");
	 
	 CreateUser user = new CreateUser("morpheus", "leader");
	 
	 	given().
	  		header("content-type","application/json").
	  		pathParam("pathparam1", "users").
	  		body(user).
	  	when().
	  		post("/api/{pathparam1}").
	  	then().log().all().
	  		statusCode(201).
	  		body("name", is("morpheus"),"job",is("leader"));
	 
	 System.out.println("*****************DESERIALIZATION*******************");
	 
	 SingleUser singleuser= given().
	  	header("content-type","application/json").
	  	pathParam("pathparam1", "users").
	  	pathParam("pathparam2", "2").
	  when().
    	get("/api/{pathparam1}/{pathparam2}").
    then().log().all().
    	statusCode(200).extract().as(SingleUser.class);
	 
	 
	 System.out.println("THIS IS URL FROM OUR POJO CLASS - "+singleuser.getSupport().getUrl());
	  
	  
  }
  
  @DataProvider(name = "test1")
  public Object[][] createData1() {
   return new Object[][] {
     { "users", "1" },
     { "users", "2"},
   };
  }


  
}
