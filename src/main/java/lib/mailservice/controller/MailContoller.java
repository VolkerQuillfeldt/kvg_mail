package lib.mailservice.controller;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import lib.mailservice.dto.MailDto;
import lib.mailservice.dto.ResponseDto;
import lib.mailservice.services.EMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class MailContoller {

	@Autowired
	private EMailService service;

	@PostMapping("/sendmail")
	public ResponseEntity<ResponseDto> sendmail(@RequestBody MailDto mail) {
		try {
			service.sendMail(mail,mail.getHost());
			return new ResponseEntity<>(new ResponseDto("OK"), HttpStatus.OK);
		}catch (MessagingException | UnsupportedEncodingException e){
			log.error("Mail Send Error :", e);
			return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
