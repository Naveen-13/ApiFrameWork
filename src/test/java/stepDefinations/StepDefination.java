package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import resources.TestData;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification givenbody;
	Response response;
	TestData data = new TestData();
	
	@Given("given the Payload {string} {string} {string}")
	public void given_the_payload(String name, String address, String language) throws IOException  {
		 givenbody = given().spec(requestSpec()).body(data.addPlaceData(name, address, language));
	    
	}
	@When("the request is post type")
	public void the_request_is_post_type() {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
		response = givenbody.when().post("/maps/api/place/add/json")
		.then().extract().response();
	}
	@Then("the status code is {int}")
	public void the_is(int int1) {
	    assertEquals(response.getStatusCode(), int1);
	}
	@Then("the {string} is {string}")
	public void the_is(String string, String string2) {
		String responseAsString = response.asString();
		JsonPath js = new JsonPath(responseAsString);
		assertEquals(js.get(string), string2);
	    
	}
}
