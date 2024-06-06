package com.lld.bookamovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookAMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAMovieApplication.class, args);
	}

}
