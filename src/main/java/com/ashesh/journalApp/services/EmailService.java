package com.ashesh.journalApp.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {

	@Autowired
	private final JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String body) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(to);
			mailMessage.setSubject(subject);
			mailMessage.setText(body);
			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			log.error("Error sending email: {}", e.getMessage());
		}
	}
}
