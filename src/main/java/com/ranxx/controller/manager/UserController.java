package com.ranxx.controller.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ranxx.model.User;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.model.common.LayuiResponse;
import com.ranxx.service.set.UserSetService;
import com.ranxx.util.BusinessException;
import com.ranxx.util.RegexUtil;
import com.ranxx.util.StringUtil;

@ResponseBody
@RestController
@RequestMapping(value = "/manager/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserSetService userSetService;

	/**
	 * 查询所有用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public LayuiResponse listinfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
		
		LayuiResponse layuiResponse = new LayuiResponse();
		String nickname = map.get("nickName");
		PageInfo<User> pageInfo = userSetService.userInfo(StringUtil.strToInt(map.get("page")), StringUtil.strToInt(map.get("size")), nickname);
		if(pageInfo.getTotal() != 0) {
			return layuiResponse.setSuccess(null, pageInfo.getList(), pageInfo.getTotal());
		}else {
			return layuiResponse.setError(404, "没有数据！");
		}
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
			userSetService.deleteById(map.get("id"));
			return baseResponse.setValue("删除用户成功！", null);
		} else {
			return baseResponse.setError(405, "操作对象不明确！");
		}
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/deletes", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public BaseResponse deletes(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(map.get("ids") != null && RegexUtil.matches(RegexUtil.IDS_REGEX, map.get("ids"))) {
			userSetService.deleteByIds(map.get("ids"));
			return baseResponse.setValue("删除用户成功！", null);
		} else {
			return baseResponse.setError(405, "操作对象不明确！");
		}
	}
	
}
