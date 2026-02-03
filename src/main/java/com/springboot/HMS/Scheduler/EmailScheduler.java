package com.springboot.HMS.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.HMS.Service.EmailService;

@Component
public class EmailScheduler {

	@Autowired
	private EmailService emailService;

	@Scheduled(cron = "0 0/10 * * * ?") 
	public void sendScheduledEmail() {

		String[] recipients = { ""};

		emailService.sendEmail(recipients, "Scheduled Email from Spring Boot",
				"Hello! This is a scheduled email sent automatically .");
	}
}