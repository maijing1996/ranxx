package com.ranxx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranxx.mapper.PictureMapper;
import com.ranxx.model.Picture;
import com.ranxx.service.common.BaseService;

@Service
public class PictureService extends BaseService<Picture, PictureMapper>{
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取所有的图片文字
	 * @return
	 */
	public List<Picture> listPicture() {
		
		return mapper.listPicture();
	}

	/**
	 * 保存插入图片
	 * @param imgUrl
	 * @param titleText
	 * @return
	 */
	public boolean save(String imgUrl, String titleText) {
		
		return mapper.save(imgUrl, titleText);
	}
}
