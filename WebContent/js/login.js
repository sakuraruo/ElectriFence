$(function () {
    H_login = {};
    H_login.openLogin = function(){
        $('.login-header a').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
            $('.login').hide();
            $('.login-bg').hide();
        });
    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click',function(){
              var username = $("#username").val();

              var password = $("#password").val();

            
              var x,y;
             $.ajax({ 
            type: "post",
            url: "getlastpoint",
            data:"username="+username+"&password="+password,
            success: function (result) {
            	//alert(result);
            	//alert("123");
            	var point=eval('(' + result + ')'); 
            	
            	
	        	
	        	x=point.lng;
	        	y=point.lat;
               // alert(x);
               // alert(y);
            		//var x=113.141443641115,y=29.3461232704826;;
            		
	        	var map = new AMap.Map('container', {
	        	    resizeEnable: true,
	        	    zoom:16,
	        	    center: [x, y]        
	        	});
	        	marker = new AMap.Marker({
 	               icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
 	               position: [x, y]
 	           });
 	           marker.setMap(map);
 	           
 	          var polygonArr = new Array();//多边形覆盖物节点坐标数组
 	 	    polygonArr.push([113.150987,29.326954]);
 	 	    polygonArr.push([113.137704,29.327702]);
 	 	    polygonArr.push([113.140129,29.333585]);
 	 	    polygonArr.push([113.145708,29.344303]);
 	 	    var  polygon = new AMap.Polygon({
 	 	        path: polygonArr,//设置多边形边界路径
 	 	        strokeColor: "#FF33FF", //线颜色
 	 	        strokeOpacity: 0.2, //线透明度
 	 	        strokeWeight: 3,    //线宽
 	 	        fillColor: "#1791fc", //填充色
 	 	        fillOpacity: 0.35//填充透明度
 	 	    });
 	 	    polygon.setMap(map);
 	 	    
 	 	    
 	 	  var isin=point.isin;
      	  if(isin==true)
      		  {
      		     alert("设备在圈内！");
      		  
      		  }
      	  
      	  else
      		  {
      		    alert("设备越界！");
      		  
      		  }
 	 	 
            		
            		//登录成功自动关闭弹出框
              $('.login').hide();
              $('.login-bg').hide();
            		
            		 
            		 
            	
            }
        });
             
             
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();
});
              
              /* 
               * //点击获取历史轨迹

	  
	   
               */
            
