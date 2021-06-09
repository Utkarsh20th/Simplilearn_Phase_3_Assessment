package Project.Simplilearn_Phase_3_Assessment;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_adding_a_user {
	
	RequestSpecification request = null;
	//String responseBody=null;
	Response responseBody=null;
	
	private static Logger logger = LogManager.getLogger(GET_list_of_users.class.getName());
	
	@BeforeClass
	public void beforeClass() {
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		logger.info("*************** Inside Before Class *******************");
		
		logger.info("*************** Giving base URL *******************");
		RestAssured.baseURI = "https://reqres.in";
		request = RestAssured.given();
		
	}	
	
  @Test (priority=1)
  public void AddAUser() {
	  
	    logger.info("*************** Giving data in the request body *******************");
	    String data = "{\"name\": \"Simplilearn_Phase_3_Assessment\",\"job\": \"Student\"}";
		responseBody = (Response) request
									 .body(data)
									 .post("/api/users")
									 .getBody();
		
  }
  
  @Test (priority=2)
  public void ValidationMethod() {
	  
		Headers allHeaders = responseBody.headers();		   
		// Iterate over all the Headers
		for(Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " ---- Value: " + header.getValue());
		}
	  
	    int statusCode = responseBody.getStatusCode();
	    logger.info("*************** Extracting and asserting the HTTP Status Code received from the server *******************");
		System.out.println("\"Status Code\" is => "+statusCode);
	    // Assert that correct status code is returned
		Assert.assertEquals(statusCode /*actual value*/, 201 /*expected value*/, "Correct status code returned");
		// Logging the message to console
	  
  }
  
}
