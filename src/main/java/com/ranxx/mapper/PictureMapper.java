package com.ranxx.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ranxx.model.Picture;
import com.ranxx.util.MyMapper;


public interface PictureMapper extends MyMapper<Picture> {
	
	List<Picture> listPicture();

	boolean save(@Param("imgUrl") String imgUrl, @Param("titleText") String titleText);
}
