package reptile.douban;
/*
 * �Ѹ�ҳ�������������ҷ���һ���ַ���
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/** 
 *��򵥵�HTTP�ͻ���,������ʾͨ��GET����POST��ʽ����ĳ��ҳ��
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
       	 // ���������쳣
       	   e.printStackTrace();
       }finally{
    	   return html;
       }
           
    }
	
}
