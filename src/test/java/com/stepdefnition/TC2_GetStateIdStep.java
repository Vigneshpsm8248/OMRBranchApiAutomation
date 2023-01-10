package com.stepdefnition;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.global.GlobalDatas;
import com.pojo.address.StateList;
import com.pojo.address.StateList_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class TC2_GetStateIdStep extends BaseClass {

	Response response;
	//static GlobalDatas globaldatas = new GlobalDatas();

	@Given("User add header for statelist")
	public void userAddHeaderForStatelist() {
		addHeader("accept", "application/json");

	}

	@When("User Send {string} request for statelist endpoint")
	public void userSendRequestForStatelistEndpoint(String type) {
		response = requestType(type, EndPoints.STATELIST);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the StateList response message matches {string} and saved state_id")
	public void userVerifyTheStateListResponseMessageMatchesAndSavedStateId(String expStatenaem) {
		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> listStateList = stateList_Output_Pojo.getData();
		// Find The State Id For TamilNadu(Pass the State name and get The StatusId)
		for (StateList eachstateList : listStateList) {
			// Get Each StateName
			String actStatename = eachstateList.getName();
			if (actStatename.equals("Tamil Nadu")) {
				int statusIdNum = eachstateList.getId();
				TC1_LoginStep.globaldatas.setStatusIdNum(statusIdNum);
				String state_id = String.valueOf(TC1_LoginStep.globaldatas.getStatusIdNum());
				TC1_LoginStep.globaldatas.setState_id(state_id);
				System.out.println(TC1_LoginStep.globaldatas.getState_id());
				Assert.assertEquals("Verify StateName as TamilNadu", expStatenaem, actStatename);

				break;
			}
		}

	}

}
