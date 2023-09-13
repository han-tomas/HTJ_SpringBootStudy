package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sist.web.entity.*;
import java.util.*;
/*
FINO bigint 
MCNO bigint 
POSTER varchar(4000) 
NAME varchar(200) 
SCORE float 
ADDRESS varchar(1000) 
PHONE varchar(20) 
TYPE varchar(100) 
PRICE varchar(100) 
PARKING varchar(50) 
TIME varchar(100) 
MENU varchar(4000) 
HIT bigint 
LIKE_COUNT bigint
 */
@Repository
public interface JejuFoodDAO extends JpaRepository<JejuFoodEntity, Integer>{
	@Query(value = "SELECT * FROM food_info",nativeQuery = true)
	public List<JejuFoodEntity> jejuFoodData();
	@Query(value = "SELECT * "
			+ "FROM food_info WHERE name LIKE CONCAT('%',:name,'%') "
			+ "LIMIT :start,12",nativeQuery = true)
	public List<JejuFoodEntity> jejuFoodFindData(@Param("name") String name,
			@Param("start") Integer start);
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM food_info "
			+ "WHERE name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
	public int jejuFoodFindTotalPage(String name);
	public JejuFoodEntity findByFino(@Param("fino") Integer fino);
}
