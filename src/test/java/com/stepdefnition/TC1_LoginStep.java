package com.stepdefnition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.global.GlobalDatas;
import com.pojo.login.Login_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {

	 public static GlobalDatas globaldatas = new GlobalDatas();
	
	public Response response;
	
	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "application/json");

	}
	@When("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
	addBasicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
	}
	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String type) {
	 response = requestType(type, EndPoints.POSTMANBASIICAUTH);
	 int actStatusCode = getStatusCode(response);
	 globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the login response body firstname present as {string} and get the logtoken saved")
	public void userVerifyTheLoginResponseBodyFirstnamePresentAsAndGetTheLogtokenSaved(String expFirstName) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		String actFirstName = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals("Verify FirstName",expFirstName,actFirstName);
		String logtoken = login_Output_Pojo.getData().getLogtoken();
		globaldatas.setLogtoken(logtoken);
		
	}



	

}
