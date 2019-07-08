package com.qa.data;

public class BodyofAPI {

	String stateCode;
	String date;
	String sourceType;
	String timeBlock;
	String actualPower;
	


	public BodyofAPI() {

	}
	
	public BodyofAPI(String stateCode, String date,String sourceType){
		this.stateCode=stateCode;
		this.date=date;
		this.sourceType=sourceType;

	}


	//Getters and Setter methods

	
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getTimeBlock() {
		return timeBlock;
	}

	public void setTimeBlock(String timeBlock) {
		this.timeBlock = timeBlock;
	}

	public String getActualPower() {
		return actualPower;
	}

	public void setActualPower(String actualPower) {
		this.actualPower = actualPower;
	}
}
