package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;

import bdd.spojo.Data;
import bdd.spojo.Location;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResourse;
import resources.TestData;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification givenbody;
	Response response;
	JsonPath js;
	TestData data = new TestData();
	static String  placeid;
	
	@Given("given the Payload {string} {string} {string}")
	public void given_the_payload(String name, String address, String language) throws IOException  {
		 givenbody = given().spec(requestSpec()).body(data.addPlaceData(name, address, language));
	    
	}
	@When("the path parameter is {string} and the request is {string} type")
	public void the_path_parameter_is_and_the_request_is_type(String resourse, String method) {
		//constructor will be called using the valueof
		ApiResourse apiResourse = ApiResourse.valueOf(resourse);
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		if(method.equalsIgnoreCase("POST")) 
		{	
		response = givenbody.when().post(apiResourse.getResource());
		}
		else if (method.equalsIgnoreCase("GET")) 
		{
			response = givenbody.when().get(apiResourse.getResource());
		}
	}
	@Then("the status code is {int}")
	public void the_is(int int1) {
	    assertEquals(response.getStatusCode(), int1);
	}
	@Then("the {string} is {string}")
	public void the_is(String string, String string2) {
		assertEquals(JsonPathData(response, string), string2);
	    
	}
	
	@Then("Verify the place_id created maps to the {string} using the {string}")
	public void verify_the_place_id_created_maps_to_the_using_the(String actualname, String resourse) throws IOException {
		placeid = JsonPathData(response, "place_id");
		givenbody = given().spec(requestSpec()).queryParam("place_id", JsonPathData(response, "place_id"));
	    the_path_parameter_is_and_the_request_is_type(resourse, "GET");
	    System.out.println(JsonPathData(response, "name"));
	    String expectedName = JsonPathData(response, "name");
	    assertEquals(expectedName, actualname);
	}
	
	@Given("givent the payload to delete the place")
	public void givent_the_payload_to_delete_the_place() throws IOException {
	    givenbody = given().spec(requestSpec()).body(data.getDeletePayload(placeid));
	}
}
