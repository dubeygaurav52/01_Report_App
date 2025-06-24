package in.gaurav.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String subject,String body,String to,File f) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper=new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Plans-Info", f);
			javaMailSender.send(message);
			
		}catch(Exception e) {
			
		}
		
		
		return true;
	}
}
