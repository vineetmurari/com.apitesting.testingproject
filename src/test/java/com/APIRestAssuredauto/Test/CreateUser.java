package com.APIRestAssuredauto.Test;

public class CreateUser { //pojo

	private String name;
	private String Job;
	
	CreateUser(String name, String Job){
		this.name=name;
		this.Job=Job;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	
	

}
