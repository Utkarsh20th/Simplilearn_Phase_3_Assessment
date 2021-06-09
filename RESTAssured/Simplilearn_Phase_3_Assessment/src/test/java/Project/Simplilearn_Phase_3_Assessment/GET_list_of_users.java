package Project.Simplilearn_Phase_3_Assessment;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_list_of_users {
	
	private static Logger logger = LogManager.getLogger(GET_list_of_users.class.getName());
	
	@BeforeClass
	public void beforeClass() {
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		logger.info("*************** Inside Before Class *******************");
		
	}
	
	
  @Test
  public void GetListOfUsers() {
	  
	    //BasicConfigurator.configure();
		//DOMConfigurator.configure("log4j.xml");
		// Logging the message to console
	    logger.info("*************** Inside Before Class *******************");
		logger.info("Hello!!!!  This is my Phase 3 assessment project");    
		
		
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://reqres.in/api";
		// Logging the message to console
		logger.info("STEP 1: assign base URI");
		
		
		
		// Get the RequestSpecification of the request that you want to sent to the server. The server is specified by the BaseURI that we have specified in the above step
		RequestSpecification httpRequest = RestAssured.given();
		// Logging the message to console
		logger.info("STEP 2: forming request specification");
		
		
		
		// Make a request to the server by specifying the method Type and the method URL. This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/users?page=2");
		// Logging the message to console
		logger.info("STEP 3: defining type of HTTP Method (here we are giving GET)");
		logger.info("STEP 4: also giving the path to the required URI");
		
		
		
		Headers allHeaders = response.headers();		   
		// Iterate over all the Headers
		for(Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " ---- Value: " + header.getValue());
		}
		
		
		
		// Now let us print the status code of the message we have received from the server
		int statusCode = response.getStatusCode();
		System.out.println("\"Status Code\" is => "+statusCode);
	    // Assert that correct status code is returned
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		// Logging the message to console
		logger.info("STEP 5: extracting and asserting the HTTP Status Code received from the server");
		
		
		
		// Now let us print the status line of the message we have received from the server
		String statusLine = response.getStatusLine();
		System.out.println("\"Status Line\" is => "+statusLine);
		// Logging the message to console
		logger.info("STEP 6: extracting and asserting the HTTP Status Line received from the server");
		
		
		
		// Now let us print the response body of the message we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("\"Response Body\" is =>  "+responseBody);
		// Assert that [ User : Rachel ] is returned
		Assert.assertEquals(responseBody.contains("Rachel") /*expected value*/, true /*Actual Value*/);
		// Logging the message to console
		logger.info("STEP 7: extracting the HTTP response received from the server");
	  
  }
  
}
