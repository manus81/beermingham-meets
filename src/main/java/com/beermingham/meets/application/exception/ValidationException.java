package com.beermingham.meets.application.exception;

public class ValidationException extends ServiceException {

	private static final long serialVersionUID = -2231470383279019445L;

	public ValidationException(String message, Throwable e) {
		super(message, e);
	}

	public ValidationException(String message) {
		super(message);
	}
}
