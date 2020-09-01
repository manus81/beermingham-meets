package com.beermingham.meets.domain.exception;

public class ValidationException extends DomainException {

	private static final long serialVersionUID = -8926869549507123451L;

	public ValidationException(String message, Throwable e) {
		super(message, e);
	}

	public ValidationException(String message) {
		super(message);
	}
}
