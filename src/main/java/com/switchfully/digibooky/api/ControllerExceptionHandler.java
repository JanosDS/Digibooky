package com.switchfully.digibooky.api;

import com.switchfully.digibooky.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UnauthorizedException.class)
	private void unauthorizedException(UnauthorizedException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
	}
}
