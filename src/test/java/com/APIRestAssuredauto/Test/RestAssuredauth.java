package com.APIRestAssuredauto.Test;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.net.URL;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class RestAssuredauth {
  @Test
  public void f() throws InterruptedException {
	  
	  //BASIC
	  
	  
	  baseURI="http://localhost:3000";
	  
	  given().
	  	header("content-type","application/json").
	  	header("authorization","S2luZzpRdWVlbg==").
	  when().
      	get("/api/get/items").
      then().
      	statusCode(200);
	  
	  
	  
	  //API key
	  given().
	  	header("content-type","application/json").
	  	header("authorization","ThisissampleAPIKEYfromDB").
	  when().
    	get("/api/get/items").
    then().
    	statusCode(200);
	  
	  
	  //Oauth
	  
//	  GetToken obj = new GetToken();
//	  String access_token = obj.get_github_access_token();
//	  
//	  given().
//	  	header("content-type","application/json").
//	  	header("authorization",access_token).
//	  when().
//  		get("/api/get/items").
//  	  then()
//  	  	.log().all().
//  		statusCode(200);
	  
	  
	  //Schema validation
	  
	  
	  given().
	  	header("content-type","application/json").
	  	header("authorization","ThisissampleAPIKEYfromDB").
	  when().
    	get("/api/get/items").
    then().
    	statusCode(200).body(matchesJsonSchemaInClasspath("JsonRespSchema.json"));
	  
	  
	  
	  
	  // REQUEST SPEC and RESPONSE SPEC
	  
	  String Expected_Json ="{\"Item1\":\"Red Shirt\",\"Item2\":\"Blue Shirt\",\"Item3\":\"Green Shirt\"}";
	  
	  
	  RequestSpecBuilder builder = new RequestSpecBuilder();
	  builder.addHeader("content-type", "application/json");
	  builder.addPathParam("param", "items");
	  builder.addPathParam("param2", "get");
	  RequestSpecification requestSpec = builder.build();
	  
	  
	  ResponseSpecBuilder builder1 = new ResponseSpecBuilder();
	  builder1.expectStatusCode(200);
	  builder1.expectBody(Matchers.equalTo(Expected_Json));
	  ResponseSpecification responseSpec = builder1.build();
	  
	  
	  //REQ 1 with the API key
	  given().
	  		spec(requestSpec).
	  		header("authorization","ThisissampleAPIKEYfromDB").
	  when().
  			get("/api/{param2}/{param}").
  	  then().spec(responseSpec);
  			
	  
	  //REQ 2 with the BASE 64 Encoded key
	  
	  given().
	  		spec(requestSpec).
	  		header("authorization","S2luZzpRdWVlbg==").
	  when().
    		get("/api/{param2}/{param}").
      then().spec(responseSpec);
	   
  }
}
