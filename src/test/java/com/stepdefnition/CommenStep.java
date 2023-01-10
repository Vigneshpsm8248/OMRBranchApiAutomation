package com.stepdefnition;

import org.junit.Assert;
import com.base.BaseClass;
import io.cucumber.java.en.Then;

public class CommenStep extends BaseClass {

	@Then("User verify the status code in {int}")
	public void userVerifyTheStatusCodeIn(int expStatusCode) {
		                                                        
		//classname.static reffName of GlobalDates class.method of GlobalDates class
		int actstatusCode = TC1_LoginStep.globaldatas.getStatusCode();
		Assert.assertEquals("Verify StatusCode", expStatusCode,actstatusCode );
	}

}
