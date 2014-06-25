package test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test_mail {
	public static void main(String[] args) throws Exception {

		String hostname = "smtp.163.com";

		Properties props = System.getProperties();
		props.put("mail.transpot.protocol", "smtp");
		props.put("mail.store.protocol", "imap");
		props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
		props.put("mail.imap.class", "com.sun.mail.IMAPStore");
		props.put("mail.smtp.host", hostname);
		props.put("mail.smtp.auth", "true");

		Authenticator au = new Authenticator() {

			@ Override
			protected PasswordAuthentication getPasswordAuthentication() {

				// TODO Auto-generated method stub
				String username = "zhangfan2010aa@163.com";
				String password = "15034222583wo";
				return new PasswordAuthentication(username, password);
			}

		};
		Session mailsession = Session.getDefaultInstance(props, au);

		MimeMessage msg = new MimeMessage(mailsession);

		InternetAddress[] toAddrs = InternetAddress.parse("493972639@qq.com", false);

		msg.setRecipients(Message.RecipientType.TO, toAddrs);
		msg.setSubject("hello");
		msg.setFrom(new InternetAddress("zhangfan2010aa@163.com"));
		//msg.setText("<table border='1'><tbody><tr><td>aaaa</td></tr></tbody></table>");

		msg.setContent("<table border='1'><tbody><tr><td>aaaa</td></tr></tbody></table>", "text/html;charset=gb2312");
		Transport.send(msg);

		System.out.println("success");
	}
}
