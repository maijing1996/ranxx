$(function () {
    //收缩左侧导航
    $("#zk_btn").click(function () {
        if ($('#zk_val').val()=="0"){
            $('#zk_val').val("1");
            $('#zk_btn').html("<i class='iconfont'>&#xe67b;</i>");
            $("#admin-path").animate({left:"0"});
            $("#admin-body").animate({left:"0"});
            $("#footer").animate({left:"0"});
        }
        else {
            $('#zk_val').val("0");
            $('#zk_btn').html("<i class='iconfont'>&#xe673;</i>");
            $("#admin-path").animate({left:"200"});
            $("#admin-body").animate({left:"200"});
            $("#footer").animate({left:"200"});
        }
    });
    //验证表单提交
    $("#myform").Validform({
        tiptype:function(msg,o,cssctl){
            if(msg!="通过信息验证！"){
                if(msg=="正在提交数据…"){
                    layer.load();
                }
                else {
                    layer.closeAll();
                    if(msg.indexOf("成功") < 0 ){
                        layer.msg(msg,{icon: 5});
                    }
                }
            }
        },
        postonce:true,
        ajaxPost:true,
        callback:function(date){
            if(typeof(date.data.url)=="undefined") {
                layer.closeAll();
                layer.msg(date.msg,{icon: 5});
                if($("#captcha").length>0){$("#captcha").click();}
            }
            else {  //有附加信息
                if (date.data.ico==-1){
                    parent.location.href=date.data.url;
                }
                else {
                    layer.closeAll();
                    layer.open({icon: date.data.ico , content: date.msg , yes: function(){location.href=date.data.url;}});
                }
            }
        }
    });
});

//删除信息 p0:请求url p1:返回url
function delinfo(p0,p1){
    layer.open({
        btn: ['确认', '取消'],
        icon: 3 ,
        content: '确定要删除吗?' ,
        yes: function(index){
            layer.close(index);
            $.ajax({
                type:"GET",
                url:p0,
                cache: false,
                dataType:"json",
                beforeSend:function(){
                    layer.load();
                },
                error: function() {
                    layer.closeAll();
                    layer.msg("连接失败，请重试!",{icon: 5});
                },
                success:function(data){
                    layer.closeAll();
                    if(data.code=='1001') {
                        location.href=p1;
                    }
                    else {
                        layer.msg(data.msg,{icon: 5});
                    }
                }
            });
        }
    });
}

//批量删除信息 p0:请求url
function delall(p0){
    var flag = 0;
    $("input[name='m_id[]']:checkbox").each(function () {
        if (this.checked) {
            flag += 1;
        }
    });
    layer.open({
        btn: ['确认', '取消'],
        icon: 3 ,
        content: '确定要删除吗?' ,
        yes: function(index){
            layer.close(index);
            if(flag) {
                $.ajax({
                    type: "POST",
                    url: p0,
                    data: $('#listform').serialize(),
                    cache: false,
                    dataType: "json",
                    beforeSend: function () {
                        layer.load();
                    },
                    error: function () {
                        layer.closeAll();
                        layer.msg("连接失败，请重试!", {icon: 5});
                    },
                    success: function (date) {
                        layer.closeAll();
                        if (date.code == '1001') {
                            layer.open({icon: date.data.ico , content: date.data.msg , yes: function(){location.reload();}});
                        }
                        else {
                            layer.msg(date.msg, {icon: 5});
                        }
                    }
                });
            }
            else {
                layer.msg("请先选择数据!", {icon: 5});
            }
        }
    });
}

//删除相册 p0:要删除的图片
function delpic(p0){
    var input_str="";
    var pic_str="";
    $("#slide input").each(function(){
        if($(this).val()!=p0){
            input_str=input_str+"<input type='hidden' name='m_slide[]' value='"+$(this).val()+"'>"
            pic_str=pic_str+"<div class='slide_ctr'><img src='/upload/pic/"+$(this).val()+"'><div class='pic_del' onClick=\"delpic('"+$(this).val()+"')\">删除</div></div>"
        }
    });
    $("#slide_ctr").html(pic_str);
    $("#slide").html(input_str);
}

//清除缓存 p0:请求url p1:返回url
function clearCache(p0,p1){
    layer.open({
        btn: ['确认', '取消'],
        icon: 3 ,
        content: '确定要清除吗?' ,
        yes: function(index){
            layer.close(index);
            $.ajax({
                type:"GET",
                url:p0,
                cache: false,
                dataType:"json",
                beforeSend:function(){
                    layer.load();
                },
                error: function() {
                    layer.closeAll();
                    layer.msg("连接失败，请重试!",{icon: 5});
                },
                success:function(data){
                    if(data==1) {
                        layer.closeAll();
                        location.href = p1;
                    }
                }
            });
        }
    });
}
