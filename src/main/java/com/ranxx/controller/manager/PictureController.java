package com.ranxx.controller.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.ranxx.model.Picture;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.model.common.LayuiResponse;
import com.ranxx.service.PictureService;
import com.ranxx.util.BusinessException;

@ResponseBody
@RestController
@RequestMapping(value = "/manager/wechat/share")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 获取所有的分享文字图片
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public LayuiResponse listInfo() {
		
		LayuiResponse layuiResponse = new LayuiResponse();
		List<Picture> listPicture = pictureService.listPicture();
		layuiResponse.setCode(200);
		return layuiResponse.setSuccess(null, listPicture, 0);
	}
	
	/**
	 * 刪除单个用戶
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse delete(@RequestBody Map<String, Long> map) throws BusinessException {
		
		BaseResponse baseResponse = new BaseResponse();
		if(map.get("id") != null) {
			pictureService.deleteById(map.get("id"));
			return baseResponse.setValue("操作成功！", null);
		} else {
			return baseResponse.setError(405, "操作对象不明确！");
		}
	}
	
	/**
	 * 保存分享文字图片
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseResponse save(@RequestBody Map<String, String> map) throws BusinessException {
		
		BaseResponse baseResponse = new BaseResponse();
		Picture picture = new Picture();
		if(map.get("imgUrl") == null || map.get("imgUrl").trim().isEmpty()) {
			return baseResponse.setError(404, "请输入url！");
		}else if(map.get("titleText") == null || map.get("titleText").trim().isEmpty()) {
			return baseResponse.setError(404, "请输入要分享的文字！");
		}else {
//			picture.setImgUrl(map.get("imgUrl"));
//			picture.setTitleText(map.get("titleText"));
			pictureService.save(map.get("imgUrl"), map.get("titleText"));
			return baseResponse.setValue("操作成功！", null);
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public LayuiResponse Info() {
		
		LayuiResponse layuiResponse = new LayuiResponse();
		List<Picture> listPicture = pictureService.listPicture();
		layuiResponse.setCode(0);
		return layuiResponse.setSuccess(null, listPicture, 0);
	}
	
}
