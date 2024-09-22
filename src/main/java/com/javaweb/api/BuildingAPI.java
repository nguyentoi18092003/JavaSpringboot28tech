package com.javaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Beans.BuildingDTO;
import Beans.ErrorResonseDTO;
import customexception.FieldRequiredException;

@RestController
public class BuildingAPI {
	
		@PostMapping(value="/api/building/")
		public Object getBuilding(@RequestBody BuildingDTO building){
			//xu li duoi DB xong roi
			try {
				Validate(building);
			} catch(FieldRequiredException e) {
				ErrorResonseDTO errorResonseDTO=new ErrorResonseDTO();
				errorResonseDTO.setError(e.getMessage());
				List<String> details=new ArrayList<>();
				details.add("Check lai name hoac ward di boi vi dang bi null");
				errorResonseDTO.setDetail(details);
				return errorResonseDTO;	
			}
			return building;
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
