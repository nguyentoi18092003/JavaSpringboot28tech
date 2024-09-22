package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository// Tang Data Acess Layer, de lay du lieu tu DB len, trong day cx dung cac Entity de hung du lieu tu database tra ve
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER="root";
	static final String PASS="toi@1809";
	@Override
	public List<BuildingEntity>findAll(String name,Long districtId) {
		StringBuilder sql= new StringBuilder("SELECT * FROM building b WHERE 1 = 1 ");
		if(name!=null && !name.equals("")) {
		    sql.append(" AND b.name like '%" +name+"%' ");
		}
		if(districtId!=null) {
		    sql.append(" AND b.districtid="+districtId+" ");
		}
		//GET http://localhost:8080/api/building/?districtid=1& name=building
		List<BuildingEntity> result =new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql.toString())){
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				result.add(building);
			}	
				} catch(SQLException e) {
					e.printStackTrace();
				}
		return result;
	}
	
}
