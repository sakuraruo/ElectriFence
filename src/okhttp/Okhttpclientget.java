package okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttpclientget {
	
private final OkHttpClient client=new OkHttpClient();
	
	public String  get(String[]key,String[]value,String url) throws IOException
	{
		
		url+="?"+key[0]+"="+value[0];
		if(key.length>2)
		{
			for(int i=2;i<key.length-1;i++)
			{
				url+="&"+key[i]+"="+value[i]+"&";
			}
			url+=key[key.length-1]+"="+value[key.length-1];
		}
        Request request=new Request.Builder()
        		.url(url)
        		.header("Accept", "application/json")
        		.header("Authorization", value[1])
        		.build();
        
        Call call = client.newCall(request);
        Response response=call.execute();
        
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
        
	}

}
