package br.com.uaijug.chronos.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendMail {
	
	@Resource(name = "java:jboss/mail/gmail")
	private Session session;

	public void send(String name, String addresses, String topic, String textMessage) {
		try {

			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(addresses));
			message.setSubject(topic);
			message.setText("Olá, " + name + "\n" + textMessage);

			Transport.send(message);

		} catch (MessagingException e) {
			Logger.getLogger(SendMail.class.getName()).log(Level.WARNING,
					"Cannot send mail", e);
		}
	}
}
