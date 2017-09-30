package smartcardapi;
import java.io.IOException;

//import org.json.*;
import okhttp.*;

public class GetLastpoint {
	
	public String getlastpoint(String cid,String Authorization)
	{
		String url="http://capi.gpslink.cn/api/Point/Last";
		String key[]={"cid","Authorization"};
		String value[]={cid,"bearer"+" "+Authorization};
		
		Okhttpclientget okclt=new Okhttpclientget();
		String jsonobject =" " ;
		try {
			// jsonobject=new JSONObject(okclt.post(key, value, url));
			jsonobject=okclt.get(key, value, url);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobject;
	
	}
	
/*	public static void main(String []args)
	{
		String cid="861842030027687";
		String Authorization="bearer oP0NldjfO8VXSgIK8_WDjyymrm5SvgmepfWnjtj-R0MXnhs8vrxKYkJtt4xnF3xYxC9QmcQ8r-_vVRIG-TAk37wZq-wd0TcAjstMEktK-J2X5X4xCzpO36xLKAcmq7AX0Me9_MKvW6dgJSmnitZKv1zRHEEXXj3UZqmPgqp07mqZC_MKIDJKIUlcy5DGFNtcZ-e7EKiGsenEUe0DVCRkAsg0qY8ioV0RsRCcHYhNE-zvO2WnJ46gZ-pVgX9QxGr2DyiRpDoGEnRQ-PZ5CcEX97F2FV6AmA9a6E950ChkRi4yAv6zUYUQvAO12DIo2ww8dsYibxGS7ETUJqAxOHwVNrXeWKEBw81ozVS_ZxtLD-M";
		GetLastpoint gtp=new GetLastpoint();
		System.out.println(gtp.getlastpoint(cid, Authorization));
	}
	*/

}
