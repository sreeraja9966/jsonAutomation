package sysnik;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	static testBase.TestBase testBase = new testBase.TestBase();
	public static void main(String args[]) throws AddressException,
			MessagingException {

		SendEmail javaEmail = new SendEmail();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage();
	//	javaEmail.sendEmail();
	}

	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage()  
			 {
		try {
			String[] toEmails = { "sreeraja9966@gmail.com"};
			String emailSubject = "Java Email";
			String emailBody = "This is an email sent by JavaMail api.";

			mailSession = Session.getDefaultInstance(emailProperties, null);
			emailMessage = new MimeMessage(mailSession);

			for (int i = 0; i < toEmails.length; i++) {
				emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
			}

			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html");//for a html email
			//emailMessage.setText(emailBody);// for a text email
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void sendEmail(Message message) {

		try {
			String emailHost = "smtp.gmail.com";
			String fromUser = "sreeraja9966";//just the id alone without @gmail.com
			String fromUserEmailPassword = "sree@poori45";

			Transport transport = mailSession.getTransport("smtp");

			transport.connect(emailHost, fromUser, fromUserEmailPassword);
			transport.sendMessage(message, emailMessage.getAllRecipients());
			transport.close();
			System.out.println("Email sent successfully.");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public  void sendReport(String recipients) {
		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		mailSession = Session.getDefaultInstance(emailProperties, null);
		/*Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("@gmail.com", "");
                    }
                });*/
        try {
        	emailMessage = new MimeMessage(mailSession);
            String[] recipientsA = recipients.split(",");
            InternetAddress[] internetAddress = new InternetAddress[recipientsA.length];
            for (int i = 0; i < recipientsA.length; i++) {
                internetAddress[i] = new InternetAddress(recipientsA[i]);
            }
            emailMessage.addRecipients(Message.RecipientType.TO, internetAddress);
            emailMessage.setSubject("Test Report - " + LocalDateTime.now());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached test report.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
         String filename = testBase.relativePath()+"\\Reports\\Basic Testing.html";
         System.out.println(filename+"----------->filename");
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("Report.html");
         multipart.addBodyPart(messageBodyPart);
         emailMessage.setContent(multipart);
     	sendEmail(emailMessage);
        // Transport.send(message);
     } catch (MessagingException e) {
         e.printStackTrace();
     }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}	
}
