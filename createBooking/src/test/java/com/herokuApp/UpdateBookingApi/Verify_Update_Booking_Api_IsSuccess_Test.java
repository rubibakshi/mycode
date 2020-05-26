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



public class Verify_Update_Booking_Api_IsSuccess_Test {
	
	@Test
	public void verify_update_booking_api_with_success() throws IOException{
		UpdateBookingApi updateBooking = new UpdateBookingApi();
		Properties prop = new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		prop.load(fis);
		
		
		CreateBookingApi createBookingApi = new CreateBookingApi();
		Response createBookingResponse =createBookingApi.getCreateBookingApiResponse();
		
		JsonPath jsonPath = new JsonPath(createBookingResponse.asString());
		String bookingId = jsonPath.getString("bookingid");
		
		Response updateBookingResponse = updateBooking.putUpdateBookApiResponse(bookingId, prop.getProperty("cookies"));
		Assert.assertTrue(createBookingResponse.statusCode()==403, "APi response is not 403");
		
		JsonPath jsPathLastName = new JsonPath(updateBookingResponse.asString());
		String lastNameNew = jsPathLastName.getString("lastname");
		System.out.println("updated last name is: "+lastNameNew);
	}
	


}
