package com.group6.project;

import com.group6.project.concepts.relational.basic.CourseRepository;
import com.group6.project.relational.account.AccountRepository;
import org.springframework.boot.SpringApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class ProjectApplication {

	@Value("${environment}")
	private String env;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner showLogLevel() {
		return (args) -> {
			System.out.println(env);
			log.debug("Debug");
			log.info("Info");
			log.warn("Warning");
			log.error("Error");
		};
	}

	@Bean
	public CommandLineRunner showCourseList(AccountRepository repo) {
		return (args) -> {
			log.info("Num " + repo.count());
		};
	}

}
