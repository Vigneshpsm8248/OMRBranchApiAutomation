package org.pages;

import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;
import com.payload.address.AddressPayload;
import com.pojo.address.*;
import com.pojo.login.Login_Output_Pojo;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.endpoints.EndPoints;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoginClass extends BaseClass {
	String logtoken;
	int statusIdNum;
	String state_id;
	int cityId;
	String address_id;

	@Test(priority = 7)
	public void deleteUserAddress() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		// 2 PayLoad
		addBody(AddressPayload.getDeleteAddressPayload(address_id));
		// 3.Method type
		Response response = requestType("DELETE", EndPoints.DELETEUSERADDRESS);
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actmessage = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actmessage, "Address deleted successfully", "Verify Address deleted successfully");

	}

	@Test(priority = 6)
	public void getUserAddress() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		// Method type
		Response response2 = requestType("GET", EndPoints.GETUSERADDRESS);
		int actstatusCode = getStatusCode(response2);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response2.as(GetUserAddress_Output_Pojo.class);
		String actmessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actmessage, "OK", "Verify GetUserAddress Message");
		System.out.println(actmessage);

	}

	@Test(priority = 5)
	public void updateUserAddress() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		// 2 PayLoad

		addBody(AddressPayload.getUpdateAddressPayload(address_id, "Vignesh", "Vicky", "7397194873", "doorno7/256",
				statusIdNum, cityId, 101, "636007", "7/256 anna nagar", "home"));
		// 3.Method Type
		Response response2 = requestType("PUT", EndPoints.UPDATEUSERADDRESS);
		int actstatusCode = getStatusCode(response2);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMesage = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actMesage, "Address updated successfully", " Verify Address updated successMessage");
		System.out.println(actMesage);
	}

	@Test(priority = 4)
	public void addUserAddress() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		// 2 PayLoad
		addBody(AddressPayload.getAddAddressPayload("Vignesh", "Vicky", "7397194873", "doorno7/256", statusIdNum,
				cityId, 101, "636007", "7/256 anna nagar", "home"));
		// Method type
		Response response = requestType("POST", EndPoints.ADDUSERADDRESS);
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		String bodyAsPreetyString = getResBodyAsPreetyString(response);
		System.out.println(bodyAsPreetyString);

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);

		String actmessage = addUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actmessage, "Address added successfully", "Verify Adderss Added Sucessfully");
		int addressIdNum = addUserAddress_Output_Pojo.getAddress_id();
		address_id = String.valueOf(addressIdNum);
		System.out.println(address_id);
	}

	@Test(priority = 3)
	public void getCityList() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		// 2.PayLoad

		addBody(AddressPayload.getCityCodePayload(state_id));
		// 3.Method Type
		Response response2 = requestType("POST", EndPoints.CITYLIST);
		int actstatusCode = getStatusCode(response2);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		CityList_Output_Pojo cityList_Output_Pojo = response2.as(CityList_Output_Pojo.class);
		// Finding The CityId (Pass the City Name as Yercaud and get the CityId)
		ArrayList<CityList> cityList = cityList_Output_Pojo.getData();
		for (CityList eachCityList : cityList) {
			String actCityName = eachCityList.getName();
			if (actCityName.equals("Yercaud")) {
				cityId = eachCityList.getId();
				System.out.println(cityId);
				Assert.assertEquals(actCityName, "Yercaud", "Verify city name as Yercaud");
				break;
			}

		}

	}

	@Test(priority = 2)
	public void getStateList() {
		addHeader("accept", "application/json");
		Response response = requestType("GET", EndPoints.STATELIST);
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> listStateList = stateList_Output_Pojo.getData();
		// Find The State Id For TamilNadu(Pass the State name and get The StatusId)
		for (StateList eachstateList : listStateList) {
			// Get Each StateName
			String actStatename = eachstateList.getName();
			if (actStatename.equals("Tamil Nadu")) {
				statusIdNum = eachstateList.getId();
				state_id = String.valueOf(statusIdNum);
				System.out.println(statusIdNum);
				Assert.assertEquals(actStatename, "Tamil Nadu", "Verify StateName as TamilNadu");

				break;
			}

		}

	}

	@Test(priority = 1)
	public void login() {
		// 1.Header
		addHeader("accept", "application/json");
		// 2.Basic Auth
		addBasicAuth("vigneshvicky.psm@gmail.com", "Vignesh@8248");
		// 3.Method type
		Response response = requestType("POST", EndPoints.POSTMANBASIICAUTH);

		int actStatusCode = getStatusCode(response);
		Assert.assertEquals(actStatusCode, 200, "Verify StatusCode");
		System.out.println(actStatusCode);

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		String actFirstName = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals(actFirstName, "Vignesh", "Verify FirstName");
		logtoken = login_Output_Pojo.getData().getLogtoken();

	}

}
