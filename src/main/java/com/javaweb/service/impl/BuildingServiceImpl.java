package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.converter.BuildingDTOConverter;


@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buidingRepository;
	
	@Autowired//BuildingDTOConverter mk k co ham khoi tao, nen giong interface mk muon dung nos minh phai co @authowite
	private BuildingDTOConverter BuildingDTOConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String,Object> params,List<String>typeCode) {
		List<BuildingEntity> buildingEntities=buidingRepository.findAll(params,typeCode);
		List<BuildingDTO> result=new ArrayList<BuildingDTO>();
		for(BuildingEntity item:buildingEntities) {
			BuildingDTO building=BuildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}
	

}
