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
	public List<BuildingEntity>findAll(String name) {
		String sql= "SELECT * FROM building b WHERE name like '%"+ name +"%'";
		System.out.println("------------------------------da vao------------------------");
		//Tim kiem: GET http://localhost:8080/api/building/?name=building
		List<BuildingEntity> result =new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);){
			while(rs.next()) {
				System.out.println("------------------------------da tim kiem------------------------");
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
