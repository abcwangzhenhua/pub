package model;

import java.security.Security;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

public class MailRecStore {

		static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
		private String SSLFlg ;	
		private String mailPort ;
		private String popAddr ;
		private String userName ;
		private String passWord ;
		private Store  store ;
		private String portType;
		
		
		public void setPortType(String portType) {
			this.portType=portType;
		}
		
		/**
		 *  收邮件服务器类型，默认为Pop3 ，可选Pop3，Imap 
		 */
		public String getPortType() {
			return this.portType;
		}
		
		/**
		 * @param SSLFlg    SSL标记
		 * @param mailPort	端口
		 * @param popAddr	POP3或者IMAP地址
		 * @param userName	用户名
		 * @param passwd	密码
		 * @param revType   接收类型:POP IMAP Exchange add by tucy 2013-01-08
		 */
		public MailRecStore(String SSLFlg ,String mailPort,String popAddr,String userName,String passwd,String revType) {
			
			this.SSLFlg  =  SSLFlg;
			this.mailPort = mailPort;
			this.popAddr  = popAddr;
			revType = revType==null?"":revType.toString().trim();
			
			if( popAddr != null && popAddr.contains("imap")||"IMAP".equals(revType))
				setPortType("imap");
			else if("Exchange".equals(revType))//如果是 Exchange
				;
			else
				setPortType("pop3");
			
			this.userName = userName;
			this.passWord = passwd;
		}
		//老方法临时过渡，以后确定没问题就删除 add by tucy 2013-01-08
		public MailRecStore(String SSLFlg ,String mailPort,String popAddr,String userName,String passwd) {
			this.SSLFlg  =  SSLFlg;
			this.mailPort = mailPort;
			this.popAddr  = popAddr;
			
			if( popAddr != null && popAddr.contains("imap"))
				setPortType("imap");
			else
				setPortType("pop3");
			
			this.userName = userName;
			this.passWord = passwd;
		}
		/**
		 * 连接POP-IMAP服务器
		 * @Author : qicw
		 * @Date   : Jun 30, 2009 12:24:09 PM
		 */
		public void connect() throws MessagingException{
			
			Properties props = new Properties();
			props.put("mail."+getPortType()+".connectiontimeout", "200000");
			props.put("mail."+getPortType()+".timeout", "600000");
			props.put("mail."+getPortType()+".port",mailPort);
			
			if("Y".equals(SSLFlg)){

				Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				
				props.setProperty("mail."+getPortType()+".socketFactory.class", SSL_FACTORY);
				props.setProperty("mail."+getPortType()+".socketFactory.fallback", "false");
				props.setProperty("mail."+getPortType()+".socketFactory.port", mailPort);
				
				Session session = Session.getInstance(props, null);
				
				int portNum = getPortType().equals("imap") ? 993 : 995 ;
				
				try{
					portNum = Integer.parseInt (mailPort);
				} catch(Exception ex){}
				
				URLName urln = new URLName(getPortType(), popAddr, portNum, null, userName, passWord);
				store = session.getStore(urln);
				store.connect();
				
			} else {
				
				Session session = Session.getInstance(props, null);
				store = session.getStore(getPortType());
				System.out.println("正在连接邮件服务器:" + popAddr + "......");
				store.connect(popAddr, userName, passWord);
				
			}
			
		}
		
		public Store getStore() {
			if( store == null ) throw new NullPointerException("Store 未初始化");
			return store;
		}
		
		public void disConnect() throws MessagingException{
			if(store!=null && store.isConnected()) store.close();
		}
		
}
