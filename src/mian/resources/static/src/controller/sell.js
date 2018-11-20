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
		elem: '#OM-sell-detail',
		url: '/manager/sell/detail/info',
		cols: [[
			{field: 'id', title: '编号', width: 100},
			{field: 'title', title: '商品名称'},
			{field: 'sn', title: '货号'},
			{field: 'amount', title: '销售量'},
			{field: 'price', title: '销售价'},
			{field: 'add_date', title: '下单时间'}
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
	
	table.render({
		elem: '#OM-sell-member',
		url: '/manager/sell/member/info',
		cols: [[
			{field: 'ranking', title: '排行', width: 100},
			{field: 'nickname', title: '会员昵称'},
			{field: 'title', title: '会员等级'},
			{field: 'amount', title: '订单数'},
			{field: 'price', title: '订单金额'}
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
	
	table.render({
		elem: '#OM-sell-rangking',
		url: '/manager/sell/rangking/info',
		cols: [[
			{field: 'ranking', title: '排行', width: 100},
			{field: 'title', title: '商品名称'},
			{field: 'sn', title: '货号'},
			{field: 'amount', title: '销售量'},
			{field: 'amount', title: '销售额'},
			{field: 'price', title: '均价'}
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
	
	exports('sell', {})
});