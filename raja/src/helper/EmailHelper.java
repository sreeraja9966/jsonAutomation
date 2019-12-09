package helper;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import testBase.TestBase;
public class EmailHelper extends TestBase {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}
	
	public void sendEmailConfigurations(Message message) {

		try {
			String emailHost = "smtp.gmail.com";
			String fromUser = "sysnikautomationreport";//just the id alone without @gmail.com
			String fromUserEmailPassword = "sysnik@123";

			Transport transport = mailSession.getTransport("smtp");

			transport.connect(emailHost, fromUser, fromUserEmailPassword);
			transport.sendMessage(message, emailMessage.getAllRecipients());
			transport.close();
			log.info("Email sent successfully.");
		} catch (AddressException e) {
			log.error(e);
		}
		 catch (MessagingException e) {
				log.error(e);
			}
		
	}
	
	
	public  void sendReport(String recipients,String reportName,String attachmentName) {
		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		mailSession = Session.getDefaultInstance(emailProperties, null);
	        try {
        	emailMessage = new MimeMessage(mailSession);
            String[] recipientsA = recipients.split(",");
            InternetAddress[] internetAddress = new InternetAddress[recipientsA.length];
            for (int i = 0; i < recipientsA.length; i++) {
                internetAddress[i] = new InternetAddress(recipientsA[i]);
            }
            emailMessage.addRecipients(Message.RecipientType.TO, internetAddress);
            DateHelper dateHelper = new DateHelper();
            emailMessage.setSubject("Automation Test Report - " + dateHelper.getSystemDate("dd/mmm/yyyy"));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached Automation Test Report.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
         String filename = relativePath()+"\\Reports\\"+reportName+".html";
                 DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(attachmentName+".html");
         multipart.addBodyPart(messageBodyPart);
         emailMessage.setContent(multipart);
     	sendEmailConfigurations(emailMessage);

     } catch (MessagingException e) {
       log.error(e);
     }
	
		
	
	
	}	
		
		
		
}
