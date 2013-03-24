package mail;

//import javax.mail.internet.MimeUtility;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class JavaMailHelper {

	/**
	 * @param encoding		编码方式
	 * @param hostName		smtp的地址，如：163的为"smtp.163.com"
	 * @param addToEmail	目的邮箱
	 * @param addToName		目的用户名
	 * @param fromEmail		源邮箱
	 * @param fromName		源邮箱名
	 * @param pwd			源邮箱密码
	 * @param subject		主题
	 * @param textMsg		内容
	 * @param att		附件信息，  可以多个附件，以逗号隔开，例如    "c:\\test.jpg:一个文件,c:\\test1.jpg:两个文件"
	 * @return
	 */
	public static String htmlEmail(String encoding, String hostName,
			String addToEmail, String addToName, String fromEmail,String sender,
			String fromName, String pwd, String subject, String textMsg,String att) {
		HtmlEmail email = new HtmlEmail();
		try {
			if(!att.equals("")){
				String[] atts = att.split(",");
				for(String s:atts){
					String[]info = s.split(";");
					EmailAttachment attachment = new EmailAttachment();
					attachment.setPath(info[0]);
					attachment.setDisposition(EmailAttachment.ATTACHMENT);
					attachment.setName(info[1]);
					//attachment.setName(  MimeUtility.encodeText(info[1])  );
					email.attach(attachment);
				}
			}
			//hostName = ThreeDesUtil.decryptMode(hostName);
			//fromEmail = ThreeDesUtil.decryptMode(fromEmail);
			//fromName = ThreeDesUtil.decryptMode(fromName);
			//pwd = ThreeDesUtil.decryptMode(pwd);
			String uname = fromName;
			email.setHostName(hostName);
			email.addTo(addToEmail, addToName);
			email.setFrom(fromEmail, sender);
			email.setAuthentication(uname, pwd);
			email.setCharset(encoding);
			email.setSubject(subject);
			email.setMsg(textMsg);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 
	 * @param addToEmail	目的邮箱
	 * @param addToName		目的用户名
	 * @param subject		主题
	 * @param textMsg		内容
	 * @return
	 */
	public static String htmlEmail(String addToEmail, String addToName,
			String subject, String textMsg,String att) {
		return htmlEmail("UTF-8", MailConst.SMTP, addToEmail, addToName,
				MailConst.EMAIL, MailConst.SENDER, MailConst.USERNAME, MailConst.PASSWORD,
				subject, textMsg,att);
	}

	// 获取收件人地址
	public String getMailList(String[] mailArray) {
		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}
			}
		}
		return toList.toString();
	}

	public static void main(String[] args) {
		htmlEmail("entusr@126.com","USR","测试主题adf2","测试的内容2<img src=\"D:\\2.jpg\"/>","D:\\T.doc;T.doc");
	}
}