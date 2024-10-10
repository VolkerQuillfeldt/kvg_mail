package lib.mailservice.services;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lib.mailservice.dto.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class EMailWriter {

  @Value("${email.toEMail.andrea}")
  String emailToAndrea;

  @Value("${email.toEMail.volker}")
  String emailToVolker;

  public void sendEmail(Session session, MailDto mail, String fromEMail, String host)
      throws MessagingException, UnsupportedEncodingException {

    MimeMessage msg = new MimeMessage(session);
    // set message headers
    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
    msg.addHeader("format", "flowed");
    msg.addHeader("Content-Transfer-Encoding", "8bit");

    msg.setFrom(new InternetAddress(fromEMail, "kvk webseite " + host));
    msg.setReplyTo(InternetAddress.parse(fromEMail, false));
    msg.setSubject(mail.getSubject(), "UTF-8");
    msg.setContent(mail.getBody(), "text/html");

    String mailTo = emailToVolker;
    if (host.equals("andrea-schumacher.info") || host.contains("netify")) {
      mailTo = emailToAndrea;
    }

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false));

    Transport.send(msg);

  }
}
