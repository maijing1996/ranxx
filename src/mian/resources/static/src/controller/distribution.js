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
		elem: '#OM-distribution-identity',
		url: '/manager/distribution/identity/info',
		cols: [[
			{type: 'checkbox', fixed: 'left', width: 100},
			{field: 'id', title: '编号'},
			{field: 'name', title: '名称'},
			{field: 'apply_amount', title: '申请所需金额'},
			{field: 'amount', title: '获得代金券'},
			{field: 'buy_discount', title: '购买商品折扣%'},
			{field: 'superiorsname', title: '上级名称'},
			{field: 'commission', title: '下级购买商品佣金比例%'},
			{field: 'recommend_distribution_commission', title: '推荐分销代理奖励%'},
			{field: 'is_sale', title: '是否显示'},
			{title: '操作', width: 200, align:'center', fixed: 'right', toolbar: '#table-distribution-identity'}
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
  
	table.on('tool(OM-distribution-identity)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/distribution/identity/delete",
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
	                        	table.reload('OM-distribution-identity', null);
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
	
	table.render({
		elem: '#OM-distribution-distributionUser',
		url: '/manager/distribution/distributionUser/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号', width: 100},
			{field: 'uid', title: '用户名'},
			{field: 'oauth_nickname', title: '会员昵称'},
			{field: 'integral', title: '积分'},
			{field: 'user_money', title: '余额'},
			{field: 'name', title: '申请身份'},
			{field: 'is_work', title: '状态'},
			{field: 'distribution_apply_add_time', title: '申请时间'},
			{title: '操作', width: 200, align:'center', fixed: 'right', toolbar: '#table-distribution-distributionUser'}
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
  
	table.on('tool(OM-distribution-distributionUser)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/distribution/distributionUser/delete",
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
	                        	table.reload('OM-distribution-distributionUser', null);
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
	
	table.render({
		elem: '#OM-distribution-dcenter',
		url: '/manager/distribution/dcenter/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号', width: 100},
			{field: 'name', title: '名称'},
			{title: '操作', width: 200, align:'center', fixed: 'right', toolbar: '#table-distribution-dcenter'}
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
  
	table.on('tool(OM-distribution-dcenter)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/distribution/dcenter/delete",
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
	                        	table.reload('OM-distribution-dcenter', null);
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
	
	exports('distribution', {})
});