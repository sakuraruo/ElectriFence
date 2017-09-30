package okhttp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.*;


public class Okhttpclientpost {
	private final OkHttpClient client=new OkHttpClient();
	
	public String  post(String[]key,String[]value,String url) throws IOException
	{
		
		FormBody.Builder builder = new FormBody.Builder();
        for(int i = 0 ; i <key.length ;i ++){
            builder.add(key[i],value[i]);
            
        }
        RequestBody body = builder.build();
		
        Request request=new Request.Builder()
        		.url(url)
        		.post(body)
        		.build();
        
        Call call = client.newCall(request);
        Response response=call.execute();
        
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
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
		okhttpclientpost oktpclt=new okhttpclientpost();
		 try {
			System.out.println(oktpclt.post(key,value,url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
    

}
