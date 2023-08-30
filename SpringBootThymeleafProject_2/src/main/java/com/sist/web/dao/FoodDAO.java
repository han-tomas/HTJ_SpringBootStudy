package com.sist.web.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import com.sist.web.vo.FoodVO;
@Repository
public interface FoodDAO extends JpaRepository<FoodEntity,Integer> {
	//public FoodEntity findByFno(int fno);
	// SELECT * FRO  food_location WHERE fno=1
	// INSERT , UPDATE, DELETE
	@Query(value="SELECT * FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')"
			+ "ORDER BY fno ASC "
			+ "LIMIT :start , 12",nativeQuery=true)
	public List<FoodEntity> foodFindData(@Param("address") String address,
			@Param("start") Integer start);
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')",nativeQuery=true)
	public int foodFindTotalPage(String address);
	public FoodEntity findByFno(@Param("fno") Integer fno);
	// SELECT * FROM food_location WHERE fno=10
}
