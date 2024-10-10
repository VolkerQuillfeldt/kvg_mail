package lib.mailservice.services;


import lib.mailservice.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PasswordService {

	@Value("${email.pw.andrea}")
	String passwordEncryptAndrea;

	@Value("${email.pw.volker}")
	String passwordEncryptVolker;

	@Value("${email.fromEmail.andrea}")
	String emailFromAndrea;


	@Value("${email.fromEmail.volker}")
	String emailFromVolker;

	@Value("${email.smtp.host.andrea}")
	String smtpHostAndrea;

	@Value("${email.smtp.port.andrea}")
	String smtpPortAndrea;



	@Value("${email.smtp.host.volker}")
	String smtpHostVolker;

	@Value("${email.smtp.port.volker}")
	String smtpPortVolker;
	
	public PasswordService() {
		/*
		 * Constructor
		 */
	}
	
	public String getEMailPassword(String host) {
		if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return decrypt(passwordEncryptAndrea);
		}
		return decrypt(passwordEncryptVolker);
	}
	
	
	private static String decrypt(String encstr) {

		if (encstr.length() > 12) {

			String cipher = encstr.substring(12);
			return new String(Base64.getDecoder().decode(cipher));
		}

		return null;
	}

	public String getEMailFromEMail(String host) {
		if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return emailFromAndrea;
		}
		return emailFromVolker;
	}

	public String getEMailSMTPHost(String host) {
		if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return smtpHostAndrea;
		}
		return smtpHostVolker;
	}

	public String getEMailSMTPPort(String host) {
		if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return smtpPortAndrea;
		}
		return smtpPortVolker;
	}
}
