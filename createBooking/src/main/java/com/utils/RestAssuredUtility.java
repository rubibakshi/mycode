package com.utils;

import java.util.Map;

import javax.json.JsonObject;

import org.json.JSONObject;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import groovy.lang.Newify;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtility {
	
	
	/**
     * Function: Post api utility.
     *
     * @param uri the uri
     * @param cookies the cookies
     * @param headers the headers
     * @param params the params
     * @return the response
     */
	//public Response post(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> params, JSONObject payload){
	  public Response post(String url, Map<String, String> headers, Map<String, String> cookies, Map<String, String> params, JsonObject payload){
		RestAssured.baseURI =url;
		RestAssured.useRelaxedHTTPSValidation();
		
		RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.log().all();
		if(headers!=null)
			requestSpecification.headers(headers);
		
		if(cookies!= null)
			requestSpecification.cookies(cookies);
		
		if(params!= null)
			requestSpecification.params(params);
		
		if(payload!=null) {
			requestSpecification.body(payload.toString());
			
			} 
		
		Response response = requestSpecification.request(Method.POST);
		String responseMsg = response.asString();
			
		return response;
		
	}
	
	
	/**
     * Function: Get api utility.
     *
     * @param uri the uri
     * @param cookies the cookies
     * @param headers the headers
     * @param params the params
     * @return the response
     */
	public Response get(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> params) {
		RestAssured.baseURI = url;
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.log().all();
		if(headers!= null)
			requestSpecification.headers(headers);
		
		if(cookies!= null)
			requestSpecification.cookies(cookies);
		
		if(params!= null)
			requestSpecification.params(params);
		
		
		Response response = requestSpecification.request(Method.GET);
		String responseMsg = response.asString();
		
		return response;
	}
	
	/**
     * Function: Put api utility.
     *
     * @param uri the uri
     * @param cookies the cookies
     * @param headers the headers
     * @param params the params
     * @return the response
     */
	public Response put(String url, Map<String, String> cookies, Map<String, String> headers, Map<String, String> params, JsonObject body) {
		RestAssured.baseURI = url;
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.log().all();
		requestSpecification.basePath("/{id}");
		
		if(headers!= null)
			requestSpecification.headers(headers);
		
		if(cookies!= null)
			requestSpecification.cookies(cookies);
		
		if(params!= null)
			requestSpecification.pathParams(params);
		
		if(body!= null)
			requestSpecification.body(body.toString());
		
		
		Response response = requestSpecification.request(Method.PUT);
		String responseMsg = response.asString();
		return response;
	}
	
	/**
     * Function: Patch api utility.
     *
     * @param uri the uri
     * @param cookies the cookies
     * @param headers the headers
     * @param params the params
     * @return the response
     */
	public Response patch(String url, String basePath, Map<String, String> cookies, Map<String, String> headers, Map<String, String> params, JsonObject body) {
		RestAssured.baseURI = url;
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.log().all();
		
		requestSpecification.basePath(basePath);
		
		if(headers!= null)
			requestSpecification.headers(headers);
		
		if(cookies!= null)
			requestSpecification.cookies(cookies);
		
		if(params!= null)
			requestSpecification.pathParams(params);
		
		if(body!= null)
			requestSpecification.body(body.toString());
		
		
		Response response = requestSpecification.request(Method.PATCH);
		String responseMsg = response.asString();
		return response;
	}
	
	public String getFormattedJsonResponse(Response jsonResponse) {
		if(jsonResponse.getContentType().contains("json")){
			return new GsonBuilder().setPrettyPrinting().create().
					toJson(new JsonParser().parse(jsonResponse.asString()));
		}else 
		return jsonResponse.asString();
		
	}


}
