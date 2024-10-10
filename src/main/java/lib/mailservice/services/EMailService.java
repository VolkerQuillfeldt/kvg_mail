package lib.mailservice.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import lib.mailservice.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public
class EMailService {


  @Autowired
  private PasswordService pwService;

  @Autowired
  private EMailWriter eMailWriter;


  public void sendMail(MailDto mail, String host)
      throws MessagingException, UnsupportedEncodingException {

    final String password = pwService.getEMailPassword(host);
    final String fromEmail = pwService.getEMailFromEMail(host);
    final String smtpHost = pwService.getEMailSMTPHost(host);
    final String smtpPort = pwService.getEMailSMTPPort(host);

    Properties props = new Properties();
    props.put("mail.smtp.host", smtpHost); // SMTP Host
    props.put("mail.smtp.socketFactory.port", smtpPort); // SSL Port
    props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
    props.put("mail.smtp.starttls.enable", "true");//Compliant

    log.info("Mail send {} {} {} {}",smtpHost, smtpPort,fromEmail,password);

    Authenticator auth = new Authenticator() {
      // override the getPasswordAuthentication method
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(fromEmail, password);
      }
    };

    Session session = Session.getInstance(props, auth);

    eMailWriter.sendEmail(session, mail, fromEmail, host);
  }

}
