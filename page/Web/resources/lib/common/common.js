var api='http://127.0.0.1:1169/'
// var api='http://127.0.0.1:1169/api/'
var baseUrl='http://www.persona6.cn/'
var loginUrl='http://127.0.0.1:5500/Web/login.html';

//下次再发ajax请求把token带到后台
var token = $.cookie('TOKEN');
//设置全局ajax前置拦截
$.ajaxSetup( { 
	headers: {
		'TOKEN': token   //每次ajax请求时把token带过去
	}
});

//如果访问登陆页面这外的页面并且还没有登陆成功之后写入cookie的token就转到登陆页面
if(token==undefined){
	if(window.location!=loginUrl){
		window.top.location=loginUrl;
	}
}else{
	if(window.location!=loginUrl){
		$.ajax({
				url:api+"login/checkLogin",
				async:true,
				type:'post',
				dataType:'json',
				success:function(res){
					if(res.code==-1){
						window.top.location=loginUrl;
					}
				},
				error:function(res){
					window.top.location=loginUrl;
				}
		});
	}
}

var pers=localStorage.getItem("permissions");
var usertype=localStorage.getItem("usertype");
if(usertype==1){
	if(pers!=null){
		var permissions=pers.split(",");


		//部门权限开始
		if(permissions.indexOf("dept:add")<0){
			$(".btn_add").hide();
		}
		if(permissions.indexOf("dept:update")<0){
			$(".btn_update").hide();
		}
		if(permissions.indexOf("dept:delete")<0){
			$(".btn_delete").hide();
		}

		//进货权限
		if(permissions.indexOf("inport:add")<0){
			$(".inport-add").hide();
		}
		if(permissions.indexOf("inport:update")<0){
			$(".inport-update").hide();
		}
		if(permissions.indexOf("inport:delete")<0){
			$(".inport-delete").hide();
		}
		if(permissions.indexOf("inport:outport")<0){
			$(".inport-outport").hide();
		}

		//销售权限
		if(permissions.indexOf("sales:add")<0){
			$(".sales_add").hide();
		}
		if(permissions.indexOf("sales:update")<0){
			$(".sales_update").hide();
		}
		if(permissions.indexOf("sales:delete")<0){
			$(".sales_delete").hide();
		}
		if(permissions.indexOf("sales:salesback")<0){
			$(".sales_salesback").hide();
		}

		//客户权限
		if(permissions.indexOf("customer:add")<0){
			$(".customer_add").hide();
$(".btn_kc").hide();
		}
		if(permissions.indexOf("customer:update")<0){
			$(".customer_update").hide();
		}
		if(permissions.indexOf("customer:delete")<0){
			$(".customer_delete").hide();
		}

		


	}
	else{
		//部门隐藏
		$(".btn_add").hide();
		$(".btn_update").hide();
		$(".btn_delete").hide();
		$(".btn_reset").hide();

		$(".inport-add").hide();
		$(".inport-update").hide();
	}
}

//给页面显示登陆用户名
var username=localStorage.getItem("username");
$(".login_name").html(username);