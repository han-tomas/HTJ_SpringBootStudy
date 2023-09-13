package com.sist.web.dao;
import com.sist.web.entity.*;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CampingDAO extends JpaRepository<CampingEntity, Integer> {
	@Query(value = "SELECT * FROM camp2 ",nativeQuery = true)
	public List<CampingEntity> campingHomeListData();
	@Query(value = "SELECT * FROM camp2 "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "LIMIT :start,20",nativeQuery = true)
	public List<CampingEntity> campFindData(@Param("address") String address,
			@Param("start") Integer start);
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM camp2 "
			+ "WHERE address LIKE CONCAT('%',:address,'%')",nativeQuery = true)
	public int campFindTotalPage(@Param("address") String address);
	
	public CampingEntity findByCno(int cno);
	
	@Query(value="SELECT COUNT(*) FROM camp2 "
		        +"WHERE address LIKE CONCAT('%',:address,'%')",
		        nativeQuery=true)
	public int campFindCount(String address);

}
