package com.custTODO.googleServices;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("serial")
public class GoogleMailAPI {
	public void MailAPI(String toEmail) {
		String fromEmail = "kishen.chandran@a-cti.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("kishen1011@gmail.com"));
			message.setSubject("Signup Confirmation");
			message.setContent(	"Hi,<br> Thank you for signing up to customer TODO application.<br> Create your <a href='http://customer-todos.appspot.com/'>TODO list</a> now.","text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Mail sent.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}