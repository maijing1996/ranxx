/**

 @Name：order 会员模块控制
 @Author：偶木
 @Date：2018-09-05
    
 */


layui.define(['table', 'form'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	form = layui.form;
	
	table.render({
		elem: '#OM-wechat-reply',
		url: '/manager/wechat/reply/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'title', title: '标题'},
			{field: 'info', title: '回复内容'},
			{field: 'trigger_type', title: '触发类型'},
			{field: 'reply_type', title: '回复类型'},
			{title: '操作', width: 200, align:'center', fixed: 'right', toolbar: '#table-wechat-reply'}
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
  
	table.on('tool(OM-wechat-reply)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/wechat/reply/delete",
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
	                        	table.reload('OM-wechat-reply', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit') {
			view('OM-wechat-reply-temp').render('wechat/component/reply-edit', data).done(function() {
				console.log(data)
				form.render(null, 'layuiadmin-form-useradmin-aa');
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
	
	table.render({
		elem: '#OM-wechat-menu',
		url: '/manager/wechat/share/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号', width: 100},
			{field: 'imgUrl', title: '图片url'},
			{field: 'titleText', title: '标题文字'},
			{title: '操作', width: 350, align:'center', fixed: 'right', toolbar: '#table-wechat-menu'}
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
  
	table.on('tool(OM-wechat-menu)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/wechat/share/delete",
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
	                        	table.reload('OM-wechat-menu', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit') {
			//location.hash='/system/404';
			console.log("id:"+this.id);
			//console.log(data);
			view(null).render('member/component/member_details', data).done(function(){
				console.log(data)
				form.render(null, 'popup-form-edit');
    
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
	
	exports('wechat', {})
});