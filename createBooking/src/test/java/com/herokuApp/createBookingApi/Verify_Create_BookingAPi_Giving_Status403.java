package com.herokuApp.createBookingApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.herokuapp.restfulBookerApi.CreateBookingApi;

public class Verify_Create_BookingAPi_Giving_Status403 {
	Logger logger = Logger.getLogger(Verify_Create_BookingAPi_Giving_Status403.class);
	Properties herokuappdata ;
	
	@Test
	public void verify_Create_BookingApi_Giving_Status403_Test() throws IOException {
		herokuappdata = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resource/apiconfig/zomatoApiData.properties");
		herokuappdata.load(fileInputStream);
		
		CreateBookingApi createBooking = new CreateBookingApi();
		createBooking.getCreateBookingApiResponse();
		
	}

}
