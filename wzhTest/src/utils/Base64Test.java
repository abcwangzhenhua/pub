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
 * 可以用于图片传递，或者嵌入到html，eml等超文本文件中。
 * @author Administrator
 *
 */
public class Base64Test {

	/**Base64编码测试，将图片转成字符序列，字符序列生成图片
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
	 * 将图片文件转化为字符串，并对其进行Base64编码处理
	 */
	public static String picToStr(String picPath){
		String picStr="";
		byte[] picBuffer=null;
		try{
			//读取图片字节数组
			InputStream is=new FileInputStream(picPath);
			picBuffer=new byte[is.available()];
			is.read(picBuffer);
			is.close();
			
		}catch(IOException e){
			System.out.println("Base64Test-->picToStr-->读取图片出错！");
            e.printStackTrace();
		}
		//对字节数组Base64编码
        if(picBuffer != null){
        	BASE64Encoder encoder = new BASE64Encoder();
        	picStr=encoder.encode(picBuffer);
        }
        //返回Base64编码过的字节数组字符串
		return picStr;
	}
	/**
	 * 对进行Base64编码的字符串进行Base64解码并生成图片
	 */
	public static boolean strToPic(String picStr,String picPath){
		if(picStr==null||picStr.trim().equals("")) return false;
		if(picPath==null||picPath.trim().equals("")) picPath="./default.jpg";
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64解码，生成jpeg图片        	
            byte[] b = decoder.decodeBuffer(picStr);
            if(picPath.lastIndexOf("/")>0){
            	String picDir=picPath.substring(0, picPath.lastIndexOf("/"));
            	File dir = new java.io.File(picDir);
        		//目录不存在，则创建
        		if(dir.exists()==false)	dir.mkdirs();
            }            
            OutputStream out = new FileOutputStream(picPath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch(Exception e){
        	System.out.println("Base64Test-->strToPic-->生成图片出错！");
        	e.printStackTrace();
            return false;
        }
	}	
}
