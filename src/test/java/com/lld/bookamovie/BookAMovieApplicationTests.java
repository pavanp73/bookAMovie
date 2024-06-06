package com.lld.bookamovie;

import com.lld.bookamovie.controllers.UserController;
import com.lld.bookamovie.dtos.LoginRequestDto;
import com.lld.bookamovie.dtos.LoginResponseDto;
import com.lld.bookamovie.dtos.ResponseStatus;
import com.lld.bookamovie.dtos.SignupRequestDto;
import com.lld.bookamovie.dtos.SignupResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookAMovieApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSignup() {
		SignupRequestDto requestDto = new SignupRequestDto();
		requestDto.setName("Name1");
		requestDto.setEmail("name1@email.com");
		requestDto.setPassword("abcdef");

		SignupResponseDto responseDto = userController.signUp(requestDto);

		System.out.println(responseDto.getUserId());
	}

	@Test
	public void testLogin() {
		LoginRequestDto requestDto = new LoginRequestDto();
		requestDto.setEmail("name1@email.com");
		requestDto.setPassword("abcdef");

		LoginResponseDto responseDto = userController.login(requestDto);

		assertEquals(responseDto.getResponseStatus(), ResponseStatus.SUCCESS);
	}

}
