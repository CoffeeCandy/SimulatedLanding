package reptile.grades;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/*
 * ģ�������
 * ������˵һ�½���������飺�����ҳ���ض����������ǿ��Դӵ�ַ���п����ض����
 * ��ַ�ģ����ת��������Ҳ���Դ�Դ���Ͽ�����
 * ���ﲻ�ܼ�hostͷ���ǲ��ǵ�ַ�Ǳ�����ԭ�򻹲�̫���
 * �������漰����sessionID,���Ǵ�LoginServlet�еõ�sessionID,����Ϊcookie����ȥ��
  *������ض��������ͨ��ͷ�õ��ض���ĵ�ַ����ȻҲ�����ڵ�ַ���еõ�
  *��ʱ�����ȥ�Ĳ����������ص�ֵ����Ҫ��һ����ҳ��Դ���룬ȷ��һ��
 */
public class MyLogin {
	public static HttpClientContext context = HttpClientContext.create();
	public static CookieStore cookieStore = new BasicCookieStore();
	public static String JSESSIONID="";
	public static String username="";
	public static CloseableHttpClient client = HttpClients.custom()
			.setDefaultCookieStore(cookieStore).build();
	public static void main(String[] args)throws Exception{
		MyPost();
	}
	
	
	
	
	public static  void MyPost()throws Exception{
		String url="http://127.0.0.1:8080/day08/LoginServlet";
		HttpPost post=new HttpPost(url);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "dd"));
		nvps.add(new BasicNameValuePair("password", "234"));
		post.setEntity(new UrlEncodedFormEntity(nvps));
		 CloseableHttpResponse response =client.execute(post, context);
		

			 
		
		 List<Cookie> cookies=cookieStore.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("JSESSIONID")){
				JSESSIONID=cookie.getValue();
			}
			if(cookie.getName().equals("username")){
				username=cookie.getValue();
			}
			
		}
		
		 String content1 = EntityUtils.toString(response.getEntity());
		 System.out.println(content1);
		 getResult();
	}

	public static void getResult()throws Exception {
		String url="http://127.0.0.1:8080/day08/login/succ1.jsp";
		context = HttpClientContext.create();
		cookieStore = new BasicCookieStore();
		 client = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).build();
		HttpGet Get = new HttpGet(url);
		 
		Get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"); 
		Get.setHeader("Accept-Encoding","gzip, deflate, br");
		Get.setHeader("Accept-Language:","zh-CN,zh;q=0.8");
		//Get.setHeader("Host:","127.0.0.1:8080");
		Get.setHeader("Cache-Control:","max-age=0");
		Get.setHeader("Connection","keep-alive");
		Get.setHeader("User-Agent:","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36");
		Get.setHeader("Upgrade-Insecure-Requests","1");
	    Get.setHeader("Referer","http://127.0.0.1:8080/day08/login/login.jsp");
	
		 BasicClientCookie cookie2 = new BasicClientCookie("JSESSIONID",JSESSIONID);
		cookie2.setPath("/day08");
		
		
		 cookie2.setDomain("127.0.0.1");
		 cookieStore.addCookie(cookie2);
		 BasicClientCookie cookie3 = new BasicClientCookie("username",username);
		 cookie3.setPath("/day08");
		
			cookie3.setDomain("127.0.0.1");
		 cookieStore.addCookie(cookie3);
		 context.setCookieStore(cookieStore);
		
		 CloseableHttpResponse response =client.execute(Get, context);
		 
		 
		 
		System.out.println(response.getStatusLine());
		
		 String content1 = EntityUtils.toString(response.getEntity());
		 System.out.println(content1);
	
	}
}