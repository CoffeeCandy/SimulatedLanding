package reptile.douban;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class OperateExcel {
	/*
	 * �������ε�Ӱ���ƣ���Ӱ�������ӣ�ͼƬ���ӣ�ӰƬ������,ӰƬ�����,���ݣ�����,������
	 */
		private static HSSFWorkbook workbook = new HSSFWorkbook();
		private static HSSFSheet sheet;
		
		public OperateExcel(String tableName) throws Exception {
			String newFileName = URLEncoder.encode(tableName, "UTF-8");    
    		 sheet = workbook.createSheet(newFileName);
		}
		public  int write2Excel(ArrayList<String> al,String path){
			
			while(al.size()>0){
				String movieName=al.remove(0);
				String EnglishName=al.remove(0);
				String movieLink=al.remove(0);
				String pictureLink=al.remove(0);
				String star=al.remove(0);
				String number=al.remove(0);
				write2Excel2((short)(sheet.getLastRowNum()+1),movieName,EnglishName,movieLink,pictureLink,star,number);
							
			}
			wirte2Destination( workbook,path);
			
		
			return sheet.getLastRowNum();
		}
		public  void write2Excel2(short cells,String chineseName,String englishName,String detailLink,String photoLink,String score,String number){
			HSSFRow row = sheet.createRow(cells);
			HSSFCell cell = row.createCell((short)0);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(chineseName);
     		cell = row.createCell((short)1);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(englishName);
     		cell = row.createCell((short)2);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(detailLink);
     		cell = row.createCell((short)3);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(photoLink);
     		cell = row.createCell((short)4);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(score);
     		cell = row.createCell((short)5);
     		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
     		cell.setCellValue(number);
     		
     		
		}

         private  void wirte2Destination(HSSFWorkbook workbook,String path){
        	 try {
      			FileOutputStream fos = new FileOutputStream(path);
      			workbook.write(fos);
      			System.out.println("д��ɹ�");
      			fos.close();
      		} catch (IOException e) {
      			e.printStackTrace();
      		}
        	 
         }
}
