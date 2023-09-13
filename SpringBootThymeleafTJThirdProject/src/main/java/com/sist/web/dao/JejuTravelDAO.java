package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
@Repository
public interface JejuTravelDAO extends JpaRepository<JeJuTravelEntity, Integer> {
	@Query(value = "SELECT * "
			+ "FROM travel_detail WHERE lno=1 ORDER BY no ASC",nativeQuery = true)
	public List<JeJuTravelEntity> jejuListData();
	@Query(value="SELECT * FROM travel_detail WHERE lno=1 ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true)
	public List<JeJuTravelEntity> jejuTravelListData(int start);
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM travel_detail "
			+ "WHERE lno=1",nativeQuery = true)
	public int jejuTravelTotalPage();
	
	public JeJuTravelEntity findByNo(@Param("no") Integer no);
}
