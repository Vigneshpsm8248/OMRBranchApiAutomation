package com.pojo.address;

import java.util.ArrayList;

import com.pojo.address.UserAddress;

public class GetUserAddress_Output_Pojo {

	
	   private int status;
	   private String message;
	   private ArrayList<UserAddress> data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<UserAddress> getData() {
		return data;
	}
	public void setData(ArrayList<UserAddress> data) {
		this.data = data;
	}
	   
	   
	
}
