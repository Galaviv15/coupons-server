package com.gal.coupons.exceptions;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gal.coupons.dto.ServerErrorData;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;


// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	@ResponseBody
	public ServerErrorData customerExceptionResponse(HttpServletResponse httpServletResponse, Exception e) {

		if(e instanceof ServerException) {

			ServerException serverException = (ServerException) e;

			int errorCode = serverException.getErrorType().getErrorNumber();
			String errorMessage = serverException.getErrorType().getErrorMessage();
			String errorName = String.valueOf(serverException.getErrorType());
			httpServletResponse.setStatus(errorCode);

			if(serverException.getErrorType().isShowStackTrace()) {
				serverException.printStackTrace();
			}

			ServerErrorData serverErrorData = new ServerErrorData(errorCode, errorName ,errorMessage);
			return serverErrorData;
		}

		httpServletResponse.setStatus(601);
		ServerErrorData serverErrorData = new ServerErrorData(601,
				"Something went wrong, please try again later", "GENERAL_ERROR");

		return serverErrorData;
	}
}
