package reptile.douban;
/*
 * 把该页面下载下来并且返回一个字符串
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/** 
 *最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面
  *@authorLiudong
*/
public class DownTool {

	@SuppressWarnings("finally")
	public static String getHTML(String URL)  {
		
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(URL);
        String html="";
        try{
        	HttpResponse response = httpclient.execute(httpget);
        	HttpEntity entity = response.getEntity();
        	html = EntityUtils.toString(entity);   
        	httpclient.close();       
        }catch(Exception e){
       	 // 发生网络异常
       	   e.printStackTrace();
       }finally{
    	   return html;
       }
           
    }
	
}
