package com.herokuapp.restfulBookerApi;

import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.log4j.Logger;

import com.utils.RestAssuredUtility;

public class UpdateBookingApi {
	Logger logger = Logger.getLogger(UpdateBookingApi.class);
	Properties herokuAppData;
	
	
	public  UpdateBookingApi() throws IOException {
		herokuAppData = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/apiConfig/herokuapp.properties");
		herokuAppData.load(fileInputStream);	
	}
	
	public Response putUpdateBookApiResponse(String paramId, String cookies) {
		
		String url = herokuAppData.getProperty("baseUrl")+"/booking/";
		
		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
//		headersMap.put("Authorisation", herokuAppData.getProperty("Authorisation"));
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", paramId);
		
		// create cookies map
		Map<String, String> cookiesMap = new HashMap<String, String>();
		cookiesMap.put("token", cookies);
		
		JsonObject payload = prepareJsonObject();
		
		RestAssuredUtility restUtility = new RestAssuredUtility();
		Response response = restUtility.put(url, headersMap, cookiesMap, parameters, payload);
		return response;
		
	}
	
	//this method creates the json object to pass as api payload
	public  JsonObject prepareJsonObject() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("firstname", herokuAppData.getProperty("firstName"));
		jsonObjectBuilder.add("lastname", herokuAppData.getProperty("UpdateLastName"));
		jsonObjectBuilder.add("totalprice", herokuAppData.getProperty("totalPrice"));
		jsonObjectBuilder.add("additionalneeds",herokuAppData.getProperty("additionalNeeds"));
		jsonObjectBuilder.add("depositpaid", Boolean.parseBoolean(herokuAppData.getProperty("depositpaid")));
		
		JsonObjectBuilder bookingDatesObject =Json.createObjectBuilder();
		bookingDatesObject.add("checkin", herokuAppData.getProperty("checkInDate"));
		bookingDatesObject.add("checkout", herokuAppData.getProperty("checkOutDate"));
		
		jsonObjectBuilder.add("bookingdates", bookingDatesObject);
		
		JsonObject jsonObject = jsonObjectBuilder.build();
		return jsonObject;
		
		
		
		
		
		
	}
	

}
