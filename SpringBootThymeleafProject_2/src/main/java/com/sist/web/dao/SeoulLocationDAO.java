package com.sist.web.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Repository
public interface SeoulLocationDAO extends JpaRepository<SeoulLocationEntity, Integer> {
	@Query(value = "SELECT * FROM seoul_location "
			+ "ORDER BY no ASC "
			+ "LIMIT :start , 12",nativeQuery = true)
	public List<SeoulLocationEntity> seoulListData(int start);
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM seoul_location",nativeQuery = true)
	public int seoulListTotalPage();
}
