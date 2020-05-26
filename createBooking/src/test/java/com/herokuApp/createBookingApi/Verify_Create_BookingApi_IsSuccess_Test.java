package com.herokuApp.createBookingApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import apipayload.createbookingapidto.BookingPayload;
import apipayload.createbookingapidto.Bookingdates;

import com.herokuapp.restfulBookerApi.CreateBookingApi;

public class Verify_Create_BookingApi_IsSuccess_Test {
	
	@Test
	public void triggeringCreateBookingWithSuccess() throws IOException {
		CreateBookingApi createBooking = new CreateBookingApi();
		Properties prop=new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		prop.load(fileInputStream);
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		Bookingdates bookingdates = new Bookingdates();
		BookingPayload bookingPayload =new BookingPayload();
		
		bookingdates.setCheckin(prop.getProperty("checkInDate"));
		bookingdates.setCheckout(prop.getProperty("checkOutDate"));
		
		bookingPayload.setFirstname(prop.getProperty("firstName"));
		bookingPayload.setLastname(prop.getProperty("lastName"));
		bookingPayload.setAdditionalneeds(prop.getProperty("additionalNeeds"));
		bookingPayload.setDepositpaid(Boolean.parseBoolean(prop.getProperty("depositPaid")));
		bookingPayload.setTotalprice(Integer.parseInt(prop.getProperty("totalPrice")));
		bookingPayload.setBookingdates(bookingdates);
		
		String json = mapper.writeValueAsString(bookingPayload); 
		
		
		//Response responseBody = createBooking.getCreateBookingApiResponse(new JSONObject(json));
		Response responseBody = createBooking.getCreateBookingApiResponse();
		Assert.assertTrue(responseBody.getStatusCode()==200,"Api response is not 200");
		
		JsonPath jsonPath = new JsonPath(responseBody.asString());
		int bookingId = jsonPath.getInt("bookingid");
		System.out.println("booking id is:" + bookingId);
		
		
	}
	
}
	
 
