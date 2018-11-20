package com.ranxx.webSocket;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ranxx.model.Message;
import com.ranxx.model.User;
import com.ranxx.model.socket.BattleRecord;
import com.ranxx.model.socket.UserSocket;
import com.ranxx.service.BattleRecordService;
import com.ranxx.service.UserSocketService;
import com.ranxx.util.ApplicationContextRegister;
import com.ranxx.util.DateUtil;
import com.ranxx.util.GetRandomStr;
import com.ranxx.util.JsonUtil;
import com.ranxx.util.StringUtil;
 
 /**
 * @author james
 * @create 2018-08-10 16:34
 */
@ResponseBody
@Controller
@ServerEndpoint("/")
public class WebSocket {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static ApplicationContext applicationContext;
     
	public static void setApplicationContext(ApplicationContext applicationContext) {
		WebSocket.applicationContext = applicationContext;
	}
	 
    private UserSocketService userSocketService;
    
    private BattleRecordService battleRecordService;

	/**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<Long, WebSocket> clients = new ConcurrentHashMap<Long, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户uid
     */
    private Long uid;
    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session)
    {
        onlineNumber++;
        logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        this.session = session;
        logger.info("现在来连接的客户id："+session.getId());
//        clients.put(session.getId(), this);
//        String type = map.get("type");
//        String openId = map.get("openId");
//        
        
//        try {
//            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//            //先给所有人发送通知，说我上线了
//            Map<String,Object> map1 = new HashMap<String,Object>();
//            map1.put("messageType",1);
//            map1.put("username",username);
//            sendMessageAll(JSON.toJSONString(map1),username);
// 
//            //把自己的信息加入到map当中去
//            clients.put(username, this);
//            //给自己发一条消息：告诉自己现在都有谁在线
//            Map<String,Object> map2 = new HashMap<String,Object>();
//            map2.put("messageType",3);
//            //移除掉自己
//            Set<String> set = clients.keySet();
//            map2.put("onlineUsers",set);
//            sendMessageTo(JSON.toJSONString(map2),username);
//        }
//        catch (IOException e){
//            logger.info(username+"上线的时候通知所有人发生了错误");
//        }
    }
 
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误"+error.getMessage());
        //error.printStackTrace();
    }
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
//        webSockets.remove(this);
        if(uid != null) {
        	clients.remove(uid);
        	logger.info("用户移除成功！！！");
        }
//        try {
//            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
//            Map<String,Object> map1 =new HashMap<String,Object>();
//            map1.put("messageType",2);
//            map1.put("onlineUsers",clients.keySet());
//            map1.put("username",username);
//            sendMessageAll(JSON.toJSONString(map1),username);
//        }
//        catch (IOException e){
//            logger.info(username+"下线的时候通知所有人发生了错误");
//        }
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }
 
    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	
    	ApplicationContext applicationContext2 = ApplicationContextRegister.getApplicationContext();
    	UserSocketService userSocketService = applicationContext2.getBean(UserSocketService.class);
    	ApplicationContext applicationContext3 = ApplicationContextRegister.getApplicationContext();
    	BattleRecordService battleRecordService = applicationContext3.getBean(BattleRecordService.class);
//    	userSocketService = applicationContext.getBean(UserSocketService.class);
//      battleRecordService = applicationContext.getBean(BattleRecordService.class);
        
        try {
            logger.info("来自客户端消息：" + message+" 客户端的id是："+session.getId());
            JSONObject message2 = JSON.parseObject(message);
//            Message message2 = JsonUtil.getObject(message, Message.class);
            logger.info("来自客户端消息：" + message2.toString() +" 客户端的id是："+session.getId());
//            String textMessage = jsonObject.getString("message");
            //--------------------------------------------------------------
//            userService = applicationContext.getBean(UserService.class);
//            User user = userService.getUser(textMessage);
//            logger.info("查询的消息：", user);
            //--------------------------------------------------------------
           /* String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String,Object> map1 =new HashMap<String,Object>();
            map1.put("messageType",4);
            map1.put("fromusername",fromusername);
            if(tousername.equals("All")){
                map1.put("tousername","所有人");
                sendMessageAll(JSON.toJSONString(map1),fromusername);
            } else{
                map1.put("tousername",tousername);
                sendMessageTo(JSON.toJSONString(map1),tousername);
            }*/
            //---------------------------------------------------------------
            
            String type = message2.getString("type");
//            if(message2.getType() != null) {
//            	logger.info("2"+message2.getType());
//            } else {
//            	logger.info("1");
//            }
//            logger.info(type);
            //String type = jsonObject.getString("type");
            
//            logger.info("........");
            if("login".equals(message2.getString("type"))) {//登录
            	
            	logger.info("你进入了login......");
//            	String openId = message2.getOpenId();
            	String openId = message2.getString("openId");
            	//String openId = jsonObject.getString("openId");
            	
            	GetRandomStr random = new GetRandomStr();
            	String token = random.getRandomString(32);
            	userSocketService.saveToken(token,openId);
            	
            	Map<String,Object> map1 =new HashMap<String,Object>();
                User user = userSocketService.getUser(openId);
                uid = user.getId();
                clients.put(uid, this);
                UserSocket userSocket = new UserSocket();
                userSocket.setAvatar(user.getHeadimgurl());
                userSocket.setNickName(user.getNickname());
                userSocket.setToken(user.getToken());
                map1.put("code", 200);
                map1.put("msg", "消息发送成功！-----------1111");
                map1.put("data", userSocket);
                sendMessageTo(JSON.toJSONString(map1));
                logger.info(JSON.toJSONString(map1));
                System.out.println(JSON.toJSONString(map1));
                
            } else if("createRoom".equals(type)){//创建房间
            	
            	try {
            		String token = message2.getString("token");
            		//String token = jsonObject.getString("token");
//            	 userSocketService = applicationContext.getBean(UserSocketService.class);
            		User user2 = userSocketService.getUserByToken(token);//获取到user
            		
            		Long room_id = null;
            		Long owner_uid = user2.getId();
            		Integer timestamp = DateUtil.getTimestamp2();
            		BattleRecord battleRecord2 = battleRecordService.getByOwnerId(owner_uid);
            		if(battleRecord2 != null) {
            			room_id = battleRecord2.getRoom_id();
            		}else {
            			BattleRecord battleRecord = new BattleRecord(); 
            			battleRecord.setAdd_time(timestamp);
            			battleRecord.setOwner_uid(owner_uid);
            			battleRecordService.createRoom(battleRecord);
            			room_id = battleRecord.getRoom_id();
            		}
            		BattleRecord battleRecord = battleRecordService.getById(room_id);//房间信息
            		
            		UserSocket userSocket = new UserSocket();
            		userSocket.setAvatar(user2.getHeadimgurl());
            		userSocket.setNickName(user2.getNickname());
            		userSocket.setToken(token);
            		userSocket.setOwner(1);
            		
            		Map<String,Object> map2 =new HashMap<String,Object>();
            		Map<String,Object> map3 =new HashMap<String,Object>();
            		Map<String,Object> map4 =new HashMap<String,Object>();
            		map4.put("user1", userSocket);
            		map4.put("user2", null);
            		
            		map3.put("userList", map4);
            		map3.put("roomId", room_id);
            		map3.put("otherInfo", battleRecord);
            		
            		map2.put("code", 200);
            		map2.put("msg", "消息发送成功！");
            		map2.put("data", map3);
            		sendMessageTo(JSON.toJSONString(map2));
					
				} catch (Exception e) {
					Map<String,Object> map2 =new HashMap<String,Object>();
					map2.put("code", 201);
            		map2.put("msg", "创建失败！");
            		sendMessageTo(JSON.toJSONString(map2));
				}
            	 
            }else if("enterRoom".equals(type)) {
            	
            	try {
            		String token = message2.getString("token");
            		String roomId = message2.getString("roomId");
            		//String token = jsonObject.getString("token");//客人
            		//String roomId = jsonObject.getString("roomId");//房間
            		Long room_id = StringUtil.strToLong(roomId);
            		
            		User user2 = userSocketService.getUserByToken(token);//获取到客人信息user
            		UserSocket userSocket2 = new UserSocket();
            		userSocket2.setAvatar(user2.getHeadimgurl());
            		userSocket2.setNickName(user2.getNickname());
            		userSocket2.setToken(token);
            		userSocket2.setOwner(0);
            		userSocket2.setReady(0);
            		
            		BattleRecord battleRecord2 = battleRecordService.getById(room_id);//获取房间信息
            		
            		Long owner_uid = battleRecord2.getOwner_uid();
            		if(owner_uid == 0) {
            			Map<String, Object> map4 = new HashMap<String, Object>();
            			map4.put("code", 303);
            			map4.put("msg", "房间已解散！");
            			sendMessageTo(JSON.toJSONString(map4));
            		}else {
            			User user1 = userSocketService.getById(owner_uid);
            			UserSocket userSocket1 = new UserSocket();
            			userSocket1.setAvatar(user1.getHeadimgurl());
            			userSocket1.setNickName(user1.getNickname());
            			userSocket1.setToken(user1.getToken());
            			userSocket1.setOwner(1);
            			
            			Long uid = user2.getId();
            			Integer timestamp = DateUtil.getTimestamp2();
            			if(battleRecord2.getClient_uid() == 0) {
            				
            				BattleRecord battleRecord = new BattleRecord();
            				battleRecord.setEnter_time(timestamp);
            				battleRecord.setRoom_id(room_id);
            				battleRecord.setClient_uid(uid);
            				battleRecordService.update(battleRecord);//进入房间
            				BattleRecord battleRecord3 = battleRecordService.getById(room_id);//修改后房间信息
            				
            				Map<String, Object> map2 = new HashMap<String, Object>();
            				Map<String, Object> map3 = new HashMap<String, Object>();
//            				List<UserSocket> userList = new ArrayList<UserSocket>();
//            				userList.add(userSocket1);
//            				userList.add(userSocket2);
            				Map<String,Object> map4 =new HashMap<String,Object>();
                    		map4.put("user1", userSocket1);
                    		map4.put("user2", userSocket2);
//            				JSONArray userList = JSONArray.fromObject(list);
            				map3.put("roomId", room_id);
            				map3.put("userList", map4);
            				map3.put("otherInfo", battleRecord3);
            				
            				map2.put("code", 200);
            				map2.put("msg", "进入房间成功！");
            				map2.put("data", map3);
            				sendMessageTo(JSON.toJSONString(map2));
            				clients.get(user1.getId()).sendMessageTo(JSON.toJSONString(map2));
//               	 	battleRecordService.enterRoom(uid, roomId, timestamp);//進入房間
            			}else {
            				Map<String, Object> map = new HashMap<String, Object>();
            				map.put("code", 301);
            				map.put("msg", "房间已满！");
            				sendMessageTo(JSON.toJSONString(map));
            			}
            		}
					
				} catch (Exception e) {
					Map<String, Object> map5 = new HashMap<String, Object>();
    				map5.put("code", 303);
    				map5.put("msg", "网络错误！");
    				sendMessageTo(JSON.toJSONString(map5));
				}
            	
            }else if("quitRoom".equals(type)) {//退出房间
            	
            	String token = message2.getString("token");
            	Long roomId = StringUtil.strToLong(message2.getString("roomId"));
            	//String token = jsonObject.getString("token");
            	//Long roomId = StringUtil.strToLong(jsonObject.getString("roomId"));
            	BattleRecord battleRecord = battleRecordService.getById(roomId);//房间信息
            	
            	User user = userSocketService.getUserByToken(token);//退出用户的信息
            	if(battleRecord.getOwner_uid().equals(user.getId())) {//是否是房主
            		
            		User user2 = userSocketService.getById(battleRecord.getClient_uid());
            		UserSocket userSocket2 = new UserSocket();
                	userSocket2.setAvatar(user2.getHeadimgurl());
                	userSocket2.setNickName(user2.getNickname());
                	userSocket2.setToken(user2.getToken());
                	userSocket2.setOwner(1);
                	
            		BattleRecord battleRecord2 = new BattleRecord();
            		battleRecord2.setRoom_id(roomId);
            		battleRecord2.setOwner_uid(battleRecord.getClient_uid());
            		battleRecord2.setClient_uid((long) 0);
            		battleRecord2.setOwner_total(0);
            		battleRecord2.setClient_total(0);
            		battleRecord2.setReady_time(0);
            		boolean quit = battleRecordService.update(battleRecord2);
            		BattleRecord battleRecord3 = battleRecordService.getById(roomId);//修改后房间信息
            		
            		if(quit) {
            			Map<String, Object> map = new HashMap<String, Object>();
            			Map<String, Object> map3 = new HashMap<String, Object>();
//        				List<UserSocket> userList = new ArrayList<UserSocket>();
//        				userList.add(userSocket2);
        				Map<String,Object> map4 =new HashMap<String,Object>();
                		map4.put("user1", userSocket2);
                		map4.put("user2", null);
//        				JSONArray userList = JSONArray.fromObject(list);
        				map3.put("roomId", roomId);
        				map3.put("userList", map4);
            			map3.put("otherInfo", battleRecord3);
        				
        				map.put("code", 200);
        				map.put("msg", "退出房间成功！");
        				map.put("data", map3);
        				sendMessageTo(JSON.toJSONString(map));
            		}else {
            			Map<String, Object> map2 = new HashMap<String, Object>();
        				map2.put("code", 201);
        				map2.put("msg", "退出房间失败！");
        				sendMessageTo(JSON.toJSONString(map2));
            		}
            		
            	}else {
                	User user1 = userSocketService.getById(battleRecord.getOwner_uid());
                	UserSocket userSocket1 = new UserSocket();
                	userSocket1.setAvatar(user1.getHeadimgurl());
                	userSocket1.setNickName(user1.getNickname());
                	userSocket1.setToken(user1.getToken());
            		userSocket1.setOwner(1);
                	
            		BattleRecord battleRecord3 = new BattleRecord();
            		battleRecord3.setRoom_id(roomId);
            		battleRecord3.setClient_uid((long) 0);
            		battleRecord3.setEnter_time(0);
            		battleRecord3.setClient_total(0);
            		boolean quit = battleRecordService.update(battleRecord3);
            		if(quit) {
            			Map<String, Object> map = new HashMap<String, Object>();
            			Map<String, Object> map3 = new HashMap<String, Object>();
//        				List<UserSocket> userList = new ArrayList<UserSocket>();
//        				userList.add(userSocket1);
            			Map<String,Object> map4 =new HashMap<String,Object>();
                		map4.put("user1", userSocket1);
                		map4.put("user2", null);
                		
        				map3.put("roomId", roomId);
        				map3.put("userList", map4);
            			map3.put("otherInfo", battleRecord3);
        				
        				map.put("code", 200);
        				map.put("msg", "退出房间成功！");
        				map.put("data", map3);
        				sendMessageTo(JSON.toJSONString(map));
            		}else {
            			Map<String, Object> map2 = new HashMap<String, Object>();
        				map2.put("code", 201);
        				map2.put("msg", "退出房间失败！");
        				sendMessageTo(JSON.toJSONString(map2));
            		}
            		
            	}
            	
            }else {
             	Map<String,Object> map =new HashMap<String,Object>();
             	map.put("type", "ping");
            	sendMessageTo(JSON.toJSONString(map));
            	clients.get(uid).sendMessageTo(JSON.toJSONString(map));
            }
        } catch (Exception e){
        	Map<String,Object> map =new HashMap<String,Object>();
         	map.put("msg", "服务器发生错误了");
        	sendMessageTo(JSON.toJSONString(map));
            logger.info("发生了错误了");
            logger.info(JSONObject.toJSONString(e));
        }
 
    }
    
    public void sendMessageTo(String message) throws IOException {
    	//, String ToUserName
//        for (WebSocket item : clients.values()) {
//            if (item.username.equals(ToUserName) ) {
//                item.session.getAsyncRemote().sendText(message);
//                break;
//            }
//        }
    	this.session.getAsyncRemote().sendText(message);
    }
 
//    public void sendMessageTo(String message, String username) throws IOException {
//        for (WebSocket item : clients.values()) {
//            if (item.username.equals(username) ) {
//                item.session.getAsyncRemote().sendText(message);
//                break;
//            }
//        }
//    	this.session.getAsyncRemote().sendText(message);
//    }
    
    public void sendMessageAll(String message,String FromUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }
 
    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }
 
}
