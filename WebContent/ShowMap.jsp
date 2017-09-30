<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> 高德地图显示</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<link rel="stylesheet" type="text/css"  href="css/style.css"/>


<!-- 悬浮登录框效果引用 -->
	
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	  <script type="text/javascript" src="js/login.js"></script>
	  <script type="text/javascript" src="js/Trail.js"></script>	


<!-- 高德地图api引用 -->
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.4.0&key=您申请的key值"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
	

    <script type="text/javascript" src="js/showmap.js"></script>
    
     <style type="text/css">
    #container{
    	height:800px;
    	weight:600px;
    	margin-top:100px;
    
    }
    </style>
</head>
<body>
<div id="container"></div>
<div class="login-header"><a href="javascript:void(0);"><input name="button" type="button" value="登录"  ></a></div>
<div class="get-trail"><a href="javascript:void(0);"><input name="button" type="button" value="获取历史轨迹"  id="get-trail"></a></div>


<div class="login">
    <div class="login-title">登录会员<span><a href="javascript:void(0);" class="close-login">关闭</a></span></div>
    <div class="login-input-content">
        <div class="login-input">
            <label>用&nbsp;户&nbsp;&nbsp;名：</label>
            <input type="text" placeholder="请输入用户名"   id="username" class="list-input"/>
        </div>
        <div class="login-input">
            <label>登录密码：</label>
            <input type="password" placeholder="请输入登录密码" id="password" class="list-input"/>
        </div>
    </div>
    <div class="login-button"><a href="javascript:void(0);" id="login-button-submit">登录会员</a></div>
</div>

<div class="login-bg"></div>




</body>
</html>