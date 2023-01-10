package com.stepdefnition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.global.GlobalDatas;
import com.payload.address.AddressPayload;
import com.pojo.address.CityList;
import com.pojo.address.CityList_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_GetCityIdStep extends BaseClass {
	
	Response response;
	String state_id;
	static GlobalDatas globaldatas = new GlobalDatas();
	
	@Given("User add header for citylist")
	public void userAddHeaderForCitylist() {
		// 1.Headers
				List<Header> listHeaders = new ArrayList<>();

				Header h1 = new Header("accept", "application/json");
				Header h3 = new Header("Content-Type", "application/json");

				listHeaders.add(h1);
				listHeaders.add(h3);
				Headers headers = new Headers(listHeaders);
				addHeaders(headers);

	}
	@When("User should add request body stateId for to get citylist")
	public void userShouldAddRequestBodyStateIdForToGetCitylist() {
		// 2.PayLoad
	    state_id = TC1_LoginStep.globaldatas.getState_id();
		System.out.println(state_id);
		addBody(AddressPayload.getCityCodePayload(state_id));
		
	}
	@When("User Send {string} request for citylist endpoint")
	public void userSendRequestForCitylistEndpoint(String type) {
		// 3.Method Type
				response = requestType(type, EndPoints.CITYLIST);
				int actStatusCode = getStatusCode(response);
				TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	 
	}
	@Then("User verify the CityList response message matches {string} and saved city_id")
	public void userVerifyTheCityListResponseMessageMatchesAndSavedCityId(String expCityName) {
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		// Finding The CityId (Pass the City Name as Yercaud and get the CityId)
		ArrayList<CityList> cityList = cityList_Output_Pojo.getData();
		for (CityList eachCityList : cityList) {
			String actCityName = eachCityList.getName();
			if (actCityName.equals("Yercaud")) {
				int cityId = eachCityList.getId();
				TC1_LoginStep.globaldatas.setCityId(cityId);
				Assert.assertEquals("Verify city name as Yercaud",expCityName,actCityName);
				break;
			}

		}

		
	}




}
