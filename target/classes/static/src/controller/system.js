/**

 @Name：order 管理员管理模块控制
 @Author：偶木
 @Date：2018-08-28
    
 */

 
layui.define(['table', 'form'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	form = layui.form;
	
	//管理员列表
	table.render({
		elem: '#OM-system-list',
		url: '/manager/admin/info', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'uid', title: '用户名'},
			{field: 'role_name', title: '角色类型'},
			{field: 'is_work', title: '用户状态'},
			{field: 'last_login_ip', title: '最近登录IP'},
			{field: 'last_login_time', title: '最近登录时间'},
			{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-tookbar-list'}
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
  
	//管理员列表_监听工具条
	table.on('tool(OM-system-list)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此管理员角色？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/admin/delete",
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
	                        	table.reload('OM-system-list', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit'){
			admin.popup({
				title: '编辑用户',
				area: ['500px', '450px'],
				id: 'OM-popup-system-add',
				success: function(layero, index){
					view(this.id).render('order/component/evaluation_edit', data).done(function(){
						form.render(null, 'popup-form-edit');
            
						//监听提交
						form.on('submit(LAY-user-front-submit)', function(data){
							var field = data.field; //获取提交的字段
		
							//提交 Ajax 成功后，关闭当前弹层并重载表格
							//$.ajax({});
							layui.table.reload('OM-system-list'); //重载表格
							layer.close(index); //执行关闭 
						});
					});
				}
			});
		}
	});
	
	//管理员角色
	table.render({
		elem: '#OM-system-role',
		url: '/manager/role/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号', width: 200},
			{field: 'title', title: '角色名称', width: 200},
			{field: 'info', title: '角色描述'},
			{title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-toolbar-role'}
		]],
		page: false,
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
  
	//管理员角色_监听工具条
	table.on('tool(OM-system-role)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此管理员角色？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/role/delete",
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
	                        	table.reload('OM-system-role', null);
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
					view(this.id).render('power/component/roleform_edit', data).done(function(){
						form.render(null, 'popup-form-edit');
            
						//监听提交
						form.on('submit(LAY-user-back-submit)', function(data){
							var field = data.field; //获取提交的字段

							//提交 Ajax 成功后，关闭当前弹层并重载表格
							//$.ajax({});
							layui.table.reload('OM-system-role'); //重载表格
							layer.close(index); //执行关闭 
						});
					});
				}
			});
		}
	});
	
	//系统操作日志
	table.render({
		elem: '#OM-system-log',
		url: '/manager/log/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'uid', title: '用户名'},
			{field: 'log_info', title: '日志内容'},
			{field: 'log_ip', title: '操作IP'},
			{field: 'log_state', title: '状态'},
			{field: 'add_date', title: '操作时间'},
			{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-toolbar-log'}
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
  
	//系统操作日志_监听工具条
	table.on('tool(OM-system-log)', function(obj){
		var data = obj.data;
		layer.confirm('确定删除此系统日志？', function(index){
			layer.close(index);
			
			//数据封装
			var req = {"id": data.id};
			//执行 Ajax 后重载
          	$.ajax({
				type: "POST",
				url: "/manager/log/delete",
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
                        	table.reload('OM-system-log', null);
                        }
                    });
				}, error: function(){
					location.hash='/system/404';
				}
	    	});
		});
	});
	
	exports('system', {})
});