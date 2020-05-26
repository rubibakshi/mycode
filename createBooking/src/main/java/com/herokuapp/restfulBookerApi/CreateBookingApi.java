package com.herokuapp.restfulBookerApi;

import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.json.JSONObject;
import org.testng.log4testng.Logger;

import com.utils.RestAssuredUtility;

public class CreateBookingApi {
	Logger logger = Logger.getLogger(CreateBookingApi.class);
	Properties herokuAppData;
	
	public  CreateBookingApi() throws IOException {
		herokuAppData =  new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		herokuAppData.load(fileInputStream);
	   
	}
	
	//public Response getCreateBookingApiResponse(JSONObject payLoad) throws IOException{
	 public Response getCreateBookingApiResponse() throws IOException{	
			
		String url = herokuAppData.getProperty("baseUrl")+"/booking";
		
		
		Map<String, String> headersMap = new HashMap<String, String>();//public Response post(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> params, JSONObject payload){
		headersMap.put("Content-Type", "application/json");		
		
		JsonObject payLoad = prepareJsonObject();
		
		RestAssuredUtility restUtility = new RestAssuredUtility();
		Response response = restUtility.post(url, headersMap, null, null, payLoad);
		System.out.println("printing response");
		return response;
	}
	
	public  JsonObject prepareJsonObject() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("firstname", herokuAppData.getProperty("firstName"));
		jsonObjectBuilder.add("lastname", herokuAppData.getProperty("lastName"));
		jsonObjectBuilder.add("totalprice", herokuAppData.getProperty("totalPrice"));
		jsonObjectBuilder.add("additionalneeds", herokuAppData.getProperty("additionalNeeds"));
		jsonObjectBuilder.add("depositpaid", herokuAppData.getProperty("depositPaid"));
		//jsonObjectBuilder.add("additionalneeds", herokuAppData.getProperty("additionalNeeds"));
		
		JsonObjectBuilder bookingDatesObject =Json.createObjectBuilder();
		bookingDatesObject.add("checkin", herokuAppData.getProperty("checkInDate"));
		bookingDatesObject.add("checkout", herokuAppData.getProperty("checkOutDate"));
		
		jsonObjectBuilder.add("bookingdates", bookingDatesObject);
		
		
		JsonObject jsonObject = jsonObjectBuilder.build();
		
		System.out.println("printing object");
		return jsonObject;
		
	}
	
}