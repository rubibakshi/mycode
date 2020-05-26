package com.herokuApp.UpdatePartialBookingApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.restfulBookerApi.CreateBookingApi;
import com.herokuapp.restfulBookerApi.UpdatePartialBookingApi;



public class Verify_Update_Partial_Booking_Api_IsSuccess_Test {
	
	@Test
	public void verify_update_booking_api_with_success() throws IOException{
		UpdatePartialBookingApi updateBooking = new UpdatePartialBookingApi();
		Properties prop = new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/apiConfig/herokuapp.properties");
		prop.load(fis);
		
		
		CreateBookingApi createBookingApi = new CreateBookingApi();
		Response createBookingResponse =createBookingApi.getCreateBookingApiResponse();
		Assert.assertTrue(createBookingResponse.statusCode()==200, "APi response is not 200");
		
		JsonPath jsonPath = new JsonPath(createBookingResponse.asString());
		String bookingId = jsonPath.getString("bookingid");
		
		Response updateBookingResponse = updateBooking.getUpdatePartialBookApiResponse(bookingId);
		Assert.assertTrue(createBookingResponse.statusCode()==200, "APi response is not 200");
		Assert.assertEquals(createBookingResponse.statusCode(), 200, "APi response is not 200");
		
		JsonPath jsPathLastName = new JsonPath(updateBookingResponse.asString());
		String lastNameNew = jsPathLastName.getString("lastname");
		System.out.println("updated last name is: "+lastNameNew);
	}
	


}
