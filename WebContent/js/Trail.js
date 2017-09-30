/**
 * 
 */
$ (function(){
 $('#get-trail').click(function()
{

	 
	 $.ajax({ 
         type: "get",
         url: "gettrail",
         success: function (result) {
        	 var trail=eval('(' + result + ')'); 
        	 var error=result.toString();
        	 if(error==" 请重新登录")
        		 {
        		    alert("请重新登录");
        		 
        		 }
        	 
        	 else
        		 {
        		 
        		 var polygonArr = new Array();
        	      
        	      var i;
        	      
        	      for(i  in trail.items)
        	      {
        	        polygonArr.push([trail.items[i].lng, trail.items[i].lat]);
        	      }
        	      
        	      
        	    //创建地图
        	      var map = new AMap.Map('container', {
        	          zoom: 4
        	      });

        	      AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

        	          if (!PathSimplifier.supportCanvas) {
        	              alert('当前环境不支持 Canvas！');
        	              return;
        	          }

        	          var pathSimplifierIns = new PathSimplifier({
        	              zIndex: 100,
        	              //autoSetFitView:false,
        	              map: map, //所属的地图实例

        	              getPath: function(pathData, pathIndex) {

        	                  return pathData.path;
        	              },
        	              getHoverTitle: function(pathData, pathIndex, pointIndex) {

        	                  if (pointIndex >= 0) {
        	                      //point 
        	                      return pathData.name + '，点：' + pointIndex + '/' + pathData.path.length;
        	                  }

        	                  return pathData.name + '，点数量' + pathData.path.length;
        	              },
        	              renderOptions: {

        	                  renderAllPointsIfNumberBelow: 100 //绘制路线节点，如不需要可设置为-1
        	              }
        	          });

        	          window.pathSimplifierIns = pathSimplifierIns;

        	          //设置数据
        	          pathSimplifierIns.setData([{
        	              name: '路线0',
        	              path: polygonArr
        	          }]);

        	          //对第一条线路（即索引 0）创建一个巡航器
        	          var navg1 = pathSimplifierIns.createPathNavigator(0, {
        	              loop: true, //循环播放
        	              speed: 1000 //巡航速度，单位千米/小时
        	          });

        	          navg1.start();
        	      });

        	      
        		 }
     
         	
         }
     });
	 

	
    
    
});
});