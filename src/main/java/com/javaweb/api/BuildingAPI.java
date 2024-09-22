package com.javaweb.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Beans.BuildingDTO;
import Beans.ErrorResonseDTO;
import customexception.FieldRequiredException;

@RestController// de danh dau la day khong phai class thong thuong mà no la restfulAPI Webservice,nơi tiếp nhận dữ liệu từ client đổ về, đây cx là nơi mà server trả về dư liệu cho client đax nhập 
public class BuildingAPI {
	@GetMapping(value="/api/building/")
	public Object getBuilding(@RequestParam(value="name", required=false) String nameBuilding,// neeus k de require thi no se mac dinh la true, neu la true thi tren url bat buoc phai co key do truyen len
							@RequestParam(value="ward", required=false) String ward) {//RequestParame la phan yeu cau dang sau api tren thanh param //.../api/building/?name="Toi"& ward="hehe", thuogn truyen len nhu nay laf dung de search
	//neu tim kiem theo 1 vai truowng thi lam nhu nay duoc neu nhieu thi k nen-> nen dung cach duoi
		//xu ly duoi DB xong roi
		try {
			System.out.println(5/0);
		}
		catch(Exception e) {
			ErrorResonseDTO errorResonseDTO=new ErrorResonseDTO();
			errorResonseDTO.setError(e.getMessage());
			List<String> detail=new ArrayList<>();
			detail.add("So nguyen lam soa chia cho khong duoc");
			errorResonseDTO.setDetail(detail);
			return errorResonseDTO;
		}
		BuildingDTO result =new BuildingDTO();
		result.setName(nameBuilding);
		result.setWard(ward);
		return result;// day chi co kieu cau truc cua json thoi, nen can them @ResponseBody, vi ben client no chi hieu dang json thoi, con dang giong json thi no cx k hieuu

	}
	public void Validate(BuildingDTO buildingDTO) throws FieldRequiredException {
		if(buildingDTO.getName()==null||buildingDTO.getName().equals("")||buildingDTO.getWard()==null) {
			throw new FieldRequiredException("name or numberofbasement is null");
		}
		
		
	}
	
	//POST tren params
	//	@RequestMapping(value="/api/building/",method=RequestMethod.POST)
	//	public void getBuilding2
	//	(@RequestParam Map<String,String> params)
	//	{
	//		System.out.print("OK");
	//	}
	
	//POST trong body
		@PostMapping(value="/api/building/")
		public Object getBuilding3
		(@RequestBody BuildingDTO building)// o day nhan map cx dc, building DTO cx dc vi file json guwi len no o dang key value, ca 2 loaij nay deu dap ung dey, value
		{
			//xu li duoi DB xong roi
			try {
				Validate(building);
			}
			catch(FieldRequiredException e) {
				ErrorResonseDTO errorResonseDTO=new ErrorResonseDTO();
				errorResonseDTO.setError(e.getMessage());
				List<String> details=new ArrayList<>();
				details.add("Check lai name hoac ward di boi vi dang bi null");
				errorResonseDTO.setDetail(details);
				return errorResonseDTO;
				
			}
			return building;
		}
	
		@DeleteMapping(value="/api/building/{id}")
		public void deleteBuilding(@PathVariable Integer id) {
			System.out.print("Da xoa toa nha co id la"+id +" roi nhe");
		}
	
}
