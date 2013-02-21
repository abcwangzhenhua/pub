package test;

import java.io.File;
import java.util.StringTokenizer;

import model.Mail;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		t1();
	}
	public static void t1(){
		//发件服务器
		String mailsmtp="smtp.126.com";
		//发件箱用户名
		String username="enttest";
		//发件箱密码
		String password="qwerty";
		//发件smtp端口
		String mailsmtpport="25";
		//编码方式
		String charset="utf-8";
		//主题
		String subject="恭喜您，成功注册斗价网！";
		String mailbody="<html><body>注意事项：<br>发个邮件测试测试！！哈哈<br/><img src=\"/enterdoc/uploadfile/kcp31.jpg\" alt=\"点我试试\"></body></html>";
		//发件人昵称
		String mailsendusr="张三";
		//发件箱
		String from="enttest@126.com";
		//收件箱
		String to="250289416@qq.com";
		//需要回执<0：不，1:要>
		String needrt="0";
		String priority="";
		Mail themail = null;
		themail=new Mail(mailsmtp,username,password,mailsmtpport);//设置smtp服务器	    
	    themail.setMailCharacter(charset);
	    themail.setNeedAuth(true);
		if(themail.setSubject(subject) == false) return;
		if(themail.setBody(mailbody) == false) return;		
		if(mailsendusr.trim().equals("")){
			if(themail.setFrom(from) == false) return; 
		}else{
			if(themail.setFrom(mailsendusr,from)== false) return;
		}
		if (needrt.equals("1")) themail.setReplySign(); 		
		if(priority.equals(""))priority="3";
		themail.setPriority(priority);//设置邮件优先级		
		if(themail.setTo(to)){
			String strReturnMsg=themail.sendoutEx();
		}
	}
} 
