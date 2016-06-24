package util;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class SendEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Parameter(names = { "-h", "--help" }, help = true)
	boolean help;

	@Parameter(names = {"-v", "--version"}, description = "Version", required = false)
	boolean version = false;

	@Parameter(names = "-user", description = "User", required = true)
	String authuser;

	@Parameter(names = "-password", description = "Password", required = true, password = true)
	String authpwd;

	@Parameter(names = "-smtp.host", description = "SMTP Host", required = true)
	String smtpHostName;

	@Parameter(names = "-smtp.port", description = "SMTP Port", required = false)
	Integer smtpPort = 587;

	@Parameter(names = "-from", description = "From", required = true)
	String from;

	@Parameter(names = "-to", description = "To", required = true)
	String to;

	@Parameter(names = "-replyto", description = "Reply To", required = false)
	String replyTo;

	@Parameter(names = "-cc", description = "CC", required = false)
	String cc;

	@Parameter(names = "-bcc", description = "BCC", required = false)
	String bcc;

	@Parameter(names = "-subject", description = "Subject", required = true)
	String subject;

	@Parameter(names = "-html", description = "Html Message", required = true)
	String htmlMessage;

	@Parameter(names = "-debug", description = "Debug Mode", required = false)
	Boolean debug = true;

	@Parameter(names = "-tls", description = "TLS Enabled", required = false)
	Boolean tls = true;

	@Parameter(names = "-attachs", description = "Attachments", required = false)
	private List<String> attachments = new ArrayList<String>();

	void send() throws MalformedURLException, EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setSmtpPort(smtpPort);
		email.setAuthentication(authuser, authpwd);
		email.setDebug(debug);
		email.setStartTLSEnabled(tls);

		email.setHostName(smtpHostName);

		email.setFrom(from);

		if (replyTo != null)
			email.addReplyTo(replyTo);

		if (cc != null)
			email.addCc(cc.split(","));
		if (bcc != null)
			email.addBcc(bcc.split(","));

		email.addTo(to.split(","));

		email.setSubject(subject);

		for (String apath : attachments) {
			File file = new File(apath);
			URL url = file.toURI().toURL();

			EmailAttachment attachment = new EmailAttachment();
			attachment.setURL(url);
			attachment.setName(file.getName());
			String tagCid = "cid:" + file.getName();

			if (htmlMessage.contains(tagCid)) {
				String cid = email.embed(url, file.getName());
				htmlMessage = htmlMessage.replaceAll(tagCid, "cid:" + cid);

			} else {
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				email.attach(attachment);
			}

		}

		// set the html message
		email.setHtmlMsg(htmlMessage);

		// send the email
		email.send();
	}

	public static void main(String ... args) throws MalformedURLException, EmailException {
		SendEmail sendMail = new SendEmail();
		try {

			JCommander jCommander = new JCommander(sendMail, args);
			if (sendMail.help)
				jCommander.usage();
			else if (sendMail.version)
				System.out.format("Version: %d\n", serialVersionUID);
			else
				sendMail.send();

		} catch (ParameterException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}
}
