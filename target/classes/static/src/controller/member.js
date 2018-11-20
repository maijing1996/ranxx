/**

 @Name：order 会员模块控制
 @Author：偶木
 @Date：2018-08-28
    
 */


layui.define(['table', 'form'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	form = layui.form;
	
	//用户列表
	table.render({
		elem: '#OM-member-list',
		url: '/manager/user/list',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'nickname', title: '用户昵称'},
			{field: 'openid', title: 'openid'},
			{field: 'headimgurl', title: '头像'},
			{field: 'grade', title: '分数'},
			{field: 'nowLevel', title: '关卡'},
			/*{field: 'is_fx', title: '分销人员'},
			{field: 'add_date', title: '注册时间'},*/
			{title: '操作', width: 200, align:'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',

		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//会员列表_监听工具条
	table.on('tool(OM-member-list)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/user/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.desc,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-member-list', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit') {
			//location.hash='/system/404';
			//console.log("id:"+this.id);
			//console.log(data);
			view(null).render('member/component/member_details', data).done(function(){
				console.log(data)
				form.render(null, 'OM-member-list-temp');
    
				//监听提交
				/*form.on('submit(LAY-user-back-submit)', function(data){
					var field = data.field; //获取提交的字段

					//提交 Ajax 成功后，关闭当前弹层并重载表格
					//$.ajax({});
					layui.table.reload('OM-member-level'); //重载表格
					layer.close(index); //执行关闭 
				});*/
			});
		}
	});
	
	//积分明细
	table.render({
		elem: '#OM-member-integral',
		url: '/manager/member/financial/integral', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'oauth_nickname', title: '会员昵称'},
			{field: 'fee', title: '积分数额'},
			{field: 'account_fee', title: '剩余积分'},
			{field: 'info', title: '积分描述'},
			{field: 'type', title: '积分状态'},
			{field: 'add_date', title: '操作日期'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',

		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//会员等级
	table.render({
		elem: '#OM-member-level',
		url: '/manager/member/level/info', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'title', title: '等级名称'},
			{field: 'amount', title: '消费额度'},
			{field: 'rebate', title: '折扣率'},
			{field: 'info', title: '等级描述'},
			{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-toolbar-level'}
		]],
		page: false,
		height: 'full-320',
		
		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//会员等级_监听工具条
	table.on('tool(OM-member-level)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此会员等级？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/member/level/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: true,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.desc,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-member-level', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit'){
			admin.popup({
				title: '编辑管理员',
				area: ['420px', '450px'],
				id: 'LAY-popup-user-add',
				success: function(layero, index){
					view(this.id).render('member/component/level_edit', data).done(function(){
						form.render(null, 'popup-form-edit');
            
						//监听提交
						form.on('submit(LAY-user-back-submit)', function(data){
							var field = data.field; //获取提交的字段

							//提交 Ajax 成功后，关闭当前弹层并重载表格
							//$.ajax({});
							layui.table.reload('OM-member-level'); //重载表格
							layer.close(index); //执行关闭 
						});
					});
				}
			});
		}
	});
	
	//资金明细
	table.render({
		elem: '#OM-member-property',
		url: '/manager/member/financial/info', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'oauth_nickname', title: '会员昵称'},
			{field: 'fee', title: '金额'},
			{field: 'account_fee', title: '剩余金额'},
			{field: 'info', title: '操作描述'},
			{field: 'type', title: '操作状态'},
			{field: 'add_date', title: '操作日期'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',

		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//会员提现
	table.render({
		elem: '#OM-member-withdraw',
		url: '/manager/member/withdrawal/info', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'oauth_nickname', title: '会员昵称'},
			{field: 'fee', title: '提现金额'},
			{field: 'account_fee', title: '剩余金额'},
			{field: 'info', title: '提现描述'},
			{field: 'add_date', title: '申请时间'},
			{field: 'pay_state', title: '提现状态'},
			{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#toolbar-member-withdraw'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',

		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//会员提现_监听工具条
	table.on('tool(OM-member-withdraw)', function(obj){
		var data = obj.data;
		if(obj.event === 'disagree'){
			layer.confirm('确定不同意此用户提现吗？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id, "state": -1};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/member/user/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: true,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.desc,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-member-withdraw', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'agree'){
			layer.confirm('确定同意此用户提现吗？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id, "state": 1};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/member/check",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.desc,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-member-withdraw', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		}
	});

	exports('member', {})
});