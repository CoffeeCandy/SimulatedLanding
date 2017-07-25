package reptile.douban;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperateDouBan {
	/*
	 * �˷�����õ�����Ӱ�����ݣ�ÿ�����ݶ����ַ���
	 * /*
	 * ����java��ȡ�����ӰTop250�������Ϣ��������Ӱ��������,ͼƬ����,ӰƬ������,ӰƬ�����,
	 * ����,������,�ſ�,����,����,���,����,�����12�����ݣ�Ȼ����ȡ����Ϣд��Excel���С�
	 * �Ҿ�ץȡǰ50��
	 * 1.����һ��exel�࣬�������ε�Ӱ���ƣ���Ӱ�������ӣ�ͼƬ���ӣ�ӰƬ������,ӰƬ�����,���ݣ�����,������
	 * 		������������еķ�����������Ϊ����7���ַ���
	 * 2.����һ����ø�ҳ�������ӵķ���
	 * 3.ÿ�λ��һ������exel�С�
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
		oe.write2Excel2((short)(0),"��������","Ӣ������","��Ӱ��������","ͼƬ������","��Ӱ������","���۵�Ӱ����");
		oe.write2Excel(al, path);
	}
	/*
	 * ��δ��������ڹ涨Ҫ���¶��ٸ���¼��ʱ����
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
		 * ƥ��strFirst��strLast���һ����Ӱ����ȫ��ϸ��Ϣ
		 */
		html = html.replaceAll("\r|\n|\t", "");
		String pattern = "(<div class=\"item\">)(.*?)(<div class=\"pic\">)(.*?)(<a href=\")(.*?)(\">)(.*?)(<img alt=\")(.*?)(\")(.*?)(src=\")(.*?)(\")(.*?)(</div>)(.*?)(<div class=\"hd\">)(.*?)(<span class=\"title\">)(.*?)(</span>)(.*?)(;)(.*?)(</span>)(.*?)(</div>)(.*?)(<div class=\"bd\">)(.*?)(<div class=\"star\">)(.*?)(property=\"v:average\">)(.*?)(</span>)(.*?)(<span>)(.*?)(</span>)(.*?)(</div>)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(html);
		while (m.find()) {
			// ��Ӱ����������
			al.add(m.group(22));
			// ��Ӱ��Ӣ������
			String englishName = m.group(26);
			String[] engN = englishName.split(";");
			englishName = engN[engN.length - 1];
			al.add(englishName);
			// ��Ӱ����ĵ�����
			al.add(m.group(6));
			// ͼƬ������
			al.add(m.group(14));
			// ��Ӱ������
			al.add(m.group(36));
			// ���۸õ�Ӱ������
			String evaluateNum = m.group(40).substring(0,
					m.group(40).length() - 3);
			al.add(evaluateNum);
		}
		return al;
	}
}
