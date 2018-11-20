package com.ranxx.controller;
import java.util.HashMap;
import java.util.List;
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

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.ranxx.model.GameSet;
import com.ranxx.model.User;
import com.ranxx.model.UserLevelGrade;
import com.ranxx.model.WxConfig;
import com.ranxx.model.common.BaseResponse;
import com.ranxx.model.dto.UserLevelGradeMax;
import com.ranxx.service.GameSetService;
import com.ranxx.service.UserLevelGradeService;
import com.ranxx.service.UserService;
import com.ranxx.service.WxConfigService;
import com.ranxx.util.BusinessException;
import com.ranxx.util.ContentValues;
import com.ranxx.util.HttpRequest;
import com.ranxx.util.StringUtil;

import net.sf.json.JSONObject;


@ResponseBody
@RestController
@RequestMapping(value = "/run/user")
public class UserInfoController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private GameSetService gameSetService;
	@Autowired
	private WxConfigService wxConfigService;
	@Autowired
	private UserLevelGradeService userLevelGradeService;
	
	/**
	 * 多人模式玩的的次数
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMoreNum", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateMoreNum(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		
		Integer more_num = StringUtil.strToInt(map.get("more_num"));
		Long uid = StringUtil.strToLong(map.get("uid"));
		
		User user = new User();
		user.setMore_num(more_num);
		user.setId(uid);
		if(uid != null) {
			userService.update(user);
			logger.info("多人模式玩的的次数修改成功！");
			return baseResponse.setValue("多人模式玩的的次数修改成功！", null);
		}else {
			return baseResponse.setValue("uid为空", null);
		}
	}
	
	/**
	 * 皮肤
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateFashion", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateFashion(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		
		Integer fashion_num = StringUtil.strToInt(map.get("fashion_num"));
		Long uid = StringUtil.strToLong(map.get("uid"));
		
		User user = new User();
		user.setFashion_num(fashion_num);
		user.setId(uid);
		if(uid != null) {
			userService.update(user);
			logger.info("皮肤修改成功！");
			return baseResponse.setValue("皮肤修改成功！", null);
		}else {
			return baseResponse.setValue("uid为空", null);
		}
	}
	
	/**
	 * 多人模式赢的次数
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMoreWin", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateMoreWin(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		
		Integer more_win_num = StringUtil.strToInt(map.get("more_win_num"));
		Long uid = StringUtil.strToLong(map.get("uid"));
		
		User user = new User();
		user.setMore_win_num(more_win_num);
		user.setId(uid);
		if(uid != null) {
			userService.update(user);
			logger.info("多人模式赢的次数修改成功！");
			return baseResponse.setValue("多人模式赢的次数修改成功！", null);
		}else {
			return baseResponse.setValue("uid为空", null);
		}
	}
	
	/**
	 * 修改用户通关信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCarnet", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateCarnet(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		Integer level = StringUtil.strToInt(map.get("level"));
		Integer is_carnet = StringUtil.strToInt(map.get("is_carnet"));
		Long uid = StringUtil.strToLong(map.get("uid"));
		
		userLevelGradeService.updateCarnet(level, is_carnet, uid);
		logger.info("修改通过关卡成功！");
		return baseResponse.setValue("修改成功！", null);
	}
	
	/**
	 * 回放游戏过程
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/replay", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse saveReplay(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		String openid = map.get("openid");
		String replay = map.get("replay");
		
		userService.updateReplay(openid,replay);
		
		return baseResponse.setValue(null, null);
	}
	
	/**
	 * 修改用户的观看次数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateLooNum", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateLooNum(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		Long uid = StringUtil.strToLong(map.get("uid"));
		Integer level = StringUtil.strToInt(map.get("level"));	
		Integer lookNum = StringUtil.strToInt(map.get("lookNum"));
		
		userLevelGradeService.updateLooNum(uid, level, lookNum);
		logger.info("修改关卡观看次数成功！");
		return baseResponse.setValue("操作成功！", null);
	}
	
	/**
	 * 修改关卡
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateLevel", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse updateNowLevel(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		userService.updateLevel(map.get("openid"),StringUtil.strToInt(map.get("nowLevel")));
		return baseResponse.setValue(null, null);
	}
	
	/**
	 * 修改用户分数
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse update(@RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		String openid = map.get("openid");
		String string = map.get("grade");
		Integer level = StringUtil.strToInt(map.get("level"));
		Integer grade = StringUtil.strToInt(map.get("grade"));
		Long uid = StringUtil.strToLong(map.get("uid"));
		Integer maxgrade = StringUtil.strToInt(map.get("maxgrade"));
		
		if(grade != null && maxgrade != null) {
			if(level != null) {
//				userLevelGradeService.updateLevelGrade(uid, level, grade);
//				logger.info("修改关卡分数成功！");
//				
//				boolean result = userService.update(openid,maxgrade);
//				
//				logger.info("修改总分成功！"+result);
				boolean update = userLevelGradeService.updateGrade(uid, level, grade, openid, maxgrade);
				return baseResponse.setValue("修改分数成功！", update);
			}else {
				return baseResponse.setError(500, "关卡为空！");
			}
		}else {
			return baseResponse.setError(500, "分数为空！");
		}
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public BaseResponse listinfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
		
		BaseResponse baseResponse = new BaseResponse();
		String openid = map.get("openid");
		User user = userService.getUser(openid);
		PageInfo<User> result = userService.listinfo();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("user", user);
		map2.put("ranklist", result);
		logger.info("查询成功！！"+map2);
		return baseResponse.setValue("操作成功！", map2);
	}
	
	/**
	 * 用户基本信息
	 * @param request
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
	public BaseResponse getInfo(HttpServletRequest request, @RequestBody Map<String, String> map) throws BusinessException {
		logger.info("登入接口....");
		BaseResponse baseResponse = new BaseResponse();
		String code = map.get("code");
		//Integer grade = StringUtil.strToInt(map.get("grade"));
		String nickname = map.get("nickname");
		String headimgurl = map.get("headimgurl");
		Integer sex = StringUtil.strToInt(map.get("sex"));
		Integer levelNum = StringUtil.strToInt(map.get("levelNum"));
		
		User user = new User();
		if(nickname != null) {
			user.setNickname(nickname);
		}else {
			user.setNickname("");
		}
		user.setGrade(0);
		user.setHeadimgurl(headimgurl);
//		user.setNowLevel(1);
		if(sex == null) {
			user.setSex(0);
		}else {
			user.setSex(sex);
		}
		Map<String, Object> m = new HashMap<String, Object>(); 
	    // 登录凭证不能为空  
	    if (code == null || code.length() == 0) {  
	        m.put("status", 0);
	        m.put("msg", "code 不能为空");
	        return baseResponse.setValue(null, m);
	    } 
	    WxConfig config = wxConfigService.getWxConfig();
	    // 小程序唯一标识 (在微信小程序管理后台获取)
	    String wxspAppid = config.getAppid();  
	    // 小程序的 app secret (在微信小程序管理后台获取)  
	    String wxspSecret = config.getSecret();  
	    // 授权（必填）  
	    String grant_type = "authorization_code";  
	    
	    // 获取接口访问凭证
	    //String accessToken = CommonUtil.getToken(wxspAppid, wxspSecret).getToken();

	    
		//1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid  
		// 请求参数  
		String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="  
		   + grant_type;  
		// 发送请求  
		String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);  
		logger.info(sr);
		// 解析相应内容（转换成json对象）  
		JSONObject json = JSONObject.fromObject(sr);
		//JSONObject json = new JSONObject(sr);  
		// 获取会话密钥（session_key）  
		//String session_key = json.get("session_key").toString();
		Map<String, Object> infoMap = new HashMap<String,Object>();
		//游戏设置数据
		GameSet gameSet = gameSetService.getInfo();
		infoMap.put("gameSet", gameSet);
		try {
			// 用户的唯一标识（openid）
			String openid = (String) json.get("openid");
			user.setOpenid(openid);
			User u = userService.getUser(openid);
			
			if(u != null) {
				//-------------------------------------------------------------------
				//判断总共关卡数，如不相等则修改
				Long uid = u.getId();
				List<UserLevelGradeMax> list = userLevelGradeService.getLevelGrade(uid);
				
				if(list != null) {
					int size = list.size();
					if(levelNum > size) {
						for(int i=size+1;i<=levelNum;i++) {
							userLevelGradeService.save(uid, i);
						}
					}
					
				}else {
					for(int i=1;i<=levelNum;i++) {
						userLevelGradeService.save(uid, i);
					}
				}
				List<UserLevelGradeMax> list2 = userLevelGradeService.getLevelGrade(uid);
				logger.info("关卡配置："+list2);
//				Map<String, Object> map2 = new HashMap<String, Object>();
				infoMap.put("lockLevelList", list2);
				//-------------------------------------------------------------------
				
				String nickname2 = u.getNickname();
				String headimgurl2 = u.getHeadimgurl();
				if(!nickname.equals(nickname2) || !headimgurl.equals(headimgurl2)) {
					userService.updateUser(user.getOpenid(), user.getNickname(), user.getHeadimgurl());
					u.setHeadimgurl(user.getHeadimgurl());
					u.setNickname(user.getNickname());
					infoMap.put("info", u);
					return baseResponse.setValue("result", JSONObject.fromObject(infoMap));
				}else {
					logger.info("返回数据.......", infoMap);
					infoMap.put("info", u);
					return baseResponse.setValue("result", JSONObject.fromObject(infoMap));
				}
			}else {
//				boolean save = userService.save(user.getOpenid(),user.getGrade(),user.getSex(),user.getNickname(),user.getHeadimgurl());
				boolean save = userService.save(user);
				UserLevelGrade userLevelGrade = new UserLevelGrade();
				Long uid = user.getId();
				for(int i=1;i<=levelNum;i++) {
					userLevelGradeService.save(uid, i);
				}
				
				List<UserLevelGradeMax> list2 = userLevelGradeService.getLevelGrade(uid);
				infoMap.put("lockLevelList", list2);
				
				logger.info("保存成功！"+save);
				user.setFashion_num(0);
				user.setMore_num(0);
				user.setMore_win_num(0);
				infoMap.put("info", user);
				return baseResponse.setValue("result", JSONObject.fromObject(infoMap));
			}
		}catch (Exception e) {
			logger.error("没有获取到openid！"+e.toString());
			return baseResponse.setError(500, "code超时了！请重新请求！");
		}
		
	}
	 
	/**
	   * 获取用户信息
	   * 
	   * @param accessToken 接口访问凭证
	   * @param openId 用户标识
	   * @return WeixinUserInfo
	   */
	  /*public  User getUserInfo(String accessToken, String openId) {
	    
	    User weixinUserInfo = null;
	    //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID
	    // 拼接请求地址 
	    String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	    requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	    // 获取用户信息
	    JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	    
	    if (null != jsonObject) {
	    	
	      try {
	        weixinUserInfo = new User();
	        // 用户的标识
	        weixinUserInfo.setOpenid(jsonObject.getString("openid"));
	        System.out.println(jsonObject.getString("openid"));
	        // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	        weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
	        // 用户关注时间
	        Integer time = StringUtil.strToInt(jsonObject.getString("subscribe_time"));
	        weixinUserInfo.setSubscribe_time(time);
	        // 昵称
	        weixinUserInfo.setNickname(jsonObject.getString("nickname"));
	        // 用户的性别（1是男性，2是女性，0是未知）
	        weixinUserInfo.setSex(jsonObject.getInt("sex"));
	        // 用户所在国家
	        weixinUserInfo.setCountry(jsonObject.getString("country"));
	        // 用户所在省份
	        weixinUserInfo.setProvince(jsonObject.getString("province"));
	        // 用户所在城市
	        weixinUserInfo.setCity(jsonObject.getString("city"));
	        // 用户的语言，简体中文为zh_CN
	        weixinUserInfo.setLanguage(jsonObject.getString("language"));
	        // 用户头像
	        weixinUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
	        //用戶成绩（分数）
	        weixinUserInfo.setGrade(0);
	        
	      } catch (Exception e) {
	        if (0 == weixinUserInfo.getSubscribe()) {
	          logger.error("用户{}已取消关注", weixinUserInfo.getOpenid());
	        } else {
	          int errorCode = jsonObject.getInt("errcode");
	          String errorMsg = jsonObject.getString("errmsg");
	          logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
	        }
	      }
	    }
	    System.out.println("weixinUserInfo:"+weixinUserInfo);
	    return weixinUserInfo;
	  }*/
	  
}
