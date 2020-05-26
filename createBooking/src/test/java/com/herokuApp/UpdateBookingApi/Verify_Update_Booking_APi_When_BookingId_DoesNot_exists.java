package com.herokuApp.UpdateBookingApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.herokuapp.restfulBookerApi.CreateBookingApi;
import com.herokuapp.restfulBookerApi.UpdateBookingApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;






public class Verify_Update_Booking_APi_When_BookingId_DoesNot_exists {
	Logger logger = Logger.getLogger(Verify_Update_Booking_APi_When_BookingId_DoesNot_exists.class);
	Properties herpokuAppData;
	
	
	@Test
	public void verify_update_booking_api_with_success() throws IOException{
		UpdateBookingApi updateBooking = new UpdateBookingApi();
		herpokuAppData= new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		herpokuAppData.load(fis);
		
		
		CreateBookingApi createBookingApi = new CreateBookingApi();
		Response createBookingResponse =createBookingApi.getCreateBookingApiResponse();
		
		
		JsonPath jsonPath = new JsonPath(createBookingResponse.asString());
		String bookingId = jsonPath.get(herpokuAppData.getProperty("randomBookingId"));
		
		Response updateBookingResponse = updateBooking.putUpdateBookApiResponse(bookingId, herpokuAppData.getProperty("cookies"));
		Assert.assertTrue(createBookingResponse.statusCode()==400, "APi response is not 400");
		
		JsonPath jsPathLastName = new JsonPath(updateBookingResponse.asString());
		String lastNameNew = jsPathLastName.getString("lastname");
		System.out.println("updated last name is: "+lastNameNew);
	}

}
