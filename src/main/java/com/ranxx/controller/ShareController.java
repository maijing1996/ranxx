package com.ranxx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranxx.model.Picture;
import com.ranxx.model.WxConfig;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.service.PictureService;
import com.ranxx.service.WxConfigService;
import com.ranxx.util.ContentValues;
import com.ranxx.util.WxConfigUtil;

@ResponseBody
@RestController
@RequestMapping(value = "/run")
public class ShareController {
	
	@Autowired
	private PictureService pictureService;
	@Autowired
	private WxConfigService wxConfigService;
	
	@ResponseBody
	@RequestMapping(value = "/share", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public BaseResponse share(HttpServletRequest request, @RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
        String urlTemp = "http://" + request.getServerName() + request.getContextPath();
        String urlpath = "http://" + request.getServerName();
        String appUrl = request.getParameter("url");
        if (request.getParameter("code") != null) {
            appUrl += "&code=" + request.getParameter("code");
        }
        if (request.getParameter("state") != null) {
            appUrl += "&state=" + request.getParameter("state");
        }
        WxConfig config = wxConfigService.getWxConfig();
        Map<String, Object> signature = WxConfigUtil.getSignature(appUrl, config.getAppid(), config.getSecret(), urlTemp, urlpath);
        
        List<Picture> listpicture = pictureService.listPicture();
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("listPicture", listpicture);
        signature.put("list", map2);
        
        return baseResponse.setValue("data", signature);
    }
	
}
