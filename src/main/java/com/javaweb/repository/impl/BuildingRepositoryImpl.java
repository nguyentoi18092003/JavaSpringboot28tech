package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUntil;
import com.javaweb.utils.StringUtil;
import com.mysql.cj.Query;

@Repository// Tang Data Acess Layer, de lay du lieu tu DB len, trong day cx dung cac Entity de hung du lieu tu database tra ve
public class BuildingRepositoryImpl implements BuildingRepository{
	
	public static void joinTable(Map<String,Object>params,List<String>typeCode,StringBuilder sql) {
		String staffid=(String)params.get("staffId");
		if(StringUtil.checkString(staffid)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id=assignmentbuilding.buildingid ");
		}
		//null la k co key, cx k co value, xau rong la co key, value la xau rong
		if(typeCode!=null && typeCode.size()!=0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id= buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON renttype.id=buildingrenttype.renttypeid ");	
		}
		
		
	}
	public static void queryNomal(Map<String,Object>params,StringBuilder where) {
		// Ham nay mk xu li cho nhung truogn chi can dung tai bang do (filde cua bang do)la lay dc, khong can join voi bat ki bang nao khac
		for(Map.Entry<String, Object> it:params.entrySet()) {
			System.out.println("-----------" + it.getKey()+"-------------"+it.getValue());
			if(!it.getKey().equals("staffId")&& !it.getKey().equals("typeCode") && !it.getKey().startsWith("area")&&  !it.getKey().startsWith("rentPrice")){
				System.out.println("---------da khac");
				//Vi trong nhung truogn mk can lay ra thi co 4 truong :staffId,typeCode,areaTo,areaFrom muon lay dc phai join voi bang khac, truong rentPrice sau nay sex tim kiem voi dieu kien trong khoang < and >., trong khoang.
				String value=it.getValue().toString();
				if(StringUtil.checkString(value)) {
					
					if(NumberUntil.isNumber(value)==true) {
						where.append(" AND b." +it.getKey()+" = "+value);	
					}
					else {
						where.append(" AND b." +it.getKey()+" LIKE '%"+ value +"%'");	
					}
				}
			}
		}
		
		
	}
	public static void querySpecial(Map<String,Object>params,List<String>typeCode,StringBuilder where) {
		//Muon lay ra truong do phai join voi bang khac, lay ra doan <=AND<=,nam trong khoang
		String staffId=(String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = "+staffId);
		}
		
		String rentAreaTo=(String) params.get("areaTo");
		String rentAreaFrom=(String) params.get("areaFrom");
		if(StringUtil.checkString(rentAreaTo)==true||StringUtil.checkString(rentAreaFrom)==true) {
			where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id=r.buildingid ");
						if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND r.value >="+rentAreaFrom);
				
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND r.value <="+rentAreaTo);
			}
			where.append(") ");
		}
			
			String rentPriceTo=(String) params.get("rentPriceTo");
			String rentPriceFrom=(String) params.get("rentPriceFrom");
			if(StringUtil.checkString(rentPriceFrom)==true||StringUtil.checkString(rentPriceFrom)==true) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" AND  b.rentprice <=" + rentPriceTo);
			}	
		

		if(typeCode!=null && typeCode.size()!=0) {
			where.append(" AND(");
			String sql =typeCode.stream().map(it-> " renttype.code Like "+"'%"+it+"%'").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(") ");
		}
		
	}
	@Override
	public List<BuildingEntity>findAll(Map<String,Object> params,List<String>typeCode) {
		StringBuilder sql= new StringBuilder("SELECT b.id, b.name,b.districtid,b.street,b.ward, b.numberofbasement,b.floorarea,b.rentprice,"+
											"\n b.managername,b.managerphonenumber,b.servicefee,b.brokeragefee FROM building b");
		joinTable(params, typeCode, sql);// vi cac truong mk lay ra no nam o bang khac, bat buoc phai join moi lay ra dc i
		StringBuilder where =new StringBuilder(" WHERE 1=1");
		queryNomal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" GROUP BY b.id");
		sql.append(where);
		System.out.println(sql);
		
		List<BuildingEntity> result =new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql.toString())){
			while(rs.next()) {
				BuildingEntity buildingEntity=new BuildingEntity();
				buildingEntity.setId(rs.getLong("b.id"));
				buildingEntity.setName(rs.getString("b.name"));
				buildingEntity.setWard(rs.getString("b.ward"));
				buildingEntity.setDistrictid(rs.getLong("b.districtid"));
				buildingEntity.setStreet(rs.getString("b.street"));
				buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
				buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
				buildingEntity.setServiceFee(rs.getString("b.servicefee"));
				buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
				buildingEntity.setManagerName(rs.getString("b.managername"));
				buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
				result.add(buildingEntity);
				
						
			}	
				} catch(SQLException e) {
					e.printStackTrace();
				}
		return result;
	}
	
}
