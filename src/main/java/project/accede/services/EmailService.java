package project.accede.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;


    public void sendMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String mailcontent = "Hello,\n" +
                    "\n" +
                    " \n" +
                    " \tYou recently tried to log into Accede. In order to complete your login, please use the code below\n" +
                    "\n" +
                    text +
                    " \t\n" +
                    "If this wasn't you, please change your password.\n" +
                    "\n" +
                    "Thank you,\n" +
                    "Accede";

            helper.setFrom("AccedeProject@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(mailcontent);
            emailSender.send(message);
        }
        catch (MessagingException e) {
            log.error("Mail sending failed: {}", e.getMessage());
        }
    }
}
