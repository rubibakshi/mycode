package com.herokuApp.UpdateBookingApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.restfulBookerApi.CreateBookingApi;
import com.herokuapp.restfulBookerApi.UpdateBookingApi;

public class Verify_Update_BookingApi_Giving_Status403 {
	
	@Test
	public void verify_update_booking_api_giving_status403() throws IOException{
		
		Properties herokuappData = new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		herokuappData.load(fis);
		
		CreateBookingApi createBookingApi = new CreateBookingApi();
		Response createBookingResponse =createBookingApi.getCreateBookingApiResponse();
		
		JsonPath jsonPath = new JsonPath(createBookingResponse.asString());
		String bookingId = jsonPath.getString("bookingid");
		
		UpdateBookingApi updateBooking = new UpdateBookingApi();
		Response updateBookingResponse = updateBooking.putUpdateBookApiResponse(bookingId,herokuappData.getProperty("invalidToken"));
		Assert.assertTrue(updateBookingResponse.statusCode()==403, "APi response is not 403");

	}
	
	

}
