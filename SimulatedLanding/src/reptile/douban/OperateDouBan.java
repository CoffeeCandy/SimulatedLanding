package reptile.douban;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperateDouBan {
	/*
	 * 此方法获得单个电影的数据，每个数据都是字符串
	 * /*
	 * 利用java爬取豆瓣电影Top250的相关信息，包括电影详情链接,图片链接,影片中文名,影片外国名,
	 * 评分,评价数,概况,导演,主演,年份,地区,类别这12项内容，然后将爬取的信息写入Excel表中。
	 * 我就抓取前50个
	 * 1.定义一个exel类，标题依次电影名称，电影详情链接，图片链接，影片中文名,影片外国名,导演，评分,评价数
	 * 		该类中有添加行的方法，参数即为上面7个字符串
	 * 2.定义一个获得该页面上连接的方法
	 * 3.每次获得一组便存入exel中。
	 */
	 
	private static ArrayList<String> al = new ArrayList<String>();
	private static int count=0;
	private static String url="https://movie.douban.com/top250";
	private static String html;
	private static String path="E:\\1.xls";
	private static OperateExcel oe;
	public static void main(String[] args) throws Exception {
		html = DownTool.getHTML(url);
		System.out.println(html);
		al=extracLinks(html);
		System.out.println(al.size());
		oe=new OperateExcel("toubanTOP50");
		oe.write2Excel2((short)(0),"中文名字","英文名字","电影详情连接","图片的连接","电影的评分","评价电影数量");
		oe.write2Excel(al, path);
	}
	/*
	 * 这段代码是用在规定要爬下多少个记录的时候用
	 */
	public static void main1() throws Exception{
		System.out.println(count);
		url = "https://movie.douban.com/top250"+"?start="+count+"&filter=";
		
		 String html1 = DownTool.getHTML(url);
		al=extracLinks(html1);
		count=oe.write2Excel(al, path);
	}
	public static ArrayList<String> extracLinks(String html) {
		/*
		 * 匹配strFirst和strLast获得一个电影的完全详细信息
		 */
		html = html.replaceAll("\r|\n|\t", "");
		String pattern = "(<div class=\"item\">)(.*?)(<div class=\"pic\">)(.*?)(<a href=\")(.*?)(\">)(.*?)(<img alt=\")(.*?)(\")(.*?)(src=\")(.*?)(\")(.*?)(</div>)(.*?)(<div class=\"hd\">)(.*?)(<span class=\"title\">)(.*?)(</span>)(.*?)(;)(.*?)(</span>)(.*?)(</div>)(.*?)(<div class=\"bd\">)(.*?)(<div class=\"star\">)(.*?)(property=\"v:average\">)(.*?)(</span>)(.*?)(<span>)(.*?)(</span>)(.*?)(</div>)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(html);
		while (m.find()) {
			// 电影的中文名字
			al.add(m.group(22));
			// 电影的英文名字
			String englishName = m.group(26);
			String[] engN = englishName.split(";");
			englishName = engN[engN.length - 1];
			al.add(englishName);
			// 电影详情的的连接
			al.add(m.group(6));
			// 图片的连接
			al.add(m.group(14));
			// 电影的评分
			al.add(m.group(36));
			// 评价该电影的数量
			String evaluateNum = m.group(40).substring(0,
					m.group(40).length() - 3);
			al.add(evaluateNum);
		}
		return al;
	}
}
