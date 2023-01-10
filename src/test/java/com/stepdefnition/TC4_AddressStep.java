package com.stepdefnition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.payload.address.AddressPayload;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetUserAddress_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {
	Response response;

	@Given("User add header and bearer authentication for accessing AddUseraddress endpoints")
	public void userAddHeaderAndBearerAuthenticationForAccessingAddUseraddressEndpoints() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for Add user new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForAddUserNewAddressAnd(String first_name, String last_name, String mobile, String apartment,
			String state, String city, String country, String zipcode, String address, String address_type) {
		// 2 PayLoad
		addBody(AddressPayload.getAddAddressPayload(first_name, last_name,mobile, apartment,
				TC1_LoginStep.globaldatas.getState_id(), TC1_LoginStep.globaldatas.getCityId(),country, zipcode,
				address, address_type));
	}

	@When("User send {string} request for addUserAddress endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String type) {
		response = requestType(type, EndPoints.ADDUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the addUserAddress response message matches {string}")
	public void userVerifyTheAddUserAddressResponseMessageMatches(String expMessage) {

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actMessage = addUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Adderss Added Sucessfully", expMessage, actMessage);
		int addressIdNum = addUserAddress_Output_Pojo.getAddress_id();
		String address_id = String.valueOf(addressIdNum);
		TC1_LoginStep.globaldatas.setAddress_id(address_id);
	}

	@Given("User add header and bearer authentication for accessing UpdateUseraddress endpoints")
	public void userAddHeaderAndBearerAuthenticationForAccessingUpdateUseraddressEndpoints() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

	}

	@When("User add request body for Update user address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForUpdateUserAddressAnd(String string, String string2, String string3, String string4,
			String string5, String string6, String string7, String string8, String string9, String string10) {
		// 2 PayLoad
		addBody(AddressPayload.getUpdateAddressPayload(TC1_LoginStep.globaldatas.getAddress_id(), "Vignesh", "Vicky",
				"7397194873", "doorno7/256", TC1_LoginStep.globaldatas.getState_id(),
				TC1_LoginStep.globaldatas.getCityId(), 101, "636007", "7/256 anna nagar", "home"));
	}

	@When("User send {string} request for updateUserAddress endpoint")
	public void userSendRequestForUpdateUserAddressEndpoint(String type) {
		response = requestType(type, EndPoints.UPDATEUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the updateUserAddress response message matches {string}")
	public void userVerifyTheUpdateUserAddressResponseMessageMatches(String expMesage) {
		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMesage = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(" Verify Address updated successMessage", expMesage, actMesage);
	}

	@Given("User add header and bearer authentication for accessing GetUseraddress endpoints")
	public void userAddHeaderAndBearerAuthenticationForAccessingGetUseraddressEndpoints() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User send {string} request for getUserAddress endpoint")
	public void userSendRequestForGetUserAddressEndpoint(String type) {
		response = requestType(type, EndPoints.GETUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the getUserAddress response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expMessage) {
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String actMessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify GetUserAddress Message", expMessage, actMessage);
	}

	@Given("User add header and bearer authentication for accessing DeleteUseraddress endpoints")
	public void userAddHeaderAndBearerAuthenticationForAccessingDeleteUseraddressEndpoints() {
		// 1.Headers
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for delete address")
	public void userAddRequestBodyForDeleteAddress() {
		// 2 PayLoad
		addBody(AddressPayload.getDeleteAddressPayload(TC1_LoginStep.globaldatas.getAddress_id()));

	}

	@When("User send {string} request for deleteUserAddress endpoint")
	public void userSendRequestForDeleteUserAddressEndpoint(String type) {
		Response response = requestType(type, EndPoints.DELETEUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the deleteUserAddress response message matches {string}")
	public void userVerifyTheDeleteUserAddressResponseMessageMatches(String expMessage) {
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMessage = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Address deleted successfully", expMessage, actMessage);

	}

}
