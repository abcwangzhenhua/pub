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
		//����������
		String mailsmtp="smtp.126.com";
		//�������û���
		String username="enttest";
		//����������
		String password="qwerty";
		//����smtp�˿�
		String mailsmtpport="25";
		//���뷽ʽ
		String charset="utf-8";
		//����
		String subject="��ϲ�����ɹ�ע�ᶷ������";
		String mailbody="<html><body>ע�����<br>�����ʼ����Բ��ԣ�������<br/><img src=\"/enterdoc/uploadfile/kcp31.jpg\" alt=\"��������\"></body></html>";
		//�������ǳ�
		String mailsendusr="����";
		//������
		String from="enttest@126.com";
		//�ռ���
		String to="250289416@qq.com";
		//��Ҫ��ִ<0������1:Ҫ>
		String needrt="0";
		String priority="";
		Mail themail = null;
		themail=new Mail(mailsmtp,username,password,mailsmtpport);//����smtp������	    
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
		themail.setPriority(priority);//�����ʼ����ȼ�		
		if(themail.setTo(to)){
			String strReturnMsg=themail.sendoutEx();
		}
	}
} 
