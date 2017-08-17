package in.artist.util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import in.artist.util.CommonLib;

public class Email {

	private static volatile Email sInstance;
	private final String HOST_NAME = "smtp.gmail.com";
	private final int PORT_NUMBER = 587;
	private final String START_TLS_ENABLED = "true";
	private final String AUTH_FLAG = "true";

	/**
	 * Empty constructor to prevent multiple objects in memory
	 */
	private Email() {
	}

	/**
	 * Implementation of double check'd locking scheme.
	 */
	public static Email getInstance() {

		if (sInstance == null) {
			synchronized (Email.class) {
				if (sInstance == null) {
					sInstance = new Email();
				}
			}
		}
		return sInstance;
	}

	/**
	 * Function - send a mail as per detailed in the model in a separate thread.
	 * 
	 * @param emailModel
	 */
	public void sendEmail(EmailModel emailModel) {

		Runnable runnable = new Runnable() {
			public void run() {
				System.setProperty("jsse.enableSNIExtension", "false");

				// Get system properties
				Properties properties = new Properties();

				// Setup mail server
				properties.setProperty("mail.smtp.host", HOST_NAME);
				properties.put("mail.smtp.starttls.enable", START_TLS_ENABLED);
				properties.put("mail.smtp.port", PORT_NUMBER);
				properties.put("mail.smtp.auth", AUTH_FLAG);
				properties.put("mail.smtp.debug", "false");

				// Get the default Session object.
				Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(CommonLib.ZAPP_ID, CommonLib.ZAPP_PWD);
					}
				});

				// Enable the debugger for any logs
				session.setDebug(false);

				try {
					// Create a MimeMessage object.
					MimeMessage message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(emailModel.getFrom()));

					// Set To: header field of the header.
					for (String sender : emailModel.getSenders()) {
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(sender));
					}

					// Set Subject: header field
					message.setSubject(emailModel.getSubject());

					// Set Content: header field
					message.setText(emailModel.getContent());

					// Let's try sending the object
					Transport.send(message);

				} catch (MessagingException mex) {
					mex.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread newThread = new Thread(runnable);
		newThread.start();
	}

	/**
	 * Function - send a mail as per detailed in the model in a separate thread.
	 * 
	 * @param emailModel
	 */
	public void sendHtmlEmail(EmailModel emailModel) {

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					HtmlEmail newemail = new HtmlEmail();
					newemail.setHostName("smtp.gmail.com");
					newemail.setSmtpPort(465);
					newemail.setAuthenticator(new DefaultAuthenticator(CommonLib.ZAPP_ID, CommonLib.ZAPP_PWD));
					newemail.setSSLOnConnect(true);
					newemail.setFrom(emailModel.getFrom());
					newemail.setSubject(emailModel.getSubject());
					newemail.setHtmlMsg(emailModel.getContent());
					for (String sender : emailModel.getSenders()) {
						newemail.addTo(sender);
					}
					newemail.send();
				} catch (EmailException mex) {
					mex.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread newThread = new Thread(runnable);
		newThread.start();
	}
}
