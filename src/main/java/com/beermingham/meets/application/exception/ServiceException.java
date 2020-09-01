package com.beermingham.meets.application.exception;

public abstract class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4825546453193795408L;

	public ServiceException(String message, Throwable e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
