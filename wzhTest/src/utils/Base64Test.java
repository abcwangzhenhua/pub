package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import sun.misc.*;

/**
 * ��������ͼƬ���ݣ�����Ƕ�뵽html��eml�ȳ��ı��ļ��С�
 * @author Administrator
 *
 */
public class Base64Test {

	/**Base64������ԣ���ͼƬת���ַ����У��ַ���������ͼƬ
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		/*String fileUrl="src/1.txt";
		String fileUrl2="src/2.txt";		
		byte []buffer=parseTextToBytes(fileUrl);
		byte []buffer2=parseTextToBytes(fileUrl2);
		
		Workbook wb=WorkbookFactory.create(new FileInputStream("src/test.xls"));
		insertImg(wb,"pic",buffer,0);
		insertImg(wb,"pic",buffer2,1);
		OutputStream out=new FileOutputStream("src/testnew.xls");
		wb.write(out);		
		out.close();*/
		//BASE64Encoder encoder=new BASE64Encoder();
		//String picStr=encoder.encode(buffer);
		//strToPic(picStr,"src/exc.jpg");
		test();
	}
	public static void test(){
		String fileUrl="src/3.txt";
		//String newFileUrl="src/nn.txt";
		String str="";		
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileUrl)));
			//PrintWriter pw=new PrintWriter(newFileUrl);
			StringBuilder sb=new StringBuilder();
			String s="";
			while((s=br.readLine())!=null){
				sb.append(s);
			}
			byte[] b = decoder.decodeBuffer(sb.toString());
			System.out.println(new String(b,"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static byte[] parseTextToBytes(String fileUrl) throws Exception{
		InputStream is=new FileInputStream(fileUrl);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		StringBuilder sb=new StringBuilder();
		String s="";
		while((s=br.readLine())!=null){
			sb.append(s);
		}
		//System.out.println(sb);
		String[] sbs=sb.toString().split(",");
		byte[] buffer=new byte[sbs.length];
		for(int i=0;i<sbs.length;i++){
			buffer[i]=Byte.parseByte(sbs[i]);
		}
		is.close();
		return buffer;
	}
	/**
	 * ��ͼƬ�ļ�ת��Ϊ�ַ��������������Base64���봦��
	 */
	public static String picToStr(String picPath){
		String picStr="";
		byte[] picBuffer=null;
		try{
			//��ȡͼƬ�ֽ�����
			InputStream is=new FileInputStream(picPath);
			picBuffer=new byte[is.available()];
			is.read(picBuffer);
			is.close();
			
		}catch(IOException e){
			System.out.println("Base64Test-->picToStr-->��ȡͼƬ����");
            e.printStackTrace();
		}
		//���ֽ�����Base64����
        if(picBuffer != null){
        	BASE64Encoder encoder = new BASE64Encoder();
        	picStr=encoder.encode(picBuffer);
        }
        //����Base64��������ֽ������ַ���
		return picStr;
	}
	/**
	 * �Խ���Base64������ַ�������Base64���벢����ͼƬ
	 */
	public static boolean strToPic(String picStr,String picPath){
		if(picStr==null||picStr.trim().equals("")) return false;
		if(picPath==null||picPath.trim().equals("")) picPath="./default.jpg";
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64���룬����jpegͼƬ        	
            byte[] b = decoder.decodeBuffer(picStr);
            if(picPath.lastIndexOf("/")>0){
            	String picDir=picPath.substring(0, picPath.lastIndexOf("/"));
            	File dir = new java.io.File(picDir);
        		//Ŀ¼�����ڣ��򴴽�
        		if(dir.exists()==false)	dir.mkdirs();
            }            
            OutputStream out = new FileOutputStream(picPath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch(Exception e){
        	System.out.println("Base64Test-->strToPic-->����ͼƬ����");
        	e.printStackTrace();
            return false;
        }
	}	
}
