package com.javaweb.model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class BuildingDTO {
	//DTO: Data tranfer Object
		//day la mk dang viet javabean, nho phai tuan thu theo 3 nguyen tac, giong nhu class,casc thuoc tinh phai de private, moi thuoc tinh deu co getter,setter
		private String name;
		private Integer numberOfBasement;
		private String address;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		

}
