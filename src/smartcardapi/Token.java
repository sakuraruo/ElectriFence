package smartcardapi;

import java.io.IOException;

import org.json.JSONObject;

import okhttp.Okhttpclientpost;

public class Token {
	public String GetToken(String username,String password,String grant_type,String scope) throws IOException
	{
		String url="http://capi.gpslink.cn/Token";
		String key[]={"username","password","grant_type","scope"};
		String value[]={username,password,grant_type,scope};
		Okhttpclientpost okclt=new Okhttpclientpost();
		JSONObject obj=new JSONObject(okclt.post(key, value, url));
		return obj.get("access_token").toString();
	}
	
	/*public static void main(String []args)
	{
		String username="861842030027687";
		String password="123456";
		String grant_type="password";
		String scope="single";
		String url="http://capi.gpslink.cn/Token";
		String key[]={"username","password","grant_type","scope"};
		String value[]={username,password,grant_type,scope};
		Okhttpclientpost oktpclt=new Okhttpclientpost();
		 try {
			System.out.println(oktpclt.post(key,value,url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	

}
