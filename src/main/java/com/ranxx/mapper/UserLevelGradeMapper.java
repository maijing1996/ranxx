package com.ranxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.UserLevelGrade;
import com.ranxx.model.dto.UserLevelGradeMax;
import com.ranxx.util.MyMapper;

public interface UserLevelGradeMapper extends MyMapper<UserLevelGrade> {
	
	List<UserLevelGradeMax> getLevelGrade(@Param("uid") Long uid);

	boolean save(@Param("uid") Long uid, @Param("level") Integer level);

	boolean updateLevelGrade(@Param("uid") Long uid, @Param("level") Integer level, @Param("grade") Integer grade);

	boolean updateLooNum(@Param("uid") Long uid, @Param("level") Integer level, @Param("lookNum") Integer lookNum);

	boolean updateCarnet(@Param("level") Integer level, @Param("is_carnet") Integer is_carnet, @Param("uid") Long uid);
}
