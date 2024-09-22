package com.javaweb.api;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

import customexception.FieldRequiredException;

@RestController
@ComponentScan(basePackages = "controllerAdvice")//them dong nay so voi anh day
public class BuildingAPI {
		// Khi phan tang dung cac annotion, thi cac interface khong duoc goi thanng ra ma phai co them @Autowired
		@Autowired
		private BuildingService buildingService;
		@GetMapping(value="/api/building/")
		public List<BuildingDTO> getBuilding(@RequestParam(name="name") String name){
			
			List<BuildingDTO> result=buildingService.findAll(name);
			System.out.println("-------------------------da tim kiem xong ");
			System.out.println(result.size());
			for(BuildingDTO item:result) {
				System.out.println("-------------------------"+ item.getName());
				
			}
			return result;
		}
	
		public void Validate(BuildingDTO buildingDTO){
			if(buildingDTO.getName()==null||buildingDTO.getName().equals("")||buildingDTO.getNumberOfBasement()==null) {
				throw new FieldRequiredException("name or numberofbasement is null");
			}	
		}
		
		@DeleteMapping(value="/api/building/{id}/{name}/")
		public void deleteBuilding(@PathVariable Integer id,
				@PathVariable String name,
				@RequestParam(value="ward",required=false) String ward)
				 {
			System.out.print("Da xoa toa nha co id la"+id +" roi nhe");
		}
	
}
