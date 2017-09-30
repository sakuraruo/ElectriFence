package smartcardapi;

import java.io.IOException;

import okhttp.Okhttpclientget;

public class GetTrail {
	public String gettrail(String cid, String Authorization,String beg,String end)
	{
		String url="http://capi.gpslink.cn/api/Point/Trail";
		String key[]={"cid","Authorization","beg","end"};
		String value[]={cid,"bearer"+" "+Authorization,beg,end};
		
		
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
	
	/*public static void main(String []args)
	{
		String cid="861842030027687";
		String Authorization="lPR-WIecparWcXxj4xdyBJkGYFrfBGtiaVaRunFFVKQuNxre-rRH3lGU9xoZkoCxMKTfZoom6fT8pFX1blDdi8bd278LZrPux72IEu1af4kqXViShGgTXzZ-sSy8FAxnPWoyWB1N48X6YsQvztRrsKUm_kAqq-CmNnGEw2fQtFbDlMt3eAg_XdOWo2zH-2EBb-mYdHoVva-lEtIfVhPgmNrX_UOZcUGWur0DvDo53NcfUbpouv77-zzFKZ1M49aCZvKMyyCqIEMH5yoYCxA5mzCOUYo1S4UzcCfodccQFUAIShpH-O6HJHPNGYxlYx1B8F1NJ3vkq7Cfo3K5Hg5pgcKK9T5h4ZJBhRYRGoEysrA";
		String beg="2017-8-25";
		String end="2017-9-25";
		GetTrail gettra=new GetTrail();
		System.out.println(gettra.gettrail(cid, Authorization, beg, end));
	}*/

}
