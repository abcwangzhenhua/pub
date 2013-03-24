/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MailConst.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-8           yixiugg                      initial
**/

package mail;
/**
 **MailConst.java
 **/
public class MailConst {
	public static String SMTP="smtp.126.com";
	public static String EMAIL="enttest@126.com";
	public static String PASSWORD="qwerty";
	public static String USERNAME="enttest";
	public static String SENDER="WTEST";
	public static String getSENDER() {
		return SENDER;
	}
	public static void setSENDER(String sENDER) {
		SENDER = sENDER;
	}
	public static String getSMTP() {
		return SMTP;
	}
	public static void setSMTP(String smtp) {
		SMTP = smtp;
	}
	public static String getEMAIL() {
		return EMAIL;
	}
	public static void setEMAIL(String email) {
		EMAIL = email;
	}
	public static String getPASSWORD() {
		return PASSWORD;
	}
	public static void setPASSWORD(String password) {
		PASSWORD = password;
	}
	public static String getUSERNAME() {
		return USERNAME;
	}
	public static void setUSERNAME(String username) {
		USERNAME = username;
	}
	

}

