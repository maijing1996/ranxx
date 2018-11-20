/**

 @Name：order 订单模块控制
 @Author：偶木
 @Date：2018-08-28
    
 */


layui.define(['table', 'form'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	form = layui.form;
	
	//订单列表
	table.render({
		elem: '#OM-order-list',
		url: '/manager/order/order/info',
		//ajax
		contentType: 'application/json;charset=UTF-8',
		method: 'post',
		//数据预解析
		/*parseData: function(res){ //res 即为原始返回的数据
			return {
				"code": res.descCode, //解析接口状态
				"msg": res.desc, //解析提示文本
				"count": res.result.total, //解析数据长度
				"data": res.result.list //解析数据列表
			};
		},*/
		//用于对分页请求的参数：page、limit重新设定名称
		request: {
			//pageName: 'page', //页码的参数名称，默认：page
			limitName: 'size' //每页数据量的参数名，默认：limit
		},
		//规定的数据格式
		response: {
			//statusName: 'descCode', //规定数据状态的字段名称，默认：code
			statusCode: 200 //规定成功的状态码，默认：0
			//msgName: 'desc', //规定状态信息的字段名称，默认：msg
			//countName: 'total', //规定数据总数的字段名称，默认：count
			//dataName: 'data', //规定数据列表的字段名称，默认：data
		},
		//表格设置
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', width: 100, title: '订单号', sort: true},
			{field: 'name', title: '收货人'},
			{field: 'price', title: '订单额（元）'},
			{field: 'pay_price', title: '实付额（元）'},
			{field: 'type_name', title: '支付方式'},
			{field: 'state_name', title: '订单状态'},
			{field: 'add_date', title: '下单时间'},
			{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-order-list'}
		]],
		page: true,
		limit: 20,
		height: 'full-320',
		text: '对不起，加载出现异常！'
	});
	
	//商品评论
	table.render({
		elem: '#OM-order-shop-evaluate',
		url: '/manager/order/evaluate/info',
		//设置表格
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'uid', title: '用户名称'},
			{field: 'info', title: '评论内容'},
			{field: 'title', title: '商品名称'},
			{field: 'is_show', title: '是否显示'},
			{field: 'add_date', title: '添加时间'},
			{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-order-shop-evaluate'}
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
  
	//商品评论_监听工具条
	table.on('tool(OM-order-shop-evaluate)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此用户信息？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/order/evaluate/delete",
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
	                        	table.reload('OM-order-shop-evaluate', null);
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
					view(this.id).render('order/component/evaluation_edit', data).done(function(){
						form.render(null, 'popup-form-edit');
            
						//监听提交
						form.on('submit(LAY-user-back-submit)', function(data){
							var field = data.field; //获取提交的字段

							//提交 Ajax 成功后，关闭当前弹层并重载表格
							//$.ajax({});
							layui.table.reload('OM-order-shop-evaluate'); //重载表格
							layer.close(index); //执行关闭 
						});
					});
				}
			});
		}
	});

	exports('order', {})
});