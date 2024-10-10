package lib.mailservice.services;


import lib.mailservice.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PasswordService {

	@Value("${email.pw.andrea}")
	String passwordEncryptAndrea;

	@Value("${email.fromEmail.andrea}")
	String emailFromAndrea;

	@Value("${email.smtp.host.andrea}")
	String smtpHostAndrea;

	@Value("${email.smtp.port.andrea}")
	String smtpPortAndrea;

	@Value("${email.pw.volker}")
	String passwordEncryptVolker;

	@Value("${email.fromEmail.volker}")
	String emailFromVolker;

	@Value("${email.smtp.host.volker}")
	String smtpHostVolker;

	@Value("${email.smtp.port.volker}")
	String smtpPortVolker;

	@Value("${email.smtp.host.klingnau}")
	String smtpHostKlingnau;

	@Value("${email.smtp.port.klingnau}")
	String smtpPortKlingnau;

	@Value("${email.pw.klingnau}")
	String passwordEncryptKlingnau;

	@Value("${email.fromEmail.klingnau}")
	String emailFromKlingnau;
	
	public PasswordService() {
		/*
		 * Constructor
		 */
	}
	
	public String getEMailPassword(String host) {
		if( host.contains(Constants.URL_KVKLINGNAU)){
			return decrypt(passwordEncryptKlingnau);
		}else if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
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
		if( host.contains(Constants.URL_KVKLINGNAU)){
			return emailFromKlingnau;
		}else if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return emailFromAndrea;
		}
		return emailFromVolker;
	}

	public String getEMailSMTPHost(String host) {
		if( host.contains(Constants.URL_KVKLINGNAU)){
			return smtpHostKlingnau;
		}else if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return smtpHostAndrea;
		}
		return smtpHostVolker;
	}

	public String getEMailSMTPPort(String host) {
		if( host.contains(Constants.URL_KVKLINGNAU)){
			return smtpPortKlingnau;
		}else if( host.equals(Constants.URL_ANDREA) || host.contains(Constants.URL_NETIFY)){
			return smtpPortAndrea;
		}
		return smtpPortVolker;
	}
}
