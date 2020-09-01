package com.beermingham.meets.application.exception;

public class DuplicateEntityException extends ServiceException {

	private static final long serialVersionUID = -9165787882038421064L;

	public DuplicateEntityException(String message, Throwable e) {
        super(message, e);
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
