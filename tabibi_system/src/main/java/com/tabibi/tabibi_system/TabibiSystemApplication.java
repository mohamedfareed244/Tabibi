package com.tabibi.tabibi_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@SpringBootApplication
public class TabibiSystemApplication {	
	public static void main(String[] args) {
		SpringApplication.run(TabibiSystemApplication.class, args);
	}

	@Bean JavaMailSenderImpl getMailSenderBean() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("tabibii.application@gmail.com");
        mailSender.setPassword("maga ltqn qnoi azhz");
		return mailSender;
	}
}
	