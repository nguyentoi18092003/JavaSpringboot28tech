package Beans;

public class BuildingDTO {
	//DTO: Data tranfer Object
	//day la mk dang viet javabean, nho phai tuan thu theo 3 nguyen tac, giong nhu class,casc thuoc tinh phai de private, moi thuoc tinh deu co getter,setter
	private String name;
	private String ward;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
}
