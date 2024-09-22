package com.javaweb.repository.entity;

public class BuildingEntity {
	//Entity phai map 1:1 voi DB trong DB co bao nhieu truong thi trong day cx phai co bay nhieu truong
	private String name;
	private Integer numberOfBasement;
	private String ward;
	private String street;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
